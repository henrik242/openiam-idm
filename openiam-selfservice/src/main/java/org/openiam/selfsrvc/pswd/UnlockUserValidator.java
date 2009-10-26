package org.openiam.selfsrvc.pswd;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class UnlockUserValidator implements Validator {
	
	protected LoginDataService loginManager;
	protected PasswordConfiguration configuration;
	protected ChallengeResponseService challengeResponse;
	protected PolicyDataService policyDataService;
	protected SecurityDomainDataService secDomainService;
	
	private static final Log log = LogFactory.getLog(UnlockUserValidator.class);

	public void validate(Object cmd, Errors err) {
		UnlockUserCommand idQuestCmd =  (UnlockUserCommand) cmd;		
	}
	public void validateUnlockUser(Object cmd, Errors err) {
		// (Page 0)
		
		boolean required = true;
		log.debug("unlockUser validator called.");	
		UnlockUserCommand idQuestCmd =  (UnlockUserCommand) cmd;
		
		if (idQuestCmd.getPrincipal() == null || idQuestCmd.getPrincipal().length() == 0 ) {
			err.rejectValue("principal","required");
			return;
		}
		// check if the login exists
		Login lg = loginManager.getLoginByManagedSys(configuration.getDefaultSecurityDomain(), 
				idQuestCmd.getPrincipal(), 
				/* Our primary identity */
				configuration.getDefaultManagedSysId());
		if (lg == null) {
			err.rejectValue("principal","invalid");
			return;
		}
		// check if this user has answered the challenge response questions
		boolean answerStatus = challengeResponse.userAnserExists(lg.getUserId());
		if (!answerStatus) {
			err.rejectValue("principal", "noanswers");
			return;
		}
		// initialize the questions for this user.
		initQuestion((UnlockUserCommand)cmd, lg.getUserId());
		
	}
	public void validateUnlockVerifyIdentity(Object cmd, Errors err) {
		// Page 1
		
		boolean required = true;
		log.debug("unlockVerifyIdentity validator called.");	
		UnlockUserCommand idQuestCmd =  (UnlockUserCommand) cmd;
		
		List<UserIdentityAnswer> ansList = idQuestCmd.getAnswerList();

		log.debug("AsnwerList=" + ansList);

		boolean valid = challengeResponse.isResponseValid(configuration.getDefaultSecurityDomain(),
				idQuestCmd.getPrincipal(),
				configuration.getDefaultManagedSysId(),
				configuration.getDefaultChallengeResponseGroup(),
				ansList);
		if (!valid) {
			err.rejectValue("domainId","notequal");
		}

		
	}
	public void validateUnlockSetNewPassword(Object cmd, Errors err) {
		// Page 2 (Finish)

		boolean required = true;
		log.debug("unlockSetNewPassword validator called.");	
		UnlockUserCommand idQuestCmd =  (UnlockUserCommand) cmd;
		
		// validate the password against the policy
		if (idQuestCmd.getPassword() == null || idQuestCmd.getPassword().length() == 0 ) {
			err.rejectValue("password","required");
			required = false;
		}
		if (idQuestCmd.getConfPassword() == null || idQuestCmd.getConfPassword().length() == 0 ) {
			err.rejectValue("confPassword","required");
			required = false;
		}
		if (!required) {
			return;
		}
		if ( !idQuestCmd.getConfPassword().equals(idQuestCmd.getPassword() )) {
			err.rejectValue("confPassword","notequal");
			required = false;
			return;
		}
		
		// validate the password against the policy
		
	}
	 

	public void initQuestion(UnlockUserCommand cmd, String userId) {
		log.debug("Answerlist in the command object=" + cmd.getAnswerList());
		if (cmd.getAnswerList() != null && cmd.getAnswerList().size() > 0) {
			// dont load the list if it has already been loaded.
			return;
		}
		
		List<UserIdentityAnswer> answerList = challengeResponse.answersByUser(userId);
		for (UserIdentityAnswer ans : answerList) {
			// dont want to show the answer on the UI for the challenge response
			ans.setQuestionAnswer(null);
		}		
		cmd.setAnswerList(answerList);
		
		
	}
		


	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class cls) {
		return UnlockUserCommand.class.equals(cls);
	}
	public LoginDataService getLoginManager() {
		return loginManager;
	}
	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}
	public PasswordConfiguration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}
	public ChallengeResponseService getChallengeResponse() {
		return challengeResponse;
	}
	public void setChallengeResponse(ChallengeResponseService challengeResponse) {
		this.challengeResponse = challengeResponse;
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
	
}
	
