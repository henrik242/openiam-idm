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

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthContextFactory;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.AuthState;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.AuthStateDAO;
import org.openiam.idm.srvc.auth.login.LoginDAOImpl;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.spi.LoginModule;
import org.openiam.idm.srvc.auth.spi.LoginModuleFactory;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;

/**
 * @author suneet
 *
 */

@WebService(endpointInterface = "org.openiam.idm.srvc.auth.service.AuthenticationService", 
		targetNamespace = "urn:idm.openiam.org/srvc/auth/service",
		serviceName = "AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	protected LoginModule defaultLoginModule;
	protected AuthStateDAO authStateDao;
	protected LoginDataService loginManager;
	protected SecurityDomainDataService secDomainService; 
	protected String authContextClass;
	
	private static final Log log = LogFactory.getLog(AuthenticationServiceImpl.class);

	
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
	public Subject passwordAuth(String secDomainId, String principal,
			String password) throws AuthenticationException {
	
		AuthenticationContext ctx = null;
		LoginModule loginModule = null;
		
		SecurityDomain secDomain = secDomainService.getSecurityDomain(secDomainId);
		if (secDomain == null) {
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_DOMAIN);
		}
		try {
			loginModule = LoginModuleFactory.createModule(secDomain.getDefaultLoginModule());
			ctx = AuthContextFactory.createContext(authContextClass);
		}catch(Exception ie) {
			log.error(ie.getMessage(),ie);
			new AuthenticationException(AuthenticationConstants.INTERNAL_ERROR,ie.getMessage(),ie);
		}
		PasswordCredential cred = (PasswordCredential) ctx.createCredentialObject( AuthenticationConstants.AUTHN_TYPE_PASSWORD );
		cred.setCredentials(secDomainId, principal,password);
		ctx.setCredential(AuthenticationConstants.AUTHN_TYPE_PASSWORD , cred);
		
		Map<String,Object> authParamMap = new HashMap<String,Object>();
		authParamMap.put("SEC_DOMAIN_ID",secDomainId);
		ctx.setAuthParam(authParamMap);
		
		Subject sub = loginModule.login(ctx);
	
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
	
	public void updateAppStatus (String managedSysId, String loginId, String status, String sessionId, String token) {
		
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

	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public String getAuthContextClass() {
		return authContextClass;
	}

	public void setAuthContextClass(String authContextClass) {
		this.authContextClass = authContextClass;
	}
	
}
