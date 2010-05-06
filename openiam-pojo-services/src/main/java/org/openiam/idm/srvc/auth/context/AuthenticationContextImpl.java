package org.openiam.idm.srvc.auth.context;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.base.ws.ObjectMapAdapter;


/**
 * AuthenticationContext enables a higher level of flexibility during the authentication
 * process.
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationContextImpl", propOrder = {
    "authenticationType",
    "resourceId",
    "credential",
    "domainId",
    "principal",
    "password",
    "authParam",
    "loginModule"
})
@XmlSeeAlso({
    PasswordCredential.class
})
public class AuthenticationContextImpl implements Serializable, AuthenticationContext {

	private String authenticationType;
	/* Class that will be responsible for authentication  */
	private String loginModule;
	private String resourceId;
	private Credential credential;
	private String domainId;
	private String principal;
	private String password;	
	@XmlJavaTypeAdapter(ObjectMapAdapter.class)
	private Map<String,Object> authParam = new HashMap();
	
	private static final Log log = LogFactory.getLog(AuthenticationContextImpl.class);
	


	public void AuthenticationContext() {
		
	}
	
	/**
	 * Add a parameter to the context
	 * @param key
	 * @param value
	 */
	public void addParam(String key, Object value) {
		authParam.put(key, value);
	}
	/**
	 * Retrieve a parameter from the context
	 * @param key
	 * @return
	 */
	public Object getParam(String key) {
		return (authParam.get(key));
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#createCredentialObject(java.lang.String)
	 */
	public Credential createCredentialObject(String authnType) {
		if (authnType.equals(AuthenticationConstants.AUTHN_TYPE_PASSWORD)) {
			return new PasswordCredential();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#setCredential(java.lang.String, org.openiam.idm.srvc.auth.context.Credential)
	 */
	public void setCredential(String authnType, Credential cred) {
		authenticationType = authnType;
		credential = cred;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#getCredential()
	 */
	public Credential getCredential() {
		return credential;
	}

	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#getResourceId()
	 */
	public String getResourceId() {
		return resourceId;
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#setResourceId(java.lang.String)
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#getAuthParam()
	 */
	public Map<String, Object> getAuthParam() {
		return authParam;
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.context.AuthenticationContext#setAuthParam(java.util.Map)
	 */
	public void setAuthParam(Map<String, Object> authParam) {
		this.authParam = authParam;
	}

	public String getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getLoginModule() {
		return loginModule;
	}

	public void setLoginModule(String loginModule) {
		this.loginModule = loginModule;
	}
	
}
