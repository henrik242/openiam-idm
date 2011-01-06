package org.openiam.selfsrvc.prov;

import java.util.List;


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class NewRequestValidator implements Validator {

	

	public boolean supports(Class cls) {
		 return NewRequestCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		NewRequestCommand newHireCmd =  (NewRequestCommand) cmd;


		
		
		
	}

		
	
}
