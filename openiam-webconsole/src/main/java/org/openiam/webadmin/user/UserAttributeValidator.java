package org.openiam.webadmin.user;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class UserAttributeValidator implements Validator {


	public boolean supports(Class cls) {
		 return UserAttributeCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {

		
		UserAttributeCommand attrCmd =  (UserAttributeCommand) cmd;

			
		
		
	}



	
}
