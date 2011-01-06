package org.openiam.selfsrvc.profile;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Controller to retrieve user information. The information is presented through a readonly view.
 * @author suneet
 *
 */
public class ViewUserController extends AbstractController {

	private static final Log log = LogFactory.getLog(ViewUserController.class);
	private String view;
	
	UserDataWebService userManager = null;
	OrganizationDataService orgManager = null;
	
	public ViewUserController() {
		super();
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
		Organization org = new Organization();
		User userData = null;
		User manager = new User();
		
		log.debug("ViewUserController: handleRequestInternal called.");
		
		String personId = req.getParameter("personId");
		
		// get objects related to the user to show move detail on the view.
	    userData = userManager.getUserWithDependent(personId,true).getUser();
	    if (userData.getCompanyId() != null && !userData.getCompanyId().isEmpty()) {
	    	org = orgManager.getOrganization(userData.getCompanyId());
	    }
	    
	    // get the supervisor
	    List<Supervisor> supervisors = userManager.getSupervisors(personId).getSupervisorList();
	    // get the direct reports
	    List<Supervisor> directReports = userManager.getEmployees(personId).getSupervisorList();
	    

	    
	    
		ModelAndView mav = new ModelAndView(view);
		mav.addObject("userData", userData);
		mav.addObject("org", org);
		mav.addObject("directReports", directReports);
		mav.addObject("supervisor", supervisors);
		
		return mav;
		
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}



	public OrganizationDataService getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}

	public UserDataWebService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataWebService userManager) {
		this.userManager = userManager;
	}

	
}
