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
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.AuthState;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.AuthStateDAO;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.spi.LoginModule;

/**
 * @author suneet
 *
 */

@WebService(endpointInterface = "org.openiam.idm.srvc.auth.service.AuthenticationService", 
		targetNamespace = "urn:idm.openiam.org/srvc/auth/service",
		serviceName = "AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	LoginModule defaultLoginModule;
	AuthStateDAO authStateDao;
	LoginDataService loginManager;
	

	
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
	
		AuthenticationContext ctx = new AuthenticationContext();
		PasswordCredential cred = (PasswordCredential) ctx.createCredentialObject( AuthenticationConstants.AUTHN_TYPE_PASSWORD );
		cred.setCredentials(domainId, principal,password);
		ctx.setCredential(AuthenticationConstants.AUTHN_TYPE_PASSWORD , cred);
		Subject sub = defaultLoginModule.login(ctx);
		
		
		sub.setPrincipal(principal);
		sub.setResultCode(AuthenticationConstants.RESULT_SUCCESS);
		return sub;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.service.AuthenticationService#validateToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean validateToken(String loginId, String token, String tokenType) {
		if (loginId == null)
			return false;
		if (token == null)
			return false;
		if (tokenType == null)
			return false;
		
		if (!tokenType.equalsIgnoreCase(AuthenticationConstants.OPENIAM_TOKEN))
			return false;
		
		// find the user for the loginId / principal
		Login lg = loginManager.getLoginByManagedSys("USR_SEC_DOMAIN", loginId, "0");
		AuthState authSt = authStateDao.findById(lg.getUser().getUserId());
		
		if (authSt == null )
			return false;
		if (authSt.getAuthState().intValue() == 0)
			return false;
		if (authSt.getAuthState().intValue() == 1) {
			String authStateTkn = authSt.getToken();
			if (authStateTkn == null)
				return false;
			if (!authStateTkn.equals(token)) {
				return false;
			}
			return true;
		}
		
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

	public LoginModule getDefaultLoginModule() {
		return defaultLoginModule;
	}

	public void setDefaultLoginModule(LoginModule defaultLoginModule) {
		this.defaultLoginModule = defaultLoginModule;
	}

	public AuthStateDAO getAuthStateDao() {
		return authStateDao;
	}

	public void setAuthStateDao(AuthStateDAO authStateDao) {
		this.authStateDao = authStateDao;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

}
