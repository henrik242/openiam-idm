package org.openiam.selfsrvc.prov;

import java.util.List;

import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;


public class ChangeAccessValidator implements Validator {
	protected UserDataWebService userMgr;
	LoginDataWebService loginManager;
	



	public UserDataWebService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}

	public boolean supports(Class cls) {
		 return ChangeAccessCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		ChangeAccessCommand newHireCmd =  (ChangeAccessCommand) cmd;
		String firstName = newHireCmd.getFirstName();
		String lastName = newHireCmd.getLastName();

		
		try {

	        UserSearch search = new UserSearch();
	        
	         // lastname
	        if (lastName != null &&  lastName.length() > 0) {
	        	search.setLastName(lastName+"%");
	    	}
	        if (firstName != null &&  firstName.length() > 0) {
	        	search.setFirstName(firstName+"%");
	    	}
			
    		
    		List userList = userMgr.search(search).getUserList();

			if (userList != null && userList.size() > 0) {
				err.rejectValue("userPrincipalName", "error.duplicate");
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}

/*	private void valiDatePhoneNumbers(ChangeAccessCommand cmd, Errors err) {
		// work phone
		if (cmd.getWorkAreaCode() != null && !cmd.getWorkAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getWorkAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("workAreaCode", "error.notnumber");
			}
		}
		if (cmd.getWorkPhone() != null && !cmd.getWorkPhone().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getWorkAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("workPhone", "error.notnumber");
			}
		}
		
		// cell phone
		if (cmd.getCellAreaCode() != null && !cmd.getCellAreaCode().isEmpty() ) { 
			try {
			Integer.valueOf( cmd.getCellAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("cellAreaCode", "error.notnumber");
			}
		}
		if (cmd.getCellPhone() != null && !cmd.getCellAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getCellPhone());
			}catch(NumberFormatException ne) {
				err.rejectValue("cellPhone", "error.notnumber");
			}
		}
		// fax phone
		if (cmd.getFaxAreaCode() != null && !cmd.getFaxAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getFaxAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("faxAreaCode", "error.notnumber");
			}
		}
		if (cmd.getFaxPhone() != null && !cmd.getFaxPhone().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getFaxPhone());
			}catch(NumberFormatException ne) {
				err.rejectValue("faxPhone", "error.notnumber");
			}
		}
		
		
		// home phone
		if (cmd.getHomePhoneAreaCode() != null && !cmd.getHomePhoneAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getHomePhoneAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("homePhoneAreaCode", "error.notnumber");
			}
		}
		if (cmd.getHomePhoneNbr() != null && !cmd.getHomePhoneNbr().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getHomePhoneNbr());
			}catch(NumberFormatException ne) {
				err.rejectValue("homePhoneNbr", "error.notnumber");
			}
		}
		
		// Alt Cell phone
		if (cmd.getAltCellAreaCode() != null && !cmd.getAltCellAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getAltCellAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("altCellAreaCode", "error.notnumber");
			}
		}
		if (cmd.getAltCellNbr() != null && !cmd.getAltCellNbr().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getAltCellNbr());
			}catch(NumberFormatException ne) {
				err.rejectValue("altCellNbr", "error.notnumber");
			}
		}

		// Personal Cell phone
		if (cmd.getPersonalAreaCode() != null && !cmd.getPersonalAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getPersonalAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("personalAreaCode", "error.notnumber");
			}
		}
		if (cmd.getPersonalNbr() != null && !cmd.getPersonalNbr().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getPersonalNbr());
			}catch(NumberFormatException ne) {
				err.rejectValue("personalNbr", "error.notnumber");
			}
		}
		
		// All Phone area code
		if (cmd.getAltPhoneAreaCode() != null && !cmd.getAltPhoneAreaCode().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getAltPhoneAreaCode());
			}catch(NumberFormatException ne) {
				err.rejectValue("altPhoneAreaCode", "error.notnumber");
			}
		}
		if (cmd.getAltPhoneNbr() != null && !cmd.getAltPhoneNbr().isEmpty()) { 
			try {
			Integer.valueOf( cmd.getAltPhoneNbr());
			}catch(NumberFormatException ne) {
				err.rejectValue("altPhoneNbr", "error.notnumber");
			}
		}
		
		
	}
	
*/
}
