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

import java.util.ArrayList;
import java.util.List;

import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.provision.type.ExtensibleUser;

/**
 * Captures a list of attributes for a managed system based on the policies that we have
 * @author suneet
 *
 */
public class ManagedSysAttributes {
	String managedSysId;

	ExtensibleUser extUser = new ExtensibleUser();
	List<Login> principalList = new ArrayList<Login>();
	
	public String getManagedSysId() {
		return managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}

	public ExtensibleUser getExtUser() {
		return extUser;
	}

	public void setExtUser(ExtensibleUser extUser) {
		this.extUser = extUser;
	}

	public List<Login> getPrincipalList() {
		return principalList;
	}

	public void setPrincipalList(List<Login> principalList) {
		this.principalList = principalList;
	}
	
}
