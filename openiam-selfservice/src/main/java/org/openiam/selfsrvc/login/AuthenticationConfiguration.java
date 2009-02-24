package org.openiam.selfsrvc.login;

/**
 * Obtains configuration information used for authentication change and reset from spring configuration files.
 * @author suneet
 * @version 2
 *
 */
public class AuthenticationConfiguration {

	private String defaultSecurityDomain = null;

	public String getDefaultSecurityDomain() {
		return defaultSecurityDomain;
	}

	public void setDefaultSecurityDomain(String defaultSecurityDomain) {
		this.defaultSecurityDomain = defaultSecurityDomain;
	}
	
}
