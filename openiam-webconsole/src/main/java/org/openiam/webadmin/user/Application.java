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
package org.openiam.webadmin.user;

import java.util.List;

import org.openiam.idm.srvc.res.dto.Resource;

/**
 * Application that is used in the provisioning process.
 * @author suneet
 *
 */
public class Application extends Resource {
	
	List<String> privlegeId;
	String applicationRole;
	
	public Application() {
		
	}
	public Application(Resource res) {
		this.setBranchId(res.getBranchId());
		this.setCategoryId(res.getCategoryId());
		this.setDescription(res.getDescription());
		this.setDisplayOrder(res.getDisplayOrder());
		this.setName(res.getName());
		this.setResourceId(this.getResourceId());
		this.setResourceParent(this.getResourceParent());
		this.setSensitiveApp(res.getSensitiveApp());
	}
	
	public List<String> getPrivlegeId() {
		return privlegeId;
	}
	public void setPrivlegeId(List<String> privlegeId) {
		this.privlegeId = privlegeId;
	}
	public String getApplicationRole() {
		return applicationRole;
	}
	public void setApplicationRole(String applicationRole) {
		this.applicationRole = applicationRole;
	}

}
