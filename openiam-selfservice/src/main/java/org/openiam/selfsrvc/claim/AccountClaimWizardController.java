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
package org.openiam.selfsrvc.claim;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.BaseObject;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.service.ProvisionService;
import org.openiam.selfsrvc.pswd.IdentityQuestionCommand;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openiam.idm.srvc.user.dto.User;

/**
 * @author suneet
 *
 */
public class AccountClaimWizardController extends AbstractWizardFormController {

	protected LoginDataWebService loginManager;
	protected PasswordConfiguration configuration;
	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService;
	protected ChallengeResponseService challengeResponse;
    protected UserDataWebService userMgr;
	
	protected static final Log log = LogFactory.getLog(AccountClaimWizardController.class);
	protected ProvisionService provisionService;	
	

    	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );

	}
	

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException arg3)
			throws Exception {
		
		AccountClaimCommand cmd  =(AccountClaimCommand)command;

        // update the challenge questions
	    List<UserIdentityAnswer> answerList = cmd.getAnswerList();
		cleanupAnswerList(answerList, cmd.getUserId());
		this.challengeResponse.saveAnswers(answerList);

        UserAttribute atr = new UserAttribute("CLAIMED","1");
        // update set the CLAIMED ATTRIBUTE
        User completeUser =  userMgr.getUserWithDependent(cmd.getUserId(),true).getUser();
        if ( completeUser.getUserAttributes() != null ) {
            completeUser.getUserAttributes().put("CLAIMED",atr);

        }else {
            Map<String,UserAttribute> attrMap = new HashMap<String,UserAttribute>();
            attrMap.put("CLAIMED",atr);
            completeUser.setUserAttributes(attrMap);
        }
        userMgr.updateUserWithDependent(completeUser,true);



		// sync the password
		// get objects from the command object
		String password = cmd.getPassword();

		// update the password in the openiam repository of the primary id
		String managedSysId = configuration.getDefaultManagedSysId();
		String secDomainId = configuration.getDefaultSecurityDomain();

		//String encPassword = loginManager.encryptPassword(password);
		//this.loginManager.resetPassword(secDomainId, cmd.getPrincipal(), managedSysId, encPassword);

		log.info("-Sync password start");
		PasswordSync passwordSync = new PasswordSync("SET PASSWORD", managedSysId, password,
				cmd.getRedid(), null, secDomainId, "SELFSERVICE", false );

		provisionService.setPassword(passwordSync);

		log.info("-Sync password complete");


		  Map model = new HashMap();
	      model.put("message", "Job done!");
	      return new ModelAndView("pub/confirm");

	}

    private void cleanupAnswerList(List<UserIdentityAnswer> answerList, String userId) {


		  for (UserIdentityAnswer ans : answerList) {
			 ans.setUserId(userId);
			 if (ans.getObjectState().equalsIgnoreCase(BaseObject.NEW)) {
				 // let hibernate generate the UUID.
				 ans.setIdentityAnsId(null);
			 }
			 // set the question text with the answer. Will need it for the challenge response
			 IdentityQuestion question = challengeResponse.getQuestion(ans.getIdentityQuestionId());
			 if (question != null) {
				 ans.setQuestionText(question.getQuestionText());
			 }

		}

	}


	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		Map model = new HashMap();
        model.put("message", "Request to claim account been canceled");
        return new ModelAndView("pub/cancel");

	}

    	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		log.debug("Validate page:" + page);
		AccountClaimValidator validator = (AccountClaimValidator)getValidator();
		switch (page) {
		case 0:
			validator.validateAcceptPolicy(command, errors);
			break;
		case 1:
			validator.validateSelectUser(command, errors);
			break;
		case 2:
			validator.validateAuthQuestion(command, errors);
			break;
 		case 3:
			validator.validateSetNewPassword(command, errors);
			break;

		}

	}



    protected Object formBackingObject(HttpServletRequest request)	throws Exception {

		AccountClaimCommand cmd = new AccountClaimCommand();

		HttpSession session = request.getSession();
		//String userId = (String)session.getAttribute("userId");
        String userId = "3006";

		SecurityDomain domain = secDomainService.getSecurityDomain(configuration.getDefaultSecurityDomain());
		Policy passwordPolicy = policyDataService.getPolicy(domain.getPasswordPolicyId());

		// get the policies in place
		PolicyAttribute countAttr = passwordPolicy.getAttribute("QUEST_COUNT");
		PolicyAttribute questSrcAttr = passwordPolicy.getAttribute("QUEST_SRC");
		PolicyAttribute questListAttr = passwordPolicy.getAttribute("QUEST_LIST");

		log.debug("Question count=" + countAttr.getValue1());

		cmd.setQuestionCount(Integer.valueOf(countAttr.getValue1()));

		// check if answers to questions already exist for this user

		List<UserIdentityAnswer> answerList = challengeResponse.answersByUser(userId);
		if (answerList == null) {
			// needed for the UI
			answerList = prefillAnswerList(cmd.getQuestionCount());
		}

		log.debug("Size of answerlist=" + answerList.size());

		List<IdentityQuestion> questionList = challengeResponse.questionsByGroup(configuration.getDefaultChallengeResponseGroup());

		log.debug("question list size =" + questionList.size());

		cmd.setAnswerList( answerList);
		cmd.setQuestionList(questionList);

		return cmd;
	}

    private void setUpdateFlag(List<UserIdentityAnswer> answerList) {
		for (UserIdentityAnswer ans : answerList) {
			ans.setObjectState(BaseObject.UPDATE);
		}
	}

	private List<UserIdentityAnswer> prefillAnswerList(int questionCount) {
		List<UserIdentityAnswer> answerList = new ArrayList<UserIdentityAnswer>();
		for (int i=0; i < questionCount; i++) {
			UserIdentityAnswer ans = new UserIdentityAnswer();
			ans.setIdentityAnsId(null);
			ans.setObjectState(BaseObject.NEW);
			answerList.add(ans);
		}
		return answerList;
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

    public UserDataWebService getUserMgr() {
        return userMgr;
    }

    public void setUserMgr(UserDataWebService userMgr) {
        this.userMgr = userMgr;
    }
}
