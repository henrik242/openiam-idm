package org.openiam.webadmin.admin.data;


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * Validator for the ConnectorDefinition controller
 * @author suneet
 *
 */
public class ChallengeQuestionValidator implements Validator {

	
	public boolean supports(Class cls) {
		 return ChallengeQuestionCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		ChallengeQuestionCommand questCmd =  (ChallengeQuestionCommand) cmd;

		if (questCmd.getIdQuest().getQuestionText() == null || questCmd.getIdQuest().getQuestionText().length() == 0 ) {
			err.rejectValue("idQuest.questionText", "required");
		}
				
		
	}

}
