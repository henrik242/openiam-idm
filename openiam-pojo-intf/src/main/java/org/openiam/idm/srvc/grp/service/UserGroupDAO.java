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
package org.openiam.idm.srvc.grp.service;

import java.util.List;

import org.openiam.idm.srvc.grp.dto.UserGroup;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Interface for the User-Group DAO which manages the relationship between users and groups.
 * @author suneet
 *
 */
public interface UserGroupDAO {

	public abstract void add(UserGroup transientInstance);

	public abstract void remove(UserGroup persistentInstance);

	public abstract UserGroup update(UserGroup detachedInstance);

	public abstract UserGroup findById(java.lang.String id);
	
	void removeUserFromGroup(String grpId, String userId);
	
	List<UserGroup> findUserInGroup(String groupId, String userId);
	
	/**
	 * Returns a list of users that are associated with a group.
	 * @param groupId
	 * @return
	 */
	List<User> findUserByGroup(String groupId);
	

}