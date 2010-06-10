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
import java.util.HashSet;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.ws.NavigatorDataWebService;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.dto.ResourceType;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;

/**
 * Controller to manage connectivity information for a managed system
 * @author suneet
 *
 */
public class ResourceDetailController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ResourceDetailController.class);

	protected ResourceDataService resourceDataService;
	protected NavigatorDataWebService navigationDataService;
	protected MetadataWebService metadataService;
	protected String redirectView;
	protected ManagedSystemDataService managedSysClient;


	public ResourceDetailController() {
		super();
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"),true) );
				
	}
	

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		Resource res = null;
		HttpSession session =  request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		String resId = request.getParameter("resId");
		String resTypeId = request.getParameter("restype");
		
		if (resId != null && resId.length() > 0) {
			request.setAttribute("objId",resId);
		}else {
			resId = request.getParameter("objId");
			request.setAttribute("objId",resId);
		}
		log.info("ResourceId = " + resId);
		
		String menuGrp = request.getParameter("menugrp");
		
		if (resId != null && resId.length() > 0) {
			// existing
			res = resourceDataService.getResource(resId); 
		}else {
			// new
			res = new Resource();
			ResourceType resType = new ResourceType();
			resType.setResourceTypeId(resTypeId);
			res.setResourceType(resType);
			MetadataElement[] elementAry = metadataService.getMetadataElementByType(resTypeId).getMetadataElementAry();
			Set<ResourceProp> propSet = new HashSet<ResourceProp>();
			if (elementAry != null) {
				for (MetadataElement m  : elementAry) {
					ResourceProp p = new ResourceProp();
					p.setMetadataId(m.getMetadataElementId());
					p.setName(m.getAttributeName());
					propSet.add(p);
				}
			}
			res.setResourceProps(propSet);
			
		}
	

		ManagedSys[] sysAry = managedSysClient.getAllManagedSys();
		
		List<Menu> level3MenuList =  navigationDataService.menuGroupByUser(menuGrp, userId, "en").getMenuList();
		request.setAttribute("menuL3", level3MenuList);			

		ResourceDetailCommand cmd = new ResourceDetailCommand();
		cmd.setResource(res);
		cmd.setResourceProp(res.getResourceProps());
		cmd.setManagedSysAry(sysAry);

		return cmd;
	}




	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.debug("onSubmit called");
		
		ResourceDetailCommand resCommand = (ResourceDetailCommand)command;
		ManagedSys sys;
		
		String btn = request.getParameter("btn");

		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			Resource res = resCommand.getResource();
			resourceDataService.removeResource(res.getResourceId());
			return new ModelAndView(new RedirectView(redirectView, true));
			
		}
		
		if (resCommand.getResource().getResourceId() == null ||
				resCommand.getResource().getResourceId().length() == 0	) {
			// new 
			log.info("Creating new resource..");
			Resource res = resCommand.getResource();
			res.setResourceId(null);
			Set<ResourceProp> propSet = resCommand.getResourceProp();

			Set<ResourceProp> newPropSet = new HashSet<ResourceProp>();
			
			if (propSet != null) {
				for (ResourceProp rp : propSet) {
					rp.setResourcePropId(null);
					rp.setResourceId(null);
					newPropSet.add(rp);
				}
			}
			res.setResourceProps(newPropSet);
			
			resourceDataService.addResource(res);


			
			
		}else {
			// existing record
			Resource res = resCommand.getResource();
			res.setResourceProps(resCommand.getResourceProp());
			
			resourceDataService.updateResource(res);
			
		}


	
		
		return new ModelAndView(new RedirectView(redirectView, true));


		
	}


	public ResourceDataService getResourceDataService() {
		return resourceDataService;
	}

	public void setResourceDataService(ResourceDataService resourceDataService) {
		this.resourceDataService = resourceDataService;
	}

	public NavigatorDataWebService getNavigationDataService() {
		return navigationDataService;
	}

	public void setNavigationDataService(
			NavigatorDataWebService navigationDataService) {
		this.navigationDataService = navigationDataService;
	}

	public String getRedirectView() {
		return redirectView;
	}

	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

	public MetadataWebService getMetadataService() {
		return metadataService;
	}

	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}

	public ManagedSystemDataService getManagedSysClient() {
		return managedSysClient;
	}

	public void setManagedSysClient(ManagedSystemDataService managedSysClient) {
		this.managedSysClient = managedSysClient;
	}


	

}
