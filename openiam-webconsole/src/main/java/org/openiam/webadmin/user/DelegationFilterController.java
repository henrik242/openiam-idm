package org.openiam.webadmin.user;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.ws.LoginDataWebService;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupListResponse;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.openiam.idm.srvc.user.ws.UserResponse;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.service.ProvisionService;


public class DelegationFilterController extends SimpleFormController {


	protected UserDataWebService userMgr;
	protected LoginDataWebService loginManager;
	
	protected NavigatorDataWebService navigationDataService;
	protected String redirectView;
	
	
	private static final Log log = LogFactory.getLog(DelegationFilterController.class);

	
	public DelegationFilterController() {
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
		
		
		
		DelegationFilterCommand idCmd = new DelegationFilterCommand();
		
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		String menuGrp = request.getParameter("menugrp");
		String personId = request.getParameter("personId");
		
		idCmd.setPerId(personId);
		
		// get the level 3 menu
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
		request.setAttribute("personId", personId);		
		
		List<Login> principalList = loginManager.getLoginByUser(personId).getPrincipalList();
		idCmd.setPrincipalList(principalList);
		
		return idCmd;
		
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		User usr = null;
		
		System.out.println("UserIdentityController: onSubmit");
		
		UserIdentityCommand identityCmd =(UserIdentityCommand)command;
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		// get the current user object
		String personId = identityCmd.getPerId();
		log.info("Person id=" + personId);
		
		UserResponse usrResp =  userMgr.getUserWithDependent(personId, true);
		if (usrResp.getStatus() == ResponseStatus.SUCCESS ) {
			usr = usrResp.getUser();
		}
		ProvisionUser pUser = new ProvisionUser(usr);
		
		List<Login> loginList = identityCmd.getPrincipalList();
		// update this with the delete flag if one exists
		if (loginList != null) {
			for (Login lg: loginList) {
				if (lg.isSelected()) {
					lg.setOperation(AttributeOperationEnum.DELETE);
				}
			}
		}
		pUser.setPrincipalList(loginList);
		
		
		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));

	}




	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}


	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}


	public LoginDataWebService getLoginManager() {
		return loginManager;
	}


	public void setLoginManager(LoginDataWebService loginManager) {
		this.loginManager = loginManager;
	}


	public UserDataWebService getUserMgr() {
		return userMgr;
	}


	public void setUserMgr(UserDataWebService userMgr) {
		this.userMgr = userMgr;
	}


	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}




	
}
