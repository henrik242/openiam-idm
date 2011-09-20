package org.openiam.selfsrvc.usradmin;

import java.util.List;

import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.openiam.idm.srvc.auth.dto.Login;


public class EditUserValidator implements Validator {




	public boolean supports(Class cls) {
		 return EditUserCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {

		
		EditUserCommand editUserCommand =  (EditUserCommand) cmd;
        User user = editUserCommand.getUser();

        if (editUserCommand.getUser().getFirstName() == null || editUserCommand.getUser().getFirstName().length() == 0) {
			err.rejectValue("user.firstName", "required");

		}
		if (user.getLastName() == null || user.getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}
		if (user.getSex() == null || user.getSex().equalsIgnoreCase("-") ) {
			err.rejectValue("user.sex", "required");
		}

        // check if the required attributes have been filled in
        List<UserAttribute> attrList =  editUserCommand.getAttributeList();
        if (attrList != null) {
            for ( UserAttribute ua : attrList) {
                if (ua.getRequired() == true ) {
                    if (ua.getValue() == null || ua.getValue().length() == 0) {
                        err.rejectValue("attributeList", "required");
                        return;
                    }
                }
            }
        }
			
		
		
	}


}
