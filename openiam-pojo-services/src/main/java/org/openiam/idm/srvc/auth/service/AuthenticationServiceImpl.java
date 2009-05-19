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
package org.openiam.idm.srvc.auth.service;

import javax.jws.WebService;

import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.dto.Subject;

/**
 * @author suneet
 *
 */

@WebService(endpointInterface = "org.openiam.idm.srvc.auth.service.AuthenticationService", 
		targetNamespace = "urn:idm.openiam.org/srvc/auth/service",
		serviceName = "AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#authenticate(org.openiam.idm.srvc.auth.context.AuthenticationContext)
	 */
	public Subject authenticate(AuthenticationContext ctx)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#authenticateByToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Subject authenticateByToken(String userId, String token,
			String tokenType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#globalLogout(java.lang.String)
	 */
	public void globalLogout(String userId) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#passwordAuth(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Subject passwordAuth(String domainId, String principal,
			String password) throws AuthenticationException {
		// TODO Auto-generated method stub
		Subject sub = new Subject();
		sub.setPrincipal(principal);
		sub.setResultCode(AuthenticationConstants.RESULT_SUCCESS);
		return sub;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#validateToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean validateToken(String loginId, String token, String tokenType) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#validateTokenByUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean validateTokenByUser(String userId, String token,
			String tokenType) {
		// TODO Auto-generated method stub
		return false;
	}

}
