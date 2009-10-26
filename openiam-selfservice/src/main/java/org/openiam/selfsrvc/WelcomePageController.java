package org.openiam.selfsrvc;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.grp.ws.GroupListResponse;
import org.openiam.idm.srvc.loc.service.LocationDataService;

import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;



public class WelcomePageController extends AbstractController {


	private static final Log log = LogFactory.getLog(WelcomePageController.class);
	private String successView;
	private UserDataWebService userMgr;
	private RoleDataWebService roleDataService;
	private GroupDataWebService groupManager;

	
	public WelcomePageController() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
	
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		
		UserResponse response = userMgr.getUserWithDependent(userId, true);
		if (response.getStatus() == ResponseStatus.FAILURE) {
			ModelAndView mav = new ModelAndView("/error");		
			return mav;			
		}
		User usr = response.getUser();
		GroupListResponse groupResponse = groupManager.getUserInGroups(userId);
		RoleListResponse roleResponse =  roleDataService.getUserRoles(userId);
			
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("user", usr);
		mav.addObject("groupList",groupResponse.getGroupList());
		mav.addObject("roleList", roleResponse.getRoleList());
		
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

	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}

	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}

	public GroupDataWebService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}

	

}
