package org.openiam.webadmin.user;

import java.util.List;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class DelegationFilterValidator implements Validator {
	

	public boolean supports(Class cls) {
		 return DelegationFilterCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {

		
		DelegationFilterCommand filterCmd =  (DelegationFilterCommand) cmd;

			
		
		
	}



	
}
