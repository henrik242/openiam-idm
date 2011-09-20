package org.openiam.webadmin.conn.def;


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
/**
 * Validator for the ConnectorDefinition controller
 * @author suneet
 *
 */
public class ConnectorDefinitionDetailValidator implements Validator {

	
	public boolean supports(Class cls) {
		 return ConnectorDefinitionDetailCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		ConnectorDefinitionDetailCommand connectorCmd =  (ConnectorDefinitionDetailCommand) cmd;

		
		
	}

}
