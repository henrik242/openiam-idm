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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.context.PasswordCredential;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.sso.SSOTokenFactory;
import org.openiam.idm.srvc.auth.sso.SSOTokenModule;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;


/**
 * DefaultLoginModule provides basic password based authentication using the OpenIAM repository.
 * @author suneet
 *
 */
public class DefaultLoginModule extends AbstractLoginModule  {
	
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
		
		Subject sub = new Subject();
		
		log.info("login() in DefaultLoginModule called");
		
		// current date
		Date curDate = new Date(System.currentTimeMillis());
		PasswordCredential cred =  (PasswordCredential)authContext.getCredential();
		
		String principal = cred.getPrincipal();
		String domainId = cred.getDomainId();
		String password = cred.getPassword();
			
		//TODO principal.length==0 can be changed to principal.isEmpty() once we move to Java 6
		
		if (user != null && user.getStatus() != null ) {
			log.info("User Status=" + user.getStatus());
			if (user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {
				if (!pendingInitialStartDateCheck(user, curDate)) {
					log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID_USER_STATUS", domainId, null, principal, null, null);
					throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
				}
			}				
			if (!user.getStatus().equals(UserStatusEnum.ACTIVE) && !user.getStatus().equals(UserStatusEnum.PENDING_INITIAL_LOGIN)) {
				// invalid status
				log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID_USER_STATUS", domainId, null, principal, null, null);
				throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_USER_STATUS);
			}
			// check the secondary status
			log.info("Secondary status=" + user.getSecondaryStatus());
			checkSecondaryStatus(user);

		}	
		
		log.debug("Authentication policyid=" + securityDomain.getAuthnPolicyId());
		// get the authentication lock out policy
		Policy plcy = policyDao.findById(securityDomain.getAuthnPolicyId());
		String attrValue = getPolicyAttribute( plcy.getPolicyAttributes(), "FAILED_AUTH_COUNT");
		
		String tokenType = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_TYPE");
		String tokenLife = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_LIFE");
		String tokenIssuer = getPolicyAttribute( plcy.getPolicyAttributes(), "TOKEN_ISSUER");
		
		
		Map tokenParam = new HashMap();
		tokenParam.put("TOKEN_TYPE", tokenType);
		tokenParam.put("TOKEN_LIFE", tokenLife);
		tokenParam.put("TOKEN_ISSUER", tokenIssuer);
		tokenParam.put("PRINCIPAL", principal);
		
