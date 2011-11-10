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

import org.openiam.idm.srvc.grp.ws.GroupDataWebService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.webadmin.admin.AppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;
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


public class DelegationFilterController extends CancellableFormController {


	protected UserDataWebService userMgr;
	protected LoginDataWebService loginManager;
	
	protected NavigatorDataWebService navigationDataService;
	protected String redirectView;

    protected OrganizationDataService orgManager;
	protected GroupDataWebService groupManager;
	protected RoleDataWebService roleDataService;
    protected ResourceDataService resourceDataService;


	private static final Log log = LogFactory.getLog(DelegationFilterController.class);


    protected AppConfiguration configuration;
    protected ProvisionService provRequestService;

	public DelegationFilterController() {
		super();
	}
	

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
		
	}

    @Override
      protected ModelAndView onCancel(Object command) throws Exception {
          return new ModelAndView(new RedirectView(getCancelView(),true));
     }


	
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		log.debug("referenceData called.");
		
		Map<Object, Object> dataMap = new HashMap<Object, Object>();

        dataMap.put("orgList", orgManager.getOrganizationList(null,"ACTIVE")); // orgManager.getTopLevelOrganizations() );
		// get the divisions
		dataMap.put("divList", orgManager.allDivisions(null));
		// load the department list
		dataMap.put("deptList",orgManager.allDepartments(null));

        dataMap.put("groupList", groupManager.getAllGroups().getGroupList());
        dataMap.put("roleList", roleDataService.getAllRoles().getRoleList()) ;

        dataMap.put("resourceList", resourceDataService.getResourcesByType("NO-PROVISION-APP"));


			
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

        UserResponse resp = userMgr.getUserWithDependent(personId, true);
		if (resp.getStatus() == ResponseStatus.FAILURE) {
			ModelAndView mv = new ModelAndView(configuration.getErrorUrl());
			mv.addObject("msg", "Invalid user id. ");
			return mv;
		}
		User usr = resp.getUser();
        Map<String, UserAttribute> attrMap = usr.getUserAttributes();

        if (attrMap.get("DLG_FLT_APP") != null) {
            idCmd.setAppFilterFromString( attrMap.get("DLG_FLT_APP").getValue());
        }
        if (attrMap.get("DLG_FLT_DEPT") != null) {
            idCmd.setDeptFilterFromString(attrMap.get("DLG_FLT_DEPT").getValue());
        }
        if (attrMap.get("DLG_FLT_DIV") != null) {
            idCmd.setDivisionFilterFromString(attrMap.get("DLG_FLT_DIV").getValue());
        }
        if (attrMap.get("DLG_FLT_GRP") != null)  {
            idCmd.setGroupFilterFromString(attrMap.get("DLG_FLT_GRP").getValue());
        }
        if (attrMap.get("DLG_FLT_ORG") != null) {
            idCmd.setOrgFilterFromString(attrMap.get("DLG_FLT_ORG").getValue());
        }
        if (attrMap.get("DLG_FLT_ROLE") != null) {
            idCmd.setRoleFilterFromString(attrMap.get("DLG_FLT_ROLE").getValue());
        }


		return idCmd;
		
	}

		
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
	
		User usr = null;

		System.out.println("UserAttributeController: onSubmit");

		String btn = request.getParameter("saveBtn");
		if (btn.equals("Cancel")) {
			return new ModelAndView(configuration.getHomePage());
		}

		DelegationFilterCommand cmd =(DelegationFilterCommand)command;
		String personId = cmd.getPerId();
		if (personId == null || personId.length() == 0 ) {
			ModelAndView mv = new ModelAndView(configuration.getErrorUrl());
			mv.addObject("msg", "User id is null. ");
			return mv;

		}
		UserResponse resp = userMgr.getUserWithDependent(personId, true);
		if (resp.getStatus() == ResponseStatus.FAILURE) {
			ModelAndView mv = new ModelAndView(configuration.getErrorUrl());
			mv.addObject("msg", "Invalid user id. ");
			return mv;
		}
		usr = resp.getUser();
        Map<String, UserAttribute> attrMap = usr.getUserAttributes();



		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

        // get the attributes from the filter
       String appFilter =  cmd.getAppFilterAsString();
       String deptFilter =  cmd.getDeptFilterAsString();
       String divFilter =   cmd.getDivFilterAsString();
       String groupFilter = cmd.getgroupFilterAsString();
       String roleFilter =  cmd.getRoleFilterAsString();
       String orgFilter =  cmd.getOrgFilterAsString();

        updateUserAttr(attrMap,"DLG_FLT_APP", appFilter, personId);
        updateUserAttr(attrMap,"DLG_FLT_DEPT", deptFilter, personId);
        updateUserAttr(attrMap,"DLG_FLT_DIV", divFilter, personId);
        updateUserAttr(attrMap,"DLG_FLT_GRP", groupFilter, personId);
        updateUserAttr(attrMap,"DLG_FLT_ROLE", roleFilter, personId);
        updateUserAttr(attrMap,"DLG_FLT_ORG", orgFilter, personId);

		ProvisionUser pUser = new ProvisionUser(usr);

        String login = (String)session.getAttribute("login");
        String domain = (String)session.getAttribute("domainId");
        pUser.setRequestClientIP(request.getRemoteHost());
        pUser.setRequestorLogin(login);
        pUser.setRequestorDomain(domain);
		// check the user attributes

	 //	Map<String, UserAttribute> attrMap = pUser.getUserAttributes();

		this.provRequestService.modifyUser(pUser);

		return new ModelAndView(new RedirectView(redirectView+"&mode=1", true));
	}


    private void updateUserAttr(Map<String, UserAttribute> attrMap, String attrName, String value,  String personId) {
        UserAttribute attr =  attrMap.get(attrName);
        if (attr == null) {
            // new attr
            attr = new UserAttribute(attrName, value);
            attr.setOperation(AttributeOperationEnum.ADD);
            attr.setId(null);
            attr.setUserId(personId);
            attrMap.put(attrName,attr );
        }else {
            // update existing attr
            attr.setOperation(AttributeOperationEnum.REPLACE);
            attr.setValue(value);
            attrMap.put(attrName, attr);

        }


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


    public OrganizationDataService getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(OrganizationDataService orgManager) {
        this.orgManager = orgManager;
    }

    public GroupDataWebService getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(GroupDataWebService groupManager) {
        this.groupManager = groupManager;
    }

    public RoleDataWebService getRoleDataService() {
        return roleDataService;
    }

    public void setRoleDataService(RoleDataWebService roleDataService) {
        this.roleDataService = roleDataService;
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public AppConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    public ProvisionService getProvRequestService() {
        return provRequestService;
    }

    public void setProvRequestService(ProvisionService provRequestService) {
        this.provRequestService = provRequestService;
    }
}
