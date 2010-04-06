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
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;

/**
 * Data access implementation for Policy Definition Parameters
 * @author suneet
 *
 */
public interface PolicyDefParamDAO {

	void add(PolicyDefParam transientInstance);

	void remove(PolicyDefParam persistentInstance);

	PolicyDefParam update(PolicyDefParam detachedInstance);

	PolicyDefParam findById(java.lang.String id);
	
	List<PolicyDefParam> findPolicyDefParamByGroup(String defId, String group);


}