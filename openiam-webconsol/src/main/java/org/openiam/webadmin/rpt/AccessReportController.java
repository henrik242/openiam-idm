package org.openiam.webadmin.rpt;


import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.role.dto.Role;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Controller for reports related to access management.
 * @author suneet
 * @version 2
 */
public class AccessReportController extends MultiActionController {

	org.openiam.idm.srvc.role.service.RoleDataService roleDataService;
	

	public org.openiam.idm.srvc.role.service.RoleDataService getRoleDataService() {
		return roleDataService;
	}

	public void setRoleDataService(
			org.openiam.idm.srvc.role.service.RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}



	/**
	 * Method to obtain a list of all roles then pass it on the reporting engine.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView allRolesReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		Role[] roleAry = roleDataService.getAllRoles();
		List roleList = Arrays.asList(roleAry);  
		  

		Map model = new HashMap();
		
		model.put("format", "pdf");
		model.put("dataSource",roleList);
	    return new ModelAndView("rolesMultiFormatReport", model); 

	}


}
