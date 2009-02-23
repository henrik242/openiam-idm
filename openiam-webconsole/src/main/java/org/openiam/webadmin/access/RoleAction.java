/**
 * ------------------------------------------------------------------------------
 * Title: RoleAction
 * Author: APS 04-09-2004
 * Overview:Handles all functions like adding , updating, deleting and viewing
 * Role for available Services.
 * ------------------------------------------------------------------------------
 * Copyright (c) 2000-2004 Diamelle Inc. All Rights Reserved.
 *
 * This SOURCE CODE FILE, which has been provided by Diamelle Technologies as part
 * of a Diamelle Software product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of Diamelle Technologies.
 *
 * This code or parts or derivatives of it cannot be used for any commercial
 * products without written permission from Diamelle Technologies.
 *
 * USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS
 * OF THE LICENSE STATEMENT FURNISHED WITH THE PRODUCT.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD Diamelle Technologies, ITS
 * RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY
 * CLAIMS OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR
 * DISTRIBUTION OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES
 * ARISING OUT OF OR RESULTING FROM THE USE, MODIFICATION, OR
 * DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM, BASED ON, AND/OR
 * DERIVED FROM THIS SOURCE CODE FILE.
 * ------------------------------------------------------------------------------
 * CHANGE CONTROL:
 * aaa - APS
 * ------------------------------------------------------------------------------
 */

package org.openiam.webadmin.access;

import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.Search;
import org.openiam.util.db.SearchImpl;
import org.openiam.webadmin.busdel.security.ACLAccess;
import java.io.*;
import java.util.*;
import java.rmi.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.openiam.webadmin.busdel.base.*;
import diamelle.security.auth.RoleValue;

import org.openiam.webadmin.busdel.security.SecurityAccess;
import org.openiam.webadmin.busdel.security.RoleDataServiceAccess;
import org.openiam.webadmin.busdel.identity.UserDataServiceAccess;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.idm.srvc.secdomain.service.*;
import org.openiam.idm.srvc.secdomain.dto.*;
import org.openiam.idm.srvc.role.dto.Role;
//import org.openiam.idm.srvc.role.dto.UserRole;

import diamelle.common.service.ServiceMgr;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.validator.DynaValidatorForm;

import diamelle.ebc.navigator.*;
import javax.naming.*;
import diamelle.security.resource.*;
import diamelle.util.LoggerUtil;

import org.springframework.web.struts.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;

/**
 * This class allows us to 1. View List of Roles for a particular Service 2.
 * View details of a Role 3. Add Role 4. Update a Role 5. Delete a Role
 */

public class RoleAction extends NavigationDispatchAction {

	//private ServiceAccess serviceAccess = new ServiceAccess();

	private SecurityAccess securityAccess = new SecurityAccess();

	private NavigationAccess navAccess = new NavigationAccess();

	private ACLAccess acl = new ACLAccess();
	private RoleDataServiceAccess roleDS;
	private UserDataServiceAccess userDS;
	
//   final static Logger logger = Logger.getLogger(className);

	/**
	 * Display list of Roles
	 */
	public ActionForward viewRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionErrors errors = new ActionErrors();

