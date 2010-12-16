package org.openiam.webadmin.login;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.service.AuthenticationConstants;
import org.openiam.idm.srvc.auth.service.AuthenticationService;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.openiam.idm.srvc.auth.dto.Subject;


public class LoginValidator implements Validator {

	protected AuthenticationService authenticate;
	protected String securityDomain;
	
	private static final Log log = LogFactory.getLog(LoginValidator.class);

	public boolean supports(Class cls) {
		 return LoginCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		boolean required = true;
		System.out.println("LoginValidator:validate");
		
		log.debug("LoginValidator:validate");
		
		LoginCommand loginCmd =  (LoginCommand) cmd;
		
		if (loginCmd.getPrincipal() == null || loginCmd.getPrincipal().length() == 0 ) {
			err.rejectValue("principal","required");
			required = false;
		}
		if (loginCmd.getPassword() == null || loginCmd.getPassword().length() == 0 ) {
			err.rejectValue("password","required");
			required = false;
		}
		
		if (!required) {
			return;
		}
		
		// authenticate the user
		try {
			
			Subject sub = authenticate.passwordAuth(securityDomain, loginCmd.getPrincipal(), loginCmd.getPassword());
			
			log.debug("validation: subject=" + sub);
			
			loginCmd.setSubject(sub);
			
		}catch(AuthenticationException ae) {
			int errCode = ae.getErrorCode();
			switch (errCode) {
			case AuthenticationConstants.RESULT_INVALID_DOMAIN:
				err.rejectValue("principal","servicenotvalid");
				break;
			case AuthenticationConstants.RESULT_INVALID_LOGIN:
				err.rejectValue("principal","invalid");
				break;
			case AuthenticationConstants.RESULT_INVALID_PASSWORD:
				err.rejectValue("password","invalid");
				break;
			case AuthenticationConstants.RESULT_INVALID_USER_STATUS:
				err.rejectValue("principal","status");
				break;
			case AuthenticationConstants.RESULT_LOGIN_DISABLED:
				err.rejectValue("principal","diabled");
				break;
			case AuthenticationConstants.RESULT_LOGIN_LOCKED:
				err.rejectValue("principal","locked");
				break;
			case AuthenticationConstants.RESULT_PASSWORD_EXPIRED:
				err.rejectValue("principal","expired");
				break;
			case AuthenticationConstants.RESULT_SERVICE_NOT_FOUND:
				err.rejectValue("principal","servicenotfound");
				break;
			default:
				err.rejectValue("principal","invalid");
			}
		}
		
		
	}

	public AuthenticationService getAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(AuthenticationService authenticate) {
		this.authenticate = authenticate;
	}

	public String getSecurityDomain() {
		return securityDomain;
	}

	public void setSecurityDomain(String securityDomain) {
		this.securityDomain = securityDomain;
	}	
	
}

