package org.openiam.selfsrvc.util;

public class ConnectionParam {

	protected String hostName;
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getAccountpassword() {
		return accountpassword;
	}
	public void setAccountpassword(String accountpassword) {
		this.accountpassword = accountpassword;
	}
	public String getUserIdAttribute() {
		return userIdAttribute;
	}
	public void setUserIdAttribute(String userIdAttribute) {
		this.userIdAttribute = userIdAttribute;
	}
	public String getBaseDN() {
		return baseDN;
	}
	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}
	public String getSearchAttributes() {
		return searchAttributes;
	}
	public void setSearchAttributes(String searchAttributes) {
		this.searchAttributes = searchAttributes;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	protected String accountid;
	protected String accountpassword;
	protected String userIdAttribute;
	protected String baseDN;
	protected String searchAttributes;
	protected String protocol;

}

