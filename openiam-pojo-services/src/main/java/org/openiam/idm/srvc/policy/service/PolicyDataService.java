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
 * PolicyDataService is used create and manage policies. 
 * Enforcement of these policies is handled through policy specific services and 
 * policy enforcement points. 
 */
package org.openiam.idm.srvc.policy.service;

import javax.jws.WebService;

import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyDef;

/**
 * @author suneet
 *
 */
@WebService
public interface PolicyDataService {
	/**
	 * Returns an array of policy types that are supported by the system.
	 * @return
	 */
	String[] getPolicyTypes();
	
	/**
	 * Returns an array of all policy definitions
	 * @return
	 */
	PolicyDef[] getAllPolicyDef();

	/**
	 * Returns a policy definition. These are the parameters that are available to a policy. 
	 * @param policyDefId
	 * @return
	 */
	PolicyDef getPolicyDefinition(String policyDefId);

	
	/**
	 * Adds a new policy definition to the system. PolicyDefId must be defined.
	 * @param val
	 */
	void addPolicyDefinition(PolicyDef val);

	/**
	 * Updates an existing policy definition to the system. PolicyDefId must be defined.
	 * @param val
	 */	
	void updatePolicyDefinition(PolicyDef val);

	/**
	 * Removes a policy definition from the system.
	 * @param definitionId
	 */
	void removePolicyDefinition(String definitionId);
	

	/**
	 * Returns an array of all policies for a particular Policy
	 * @return
	 */
	Policy[] getAllPolicies(String policyDefId);

	/**
	 * Returns a policy.  
	 * @param policy
	 * @return
	 */
	Policy getPolicy(String policyId);

	
	/**
	 * Adds a new policy to the system. PolicyId and PolicyDefId must be defined.
	 * @param val
	 */
	void addPolicy(Policy val);

	/**
	 * Updates an existing policy in the system. PolicyId and PolicyDefId must be defined.
	 * @param val
	 */	
	void updatePolicy(Policy val);

	/**
	 * Removes a policy from the system.
	 * @param policyId
	 */
	void removePolicy(String policyId);
	
	/**
	 * Checks to see if a policyName exists for a given policy type.
	 * @param policyType
	 * @param policyName
	 * @return
	 */
	boolean isPolicyExist(String policyType, String policyName);

	
}
