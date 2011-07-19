package org.openiam.webadmin.client;

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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.service.OrganizationDataService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientListController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ClientListController.class);

	protected OrganizationDataService orgDataService;

	public ClientListController() {
		super();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map model = new HashMap();
		
		String mode = request.getParameter("mode");
		if (mode != null && mode.equalsIgnoreCase("1")) {
			model.put("msg", "Your information has been sucessfully saved.");
		}
		
		request.getSession().removeAttribute("sideMenus");
		
		return model;
	}

	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClientListCommand orgListCmd = (ClientListCommand)command;
		
		// get the list of Groups
		String nameValue = orgListCmd.getNameFilter();
		String orgClassification = orgListCmd.getOrgType();
		if (orgClassification != null && orgClassification.length() == 0) {
			orgClassification = null;
		}
		List<Organization> orgList = orgDataService.search(nameValue + "%", null, orgClassification, null);
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		int count = 0;
		mav.addObject("clientListCmd", orgListCmd);
		mav.addObject("searchResult", orgList);
		if (orgList != null) {
			count = orgList.size();
		}
		mav.addObject("resultsize", count);
		
		return mav;

		
	}




	public OrganizationDataService getOrgDataService() {
		return orgDataService;
	}




	public void setOrgDataService(OrganizationDataService orgDataService) {
		this.orgDataService = orgDataService;
	}





	

}
