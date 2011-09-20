/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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
package org.openiam.spml2.base;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.sso.SSOTokenFactory;
import org.openiam.idm.srvc.auth.sso.SSOTokenModule;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;

/**
 * Abstract class from which SPML2Complete implementations should be inherited.
 * @author suneet
 *
 */
public abstract class AbstractSpml2Complete {
	private static final Log log = LogFactory.getLog(AbstractSpml2Complete.class);
	
	protected String getPolicyAttribute(Set<PolicyAttribute> attr, String name) {
		assert name != null : "Name parameter is null";
		
		for ( PolicyAttribute policyAtr :attr) {
			if (policyAtr.getName().equalsIgnoreCase(name)) {
				return policyAtr.getValue1();
			}
		}
		return null;
		
	}

	public boolean pendingInitialStartDateCheck(User user, Date curDate) {
		if (user.getStatus().equals(UserStatusEnum.PENDING_START_DATE)) {
			if (user.getStartDate() != null && curDate.before(user.getStartDate()))  {
				log.debug("UserStatus= PENDING_START_DATE and user start date=" + user.getStartDate());
				return false;
			}else {
				log.debug("UserStatus= PENDING_START_DATE and user start date=null");
				return false;
			}
		}
		return true;
	}
	public int checkSecondaryStatus(User user)  {
		if (user.getSecondaryStatus() != null ) {
			if (user.getSecondaryStatus().equals(UserStatusEnum.LOCKED)  || user.getSecondaryStatus().equals(UserStatusEnum.LOCKED_ADMIN)) {
				log.debug("User is locked. throw exception.");
				return AuthenticationConstants.RESULT_LOGIN_LOCKED;
			}
			if (user.getSecondaryStatus().equals(UserStatusEnum.DISABLED)) {
				return AuthenticationConstants.RESULT_LOGIN_DISABLED;
			}
		}
		return 1;

	}
	
	public void setResultCode(Login lg, Subject sub, Date curDate) {
		if (lg.getFirstTimeLogin() == 1) {
			sub.setResultCode(AuthenticationConstants.RESULT_SUCCESS_FIRST_TIME);
		}else if (lg.getPwdExp() != null) {
			if ((curDate.after(lg.getPwdExp()) && curDate.before( lg.getGracePeriod()))) {
				// check for password expiration, but successful login
				sub.setResultCode(AuthenticationConstants.RESULT_SUCCESS_PASSWORD_EXP);
			}
		}else {
			sub.setResultCode(AuthenticationConstants.RESULT_SUCCESS);
		}

	}
	
	public int  setDaysToPassworExpiration(Login lg, Date curDate, Subject sub) {
		if (lg.getPwdExp() == null) {
			return -1;
		}
		
		long DAY = 86400000L;
		
		// lg.getPwdExp is the expiration date/time
		
		long diffInMilliseconds = lg.getPwdExp().getTime() - curDate.getTime();
		long diffInDays = diffInMilliseconds / DAY;
		
		// treat anything that is less than a day, as zero
		if (diffInDays < 1) {
			return 0;
		}
		
		return (int)diffInDays;
				
	}
	
	protected SSOToken token(String userId, Map tokenParam) {

		tokenParam.put("USER_ID",userId);
		
		SSOTokenModule tkModule = SSOTokenFactory.createModule((String)tokenParam.get("TOKEN_TYPE"));
		return tkModule.createToken(tokenParam);
	}
	


	
}
