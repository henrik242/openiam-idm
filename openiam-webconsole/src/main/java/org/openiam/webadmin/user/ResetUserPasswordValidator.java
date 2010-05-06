package org.openiam.webadmin.user;

import java.util.List;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class ResetUserPasswordValidator implements Validator {
	

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		boolean required = true;

		ResetUserPasswordCommand pswdChangeCmd =  (ResetUserPasswordCommand) cmd;
		
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
		return ResetUserPasswordCommand.class.equals(cls);
	}


		
		
}
	
