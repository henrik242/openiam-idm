package org.openiam.webadmin.admin.loc;

import java.util.List;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * Validator for the ConnectorDefinition controller
 * @author suneet
 *
 */
public class LocationValidator implements Validator {

	
	public boolean supports(Class cls) {
		 return LocationCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		LocationCommand locationCmd =  (LocationCommand) cmd;


				
		
	}

}
