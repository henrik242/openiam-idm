package org.openiam.selfsrvc.hire;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class NewHireValidator implements Validator {

	private static final Log log = LogFactory.getLog(NewHireValidator.class);
    protected ResourceDataService resourceDataService;
    protected ManagedSystemDataService managedSysService;
    protected String requestType;
	
	public boolean supports(Class cls) {
		 return NewHireCommand.class.equals(cls);
	}

	public void validateNewHireForm(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		NewHireCommand newHireCmd =  (NewHireCommand) cmd;
		
		
		User user = newHireCmd.getUser();
		log.info("User from form = " + user);
		

        if (newHireCmd.getUser().getFirstName() == null || newHireCmd.getUser().getFirstName().length() == 0) {
			err.rejectValue("user.firstName", "required");

		}
		if (user.getLastName() == null || user.getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}


		if (newHireCmd.getStatus() == null || newHireCmd.getStatus().equalsIgnoreCase("-") ) {
			err.rejectValue("status", "required");
		}
		if (user.getSex() == null || user.getSex().equalsIgnoreCase("-") ) {
			err.rejectValue("user.sex", "required");
		}
		if (newHireCmd.getRole() == null || newHireCmd.getRole().equalsIgnoreCase("-") ) {
			err.rejectValue("role", "required");
        }
        // supervisor is required if the form uses supervisor as the approver
        ApproverAssociation ap = managedSysService.getApproverByRequestType(requestType, 1);

        if (ap.getAssociationType().equalsIgnoreCase("SUPERVISOR")) {
            if (newHireCmd.getSupervisorId() == null || newHireCmd.getSupervisorId().length() == 0) {
                err.rejectValue("supervisorId", "required");
            }
        }
		
	}


	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object cmd, Errors arg1) {
		NewHireCommand newHireCmd =  (NewHireCommand) cmd;
		
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
