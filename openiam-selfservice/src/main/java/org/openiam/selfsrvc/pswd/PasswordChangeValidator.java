package org.openiam.selfsrvc.pswd;

import java.util.List;

import org.openiam.selfsrvc.login.LoginCommand;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class PasswordChangeValidator implements Validator {
	

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		boolean required = true;

		PasswordChangeCommand pswdChangeCmd =  (PasswordChangeCommand) cmd;
		
		if (pswdChangeCmd.getPassword() == null || pswdChangeCmd.getPassword().length() == 0 ) {
			err.rejectValue("password","required");
			required = false;
		}
		if (pswdChangeCmd.getConfPassword() == null || pswdChangeCmd.getConfPassword().length() == 0 ) {
			err.rejectValue("confPassword","required");
			required = false;
		}
		if (!required) {
			return;
		}
		if ( !pswdChangeCmd.getConfPassword().equals(pswdChangeCmd.getPassword() )) {
			err.rejectValue("confPassword","notequal");
			required = false;
			return;
		}
		// validate the password against the policy
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class cls) {
		return PasswordChangeCommand.class.equals(cls);
	}


		
		
}
	
