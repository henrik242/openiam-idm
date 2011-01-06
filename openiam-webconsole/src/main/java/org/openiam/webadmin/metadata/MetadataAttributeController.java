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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.cat.service.CategoryDataService;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;

public class MetadataAttributeController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(MetadataAttributeController.class);

	MetadataWebService metadataService;	
	CategoryDataService categorydataService;
	String baseCategory;


	public MetadataAttributeController() {
		super();
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		MetadataAttributeCommand cmd = new MetadataAttributeCommand();
		
		String typeId = request.getParameter("typeId");
		String menuGroup = request.getParameter("menuGroup");
		String attrId = request.getParameter("attrId");
		
		
		// used by the UI for to show the side menu
		request.setAttribute("menuGroup", menuGroup);
		request.setAttribute("typeId", typeId);
		cmd.getElement().setMetadataTypeId(typeId);
		
		
		MetadataElement[] elementAry = metadataService.getMetadataElementByType(typeId).getMetadataElementAry();
		MetadataElement attr = null;
		if (attrId != null) {
			attr = metadataService.getMetadataElementById(attrId).getMetadataElement();
			cmd.setElement(attr);
			
		}
		
		if (elementAry == null) {
			return new MetadataAttributeCommand();
		}else {
			cmd.setElementAry(elementAry);
			if (attr != null) {
				cmd.setElement(attr);
			}
			
			return cmd;
		}
		
		
	}



	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		log.info("onSubmit called.");
	
		MetadataAttributeCommand cmd = (MetadataAttributeCommand)command;
		//String categoryId = typeListCommand.getCategoryId();
		
		MetadataElement element = cmd.getElement();
		
		// code to save the object.
		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			metadataService.removeMetadataElement(element.getMetadataElementId());
		
		}else {		
			if (element.getMetadataElementId() == null || element.getMetadataElementId().length() == 0) {
				element.setMetadataElementId(null);
				metadataService.addMetadataElement(element);
			}else {
				metadataService.updateMetadataElement(element);
			}

		}
	
	
		return new ModelAndView(new RedirectView("metadataAttribute.cnt?typeId=" + element.getMetadataTypeId() +"&menuGroup=METADATA", true));


		
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
