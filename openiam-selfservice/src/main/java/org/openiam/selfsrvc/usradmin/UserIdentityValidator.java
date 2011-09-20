package org.openiam.selfsrvc.usradmin;

import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserIdentityValidator implements Validator {
    LoginDataWebService loginManager;


    public boolean supports(Class cls) {
        return UserIdentityCommand.class.equals(cls);
    }

    public void validate(Object cmd, Errors err) {


        UserIdentityCommand newHireCmd = (UserIdentityCommand) cmd;


    }

    public LoginDataWebService getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginDataWebService loginManager) {
        this.loginManager = loginManager;
    }


}
