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
package org.openiam.batch.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;

/**
 * Job bean that is called by quartz to unlock any account.
 * @author suneet
 *
 */
public class AccountUnlockJob {
	
	protected LoginDataWebService loginManager;
	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService;
	
	private static final Log log = LogFactory.getLog(AccountUnlockJob.class);
	
	public void execute() {
		log.info("Account unlock Job called.");
		// get the list of domains
		loginManager.bulkUnLock(UserStatusEnum.LOCKED);
		
		
	}



	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}


	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}


	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}



	public LoginDataWebService getLoginManager() {
		return loginManager;
	}



	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}
}
