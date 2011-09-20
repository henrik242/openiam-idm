package org.openiam.webadmin.role;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.webadmin.util.AuditHelper;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;


public class RoleDetailController extends SimpleFormController {

	protected RoleDataWebService roleDataService;
	protected MetadataWebService metadataService;
	protected String roleTypeCategory;
	protected String redirectView;
	protected AuditHelper auditHelper;
	protected NavigatorDataWebService navigationDataService;
	protected String menuGroup;
	
	private static final Log log = LogFactory.getLog(RoleDetailController.class);

	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Role role = null;
		RoleDetailCommand roleCommand = new RoleDetailCommand();
		
	  	roleCommand.setTypeList( metadataService.getTypesInCategory(roleTypeCategory).getMetadataTypeAry() );
			
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
	  	
		String roleId = request.getParameter("roleid");
		String domainId = request.getParameter("domainid");
		String mode = request.getParameter("md");
		
		if (mode != null && mode.equalsIgnoreCase("NEW")) {
			request.setAttribute("md", mode);
			session.removeAttribute("roleid");
			session.setAttribute("domainid", domainId);
		}else {
	
			if (roleId != null && domainId != null) {
				session.setAttribute("roleid", roleId);
				session.setAttribute("domainid", domainId);
			}else {
				roleId = (String)session.getAttribute("roleid");
				domainId = (String)session.getAttribute("domainid");
			}
		}

		
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGroup, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
				
		System.out.println(" View roleId = " + roleId);
		System.out.println(" - domainId = " + domainId);
		
		if ( roleId != null) {
			role = roleDataService.getRole(domainId, roleId).getRole();
			roleCommand.setMode("UPDATE");
			System.out.println(" - Viewing exisisting role=" + role);
			roleCommand.setRole(role);

		}else if (mode != null && mode.equalsIgnoreCase("NEW")) {
			
			role = new Role();
			RoleId id = new RoleId();
			id.setServiceId(domainId);
			role.setId(id);
			roleCommand.setMode("NEW");
			roleCommand.setRole(role);

		}
		
		request.setAttribute("menuGroup", "SECURITY_ROLE");


		return roleCommand;
	}
	
	






	public String getRedirectView() {
		return redirectView;
	}



	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}



	public RoleDetailController() {
		super();
	}


	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		RoleDetailCommand roleCommand = (RoleDetailCommand)command;		

		String userId = (String)request.getSession().getAttribute("userId");
		String domainId = (String)request.getSession().getAttribute("domainid");
		String login = (String)request.getSession().getAttribute("login");
		
		Role role = roleCommand.getRole();
		prepareObject(role);
		
		String btn = request.getParameter("btn");

		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			this.roleDataService.removeRole(role.getId().getServiceId(),
					role.getId().getRoleId());

			auditHelper.addLog("DELETE", domainId,	login,
					"WEBCONSOLE", userId, "0", "ROLE", role.getId().getRoleId(), 
					null,   "SUCCESS", null,  null, 
					null, null, null);
			
			return new ModelAndView(new RedirectView(redirectView, true));


		}

		if (roleDataService.getRole(role.getId().getServiceId(), role.getId().getRoleId()) != null ) {
			// update
			roleDataService.updateRole(role);

            if (roleCommand.getMode().equalsIgnoreCase("NEW")) {
                 auditHelper.addLog("NEW", domainId,	login,
                        "WEBCONSOLE", userId, "0", "ROLE", role.getId().getRoleId(),
                        null,   "SUCCESS", null,  null,
                        null, null, null);
            }else {

                auditHelper.addLog("MODIFY", domainId,	login,
                        "WEBCONSOLE", userId, "0", "ROLE", role.getId().getRoleId(),
                        null,   "SUCCESS", null,  null,
                        null, null, null);
                }
		}else {
			// new
			role.setCreateDate(new Date(System.currentTimeMillis()));
			role.setCreatedBy(userId);
			roleDataService.addRole(role);

			auditHelper.addLog("CREATE", domainId,	login,
					"WEBCONSOLE", userId, "0", "ROLE", role.getId().getRoleId(), 
					null,   "SUCCESS", null,  null, 
					null, null, null);	
			
		}

		request.getSession().removeAttribute("roleid");
		request.getSession().removeAttribute("domainid");
	
	
		return new ModelAndView(new RedirectView(redirectView, true));

		

		
	}

	private void prepareObject(Role r) {
		if (r.getMetadataTypeId().equals("")) {
			r.setMetadataTypeId(null);
		}
		if (r.getStatus().equals("")) {
			r.setStatus(null);
		}

	}


	public String getRoleTypeCategory() {
		return roleTypeCategory;
	}



	public void setRoleTypeCategory(String roleTypeCategory) {
		this.roleTypeCategory = roleTypeCategory;
	}


	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}


	public AuditHelper getAuditHelper() {
		return auditHelper;
	}


	public void setAuditHelper(AuditHelper auditHelper) {
		this.auditHelper = auditHelper;
	}








	public MetadataWebService getMetadataService() {
		return metadataService;
	}








	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}








	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}








	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}








	public String getMenuGroup() {
		return menuGroup;
	}








	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}

	

}
