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
package org.openiam.idm.srvc.auth.login;

import org.openiam.idm.srvc.auth.dto.AuthState;

/**
 * DAO interface for AuthState.
 * @author suneet
 *
 */
public interface AuthStateDAO {

	public abstract void add(AuthState transientInstance);

	public abstract void remove(AuthState persistentInstance);

	public abstract AuthState update(AuthState detachedInstance);

	public abstract AuthState findById(java.lang.String id);

}