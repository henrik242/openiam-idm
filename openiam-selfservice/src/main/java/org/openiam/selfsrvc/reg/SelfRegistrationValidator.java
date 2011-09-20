package org.openiam.selfsrvc.reg;

import java.util.List;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class SelfRegistrationValidator implements Validator {


    protected ResourceDataService resourceDataService;
    protected ManagedSystemDataService managedSysService;
    protected String requestType;


	public boolean supports(Class cls) {
		 return SelfRegistrationCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		SelfRegistrationCommand newHireCmd =  (SelfRegistrationCommand) cmd;

        User user = newHireCmd.getUser();

        if (newHireCmd.getUser().getFirstName() == null || newHireCmd.getUser().getFirstName().length() == 0) {
			err.rejectValue("user.firstName", "required");

		}
		if (user.getLastName() == null || user.getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}


		if (newHireCmd.getRole() == null || newHireCmd.getRole().equalsIgnoreCase("-") ) {
			err.rejectValue("role", "required");
        }

		
	}

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
