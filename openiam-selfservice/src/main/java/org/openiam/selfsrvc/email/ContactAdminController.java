package org.openiam.selfsrvc.email;


import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.email.MailService;

public class ContactAdminController extends SimpleFormController {

	MailService mailService;
	protected String emailAddress;

	private static final Log log = LogFactory.getLog(ContactAdminController.class);

	
	public ContactAdminController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		ContactAdminCommand contactAdminCmd = new ContactAdminCommand();
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		if (login != null && login.length() > 0) {
			contactAdminCmd.setFrom(login);
		}else {
			contactAdminCmd.setFrom("Anonymous");
		}
		
		
		
		return contactAdminCmd;
		
		
	}
	
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		// TODO Auto-generated method stub
		
		
		ContactAdminCommand contactAdminCmd =(ContactAdminCommand)command;
	

		mailService.send(contactAdminCmd.getFrom(), emailAddress, contactAdminCmd.getSubject(), 
				contactAdminCmd.getMessage());
		

		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("contactAdminCmd",contactAdminCmd);
		
		
		return mav;
	}

	


	public MailService getMailService() {
		return mailService;
	}


	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	

}
