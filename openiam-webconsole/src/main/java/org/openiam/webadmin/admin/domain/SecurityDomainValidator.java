package org.openiam.webadmin.admin.domain;


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * Validator for the ConnectorDefinition controller
 * @author suneet
 *
 */
public class SecurityDomainValidator implements Validator {

	
	public boolean supports(Class cls) {
		 return SecurityDomainCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		SecurityDomainCommand domainCmd =  (SecurityDomainCommand) cmd;

		if (domainCmd.getDomain().getName() == null || domainCmd.getDomain().getName().length() == 0 ) {
			err.rejectValue("domain.name", "required");
		}
				
		
	}

}
