package org.openiam.selfsrvc.profile;

import java.util.List;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class ProfileValidator implements Validator {

	


	public boolean supports(Class cls) {
		 return ProfileCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		System.out.println("ProfileValidator:validate");
		
		ProfileCommand newHireCmd =  (ProfileCommand) cmd;
		//String userPrincipalName = newHireCmd.getUserPrincipalName();
		//String firstName = newHireCmd.getFirstName();
		//String lastName = newHireCmd.getLastName();
		


		
				//err.rejectValue("userPrincipalName", "error.duplicate");
			
		
		
	}


	
}
