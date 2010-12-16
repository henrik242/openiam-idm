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

import org.openiam.idm.srvc.policy.dto.PolicyDef;

/**
 * Data access implementation for PolicyDefinitions
 * @author suneet
 *
 */
public interface PolicyDefDAO {

	public abstract void add(PolicyDef transientInstance);

	public abstract void remove(PolicyDef persistentInstance);

	public abstract PolicyDef update(PolicyDef detachedInstance);

	public PolicyDef findById(java.lang.String id);
	public List<String> findAllPolicyTypes();
	public List<PolicyDef> findAllPolicyDef();

	public List<PolicyDef> findByExample(PolicyDef instance);

}