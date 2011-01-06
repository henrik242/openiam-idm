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
package org.openiam.provision.service;

import java.util.List;

import org.openiam.idm.srvc.res.dto.Resource;

/**
 * Consolidates the the policies of all the resources that are associated with a managed system.
 * In enterprise systems its possible to have a system where we need to control the attributes for several 
 * module. Each of these modules can have their own attribute policies but they are all processed by one 
 * connector
 * @author suneet
 *
 */
public class ManagedSysPolicy {
	String managedSysId;
	List<Resource> resourceList;
	public String getManagedSysId() {
		return managedSysId;
	}
	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
}
