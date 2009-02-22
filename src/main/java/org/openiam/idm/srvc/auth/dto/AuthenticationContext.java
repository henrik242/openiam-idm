package org.openiam.idm.srvc.auth.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

public class AuthenticationContext implements Serializable {

	private String serviceId;
	private String principal;
	private String password;
	
	private Map<String,Object> authParamList = new HashMap();
	
	/* For web integration. Could have been added to the authParamList, but its more 
	 * readable this way. 
	 */
	//private HttpServletRequest request;
	//private HttpServletResponse response;
	
	public void AuthenticationContext() {
		
	}
	
	/**
	 * Constructor to be used for the common password based authentication scenario.
	 * @param serviceId
	 * @param principal
	 * @param password
	 */
	public void AuthenticationContext(String serviceId, String principal, String password) {
		this.serviceId = serviceId;
		this.principal = principal;
		this.password = password;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	public Map<String, Object> getAuthParamList() {
		return authParamList;
	}
	public void setAuthParamList(Map<String, Object> authParamList) {
		this.authParamList = authParamList;
	}


	
}
