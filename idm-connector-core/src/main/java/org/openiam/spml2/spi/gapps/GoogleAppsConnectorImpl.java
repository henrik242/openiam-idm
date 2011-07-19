package org.openiam.spml2.spi.gapps;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.ConfigurationException;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.ErrorCode;
import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.ReturnDataType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;
import org.openiam.spml2.msg.suspend.ActiveRequestType;
import org.openiam.spml2.msg.suspend.ActiveResponseType;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;
import org.openiam.spml2.util.connect.ConnectionFactory;
import org.openiam.spml2.util.connect.ConnectionManagerConstant;
import org.openiam.spml2.util.connect.ConnectionMgr;

import com.google.gdata.client.appsforyourdomain.AppsForYourDomainQuery;
import com.google.gdata.client.appsforyourdomain.AppsGroupsService;
import com.google.gdata.client.appsforyourdomain.EmailListRecipientService;
import com.google.gdata.client.appsforyourdomain.EmailListService;
import com.google.gdata.client.appsforyourdomain.NicknameService;
import com.google.gdata.client.appsforyourdomain.UserService;
import com.google.gdata.data.Link;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainErrorCode;
import com.google.gdata.data.appsforyourdomain.AppsForYourDomainException;
import com.google.gdata.data.appsforyourdomain.EmailList;
import com.google.gdata.data.appsforyourdomain.Login;
import com.google.gdata.data.appsforyourdomain.Name;
import com.google.gdata.data.appsforyourdomain.Nickname;
import com.google.gdata.data.appsforyourdomain.Quota;
import com.google.gdata.data.appsforyourdomain.generic.GenericEntry;
import com.google.gdata.data.appsforyourdomain.generic.GenericFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListRecipientEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.EmailListRecipientFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.NicknameEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.NicknameFeed;
import com.google.gdata.data.appsforyourdomain.provisioning.UserEntry;
import com.google.gdata.data.appsforyourdomain.provisioning.UserFeed;
import com.google.gdata.data.extensions.Who;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * Provisioning connector for Google Apps
 * @author suneet
 *
 */
@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "GoogleAppsConnectorServicePort", 
		serviceName="GoogleAppsConnectorService")
public class GoogleAppsConnectorImpl implements ConnectorService {

	private static final Log log = LogFactory.getLog(GoogleAppsConnectorImpl.class);
	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	
	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected ResourceDataService resourceDataService;
	protected IdmAuditLogDataService auditDataService;
	protected LoginDataService loginManager;
	protected PolicyDAO policyDao;
	protected SecurityDomainDataService secDomainService; 
	protected UserDataService userManager;
	
	  private static final String APPS_FEEDS_URL_BASE =
	      "https://apps-apis.google.com/a/feeds/";
	
	public boolean testConnection(String targetID) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void init() {
		String filename = System.getProperty("java.home")
		+ "/lib/security/cacerts".replace('/', File.separatorChar);
		System.out.println("filenname=" + filename);
		String password = "changeit";
		System.setProperty("javax.net.ssl.trustStore", res.getString("KEYSTORE"));
		System.setProperty("javax.net.ssl.trustStorePassword",res.getString("KEYSTORE_PSWD"));
	}

	public AddResponseType add(AddRequestType reqType) {
		String userName = null;
		String password = null;
		String givenName = null;
		String lastName = null;
		
		init();
		
		log.debug("Google Apps: add request called..");
		
		String requestID = reqType.getRequestID();
		/* ContainerID - May specify the container in which this object should be created
		 *      ie. ou=Development, org=Example */
		PSOIdentifierType containerID = reqType.getContainerID();
		System.out.println("ContainerId =" + containerID );
	
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();
		userName = psoID.getID();
		
		log.debug("PSOId=" + psoID.getID());

		
		/* targetID -  */
		String targetID = reqType.getTargetID();
				
		// Data sent with request - Data must be present in the request per the spec
		ExtensibleType data = reqType.getData(); 
		Map<QName,String> otherData  = reqType.getOtherAttributes();
		
		/* Indicates what type of data we should return from this operations */
		ReturnDataType returnData =  reqType.getReturnData();
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);  
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
		
		
		List<ExtensibleObject> requestAttributeList =  reqType.getData().getAny();
		
