package org.openiam.idm.srvc.auth.context;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Enables the capture of credentials needed for password based authentication.
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordCredential", propOrder = {
    "domainId",
    "principal",
    "password"
})
public class PasswordCredential extends BaseCredential {

	String domainId;
	String principal;
	String password;
	
	public PasswordCredential() {
		
	}
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
