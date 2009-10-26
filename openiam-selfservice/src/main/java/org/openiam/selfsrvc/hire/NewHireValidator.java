package org.openiam.selfsrvc.hire;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.user.dto.User;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class NewHireValidator implements Validator {

	private static final Log log = LogFactory.getLog(NewHireValidator.class);
	
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

		if (user.getCompanyId() == null || user.getCompanyId().equalsIgnoreCase("-") ) {
			err.rejectValue("user.companyId", "required");
		}
		if (user.getDeptCd() == null || user.getDeptCd().equalsIgnoreCase("-") ) {
			err.rejectValue("user.deptCd", "required");
		}
		if (user.getEmployeeType() == null || user.getEmployeeType().equalsIgnoreCase("-") ) {
			err.rejectValue("user.employeeType", "required");
		}
		if (newHireCmd.getManagedBy() == null || newHireCmd.getManagedBy().equalsIgnoreCase("-") ) {
			err.rejectValue("managedBy", "required");
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
	
		if (user.getTitle() == null || user.getTitle().equalsIgnoreCase("") ) {
			err.rejectValue("user.title", "required");
		}
		
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object cmd, Errors arg1) {
		NewHireCommand newHireCmd =  (NewHireCommand) cmd;
		
	}


}
