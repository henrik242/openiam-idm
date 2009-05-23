package org.openiam.idm.srvc.auth.context;

/**
 * Enables the capture of credentials needed for password based authentication.
 * @author Suneet Shah
 *
 */
public class PasswordCredential extends BaseCredential {

	String domainId;
	String principal;
	String password;
	
	public void setCredentials(String domainId, String principal, String password) {
		this.domainId = domainId;
		this.password = password;
		this.principal = principal;
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
}
