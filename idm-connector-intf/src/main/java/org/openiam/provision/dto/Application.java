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
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.provision.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents an application that is to be provisioned
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application", propOrder = {
    "resourceId",
    "managedSystemId",
    "provisionMethod" ,
    "privileges"
})
public class Application implements Serializable {

	protected String resourceId;
	protected String managedSystemId;
	protected ProvisionMethodEnum provisionMethod;
	protected List<String> privileges;
	
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getManagedSystemId() {
		return managedSystemId;
	}
	public void setManagedSystemId(String managedSystemId) {
		this.managedSystemId = managedSystemId;
	}

	public List<String> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
	public ProvisionMethodEnum getProvisionMethod() {
		return provisionMethod;
	}
	public void setProvisionMethod(ProvisionMethodEnum provisionMethod) {
		this.provisionMethod = provisionMethod;
	}

}
