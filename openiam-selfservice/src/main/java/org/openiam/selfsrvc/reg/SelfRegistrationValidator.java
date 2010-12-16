package org.openiam.selfsrvc.reg;

import java.util.List;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class SelfRegistrationValidator implements Validator {
	UserDataService userMgr;
	LoginDataService loginManager;
	
	public LoginDataService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataService loginManager) {
		this.loginManager = loginManager;
	}

	public UserDataService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataService userMgr) {
		this.userMgr = userMgr;
	}

	public boolean supports(Class cls) {
		 return SelfRegistrationCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		SelfRegistrationCommand newHireCmd =  (SelfRegistrationCommand) cmd;
		String firstName = newHireCmd.getUser().getFirstName();
		String lastName = newHireCmd.getUser().getLastName();

		
		
		
	}

}
