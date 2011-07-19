package org.openiam.selfsrvc.delegate;


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
import org.openiam.idm.srvc.msg.service.MailService;

public class OutOfOfficeDelegateController extends SimpleFormController {

	MailService mailService;
	protected String emailAddress;

	private static final Log log = LogFactory.getLog(OutOfOfficeDelegateController.class);

	
	public OutOfOfficeDelegateController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}


	
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		// TODO Auto-generated method stub
		
		
		OutOfOfficeDelegateCommand cmd =(OutOfOfficeDelegateCommand)command;
	



		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("outOfOfficeCmd",cmd);
		
		
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
