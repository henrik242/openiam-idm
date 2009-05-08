package org.openiam.selfsrvc.prov;

import java.util.List;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.Search;
import org.openiam.util.db.SearchImpl;

public class NewRequestValidator implements Validator {

	

	public boolean supports(Class cls) {
		 return NewRequestCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		NewRequestCommand newHireCmd =  (NewRequestCommand) cmd;


		
		
		
	}

		
	
}
