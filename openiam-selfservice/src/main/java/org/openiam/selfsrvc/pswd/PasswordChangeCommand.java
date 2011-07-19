package org.openiam.selfsrvc.pswd;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;

/**
 * Command object for the NewHireController
 * @author suneet
 *
 */
public class PasswordChangeCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4513024965714401771L;

	private String password;
	private String confPassword;
	private String domainId;
	private String principal;
	private String submit;
	
	public PasswordChangeCommand( ) {
		super();
		
	}	
	
	public PasswordChangeCommand( String principal,
			String domainId, String password,
			String confPassword ) {
		super();
		this.confPassword = confPassword;
		this.domainId = domainId;
		this.password = password;
		this.principal = principal;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
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
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	


	
	

	

}
