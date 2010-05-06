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

import org.openiam.idm.srvc.policy.dto.Policy;

/**
 * Data access implementation for Policy
 * @author suneet
 *
 */
public interface PolicyDAO {

	public abstract void add(Policy transientInstance);

	public abstract void remove(Policy persistentInstance);

	public abstract Policy update(Policy detachedInstance);

	public abstract Policy findById(java.lang.String id);

	public abstract List<Policy> findByExample(Policy instance);

	public List<Policy> findAllPolicies(String policyDefId);
	
	public List<Policy> findPolicyByName(String policyDefId, String policyName);
}