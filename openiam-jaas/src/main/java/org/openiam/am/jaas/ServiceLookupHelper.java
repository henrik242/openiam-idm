package org.openiam.am.jaas;

import java.util.ResourceBundle;

import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;

import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class ServiceLookupHelper {

	static protected ResourceBundle res = ResourceBundle.getBundle("iam_client_config");
	static String serviceBaseUrl = res.getString("openiam.service_base_url");
	static String secDomain = res.getString("openiam.securityDomain");
	static String managedSysId = res.getString("openiam.managedSysId");
	

	//static public void 
	
	static public AuthenticationService getAuthenticationService() {
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(AuthenticationService.class);
           
           
        factory.setAddress(serviceBaseUrl + "/idm-ws/idmsrvc/AuthenticationService");
         javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("AuthenticationServicePort");
         factory.setEndpointName(qname);
         return (AuthenticationService)factory.create();
         
	}

	static public LoginDataWebService getLoginService() {
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(LoginDataWebService.class);
           
           
        factory.setAddress(serviceBaseUrl + "/idm-ws/idmsrvc/LoginDataWebService");
         javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("LoginDataWebServiceImplPort");
         factory.setEndpointName(qname);
         return (LoginDataWebService)factory.create();
         
	}

	static public UserDataWebService getUserService() {
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(UserDataWebService.class);
           
           
        factory.setAddress(serviceBaseUrl + "/idm-ws/idmsrvc/UserDataService");
         javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("UserDataWebServiceImplPort");
         factory.setEndpointName(qname);
         return (UserDataWebService)factory.create();
         
	}
	static public RoleDataWebService getRoleService() {
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(RoleDataWebService.class);
           
           
        factory.setAddress(serviceBaseUrl + "/idm-ws/idmsrvc/RoleDataWebService");
         javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("RoleDataWebServicePort");
         factory.setEndpointName(qname);
         return (RoleDataWebService)factory.create();
         
	}
	
	static public GroupDataWebService getGroupService() {
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(GroupDataWebService.class);
           
           
        factory.setAddress(serviceBaseUrl + "/idm-ws/idmsrvc/GroupDataWebService");
         javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("GroupDataWebServicePort");
         factory.setEndpointName(qname);
         return (GroupDataWebService)factory.create();
         
	}
	
	public static String getServiceBaseUrl() {
		return serviceBaseUrl;
	}

	public static void setServiceBaseUrl(String serviceBaseUrl) {
		ServiceLookupHelper.serviceBaseUrl = serviceBaseUrl;
	}

	public static String getSecDomain() {
		return secDomain;
	}

	public static void setSecDomain(String secDomain) {
		ServiceLookupHelper.secDomain = secDomain;
	}

	public static String getManagedSysId() {
		return managedSysId;
	}

	public static void setManagedSysId(String managedSysId) {
		ServiceLookupHelper.managedSysId = managedSysId;
	}
}
