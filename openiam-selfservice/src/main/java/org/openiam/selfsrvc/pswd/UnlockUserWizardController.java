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
package org.openiam.selfsrvc.pswd;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;

import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.service.ProvisionService;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author suneet
 *
 */
public class UnlockUserWizardController extends AbstractWizardFormController {

	protected LoginDataWebService loginManager;
	protected PasswordConfiguration configuration;
	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService;
	protected ChallengeResponseService challengeResponse;
	
	protected static final Log log = LogFactory.getLog(UnlockUserWizardController.class);
	protected ProvisionService provisionService;	
	

	

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException arg3)
			throws Exception {
		
		UnlockUserCommand cmd  =(UnlockUserCommand)command;
		
		// get objects from the command object
		String password = cmd.getPassword();
		
		// update the password in the openiam repository of the primary id
		String managedSysId = configuration.getDefaultManagedSysId();
		String secDomainId = configuration.getDefaultSecurityDomain();
		
		//String encPassword = loginManager.encryptPassword(password);
		//this.loginManager.resetPassword(secDomainId, cmd.getPrincipal(), managedSysId, encPassword);
		
		log.info("-Sync password start");
		PasswordSync passwordSync = new PasswordSync("RESET PASSWORD", managedSysId, password, 
				cmd.getPrincipal(), null, secDomainId, "SELFSERVICE", false );

        passwordSync.setRequestClientIP(request.getRemoteHost());
        passwordSync.setRequestorLogin(cmd.getPrincipal());
        passwordSync.setRequestorDomain(secDomainId);

		provisionService.setPassword(passwordSync);
			
		log.info("-Sync password complete");
		
		
		  Map model = new HashMap();   
	      model.put("message", "Job done!");   
	      return new ModelAndView("pub/confirm");   
	        
	}

	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Map model = new HashMap();   
        model.put("message", "Request to reset the password has been canceled");   
        return new ModelAndView(new RedirectView("/login.selfserve",true));
        
	}

	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		log.debug("Validate page:" + page);
		UnlockUserValidator validator = (UnlockUserValidator)getValidator();
		switch (page) {
		case 0: 
			validator.validateUnlockUser(command, errors);
			break;
		case 1:
			validator.validateUnlockVerifyIdentity(command, errors);
			break;
		case 2:
			validator.validateUnlockSetNewPassword(command, errors);
			break;
		}
		
	}



	public PasswordConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
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

	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}

	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
	}

	public ProvisionService getProvisionService() {
		return provisionService;
	}

	public void setProvisionService(ProvisionService provisionService) {
		this.provisionService = provisionService;
	}

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}

}
