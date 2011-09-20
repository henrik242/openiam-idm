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
package org.openiam.idm.srvc.pswd.rule;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.dto.LoginId;
import org.openiam.idm.srvc.auth.login.LoginDAO;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.service.PasswordHistoryDAO;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDAO;
import org.openiam.script.ScriptFactory;
import org.openiam.script.ScriptIntegration;
import org.openiam.util.encrypt.Cryptor;

/**
 * PasswordValidator validates a password against the password policy.
 * @author suneet
 *
 */
public class PasswordValidatorImpl implements PasswordValidator {

	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService; 
	protected UserDAO userDao;
	protected LoginDAO loginDao;
	protected PasswordHistoryDAO passwordHistoryDao;
	protected Cryptor cryptor;
	protected String scriptEngine;
	
	private static final Log log = LogFactory.getLog(PasswordValidatorImpl.class);
	
	public PasswordValidatorImpl() {
		
	}

	

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.rule.PasswordValidator#validate(org.openiam.idm.srvc.pswd.dto.Password)
	 */
	public PasswordValidationCode validate(Policy pswdPolicy, Password password) throws ObjectNotFoundException, IOException {
		Class cls = null;
		AbstractPasswordRule rule  = null;
		ScriptIntegration se = null;
		
		// get the password policy for this domain
		//SecurityDomain securityDomain =  secDomainService.getSecurityDomain( password.getDomainId() );
		//Policy pswdPolicy =  policyDataService.getPolicy( securityDomain.getPasswordPolicyId() ) ;
		// get the list of rules for password validation
		List<PolicyDefParam> defParam = policyDataService.getPolicyDefParamByGroup(pswdPolicy.getPolicyDefId(),"PSWD_COMPOSITION");
		
		// get the user object for the principal
		Login lg = loginDao.findById(new LoginId(password.getDomainId(), password.getPrincipal(), password.getManagedSysId()));
		User usr = userDao.findById(lg.getUserId());
		
		
		// for each rule
		if (defParam != null) {
			   for ( PolicyDefParam param : defParam) {
				  //check if this is parameter that is the policy that we need to check 
				  if (policyToCheck(param.getDefParamId(), pswdPolicy)) {
				  //
					  String strRule =  param.getPolicyParamHandler();
					  if (strRule != null && strRule.length() > 0) {
							// -- instantiate the rule class
						  log.info("StrRule:" + strRule);
						  if (param.getHandlerLanguage() == null || param.getHandlerLanguage().equalsIgnoreCase("java")) {
							  try {
								  cls = Class.forName(strRule);
								  rule = (AbstractPasswordRule)cls.newInstance();
							  }catch(Exception c) {
								  log.info("Error creating object: " + strRule);
								  log.error(c);
								  throw new ObjectNotFoundException();
							  }
						  }else {
							  try {
									se = ScriptFactory.createModule(this.scriptEngine); 
								}catch(Exception e) {
									log.error(e);
									e.printStackTrace();
									return null;
								}
								rule = (AbstractPasswordRule)se.instantiateClass(null, strRule);
							  
						  }
					  }
					  // -- set the parameters
					  rule.setPassword(password.getPassword());
					  rule.setPrincipal(password.getPrincipal());
					  rule.setManagedSysId(password.getManagedSysId());
					  rule.setUser(usr);
					  rule.setLg(lg);
					  rule.setPolicy(pswdPolicy);
					  rule.setPasswordHistoryDao(passwordHistoryDao);
					  rule.setCryptor(cryptor);
					// -- check if valid
					  PasswordValidationCode retval = rule.isValid();
					  
					  if ( retval != PasswordValidationCode.SUCCESS ) {
						  log.info("Password failed validation check for rule:" + strRule);
						  return retval;
					  }else {
						  log.info("Passed validation for:" + strRule);
					  }
				  }
				  
			   }
		}
	
		return PasswordValidationCode.SUCCESS;
	}

	private boolean policyToCheck(String defParamId, Policy pswdPolicy) {

		Set<PolicyAttribute> attrSet = pswdPolicy.getPolicyAttributes();
		Iterator<PolicyAttribute> atrIt = attrSet.iterator();
		while (atrIt.hasNext()) {
			PolicyAttribute atr = atrIt.next();
			if (atr.getDefParamId().equals(defParamId)) {
				return true;
			}
		}
		return false;
		
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



	public UserDAO getUserDao() {
		return userDao;
	}



	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}



	public LoginDAO getLoginDao() {
		return loginDao;
	}



	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}



	public PasswordHistoryDAO getPasswordHistoryDao() {
		return passwordHistoryDao;
	}



	public void setPasswordHistoryDao(PasswordHistoryDAO passwordHistoryDao) {
		this.passwordHistoryDao = passwordHistoryDao;
	}



	public Cryptor getCryptor() {
		return cryptor;
	}



	public void setCryptor(Cryptor cryptor) {
		this.cryptor = cryptor;
	}



	public String getScriptEngine() {
		return scriptEngine;
	}



	public void setScriptEngine(String scriptEngine) {
		this.scriptEngine = scriptEngine;
	}




}
