package org.openiam.webadmin.client;
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


import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.dto.OrganizationAttribute;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * Command object for the ClientDetailController
 * @author suneet
 *
 */
public class ClientDetailCommand implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8802546006575356954L;

	private List<Organization> childOrg = null ;
	private Organization parentOrg;
	private Set<OrganizationAttribute> orgAttributeSet;
	private Organization org;
	private MetadataType[] typeList;
    String firstName;
    String lastName;
	
	
    


	public ClientDetailCommand() {
    }


	public List<Organization> getChildOrg() {
		return childOrg;
	}




	public void setChildOrg(List<Organization> childOrg) {
		this.childOrg = childOrg;
	}




	public Organization getParentOrg() {
		return parentOrg;
	}




	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}




	public Organization getOrg() {
		return org;
	}




	public void setOrg(Organization org) {
		this.org = org;
	}


	public MetadataType[] getTypeList() {
		return typeList;
	}


	public void setTypeList(MetadataType[] typeList) {
		this.typeList = typeList;
	}


	public Set<OrganizationAttribute> getOrgAttributeSet() {
		return orgAttributeSet;
	}


	public void setOrgAttributeSet(Set<OrganizationAttribute> orgAttributeSet) {
		this.orgAttributeSet = orgAttributeSet;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
