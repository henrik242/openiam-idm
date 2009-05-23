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
package org.openiam.idm.srvc.auth.sso;

import java.util.Map;

/**
 * Interface for classes for the management of SSO tokens. The implementations are used by the authentication service and login modules.
 * @author suneet
 *
 */
public interface SSOTokenModule {

	/**
	 * Creates a new token.
	 * @param tokenParam
	 * @return
	 */
	String createToken(Map tokenParam);
	/**
	 * Updates the token
	 * @param userId
	 * @param token
	 * @return
	 */
	String refreshToken(String userId, String token);
	/**
	 * Determines if a token is still valid
	 * @param userId
	 * @param token
	 * @return
	 */
	boolean isTokenValid(String userId, String token);

}
