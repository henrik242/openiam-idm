package org.openiam.webadmin.user;

import java.util.List;


import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
public class UserIdentityValidator implements Validator {
	LoginDataWebService loginManager;


	public boolean supports(Class cls) {
		 return UserIdentityCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {

		
		UserIdentityCommand newHireCmd =  (UserIdentityCommand) cmd;

			
		
		
	}

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	
}
