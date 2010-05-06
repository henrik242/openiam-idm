package org.openiam.webadmin.admin.loc;

import java.util.List;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.Search;
import org.openiam.util.db.SearchImpl;
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
