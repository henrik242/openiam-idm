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
package org.openiam.idm.srvc.auth.spi;

import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.dto.Subject;

/**
 * Interface for a LoginModule.  A LoginModule is responsible for authentication. All LoginModules in OpenIAM must
 * implement the LoginModule interface. The AuthenticationService will utilize these modules.
 * @author suneet
 *
 */
public interface LoginModule {

	/**
	 * Authenticates a user based on the information submitted in the AuthenticationContext
	 * @param authContext
	 * @return
	 * @throws AuthenticationException
	 */
	Subject login(AuthenticationContext authContext) throws AuthenticationException;
	
	/**
	 * Logs out the user associate with the principal from the managed system specified.
	 * @param securityDomain
	 * @param principal
	 * @param managedSysId
	 */
	void logout(String securityDomain, String principal, String managedSysId);
	
	/**
	 * logs out the user associated with the principal from all applications
	 * @param securityDomain
	 * @param principal
	 */
	void globalLogout(String securityDomain, String principal);
	
}
