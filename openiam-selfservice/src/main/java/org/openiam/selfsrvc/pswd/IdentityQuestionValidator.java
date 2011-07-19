package org.openiam.selfsrvc.pswd;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class IdentityQuestionValidator implements Validator {
	
	
	private static final Log log = LogFactory.getLog(IdentityQuestionValidator.class);

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		log.debug("Validating challenge response");
	
		boolean required = true;

		IdentityQuestionCommand idQuestCmd =  (IdentityQuestionCommand) cmd;
		
		// validate the list of answer
		List<UserIdentityAnswer> ansList =idQuestCmd.getAnswerList();
		
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

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class cls) {
		return IdentityQuestionCommand.class.equals(cls);
	}


			
}
	
