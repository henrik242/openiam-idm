package org.openiam.webadmin.res;

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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceType;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

public class ResourceListController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ResourceListController.class);


	protected ResourceDataService resourceDataService;


	public ResourceListController() {
		super();
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		System.out.println("referenceData called.");
		
		List<ResourceType> resTypeList =  resourceDataService.getAllResourceTypes();
		
		Map model = new HashMap();
		model.put("resTypeList", resTypeList);
	
		return model;
	}
	

	private void loadReferenceDataMAV(ModelAndView model) {
		List<ResourceType> resTypeList =  resourceDataService.getAllResourceTypes();
		model.addObject("resTypeList", resTypeList);
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		ResourceListCommand resListCommand = (ResourceListCommand)command;
		List<Resource> resourceList = resourceDataService.getResourcesByType( resListCommand.getResourceTypeId() );
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("resourceList", resourceList);
		mav.addObject("resourceListCmd", resListCommand);
		mav.addObject("resTypeId", resListCommand.getResourceTypeId());
		if (resourceList != null) {
			mav.addObject("searchResults", resourceList.size());
		}else {
			mav.addObject("searchResults", 0);
		}
		loadReferenceDataMAV(mav);
		
		return mav;

		
	}





	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}


	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}


	

}
