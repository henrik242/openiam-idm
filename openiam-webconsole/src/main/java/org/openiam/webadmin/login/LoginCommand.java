package org.openiam.webadmin.login;

import java.io.Serializable;
import java.util.List;

import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.role.dto.Role;

/**
 * Command object for the Login
 * @author suneet
 *
 */
public class LoginCommand implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -134589435790354084L;

	  private String domainId;
	  private String password;
	  private String principal;
	  private String pin;
	  private String otp = null;
	  private String resultCode;
	  private Subject subject;
	  
	  
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	  
	





}
