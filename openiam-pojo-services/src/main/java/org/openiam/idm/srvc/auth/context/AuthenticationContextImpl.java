package org.openiam.idm.srvc.auth.context;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;

/**
 * AuthenticationContext enables a higher level of flexibility during the authentication
 * process.
 * @author Suneet Shah
 *
 */
public class AuthenticationContextImpl implements Serializable, AuthenticationContext {

	private String authenticationType;
	private String resourceId;
	private Credential credential;
	private String serviceId;
	private String principal;
	private String password;	
	private Map<String,Object> authParam = new HashMap();
	
	private static final Log log = LogFactory.getLog(AuthenticationContextImpl.class);
	


	public void AuthenticationContext() {
		
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
	
}
