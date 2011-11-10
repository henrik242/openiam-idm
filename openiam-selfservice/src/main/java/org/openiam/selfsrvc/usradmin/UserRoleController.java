package org.openiam.selfsrvc.usradmin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.propertyeditors.CustomDateEditor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.AttributeOperationEnum;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;


public class UserRoleController extends SimpleFormController {


	protected RoleDataWebService roleDataService;
	protected NavigatorDataWebService navigationDataService;	
	protected String redirectView;
	protected ProvisionService provRequestService; 
	protected UserDataWebService userMgr;
	
	private static final Log log = LogFactory.getLog(UserRoleController.class);

	
	public UserRoleController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
		
	}

	
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		log.info("refernceData called.");
		
		Map<Object, Object> dataMap = new HashMap<Object, Object>();
	
		return dataMap;
		

	}
	


	@Override
	protected Object formBackingObject(HttpServletRequest request)		throws Exception {
		
		
		
		UserRoleCommand roleCmd = new UserRoleCommand();
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
        User usr = (User)session.getAttribute("userObj");
		
		String menuGrp = request.getParameter("menugrp");
		String personId = request.getParameter("personId");
		
		// get the level 3 menu
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		request.setAttribute("personId", personId);				

		roleCmd.setPerId(personId);
		
		// get the list of roles
		List<Role> fullRoleList = new ArrayList<Role>();
		List<Role> roleList = roleDataService.getAllRoles().getRoleList();


		// get the roles that the user has
		RoleListResponse resp =  roleDataService.getUserRolesAsFlatList(personId);
		
		if (resp != null && resp.getStatus() == ResponseStatus.SUCCESS) {
			// assign the selection of the 
			List<Role> userRoleList = resp.getRoleList();
			// for each role in the main list, check the userRole list to see if its there
			for (Role rl : roleList) {
				boolean found = false;
				for (Role r : userRoleList ) {
					if (rl.getId().getRoleId().equalsIgnoreCase(r.getId().getRoleId())) {
						rl.setSelected(true);
						fullRoleList.add(rl);
						found = true;
					}
				}
				if (!found) {
					fullRoleList.add(rl);
				}
			}
		}

        if (usr.getDelAdmin() != null &&  usr.getDelAdmin().intValue() == 0) {
            Map<String, UserAttribute> attrMap = usr.getUserAttributes();
            List<String> roleIdList = null;

            roleIdList = DelegationFilterHelper.getRoleFilterFromString(attrMap);

            if (roleIdList == null) {
                roleCmd.setRoleList(roleList);
                return roleCmd;

            }

            List<Role> newRoleList = new ArrayList<Role>();
            for (Role r : roleList) {


                if (roleIdList.contains(r.getId().getServiceId() + "*" + r.getId().getRoleId())) {
                       newRoleList.add(r);
                }
            }
            roleCmd.setRoleList(newRoleList);
            return roleCmd;




         }
		
		
		roleCmd.setRoleList(roleList);

		return roleCmd;
		
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		User usr = null;
		List<Role> currentUserRoleList = null;
		
		log.info("UserRoleController: onSubmit");
		
		UserRoleCommand roleCmd =(UserRoleCommand)command;
		
		HttpSession session = request.getSession();
		
		// get the current user object
		String personId = roleCmd.getPerId();
		UserResponse usrResp =  userMgr.getUserWithDependent(personId, true);
		if (usrResp.getStatus() == ResponseStatus.SUCCESS ) {
			usr = usrResp.getUser();
		}
		ProvisionUser pUser = new ProvisionUser(usr);
		

		
		RoleListResponse resp =  roleDataService.getUserRolesAsFlatList(personId);
		currentUserRoleList = resp.getRoleList();
		log.info("Current userRoleList = " + currentUserRoleList);
		
		// - get the list of roles from the form
		List<Role> newRoleList = roleCmd.getRoleList();
		// show the list of roles that were selected
		List<Role> provRoleList = new ArrayList<Role>();
		if (newRoleList != null) {
			for (Role r : newRoleList) {
				if (r.getSelected()) {
					log.info("add following role to provRoleList="  +r.getId());
					provRoleList.add(r);
	
				}
			}
		}
		// roles to delete
		log.info("checking current role list");
		if (currentUserRoleList != null) {
			
			log.info("currentUserRoleList is not null");
			
			for ( Role curRl : currentUserRoleList) {
				if (!isCurrentRoleInList(curRl, provRoleList)) {
					curRl.setOperation(AttributeOperationEnum.DELETE);
					log.info("Removing role=" + curRl.getId());
					provRoleList.add(curRl);
				}
			}
		}

        String login = (String)session.getAttribute("login");
        String domain = (String)session.getAttribute("domainId");
        pUser.setRequestClientIP(request.getRemoteHost());
        pUser.setRequestorLogin(login);
        pUser.setRequestorDomain(domain);


		pUser.setMemberOfRoles(provRoleList);
		log.info("modifyUser on ProvisioningService to be called.");
		provRequestService.modifyUser(pUser);
		
		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));

	}


	private boolean isCurrentRoleInList(Role curRl, List<Role> newRoleList) {
		if (newRoleList != null) {
			for ( Role r : newRoleList) {
				if (r.getId().equals(curRl.getId())) {
					log.info("-Role found in currentList=" + curRl.getId());
					return true;
				}
			}
		}
		log.info("-Role not found in currentList=" + curRl.getId());
		return false;
	}

	


	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
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


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}



	
}
