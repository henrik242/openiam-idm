package org.openiam.webadmin.metadata;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.cat.service.CategoryDataService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;


public class MetadataTypeController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(MetadataTypeController.class);

	MetadataWebService metadataService;	
	CategoryDataService categorydataService;
	String baseCategory;


	public MetadataTypeController() {
		super();
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request) 			throws Exception {
		// TODO Auto-generated method stub
		
		log.info("formBackingObject called.");
		MetadataTypeCommand cmd = new MetadataTypeCommand();
		
		String typeId = request.getParameter("typeId");
		String menuGroup = request.getParameter("menuGroup");
		
		// used by the UI for to show the side menu
		request.setAttribute("menuGroup", menuGroup);
		request.setAttribute("typeId", typeId);
		
		if (typeId.equalsIgnoreCase("NEW")) {
			return cmd;
		}else {
			MetadataType type = metadataService.getMetadataType(typeId).getMetadataType();
			cmd.setMetadataType(type);

		}
		return cmd;

	}




	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("onSubmit called.");
	
		MetadataTypeCommand typeCommand = (MetadataTypeCommand)command;
		MetadataType type = typeCommand.getMetadataType();
		
		String categoryId = (String)request.getSession().getAttribute("metadataCategoryId");

	
		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			metadataService.removeMetadataType(type.getMetadataTypeId());
		}
		if (type.getMetadataTypeId() == null || type.getMetadataTypeId().length() == 0) {
			type.setMetadataTypeId(null);
			metadataService.addMetadataType(type);
			metadataService.addTypeToCategory(type.getMetadataTypeId(), categoryId);
		}else {
			metadataService.updateMetdataType(type);
		}
		
		
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("metadataTypeCmd", typeCommand);
		
		return mav;

		
	}



	public CategoryDataService getCategorydataService() {
		return categorydataService;
	}


	public void setCategorydataService(CategoryDataService categorydataService) {
		this.categorydataService = categorydataService;
	}


	public String getBaseCategory() {
		return baseCategory;
	}


	public void setBaseCategory(String baseCategory) {
		this.baseCategory = baseCategory;
	}


	public MetadataWebService getMetadataService() {
		return metadataService;
	}


	public void setMetadataService(MetadataWebService metadataService) {
		this.metadataService = metadataService;
	}






	

}
