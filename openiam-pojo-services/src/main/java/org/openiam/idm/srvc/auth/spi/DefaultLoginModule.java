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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.sso.SSOTokenModule;
import org.openiam.idm.srvc.user.service.UserDataService;

/**
 * DefaultLoginModule provides basic password based authentication using the OpenIAM repository.
 * @author suneet
 *
 */
public class DefaultLoginModule implements LoginModule {
	
	SSOTokenModule defaultToken;
	LoginDataService loginManager;
	UserDataService userManager;
	
	private static final Log log = LogFactory.getLog(DefaultLoginModule.class);

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
		
		PasswordCredential cred =  (PasswordCredential)authContext.getCredential();
		
		String principal = cred.getPrincipal();
		String domainId = cred.getDomainId();
		String password = cred.getPassword();
			
		if (principal == null || principal.isEmpty())
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);
		
		if (password == null 	|| password.equals(""))
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_PASSWORD);
		
		Login lg = loginManager.getLogin(domainId,principal);
		
		if (lg == null) {
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);
		}
		
		// check the password
		if (!lg.getPassword().equals(password)) {

			// increment the auth fail counter
			int failCount = lg.getAuthFailCount().intValue();	
			failCount++;
			lg.setAuthFailCount(failCount);
			if (failCount >= 3) {
				 // lock ther record and save the record.
				lg.setIsLocked(1);
				loginManager.updateLogin(lg);				
				throw new AuthenticationException(AuthenticationConstants.RESULT_LOGIN_LOCKED);
			}else {
				// update the counter save the record
				loginManager.updateLogin(lg);
				throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_PASSWORD);				
			}		
		}
		
		// Successful login
		Subject sub = new Subject();
		sub.setUserId(lg.getUser().getUserId());
		sub.setToken(lg.getUser().getUserId());
		sub.setResultCode(1);
		
		
		return sub;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#logout(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void logout(String securityDomain, String principal,
			String managedSysId) {
		// TODO Auto-generated method stub

	}

	public SSOTokenModule getDefaultToken() {
		return defaultToken;
	}

	public void setDefaultToken(SSOTokenModule defaultToken) {
		this.defaultToken = defaultToken;
	}

	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}
	
	/* supporting methods */
	
	private String token(String userId) {
		Map tokenParam = new HashMap();
		tokenParam.put("USER_ID",userId);
		return defaultToken.createToken(tokenParam);
	}





}
