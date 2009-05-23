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
 * Generates SAML s compliant assertions for use with SSO
 * @author suneet
 *
 */
public class SAML2TokenModule implements SSOTokenModule {

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#createToken(java.util.Map)
	 */
	public String createToken(Map tokenParam) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#isTokenValid(java.lang.String, java.lang.String)
	 */
	public boolean isTokenValid(String userId, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.sso.SSOTokenModule#refreshToken(java.lang.String, java.lang.String)
	 */
	public String refreshToken(String userId, String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
