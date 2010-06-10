package org.openiam.webadmin.grp;
/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
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


import java.io.Serializable;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.org.dto.Organization;


import java.util.List;

/**
 * Command object for the ManagedSystemList 
 * @author suneet
 *
 */
public class GroupDetailCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 513253291470702152L;
	
	private List<Group> childGroup = null ;
	private Group parentGroup;
	private Group group;
	
	private List<Organization> orgList;
	private MetadataType[] typeList;
	
	public List<Group> getChildGroup() {
		return childGroup;
	}
	public void setChildGroup(List<Group> childGroup) {
		this.childGroup = childGroup;
	}
	public Group getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public List<Organization> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}
	public MetadataType[] getTypeList() {
		return typeList;
	}
	public void setTypeList(MetadataType[] typeList) {
		this.typeList = typeList;
	}
   
    
    

	

}
