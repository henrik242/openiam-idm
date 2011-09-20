package org.openiam.webadmin.user;

import java.util.List;

import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.openiam.idm.srvc.auth.dto.Login;


public class EditUserValidator implements Validator {

	



	public boolean supports(Class cls) {
		 return EditUserCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		System.out.println("EditUserValidator:validate");
		
		EditUserCommand editUserCmd =  (EditUserCommand) cmd;
			
		if (editUserCmd.getUser().getFirstName() == null || editUserCmd.getUser().getFirstName().length() == 0) {
			err.rejectValue("user.firstName", "required");
		}
		if (editUserCmd.getUser().getLastName() == null || editUserCmd.getUser().getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}			
		
	}


	
}
