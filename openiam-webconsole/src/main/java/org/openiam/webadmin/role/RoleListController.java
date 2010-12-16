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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

public class RoleListController extends SimpleFormController {

	protected RoleDataWebService roleDataService;
	protected SecurityDomainDataService secDomainService;

	
	private static final Log log = LogFactory.getLog(RoleListController.class);
	


	public RoleListController() {
		super();
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		log.info("referenceData called.");
		request.getSession().removeAttribute("sideMenus");
		
		String menuGroup = request.getParameter("menuid");
		request.getSession().setAttribute("menugrp", menuGroup);
		
		
		SecurityDomain[] domainAry = secDomainService.getAllSecurityDomains();
		
		Map model = new HashMap();
		model.put("secDomainAry", domainAry);
		
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			request.setAttribute("message","The information has been successfully updated.");
		}
			
		return model;
	}
	
	private void loadReferenceDataMAV(ModelAndView model) {
		SecurityDomain[] domainAry = secDomainService.getAllSecurityDomains();
		model.addObject("secDomainAry", domainAry);
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		org.openiam.webadmin.role.RoleListCommand roleListCommand = (org.openiam.webadmin.role.RoleListCommand)command;
		
		List<Role> roleList = roleDataService.getRolesInDomain( roleListCommand.getDomainId() ).getRoleList();
	
	

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("roleListCmd", roleListCommand);
		mav.addObject("roleList", roleList);
		mav.addObject("domainId", roleListCommand.getDomainId());
		if (roleList != null) {
			mav.addObject("searchResults", roleList.size());
		}else {
			mav.addObject("searchResults", 0);
		}
		loadReferenceDataMAV(mav);
		
		return mav;
	
	}






	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}





	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}


	public RoleDataWebService getRoleDataService() {
		return roleDataService;
	}


	public void setRoleDataService(RoleDataWebService roleDataService) {
		this.roleDataService = roleDataService;
	}




	

}