		try {
			//intializing the rolelist.jsp
			init(mapping, form, request, response);

			//setting mode to view
			DynaValidatorForm roleForm = (DynaValidatorForm) form;
			roleForm.set("mode", "view");
		} catch (Exception e) {
			e.printStackTrace();
			//LoggerUtil.error(logger,e);
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.databaseselect"));
		}
		return mapping.findForward("rolelist");
	}

	/**
	 * Extracts nav, categoryId, menuId and nextAction parameters from a request
	 * object and sets nextAction, categoryId,categoryValue, categories, navBar,
	 * menuId, menus, menuBar in session Also extracts Available Services.
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

	//	LoggerUtil.info(logger, "entering init");
		ActionErrors errors = new ActionErrors();
		
		ServletContext servletContext =  getServlet().getServletConfig().getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		org.openiam.idm.srvc.service.service.ServiceMgr servMgr = 
			(org.openiam.idm.srvc.service.service.ServiceMgr)ctx.getBean("serviceManager");

		SecurityDomainDataService secDomainDS = 
			(SecurityDomainDataService)ctx.getBean("secDomainService");

		
		// Extracts nav, categoryId, menuId and nextAction parameters from a
		// request object and sets
		// nextAction, categoryId,categoryValue, categories, navBar, menuId,
		// menus, menuBar in session
		// categoryId left unchanged if null or showlist=1
		// nav=resetMenu, resetCat removes those attributes only, reset removes
		// both
		super.setNavigation(mapping, form, request, response);

		Locale locale = getLocale(request);
		//MessageResources messages = getResources();
		HttpSession session = request.getSession();

		//String categoryId = (String)session.getAttribute("categoryId");

		try {
			//String langCd = locale.getLanguage();
			
			//Map serviceMap = servMgr.getServicesMap();
			//request.setAttribute("services", serviceMap);
			
			SecurityDomain domainAry[] = secDomainDS.getAllSecurityDomains();
			request.setAttribute("services", domainAry);

			
			
			// this param set in roleList.jsp
			String serviceId = request.getParameter("serviceId");

			// if not passed as a parameter, check if it already set in session
			if (serviceId == null || serviceId.length() == 0)
				serviceId = (String) session.getAttribute("serviceId");

			if (serviceId != null) {
				session.setAttribute("serviceId", serviceId);
				List roleList = securityAccess.getAllRoles(serviceId);
				request.setAttribute("roleList", roleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		// if errors, return to welcome page via index.jsp
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			//request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("rolelist");
		}
		// category.jsp will need this to provide link to action class
		// request.setAttribute("nextAction", "xxx.do?method=yyy");
		return mapping.findForward("rolelist");
	}

	/**
	 * Sets mode to Add, for adding new Role
	 */

	public ActionForward addRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		//set mode. if mode is add/edit, roleList jsp will include role.jsp
		DynaValidatorForm roleForm = (DynaValidatorForm) form;
		roleForm.set("mode", "add");

		//intializing the rolelist.jsp
		init(mapping, form, request, response);
		return mapping.findForward("rolelist");
	}

	/**
	 * Sets Role Details, and mode to Edit, for updating the Role Details
	 */

	public ActionForward editRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		ActionErrors errors = new ActionErrors();
		DynaValidatorForm roleForm = (DynaValidatorForm) form;

		try {

			String roleId = request.getParameter("roleId");
			String serviceId = request.getParameter("serviceId");
			
			//APS - temporary fix
			HttpSession session = request.getSession();
			session.setAttribute("roleId", roleId);
			session.setAttribute("serviceId", serviceId);

			//set mode. if mode is add/edit, roleList jsp will include role.jsp
			roleForm.set("mode", "edit");

			if (roleId != null) {

				RoleValue roleValue = securityAccess.getRoleValue(roleId);
				setRoleValue(form, roleValue);

				request.setAttribute("roleValue", roleValue);
				request
						.setAttribute("tabOptions",
								setActiveTab("Role Details"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("role");
	}

	/**
	 * Retrieves a list of all entitlements and a list of entitlements for the
	 * given Role
	 */

	public ActionForward entitlements(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("RoleAction - entitlements...");

		ActionErrors errors = new ActionErrors();
		String roleId = request.getParameter("roleId");

		try {

			List allEntitlements = securityAccess.getAllEntitlements();
			List entitlementList = securityAccess.getEntitlementsByRole(roleId);

			request.setAttribute("allEntitlements", allEntitlements);
			request.setAttribute("entitlementList", entitlementList);
			request.setAttribute("tabOptions", setActiveTab("Entitlements"));

			RoleValue roleValue = securityAccess.getRoleValue(roleId);
			request.setAttribute("roleValue", roleValue);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("role");
	}

	/**
	 * Retrieves the Permission tab and RoleValue in request
	 */

	public ActionForward permissions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		Locale locale = getLocale(request);
		HttpSession session = request.getSession();
		String langCd = locale.getLanguage();

		ActionErrors errors = new ActionErrors();
		String menu = request.getParameter("menu");
		String roleId = request.getParameter("roleId");

		try {
			Context ctx = new InitialContext();
			if (menu == null || menu.length() <= 0) {
				session.removeAttribute("menuNavBar");

				menu = (String) ctx.lookup("java:comp/env/rootAppMenu");
			}

			MenuData menuData = navAccess.getMenu(menu);

			if (menuData != null)
				super.setNavBar("menuNavBar", session, request, menuData
						.getMenuName());

			List menus = (List) navAccess.getMenuList(menu);
			request.setAttribute("menus", menus);

			List permissions = (List) securityAccess.getPermissionsInRole(
					roleId, langCd);

			// no permissions set in desired language, so try default lang
			if (permissions == null) {
				String defaultLanguage = (String) ctx
						.lookup("java:comp/env/defaultLanguage");
				permissions = (List) securityAccess.getPermissionsInRole(
						roleId, defaultLanguage);
			}

			request.setAttribute("permissions", permissions);

			//menus.removeAll(permissions);

			request.setAttribute("menu", menu);

			request.setAttribute("tabOptions", setActiveTab("Permissions"));

			RoleValue roleValue = securityAccess.getRoleValue(roleId);
			request.setAttribute("roleValue", roleValue);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		
	
		return mapping.findForward("role");
	}

	/**
	 * Retrieves a list of all groups and a list of groups having the given Role
	 * and Role tab
	 */

	public ActionForward groups(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("RoleAction - groups...");

		ActionErrors errors = new ActionErrors();
		String roleId = request.getParameter("roleId");

		try {

			List grpList = securityAccess.getAllGroups();
			List grpRoleList = securityAccess.getGroupsForRole(roleId);

			request.setAttribute("grpList", grpList);
			request.setAttribute("grpRoleList", grpRoleList);

			request.setAttribute("tabOptions", setActiveTab("Groups"));

			RoleValue roleValue = securityAccess.getRoleValue(roleId);
			request.setAttribute("roleValue", roleValue);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("role");
	}

	public ActionForward users(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("RoleAction - users...");

		HttpSession session = request.getSession();
		
		WebApplicationContext ctx = getWebApplicationContext();
		this.roleDS = new RoleDataServiceAccess(ctx);
		//this.userDS = new UserDataServiceAccess(ctx);
	
    	UserDataService userMgr = (UserDataService)ctx.getBean("userManager");
		
    	
		ActionErrors errors = new ActionErrors();
		String roleId = request.getParameter("roleId");
		if (roleId == null || roleId.length()==0) {
			roleId = (String)request.getAttribute("roleId");
		}
		String serviceId =  (String)session.getAttribute("serviceId");

		
		  
		try {
        	Search search = createSearch((DynaValidatorForm)form);     
    		List userList = userMgr.search(search);
    		
    		System.out.println("serviceId = " + serviceId);
    		System.out.println("roleId=" + roleId);
    		
    		User[] userRoleAry = roleDS.getUsersInRole(serviceId, roleId);

    		System.out.println("roleId = " + userRoleAry);
    		
    		//List grpList = securityAccess.getAllGroups();
			//List grpRoleList = securityAccess.getGroupsForRole(roleId);

			request.setAttribute("userList", userList);
			request.setAttribute("userRoleAry", userRoleAry);

			request.setAttribute("tabOptions", setActiveTab("Users"));

			RoleValue roleValue = securityAccess.getRoleValue(roleId);
			request.setAttribute("roleValue", roleValue);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("role");
	}	
	
	
	/**
	 * Builds tab to display ACLs for a role
	 */

	// APS JUL 29 04
	public ActionForward acl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		ActionErrors errors = new ActionErrors();

		Locale locale = getLocale(request);
		HttpSession session = request.getSession();
		String langCd = locale.getLanguage();

		String roleId = (String) session.getAttribute("roleId");

		// coming from RoleResourceAction after deleting a privilege
		String resourceId = (String) request.getAttribute("resourceId");
		if (resourceId == null)
			resourceId = request.getParameter("resourceId");

		// resourceId for which we will display privileges
		String privResourceId = request.getParameter("privResourceId");

		try {
			String categoryId = request.getParameter("resCategoryId");
			if (categoryId == null)
				categoryId = (String) request.getAttribute("resCategoryId");
			if (categoryId == null) {
				Context ctx = new InitialContext();
				categoryId = (String) ctx.lookup("java:comp/env/rootCategory");
			}
			System.out.println("categoryId is " + categoryId);

			if (categoryId != null) {
				session.setAttribute("resCategoryId", categoryId);
				// build category list to show
				super.setCategories(categoryId, langCd, request, session,
						"resCategories", "resNavBar", "resCategoryValue");
			}

			// get all resource descendents for this node
			List availResources = new ArrayList();
			List roleResources = new ArrayList();
			

			if (resourceId != null) {
				diamelle.security.resource.ResourceValue rv = acl.getResource(resourceId);

				request.setAttribute("resourceValue", rv);
				availResources = acl.getNodeResources(resourceId);

			} else {
				// if no resourceId, get root branches
				availResources = acl.getCategoryBranches(categoryId);
				System.out.println("categoryBranches = " + availResources);
			}

			// available resources are those left after removing this collection
			// from the first one
			//if (roleResources != null) {
			//  availResources.removeAll(roleResources);
			//}
			request.setAttribute("availResources", availResources);

			// all privileges that are possible
			List allPrivileges = acl.getAllPrivileges();
			request.setAttribute("allPrivileges", allPrivileges);

			List privileges = new ArrayList();

			// if user clicked on a child resource, we want to show child's
			// privileges
			if (privResourceId != null) {
				privileges = acl.getPrivileges(privResourceId, roleId);
				String privilegeTitle = privResourceId + " Privileges";
				request.setAttribute("privilegeTitle", privilegeTitle);
			}

			// else we want to show node's privileges
			else if ((resourceId != null) && (availResources != null)) {
				
				
				Iterator it = availResources.iterator();
				while (it.hasNext()) {
					ResourceValue r = (ResourceValue) it.next();

					// List of ResourceRoleValue
					List p = acl.getPrivileges(r.getResourceId(), roleId);
					privileges.addAll(p);
				}
			}

			if (privileges != null) 
				request.setAttribute("privileges", privileges);
				
			request.setAttribute("tabOptions", setActiveTab("Access Controls"));

			RoleValue roleValue = securityAccess.getRoleValue(roleId);
			request.setAttribute("roleValue", roleValue);

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}
		return mapping.findForward("role");
	}

	/**
	 * Saves/Update Role Details
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward saveRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("RoleAction - saveRole...");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaValidatorForm roleForm = (DynaValidatorForm) form;

		String serviceId = (String) session.getAttribute("serviceId");
		String mode = (String) roleForm.get("mode");
		roleForm.set("mode", "view");

		String page = "rolelist";

		try {
			if (serviceId != null) {
				RoleValue roleValue = getRoleValue(form);
				roleValue.setServiceId(serviceId);

				if (mode.equalsIgnoreCase("add")) {
					//adding the new role

					// if the user had added a RoleId in the Form
					if (roleValue.getRoleId() != null
							&& roleValue.getRoleId().length() > 0) {

						// checking if such a roleId is there in the database
						RoleValue rv = securityAccess.getRoleValue(roleValue
								.getRoleId());

						if (rv == null)
							// if the role does not exist add the role
							securityAccess.addRole(roleValue);
						else {
							// if the role exists throw an error
							roleForm.set("mode", "add");
							errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.exception.exists"));

						}
					} else {
						// check if the role name exists for this database
						// can't have the same role name
						if ( isDupRole(serviceId, roleValue.getRoleName())) {
							roleForm.set("mode", "add");
							
							ActionMessages actionMsg = new ActionMessages();
							actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.exception.exists"));
							this.saveErrors(request, actionMsg);
							
						}else{
							securityAccess.addRole(roleValue);
						}
					}

					init(mapping, form, request, response);

				} else if (mode.equalsIgnoreCase("edit")) {
					//updating the role
					securityAccess.updateRole(roleValue);
					page = "role";
				}
				setRoleValue(form, roleValue);
				request.setAttribute("roleValue", roleValue);
				request.setAttribute("tabOptions",
								setActiveTab("Role Details"));

				//init(mapping,form,request,response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.ejb"));
		}

		// if errors, return to welcome page via index.jsp
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			//request.setAttribute("nextAction", "xxx.do?method=yyy");
			return mapping.findForward("rolelist");
		}

		// category.jsp will need this to provide link to action class
		// request.setAttribute("nextAction", "xxx.do?method=yyy");
		System.out.println("page is  ****************** " + page);
		return mapping.findForward(page);
	}

	// check to see if there are duplicate roles
	private boolean isDupRole(String serviceId, String roleName ) throws RemoteException  {
		List roles = securityAccess.getAllRoles(serviceId);
		if (roles == null)
			return false;
		
		int size = roles.size();
		for (int i=0; i<size; i++) {
			RoleValue roleVal = (RoleValue)roles.get(i);
			if (roleVal.getRoleName().equalsIgnoreCase(roleName))
				return true;
		}
		return false;
		
	}
	
	public ActionForward saveRoleValidate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		System.out.println("RoleAction - saveRoleValidate...");
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();

		String page = "rolelist";
		DynaValidatorForm roleForm = (DynaValidatorForm) form;

		String serviceId = (String) session.getAttribute("serviceId");
		String mode = (String) roleForm.get("mode");

		try {
			if (serviceId != null) {
				RoleValue roleValue = getRoleValue(form);

				if (mode.equalsIgnoreCase("add")) {
					roleForm.set("mode", "add");

				} else if (mode.equalsIgnoreCase("edit")) {
					RoleValue rv = securityAccess
							.getRoleValue((String) roleForm.get("roleId"));

					roleForm.set("mode", "edit");
					request.setAttribute("roleValue", rv);
					request.setAttribute("tabOptions",
							setActiveTab("Role Details"));
					page = "role";
				}

				init(mapping, form, request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.ejb"));
		}

		return mapping.findForward(page);
	}

	/**
	 * Removes selected Role/Roles from the list of Role for a Service
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return ActionForward object
	 */

	public ActionForward removeRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("RoleAction - removeRole...");
		ActionErrors errors = new ActionErrors();
		try {
			if (request.getParameterValues("roleId") != null) {
				// deleting a role from the service
				String[] arrRoleId = request.getParameterValues("roleId");
				for (int i = 0; i < arrRoleId.length; i++)
					securityAccess.deleteRole(arrRoleId[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("error.removeRole"));
		}
		init(mapping, form, request, response);
		return mapping.findForward("rolelist");
	}

	/**
	 * Fetches input data from the form and sets into value class
	 * 
	 * @param form -
	 *            ActionForm return void
	 */

	private RoleValue getRoleValue(ActionForm form) {
		DynaValidatorForm roleForm = (DynaValidatorForm) form;

		RoleValue roleValue = new RoleValue();
		roleValue.setRoleId((String) roleForm.get("roleId"));
		roleValue.setRoleName((String) roleForm.get("roleName"));

		return roleValue;
	}

	/**
	 * Takes values from value class object and sets it into form
	 * 
	 * @param form -
	 *            ActionForm
	 * @param form -
	 *            RoleValue return void
	 */
	private void setRoleValue(ActionForm form, RoleValue roleValue)
			throws Exception {
		DynaValidatorForm roleForm = (DynaValidatorForm) form;

		roleForm.set("roleId", roleValue.getRoleId());
		roleForm.set("serviceId", roleValue.getServiceId());
		roleForm.set("roleName", roleValue.getRoleName());
	}

	/**
	 * Retrieves a list of Attachments for a User
	 * 
	 * @param mapping -
	 *            ActionMapping
	 * @param form -
	 *            ActionForm
	 * @param request -
	 *            HttpServletRequest
	 * @param response -
	 *            HttpServletResponse return
	 */
	private List initTabOptions() {

		List l = new ArrayList();

		TabOption option = new TabOption("Role Details", false,
				"security/role.do?method=editRole", "role.jsp");
		l.add(option);

		option = new TabOption("Access Controls", false, "security/role.do?method=acl",
				"roleresources.jsp");
		l.add(option);

		option = new TabOption("Permissions", false,
				"security/role.do?method=permissions", "permissionlist.jsp");
		l.add(option);

		option = new TabOption("Groups", false, "security/role.do?method=groups", "grouplist.jsp");
		l.add(option);

		option = new TabOption("Users", false, "security/role.do?method=users", "userlist.jsp");
		l.add(option);
		
		
		return l;
	}

	/**
	 * Sets the active tab, regenerating list each time
	 */

	public List setActiveTab(String tab) {
		TabOption option = null;
		List optionList = initTabOptions();
		int size = optionList.size();

		for (int i = 0; i < size; i++) {
			option = (TabOption) optionList.get(i);
			if (option.getTitle().equalsIgnoreCase(tab)) {
				option.setActive(true);
			} else {
				option.setActive(false);
			}
			optionList.set(i, option);
		}
		return optionList;
	}

	/**
	 * Sets the active tab, optionList is stored in session
	 */

	public void setDefaultTabOption(String tab, List optionList) {
		TabOption option = null;
		int size = optionList.size();

		for (int i = 0; i < size; i++) {
			option = (TabOption) optionList.get(i);
			if (option.getTitle().equalsIgnoreCase(tab)) {
				option.setActive(true);
			} else {
				option.setActive(false);
			}
			optionList.set(i, option);
		}
	}
	
    private Search createSearch(DynaValidatorForm form) {
        Search search = new SearchImpl();
        QueryCriteria qc = new QueryCriteria();
      

		qc.like(UserSearchField.FirstName, "%");
		search.addSearchCriteria(qc);
		

        // lastname
        if (form.get("lastName")!= null && ((String) form.get("lastName")).length()>0) {
    		qc.like(UserSearchField.LastName, form.get("lastName"));
    		search.addSearchCriteria(qc);
    	}


        return search;
     }
}