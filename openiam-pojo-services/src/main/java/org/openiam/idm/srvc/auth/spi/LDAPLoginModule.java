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
 * LDAPLoginModule provides basic password based authentication using an LDAP directory.
 * @author suneet
 *
 */
public class LDAPLoginModule implements LoginModule {

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#globalLogout(java.lang.String, java.lang.String)
	 */
	public void globalLogout(String securityDomain, String principal) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#login(org.openiam.idm.srvc.auth.context.AuthenticationContext)
	 */
	public Subject login(AuthenticationContext authContext)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#logout(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void logout(String securityDomain, String principal,
			String managedSysId) {
		// TODO Auto-generated method stub

	}

}
