package org.openiam.webadmin.res;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.user.dto.User;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class ReconConfigurationValidator implements Validator {

	private static final Log log = LogFactory.getLog(ReconConfigurationValidator.class);
	
	public boolean supports(Class cls) {
		 return ReconConfigurationCommand.class.equals(cls);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object cmd, Errors arg1) {
		ReconConfigurationCommand synchCmd =  (ReconConfigurationCommand) cmd;
		
	}

	
/*	public void validateNewUserType(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		SynchUserCommand newUserCmd =  (SynchUserCommand) cmd;
		
		User user = newUserCmd.getUser();
	
		if (user.getMetadataTypeId() == null ||
			user.getMetadataTypeId().length() == 0) {
			err.rejectValue("user.metadataTypeId", "required");
		}
		
	}

	
	public void validateNewUserForm(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		SynchUserCommand newHireCmd =  (SynchUserCommand) cmd;
		
		User user = newHireCmd.getUser();
		log.info("User from form = " + user);
		
		
		if (newHireCmd.getUser().getFirstName() == null || newHireCmd.getUser().getFirstName().length() == 0) {
			err.rejectValue("user.firstName", "required");
		}
		if (user.getLastName() == null || user.getLastName().length() == 0) {
			err.rejectValue("user.lastName", "required");
		}	
		if (user.getCompanyId() == null || user.getCompanyId().length() == 0 ) {
			err.rejectValue("user.companyId", "required");
		}
		if (user.getDeptCd() == null || user.getDeptCd().length() == 0 ) {
			err.rejectValue("user.deptCd", "required");
		}
		if (user.getEmployeeType() == null || user.getEmployeeType().length() == 0 ) {
			err.rejectValue("user.employeeType", "required");
		}

		if (user.getSex() == null || user.getSex().length() == 0 ) {
			err.rejectValue("user.sex", "required");
		}	
		if (user.getTitle() == null || user.getTitle().equalsIgnoreCase("") ) {
			err.rejectValue("user.title", "required");
		}
		if (newHireCmd.getRole() == null || newHireCmd.getRole().equalsIgnoreCase("") ) {
			err.rejectValue("user.title", "required");
		}		
		
	}

*/

}
