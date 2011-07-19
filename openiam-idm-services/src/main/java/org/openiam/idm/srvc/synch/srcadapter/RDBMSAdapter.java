/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.synch.srcadapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleException;
import org.mule.module.client.MuleClient;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.service.MatchObjectRule;
import org.openiam.idm.srvc.synch.service.SourceAdapter;
import org.openiam.idm.srvc.synch.service.TransformScript;
import org.openiam.idm.srvc.synch.service.ValidationScript;
import org.openiam.idm.srvc.synch.util.DatabaseUtil;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.provision.service.ProvisionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Reads a CSV file for use during the synchronization process
 * @author suneet
 *
 */
public class RDBMSAdapter extends  AbstractSrcAdapter { // implements SourceAdapter  {

	protected LineObject rowHeader = new LineObject();
	protected ProvisionUser pUser = new ProvisionUser();
	public static ApplicationContext ac;
	protected LoginDataService loginManager;
	protected RoleDataService roleDataService;

	ProvisionService provService = null;
	String systemAccount;
	
	MatchRuleFactory matchRuleFactory;
	
	private static final Log log = LogFactory.getLog(RDBMSAdapter.class);
	private Connection con = null; 

	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			ac = applicationContext;
	}
	
	
	public SyncResponse startSynch(SynchConfig config) {
		// rule used to match object from source system to data in IDM
		MatchObjectRule matchRule = null;
		String changeLog = null;
		Date mostRecentRecord = null;
		IdmAuditLog synchUserStartLog = null;
		provService = (ProvisionService)ac.getBean("defaultProvision");
		
		log.debug("RDBMS SYNCH STARTED ^^^^^^^^");

         String requestId = UUIDGen.getUUID();

        IdmAuditLog synchStartLog = new IdmAuditLog();
        synchStartLog.setSynchAttributes("SYNCH_USER", config.getSynchConfigId(), "START", "SYSTEM", requestId);
        synchStartLog = auditHelper.logEvent(synchStartLog);

		
		if (!connect(config)) {
			SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.FAIL_SQL_ERROR);
			return resp;
		}
		
		try {		
			matchRule = matchRuleFactory.create(config);
		}catch(ClassNotFoundException cnfe) {
			log.error(cnfe);


                synchStartLog.updateSynchAttributes("FAIL",ResponseCode.CLASS_NOT_FOUND.toString() , cnfe.toString());
                auditHelper.logEvent(synchStartLog);

                SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
                resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
                return resp;
		}

		// execute the query
		StringBuffer sql = new StringBuffer ( config.getQuery() );	
		java.util.Date lastExec = null;
		
		if (config.getLastExecTime() != null) {
			lastExec =  config.getLastExecTime() ;
		}
			
		// if its incremental synch, then add the change log parameter
		if (config.getSynchType().equalsIgnoreCase("INCREMENTAL")) {
			if ((sql != null && sql.length() > 0) && (lastExec != null)) {
				changeLog = config.getQueryTimeField();
				String temp = sql.toString().toUpperCase();
				if (temp.contains("WHERE")) {
					sql.append(" AND ");
				}else {
					sql.append(" WHERE " );
				}
				sql.append( changeLog + " >= ?");	
			}
		}
		
		try {
			
			log.debug("-SYNCH SQL=" + sql.toString());
			log.debug("-last processed record =" + lastExec);

			
			
			PreparedStatement ps = con.prepareStatement(sql.toString());
			if (config.getSynchType().equalsIgnoreCase("INCREMENTAL") && (lastExec != null)) {
				ps.setTimestamp(1,  new Timestamp( lastExec.getTime() ) );
			}
			ResultSet rs = ps.executeQuery();
			
			// get the list of columns
			ResultSetMetaData rsMetadata = rs.getMetaData();
			DatabaseUtil.populateTemplate(rsMetadata, rowHeader);
			
			// test
			log.debug("Col. Header size = : " + rowHeader.getColumnMap().size());
			
			// Iterate through the resultset
            int ctr = 0;
			
			while ( rs.next()) {
                log.debug("-SYNCHRONIZING NEW RECORD ---" + ctr++);
				// make sure we have a new object for each row
				pUser = new ProvisionUser();
				
				LineObject rowObj = rowHeader.copy();
				DatabaseUtil.populateRowObject(rowObj, rs, changeLog);
				
				log.debug(" - Record update time=" + rowObj.getLastUpdate());
				
				if (mostRecentRecord == null) {
					mostRecentRecord = rowObj.getLastUpdate();
				}else {
					// if current record is newer than what we saved, then update the most recent record value
					
					if (mostRecentRecord.before(rowObj.getLastUpdate())) {
						log.debug("- MostRecentRecord value updated to=" + rowObj.getLastUpdate());
						mostRecentRecord.setTime(rowObj.getLastUpdate().getTime());
					}
				}
				
				// start the synch process 
				// 1) Validate the data
				// 2) Transform it
				// 3) if not delete - then match the object and determine if its a new object or its an udpate
				try {
					// validate
					if (config.getValidationRule() != null && config.getValidationRule().length() > 0) {
						ValidationScript script = SynchScriptFactory.createValidationScript(config.getValidationRule());
						int retval = script.isValid( rowObj );
						if (retval == ValidationScript.NOT_VALID ) {
							log.debug("Validation failed...");
							// log this object in the exception log
						}
						if (retval == ValidationScript.SKIP) {
							continue;
						}
					}
					
					// check if the user exists or not
					Map<String, Attribute> rowAttr = rowObj.getColumnMap();					
					//
					matchRule =  matchRuleFactory.create(config);
					User usr = matchRule.lookup(config, rowAttr);
					
		
					// transform
					if (config.getTransformationRule() != null && config.getTransformationRule().length() > 0) {
						TransformScript transformScript =  SynchScriptFactory.createTransformationScript(config.getTransformationRule());
						
						// initialize the transform script
						if (usr != null) {
							transformScript.setNewUser(false);
							transformScript.setUser( userMgr.getUserWithDependent(usr.getUserId(), true) );
							transformScript.setPrincipalList(loginManager.getLoginByUser(usr.getUserId()));
							transformScript.setUserRoleList(roleDataService.getUserRolesAsFlatList(usr.getUserId()));
							
						}else {
							transformScript.setNewUser(true);
                            transformScript.setUser(null);
                            transformScript.setPrincipalList(null);
                            transformScript.setUserRoleList(null);
						}
						
						int retval = transformScript.execute(rowObj, pUser);
						
						log.debug("- Transform result=" + retval);

                        // show the user object
                        log.debug("- User After Transformation =" + pUser);
                        log.debug("- User = " + pUser.getUserId() + "-" + pUser.getFirstName() + " " + pUser.getLastName());
                        log.debug("- User Attributes = " + pUser.getUserAttributes());

						pUser.setSessionId(synchStartLog.getSessionId());
						
						
						if (retval == TransformScript.DELETE && usr != null) {
							log.debug("deleting record - " + usr.getUserId());
							ProvisionUserResponse userResp = provService.deleteByUserId( new ProvisionUser( usr ), UserStatusEnum.DELETED, systemAccount);
							
						}else {					
							// call synch

							if (retval != TransformScript.DELETE) {

                                log.debug("-Provisioning user=" + pUser.getLastName());

								if (usr != null) {
									log.debug("-updating existing user...systemId=" + pUser.getUserId());
									pUser.setUserId(usr.getUserId());

                                    modifyUser(pUser);
									
								}else {
									log.debug("-adding new user...");

									pUser.setUserId(null);
                                    addUser(pUser);

									
								}
							}
						}
					}
									
					
				}catch(ClassNotFoundException cnfe) {
					log.error(cnfe);

                    synchStartLog.updateSynchAttributes("FAIL",ResponseCode.CLASS_NOT_FOUND.toString() , cnfe.toString());
                    auditHelper.logEvent(synchStartLog);


					SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
					resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
                    resp.setErrorText(cnfe.toString());

					return resp;
				}catch (IOException fe ) {

                    log.error(fe);

                    synchStartLog.updateSynchAttributes("FAIL",ResponseCode.FILE_EXCEPTION.toString() , fe.toString());
                    auditHelper.logEvent(synchStartLog);


                    SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
                    resp.setErrorCode(ResponseCode.FILE_EXCEPTION);
                    resp.setErrorText(fe.toString());
                    return resp;


                }
			}
						
		}catch(SQLException se) {
			log.error(se);
			closeConnection();

            synchStartLog.updateSynchAttributes("FAIL",ResponseCode.SQL_EXCEPTION.toString() , se.toString());
            auditHelper.logEvent(synchStartLog);


			SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.SQL_EXCEPTION);
			resp.setErrorText(se.toString());
			return resp;
		}finally {
			// mark the end of the synch
			IdmAuditLog synchEndLog = new IdmAuditLog();
			synchEndLog.setSynchAttributes("SYNCH_USER", config.getSynchConfigId(), "END", "SYSTEM", synchStartLog.getSessionId());
			auditHelper.logEvent(synchEndLog);			
		}
		
		log.debug("RDBMS SYNCH COMPLETE.^^^^^^^^");

		
		closeConnection();
		SyncResponse resp = new SyncResponse(ResponseStatus.SUCCESS);
		resp.setLastRecordTime(mostRecentRecord);
		return resp;
		
	}
	
