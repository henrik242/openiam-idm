package org.openiam.selfsrvc.pswd;

import java.util.List;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.ws.PasswordWebService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class SingleSysPasswordChangeValidator implements Validator {
	
	protected PasswordWebService passwordService;
	protected PasswordConfiguration configuration;
	protected LoginDataWebService loginManager;
	

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		boolean required = true;

		SingleSysPasswordChangeCommand pswdChangeCmd =  (SingleSysPasswordChangeCommand) cmd;
		if (pswdChangeCmd.getCurrentPassword() == null || pswdChangeCmd.getCurrentPassword().length() == 0 ) {
			err.rejectValue("currentPassword","required");
			required = false;
		}
		
		if (pswdChangeCmd.getPassword() == null || pswdChangeCmd.getPassword().length() == 0 ) {
			err.rejectValue("password","required");
			required = false;
		}
		if (pswdChangeCmd.getConfPassword() == null || pswdChangeCmd.getConfPassword().length() == 0 ) {
			err.rejectValue("confPassword","required");
			required = false;
		}
		if (!required) {
			return;
		}
		if ( !pswdChangeCmd.getConfPassword().equals(pswdChangeCmd.getPassword() )) {
			err.rejectValue("confPassword","notequal");
			required = false;
			return;
		}
		// check the password against the current password
		Login l = loginManager.getLoginByManagedSys(pswdChangeCmd.getDomainId(), 
				pswdChangeCmd.getPrincipal(), pswdChangeCmd.getSysId()).getPrincipal();
		String decPswd = (String)loginManager.decryptPassword(l.getPassword()).getResponseValue();
		if (decPswd != null) {

			if (!decPswd.equals(pswdChangeCmd.getCurrentPassword())) {
				err.rejectValue("currentPassword","notequal");
				return;				
			}
			
		}
		
		// validate the password against the policy
		Password pswd = new Password();
		pswd.setDomainId(configuration.getDefaultSecurityDomain());
		pswd.setManagedSysId(configuration.getDefaultManagedSysId());
		pswd.setPrincipal(pswdChangeCmd.getPrincipal());
		pswd.setPassword(pswdChangeCmd.getPassword());
		
		
		try {
			Response resp = passwordService.isPasswordValid(pswd);
			if (resp.getStatus() == ResponseStatus.FAILURE) {
				err.rejectValue("password",resp.getErrorCode().toString());
				required = false;				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class cls) {
		return SingleSysPasswordChangeCommand.class.equals(cls);
	}

	public PasswordWebService getPasswordService() {
		return passwordService;
	}

	public void setPasswordService(PasswordWebService passwordService) {
		this.passwordService = passwordService;
	}

	public PasswordConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}

	public LoginDataWebService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


		
		
}
	
