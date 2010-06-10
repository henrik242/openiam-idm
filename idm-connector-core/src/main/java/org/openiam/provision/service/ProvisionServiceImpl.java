/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.base.SysConfiguration;
import org.openiam.base.id.UUIDGen;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.audit.service.AuditHelper;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDAO;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.service.PasswordGenerator;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.dto.AccountLockEnum;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.dto.ProvisionGroup;
import org.openiam.provision.dto.ProvisionModelEnum;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.PasswordResponse;
import org.openiam.provision.resp.ProvisionUserResponse;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.provision.service.ProvisionService", 
		targetNamespace = "http://www.openiam.org/service/provision", 
		portName = "ProvisionControllerServicePort", 
		serviceName = "ProvisionControllerService")
public class ProvisionServiceImpl
implements ProvisionService,  ApplicationContextAware  {

	protected static final Log log = LogFactory.getLog(ProvisionServiceImpl.class);

	public static ApplicationContext ac;

	
	protected UserDataService userMgr;
	protected LoginDataService loginManager;
	protected LoginDAO loginDao;
	
	protected IdmAuditLogDataService auditDataService;
	protected ConnectorDataService connectorService;
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
	protected AuditHelper auditHelper;

	
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
	public ProvisionUserResponse addUser(ProvisionUser provUser) {
		Organization org = null;
		Map<String, ManagedSysAttributes> managedSysMap = new HashMap<String, ManagedSysAttributes>();
	
		ScriptIntegration se = null;
		String secDomain = null;
		String password = null;

		Login primaryLogin = null;
		
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		
		
		password = PasswordGenerator.generatePassword(10);
		
		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		bindingMap.put("context", ac);
		
		String gmSysKey = (String)se.execute(bindingMap, "provision/globalManagerSyskey.groovy");
		
		//TODO: Add policies to validate the request
		//TODO: Add policies to enhance the request
		
		// add the gmsyskey attribute
		
		UserAttribute uAttr = new UserAttribute();
		uAttr.setName("GM_SYSKEY");
		uAttr.setValue(gmSysKey);
		provUser.getUserAttributes().put("GM_SYSKEY", uAttr);
		
		log.info("addUser called.");
		
		log.info("Creating user in openiam repository");
		// create a user in the openiam repository
		User user = provUser.getUser();

		log.info("User alternate in addUser=" + user.getAlternateContactId());
		
		// temp hack
		if (user.getCompanyId() != null) {
			org = orgManager.getOrganization(user.getCompanyId());
		}
		List<Login> principalList = provUser.getPrincipalList();
		
		if ( principalList == null) {
			principalList = new ArrayList<Login>();
		}
	

		bindingMap.put("sysId", "1");
		bindingMap.put("user", user);
		bindingMap.put("org", org);
		bindingMap.put("password", password);
		if (principalList.get(0) != null) {
			primaryLogin = principalList.get(0);
			log.info("primary login=" + primaryLogin);
			bindingMap.put("lg",primaryLogin);
			secDomain = primaryLogin.getId().getDomainId();
		}
		
	/* -- Temp hack -- */
		String networxId = (String)se.execute(bindingMap, "provision/networxId.groovy");
		String globalManagerId = (String)se.execute(bindingMap, "provision/globalManagerId.groovy");
	
			
	/*	LoginId networkLgId = new LoginId(secDomain, networxId, "1" );
		Login networxLg = new Login();
		networxLg.setId(networkLgId);
		networxLg.setPassword(password);
		networxLg.setStatus("ACTIVE");
		principalList.add(networxLg);

		LoginId gmLgId = new LoginId(secDomain, globalManagerId, "2" );
		Login gmLg = new Login();
		gmLg.setId(gmLgId);
		gmLg.setStatus("ACTIVE");
		principalList.add(gmLg);
	*/	
		//

		
		
		User newUser = userMgr.addUser(user);
		if (newUser == null || newUser.getUserId() == null) {
			ProvisionUserResponse resp = new ProvisionUserResponse();
			resp.setStatus(ResponseStatus.FAILURE);
		}
		
		log.info("User created in openiam repository");
		
		Supervisor supervisor = provUser.getSupervisor();
		if (supervisor != null && supervisor.getSupervisor() != null) {
			supervisor.setEmployee(user);
			userMgr.addSupervisor(supervisor);
			log.info("created user supervisor");
		}
		
		
		
		
		log.info("Associated a user to a group");
		List<Group> groupList = provUser.getMemberOfGroups();
		log.info("Group list = " + groupList);
		if (groupList != null) {
			for ( Group g : groupList) {
				// check if the group id is valid
				if (g.getGrpId() == null) {
					ProvisionUserResponse resp = new ProvisionUserResponse();
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setErrorCode( ResponseCode.GROUP_ID_NULL);
					return resp;
				}
				if ( groupManager.getGroup(g.getGrpId()) == null)  {
					if (g.getGrpId() == null) {
						ProvisionUserResponse resp = new ProvisionUserResponse();
						resp.setStatus(ResponseStatus.FAILURE);
						resp.setErrorCode(ResponseCode.GROUP_ID_NULL);
						return resp;
					}				
				}
				groupManager.addUserToGroup(g.getGrpId(), newUser.getUserId());
			}
		}
		
		
		log.info("Associating users to a role");
		List<Role> roleList = provUser.getMemberOfRoles();
		log.info("Role list = " + roleList);
		if (roleList != null && roleList.size() > 0) {
			for (Role r: roleList) {
				// check if the roleId is valid
				if (r.getId().getServiceId() == null || r.getId().getRoleId() == null) {
					ProvisionUserResponse resp = new ProvisionUserResponse();
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setErrorCode( ResponseCode.ROLE_ID_NULL);
					return resp;				
				}
				if (roleDataService.getRole(r.getId().getServiceId(), r.getId().getRoleId()) == null ) {
					ProvisionUserResponse resp = new ProvisionUserResponse();
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setErrorCode(ResponseCode.ROLE_ID_INVALID);
					return resp;				
				}
				roleDataService.addUserToRole(r.getId().getServiceId(), r.getId().getRoleId(), newUser.getUserId());
			}
		}
		
		// determine if this is role based, rule base or static list for provisioning the apps
		// for now, assume that its role based.
		log.info("default provisioning model=" + defaultProvisioningModel);
		
		
		log.info("create user identities");
		
	// temp hack - tack on the network identity
		
		
		//ManagedSysAttributes sysAttribute = null;
		
		/* Start with 1 role first and build from there. */
		if (roleList != null  && roleList.size() > 0) {
			List<Resource> roleResource = getResourcesForRole(roleList);
			// collect all the resources that belong to a managed system execute their policies
			if (roleResource != null) {
				log.info("List of resources for roles = " + roleResource.size());
				// for each resource, get the list of polices and execute them.
				for (Resource res : roleResource) {
					
					if (res.getName().equalsIgnoreCase("GLOBAL MANAGER")) {
						LoginId  gmLgId = new LoginId(secDomain, globalManagerId, "2" );
						Login gmLg = new Login();
						gmLg.setId(gmLgId);
						gmLg.setStatus("ACTIVE");
						principalList.add(gmLg);		
						
					}
					if (res.getName().equalsIgnoreCase("NETWORX")) {
						LoginId  networkLgId = new LoginId(secDomain, networxId, "1" );
						Login networxLg = new Login();
						networxLg.setId(networkLgId);
						networxLg.setPassword(password);
						networxLg.setStatus("ACTIVE");
						principalList.add(networxLg);						
					}
							

				}
		

			}	
	
		}

		// persist the list of identities in the openiam repository
		log.info("Persisting identity count=" + principalList.size());
		if (principalList != null) {
			for (Login lg : principalList) {
				Login newLg = new Login();
				LoginId newLgId = new LoginId();
				
				newLgId = lg.getId();
				newLg.setId(newLgId);
				newLg.setUserId(newUser.getUserId());
				newLg.setFirstTimeLogin(1);
				newLg.setStatus("ACTIVE");
				
				String pswd = lg.getPassword();
				if (pswd != null) {
					newLg.setPassword(loginManager.encryptPassword(pswd));
				}
				
				//lg.setUserId(newUser.getUserId());
				//lg.setFirstTimeLogin(1);
				//lg.setStatus("ACTIVE");
				
				//log.info("--Principal=" + lg);
				loginManager.addLogin(newLg);
				
				log.info("--added identity:" + lg.getId());
			}
		}
		
		
		log.info("Creating user in managed systems..");
		//TODO add the capability to get the list of applications if a role is specified
		//TODO - if the configuration on the form is based on rules, then process that to get the list of apps
		//TODO - get the list of apps from the user.
		String requestId = null;
		
		List<Login> appList = provUser.getPrincipalList();
		boolean syncCalled = false;
		if (principalList != null) {
			log.info("principal list size=" + principalList.size());
			for (Login lg : principalList) {
				log.info("Login object=" + lg);
				if (!lg.getId().getManagedSysId().equals("0") && !syncCalled) {
					log.info("Login managedsys is =" + lg.getId().getManagedSysId());
					// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
					//ManagedSys managedSys = managedSysService.getManagedSys(lg.getId().getManagedSysId());
					ManagedSys managedSys = managedSysService.getManagedSys("1");
					log.info("Managedsys object= " + managedSys);
					if (managedSys != null) {
						log.info("Managed sys found for managedSysId=" + lg.getId() ); 
						
						// collection of attributes that were determined earlier
						ManagedSysAttributes sysAttribute =  managedSysMap.get(managedSys.getManagedSysId());
						
						ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
						log.info("Connector found for " + connector.getConnectorId() ); 
						if (connector != null) {
								
								//Service service = Service.create(QName.valueOf("http://localhost:8080/idm-connector-ws/ExampleConnectorService"));
								Service service = Service.create(QName.valueOf(connector.getServiceUrl()));
								
								service.addPort(new QName(connector.getServiceNameSpace(),
										connector.getServicePort()),
										SOAPBinding.SOAP11HTTP_BINDING, 
										connector.getServiceUrl());
							
								
								ConnectorService port = service.getPort(new QName(connector.getServiceNameSpace(),
										connector.getServicePort()), 
										ConnectorService.class);
								
							 							
					            log.info("connector service client " + port);
					                
					            AddRequestType addReqType = new AddRequestType();
					            PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null, "target");
					            addReqType.setPsoID(idType);
					            requestId = "R" + System.currentTimeMillis();
					            addReqType.setRequestID(requestId);
					            addReqType.setTargetID(lg.getId().getManagedSysId());
					              
					            ExtensibleUser extUser = null;
					            
					            //TODO - Move to use groovy script based on attribute policies so that this is dynamic.
					            try {
					            	extUser = UserAttributeHelper.newUser(provUser);
					            }catch(Exception e) {
					            	e.printStackTrace();
					            	log.error(e);
					            }
					         //   ExtensibleUser extUser = sysAttribute.getExtUser();
					         //   log.info("Ext user being sent to connector = " + extUser);
					              
					            //addReqType.getData().getAny().add(sysAttribute.getExtUser());
					            addReqType.getData().getAny().add(extUser);
					            port.add(addReqType);
					            syncCalled = true;

								}
								
							}else {
								log.debug("Managed sys not found for managedSysId=" + lg.getId().getManagedSysId() ); 
							}
						}
						// get the connector

					}

			}
	
		auditHelper.addLog("NEW USER", provUser.getSecurityDomain(),	primaryLogin.getId().getLogin(),
				"IDM SERVICE", provUser.getCreatedBy(), "0", "USER", newUser.getUserId(), 
				null,   "SUCCESS", null,  "USER_STATUS", 
				provUser.getUser().getStatus().toString(),
				requestId, null);
		
	/*	String action,String domainId, String principal, 
		String srcSystem, String userId, String targetSystem, String objectType,  String objectId, String objectName,
		String actionStatus, String linkedLogId, String attrName, String attrValue,
		String requestId, String reason
	*/
	
		

		ProvisionUserResponse resp = new ProvisionUserResponse();
		resp.setStatus(ResponseStatus.SUCCESS);
		provUser.setUserId(newUser.getUserId());
		resp.setUser(provUser);
		return resp;
		

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
			log.info("-Adding role." + rl.getId().getRoleId());
			roleIdList.add( rl.getId().getRoleId() );
		}
		
		List<Resource> roleResources = 
			resourceDataService.getResourcesForRoles(domainId, roleIdList);
			//getResourcesForRoleList(domainId, roleIdList);
		return roleResources;
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
		log.info("deleteUser called.");
		
		ProvisionUserResponse resp = new ProvisionUserResponse();
		
		Login login = loginManager.getLoginByManagedSys(securityDomain,
				principal, managedSystemId);
		if (login == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
			return resp;
		}
		// change the status on the identity
		login.setStatus("INACTIVE");
		loginManager.updateLogin(login);
		
		if (login.getId().getManagedSysId().equals("0")  ) {
			// Turning off the primary identity - change the status on the user
			String userId = login.getUserId();
			if (userId != null) {
				User usr = userMgr.getUserWithDependent(userId, false);
				usr.setStatus(UserStatusEnum.DELETED);
				userMgr.updateUser(usr);
			}
		}
	
		List<Login> principalList = loginManager.getLoginByUser(login.getUserId());
		if (principalList != null) {
			for ( Login lg : principalList) {
				// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
				if (!lg.getId().getManagedSysId().equalsIgnoreCase(this.sysConfiguration.getDefaultManagedSysId())) {
				//if (!lg.getId().getManagedSysId().equalsIgnoreCase(passwordSync.getManagedSystemId())) {
					ManagedSys managedSys = managedSysService.getManagedSys(lg.getId().getManagedSysId());
					if (managedSys != null) {
						log.debug("Managed sys found for managedSysId=" + lg.getId().getManagedSysId() ); 
						ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
						
						if (connector != null) {
						
							ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
				            factory.setServiceClass(ConnectorService.class);
			               
				            log.info("Service endpoint : " + connector.getServiceUrl() );
			               
				            factory.setAddress(connector.getServiceUrl());
	 		               	javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf(connector.getServiceNameSpace());
	 		               	factory.setEndpointName(qname);
	 		                ConnectorService client = (ConnectorService) factory.create();    
	 		               
			                log.info("connector service client " + client);
			                

			                DeleteRequestType deleteRequest = new DeleteRequestType();
			                PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null, lg.getId().getManagedSysId());
			                deleteRequest.setRequestID("R" + System.currentTimeMillis());
			                deleteRequest.setRecursive(new Boolean(true));
			                deleteRequest.setPsoID(idType);
			                
			                ResponseType respType = client.delete(deleteRequest);

			                if (respType == null) {
			                	log.info("Response object from set password is null");
			                	resp.setStatus(ResponseStatus.FAILURE);
			                	return resp;
			                }
			                
			                if (respType.getStatus() == null) {
			                	log.info("Response status is null");
			                	resp.setStatus(ResponseStatus.FAILURE);
			                	return resp;
			                }
			                log.info("Response status=" + resp.getStatus());
					}
						
					}else {
						log.debug("Managed sys not found for managedSysId=" + lg.getId().getManagedSysId() ); 
					}
				}
				// get the connector

			}
		}
		

		
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

	private void showRoles(List<Role> activeRoleList) {
		log.info("show the roles:");
		if (activeRoleList != null) {
			for (Role r: activeRoleList) {
				log.info("-active role id: " + r.getId().getRoleId());
			}
		}
	}
	
	
	private List<String> removeFromInactiveResList(String managedSysId, List<String> inactiveResourceList) {
		
		List<String> resList = new ArrayList<String>();
		resList.addAll(inactiveResourceList);
		
	//	int ctr = 0;
		for (String s : resList) {
			if (s.equalsIgnoreCase(managedSysId)) {
				log.info("-- Removing resource: " + s);
				inactiveResourceList.remove(s);
			//	inactiveResourceList.remove(ctr);
		//		ctr++;
			}
		}
		
		return inactiveResourceList;
	}
	
	private boolean onInactiveList(String managedSysId, List<String> inactiveResourceList )  {
		boolean gmActive = false;
		boolean networx = false;
		
		for (String s : inactiveResourceList) {
			if (s.equalsIgnoreCase("2")) {
				gmActive = true;
			}
			if (s.equalsIgnoreCase("1")) {
				networx = true;
			}
		}
		if (gmActive == true && networx == true) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#modifyUser(org.openiam.provision.dto.ProvisionUser)
	 */
	public ProvisionUserResponse modifyUser(ProvisionUser provUser) {
		log.info("modifyUser called.");


		List<String> inactiveResourceList = new ArrayList<String>();
		
		//TODO: Add policies to validate the request
		//TODO: Add policies to enhance the request
		
		// get the current user object - update it with the new values and then save it
		User origUser = userMgr.getUserWithDependent(provUser.getUserId(), true);
		
		if (origUser == null || origUser.getUserId() == null) {
			ProvisionUserResponse resp = new ProvisionUserResponse();
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}

		// origUser2 is used for comparison purposes in the sync process
		//User currentUser2 = userMgr.getUserWithDependent(provUser.getUserId(), true);
		User currentUser2 = UserAttributeHelper.cloneUser(origUser);
		
		List<Role> curRoleList = roleDataService.getUserRolesAsFlatList(provUser.getUserId());
		List<Group> curGroupList = this.groupManager.getUserInGroupsAsFlatList(provUser.getUserId());
		
		log.info("** 1) Deptcd in Orig=" + currentUser2.getDeptCd());

		
		User newUser = provUser.getUser();

		log.info("** 1a) Deptcd in Orig=" + currentUser2.getDeptCd());
		log.info("** Deptcd in new=" + newUser.getDeptCd());
		
		updateUserObject(origUser, newUser);
		
		log.info("Modifying user in openiam repository");

		String requestId = "R" + System.currentTimeMillis();
	
		userMgr.updateUserWithDependent(origUser, true);
		
		// get the primary identity
		Login primaryLg = loginManager.getPrimaryIdentity(origUser.getUserId());
		String primaryId = null;
		if (primaryLg != null) {
			primaryId = primaryLg.getId().getLogin();
		}
		log.info("Primary id=" + primaryId);
		
		log.info("logging primary modify user" );
		String logId = auditHelper.addLog("MODIFY USER", provUser.getSecurityDomain(),	primaryId,
				"IDM SERVICE",	provUser.getUser().getLastUpdatedBy(), "0","USER", provUser.getUserId(), null,"SUCCESS", null,  "USER_STATUS", 
				provUser.getUser().getStatus().toString(),
				requestId, null);
		
			
		
		updateGroupAssociation(origUser.getUserId(), provUser.getMemberOfGroups(), logId, requestId, provUser.getUser().getLastUpdatedBy(), primaryId);
		
		updateRoleAssociation(origUser.getUserId(), provUser.getMemberOfRoles(), logId, requestId, provUser.getUser().getLastUpdatedBy(), primaryId);
		
		updateSupervisor(newUser, provUser.getSupervisor());

	
	

		
		
		// update the identities
		List<Login> tempPrincipalList = provUser.getPrincipalList();
		log.info("pricipallist = " + tempPrincipalList);
		if ( tempPrincipalList != null && tempPrincipalList.size() > 0) {
			updatePrincipals(newUser, provUser.getPrincipalList());
		}

		// temp hack
		List<Login> curPrincipalList =  loginManager.getLoginByUser(origUser.getUserId());
		
		Login primaryLogin = null;
		String secDomain = null;
		for ( Login lg : curPrincipalList) {
			if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
				primaryLogin = lg;
				secDomain = primaryLogin.getId().getDomainId();
				//rolePrincipalList.add(lg);
			}
			// build the active-inactive list of resources
			if (lg.getStatus() != null && lg.getStatus().equalsIgnoreCase("INACTIVE")) {
				inactiveResourceList.add(lg.getId().getManagedSysId());
			}
		}
		

	
		List<Login>principalList = provUser.getPrincipalList();
		String password = PasswordGenerator.generatePassword(10);
		
		ScriptIntegration se = null;
		Organization org = null;
		if (origUser.getCompanyId() != null) {
			org = orgManager.getOrganization(origUser.getCompanyId());
		}
		
		Map<String, Object> bindingMap = new HashMap<String, Object>();
		bindingMap.put("context", ac);
		bindingMap.put("sysId", "1");
		bindingMap.put("user", newUser);
		bindingMap.put("org", org);
		bindingMap.put("password", password);
		bindingMap.put("lg", primaryLogin);
		

		try {
			se = ScriptFactory.createModule(this.scriptEngine); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String networxId = (String)se.execute(bindingMap, "provision/networxId.groovy");
		String globalManagerId = (String)se.execute(bindingMap, "provision/globalManagerId.groovy");
		String gmSysKey = (String)se.execute(bindingMap, "provision/globalManagerSyskey.groovy");
		
		/* -- Temp hack -- */
		//
		

		
		// send message to the connectors.
		
		log.info("User created in openiam repository");
		
		List<Role> activeRoleList = this.roleDataService.getUserRolesAsFlatList(origUser.getUserId());  // provUser.getActiveMemberOfRoles();
		
		showRoles(activeRoleList);
		
		//List<Role> roleList = provUser.getMemberOfRoles();
		List<Login> rolePrincipalList = new ArrayList<Login>();
		
		if (activeRoleList != null  && activeRoleList.size() > 0) {
			log.info("Active role List= " + activeRoleList.size());
			
			List<Resource> roleResource = getResourcesForRole(activeRoleList);
			
			// collect all the resources that belong to a managed system execute their policies
			if (roleResource != null) {
				log.info("**** List of resources for roles = " + roleResource.size());
				// for each resource, get the list of polices and execute them.
				for (Resource res : roleResource) {
					// CHECK IF this resource is in the rolePrincipal list
					// if it is make sure that its active
					// if its not there, the add it.
					log.info("Checking resource id = " + res.getResourceId());
					log.info("Role principal list size = " + rolePrincipalList.size());
					boolean found = false;
					for ( Login l : curPrincipalList ) {
						log.info("checking identity: " + l.getId() + " " + l.getId().getManagedSysId() );
						if (l.getId().getManagedSysId().equalsIgnoreCase(res.getResourceId())) {
							// found
							log.info("-Match for resource found. Setting status to active.");
							l.setPasswordChangeCount(0);
							l.setAuthFailCount(0);
							l.setStatus("ACTIVE");
							found = true;
							rolePrincipalList.add(l);	
							// remove from the inactive list
							log.info("Res made active....=" + l.getId().getManagedSysId());
							log.info("InactiveResoruceList size=" + inactiveResourceList);
							
							inactiveResourceList = removeFromInactiveResList(l.getId().getManagedSysId(),inactiveResourceList);
							
							log.info("InactiveResoruceList after update size=" + inactiveResourceList);
						}
					}
					if (!found) {
						log.info("-Match for resource not found. added identity for " + res.getName());
						if (res.getName().equalsIgnoreCase("GLOBAL MANAGER")) {
							LoginId  gmLgId = new LoginId(secDomain, globalManagerId, "2" );
							Login gmLg = new Login();
							gmLg.setId(gmLgId);
							gmLg.setPasswordChangeCount(0);
							gmLg.setAuthFailCount(0);
							gmLg.setStatus("ACTIVE");
							rolePrincipalList.add(gmLg);	
							
							log.info("GM made active....");
							log.info("InactiveResoruceList size=" + inactiveResourceList);
							
							inactiveResourceList = removeFromInactiveResList(gmLg.getId().getManagedSysId(),inactiveResourceList);
							
							auditHelper.addLog("MODIFY USER", provUser.getSecurityDomain(),
									primaryId, "IDM SERVICE",
									provUser.getUser().getLastUpdatedBy(), "0",
									"USER", provUser.getUserId(), null ,"SUCCESS", logId,  "NEW IDENTITY", 
									res.getName(),
									requestId,  null);
														
							
						}
						if (res.getName().equalsIgnoreCase("NETWORX")) {
							LoginId  networkLgId = new LoginId(secDomain, networxId, "1" );
							Login networxLg = new Login();
							networxLg.setId(networkLgId);
							networxLg.setPassword(password);
							networxLg.setPasswordChangeCount(0);
							networxLg.setAuthFailCount(0);
							networxLg.setStatus("ACTIVE");
							rolePrincipalList.add(networxLg);						
							auditHelper.addLog("MODIFY USER", provUser.getSecurityDomain(),
									primaryId, "IDM SERVICE",
									provUser.getUser().getLastUpdatedBy(), "0",
									"USER", provUser.getUserId(), null, "SUCCESS", logId,  "NEW IDENTITY", 
									res.getName(),
									requestId, null);
						}							
					}
					}
				}
			}
		
		

		// determine if there are modifications to be made to the list of identities
		
		if (curPrincipalList != null) {
			for ( Login lg : curPrincipalList) {
				if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
					rolePrincipalList.add(lg);
				}
			}
			// IF A VALUE IS IN THE CURRENT LIST, BUT NOT in the rolelist, then delete it
			log.info("Searching the curent principal list...");
			for (Login curLg : curPrincipalList) {
				log.info("cur lg sysid = " + curLg.getId().getManagedSysId());
				boolean found = false;
				for (Login roleLg : rolePrincipalList) {
					if (roleLg.getId().getManagedSysId().equalsIgnoreCase(curLg.getId().getManagedSysId())) {
						found = true;
					}
				}
				if (!found) {
					curLg.setOperation(AttributeOperationEnum.DELETE);
					curLg.setStatus("INACTIVE");
					rolePrincipalList.add(curLg);
					
					auditHelper.addLog("MODIFY USER", provUser.getSecurityDomain(),
							primaryId, "IDM SERVICE",
							provUser.getUser().getLastUpdatedBy(), "0",
							"USER", provUser.getUserId(), null,"SUCCESS", logId,  "DISABLE IDENTITY", 
							curLg.getId().getLogin(),
							requestId, null);
				}
			}
			
		}
		
		log.info("** A) Deptcd in Orig=" + currentUser2.getDeptCd());
		
		// IF A VALUE IS IN THE CURRENT LIST, BUT NOT in the rolelist, then delete it
		
		// if a role is define
		
		if (activeRoleList != null) {
			log.info("-- updatePrincipals will be called.");
			updatePrincipals(newUser, rolePrincipalList);
		}
		log.info("--Check the status of this request.");
		// if the status has been set to TERMINATE - THEN SET THE IDENTITIES TO INACTIVE
		if ( isTerminate(newUser)) {
			log.info("--Status has been changed to terminate.");
			for ( Login lg : rolePrincipalList) {
				lg.setStatus("INACTIVE");
				log.info("Updating status for login=" + lg.getId());
				loginManager.updateLogin(lg);

			}
		}else {
			log.info("-- Status is not TERMINATE.");
			for ( Login lg : rolePrincipalList) {
				if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
					lg.setStatus("ACTIVE");
					lg.setPasswordChangeCount(0);
					lg.setAuthFailCount(0);
					log.info("Updating status TO ACTIVE for login=" + lg.getId());
					loginManager.updateLogin(lg);
				}
			

			}			
		}
		
		// pass 2 - check the current list with the role list
		
		provUser.setPrincipalList(rolePrincipalList);
		
		log.info("ROLE principal list (Before SPML block) = " + rolePrincipalList);
		
		//  show inactive list
		log.info ("---- show inactivelist ----");
 		for (String s : inactiveResourceList) {
 			log.info("Inactive resource: " + s);
 		}
		//
		
		//List<Login> principalList = provUser.getPrincipalList();
		if (rolePrincipalList != null) {
			log.info("Role based principal list size=" + rolePrincipalList.size());
			for (Login lg : rolePrincipalList) {
				log.info("Login object=" + lg);
				if (!lg.getId().getManagedSysId().equals("0") &&
					//lg.getStatus().equalsIgnoreCase("ACTIVE") ) {
						!onInactiveList(lg.getId().getManagedSysId(),inactiveResourceList ) ) {
					//lg.getStatus().equalsIgnoreCase("ACTIVE")) {
					log.info("Login managedsys is =" + lg.getId().getManagedSysId());
					// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
					ManagedSys managedSys = managedSysService.getManagedSys(lg.getId().getManagedSysId());
					log.info("Managedsys object= " + managedSys);
					// CHECK IF WE HAVE A NETWORX ID. IF WE DO, THEN LEAVE IT ALONE.
					// IF WE DONT, THEN HARD CODE THE CALL.
					if (!networx(rolePrincipalList)) {
						managedSys =  managedSysService.getManagedSys("1");
						log.info("Get the connector =" + managedSys);
					}
					
					if (managedSys != null) {
						log.info("Managed sys found for managedSysId=" + lg.getId() ); 
						
						// collection of attributes that were determined earlier
						//ManagedSysAttributes sysAttribute =  managedSysMap.get(managedSys.getManagedSysId());
						
						ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
						log.info("Connector found for " + connector.getConnectorId() ); 
						if (connector != null) {
								
								//Service service = Service.create(QName.valueOf("http://localhost:8080/idm-connector-ws/ExampleConnectorService"));
								Service service = Service.create(QName.valueOf(connector.getServiceUrl()));
								
								service.addPort(new QName(connector.getServiceNameSpace(),
										connector.getServicePort()),
										SOAPBinding.SOAP11HTTP_BINDING, 
										connector.getServiceUrl());
							
								
								ConnectorService port = service.getPort(new QName(connector.getServiceNameSpace(),
										connector.getServicePort()), 
										ConnectorService.class);
								
							 							
					            log.info("connector service client " + port);
					                
					            ModifyRequestType modReqType = new ModifyRequestType();
					            PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null, "target");
					            idType.setTargetID(lg.getId().getManagedSysId());
					            modReqType.setPsoID(idType);
					            modReqType.setRequestID(requestId);
					              
					            ExtensibleUser extUser = null;
					            
					            //TODO - Move to use groovy script based on attribute policies so that this is dynamic.
					            
					            // check if we have the syskey in this
					            UserAttribute gmAtt = currentUser2.getAttribute("GM_SYSKEY");
					            log.info("gmAtt=" + gmAtt.getValue());
					            
					    		log.info("** b) Deptcd in Orig=" + currentUser2.getDeptCd());
					            
					            try {
					            	extUser = UserAttributeHelper.modifyUser(currentUser2, curRoleList, curGroupList, provUser);
					         
					            
					            }catch(Exception e) {
					            	e.printStackTrace();
					            	log.error(e);
					            }
					         //   ExtensibleUser extUser = sysAttribute.getExtUser();
					         //   log.info("Ext user being sent to connector = " + extUser);
					              
					            log.info("Ext user attributes=" + extUser.getAttributes().size());
					            
					            ModificationType mod = new ModificationType();
					            mod.getData().getAny().add(extUser);
					            
					            List<ModificationType> modTypeList =  modReqType.getModification();
					            modTypeList.add(mod);
					            
					            port.modify(modReqType);
					            
					            //addReqType.getData().getAny().add(sysAttribute.getExtUser());
					            //port.add(addReqType);

								}
								
							}else {
								log.debug("Managed sys not found for managedSysId=" + lg.getId().getManagedSysId() ); 
							}
						}
						// get the connector

					}

			}

		
		
		ProvisionUserResponse resp = new ProvisionUserResponse();
		resp.setStatus(ResponseStatus.SUCCESS);
		return resp;
		
	}
	
	private boolean isTerminate(User newUser) {
		if (newUser.getStatus() == UserStatusEnum.TERMINATE ||
				newUser.getStatus() == UserStatusEnum.LEAVE || 
				newUser.getStatus() == UserStatusEnum.INACTIVE || 
				newUser.getStatus() == UserStatusEnum.DELETED) {
			return true;
		}
		return false;
	}
	
	private boolean networx(List<Login> rolePrincipalList) {
		if (rolePrincipalList == null) {
			return false;
		}
		for ( Login l : rolePrincipalList) {
			if (l.getId().getManagedSysId().equalsIgnoreCase("1")) {
				return true;
			}
		}
		return false;
	}
	
	private void updatePrincipals(User newUser, List<Login> principalList) {
		log.info(" -- Update principals called...");
		if (principalList == null) {
			return;
		}
		
		// get the primary users identity and set that as the new identity
		String newLogin = null;
		for (Login lg: principalList) {
			if (lg.getId().getManagedSysId().equalsIgnoreCase("0")) {
				newLogin = lg.getId().getLogin();
			}
		}
		log.info("----New identity=" + newLogin);
		
		//
		for (Login lg: principalList) {
			// check if its new / updated or to be removed

				Login l = loginDao.findLoginByManagedSys(lg.getId().getDomainId(), lg.getId().getManagedSysId(), newUser.getUserId());
				//List<Login> currentPrincipalList = loginManager.getLoginByUser(newUser.getUserId());
				//int result = checkPrincipal(lg, currentPrincipalList);
				
				if (l == null) {
					// new
					log.info("New Login");
					lg.setUserId(newUser.getUserId());
					lg.setIsLocked(0);
					lg.setCreateDate(new Date(System.currentTimeMillis()));
					loginManager.addLogin(lg);
				}else {
					//if (!l.getId().getLogin().equalsIgnoreCase(lg.getId().getLogin())) {
					if (!l.getId().getManagedSysId().equalsIgnoreCase("1")) {
						// update
						log.info("removed Identity");
						loginDao.remove(l);
						log.info("adding newidentity");
						Login newIdentity = new Login();
						LoginId newIdentityId = new LoginId();
						newIdentityId.setDomainId(l.getId().getDomainId());
						newIdentityId.setLogin(newLogin);
						newIdentityId.setManagedSysId(l.getId().getManagedSysId());
						//newIdentityId.
						newIdentity.setId(newIdentityId);
						
						newIdentity.setUserId(newUser.getUserId());
						newIdentity.getId().setLogin(newLogin);
						newIdentity.setAuthFailCount(l.getAuthFailCount());
						newIdentity.setCanonicalName(l.getCanonicalName());
						newIdentity.setCreateDate(l.getCreateDate());
						newIdentity.setCreatedBy(l.getCreatedBy());
						newIdentity.setFirstTimeLogin(l.getFirstTimeLogin());
						newIdentity.setGracePeriod(l.getGracePeriod());
						newIdentity.setIsDefault(l.getIsDefault());
						newIdentity.setIsLocked(l.getIsLocked());
						newIdentity.setLastAuthAttempt(l.getLastAuthAttempt());
						newIdentity.setLastLogin(l.getLastLogin());
						newIdentity.setPassword(l.getPassword());
						newIdentity.setPasswordChangeCount(l.getPasswordChangeCount());
						newIdentity.setPwdChanged(l.getPwdChanged());
						newIdentity.setPwdExp(l.getPwdExp());
						newIdentity.setStatus(l.getStatus());
						
						log.info("Updating identity: " + newIdentity.getId().getLogin() + " " + newIdentity.getId().getManagedSysId());
						
						loginManager.addLogin(newIdentity);
					}
					// do nothing
				}
			//}
			
		}
	}

	private int checkPrincipal(Login lg, List<Login> currentPrincipalList) {
		if (currentPrincipalList == null ) {
			// add this identity - its new
			return 1;
		}
		for (Login currentLg : currentPrincipalList) {
			if (currentLg.getId().getDomainId().equalsIgnoreCase(lg.getId().getDomainId()) && 
			    currentLg.getId().getManagedSysId().equalsIgnoreCase(lg.getId().getManagedSysId())) {
			    	// found - now check if a change has occurred
			    	if (currentLg.getId().getLogin().equalsIgnoreCase(lg.getId().getLogin())) {
			    		// do nothing
			    		return 0;
			    	}else {
			    		// update the identity
			    		return 2;
			    	}
			    }
		}
		return 1;
		
	}

	
	public void updateUserObject(User origUser, User newUser) {
		
		updatePrimaryUserInfo(origUser, newUser);
		updateUserAttributes(origUser, newUser);
		updateUserEmail(origUser, newUser);
		updateUserPhone(origUser, newUser);
		updateUserAddress(origUser, newUser);
	}

	private void updatePrimaryUserInfo(User origUser, User newUser) {
			origUser.setAddress1(newUser.getAddress1());
			origUser.setAddress2(newUser.getAddress2());
			origUser.setAddress3(newUser.getAddress3());
			origUser.setAddress4(newUser.getAddress4());
			origUser.setAddress5(newUser.getAddress5());
			origUser.setAddress6(newUser.getAddress6());
			origUser.setAddress7(newUser.getAddress7());
			origUser.setAreaCd(newUser.getAreaCd());
			origUser.setBirthdate(newUser.getBirthdate());
			origUser.setBldgNum(newUser.getBldgNum());
			origUser.setCity(newUser.getCity());
			origUser.setClassification(newUser.getClassification());
			origUser.setCompanyId(newUser.getCompanyId());
			origUser.setCostCenter(newUser.getCostCenter());
			origUser.setCountry(newUser.getCountry());
			origUser.setCountryCd(newUser.getCountryCd());
			origUser.setDeptCd(newUser.getDeptCd());
			origUser.setDeptName(newUser.getDeptName());
			origUser.setDivision(newUser.getDivision());
			origUser.setEmail(newUser.getEmail());

			origUser.setEmployeeId(newUser.getEmployeeId());
			origUser.setEmployeeType(newUser.getEmployeeType());
			origUser.setFirstName(newUser.getFirstName());
			origUser.setJobCode(newUser.getJobCode());
			origUser.setLastName(newUser.getLastName());
			origUser.setLastDate(newUser.getLastDate());
			origUser.setLocationCd(newUser.getLocationCd());
			origUser.setLocationName(newUser.getLocationName());
			origUser.setMaidenName(newUser.getMaidenName());
			origUser.setMailCode(newUser.getMailCode());
			origUser.setMetadataTypeId(newUser.getMetadataTypeId());
			origUser.setMiddleInit(newUser.getMiddleInit());
			origUser.setNickname(newUser.getNickname());
			origUser.setPasswordTheme(newUser.getPasswordTheme());
			origUser.setPhoneExt(newUser.getPhoneExt());
			origUser.setPhoneNbr(newUser.getPhoneNbr());
			origUser.setPostalCd(newUser.getPostalCd());
			origUser.setPrefix(newUser.getPrefix());
			origUser.setSecondaryStatus(newUser.getSecondaryStatus());
			origUser.setSex(newUser.getSex());
			origUser.setStartDate(newUser.getStartDate());
			origUser.setStatus(newUser.getStatus());
			origUser.setStreetDirection(newUser.getStreetDirection());
			origUser.setState(newUser.getState());
			origUser.setSuffix(newUser.getSuffix());
			origUser.setTitle(newUser.getTitle());
			origUser.setUserTypeInd(newUser.getUserTypeInd());
			origUser.setManagerId(newUser.getManagerId());
			origUser.setAlternateContactId(newUser.getAlternateContactId());

		
	}
	private void updateUserAttributes(User origUser, User newUser) {
		Map<String, UserAttribute> origAttributes =  origUser.getUserAttributes();
		if (origAttributes == null) {
			origAttributes = new HashMap<String, UserAttribute>();
		}
		Map<String, UserAttribute> newAttributes = newUser.getUserAttributes();
		if (newAttributes == null) {
			return;
		}
		
		Iterator<UserAttribute> attrIt =  newAttributes.values().iterator();
		while (attrIt.hasNext()) {
			UserAttribute newAttr = attrIt.next();
			if (newAttr.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("size before remove: " + origAttributes.size());
				origAttributes.remove(newAttr.getName());
				log.info("size after remove: " + origAttributes.size());
			}else {
				UserAttribute origAttr = origAttributes.get(newAttr.getName());
				if (origAttr != null) {
					origAttr.setValue(newAttr.getValue());
					origAttributes.put(origAttr.getName(),origAttr);
				}else {
					origAttributes.put(newAttr.getName(), newAttr);
				}
			}
		}
	
		
	}
	private void updateUserEmail(User origUser, User newUser) {
		Set<EmailAddress> origEmailSet = origUser.getEmailAddress();
		if (origEmailSet == null) {
			origEmailSet = new HashSet<EmailAddress>();
		}
		Set<EmailAddress> newEmailSet = newUser.getEmailAddress();
		if (newEmailSet == null) {
			return;
		}
		Iterator<EmailAddress> it = newEmailSet.iterator();
		while (it.hasNext()) {
			EmailAddress newEmail = it.next();
			EmailAddress e = getEmailAddress(newEmail.getEmailId(), origEmailSet);
			if (newEmail.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing email :" + newEmail.getEmailAddress() );
				// get the email object from the original set of emails
				origEmailSet.remove(e);
			}else {
				if ( e != null) {
					// update the existing object
					log.info("emailSet size before update: " + newEmail.getEmailAddress() + " " + origEmailSet.size());
					e.setEmailAddress(newEmail.getEmailAddress());
					e.setDescription(newEmail.getDescription());
					e.setIsDefault(newEmail.getIsDefault());
					e.setName(newEmail.getName());
					origEmailSet.add(e);
					log.info("emailSet size after update: " + origEmailSet.size());
				}else {
					// new object
					log.info("adding email :" + newEmail.getEmailAddress() );
					origEmailSet.add(newEmail);
					log.info("emailSet size after add: " + origEmailSet.size());
				}
				
			}
		}
	}
	
	
	private void  updateUserPhone(User origUser, User newUser) {
		Set<Phone> origPhoneSet = origUser.getPhone();
		if (origPhoneSet == null) {
			origPhoneSet = new HashSet<Phone>();
		}
		Set<Phone> newPhoneSet = newUser.getPhone();
		if (newPhoneSet == null) {
			return;
		}
		Iterator<Phone> it = newPhoneSet.iterator();
		while (it.hasNext()) {
			Phone newPhone = it.next();
			Phone p = getPhone(newPhone.getPhoneId(), origPhoneSet);
			if (newPhone.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing phone :" + newPhone.getPhoneNbr() );
				// get the email object from the original set of emails
				origPhoneSet.remove(p);
			}else {
				if ( p != null) {
					// update the existing object
					log.info("emailSet size before update: " + newPhone.getPhoneNbr() + " " + origPhoneSet.size());
					p.setAreaCd(newPhone.getAreaCd());
					p.setCountryCd(newPhone.getCountryCd());
					p.setDescription(newPhone.getDescription());
					p.setIsDefault(newPhone.getIsDefault());
					p.setPhoneExt(newPhone.getPhoneExt());
					p.setPhoneNbr(newPhone.getPhoneNbr());
					p.setPhoneType(newPhone.getPhoneType());
					p.setName(newPhone.getName());
					origPhoneSet.add(p);
					log.info("emailSet size after update: " + origPhoneSet.size());
				}else {
					// new object
					log.info("adding email :" + newPhone.getPhoneNbr() );
					origPhoneSet.add(newPhone);
					log.info("emailSet size after add: " + origPhoneSet.size());
				}
				
			}
		}
		
	}
	private void  updateUserAddress(User origUser, User newUser) {
		Set<Address> origAddressSet = origUser.getAddresses();
		if (origAddressSet == null) {
			origAddressSet = new HashSet<Address>();
		}
		Set<Address> newAddressSet = newUser.getAddresses();
		if (newAddressSet == null) {
			return;
		}
		Iterator<Address> it = newAddressSet.iterator();
		while (it.hasNext()) {
			Address newAddress = it.next();
			Address a = getAddress(newAddress.getAddressId(), origAddressSet);
			if (newAddress.getOperation() == AttributeOperationEnum.DELETE) {
				log.info("removing address :" + newAddress.getAddress1() );
				// get the email object from the original set of emails
				origAddressSet.remove(a);
			}else {
				if ( a != null) {
					// update the existing object
					log.info("emailSet size before update: " + newAddress.getAddress1() + " " + origAddressSet.size());
					a.setAddress1(newAddress.getAddress1());
					a.setAddress2(newAddress.getAddress2());
					a.setAddress3(newAddress.getAddress3());
					a.setAddress4(newAddress.getAddress4());
					a.setAddress5(newAddress.getAddress5());
					a.setAddress6(newAddress.getAddress6());
					a.setAddress7(newAddress.getAddress7());
					a.setBldgNumber(newAddress.getBldgNumber());
					a.setCity(newAddress.getCity());
					a.setCountry(newAddress.getCountry());
					a.setDescription(newAddress.getDescription());
					a.setIsDefault(newAddress.getIsDefault());
					a.setName(newAddress.getName());
					origAddressSet.add(a);
					log.info("emailSet size after update: " + origAddressSet.size());
				}else {
					// new object
					log.info("adding email :" + newAddress.getAddress1() );
					origAddressSet.add(newAddress);
					log.info("emailSet size after add: " + origAddressSet.size());
				}
				
			}
		}
		
	}


	
	private EmailAddress getEmailAddress(String id, Set<EmailAddress> emailSet) {
		Iterator<EmailAddress> emailIt = emailSet.iterator();
		while (emailIt.hasNext()) {
			EmailAddress email = emailIt.next();
			if (email.getEmailId() != null) {
				if (email.getEmailId().equals(id) && (id != null && id.length() > 0)) {
					log.info("Match >> email.getEmailId = " + email.getEmailId() + " - " + id);
					return email;
				}
			}
		}
		return null;
		
	}

	private Phone getPhone(String id, Set<Phone> phoneSet) {
		Iterator<Phone> phoneIt = phoneSet.iterator();
		while (phoneIt.hasNext()) {
			Phone phone = phoneIt.next();
			if (phone.getPhoneId() != null) {
				if (phone.getPhoneId().equals(id) && (id != null && id.length() > 0)) {
					log.info("Match >> phone.getPhoneId = " + phone.getPhoneId() + " - " + id);
					return phone;
				}
			}
		}
		return null;
		
	}

	private Address getAddress(String id, Set<Address> addressSet) {
		Iterator<Address> addressIt = addressSet.iterator();
		while (addressIt.hasNext()) {
			Address adr = addressIt.next();
			if (adr.getAddressId() != null  ) {
				if (adr.getAddressId().equals(id) && (id != null && id.length() > 0)) {
					log.info("Match >> adr.getAdrId = " + adr.getAddressId() + " - " + id);
					return adr;
				}
			}
		}
		return null;
		
	}
	
	private void updateGroupAssociation(String userId, List<Group> newGroupList,
			String logId, String requestId, String updatedBy, String primaryId) {
		// loop through the new list
		// if its marked - delete then delete the user-group association
		// otherwise - check if the group is already linked to the user. iF its not, then add it.
		if (newGroupList == null) {
			return;
		}
		for (Group g : newGroupList) {
			if (g.getOperation() == AttributeOperationEnum.DELETE) {
				this.groupManager.removeUserFromGroup(g.getGrpId(), userId);

				auditHelper.addLog("MODIFY USER", null,
						primaryId, "IDM SERVICE",
						updatedBy, "0",
						"USER", userId,null, "SUCCESS", logId,  "REMOVE GROUP", 
						g.getGrpId(),
						requestId,  null);
				
			}else {
				if (!groupManager.isUserInGroup(g.getGrpId(), userId)) {
					groupManager.addUserToGroup(g.getGrpId(), userId);
					
					auditHelper.addLog("MODIFY USER", null,
							primaryId, "IDM SERVICE",
							updatedBy, "0",
							"USER", userId, null ,"SUCCESS", logId,  "ADD GROUP", 
							g.getGrpId(),
							requestId, null);
					
				}
			}
		}
	}
	
	private void updateRoleAssociation(String userId, List<Role> newRoleList, 
			String logId, String requestId, String updatedBy, String primaryId) {
		if (newRoleList == null) {
			return;
		}
		for (Role r : newRoleList) {
			if (r.getOperation() == AttributeOperationEnum.DELETE) {
				roleDataService.removeUserFromRole(r.getId().getServiceId(),
						r.getId().getRoleId(), userId);
				
				auditHelper.addLog("MODIFY USER", null,
						primaryId, "IDM SERVICE",
						updatedBy, "0",
						"USER", userId, null ,"SUCCESS", logId,  "REMOVE ROLE", 
						r.getRoleName(),
						requestId, null);
				
			}else {
				if (!roleDataService.isUserInRole(r.getId().getServiceId(), r.getId().getRoleId(), userId)) {
					roleDataService.addUserToRole(r.getId().getServiceId(),
							r.getId().getRoleId(), userId);

					auditHelper.addLog("MODIFY USER", null,
							primaryId, "IDM SERVICE",
							updatedBy, "0",
							"USER", userId, null ,"SUCCESS", logId,  "ADD ROLE", 
							r.getRoleName(),
							requestId, null);
					
				}
			}
		}		
	}

	private void updateSupervisor(User user, Supervisor supervisor) {
		
		if (supervisor == null) {
			return;
		}
		// check the current supervisor - if different - remove it and add the new one.
		List<Supervisor> supervisorList = userMgr.getSupervisors(user.getUserId());
		for (Supervisor s : supervisorList) {
			log.info("looking to match supervisor ids = " + s.getSupervisor().getUserId() + " " + supervisor.getSupervisor().getUserId());
			if (s.getSupervisor().getUserId().equalsIgnoreCase(supervisor.getSupervisor().getUserId())) {
				return;
			}
			userMgr.removeSupervisor(s);
		}
		log.info("adding supervisor: " + supervisor.getSupervisor().getUserId());
		supervisor.setEmployee(user);
		userMgr.addSupervisor(supervisor);

	}

	
	
	
	
	public PasswordResponse resetPassword(PasswordSync passwordSync) {
		log.info("----resetPassword called.------");
		
		PasswordResponse response = new PasswordResponse(ResponseStatus.SUCCESS);
		
		String primaryLogId = null;
		
		String requestorId =  passwordSync.getRequestorId();
		
		// get the user object associated with this principal
		Login login = loginManager.getLoginByManagedSys(passwordSync.getSecurityDomain(),
				passwordSync.getPrincipal(), passwordSync.getManagedSystemId());
		if (login == null) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
			return response;
		}

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
	/*	if (!usr.getStatus().equals(UserStatusEnum.ACTIVE)) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_STATUS);	
			return response;				
		}
	*/
		// determine which password policy to use
		
		// validate the password against password policy
		
		String requestId = "R" + System.currentTimeMillis();
		
		// update the openIAM repository
		String password = passwordSync.getPassword();
		if (password == null || password.length() ==0) {
			// autogenerate the password
			password = String.valueOf( PasswordGenerator.generatePassword(8));
		}
		if (!passwordSync.getManagedSystemId().equals("0") ) {
			// update the connector directly
			String encPassword = loginManager.encryptPassword(password);
			
			boolean retval = loginManager.resetPassword( passwordSync.getSecurityDomain(),
					passwordSync.getPrincipal(), passwordSync.getManagedSystemId(),
					encPassword);
			
			//Login lg = loginManager.getLoginByManagedSys(passwordSync.getSecurityDomain(),
			//		passwordSync.getPrincipal(), passwordSync.getManagedSystemId());
	
			
		
			ManagedSys managedSys = managedSysService.getManagedSys(passwordSync.getManagedSystemId());
			if (managedSys != null) {
				log.debug("Managed sys found for managedSysId=" + passwordSync.getManagedSystemId() );
				

				
				ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
				
				if (connector != null) {
				
					ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		            factory.setServiceClass(ConnectorService.class);
	               
		            log.info("Service endpoint : " + connector.getServiceUrl() );
	               
		            factory.setAddress(connector.getServiceUrl());
		               	javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf(connector.getServiceNameSpace());
		               	factory.setEndpointName(qname);
		                ConnectorService client = (ConnectorService) factory.create();    
		               
	                log.info("connector service client " + client);
	                
	            	SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
	                PSOIdentifierType idType = new PSOIdentifierType(passwordSync.getPrincipal(),null, passwordSync.getManagedSystemId()   		);
	                pswdReqType.setPsoID(idType);
	                //pswdReqType.setRequestID(UUIDGen.getUUID());
	                pswdReqType.setRequestID(requestId);
	                pswdReqType.setPassword(password);
	                	
	                log.info("Setting password on target system:" +  passwordSync.getManagedSystemId());

	                ResponseType resp =  client.setPassword(pswdReqType);
	                if (resp == null) {
	                	log.info("Response object from set password is null");
	                	response.setStatus(ResponseStatus.FAILURE);
	                	return response;
	                }
	                
	                if (resp.getStatus() == null) {
	                	log.info("Response status is null");
	                	response.setStatus(ResponseStatus.FAILURE);
	                	return response;
	                }
	                log.info("Response status=" + resp.getStatus());
	                if (resp.getStatus().equals(StatusCodeType.FAILURE)) {
	                	response.setStatus(ResponseStatus.FAILURE);
	                  /* 	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
	                   			passwordSync.getPrincipal(), 
		            			passwordSync.getSrcSystemId(), lg, "PASSWORD", "FAILURE", primaryLogId );		
		              */	                	
	                }else {
	                	/*
	                   	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
	            			lg.getId().getLogin(), 
	            			passwordSync.getSrcSystemId(), lg, "PASSWORD", "SUCCESS", primaryLogId );
	            		*/
	                }


				}
				
			}
			
			
			
			
		}else {
		String encPassword = loginManager.encryptPassword(password);
		boolean retval = loginManager.resetPassword(passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
				passwordSync.getManagedSystemId(), encPassword);
		
		if (retval) {
			log.info("-Password changed in openiam repository for user:" + passwordSync.getPrincipal());
			
			Login l = loginManager.getLoginByManagedSys(passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					passwordSync.getManagedSystemId());
			
			auditHelper.addLog("RESET PASSWORD", passwordSync.getSecurityDomain(),	 passwordSync.getPrincipal(),
					"IDM SERVICE", requestorId, "PASSWORD","PASSWORD" ,
					l.getUserId(), null,  "SUCCESS", null,  null, 
					null,
					requestId, null);
	
			/*		String action,String domainId, String principal, 
			String srcSystem, String userId, String targetSystem, String objectType,  String objectId, String objectName,
			String actionStatus, String linkedLogId, String attrName, String attrValue,
			String requestId, String reason
			
	*/
			
			// audit log the success
			//primaryLogId = logEvent(passwordSync, login,"PASSWORD", "SUCCESS", null);
			//primaryLogId = "SP"+ primaryLogId;
			
			// reset the user
			//User usr = userMgr.getUserWithDependent(login.getUserId(), false);
			usr.setSecondaryStatus(null);
			response.setPassword(password);
			
		} else {
			// audit log the failure and stop the process		
			//logEvent(passwordSync, login,"PASSWORD", "FAILURE", null);
			
			Response resp = new Response();
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
		}
		
		// update the connected systems
		List<Login> principalList = loginManager.getLoginByUser(login.getUserId());
		if (principalList != null) {
			for ( Login lg : principalList) {
				
				log.info("PrincipalList size =" + principalList.size());
				
				// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
				if (!lg.getId().getManagedSysId().equalsIgnoreCase(passwordSync.getManagedSystemId())) {
					String managedSysId = lg.getId().getManagedSysId();
					Resource res =  resourceDataService.getResource(managedSysId);
					
					log.info(" - managedsys id = " + managedSysId);
					log.info(" - Resource for sysId =" + res);
					
					// check the sync flag
					Set<ResourceProp> resPropSet = null;
					String syncFlag = null;
					boolean syncAllowed = false;
					
					if (res != null) {
						resPropSet =  res.getResourceProps();
						syncFlag = getResProperty(resPropSet, "INCLUDE_PSWD_SYNC");
						log.info(" - SyncFlag=" + syncFlag);
					}
					if ( res == null) {
						syncAllowed = true;
					}else {
						if (syncFlag == null || !syncFlag.equalsIgnoreCase("N")) {
							log.info(" - Sync allowed=true" );
							syncAllowed = true;
						}
					}

					if (syncAllowed) {
						
						log.info("Sync allowed for sys=" + managedSysId);				
					
						retval = loginManager.resetPassword(lg.getId().getDomainId(),
								lg.getId().getLogin(), lg.getId().getManagedSysId(),
								encPassword);
						
						ManagedSys managedSys = managedSysService.getManagedSys(lg.getId().getManagedSysId());
						if (managedSys != null) {
							
							log.debug("Managed sys found for managedSysId=" + lg.getId().getManagedSysId() ); 
							
	
							
							ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
							
							if (connector != null) {
							
								ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
					            factory.setServiceClass(ConnectorService.class);
				               
					            log.info("Service endpoint : " + connector.getServiceUrl() );
				               
					            factory.setAddress(connector.getServiceUrl());
		 		               	javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf(connector.getServiceNameSpace());
		 		               	factory.setEndpointName(qname);
		 		                ConnectorService client = (ConnectorService) factory.create();    
		 		               
				                log.info("connector service client " + client);
				                
				            	SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
				                PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null, lg.getId().getManagedSysId());
				                pswdReqType.setPsoID(idType);
				                pswdReqType.setRequestID(primaryLogId);
				                pswdReqType.setPassword(password);
				                	
				                log.info("Setting password on target system:" + lg.getId().getManagedSysId());
	
				                ResponseType resp =  client.setPassword(pswdReqType);
				                if (resp.getStatus().equals(StatusCodeType.FAILURE)) {
				                	response.setStatus(ResponseStatus.FAILURE);
				                   //	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
					            //			lg.getId().getLogin(), 
					            //			passwordSync.getSrcSystemId(), lg, "PASSWORD", "FAILURE", primaryLogId );			                	
				                }else {
				                  // 	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
				            	//		lg.getId().getLogin(), 
				            	//		passwordSync.getSrcSystemId(), lg, "PASSWORD", "SUCCESS", primaryLogId );
				                }
	
	
							}
							
						}else {
							log.debug("Managed sys not found for managedSysId=" + lg.getId().getManagedSysId() ); 
						}
					}
				}
				// get the connector

			}
		}
		}
		
		return response;

	}
	/* (non-Javadoc)
	 * @see org.openiam.provision.service.ProvisionService#setPassword(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response setPassword(PasswordSync passwordSync) {
		log.info("setPassword called.");
		
		Response response = new Response(ResponseStatus.SUCCESS);
		
		String primaryLogId = null;
		
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
		
/*	if (!usr.getStatus().equals(UserStatusEnum.ACTIVE)) {
			response.setStatus(ResponseStatus.FAILURE);
			response.setErrorCode(ResponseCode.USER_STATUS);	
			return response;				
		}
	*/
		
		
		// determine which password policy to use
		
		// validate the password against password policy
		Password pswd = new Password();
		pswd.setDomainId(passwordSync.getSecurityDomain());
		pswd.setManagedSysId(passwordSync.getManagedSystemId());
		pswd.setPrincipal(passwordSync.getPrincipal());
		pswd.setPassword(passwordSync.getPassword());
		try {
			PasswordValidationCode rtVal = passwordDS.isPasswordValid(pswd);
			if (rtVal != PasswordValidationCode.SUCCESS) {
				response.setStatus(ResponseStatus.FAILURE);
				response.setErrorCode(ResponseCode.valueOf(rtVal.getValue() ));	
				return response;				
			}

		}catch(ObjectNotFoundException oe) {
			oe.printStackTrace();
			log.error(oe);
		}
		
		// update the openIAM repository
		String requestId = "R" + System.currentTimeMillis();

		if (!passwordSync.getManagedSystemId().equals("0") ) {
			// update the connector directly
			String encPassword = loginManager.encryptPassword(passwordSync.getPassword());
			
			boolean retval = loginManager.setPassword( passwordSync.getSecurityDomain(),
					passwordSync.getPrincipal(), passwordSync.getManagedSystemId(),
					encPassword);
			log.info("Setting password for principal = " + passwordSync.getPrincipal());
			
			auditHelper.addLog("SET PASSWORD", passwordSync.getSecurityDomain(),	
					passwordSync.getPrincipal(),"IDM SERVICE",	
					passwordSync.getRequestorId(), "PASSWORD",
					login.getId().getLogin(), null, null,"SUCCESS", null,  null, 
					null,
					requestId, null);
	
		
			
			ManagedSys managedSys = managedSysService.getManagedSys(passwordSync.getManagedSystemId());
			if (managedSys != null) {
				log.debug("Managed sys found for managedSysId=" + passwordSync.getManagedSystemId() );
				

				
				ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
				
				if (connector != null) {
				
					ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		            factory.setServiceClass(ConnectorService.class);
	               
		            log.info("Service endpoint : " + connector.getServiceUrl() );
	               
		            factory.setAddress(connector.getServiceUrl());
		               	javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf(connector.getServiceNameSpace());
		               	factory.setEndpointName(qname);
		                ConnectorService client = (ConnectorService) factory.create();    
		               
	                log.info("connector service client " + client);
	                
	            	SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
	                PSOIdentifierType idType = new PSOIdentifierType(passwordSync.getPrincipal(),null, passwordSync.getManagedSystemId()   		);
	                pswdReqType.setPsoID(idType);
	                //pswdReqType.setRequestID(UUIDGen.getUUID());
	                pswdReqType.setRequestID(requestId);
	                pswdReqType.setPassword(passwordSync.getPassword());
	                	
	                log.info("Setting password on target system:" +  passwordSync.getManagedSystemId());

	                ResponseType resp =  client.setPassword(pswdReqType);
	                if (resp == null) {
	                	log.info("Response object from set password is null");
	                	response.setStatus(ResponseStatus.FAILURE);
	                	return response;
	                }
	                
	                if (resp.getStatus() == null) {
	                	log.info("Response status is null");
	                	response.setStatus(ResponseStatus.FAILURE);
	                	return response;
	                }
	                log.info("Response status=" + resp.getStatus());
	                if (resp.getStatus().equals(StatusCodeType.FAILURE)) {
	                	response.setStatus(ResponseStatus.FAILURE);
	                  /* 	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
	                   			passwordSync.getPrincipal(), 
		            			passwordSync.getSrcSystemId(), lg, "PASSWORD", "FAILURE", primaryLogId );		
		              */	                	
	                }else {
	                	/*
	                   	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
	            			lg.getId().getLogin(), 
	            			passwordSync.getSrcSystemId(), lg, "PASSWORD", "SUCCESS", primaryLogId );
	            		*/
	                }


				}
				
			}
			
			
			
			
		}else {
		String encPassword = loginManager.encryptPassword(passwordSync.getPassword());
		boolean retval = loginManager.setPassword(passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
				passwordSync.getManagedSystemId(), encPassword);
		if (retval) {
			log.info("-Password changed in openiam repository for user:" + passwordSync.getPrincipal());
			
			Login l = loginManager.getLoginByManagedSys(passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					passwordSync.getManagedSystemId());
			
			auditHelper.addLog("SET PASSWORD", passwordSync.getSecurityDomain(), passwordSync.getPrincipal(),
					"IDM SERVICE", passwordSync.getRequestorId(), "PASSWORD", "PASSWORD", l.getUserId(), 
					null,  "SUCCESS", null,  null, 
					null,
					requestId, null);

		
			// audit log the success
			//primaryLogId = logEvent(passwordSync, login,"PASSWORD", "SUCCESS", null);
			
		} else {
			// audit log the failure and stop the process		
			//logEvent(passwordSync, login,"PASSWORD", "FAILURE", null);
			
			Response resp = new Response();
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.PRINCIPAL_NOT_FOUND);
		}
		
		// update the connected systems
		List<Login> principalList = loginManager.getLoginByUser(login.getUserId());
		if (principalList != null) {
			log.info("PrincipalList size =" + principalList.size());
			for ( Login lg : principalList) {
				// get the managed system for the identity - ignore the managed system id that is linked to openiam's repository
				log.info("**** Managed System Id in passwordsync object=" + passwordSync.getManagedSystemId());
				
				if (!lg.getId().getManagedSysId().equalsIgnoreCase(passwordSync.getManagedSystemId())) {
					// determine if you should sync the password or not
					String managedSysId = lg.getId().getManagedSysId();
					Resource res =  resourceDataService.getResource(managedSysId);
					
					log.info(" - managedsys id = " + managedSysId);
					log.info(" - Resource for sysId =" + res);
					
					// check the sync flag
					Set<ResourceProp> resPropSet = null;
					String syncFlag = null;
					boolean syncAllowed = false;
					
					if (res != null) {
						resPropSet =  res.getResourceProps();
						syncFlag = getResProperty(resPropSet, "INCLUDE_PSWD_SYNC");
						log.info(" - SyncFlag=" + syncFlag);
					}
					if ( res == null) {
						syncAllowed = true;
					}else {
						if (syncFlag == null || !syncFlag.equalsIgnoreCase("N")) {
							log.info(" - Sync allowed=true" );
							syncAllowed = true;
						}
					}
					if (syncAllowed) {
					
						log.info("Sync allowed for sys=" + managedSysId);
						retval = loginManager.setPassword(lg.getId().getDomainId(),
								lg.getId().getLogin(), lg.getId().getManagedSysId(),
								encPassword);
						
						ManagedSys managedSys = managedSysService.getManagedSys(lg.getId().getManagedSysId());
						if (managedSys != null) {
							log.debug("Managed sys found for managedSysId=" + lg.getId().getManagedSysId() );
							
	
							
							ProvisionConnector connector = connectorService.getConnector(managedSys.getConnectorId());
							
							if (connector != null) {
							
								ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
					            factory.setServiceClass(ConnectorService.class);
				               
					            log.info("Service endpoint : " + connector.getServiceUrl() );
				               
					            factory.setAddress(connector.getServiceUrl());
		 		               	javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf(connector.getServiceNameSpace());
		 		               	factory.setEndpointName(qname);
		 		                ConnectorService client = (ConnectorService) factory.create();    
		 		               
				                log.info("connector service client " + client);
				                
				            	SetPasswordRequestType pswdReqType = new SetPasswordRequestType();
				                PSOIdentifierType idType = new PSOIdentifierType(lg.getId().getLogin(),null, lg.getId().getManagedSysId());
				                pswdReqType.setPsoID(idType);
				                //pswdReqType.setRequestID(UUIDGen.getUUID());
				                pswdReqType.setRequestID("R" + System.currentTimeMillis());
				                pswdReqType.setPassword(passwordSync.getPassword());
				                	
				                log.info("Setting password on target system:" + lg.getId().getManagedSysId());
	
				                ResponseType resp =  client.setPassword(pswdReqType);
				                if (resp == null) {
				                	log.info("Response object from set password is null");
				                	response.setStatus(ResponseStatus.FAILURE);
				                	return response;
				                }
				                
				                if (resp.getStatus() == null) {
				                	log.info("Response status is null");
				                	response.setStatus(ResponseStatus.FAILURE);
				                	return response;
				                }
				                log.info("Response status=" + resp.getStatus());
				                if (resp.getStatus().equals(StatusCodeType.FAILURE)) {
				                	response.setStatus(ResponseStatus.FAILURE);
				               //    	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
					           // 			lg.getId().getLogin(), 
					           // 			passwordSync.getSrcSystemId(), lg, "PASSWORD", "FAILURE", primaryLogId );			                	
				                }else {
				                //   	logManagedSysEvent(passwordSync.getAction(), passwordSync.getSecurityDomain(),
				            	//		lg.getId().getLogin(), 
				            	//		passwordSync.getSrcSystemId(), lg, "PASSWORD", "SUCCESS", primaryLogId );
				                }
	
	
							}
							
						}else {
							log.debug("Managed sys not found for managedSysId=" + lg.getId().getManagedSysId() ); 
						}
					}
				}
				// get the connector

			}
		}
	}
		
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
	 * @see org.openiam.provision.service.ProvisionService#lockUser(java.lang.String, boolean)
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
		Login lg = getPrimaryIdentity(userId);

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
		
		String requestId = "R" + System.currentTimeMillis();
		
		auditHelper.addLog("LOCK USER", lg.getId().getDomainId(),	lg.getId().getLogin(),
				"IDM SERVICE",requestorId, "USER",	"USER", user.getUserId(),null, "SUCCESS", null,  null, 
				null,
				requestId, auditReason);
	
		
		
		Response resp = new Response();
		resp.setStatus(ResponseStatus.SUCCESS);
		return resp;
	}
	
	private Login getPrimaryIdentity(String userId) {
		List<Login> loginList = loginManager.getLoginByUser(userId);
		for ( Login lg : loginList) {
			if (lg.getId().getManagedSysId().equalsIgnoreCase(sysConfiguration.getDefaultManagedSysId())) {
				return lg;
			}
		}
		return null;
	}

	
	
	/*private String logEvent(PasswordSync passwordSync, Login login, String object, String actionStatus, String linkedLogId) {
		
		IdmAuditLog log = new IdmAuditLog();
	

		log.setObjectTypeId(object);
		log.setActionId(passwordSync.getAction());
		log.setActionStatus(actionStatus);
		log.setDomainId(passwordSync.getSecurityDomain());
		log.setUserId(login.getUserId());
		log.setPrincipal(passwordSync.getPrincipal());
		log.setLinkedLogId(linkedLogId);
		log.setSrcSystemId(passwordSync.getSrcSystemId());
		log.setTargetSystemId(passwordSync.getManagedSystemId());
			
		auditDataService.addLog(log);
		return log.getLogId();
	}
*/

	
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

	public IdmAuditLogDataService getAuditDataService() {
		return auditDataService;
	}

	public void setAuditDataService(IdmAuditLogDataService auditDataService) {
		this.auditDataService = auditDataService;
	}

	public ConnectorDataService getConnectorService() {
		return connectorService;
	}

	public void setConnectorService(ConnectorDataService connectorService) {
		this.connectorService = connectorService;
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

	public SysConfiguration getSysConfiguration() {
		return sysConfiguration;
	}

	public void setSysConfiguration(SysConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}

	public LoginDAO getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public String getDefaultProvisioningModel() {
		return defaultProvisioningModel;
	}

	public void setDefaultProvisioningModel(String defaultProvisioningModel) {
		this.defaultProvisioningModel = defaultProvisioningModel;
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

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException  {
        ac = applicationContext;
	}

	public AuditHelper getAuditHelper() {
		return auditHelper;
	}

	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}
	

}
