package org.openiam.webadmin.user;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleListResponse;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.grp.ws.GroupListResponse;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;




public class UserGroupController extends SimpleFormController {


	protected UserDataWebService userMgr;
	protected GroupDataWebService groupManager;
	protected NavigatorDataWebService navigationDataService;	
	protected String redirectView;
	protected ProvisionService provRequestService; 
	
	private static final Log log = LogFactory.getLog(UserGroupController.class);

	
	public UserGroupController() {
		super();
	}
	


	@Override
	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		String menuGrp = request.getParameter("menugrp");
		String personId = request.getParameter("personId");
		String userId = (String)request.getSession().getAttribute("userId");		
		
		UserGroupCommand userGroupCmd = new UserGroupCommand();
		

		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		request.setAttribute("personId", personId);		
		
		userGroupCmd.setPerId(personId);

		List<Group> fullGroupList = new ArrayList<Group>();
		List<Group> groupList =  groupManager.getAllGroups().getGroupList(); 
		// get the groups that the user has
		GroupListResponse resp = groupManager.getUserInGroupsAsFlatList(personId);  
		
		if (resp != null && resp.getStatus() == ResponseStatus.SUCCESS) {
			// assign the selection of the 
			List<Group> userGroupList = resp.getGroupList();
			// for each role in the main list, check the userRole list to see if its there
			for (Group g : groupList) {
				boolean found = false;
				for (Group grp : userGroupList ) {
					if (grp.getGrpId().equalsIgnoreCase(g.getGrpId())) {
						g.setSelected(true);
						fullGroupList.add(g);
						found = true;
					}
				}
				if (!found) {
					fullGroupList.add(g);
				}
			}
		}
		
		
		userGroupCmd.setGroupList(groupList);

		return userGroupCmd;
		
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		User usr = null;
		List<Group> currentUserGroupList = null;
		
		log.info("UserGroupController: onSubmit");
		
		UserGroupCommand userGroupCmd = (UserGroupCommand)command;
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get the current user object
		String personId = userGroupCmd.getPerId();
		log.info("Person id=" + personId);
		
		UserResponse usrResp =  userMgr.getUserWithDependent(personId, true);
		if (usrResp.getStatus() == ResponseStatus.SUCCESS ) {
			usr = usrResp.getUser();
		}
		ProvisionUser pUser = new ProvisionUser(usr);
		

		
		GroupListResponse resp =  groupManager.getUserInGroupsAsFlatList(personId);
		currentUserGroupList = resp.getGroupList();
		log.info("Current userGroupList = " + currentUserGroupList);

		
		// - get the list of roles from the form
		List<Group> newGroupList = userGroupCmd.getGroupList();
		// show the list of roles that were selected
		List<Group> provGroupList = new ArrayList<Group>();
		if (newGroupList != null) {
			for (Group g : newGroupList) {
				if (g.getSelected()) {
					log.info("add following group to provGroupList="  + g.getGrpId());
					provGroupList.add(g);
	
				}
			}
		}
		// roles to delete
		log.info("checking current group list");
		if (currentUserGroupList != null) {
			
			log.info("currentUserRoleList is not null");
			
			for ( Group curGrp : currentUserGroupList) {
				if (!isCurrentGroupInList(curGrp, provGroupList)) {
					curGrp.setOperation(AttributeOperationEnum.DELETE);
					log.info("Removing role=" + curGrp.getGrpId());
					provGroupList.add(curGrp);
				}
			}
		}
		pUser.setMemberOfGroups(provGroupList);
		log.info("modifyUser on ProvisioningService to be called.");
		provRequestService.modifyUser(pUser);
		
		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));

	}

	private boolean isCurrentGroupInList(Group curGrp, List<Group> newGroupList) {
		if (newGroupList != null) {
			for ( Group g : newGroupList) {
				if (g.getGrpId().equals(curGrp.getGrpId())) {
					log.info("-Role found in currentList=" + curGrp.getGrpId());
					return true;
				}
			}
		}
		log.info("-Group not found in currentList=" + curGrp.getGrpId());
		return false;
	}
	

	public UserDataWebService getUserMgr() {
		return userMgr;
	}



	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}



	public GroupDataWebService getGroupManager() {
		return groupManager;
	}



	public void setGroupManager(GroupDataWebService groupManager) {
		this.groupManager = groupManager;
	}



	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}



	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}



	public String getRedirectView() {
		return redirectView;
	}



	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}



	public ProvisionService getProvRequestService() {
		return provRequestService;
	}



	public void setProvRequestService(ProvisionService provRequestService) {
		this.provRequestService = provRequestService;
	}



	




	
}
