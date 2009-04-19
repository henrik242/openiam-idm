package org.openiam.idm.srvc.auth.dto;

import java.util.Date;

/**
 * SSOToken is used to capture the token used for single sign on
 * @author Suneet Shah
 *
 */
public class SSOToken {

	private String tokenType;
	private String token;
	private String authLevel;
	private String provider;
	private String clientIP;
	private String principal;
	private int maxIdleTime;
	private Date expirationTime;
	private Date createTime;
	
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public int getMaxIdleTime() {
		return maxIdleTime;
	}
	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}
	public Date getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
