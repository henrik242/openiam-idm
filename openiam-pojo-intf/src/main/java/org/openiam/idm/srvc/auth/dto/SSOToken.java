package org.openiam.idm.srvc.auth.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * SSOToken is used to capture the token used for single sign on
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SSOToken", propOrder = {
    "tokenType",
    "token",
    "authLevel",
    "provider",
    "clientIP",
    "principal",
    "maxIdleTime",
    "expirationTime",
    "createTime"
})
public class SSOToken {

	private String tokenType;
	private String token;
	private String authLevel;
	private String provider;
	private String clientIP;
	private String principal;
	private int maxIdleTime;
	@XmlSchemaType(name = "dateTime")
	private Date expirationTime;
	@XmlSchemaType(name = "dateTime")
	private Date createTime;
	
	public SSOToken() {
		
	}
	
	
	public SSOToken(Date createTime, Date expirationTime, String token,
			String tokenType) {
		super();
		this.createTime = createTime;
		this.expirationTime = expirationTime;
		this.token = token;
		this.tokenType = tokenType;
	}


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
