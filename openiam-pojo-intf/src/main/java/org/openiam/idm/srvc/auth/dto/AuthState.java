package org.openiam.idm.srvc.auth.dto;

// Generated May 22, 2009 10:08:00 AM by Hibernate Tools 3.2.2.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * Used to track the authentication state of a user.  Also used with SSO. 
 */
public class AuthState implements java.io.Serializable {



	private String userId;
	private BigDecimal authState;
	private String token;
	private String aa;
	private Long expiration;
	private Date lastLogin;
	private String ipAddress;

	public AuthState() {
	}

	public AuthState(String userId) {
		this.userId = userId;
	}

	public AuthState(String aa, BigDecimal authState, Long expiration,
			String token, String userId) {
		super();
		this.aa = aa;
		this.authState = authState;
		this.expiration = expiration;
		this.token = token;
		this.userId = userId;
	}
	
	public AuthState(String userId, BigDecimal authState, String token,
			String aa, Long expiration, Date lastLogin, String ipAddress) {
		this.userId = userId;
		this.authState = authState;
		this.token = token;
		this.aa = aa;
		this.expiration = expiration;
		this.lastLogin = lastLogin;
		this.ipAddress = ipAddress;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAuthState() {
		return this.authState;
	}

	public void setAuthState(BigDecimal authState) {
		this.authState = authState;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAa() {
		return this.aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}

	public Long getExpiration() {
		return this.expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
