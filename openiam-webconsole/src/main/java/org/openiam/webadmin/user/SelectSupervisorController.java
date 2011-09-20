package org.openiam.webadmin.user;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.dto.UserSearch;
import org.openiam.idm.srvc.user.dto.User;



public class SelectSupervisorController extends AbstractController {


	private static final Log log = LogFactory.getLog(SelectSupervisorController.class);
	protected String successView;
	protected UserDataWebService userMgr;

	
	
	public SelectSupervisorController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("form post called...");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		UserSearch search = new UserSearch();
		if (firstName != null && firstName.length() > 0) {
			search.setFirstName(firstName + "%");
		}
		if (lastName != null && lastName.length() > 0) {
			search.setLastName(lastName + "%");
		}
		List<User> userList = userMgr.search(search).getUserList();
        if (userList != null) {
            request.getSession().setAttribute("supervisorList", userList );
        }
		
		
		ModelAndView mav = new ModelAndView(new RedirectView(successView, true));

		
		return mav;
	}





	public String getSuccessView() {
		return successView;
	}


	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public UserDataWebService getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}



	

}
