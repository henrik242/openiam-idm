package org.openiam.webadmin.user;

import java.util.List;

import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.openiam.idm.srvc.auth.dto.Login;


public class EditUserValidator implements Validator {
	UserDataWebService userMgr;
	LoginDataWebService loginManager;
	



	public boolean supports(Class cls) {
		 return EditUserCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		System.out.println("ProfileValidator:validate");
		
		EditUserCommand newHireCmd =  (EditUserCommand) cmd;
			
		
		
	}

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}

	public UserDataWebService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}

	
}
