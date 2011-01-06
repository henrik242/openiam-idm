package org.openiam.idm.srvc.secdomain.dto;

// Generated Apr 19, 2007 12:21:40 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Class to represent SecurityDomains within the IDM system. 
 * @author Suneet Shah
 * @version 2
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityDomain", propOrder = {
    "domainId",
    "name",
    "status",
    "defaultLoginModule",
    "authSysId",
    "passwordPolicyId",
    "authnPolicyId",
    "auditPolicyId"
})
public class SecurityDomain implements java.io.Serializable {

	private String domainId;
	private String name;
	private String status;
	private String defaultLoginModule;
	private String authSysId;
	private String passwordPolicyId;
	private String authnPolicyId;
	private String auditPolicyId;




	// Constructors

	/** default constructor */
	public SecurityDomain() {
	}

	/** minimal constructor */
	public SecurityDomain(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDefaultLoginModule() {
		return defaultLoginModule;
	}

	public void setDefaultLoginModule(String defaultLoginModule) {
		this.defaultLoginModule = defaultLoginModule;
	}

	public String getAuthSysId() {
		return authSysId;
	}

	public void setAuthSysId(String authSysId) {
		this.authSysId = authSysId;
	}

	public String getPasswordPolicyId() {
		return passwordPolicyId;
	}

	public void setPasswordPolicyId(String passwordPolicyId) {
		this.passwordPolicyId = passwordPolicyId;
	}

	public String getAuthnPolicyId() {
		return authnPolicyId;
	}

	public void setAuthnPolicyId(String authnPolicyId) {
		this.authnPolicyId = authnPolicyId;
	}

	public String getAuditPolicyId() {
		return auditPolicyId;
	}

	public void setAuditPolicyId(String auditPolicyId) {
		this.auditPolicyId = auditPolicyId;
	}


}