		for( ExtensibleObject obj : requestAttributeList ) {
			List<ExtensibleAttribute> attrList =  obj.getAttributes();
			for (ExtensibleAttribute att: attrList) {
				
				log.debug("Attr Name=" + att.getName() +  " " + att.getValue());
				
				String name = att.getName();
				String value = att.getValue();
				
				if (name.equalsIgnoreCase("password")) {
					password = value;
					
				}
				if (name.equalsIgnoreCase("firstName")) {
					givenName = value;
					
				}
				if (name.equalsIgnoreCase("lastName")) {
					lastName = value;
					
				}
			}
		}
		
		
		 try {
		    	UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");
		    	
		    	log.debug("google connector login:" + managedSys.getUserId() );
		    	log.debug("google connector PASSWORD:" + managedSys.getDecryptPassword() );
		    	
		    	userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
		    	
		    	UserEntry entry = new UserEntry();
		        Login login = new Login();
		        login.setUserName(userName);
		        login.setPassword(password);
		        entry.addExtension(login);

		        Name name = new Name();
		        name.setGivenName(givenName);
		        name.setFamilyName(lastName);
		        entry.addExtension(name);
		        
		        String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";
		        
		        log.debug("BASE URL=" + APPS_FEEDS_URL_BASE);
		        
		        URL insertUrl = new URL(domainUrlBase );
		        userService.insert(insertUrl, entry);
		        
	 	} catch (ServiceException e) {
			e.printStackTrace();
			log.error(e.getStackTrace());
		
			AddResponseType response = new AddResponseType();
			response.setStatus(StatusCodeType.FAILURE);
			response.setError(ErrorCode.ALREADY_EXISTS);
			return response;
			
		 	
		} catch (MalformedURLException e) {
			AddResponseType response = new AddResponseType();
			response.setError(ErrorCode.MALFORMED_REQUEST);
			log.error(e.getStackTrace());
			return response;
			
		} catch (IOException e) {
			AddResponseType response = new AddResponseType();
			response.setError(ErrorCode.UNSUPPORTED_OPERATION);
			log.error(e.getStackTrace());
			return response;
		}
	
		
		AddResponseType response = new AddResponseType();
		response.setStatus(StatusCodeType.SUCCESS);
	
