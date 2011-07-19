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
 * Base case from which all LoginModule should be inherited.
 */
package org.openiam.idm.srvc.auth.spi;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.exception.EncryptionException;
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.AuditLogUtil;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.SSOToken;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.sso.SSOTokenModule;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.util.encrypt.Cryptor;

/**
 * @author suneet
 *
 */
public abstract class AbstractLoginModule implements LoginModule {

	protected SSOTokenModule defaultToken;
	protected LoginDataService loginManager;
	protected UserDataService userManager;
	protected PolicyDAO policyDao;
	protected SecurityDomain securityDomain;
	protected Cryptor cryptor;

	protected AuditLogUtil auditUtil;
    protected ResourceDataService resourceService;
    protected PasswordService passwordManager;

	protected User user;
	protected Login lg;
    protected String authPolicyId;


	static protected ResourceBundle res = ResourceBundle.getBundle("securityconf");
	private static final Log log = LogFactory.getLog(AbstractLoginModule.class);
	
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
	
	public void setPolicyDAO(PolicyDAO policyDao) {
		this.policyDao = policyDao;
	}
	
	public void setSecurityDomain(SecurityDomain secDom) {
		this.securityDomain = secDom;
	}
	
	public String decryptPassword(String encPassword) {
		if ( encPassword != null) {
			try {
			return cryptor.decrypt(encPassword ) ;
			}catch(EncryptionException e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Checks to see if the current date is after the start date for the user.
	 * @param user
	 * @param curDate
	 * @return
	 */
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
	public void checkSecondaryStatus(User user) throws AuthenticationException {
		if (user.getSecondaryStatus() != null ) {
			if (user.getSecondaryStatus().equals(UserStatusEnum.LOCKED)  || user.getSecondaryStatus().equals(UserStatusEnum.LOCKED_ADMIN)) {
				log.debug("User is locked. throw exception.");
				throw new AuthenticationException(AuthenticationConstants.RESULT_LOGIN_LOCKED);
			}
			if (user.getSecondaryStatus().equals(UserStatusEnum.DISABLED)) {
				throw new AuthenticationException(AuthenticationConstants.RESULT_LOGIN_DISABLED);
			}
		}

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

	




	
	/**
	 * Logs a message into the audit log.
	 * @param objectTypeId
	 * @param actionId
	 * @param actionStatus
	 * @param reason
	 * @param domainId
	 * @param userId
	 * @param principal
	 * @param linkedLogId
	 * @param clientId
	 */
	public void log(String objectTypeId, String actionId, String actionStatus, String reason, 
			String domainId, String userId, String principal, 
			String linkedLogId, String clientId) {
		IdmAuditLog log = new IdmAuditLog( objectTypeId, actionId, actionStatus,
				reason, domainId, userId, principal,
				linkedLogId, clientId);
		auditUtil.log(log);
	}

	public Cryptor getCryptor() {
		return cryptor;
	}

	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}

	public AuditLogUtil getAuditUtil() {
		return auditUtil;
	}

	public void setAuditUtil(AuditLogUtil auditUtil) {
		this.auditUtil = auditUtil;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Login getLg() {
		return lg;
	}

	public void setLg(Login lg) {
		this.lg = lg;
	}

    public String getAuthPolicyId() {
        return authPolicyId;
    }

    public void setAuthPolicyId(String authPolicyId) {
        this.authPolicyId = authPolicyId;
    }

    public ResourceDataService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceDataService resourceService) {
        this.resourceService = resourceService;
    }

    public PasswordService getPasswordManager() {
        return passwordManager;
    }

    public void setPasswordManager(PasswordService passwordManager) {
        this.passwordManager = passwordManager;
    }
}
