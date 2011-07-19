package org.openiam.webadmin.policy.attr;
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
import java.util.Date;
import java.util.List;
import java.util.Set;


import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.res.dto.Resource;


/**
 * Command object for the AttributePolicy form 
 * @author suneet
 *
 */
public class PolicyDetailCommand implements Serializable {


	Policy policy;
	Set<PolicyAttribute> policyAttr;
	String policyDefName;
    List<Resource> resourceList;

	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public Set<PolicyAttribute> getPolicyAttr() {
		return policyAttr;
	}
	public void setPolicyAttr(Set<PolicyAttribute> policyAttr) {
		this.policyAttr = policyAttr;
	}
	public String getPolicyDefName() {
		return policyDefName;
	}
	public void setPolicyDefName(String policyDefName) {
		this.policyDefName = policyDefName;
	}

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
