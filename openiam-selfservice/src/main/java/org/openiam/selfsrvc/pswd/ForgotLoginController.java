package org.openiam.selfsrvc.pswd;


import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserListResponse;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.service.ProvisionService;

/**
 * Controller for the new hire form.
 * @author suneet
 *
 */
public class ForgotLoginController extends SimpleFormController {


	protected UserDataWebService userMgr;
	protected LoginDataWebService loginManager;
	protected PasswordConfiguration configuration;
	protected MailService mailService;
	protected String fromEmailAddress;
	protected String emailSubject;

		
	
	private static final Log log = LogFactory.getLog(ForgotLoginController.class);

	
	public ForgotLoginController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		log.info("onSubmit called.");

		
		ForgotLoginCommand cmd =(ForgotLoginCommand)command;
		UserSearch search = new UserSearch();
	    search.setEmailAddress(cmd.getEmailAddres());
	       
	    UserListResponse resp =  userMgr.search(search);
	    if (resp.getStatus().equals(ResponseStatus.FAILURE)) {
	    	return ( new ModelAndView("pub/loginNotFound"));
	    }
		List<User> userList = resp.getUserList(); 
		if (userList == null || userList.size() == 0) {
			// email does not exist
			return ( new ModelAndView("pub/loginNotFound"));
		}
		// get the upn
		User usr = userList.get(0);
		List<Login> principalList = loginManager.getLoginByUser(usr.getUserId()).getPrincipalList();
		// get the primary id.
		if (principalList == null || principalList.size() == 0) {
			return ( new ModelAndView("pub/loginNotFound"));
		}
		for ( Login lg : principalList) {
			if (lg.getId().getManagedSysId().equalsIgnoreCase( configuration.getDefaultManagedSysId() )) {
				// found the primary id
				emailLoginId(lg.getId().getLogin(), cmd.getFirstName(), cmd.getLastName(), 
						cmd.getPhone(), cmd.getEmailAddres());
			}
		}
		
				
		ModelAndView mav = new ModelAndView(getSuccessView());		
		return mav;
	}


	private void emailLoginId(String login, String firstName, String lastName, String phone, String email) {
		
		String msg = "Name=" + firstName + " " + lastName + "\n" +
				" Phone=" + phone + "\n" + 
				" Login Id=" + login + "\n";
		
		mailService.send(fromEmailAddress, email, emailSubject,	msg);
	}

	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}





	public PasswordConfiguration getConfiguration() {
		return configuration;
	}


	public void setConfiguration(PasswordConfiguration configuration) {
		this.configuration = configuration;
	}


	public MailService getMailService() {
		return mailService;
	}


	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}


	public String getFromEmailAddress() {
		return fromEmailAddress;
	}


	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}


	public String getEmailSubject() {
		return emailSubject;
	}


	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}




}
