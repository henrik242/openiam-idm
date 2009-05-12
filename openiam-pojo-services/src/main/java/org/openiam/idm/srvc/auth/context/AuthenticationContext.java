package org.openiam.idm.srvc.auth.context;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import org.openiam.idm.srvc.auth.service.AuthenticationConstants;

/**
 * AuthenticationContext enables a higher level of flexibility during the authentication
 * process.
 * @author Suneet Shah
 *
 */
public class AuthenticationContext implements Serializable {

	private String authenticationType;
	
	private String resourceId;
	
	private Credential credential;
	
	private String serviceId;
	private String principal;
	private String password;
	
	
	private Map<String,Object> authParamList = new HashMap();
	

	
	public void AuthenticationContext() {
		
	}
	
	
	/**
	 * Returns an object to capture the credentials appropriate for a specific type of 
	 * authentication. The type of authentication is specified by the authnType parameter.
	 * @param authnType
	 */
	public Credential createCredentialObject(String authnType) {
		if (authnType.equals(AuthenticationConstants.AUTHN_TYPE_PASSWORD)) {
			return new PasswordCredential();
		}
		return null;
	}
	
	public void setCredential(String authnType, Credential cred) {
		authenticationType = authnType;
		credential = cred;
	}
	


	
}
