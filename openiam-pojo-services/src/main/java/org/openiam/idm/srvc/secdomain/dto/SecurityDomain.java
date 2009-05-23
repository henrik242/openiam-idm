package org.openiam.idm.srvc.secdomain.dto;

// Generated Apr 19, 2007 12:21:40 AM by Hibernate Tools 3.2.0.beta8

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent SecurityDomains within the IDM system. 
 * @author Suneet Shah
 * @version 2
 */
public class SecurityDomain implements java.io.Serializable {

	private String domainId;
	private String name;
	private String status;
	private String defaultLoginModule;
	private String authSysId;
	

	


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


}
