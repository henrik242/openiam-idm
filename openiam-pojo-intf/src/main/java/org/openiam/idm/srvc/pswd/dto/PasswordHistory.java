package org.openiam.idm.srvc.pswd.dto;

// Generated Jan 23, 2010 1:06:13 AM by Hibernate Tools 3.2.2.GA

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * Password history object
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordHistory", propOrder = { 
		"pwdHistoryId",
		"login",
		"serviceId",
		"managedSysId",
		"dateCreated",
		"password"
	})
public class PasswordHistory implements java.io.Serializable {

	private String pwdHistoryId;
	private String login;
	private String serviceId;
	private String managedSysId;
	@XmlSchemaType(name = "dateTime")
	private Date dateCreated;
	private String password;

	public PasswordHistory() {
	}

	public PasswordHistory( String login, String serviceId,
			String managedSysId) {
		this.pwdHistoryId = pwdHistoryId;
		this.login = login;
		this.serviceId = serviceId;
		this.managedSysId = managedSysId;
		this.dateCreated = dateCreated;
	}

	public PasswordHistory(String pwdHistoryId, String login, String serviceId,
			String managedSysId, Date dateCreated, String password) {
		this.pwdHistoryId = pwdHistoryId;
		this.login = login;
		this.serviceId = serviceId;
		this.managedSysId = managedSysId;
		this.dateCreated = dateCreated;
		this.password = password;
	}

	public String getPwdHistoryId() {
		return this.pwdHistoryId;
	}

	public void setPwdHistoryId(String pwdHistoryId) {
		this.pwdHistoryId = pwdHistoryId;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getManagedSysId() {
		return this.managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
