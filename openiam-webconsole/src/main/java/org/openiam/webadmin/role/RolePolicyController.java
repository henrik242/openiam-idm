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



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.dto.ResourceRoleId;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.dto.RolePolicy;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;


public class RolePolicyController extends SimpleFormController {

	protected RoleDataWebService roleDataService;
	protected MetadataWebService metadataService;
	protected String roleTypeCategory;
	protected String redirectView;
	protected ResourceDataService resourceDataService;
	protected NavigatorDataWebService navigationDataService;
	protected String menuGroup;
	
	private static final Log log = LogFactory.getLog(RolePolicyController.class);

	public RolePolicyController() {
		super();
	}

	
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		log.info("RoleResourceController - formBakingObject called.");
		Role role = null;
		RolePolicyCommand roleCommand = new RolePolicyCommand();

		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
	  	
		String roleId = (String)session.getAttribute("roleid");
		String domainId = (String)session.getAttribute("domainid");

		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGroup, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);	
			
		if (roleId != null) {		
			// used by the ui add/remove role and resource associations
			roleCommand.setDomainId(domainId);
			roleCommand.setRoleId(roleId);
		}
	
		request.setAttribute("menuGroup", "SECURITY_ROLE");

		// get all the role policy
		List<RolePolicy> policyList = roleDataService.getAllRolePolicies(domainId, roleId).getRolePolicy();;		
		
		if (policyList == null) {
			policyList = new ArrayList<RolePolicy>();
		}
		// create a blank row so that the user can enter in a new policy
		RolePolicy rp = new RolePolicy();
		rp.setRoleId(roleId);
		rp.setServiceId(domainId);
		rp.setName("**ENTER NAME**");
		policyList.add(rp);
		
		roleCommand.setPolicyList(policyList);
		
		

		return roleCommand;
	}
	



	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		
		RolePolicyCommand roleCommand = (RolePolicyCommand)command;
		List<RolePolicy> policyList = roleCommand.getPolicyList();
		
		String domainId =  roleCommand.getDomainId();
		String roleId = roleCommand.getRoleId();
		
		
		
		// process the map
		String btn = request.getParameter("btn");
		if (btn.equalsIgnoreCase("Delete")) {
			if (policyList != null) {			
				for ( RolePolicy rp : policyList) {
					if (rp.getSelected()) {
						String id = rp.getRolePolicyId();
						if (id != null && id.length() > 0) {
							this.roleDataService.removeRolePolicy(rp);
						}
					}
				}				
			}
		}else {
		
			if (policyList != null) {			
				for ( RolePolicy rp : policyList) {
					if (rp.getRolePolicyId() == null || rp.getRolePolicyId().length() == 0) {
						// new 
						String name = rp.getName();
						if (name != null && name.length() > 1 && !name.equalsIgnoreCase("**ENTER NAME**")) {
							rp.setRolePolicyId(null);
							rp.setRoleId(roleId);
							rp.setServiceId(domainId);
							roleDataService.addRolePolicy(rp);
						}
					}else {
						// update
						rp.setRoleId(roleId);
						rp.setServiceId(domainId);
						roleDataService.updateRolePolicy(rp);
					}
				}
				
			}
		}

				
		ModelAndView mav = new ModelAndView(getSuccessView());
		
		return mav;

		
	}


	public String getRedirectView() {
		return redirectView;
	}



	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
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


	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}


	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
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