		return response;

	}

	public ModifyResponseType modify(ModifyRequestType reqType) {
		String userName = null;
		String firstName = null;
		String lastName = null;
		boolean change = false;
		
		init();
		
		String requestID = reqType.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();		
		userName = psoID.getID();
		
		/* targetID -  */
		String targetID = psoID.getTargetID();	
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID); 
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
	 	
	 	List<ModificationType> modTypeList = reqType.getModification(); 
	 	
	 	// check if its a rename request
	 	ExtensibleAttribute origIdentity = isRename(modTypeList);
	 	if (origIdentity != null) {
	 		log.debug("Renaming identity: " + origIdentity.getValue());
	 		
	 		ModifyResponseType respType = renameIdentity(userName, origIdentity.getValue(), managedSys, matchObj);
	 		if (respType.getStatus() == StatusCodeType.FAILURE) {
	 			return respType;
	 		}
	 	} else {
	 	
	 		// get the firstName and lastName values
	 	
		 	for (ModificationType mod: modTypeList) {
		 		ExtensibleType extType =  mod.getData();
		 		List<ExtensibleObject> extobjectList = extType.getAny();
		 		for (ExtensibleObject obj: extobjectList) {
		 			
		 			log.debug("Object:" + obj.getName() + " - operation=" + obj.getOperation());
		 			
		 			List<ExtensibleAttribute> attrList =  obj.getAttributes();
		 			List<ModificationItem> modItemList = new ArrayList<ModificationItem>();
		 			for (ExtensibleAttribute att: attrList) {
		 				if (att.getOperation() != 0 && att.getName() != null) {
		 					if (att.getName().equalsIgnoreCase("firstName")) {
		 						firstName = att.getValue();
		 						change = true;
		 					}
		 					if (att.getName().equalsIgnoreCase("lastName")) {
		 						lastName = att.getValue();
		 						change = true;
		 					}
		 				}
		 			}
		 		}
		 	}
		 	
		 	// assign to google 
		 	if ( change) {
		 		UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");	
		 	
			 	try {
		    		userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
		    		String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";    		
			        URL updateUrl = new URL(domainUrlBase + "/" + userName );
		
			    	UserEntry entry = new UserEntry();
			        
			        Name n = new Name();
			        if (firstName != null) {
			        	n.setGivenName(firstName);
			        }
			        if (lastName != null) {
			        	n.setFamilyName(lastName);
			        }
			        entry.addExtension(n);
			       
			        userService.update(updateUrl, entry);	
					
				} catch (AuthenticationException e) {
					log.error(e);
					ModifyResponseType respType = new ModifyResponseType();
				 	respType.setStatus(StatusCodeType.FAILURE);
				 	respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
				 	return respType;
				} catch (MalformedURLException e) {
					log.error(e);
					ModifyResponseType respType = new ModifyResponseType();
				 	respType.setStatus(StatusCodeType.FAILURE);
				 	respType.setError(ErrorCode.MALFORMED_REQUEST);
				 	return respType;
				} catch (AppsForYourDomainException e) {
					System.out.println("Google AppsForYourDomainException=" + e.getCodeName());
					log.error(e);
				//	e.printStackTrace();
					ModifyResponseType respType = new ModifyResponseType();
				 	respType.setStatus(StatusCodeType.FAILURE);
				 	respType.setError(ErrorCode.INVALID_CONTAINMENT);
				 	return respType;
				} catch (IOException e) {
					log.error(e);
					e.printStackTrace();
				} catch (ServiceException e) {
					log.error(e);
					System.out.println("Google ServiceException...=" + e.getCodeName());
					ModifyResponseType respType = new ModifyResponseType();
				 	respType.setStatus(StatusCodeType.FAILURE);
				 	respType.setError(ErrorCode.CUSTOM_ERROR);
				 	return respType;
				}
			
		 	}
	 	}
	 	ModifyResponseType respType = new ModifyResponseType();
	 	respType.setStatus(StatusCodeType.SUCCESS);
	 	return respType; 
	 	
	}
	
	private ModifyResponseType renameIdentity(String newIdentity, String origIdentity, ManagedSys managedSys, ManagedSystemObjectMatch matchObj) {
 		UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");	
	 	
	 	try {
    		userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
    		String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";    		
	        URL updateUrl = new URL(domainUrlBase + "/" + origIdentity );

	    	UserEntry entry = new UserEntry();
	        Login login = new Login();
	        login.setUserName(newIdentity);
	        entry.addExtension(login);
	        
	        userService.update(updateUrl, entry);

			
		} catch (AuthenticationException e) {
			log.error(e);
			e.printStackTrace();
			ModifyResponseType respType = new ModifyResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
		 	return respType;
		} catch (MalformedURLException e) {
			log.error(e);
			e.printStackTrace();
			ModifyResponseType respType = new ModifyResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.MALFORMED_REQUEST);
		 	return respType;
		} catch (AppsForYourDomainException e) {
			log.error(e);
			e.printStackTrace();
			ModifyResponseType respType = new ModifyResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.INVALID_CONTAINMENT);
		 	return respType;
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
			ModifyResponseType respType = new ModifyResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.CUSTOM_ERROR);
		 	return respType;
		}
		ModifyResponseType respType = new ModifyResponseType();
	 	respType.setStatus(StatusCodeType.SUCCESS);
	 	return respType;		
	}
	
	private ExtensibleAttribute isRename(List<ModificationType> modTypeList ) {
	 	for (ModificationType mod: modTypeList) {
	 		ExtensibleType extType =  mod.getData();
	 		List<ExtensibleObject> extobjectList = extType.getAny();
	 		for (ExtensibleObject obj: extobjectList) {
	 			
	 			log.debug("Object:" + obj.getName() + " - operation=" + obj.getOperation());
	 			
	 			List<ExtensibleAttribute> attrList =  obj.getAttributes();
	 			List<ModificationItem> modItemList = new ArrayList<ModificationItem>();
	 			for (ExtensibleAttribute att: attrList) {
	 				if (att.getOperation() != 0 && att.getName() != null) {
	 					if (att.getName().equalsIgnoreCase("ORIG_IDENTITY")) {
	 						return att;
	 					}
	 				}
	 			}
	 		}
	 	}
	 	return null;
	}

	public ResponseType delete(DeleteRequestType reqType) {
		init();
		
		
		String userName = null;
		
		String requestID = reqType.getRequestID();

		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = reqType.getPsoID();
		userName = psoID.getID();
		/* targetID -  */
		String targetID = psoID.getTargetID();
		/* ContainerID - May specify the container in which this object should be created
		 *      ie. ou=Development, org=Example */
		PSOIdentifierType containerID = psoID.getContainerID();
			
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys =  managedSysService.getManagedSys(targetID);  
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
	
	 	
    	UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");
    	try {
    		userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
    		String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";    		
	        URL deleteUrl = new URL(domainUrlBase + "/" + userName );
	        userService.delete(deleteUrl);
			
		} catch (AuthenticationException e) {
			log.error(e);
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
		 	return respType;
		} catch (MalformedURLException e) {
			log.error(e);
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.MALFORMED_REQUEST);
		 	return respType;
		} catch (AppsForYourDomainException e) {
			log.error(e);
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.INVALID_CONTAINMENT);
		 	return respType;
		} catch (IOException e) {
			log.error(e);
		} catch (ServiceException e) {
			log.error(e);
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.CUSTOM_ERROR);
		 	return respType;
		}

	 	
	 	ResponseType respType = new ResponseType();
	 	respType.setStatus(StatusCodeType.SUCCESS);
	 	return respType;

	}

	public LookupResponseType lookup(LookupRequestType reqType) {
		log.debug("Google connector:lookup. Will return a failure");
		LookupResponseType resp = new LookupResponseType();
		resp.setStatus(StatusCodeType.FAILURE);
		return resp;
	}

	public ResponseType setPassword(SetPasswordRequestType request) {
		String userName = null;
		
		init();
		
		String requestID = request.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = request.getPsoID();
		
		userName = psoID.getID();
		
		/* targetID -  */
		String targetID = psoID.getTargetID();	
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID); 
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	}
    	
	 	UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");	
	 	
	 	try {
    		userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
    		String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";    		
	        URL updateUrl = new URL(domainUrlBase + "/" + userName );

	    	UserEntry entry = new UserEntry();
	        Login login = new Login();
	        login.setPassword(request.getPassword());
	        entry.addExtension(login);
	        
	        userService.update(updateUrl, entry);

			
		} catch (AuthenticationException e) {
			log.error(e);
			e.printStackTrace();
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
		 	return respType;
		} catch (MalformedURLException e) {
			log.error(e);
			e.printStackTrace();
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.MALFORMED_REQUEST);
		 	return respType;
		} catch (AppsForYourDomainException e) {
			log.error(e);
			e.printStackTrace();
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.INVALID_CONTAINMENT);
		 	return respType;
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (ServiceException e) {
			log.error(e);
			e.printStackTrace();
			ResponseType respType = new ResponseType();
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.CUSTOM_ERROR);
		 	return respType;
		}	 	
	 	
	
	 	
	 	ResponseType respType = new ResponseType();
	 	respType.setStatus(StatusCodeType.SUCCESS);
	 	return respType;

	}

	public ResponseType expirePassword(ExpirePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResetPasswordResponseType resetPassword(
			ResetPasswordRequestType request) {
		// TODO Auto-generated method stub
		init();
		
		return null;
	}

	public ValidatePasswordResponseType validatePassword(
			ValidatePasswordRequestType request) {
		// TODO Auto-generated method stub
		return null;
	}

	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	public ManagedSystemObjectMatchDAO getManagedSysObjectMatchDao() {
		return managedSysObjectMatchDao;
	}

	public void setManagedSysObjectMatchDao(
			ManagedSystemObjectMatchDAO managedSysObjectMatchDao) {
		this.managedSysObjectMatchDao = managedSysObjectMatchDao;
	}

	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}

	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}

	public IdmAuditLogDataService getAuditDataService() {
		return auditDataService;
	}

	public void setAuditDataService(IdmAuditLogDataService auditDataService) {
		this.auditDataService = auditDataService;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

	public PolicyDAO getPolicyDao() {
		return policyDao;
	}

	public void setPolicyDao(PolicyDAO policyDao) {
		this.policyDao = policyDao;
	}

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}

	public ResponseType suspend(SuspendRequestType request) {
		String userName = null;
		String firstName = null;
		String lastName = null;
		boolean change = false;
		ResponseType respType = new ResponseType();
		
		init();
		
		String requestID = request.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = request.getPsoID();		
		userName = psoID.getID();
		
		/* targetID -  */
		String targetID = psoID.getTargetID();	
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID); 
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	} 	
	 	
 		UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");	
		 	
	 	try {
    		userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
    		String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";    		
	        URL updateUrl = new URL(domainUrlBase + "/" + userName );
	        URL retrieveUrl = new URL(domainUrlBase + "/" + userName);

	        UserEntry userEntry = userService.getEntry(retrieveUrl, UserEntry.class);
	        userEntry.getLogin().setSuspended(true);
	        	       
	        userService.update(updateUrl, userEntry);

			
		} catch (AuthenticationException e) {
			log.error(e);

		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
		 	return respType;
		} catch (MalformedURLException e) {
			log.error(e);
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.MALFORMED_REQUEST);
		 	return respType;
		} catch (AppsForYourDomainException e) {
			log.error(e);
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.INVALID_CONTAINMENT);
		 	return respType;
		} catch (IOException e) {
			log.error(e);

		} catch (ServiceException e) {
			log.error(e);
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.CUSTOM_ERROR);
		 	return respType;
		}
		
		respType.setStatus(StatusCodeType.SUCCESS);
		return respType;
	
 	}




	public ResponseType resume(ResumeRequestType request) {
		String userName = null;
		String firstName = null;
		String lastName = null;
		boolean change = false;
		ResponseType respType = new ResponseType();
		
		init();
		
		String requestID = request.getRequestID();
		/* PSO - Provisioning Service Object -
		 *     -  ID must uniquely specify an object on the target or in the target's namespace  
		 *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
		PSOIdentifierType psoID = request.getPsoID();		
		userName = psoID.getID();
		
		/* targetID -  */
		String targetID = psoID.getTargetID();	
		
	
		/* A) Use the targetID to look up the connection information under managed systems */
		ManagedSys managedSys = managedSysService.getManagedSys(targetID); 
	 	ManagedSystemObjectMatch matchObj = null;
	 	List<ManagedSystemObjectMatch> matchObjList = managedSysObjectMatchDao.findBySystemId(targetID, "USER");
	 	if (matchObjList != null && matchObjList.size() > 0 ) {
	 		matchObj = matchObjList.get(0);
	 	} 	
	 	
 		UserService userService = new UserService("gdata-sample-AppsForYourDomain-UserService");	
		 	
	 	try {
    		userService.setUserCredentials(managedSys.getUserId(), managedSys.getDecryptPassword());
    		String domainUrlBase = APPS_FEEDS_URL_BASE + matchObj.getBaseDn()  + "/user/2.0";    		
	        URL updateUrl = new URL(domainUrlBase + "/" + userName );
	        URL retrieveUrl = new URL(domainUrlBase + "/" + userName);

	        UserEntry userEntry = userService.getEntry(retrieveUrl, UserEntry.class);
	        userEntry.getLogin().setSuspended(false);
	        	       
	        userService.update(updateUrl, userEntry);

			
		} catch (AuthenticationException e) {
			log.error(e);

		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.NO_SUCH_IDENTIFIER);
		 	return respType;
		} catch (MalformedURLException e) {
			log.error(e);
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.MALFORMED_REQUEST);
		 	return respType;
		} catch (AppsForYourDomainException e) {
			log.error(e);
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.INVALID_CONTAINMENT);
		 	return respType;
		} catch (IOException e) {
			log.error(e);

		} catch (ServiceException e) {
			log.error(e);
		 	respType.setStatus(StatusCodeType.FAILURE);
		 	respType.setError(ErrorCode.CUSTOM_ERROR);
		 	return respType;
		}
		
		respType.setStatus(StatusCodeType.SUCCESS);
		return respType;
	}


	
}
