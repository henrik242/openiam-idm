package org.openiam.webadmin.conn.mngsys;

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

public class ManagedSysListValidator implements Validator {

	

	public boolean supports(Class cls) {
		 return ManagedSysListCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		ManagedSysListCommand listCommand =  (ManagedSysListCommand) cmd;

		if (listCommand.getDomainId() != null &&
			listCommand.getDomainId().equals("-") ) {
			err.rejectValue("domainId", "required");
		}
		

		
		
	}


}
