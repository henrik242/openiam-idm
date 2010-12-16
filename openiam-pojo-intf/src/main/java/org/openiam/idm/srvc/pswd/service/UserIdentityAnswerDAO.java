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
package org.openiam.idm.srvc.pswd.service;

import java.util.List;

import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;

/**
 * @author suneet
 *
 */
public interface UserIdentityAnswerDAO {

	public abstract UserIdentityAnswer add(UserIdentityAnswer transientInstance);

	public abstract void delete(UserIdentityAnswer persistentInstance);

	public UserIdentityAnswer update(
			UserIdentityAnswer detachedInstance);

	UserIdentityAnswer findById(java.lang.String id);
	List<UserIdentityAnswer> findAnswersByUser(String userId);

}