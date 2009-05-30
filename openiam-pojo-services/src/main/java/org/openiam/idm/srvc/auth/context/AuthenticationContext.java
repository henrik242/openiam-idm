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
package org.openiam.idm.srvc.auth.context;

import java.util.Map;

/**
 * AuthenticationContext enables a higher level of flexibility during the authentication
 * process.
 * @author suneet
 *
 */
public interface AuthenticationContext {

	/**
	 * Returns an object to capture the credentials appropriate for a specific type of 
	 * authentication. The type of authentication is specified by the authnType parameter.
	 * @param authnType
	 */
	public abstract Credential createCredentialObject(String authnType);

	public abstract void setCredential(String authnType, Credential cred);

	public abstract Credential getCredential();

	public abstract String getResourceId();

	public abstract void setResourceId(String resourceId);

	/**
	 * Returns a map of a authentication parameters that are needed by the login module.
	 * @return
	 */
	public abstract Map<String, Object> getAuthParam();

	/**
	 * Sets the parameters that are to be used by the login module.
	 * @param authParam
	 */
	public abstract void setAuthParam(Map<String, Object> authParam);

}