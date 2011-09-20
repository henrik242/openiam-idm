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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
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
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Reads a CSV file for use during the synchronization process
 * @author suneet
 *
 */
public class CSVAdapter implements SourceAdapter {

	protected LineObject rowHeader = new LineObject();
	protected ProvisionUser pUser = new ProvisionUser();
	public static ApplicationContext ac;
	protected LoginDataService loginManager;
	protected RoleDataService roleDataService;
	protected AuditHelper auditHelper;
	
	protected UserDataService userMgr;
	ProvisionService provService = null;
	String systemAccount;
	
	MatchRuleFactory matchRuleFactory;
	
	private static final Log log = LogFactory.getLog(CSVAdapter.class);
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}
	
	public SyncResponse startSynch(SynchConfig config) {

         log.debug("CSV startSynch CALLED.^^^^^^^^");


		
		Reader reader = null;
		
		MatchObjectRule matchRule = null;
		provService = (ProvisionService)ac.getBean("defaultProvision");

         String requestId = UUIDGen.getUUID();

        IdmAuditLog synchStartLog = new IdmAuditLog();
        synchStartLog.setSynchAttributes("SYNCH_USER", config.getSynchConfigId(), "START", "SYSTEM", requestId);
        synchStartLog = auditHelper.logEvent(synchStartLog);
		
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
	
		
		File file = new File(config.getFileName());
		try {
			reader = new FileReader(file);
		}catch(FileNotFoundException fe) {

			log.error(fe);

            synchStartLog.updateSynchAttributes("FAIL",ResponseCode.FILE_EXCEPTION.toString() , fe.toString());
            auditHelper.logEvent(synchStartLog);

			SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.FILE_EXCEPTION);
			return resp;
			
		}
		

		
		CSVParser parser = new CSVParser(reader, CSVStrategy.EXCEL_STRATEGY);
		try {
			int ctr = 0;
			String[][] fileContentAry =   parser.getAllValues();
			int size = fileContentAry.length;
			
			
			for (String[] lineAry : fileContentAry) {
				log.debug("File Row #= " + lineAry[0]);
				if (ctr == 0) {
					populateTemplate(lineAry);
					ctr++;
				}else {
					//populate the data object
					pUser = new ProvisionUser();
					
					LineObject rowObj = rowHeader.copy();
					populateRowObject(rowObj, lineAry);
					
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
							}
							
							int retval = transformScript.execute(rowObj, pUser);
							
							
							// temp code

							
							if (retval == TransformScript.DELETE && pUser.getUser() != null) {
								provService.deleteByUserId(pUser, UserStatusEnum.DELETED, systemAccount);
							}else {					
								// call synch
								if (retval != TransformScript.DELETE) {
									if (usr != null) {
										log.debug("updating existing user...systemId=" + pUser.getUserId());
										pUser.setUserId(usr.getUserId());
										provService.modifyUser(pUser);
									}else {
										pUser.setUserId(null);
										provService.addUser(pUser);
									}
								}
							}
						}
						// show the user object
										
						
					}catch(ClassNotFoundException cnfe) {
						log.error(cnfe);

                        synchStartLog.updateSynchAttributes("FAIL",ResponseCode.CLASS_NOT_FOUND.toString() , cnfe.toString());
                        auditHelper.logEvent(synchStartLog);

						SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
						resp.setErrorCode(ResponseCode.CLASS_NOT_FOUND);
						return resp;
					}
						
				}
			
			}
			
		}catch(IOException io) {

            synchStartLog.updateSynchAttributes("FAIL",ResponseCode.IO_EXCEPTION.toString() , io.toString());
            auditHelper.logEvent(synchStartLog);

            SyncResponse resp = new SyncResponse(ResponseStatus.FAILURE);
            resp.setErrorCode(ResponseCode.IO_EXCEPTION);
            return resp;


		}

        log.debug("CSV SYNCHRONIZATION COMPLETE^^^^^^^^");
		
		SyncResponse resp = new SyncResponse(ResponseStatus.SUCCESS);
		return resp;
		
	}
	
	private void populateTemplate(String[] lineAry) {
		Map<String,Attribute> columnMap = new HashMap<String, Attribute>();
		
		int ctr =0;
		for (String s  :lineAry) {
			Attribute a = new Attribute(s, null);
			a.setType("STRING");
			a.setColumnNbr(ctr);
			columnMap.put(a.getName(),a);
			ctr++;
		}
		rowHeader.setColumnMap(columnMap);
	}
	
	

	private void populateRowObject(LineObject rowObj ,String[] lineAry) {
		DateFormat df =  new SimpleDateFormat("MM-dd-yyyy"); 
		Map<String, Attribute> attrMap =  rowObj.getColumnMap();
		Set<String> keySet = attrMap.keySet();
		Iterator<String> it  = keySet.iterator();
		
		while ( it.hasNext()) {
			String key  = it.next();
			Attribute attr =  rowObj.get(key);
			int colNbr = attr.getColumnNbr();
			String colValue = lineAry[colNbr];
			
			
			attr.setValue(colValue);
		}

	}



	public static ApplicationContext getAc() {
		return ac;
	}

	public static void setAc(ApplicationContext ac) {
		CSVAdapter.ac = ac;
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

	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}

	public String getSystemAccount() {
		return systemAccount;
	}

	public void setSystemAccount(String systemAccount) {
		this.systemAccount = systemAccount;
	}

	public MatchRuleFactory getMatchRuleFactory() {
		return matchRuleFactory;
	}

	public void setMatchRuleFactory(MatchRuleFactory matchRuleFactory) {
		this.matchRuleFactory = matchRuleFactory;
	}

	public AuditHelper getAuditHelper() {
		return auditHelper;
	}

	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}

    public void setMuleContext(MuleContext ctx) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
