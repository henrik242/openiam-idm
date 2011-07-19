package org.openiam.webadmin.user;


import java.util.Date;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.ws.UserDataWebService;

public class UserChangeStatusController extends SimpleFormController {


	protected UserDataWebService userMgr;	
	protected String redirectView;
	
	private static final Log log = LogFactory.getLog(UserChangeStatusController.class);

	
	public UserChangeStatusController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
		
	}

	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		
		
		UserChangeStatusCommand statusCommand = new UserChangeStatusCommand();
		
		String personId = request.getParameter("personId");
		String status = request.getParameter("status");
		
		User usr = userMgr.getUserWithDependent(personId, false).getUser();
		
		statusCommand.setFirstName(usr.getFirstName());
		statusCommand.setLastName(usr.getLastName());
		statusCommand.setUserStatus(status);
		statusCommand.setUserId(personId);
		
	

		return statusCommand;
		
	}	
	

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		
		System.out.println("ProfileController: onSubmit");
		
		UserChangeStatusCommand statusCommand =(UserChangeStatusCommand)command;

		
		User usr = userMgr.getUserWithDependent(statusCommand.getUserId(), true).getUser();
		log.info("User found - " + usr.getUserId());
		usr.setStatus( UserStatusEnum.valueOf(statusCommand.getUserStatus()));
		userMgr.updateUserWithDependent(usr, true);
		
		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));
		

	}


	



	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}



	
}
