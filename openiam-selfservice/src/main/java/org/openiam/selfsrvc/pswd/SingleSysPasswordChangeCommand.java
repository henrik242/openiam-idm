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
public class SingleSysPasswordChangeCommand implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4513024965714401771L;

	private String password;
	private String confPassword;
	private String domainId;
	private String principal;
	private String sysId;
	private String userId;
	private String submit;
	private String currentPassword;
	
	public SingleSysPasswordChangeCommand( ) {
		super();
		
	}	
	
	public SingleSysPasswordChangeCommand( String principal,
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

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	


	
	

	

}
