package org.openiam.selfsrvc.claim;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.openiam.idm.srvc.pswd.service.ChallengeResponseService;
import org.openiam.idm.srvc.pswd.ws.PasswordWebService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.selfsrvc.pswd.PasswordConfiguration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountClaimValidator implements Validator {
	

	protected UserDataWebService userMgr;
    protected PasswordWebService passwordService;

	
	private static final Log log = LogFactory.getLog(AccountClaimValidator.class);

	public void validate(Object cmd, Errors err) {
		AccountClaimCommand idQuestCmd =  (AccountClaimCommand) cmd;
	}

    public void validateAcceptPolicy(Object cmd, Errors err) {
		// (Page 0)

		boolean required = true;
        AccountClaimCommand claimCmd =  (AccountClaimCommand) cmd;

        System.out.println("IT policy acceptance =" + claimCmd.getAcceptPolicy());

        if (claimCmd.getAcceptPolicy() == null ||  claimCmd.getAcceptPolicy() != 1) {
            err.rejectValue("acceptPolicy","required");
            System.out.println("Accept policy - validation error");
			return;
        }


	}

    public void validateSelectUser(Object cmd, Errors err) {
           // (Page 0)

           boolean required = true;
           AccountClaimCommand claimCmd =  (AccountClaimCommand) cmd;

           System.out.println("Select User validation" );

           if (claimCmd.getRedid() == null || claimCmd.getRedid().length() ==0 ) {
               err.rejectValue("redid", "required");
               required = false;
           }
           if (claimCmd.dob == null ) {
               err.rejectValue("dob","required");
                required = false;
           }
           if (claimCmd.getRegid() == null || claimCmd.getRegid().length() ==0 ) {
               err.rejectValue("regid","required");
               required = false;
           }

           if (!required) {
               return;
           }
           // check the user
        UserSearch search = new UserSearch();
        search.setDateOfBirth(claimCmd.getDob());
        search.setAttributeValue("REDID");
        search.setAttributeValue(claimCmd.getRedid());
        List<User> userList = userMgr.search(search).getUserList() ;

        if (userList == null || userList.isEmpty()) {
           err.rejectValue("redid","notfound");
           return;
        }
        User u = userList.get(0);
        claimCmd.setUserId(u.getUserId());

        User completeUser =  userMgr.getUserWithDependent(u.getUserId(),true).getUser();
        UserAttribute atr =  completeUser.getAttribute("CLAIMED");
        if (atr != null && atr.getValue().equalsIgnoreCase("1")) {
             err.rejectValue("redid","claimed");
             return;
        }



       }


     public void validateAuthQuestion(Object cmd, Errors err) {
           boolean required = true;
           AccountClaimCommand claimCmd =  (AccountClaimCommand) cmd;

         		// validate the list of answer
		List<UserIdentityAnswer> ansList = claimCmd.getAnswerList();

		// ensure that a question is selected for each line
		if (!validNumberOfQuestions(ansList)) {
			err.rejectValue("principal","requiredquestion");
			return;
		}

		// ensure that an answer has been provided for each line
		if (!validNumberOfAnswers(ansList)) {
			err.rejectValue("principal","requiredanswer");
			return;
		}
		// ensure that each question is unique
		if (duplicateQuestions(ansList)) {
			err.rejectValue("principal","dupquestion");
		}


     }

     public void validateSetNewPassword(Object cmd, Errors err) {
		// Page 2 (Finish)

		boolean required = true;
		log.debug("unlockSetNewPassword validator called.");
		AccountClaimCommand claimCmd =  (AccountClaimCommand) cmd;

		// validate the password against the policy
		if (claimCmd.getPassword() == null || claimCmd.getPassword().length() == 0 ) {
			err.rejectValue("password","required");
			required = false;
		}
		if (claimCmd.getConfPassword() == null || claimCmd.getConfPassword().length() == 0 ) {
			err.rejectValue("confPassword","required");
			required = false;
		}
		if (!required) {
			return;
		}
		if ( !claimCmd.getConfPassword().equals(claimCmd.getPassword() )) {
			err.rejectValue("confPassword","notequal");
			required = false;
			return;
		}

		// validate the password against the policy
		Password pswd = new Password();
		pswd.setDomainId("USR_SEC_DOMAIN");
		pswd.setManagedSysId("0");
		pswd.setPrincipal(claimCmd.getRedid());
		pswd.setPassword(claimCmd.getPassword());


		try {
			Response resp = passwordService.isPasswordValid(pswd);
			if (resp.getStatus() == ResponseStatus.FAILURE) {


				err.rejectValue("password",resp.getErrorCode().toString());
				required = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}



    private boolean validNumberOfQuestions(List<UserIdentityAnswer> answerList) {
        boolean retval = true;
        for ( UserIdentityAnswer ans : answerList) {
            if (ans.getIdentityQuestionId() == null || ans.getIdentityQuestionId().length() == 0 ) {
                return false;
            }
        }
        return retval;
    }
    private boolean validNumberOfAnswers(List<UserIdentityAnswer> answerList) {
        boolean retval = true;
        for ( UserIdentityAnswer ans : answerList) {
            if (ans.getQuestionAnswer() == null || ans.getQuestionAnswer().length() == 0 ) {
                return false;
            }
        }
        return retval;
    }
    private boolean duplicateQuestions(List<UserIdentityAnswer> answerList) {
        log.debug("checking for duplicates..");
        // create a map to detect duplicates
        Set<String> questionSet = new HashSet<String>();
        for ( UserIdentityAnswer ans : answerList) {
            log.debug("questionid=" + ans.getIdentityQuestionId());
            questionSet.add(ans.getIdentityQuestionId());
        }
        int mapSize = questionSet.size();

        log.debug("uniqe question list size=" + mapSize);
        log.debug("answerList size=" + answerList.size());

        // if the map has less entries, then we had duplicates
        if (answerList.size() > mapSize) {
            return true;
        }
        return false;


    }

    public PasswordWebService getPasswordService() {
        return passwordService;
    }

    public void setPasswordService(PasswordWebService passwordService) {
        this.passwordService = passwordService;
    }

    /* (non-Javadoc)
      * @see org.springframework.validation.Validator#supports(java.lang.Class)
      */
	public boolean supports(Class cls) {
		return AccountClaimCommand.class.equals(cls);
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}
	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}
	
}
	
