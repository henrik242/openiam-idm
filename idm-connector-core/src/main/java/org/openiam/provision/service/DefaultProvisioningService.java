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
package org.openiam.provision.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.SysConfiguration;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDAO;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.pswd.service.PasswordGenerator;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.AccountLockEnum;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.dto.ProvisionGroup;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.PasswordResponse;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.provision.service.ProvisionService", 
		targetNamespace = "http://www.openiam.org/service/provision", 
		portName = "DefaultProvisionControllerServicePort", 
		serviceName = "ProvisioningService")
public class DefaultProvisioningService implements ProvisionService,  ApplicationContextAware {

	protected static final Log log = LogFactory.getLog(DefaultProvisioningService.class);

	// used to inject the application context into the groovy scripts
	public static ApplicationContext ac;

	
	protected UserDataService userMgr;
	protected LoginDataService loginManager;
	protected LoginDAO loginDao;
	
	protected IdmAuditLogDataService auditDataService;
	protected ManagedSystemDataService managedSysService;
	protected RoleDataService roleDataService;
	protected GroupDataService groupManager;
	protected String connectorWsdl;
	protected String defaultProvisioningModel;
	protected SysConfiguration sysConfiguration;
	protected ResourceDataService resourceDataService;
	protected String scriptEngine;
	protected OrganizationDataService orgManager;
	protected PasswordService passwordDS;
	protected AddUser addUser;
	protected ModifyUser modifyUser;	
	protected AuditHelper auditHelper;
	protected AttributeListBuilder attrListBuilder;
	protected ConnectorAdapter connectorAdapter;

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#addGroup(org.openiam.provision.dto.ProvisionGroup)
	 */
	public ProvisionGroup addGroup(ProvisionGroup group) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#addUser(org.openiam.provision.dto.ProvisionUser)
	 */
	public ProvisionUserResponse addUser(ProvisionUser user) {
		ProvisionUserResponse resp = new ProvisionUserResponse();
		
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		String requestId = UUIDGen.getUUID();
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.FAIL_OTHER);
			return resp;
		}
		
	
		if (user.getUser().getCompanyId() != null) {
			org = orgManager.getOrganization(user.getUser().getCompanyId());
		}
		// bind the objects to the scripting engine
		
		bindingMap.put("sysId", sysConfiguration.getDefaultManagedSysId());
		bindingMap.put("user", user);
		bindingMap.put("org", org);		
		bindingMap.put("context", ac);
		
		// CREATE THE PRIMARY IDENTITY IF IT HAS NOT BEEN PASSED IN
	 	if ( user.getPrincipalList() == null || user.getPrincipalList().isEmpty()) {
	 		// build the list
	 		addUser.buildPrimaryPrincipal(user, bindingMap, se);
	 		
	 	}
	 	
		/* Create the new user in the openiam repository */
		resp = addUser.createUser(user);

		Login primaryLogin = user.getPrimaryPrincipal(sysConfiguration.getDefaultManagedSysId());
		// need decrypted password for use in the connectors:
		String decPassword = loginManager.decryptPassword(primaryLogin.getPassword());
		
		bindingMap.put("lg", primaryLogin);
		bindingMap.put("password", decPassword);
		
		log.info("Primary identity=" + primaryLogin);
		
		String logId = auditHelper.addLog("CREATE", user.getSecurityDomain(),	primaryLogin.getId().getLogin(),
				"IDM SERVICE", user.getCreatedBy(), "0", "USER", user.getUserId(), 
				null,   "SUCCESS", null,  "USER_STATUS", 
				user.getUser().getStatus().toString(),
				requestId, null);
		
		// provision the user into the systems that they should have access to.

		// get the list of resources for each role that user belongs too.

		
		
		List<Resource> resourceList =  getResourcesForRole(user.getMemberOfRoles());
		if (resourceList != null) {
			for ( Resource res : resourceList) {
				String managedSysId =  res.getManagedSysId();
								
				// object that will be sent to the connectors
				List<AttributeMap> attrMap = resourceDataService.getResourceAttributeMaps(res.getResourceId());
						
				
				ManagedSys mSys = managedSysService.getManagedSys(managedSysId);
				
			 	
				ManagedSystemObjectMatch matchObj = null;
			 	ManagedSystemObjectMatch[] matchObjAry = managedSysService.managedSysObjectParam(managedSysId, "USER");
			 	if (matchObjAry != null && matchObjAry.length > 0 ) {
			 		matchObj = matchObjAry[0];
			 		bindingMap.put("matchParam", matchObj);
			 	}

			 	log.info("Building attributes for managedSysId =" + managedSysId);
				ExtensibleUser extUser = attrListBuilder.buildFromRules(user, attrMap, se, 
						managedSysId, sysConfiguration.getDefaultSecurityDomain(),
						bindingMap, user.getCreatedBy() );	
			 	
				// build the request
	            AddRequestType addReqType = new AddRequestType();
	            // get the identity linked to this resource / managedsys
	            Login mLg = getPrincipalForManagedSys(mSys, user.getPrincipalList());
	            mLg.setPassword(primaryLogin.getPassword());
	            mLg.setUserId(primaryLogin.getUserId());
	            
	            log.info("Creating identity in openiam repository:" + mLg.getId());
	            loginManager.addLogin(mLg);
	            
	            PSOIdentifierType idType = new PSOIdentifierType(mLg.getId().getLogin(),null, "target");
	            addReqType.setPsoID(idType);
	            requestId = "R"+UUIDGen.getUUID();
	            addReqType.setRequestID(requestId);
	            addReqType.setTargetID(mLg.getId().getManagedSysId());
	            addReqType.getData().getAny().add(extUser);
	             		
	            log.info("Creating identity in target system:" + mLg.getId());
				connectorAdapter.addRequest(mSys, addReqType);

				auditHelper.addLog("CREATE", user.getSecurityDomain(),	mLg.getId().getLogin(),
						"IDM SERVICE", user.getCreatedBy(), mLg.getId().getManagedSysId(),
						"USER", user.getUserId(), 
						logId,   "SUCCESS", null,  "USER_STATUS", 
						user.getUser().getStatus().toString(),
						requestId, null);
				bindingMap.remove(matchObj);
				
			}
			
		}
		
		
		/* Response object */
		resp.setStatus(ResponseStatus.SUCCESS);
		resp.setUser(user);
		return resp;
	}
	
	private Login getPrincipalForManagedSys(ManagedSys mSys, List<Login> principalList) {
		if (principalList == null) {
			return null;
		}
		for ( Login l : principalList) {
			if (mSys != null && mSys.getManagedSysId() != null) {
				if (l.getId().getManagedSysId().equalsIgnoreCase(mSys.getManagedSysId())) {
					return l;
				}
			}
			
		}
		return null;
		
	}
		

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#deleteGroup(java.lang.String)
	 */
	public ProvisionGroup deleteGroup(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#deleteUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ProvisionUserResponse deleteUser(String securityDomain,
			String managedSystemId, String principal, UserStatusEnum status,
			String requestorId ) {
		log.info("----deleteUser called.------");
		
		ProvisionUserResponse response = new ProvisionUserResponse(ResponseStatus.SUCCESS);
		
		if (status != UserStatusEnum.DELETED && 
				status != UserStatusEnum.LEAVE & 
				status != UserStatusEnum.TERMINATE && 
				status != UserStatusEnum.RETIRED) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_STATUS);
			return response;			
		}
		
	
		String requestId = "R" + UUIDGen.getUUID();
		
		// get the user object associated with this principal
		Login login = loginManager.getLoginByManagedSys(securityDomain,
				principal, managedSystemId);
		if (login == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
			return response;
		}
		// check if the user active
		String userId = login.getUserId();
		if (userId == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_NOT_FOUND);	
			return response;
		}
		User usr = this.userMgr.getUserWithDependent(userId, false);
		if (usr == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_NOT_FOUND);	
			return response;			
		}
		if (usr.getStatus() == UserStatusEnum.DELETED || 
				usr.getStatus() == UserStatusEnum.TERMINATE) {
			log.info("User was already deleted. Nothing more to do.");
			return response;
		}
		
		if (!managedSystemId.equalsIgnoreCase(sysConfiguration.getDefaultManagedSysId())) {
			// managedSysId point to one of the seconardary identities- just terminate that identity
			login.setStatus("INACTIVE");
			login.setAuthFailCount(0);
			login.setPasswordChangeCount(0);
			login.setIsLocked(0);
			loginManager.updateLogin(login);
			// call delete on the connector
			ManagedSys mSys = managedSysService.getManagedSys(managedSystemId);
			
            PSOIdentifierType idType = new PSOIdentifierType(principal,null,managedSystemId   		);
            
			DeleteRequestType reqType = new DeleteRequestType();
			reqType.setRequestID(requestId);
			reqType.setPsoID(idType);

            connectorAdapter.deleteRequest(mSys, reqType);
			
		}else {
			// delete user and all its identities.
			usr.setStatus(status);
			usr.setSecondaryStatus(null);
			usr.setLastUpdatedBy(requestorId);
			usr.setLastUpdate(new Date(System.currentTimeMillis()));
			userMgr.updateUserWithDependent(usr, false);
			
		
			// update the identities and set them to inactive
			List<Login> principalList = loginManager.getLoginByUser(userId);
			if ( principalList != null) {
				for (Login l : principalList) {
					if (l.getStatus() != null && !l.getStatus().equalsIgnoreCase("INACTIVE")) {
						l.setStatus("INACTIVE");
						l.setAuthFailCount(0);
						l.setPasswordChangeCount(0);
						l.setIsLocked(0);				
						loginManager.updateLogin(l);
						
						// only add the connectors if its a secondary identity.
						if (!l.getId().getManagedSysId().equalsIgnoreCase(this.sysConfiguration.getDefaultManagedSysId())) {
						
							ManagedSys mSys = managedSysService.getManagedSys(l.getId().getManagedSysId());
							
							log.info("Deleting id=" + l.getId().getLogin());
							log.info("- delete using managed sys id=" + mSys.getManagedSysId());
							
				            PSOIdentifierType idType = new PSOIdentifierType(l.getId().getLogin(),null,
				            		l.getId().getManagedSysId()  		);
				            
							DeleteRequestType reqType = new DeleteRequestType();
							reqType.setRequestID(requestId);
							reqType.setPsoID(idType);
	
				            connectorAdapter.deleteRequest(mSys, reqType);
						}
					}
					
				}
			}
			
		}
		
		response.setStatus(ResponseStatus.SUCCESS);
		return response;

	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#disableUser(java.lang.String, boolean)
	 */
	public Response disableUser(String userId, boolean operation) {
		// get the user
		User user = userMgr.getUserWithDependent(userId, false);
		if (user == null) {
			log.error("UserId " + userId + " not found");
			Response resp = new Response();
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.OBJECT_NOT_FOUND);
			return resp;

		}
		if (operation) {
			user.setSecondaryStatus(UserStatusEnum.DISABLED);
		}else {
			user.setSecondaryStatus(null);
		}
		userMgr.updateUserWithDependent(user,false);
		
		Response resp = new Response();
		resp.setStatus(ResponseStatus.SUCCESS);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#lockUser(java.lang.String, org.openiam.provision.dto.AccountLockEnum)
	 */
	public Response lockUser(String userId, AccountLockEnum operation, String requestorId) {
		String auditReason = null;
		
		if (userId == null) {
			throw new NullPointerException("userId is null");
		}
		if (operation == null) {
			throw new NullPointerException("Operation parameter is null");
		}
		
		User user = userMgr.getUserWithDependent(userId, false);
		if (user == null) {
			log.error("UserId " + userId + " not found");
			Response resp = new Response();
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.OBJECT_NOT_FOUND);
			return resp;

		}
		Login lg = loginManager.getPrimaryIdentity(userId);

		if (operation.equals(AccountLockEnum.LOCKED)) {
			user.setSecondaryStatus(UserStatusEnum.LOCKED);
			if (lg != null) {
				log.info("Identity flag set to locked.");
				lg.setIsLocked(1);
			}
			auditReason = "LOCKED";
		}else if (operation.equals(AccountLockEnum.LOCKED_ADMIN)) {
			user.setSecondaryStatus(UserStatusEnum.LOCKED_ADMIN);
			if (lg != null) {
				lg.setIsLocked(2);
			}
			auditReason = "LOCKED_ADMIN";
		}else {
			user.setSecondaryStatus(null);
			if (lg == null) {
				log.error("Primary identity for UserId " + userId + " not found");
				Response resp = new Response();
				resp.setStatus(ResponseStatus.FAILURE);
				resp.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
				return resp;				
			}
			lg.setAuthFailCount(0);
			lg.setIsLocked(0);
			auditReason = "UNLOCK";
		}
		loginManager.updateLogin(lg);
		userMgr.updateUserWithDependent(user,false);
		
		String requestId = "R" + UUIDGen.getUUID();
		
		auditHelper.addLog(auditReason, lg.getId().getDomainId(),	lg.getId().getLogin(),
				"IDM SERVICE",requestorId, "USER",	"USER", user.getUserId(),null, "SUCCESS", null,  null, 
				null,
				requestId, auditReason);
	
		
		
		Response resp = new Response();
		resp.setStatus(ResponseStatus.SUCCESS);
		return resp;

	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#modifyGroup(org.openiam.provision.dto.ProvisionGroup)
	 */
	public ProvisionGroup modifyGroup(ProvisionGroup group) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#modifyUser(org.openiam.provision.dto.ProvisionUser)
	 */
	public ProvisionUserResponse modifyUser(ProvisionUser pUser) {
		ProvisionUserResponse resp = new ProvisionUserResponse();		
		String requestId = "R" + UUIDGen.getUUID();
		ScriptIntegration se = null;
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		Organization org = null;
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			log.error(e);
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.FAIL_OTHER);
			return resp;
		}
		if (pUser.getUser().getCompanyId() != null) {
			org = orgManager.getOrganization(pUser.getUser().getCompanyId());
		}
		// bind the objects to the scripting engine
		
		bindingMap.put("sysId", sysConfiguration.getDefaultManagedSysId());
		bindingMap.put("user", pUser.getUser());
		bindingMap.put("org", org);		
		bindingMap.put("context", ac);
		
		
		// get the current user object - update it with the new values and then save it
		User origUser = userMgr.getUserWithDependent(pUser.getUserId(), true);
		if (origUser == null || origUser.getUserId() == null) {
			throw new IllegalArgumentException("UserId is not valid. UserId=" + pUser.getUserId());
		}

		// origUser2 is used for comparison purposes in the sync process
		User currentUser2 = UserAttributeHelper.cloneUser(origUser);
		
		List<Role> curRoleList = roleDataService.getUserRolesAsFlatList(pUser.getUserId());
		List<Group> curGroupList = this.groupManager.getUserInGroupsAsFlatList(pUser.getUserId());
		List<Login> curPrincipalList = this.loginManager.getLoginByUser(pUser.getUserId());
		
		
		// update the openiam repository with the new user information
		modifyUser.updateUser(pUser, origUser);

		// update the supervisor
		modifyUser.updateSupervisor(origUser, pUser.getSupervisor()); 
		
		// update the group
		modifyUser.updateGroupAssociation(origUser.getUserId(), curGroupList, pUser.getMemberOfGroups());
		
		// update the role association
		modifyUser.updateRoleAssociation(origUser.getUserId(), curRoleList, pUser.getMemberOfRoles());
		
		// update the principal list
		log.info("Principals in request2=" + pUser.getPrincipalList());
		modifyUser.updatePrincipalList(origUser.getUserId(), curPrincipalList,  pUser.getPrincipalList());
		
		// get primary identity and bind it for the groovy scripts
		Login primaryIdentity = modifyUser.getPrimaryIdentity(this.sysConfiguration.getDefaultManagedSysId());
		String password = primaryIdentity.getPassword();
		String decPassword = loginManager.decryptPassword(password);
		bindingMap.put("lg", primaryIdentity);
		bindingMap.put("password", decPassword);

		// call the connector and provision to ldap
		
		List<Resource> resourceList =  getResourcesForRole(modifyUser.getRoleList());
		if (resourceList != null) {
			for ( Resource res : resourceList) {
				String managedSysId =  res.getManagedSysId();
								
				// object that will be sent to the connectors
				List<AttributeMap> attrMap = resourceDataService.getResourceAttributeMaps(res.getResourceId());
						
				
				ManagedSys mSys = managedSysService.getManagedSys(managedSysId);
			 	
				ManagedSystemObjectMatch matchObj = null;
			 	ManagedSystemObjectMatch[] matchObjAry = managedSysService.managedSysObjectParam(managedSysId, "USER");
			 	if (matchObjAry != null && matchObjAry.length > 0 ) {
			 		matchObj = matchObjAry[0];
			 		bindingMap.put("matchParam", matchObj);
			 	}
				// build the request
				ModifyRequestType modReqType = new ModifyRequestType();
	            // get the identity linked to this resource / managedsys
	            Login mLg = getPrincipalForManagedSys(mSys, modifyUser.getPrincipalList());
	            
	           // bindingMap.put("lg", mLg);
			 	
			 	log.info("Building attributes for managedSysId =" + managedSysId);
				
				ExtensibleUser extUser = attrListBuilder.buildModifyFromRules(pUser, 
						mLg, attrMap, se, managedSysId, mLg.getId().getDomainId(), bindingMap, 
						pUser.getUser().getLastUpdatedBy());
				
				modifyUser.updateAttributeList(extUser);
				
	            PSOIdentifierType idType = new PSOIdentifierType(mLg.getId().getLogin(),null, "target");
	            idType.setTargetID(mLg.getId().getManagedSysId());
	            modReqType.setPsoID(idType);
	            modReqType.setRequestID(requestId);

	            ModificationType mod = new ModificationType();
	            mod.getData().getAny().add(extUser);
	            
	            List<ModificationType> modTypeList =  modReqType.getModification();
	            modTypeList.add(mod);
       	             		
	            log.info("Creating identity in target system:" + mLg.getId());
				connectorAdapter.modifyRequest(mSys,modReqType);


				bindingMap.remove(matchObj);
				//bindingMap.remove(mLg);
				
			}
			
		}
		
		
		// ---
		
		/* Response object */
		resp.setStatus(ResponseStatus.SUCCESS);
		resp.setUser(pUser);
		return resp;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#resetPassword(org.openiam.provision.dto.PasswordSync)
	 */
	public PasswordResponse resetPassword(PasswordSync passwordSync) {
		log.info("----resetPassword called.------");
		
		PasswordResponse response = new PasswordResponse(ResponseStatus.SUCCESS);
		
		String requestId = "R" + UUIDGen.getUUID();
		
		// get the user object associated with this principal
		Login login = loginManager.getLoginByManagedSys(passwordSync.getSecurityDomain(),
				passwordSync.getPrincipal(), passwordSync.getManagedSystemId());
		if (login == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
			return response;
		}
		// check if the user active
		String userId = login.getUserId();
		if (userId == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_NOT_FOUND);	
			return response;
		}
		User usr = this.userMgr.getUserWithDependent(userId, false);
		if (usr == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_NOT_FOUND);	
			return response;			
		}
		
		String password = passwordSync.getPassword();
		if (password == null || password.length() ==0) {
			// autogenerate the password
			password = String.valueOf( PasswordGenerator.generatePassword(8));
		}
		String encPassword = loginManager.encryptPassword(password);
		boolean retval = loginManager.resetPassword(passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
				passwordSync.getManagedSystemId(), encPassword);
		
		if (retval) {
			log.info("-Password changed in openiam repository for user:" + passwordSync.getPrincipal());
			
			auditHelper.addLog("RESET PASSWORD", passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					"IDM SERVICE", passwordSync.getRequestorId(), "PASSWORD", "PASSWORD",null, null,  "SUCCESS", null,  null, 
					null,
					requestId, null);
			
		} else {
			auditHelper.addLog("RESET PASSWORD", passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					"IDM SERVICE", passwordSync.getRequestorId(), "PASSWORD", "PASSWORD",null, null,  "FAILURE", null,  null, 
					null,requestId, null);
			
			Response resp = new Response();
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
		}
		
		
		if ( passwordSync.getManagedSystemId().equalsIgnoreCase(this.sysConfiguration.getDefaultManagedSysId()) ) {
			// typical sync
			List<Login> principalList = loginManager.getLoginByUser(login.getUserId());
			if (principalList != null) {
				log.info("PrincipalList size =" + principalList.size());
				for ( Login lg : principalList) {
					// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
					log.info("**** Managed System Id in passwordsync object=" + passwordSync.getManagedSystemId());
					
					if (!lg.getId().getManagedSysId().equalsIgnoreCase(passwordSync.getManagedSystemId()) && 
						!lg.getId().getManagedSysId().equalsIgnoreCase(sysConfiguration.getDefaultManagedSysId())) {
						
						// determine if you should sync the password or not
						String managedSysId = lg.getId().getManagedSysId();
						Resource res =  resourceDataService.getResource(managedSysId);
						
						log.info(" - managedsys id = " + managedSysId);
						log.info(" - Resource for sysId =" + res);
						
						// check the sync flag
										
						if (syncAllowed(res)) {
						
							log.info("Sync allowed for sys=" + managedSysId);
							retval = loginManager.resetPassword(lg.getId().getDomainId(),
									lg.getId().getLogin(), lg.getId().getManagedSysId(),
									encPassword);
							
							ManagedSys mSys = managedSysService.getManagedSys(managedSysId);
							
							// call set password since we are setting the password.
							// IN the spml spec, a reset on the connector require that it generate a new password
							SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
			                PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null,
			                		mSys.getManagedSysId()   		);
			                pswdReqType.setPsoID(idType);
			                pswdReqType.setRequestID(requestId);
			                pswdReqType.setPassword(password);
			                connectorAdapter.setPasswordRequest(mSys, pswdReqType);
						}
					}
				}
			}

		}else {
			// update just the system that as specific
			

			
			ManagedSys mSys = managedSysService.getManagedSys(passwordSync.getManagedSystemId());
			
			// call set password since we are setting the password.
			// IN the spml spec, a reset on the connector require that it generate a new password
			SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
            PSOIdentifierType idType = new PSOIdentifierType(login.getId().getLogin(),null,
            		mSys.getManagedSysId()   		);
            pswdReqType.setPsoID(idType);
            pswdReqType.setRequestID(requestId);
            pswdReqType.setPassword(password);
            connectorAdapter.setPasswordRequest(mSys, pswdReqType);

		}
		


		
		response.setStatus(ResponseStatus.SUCCESS);
		return response;

	}

	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#setPassword(org.openiam.provision.dto.PasswordSync)
	 */
	public Response setPassword(PasswordSync passwordSync) {
		log.info("----setPassword called.------");
		
		Response response = new Response(ResponseStatus.SUCCESS);
		
		String requestId = "R" + UUIDGen.getUUID();
		
		// get the user object associated with this principal
		Login login = loginManager.getLoginByManagedSys(passwordSync.getSecurityDomain(),
				passwordSync.getPrincipal(), passwordSync.getManagedSystemId());
		if (login == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
			return response;
		}
		// check if the user active
		String userId = login.getUserId();
		if (userId == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_NOT_FOUND);	
			return response;
		}
		User usr = this.userMgr.getUserWithDependent(userId, false);
		if (usr == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_NOT_FOUND);	
			return response;			
		}
		
		String encPassword = loginManager.encryptPassword(passwordSync.getPassword());
		boolean retval = loginManager.setPassword(passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
				passwordSync.getManagedSystemId(), encPassword);
		if (retval) {
			log.info("-Password changed in openiam repository for user:" + passwordSync.getPrincipal());
			
			auditHelper.addLog("SET PASSWORD", passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					"IDM SERVICE", passwordSync.getRequestorId(), "PASSWORD", "PASSWORD",null, null,  "SUCCESS", null,  null, 
					null,
					requestId, null);
			
		} else {
			auditHelper.addLog("SET PASSWORD", passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					"IDM SERVICE", passwordSync.getRequestorId(), "PASSWORD", "PASSWORD",null, null,  "FAILURE", null,  null, 
					null,requestId, null);
			
			Response resp = new Response();
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
		}

		if ( passwordSync.getManagedSystemId().equalsIgnoreCase(this.sysConfiguration.getDefaultManagedSysId()) ) {
			// typical sync
			List<Login> principalList = loginManager.getLoginByUser(login.getUserId());
			if (principalList != null) {
				log.info("PrincipalList size =" + principalList.size());
				for ( Login lg : principalList) {
					// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
					log.info("**** Managed System Id in passwordsync object=" + passwordSync.getManagedSystemId());
					
					if (!lg.getId().getManagedSysId().equalsIgnoreCase(sysConfiguration.getDefaultManagedSysId())) {
						
						// determine if you should sync the password or not
						String managedSysId = lg.getId().getManagedSysId();
						Resource res =  resourceDataService.getResource(managedSysId);
						
						log.info(" - managedsys id = " + managedSysId);
						log.info(" - Resource for sysId =" + res);
						
						// check the sync flag
										
						if (syncAllowed(res)) {
						
							log.info("Sync allowed for sys=" + managedSysId);
							retval = loginManager.setPassword(lg.getId().getDomainId(),
									lg.getId().getLogin(), lg.getId().getManagedSysId(),
									encPassword);
							
							ManagedSys mSys = managedSysService.getManagedSys(managedSysId);
							
			            	SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
			                PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null,
			                		mSys.getManagedSysId()   		);
			                pswdReqType.setPsoID(idType);
			                pswdReqType.setRequestID(requestId);
			                pswdReqType.setPassword(passwordSync.getPassword());
			                this.connectorAdapter.setPasswordRequest(mSys, pswdReqType);
						}
					}
				}
			}
		}else {
			// just the update the managed system that was specified.
			ManagedSys mSys = managedSysService.getManagedSys(passwordSync.getManagedSystemId());
			
			// call set password since we are setting the password.
			// IN the spml spec, a reset on the connector require that it generate a new password
			SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
            PSOIdentifierType idType = new PSOIdentifierType(login.getId().getLogin(),null,
            		mSys.getManagedSysId()   		);
            pswdReqType.setPsoID(idType);
            pswdReqType.setRequestID(requestId);
            pswdReqType.setPassword(passwordSync.getPassword());
            connectorAdapter.setPasswordRequest(mSys, pswdReqType);
			
		}
		response.setStatus(ResponseStatus.SUCCESS);
		return response;
		
	}

		

	/************ Helper Methods --------------- */
	
	private boolean syncAllowed(Resource res) {
		Set<ResourceProp> resPropSet = null;
		String syncFlag = null;
		
		if (res != null) {
			resPropSet =  res.getResourceProps();
			syncFlag = getResProperty(resPropSet, "INCLUDE_PSWD_SYNC");
			log.info(" - SyncFlag=" + syncFlag);
		}
		if ( res == null) {
			return true;
		}else {
			if (syncFlag == null || !syncFlag.equalsIgnoreCase("N")) {
				log.info(" - Sync allowed=true" );
				return true;
			}
		}	
		return false;
	}
	
	private String getResProperty(Set<ResourceProp> resPropSet, String propertyName) {
		String value = null;
		
		if (resPropSet == null) {
			return null;
		}
		Iterator<ResourceProp> propIt = resPropSet.iterator();
		while (propIt.hasNext()) {
			ResourceProp prop = propIt.next();
			if (prop.getName().equalsIgnoreCase(propertyName)) {
				return prop.getPropValue();
			}
		}
		
		return value;
	}
	
	/**
	 * Returns a list of resources that are applicable for all the roles that a user belongs to.
	 * @param roleList
	 * @return
	 */
	private List<Resource> getResourcesForRole(List<Role> roleList) {
		
		log.info("GetResourcesForRole().....");
		// get the list of ids
		String domainId = null;
		List<String> roleIdList = new ArrayList<String>();
		
		if (roleList == null) {
			return null;
		}
		for (Role rl : roleList) {
			if (domainId == null) {
				domainId = rl.getId().getServiceId();
			}
			log.info("-Adding role id to list of roles:" + rl.getId().getRoleId());
			roleIdList.add( rl.getId().getRoleId() );
		}
		
		List<Resource> roleResources = 
			resourceDataService.getResourcesForRoles(domainId, roleIdList);
			//getResourceForRoleList(domainId, roleIdList);
		return roleResources;
	}

	
	
	/******** Spring methods ************ */
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException  {
        ac = applicationContext;
	}
	
	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

	public LoginDAO getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public IdmAuditLogDataService getAuditDataService() {
		return auditDataService;
	}

	public void setAuditDataService(IdmAuditLogDataService auditDataService) {
		this.auditDataService = auditDataService;
	}


	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	public RoleDataService getRoleDataService() {
		return roleDataService;
	}

	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}

	public GroupDataService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}

	public String getConnectorWsdl() {
		return connectorWsdl;
	}

	public void setConnectorWsdl(String connectorWsdl) {
		this.connectorWsdl = connectorWsdl;
	}

	public String getDefaultProvisioningModel() {
		return defaultProvisioningModel;
	}

	public void setDefaultProvisioningModel(String defaultProvisioningModel) {
		this.defaultProvisioningModel = defaultProvisioningModel;
	}

	public SysConfiguration getSysConfiguration() {
		return sysConfiguration;
	}

	public void setSysConfiguration(SysConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}

	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}

	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}

	public String getScriptEngine() {
		return scriptEngine;
	}

	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}

	public OrganizationDataService getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}

	public PasswordService getPasswordDS() {
		return passwordDS;
	}

	public void setPasswordDS(PasswordService passwordDS) {
		this.passwordDS = passwordDS;
	}

	public AddUser getAddUser() {
		return addUser;
	}

	public void setAddUser(AddUser addUser) {
		this.addUser = addUser;
	}

	public ModifyUser getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(ModifyUser modifyUser) {
		this.modifyUser = modifyUser;
	}

	public AuditHelper getAuditHelper() {
		return auditHelper;
	}

	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}

	public AttributeListBuilder getAttrListBuilder() {
		return attrListBuilder;
	}

	public void setAttrListBuilder(AttributeListBuilder attrListBuilder) {
		this.attrListBuilder = attrListBuilder;
	}

	public ConnectorAdapter getConnectorAdapter() {
		return connectorAdapter;
	}

	public void setConnectorAdapter(ConnectorAdapter connectorAdapter) {
		this.connectorAdapter = connectorAdapter;
	}

}
