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
package org.openiam.idm.srvc.role.service;

import java.util.List;

import org.openiam.idm.srvc.role.dto.UserRole;
import org.openiam.idm.srvc.user.dto.User;

/**
 * DAO Interface for UserRole. Manages the relationship between user and role.
 * @author Suneet Shah
 *
 */
public interface UserRoleDAO {

	public abstract void add(UserRole transientInstance);

	public abstract void remove(UserRole persistentInstance);

	public abstract UserRole update(UserRole detachedInstance);

	public abstract UserRole findById(java.lang.String id);
	
	public void removeUserFromRole(String serviceId, String roleId,	String userId);
	
	public void removeAllUsersInRole(String domainId, String roleId);
	
	/**
	 * Returns a list of users in a role.
	 * @param roleId
	 * @return
	 */
	List<User> findUserByRole(String domainId, String roleId);

}