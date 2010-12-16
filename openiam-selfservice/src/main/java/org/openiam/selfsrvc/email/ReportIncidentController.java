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
import org.openiam.idm.srvc.msg.service.MailService;


public class ReportIncidentController extends SimpleFormController {

	MailService mailService;
	protected String emailAddress;

	private static final Log log = LogFactory.getLog(ReportIncidentController.class);

	
	public ReportIncidentController() {
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
		
		ReportIncidentCommand rptIncidentCmd = new ReportIncidentCommand();
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		if (login != null && login.length() > 0) {
			rptIncidentCmd.setFrom(login);
		}else {
			rptIncidentCmd.setFrom("Anonymous");
		}
		
		
		
		return rptIncidentCmd;
		
		
	}
	
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		// TODO Auto-generated method stub
		
		
		ReportIncidentCommand rptIncidentCmd =(ReportIncidentCommand)command;
	
		
		// send an email to the manager
  
        System.out.println("Sending email from new hire form....");

		mailService.send(rptIncidentCmd.getFrom(), emailAddress, rptIncidentCmd.getSubject(), 
				rptIncidentCmd.getMessage());
		

		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("rptIncidentCmd",rptIncidentCmd);
		
		
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
