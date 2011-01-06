package org.openiam.webadmin.rpt.user;

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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.idm.srvc.audit.ws.IdmAuditLogWebDataService;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.ws.RoleDataWebService;
import org.openiam.idm.srvc.user.ws.UserDataWebService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;


public class InactiveReportController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(InactiveReportController.class);
	protected UserDataWebService userMgr;
	protected RoleDataWebService roleDataService;
	protected OrganizationDataService orgManager;
	


	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
	}
	
	public InactiveReportController() {
		super();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		log.info("refernceData called.");
		
	
		
		Map<Object, Object> dataMap = new HashMap<Object, Object>();	

		dataMap.put("deptList",orgManager.allDepartments(null));
		List<Role> roleList = roleDataService.getAllRoles().getRoleList();
		dataMap.put("roleList", roleList);

	
		return dataMap;
		

	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		UserReportCommand auditCommand = (UserReportCommand)command;




	   return new ModelAndView(new RedirectView(getSuccessView(), true));
	   

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

	public OrganizationDataService getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrganizationDataService orgManager) {
		this.orgManager = orgManager;
	}
		







	

}
