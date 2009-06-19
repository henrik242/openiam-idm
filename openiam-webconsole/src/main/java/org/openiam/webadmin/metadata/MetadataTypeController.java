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
import org.openiam.idm.srvc.meta.service.MetadataService;


public class MetadataTypeController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(MetadataTypeController.class);

	MetadataService metadataService;	
	CategoryDataService categorydataService;
	String baseCategory;


	public MetadataTypeController() {
		super();
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		
		log.info("formBackingObject called.");
		
		String typeId = request.getParameter("typeId");
		String menuGroup = request.getParameter("menuGroup");
		
		// used by the UI for to show the side menu
		request.setAttribute("menuGroup", menuGroup);
		request.setAttribute("typeId", typeId);
		
		MetadataType type = metadataService.getMetadataType(typeId);
		
		if (type == null) {
			return new MetadataTypeCommand();
		}else {
			return new MetadataTypeCommand(type.getActive(), type.getDescription(),
					type.getMetadataTypeId(), type.getSyncManagedSys());
		}
		

	}




	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("onSubmit called.");
	
		MetadataTypeCommand typeCommand = (MetadataTypeCommand)command;
		String typeId = typeCommand.getMetadataTypeId();
		
		//MetadataType[] typeAry =  metadataService.getTypesInCategory(categoryId); 
		// reload the reference data
		//Category[] catAry = categorydataService.getChildCategories(baseCategory, false); 
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		//mav.addObject("metadataTypeListCmd", typeListCommand);
		//mav.addObject("typeAry", typeAry);

		
		return mav;

		
	}


	public MetadataService getMetadataService() {
		return metadataService;
	}


	public void setMetadataService(MetadataService metadataService) {
		this.metadataService = metadataService;
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






	

}