		// check the password
		String decryptPswd = this.decryptPassword(lg.getPassword());
		if (decryptPswd != null && !decryptPswd.equals(password)) {

			// if failed auth count is part of the polices, then do the following processing		
			if (attrValue != null) {
			
				int authFailCount = Integer.parseInt(attrValue);
				// increment the auth fail counter
				int failCount = 0;
				if (lg.getAuthFailCount() != null) {
					failCount = lg.getAuthFailCount().intValue();
				}
				failCount++;
				lg.setAuthFailCount(failCount);
				lg.setLastAuthAttempt(new Date(System.currentTimeMillis()));
				if (failCount >= authFailCount) {
					 // lock the record and save the record.
					lg.setIsLocked(1);
					loginManager.updateLogin(lg);		
					
					// set the flag on the primary user record
					user.setSecondaryStatus(UserStatusEnum.LOCKED);
					userManager.updateUser(user);
					
					log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "ACCOUNT_LOCKED", domainId, null, principal, null, null);
					throw new AuthenticationException(AuthenticationConstants.RESULT_LOGIN_LOCKED);
				}else {
					// update the counter save the record
					loginManager.updateLogin(lg);
					log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "INVALID_PASSWORD", domainId, null, principal, null, null);

					throw new AuthenticationException(AuthenticationConstants.RESULT_INVALID_PASSWORD);				
				}		
			}
		}else {
			// validate the password expiration rules
			int pswdResult = passwordExpired(lg, curDate);
			if (pswdResult == AuthenticationConstants.RESULT_PASSWORD_EXPIRED) {
				log("AUTHENTICATION", "AUTHENTICATION", "FAIL", "PASSWORD_EXPIRED", domainId, null, principal, null, null);
				throw new AuthenticationException(AuthenticationConstants.RESULT_PASSWORD_EXPIRED);	
			}
			int daysToExp = setDaysToPassworExpiration(lg,curDate, sub);
			if (daysToExp > -1) {
				sub.setDaysToPwdExp(daysToExp);
			}
			
			log.info("login successful");
			// good login - reset the counters
			lg.setLastAuthAttempt(new Date(System.currentTimeMillis()));
			lg.setLastLogin(new Date(System.currentTimeMillis()));
			lg.setAuthFailCount(0);
			lg.setFirstTimeLogin(0);
			log.info("Good Authn: Login object updated.");
			loginManager.updateLogin(lg);
			
			// check the user status
			if (user.getStatus().equals(UserStatusEnum.PENDING_INITIAL_LOGIN) ||
				// after the start date
				user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {
				user.setStatus(UserStatusEnum.ACTIVE);
				userManager.updateUser(user);
			}
		}
		
		// Successful login
		sub.setUserId(lg.getUserId());
		sub.setPrincipal(principal);
		sub.setSsoToken(token(lg.getUserId(), tokenParam));
		sub.setDomainId(domainId);
		setResultCode(lg,sub, curDate);
		
		// send message into to audit log
		
		log("AUTHENTICATION", "AUTHENTICATION", "SUCCESS", null, domainId, user.getUserId(), principal, null, null);
		
		
		return sub;
	}
	
	/**
	 * If the password has expired, but its before the grace period then its a good login
	 * If the password has expired and after the grace period, then its an exception.
	 * You should also set the days to expiration
	 * @param lg
	 * @return
	 */
	private int passwordExpired(Login lg, Date curDate) {
		if ( lg.getGracePeriod() == null) {
			// set an early date
			lg.setGracePeriod(new Date(0));
		}
		if (lg.getPwdExp() != null) {
			if ( curDate.after(lg.getPwdExp()) && curDate.after(lg.getGracePeriod()) )  {
				// check for password expiration, but successful login
				return AuthenticationConstants.RESULT_PASSWORD_EXPIRED;
			}				
			if ((curDate.after(lg.getPwdExp()) && curDate.before( lg.getGracePeriod()))) {
				// check for password expiration, but successful login
				return AuthenticationConstants.RESULT_SUCCESS_PASSWORD_EXP;
			}	
		}
		return AuthenticationConstants.RESULT_SUCCESS_PASSWORD_EXP;
	}
	
/*	private int  setDaysToPassworExpiration(Login lg, Date curDate, Subject sub) {
		if (lg.getPwdExp() == null) {
			return -1;
		}
		
		Calendar endDate = Calendar.getInstance();
		Calendar startDate = Calendar.getInstance();

		startDate.setTime(curDate);
		endDate.setTime(lg.getPwdExp());
		
		Calendar date = (Calendar) startDate.clone();  
		int daysBetween = 0;  
		while (date.before(endDate)) {  
		    date.add(Calendar.DAY_OF_MONTH, 1);  
		    daysBetween++;  
		}  
		return daysBetween;  
	}
*/	
	
	
	private String getPolicyAttribute(Set<PolicyAttribute> attr, String name) {
		assert name != null : "Name parameter is null";
		
		for ( PolicyAttribute policyAtr :attr) {
			if (policyAtr.getName().equalsIgnoreCase(name)) {
				return policyAtr.getValue1();
			}
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.spi.LoginModule#logout(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void logout(String securityDomain, String principal,
			String managedSysId) {


		log("AUTHENTICATION", "LOGOUT", "SUCCESS", null, securityDomain, null, principal, null, null);

	}


	/* supporting methods */
	
	private SSOToken token(String userId, Map tokenParam) {

		tokenParam.put("USER_ID",userId);
	
		SSOTokenModule tkModule = SSOTokenFactory.createModule((String)tokenParam.get("TOKEN_TYPE"));
		return tkModule.createToken(tokenParam);
	}






}
