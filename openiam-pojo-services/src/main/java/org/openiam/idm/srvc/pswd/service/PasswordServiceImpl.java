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
package org.openiam.idm.srvc.pswd.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.EncryptionException;
import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDAO;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.dto.PolicyObjectAssoc;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.service.PolicyObjectAssocDAO;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordHistory;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.rule.PasswordValidator;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.util.encrypt.Cryptor;


/**
 * @author suneet
 *
 */

public class PasswordServiceImpl implements PasswordService {
	
	protected SecurityDomainDataService secDomainService; 
	protected PasswordValidator passwordValidator;
	
	protected LoginDataService loginManager;
	protected UserDataService userManager;
	PolicyObjectAssocDAO policyAssocDao;
	PolicyDataService policyDataService;
	
	protected Cryptor cryptor;
	protected PasswordHistoryDAO passwordHistoryDao;
	
	
	private static final Log log = LogFactory.getLog(PasswordServiceImpl.class);



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.pswd.PasswordService#isPasswordValid(org.openiam.idm.srvc.policy.dto.Password)
	 */
	public PasswordValidationCode isPasswordValid(Password pswd) throws ObjectNotFoundException {
		
		Policy pswdPolicy = getPasswordPolicy(pswd.getDomainId(), pswd.getPrincipal(), pswd.getManagedSysId());
		
		if (pswdPolicy == null) {
			return PasswordValidationCode.PASSWORD_POLICY_NOT_FOUND;
		}
		log.info("Selected Password policy=" + pswdPolicy.getPolicyId());
		
		return passwordValidator.validate(pswdPolicy, pswd);
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.PasswordService#daysToPasswordExpiration(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int daysToPasswordExpiration(String domainId, String principal,
			String managedSysId) {
		
		long DAY = 86400000L;
		
		long curTime = System.currentTimeMillis();
		
		
		//Date curDate = new Date(System.currentTimeMillis());
		
		Login lg =	loginManager.getLoginByManagedSys(domainId, principal, managedSysId);
		if (lg == null) {
			return -1;
		}
		if (lg.getPwdExp() == null) {
			// no expiration date
			return 9999;
		}
		
		long endTime = lg.getPwdExp().getTime();
		
		long diffInMilliseconds = endTime - curTime;
		long diffInDays = diffInMilliseconds / DAY;
		if (diffInDays < 1) {
			return 0;
		}
		return (int)diffInDays;
		


	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.PasswordService#isPasswordChangeAllowed(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean isPasswordChangeAllowed(String domainId, String principal,
			String managedSysId) {

		boolean enabled = false;
		// get the policy
		Policy policy =  getPasswordPolicy(domainId, principal, managedSysId);
		
		log.info("Password policy=" + policy);
		
		PolicyAttribute changeAttr = policy.getAttribute("PASSWORD_CHANGE_ALLOWED");
		if (changeAttr != null) {
			if (changeAttr.getValue1() != null && changeAttr.getValue1().equalsIgnoreCase("0")) {
				return false;
			}
		}
		
		
		PolicyAttribute attribute = policy.getAttribute("RESET_PER_TIME");
		if (attribute != null && attribute.getValue1() != null) {
			enabled = true;

		}
		if (enabled) {
			int changeCount = passwordChangeCount(domainId, principal, managedSysId);
			int changesAllowed =  Integer.parseInt(attribute.getValue1()); 
			
			if (changeCount >= changesAllowed) {
				return false;
			}			
		}	
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.PasswordService#passwordChangeCountByDate(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int passwordChangeCount(String domainId, String principal,
			String managedSysId) {

		Login lg =	loginManager.getLoginByManagedSys(domainId, principal, managedSysId);
		if (lg == null) {
			return -1;
		}
		return lg.getPasswordChangeCount();

	}

	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.PasswordService#getPasswordPolicy(org.openiam.idm.srvc.user.dto.User)
	 */
	public Policy getPasswordPolicy(String domainId, String principal, String managedSysId)  {
		// Find a password policy for this user
		// order of search, type, classification, domain, global
		
		PolicyObjectAssoc policyAssoc;
		
		// get the user for this principal
		Login lg = loginManager.getLoginByManagedSys(domainId, principal, managedSysId);
		log.info("login=" + lg);
		User user = this.userManager.getUserWithDependent(lg.getUserId(), false);
		
		log.info("User type and classifcation=" + user.getUserId() + " " + user.getUserTypeInd());

		if (user.getClassification() != null) {
			log.info("Looking for associate by classification.");
			 policyAssoc = policyAssocDao.findAssociationByLevel("CLASSIFICATION", user.getClassification());
			 if (policyAssoc != null) {
				 return getPolicy(policyAssoc);
			 }
		}
		
		// look to see if a policy exists for the type of user
		if (user.getUserTypeInd() != null) {
			log.info("Looking for associate by type.");
			 policyAssoc = policyAssocDao.findAssociationByLevel("TYPE", user.getUserTypeInd());
			 log.info("PolicyAssoc found=" + policyAssoc);
			 if (policyAssoc != null) {
				 return getPolicy(policyAssoc);
			 }
		}

		if (domainId != null) {
			log.info("Looking for associate by domain.");
			 policyAssoc = policyAssocDao.findAssociationByLevel("DOMAIN", domainId);
			 if (policyAssoc != null) {
				 return getPolicy(policyAssoc);
			 }	
		}
		log.info("Using global association password policy.");
		// did not find anything - get the global policy
		policyAssoc = policyAssocDao.findAssociationByLevel("GLOBAL", "GLOBAL");
		 if (policyAssoc == null) {
			 return null;
		 }
		return getPolicy(policyAssoc);			

	}
	
