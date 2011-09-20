package org.openiam.selfsrvc.pswd;

import java.util.List;

import org.openiam.selfsrvc.login.LoginCommand;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class ForgotLoginValidator implements Validator {
	

	public void validate(Object cmd, Errors err) {
		boolean required = true;

		ForgotLoginCommand flCmd =  (ForgotLoginCommand) cmd;
		
		if (flCmd.getEmailAddres() == null || flCmd.getEmailAddres().length() == 0 ) {
			err.rejectValue("email","required");
			required = false;
		}
		if (flCmd.getPhone() == null || flCmd.getPhone().length() == 0 ) {
			err.rejectValue("phone","required");
			required = false;
		}
		if (flCmd.getFirstName() == null || flCmd.getFirstName().length() == 0 ) {
			err.rejectValue("firstName","required");
			required = false;
		}
		if (flCmd.getLastName() == null || flCmd.getLastName().length() == 0 ) {
			err.rejectValue("lastName","required");
			required = false;
		}
		
		// validate the password against the policy
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class cls) {
		return ForgotLoginCommand.class.equals(cls);
	}


		
		
}
	