/*	private void logEvent(ProvisionUserResponse userResp, User usr, IdmAuditLog synchStartLog, MatchObjectRule matchRule, String reqId, String action) {
		
		IdmAuditLog synchUserStartLog  = new IdmAuditLog();
		
		if (userResp.getStatus() == ResponseStatus.SUCCESS ) {
			
			synchUserStartLog.setSynchUserAttributes("USER", usr.getUserId(), action, "SUCCESS" ,"SYSTEM", 
					null, reqId, null, synchStartLog.getSessionId(), matchRule.getMatchAttrName(), matchRule.getMatchAttrValue());
		}else {
			synchUserStartLog.setSynchUserAttributes("USER", usr.getUserId(), action, "FAIL" ,"SYSTEM", 
					userResp.getErrorCode().toString() + ":" + userResp.getErrorText(), 
					reqId, null, synchStartLog.getSessionId(), matchRule.getMatchAttrName(), matchRule.getMatchAttrValue());
		}
		synchUserStartLog = auditHelper.logEvent(synchUserStartLog);
	}

*/
	private boolean connect(SynchConfig config) {
	  
		try {
			Class.forName(config.getDriver());
		
		con = DriverManager.getConnection(
			      config.getConnectionUrl(), 
			      config.getSrcLoginId(),
			      config.getSrcPassword());
		return true;
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ClassNotFoundException cf) {
			cf.printStackTrace();
		}
		return false;
		
	}
	private void closeConnection() {
		try {
			con.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}


	public MatchRuleFactory getMatchRuleFactory() {
		return matchRuleFactory;
	}


	public void setMatchRuleFactory(MatchRuleFactory matchRuleFactory) {
		this.matchRuleFactory = matchRuleFactory;
	}


	public String getSystemAccount() {
		return systemAccount;
	}


	public void setSystemAccount(String systemAccount) {
		this.systemAccount = systemAccount;
	}


	public LoginDataService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}


	public RoleDataService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}




	
}
