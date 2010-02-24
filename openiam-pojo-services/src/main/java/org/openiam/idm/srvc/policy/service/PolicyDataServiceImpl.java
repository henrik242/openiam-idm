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

/**
 * 
 */
package org.openiam.idm.srvc.policy.service;

import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyDef;
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;

/**
 * PolicyDataService is used create and manage policies. 
 * Enforcement of these policies is handled through policy specific services and policy enforcement points. 
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.policy.service.PolicyDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/policy/service", 
		portName = "PolicyWebServicePort",
		serviceName = "PolicyWebService")
public class PolicyDataServiceImpl implements PolicyDataService {

	PolicyDefDAO policyDefDao;
	PolicyDAO policyDao;
	PolicyDefParamDAO policyDefParamDao;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDataService#getPolicyTypes()
	 */
	public String[] getPolicyTypes() {
		// TODO Auto-generated method stub
		List<String> typeList = policyDefDao.findAllPolicyTypes();
		if (typeList == null || typeList.isEmpty())
			return null;
		int size = typeList.size();
		String[] strAry = new String[size];
		typeList.toArray(strAry);
		return strAry;
	}

	public void addPolicyDefinition(PolicyDef val) {
		if (val == null) {
			throw new NullPointerException("PolicyDef is null");
		}
		policyDefDao.add(val);
		
	}

	public PolicyDef getPolicyDefinition(String policyDefId) {
		if (policyDefId == null) {
			throw new NullPointerException("policyDefId is null");
		}
		return policyDefDao.findById(policyDefId);

	}

	public void removePolicyDefinition(String definitionId) {
		if (definitionId == null) {
			throw new NullPointerException("definitionId is null");
		}
		PolicyDef def = new PolicyDef(definitionId);
		policyDefDao.remove(def);
		
	}

	public void updatePolicyDefinition(PolicyDef val) {
		if (val == null) {
			throw new NullPointerException("PolicyDef is null");
		}
		policyDefDao.update(val);
		
	}
	/**
	 * Returns an array of all policy definitions
	 * @return
	 */
	public PolicyDef[] getAllPolicyDef() {
		List<PolicyDef> defList =  policyDefDao.findAllPolicyDef();
		if (defList == null || defList.isEmpty())
			return null;
		int size = defList.size();
		PolicyDef[] defAry = new PolicyDef[size];
		defList.toArray(defAry);
		return defAry;
	}

	
	public PolicyDefDAO getPolicyDefDao() {
		return policyDefDao;
	}

	public void setPolicyDefDao(PolicyDefDAO policyDefDao) {
		this.policyDefDao = policyDefDao;
	}

	public PolicyDAO getPolicyDao() {
		return policyDao;
	}

	public void setPolicyDao(PolicyDAO policyDao) {
		this.policyDao = policyDao;
	}

	public void addPolicy(Policy val) {
		if (val == null) {
			throw new NullPointerException("Policy is null");
		}
		policyDao.add(val);
		
	}

	public List<Policy> getAllPolicies(String policyDefId) {
		if (policyDefId == null) {
			throw new NullPointerException("policyDefId is null");
		}
		List<Policy> policyList = policyDao.findAllPolicies(policyDefId);
		
		if (policyList == null || policyList.isEmpty()) {
			return null;
		}
		return policyList;
	
	}

	public Policy getPolicy(String policyId) {
		if (policyId == null) {
			throw new NullPointerException("PolicyId is null");
		}
		return policyDao.findById(policyId);
	}
	
	/**
	 * Policy definitions parameters can be further categorized by parameter groups.
	 * @param paramGroup
	 * @return
	 */
	public List<PolicyDefParam> getPolicyDefParamByGroup(String defId, String paramGroup) {
		if (paramGroup == null) {
			throw new NullPointerException("paramGroup is null");
		}
		return policyDefParamDao.findPolicyDefParamByGroup(defId, paramGroup);
	}

	public void removePolicy(String policyId) {
		if (policyId == null) {
			throw new NullPointerException("PolicyId is null");
		}
		Policy plcy = new Policy(policyId);
		policyDao.remove(plcy);
		
	}

	public void updatePolicy(Policy val) {
		if (val == null) {
			throw new NullPointerException("Policy is null");
		}
		policyDao.update(val);
		
	}

	public boolean isPolicyExist(String policyType, String policyName) {
		if (policyType == null) {
			throw new NullPointerException("policyType is null");
		}
		if (policyName == null) {
			throw new NullPointerException("policyName is null");
		}
		List<Policy> policyList = policyDao.findPolicyByName(policyType, policyName);
		if (policyList != null && policyList.size() > 0)
			return true;
		return false;
	}

	public PolicyDefParamDAO getPolicyDefParamDao() {
		return policyDefParamDao;
	}

	public void setPolicyDefParamDao(PolicyDefParamDAO policyDefParamDao) {
		this.policyDefParamDao = policyDefParamDao;
	}



}
