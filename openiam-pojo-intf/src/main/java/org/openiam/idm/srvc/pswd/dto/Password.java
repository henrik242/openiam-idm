/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.pswd.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Object representing a password in OpenIAM
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Password", propOrder = { 
		"domainId",
		"principal",
		"password",
		"managedSysId",
		"requestBy",
		"srcApplicationId"
	})
public class Password {

	protected String domainId;
	protected String principal;
	protected String password;
	protected String managedSysId;
	protected String requestBy;
	protected String srcApplicationId;
	
	public Password() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Password(String domainId, String managedSysId, String password,
			String principal) {
		super();
		this.domainId = domainId;
		this.managedSysId = managedSysId;
		this.password = password;
		this.principal = principal;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getManagedSysId() {
		return managedSysId;
	}
	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}


	public String getRequestBy() {
		return requestBy;
	}


	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}


	public String getSrcApplicationId() {
		return srcApplicationId;
	}


	public void setSrcApplicationId(String srcApplicationId) {
		this.srcApplicationId = srcApplicationId;
	}
	
}
