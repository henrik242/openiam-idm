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
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.dto.ResourceRoleId;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.role.ws.RoleListResponse;


public class GroupRoleController extends SimpleFormController {

	protected RoleDataWebService roleDataService;
	protected MetadataWebService metadataService;
	protected String roleTypeCategory;
	protected String redirectView;
	protected ResourceDataService resourceDataService;
	protected NavigatorDataWebService navigationDataService;
	protected String menuGroup;
	
	private static final Log log = LogFactory.getLog(GroupRoleController.class);

	public GroupRoleController() {
		super();
	}

	
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		log.info("RoleResourceController - formBakingObject called.");
		Role role = null;
		GroupRoleCommand roleCommand = new GroupRoleCommand();

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

		// get all the resources
		List<Resource> fullResList = new ArrayList<Resource>();
		List<Resource> resList = resourceDataService.getAllResources();
		
		List<Resource> roleResourceList =  resourceDataService.getResourcesForRole(domainId, roleId);
		
	
			// for each role in the main list, check the userRole list to see if its there
			for (Resource res : resList) {
				boolean found = false;
				if (roleResourceList != null) {
					for (Resource r : roleResourceList ) {
						if (res.getResourceId().equalsIgnoreCase(r.getResourceId())) {
							res.setSelected(true);
							fullResList.add(res);
							found = true;
						}
					}
				}
				if (!found) {
					fullResList.add(res);
				}
			}
		
		

		
		
		//roleCommand.setResourceList(fullResList);

		return roleCommand;
	}
	



	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		
		GroupRoleCommand roleCommand = (GroupRoleCommand)command;
		
		// current resource-role list
		List<Resource> curRoleResList = resourceDataService.getResourcesForRole(roleCommand.getDomainId(), roleCommand.getRoleId());
		List<Resource> newRoleResList = null; //roleCommand.getResourceList();
	
		//
		log.info("comparing new resource selection with current selecton.");
		
		if (newRoleResList != null) {
			for (Resource res  : newRoleResList) {
				log.info("Checking resource id=" + res.getResourceId());
				Resource curRes = getCurrentResource(res, curRoleResList);
				if (curRes == null && res.getSelected()) {
					// link role and resource
					log.info("Adding resource (1) " + res.getResourceId() + " to role=" + roleCommand.getRoleId());
					
					resourceDataService.addResourceRole(getResourceRole(res, 
							roleCommand.getRoleId() ,roleCommand.getDomainId()));
				}else {
						log.info("Check if resource should be removed");
						log.info("Current Res=" + curRes);
						log.info("Res Selected=" + res.getSelected());
						   if (!res.getSelected() && curRes != null ) {
							   log.info("attemptng to remove association to resource: " + res.getResourceId());
							// remove the association
							ResourceRole r = getResourceRole(curRes, 
									roleCommand.getRoleId() ,roleCommand.getDomainId());
									
							if (r != null) {
								log.info("removing resource " + res.getResourceId() + " to role=" + roleCommand.getRoleId());
								
								resourceDataService.removeResourceRole(r.getId());
							}
						}
					
				}
			}
		}
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		
		return mav;

		
	}
	
	private ResourceRole getResourceRole(Resource res, String roleId, String domainId) {
		ResourceRole rr = new ResourceRole();
		ResourceRoleId id = new ResourceRoleId();
		id.setDomainId(domainId);
		id.setRoleId(roleId);
		id.setResourceId(res.getResourceId());
		id.setPrivilegeId("na");
		rr.setId(id);
		return rr;
	}
	
	private Resource getCurrentResource(Resource newRes, List<Resource> curRoleResList) {
		if (curRoleResList == null ) {
			return null;
		}
		for (Resource curRes : curRoleResList) {
			if (curRes.getResourceId().equalsIgnoreCase(newRes.getResourceId())) {
				return curRes;
			}
		}
		return null;
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
