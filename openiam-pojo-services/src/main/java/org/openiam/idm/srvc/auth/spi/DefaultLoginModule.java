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

import java.io.FileInputStream;
import java.io.InputStream;
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
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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

	
	public DefaultLoginModule() {	
	}
	
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
		
		//TODO principal.length==0 can be changed to principal.isEmpty() once we move to Java 6
		if (principal == null || principal.length()==0)
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_LOGIN);
		
		if (password == null 	|| password.equals(""))
			throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_PASSWORD);
		
		Login lg = loginManager.getLoginByManagedSys(domainId,principal, (String)authContext.getParam("AUTH_SYS_ID"));
		
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
				 // lock the record and save the record.
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
		sub.setUserId(lg.getUserId());
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


	/* supporting methods */
	
	private String token(String userId) {
		Map tokenParam = new HashMap();
		tokenParam.put("USER_ID",userId);
		return defaultToken.createToken(tokenParam);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#setLoginService(org.openiam.idm.srvc.auth.login.LoginDataService)
	 */
	public void setLoginService(LoginDataService loginManager) {
		this.loginManager = loginManager;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#setTokenModule(org.openiam.idm.srvc.auth.sso.SSOTokenModule)
	 */
	public void setTokenModule(SSOTokenModule defaultToken) {
		this.defaultToken = defaultToken;	
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#setUserService(org.openiam.idm.srvc.user.service.UserDataService)
	 */
	public void setUserService(UserDataService userManager) {
		this.userManager = userManager;
		
	}





}