	private Policy getPolicy(PolicyObjectAssoc policyAssoc) {
		log.info("Retreiving policyId=" + policyAssoc.getPolicyId());
		return policyDataService.getPolicy(policyAssoc.getPolicyId());
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.PasswordService#passwordInHistory(org.openiam.idm.srvc.pswd.dto.Password, org.openiam.idm.srvc.policy.dto.Policy)
	 * 1 - In History, 0 - Not in history, -1 No policy defined
	 */
	public int passwordInHistory(Password pswd, Policy policy) {
		// get the list of passwords for this user.
		String decrypt = null;
		
		PolicyAttribute attr = policy.getAttribute("PWD_HIST_VER");
		if (attr == null || attr.getValue1() == null) {
			// no policy defined
			return -1;
		}
		int version =  Integer.parseInt( attr.getValue1() );
		List<PasswordHistory> historyList = this.passwordHistoryDao.findPasswordHistoryByPrincipal(
				 pswd.getDomainId(), pswd.getPrincipal(), pswd.getManagedSysId(), version);
		if (historyList == null || historyList.isEmpty()) {
			// no history
			return 0;
		}
		// check the list.
		log.info("Found " + historyList.size() + " passwords in the history");
		for ( PasswordHistory hist  : historyList) {
			String pwd = hist.getPassword();
			try {
				decrypt =  cryptor.decrypt(pwd);
			}catch(EncryptionException e) {
				log.error("Unable to decrypt password in history: " + pwd);
				throw new IllegalArgumentException("Unable to decrypt password in password history list");
			}
			if (pswd.getPassword().equals(decrypt)) {
				log.info("matching password found.");
				return 1;
			}
		}
		log.info("No match found.");
		return 0;
	}

	
	

	


	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}

	public PasswordValidator getPasswordValidator() {
		return passwordValidator;
	}

	public void setPasswordValidator(PasswordValidator passwordValidator) {
		this.passwordValidator = passwordValidator;
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


	public PolicyObjectAssocDAO getPolicyAssocDao() {
		return policyAssocDao;
	}


	public void setPolicyAssocDao(PolicyObjectAssocDAO policyAssocDao) {
		this.policyAssocDao = policyAssocDao;
	}


	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}


	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}




	public Cryptor getCryptor() {
		return cryptor;
	}


	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}


	public PasswordHistoryDAO getPasswordHistoryDao() {
		return passwordHistoryDao;
	}


	public void setPasswordHistoryDao(PasswordHistoryDAO passwordHistoryDao) {
		this.passwordHistoryDao = passwordHistoryDao;
	}






}
