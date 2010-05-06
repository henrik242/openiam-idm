package org.openiam.webadmin.metadata;

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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.cat.service.CategoryDataService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;


/**
 * Displays a list of Metadata Types
 * @author suneet
 *
 */
public class MetadataTypeListController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(MetadataTypeListController.class);

	MetadataWebService metadataService;	
	CategoryDataService categorydataService;
	String baseCategory;


	public MetadataTypeListController() {
		super();
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		log.info("referenceData called.");
		
		Category[] catAry = categorydataService.getChildCategories(baseCategory, false); 
			
	
		Map model = new HashMap();
		model.put("categoryAry", catAry);
		
		return model;
	}
	

	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("onSubmit called.");
	
		MetadataTypeListCommand typeListCommand = (MetadataTypeListCommand)command;
		String categoryId = typeListCommand.getCategoryId();
		
		MetadataType[] typeAry =  metadataService.getTypesInCategory(categoryId).getMetadataTypeAry(); 
		// reload the reference data
		Category[] catAry = categorydataService.getChildCategories(baseCategory, false); 
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("metadataTypeListCmd", typeListCommand);
		mav.addObject("typeAry", typeAry);
		mav.addObject("categoryAry", catAry);
		if (typeAry != null) {
			mav.addObject("searchResults", typeAry.length);
		}else {
			mav.addObject("searchResults", 0);
		}
		
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
