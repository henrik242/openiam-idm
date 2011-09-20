package org.openiam.webadmin.org;

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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.ws.MetadataWebService;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.org.dto.OrganizationAttribute;
import org.openiam.idm.srvc.org.service.OrganizationDataService;


public class OrganizationDetailController extends SimpleFormController {

	protected OrganizationDataService orgDataService;
	protected MetadataWebService metadataService;
	protected String orgTypeCategory;
	protected String redirectView;
	

	private static final Log log = LogFactory.getLog(OrganizationDetailController.class);



	public OrganizationDetailController() {
		super();
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		log.info("formBackingObject called.");
		
		Organization org = null;
		OrganizationDetailCommand orgCommand = new OrganizationDetailCommand();
		
	  	orgCommand.setTypeList( metadataService.getTypesInCategory(orgTypeCategory).getMetadataTypeAry() );
			
		String orgId = request.getParameter("orgId");
		String parentOrgId = request.getParameter("parentOrgId");
		
		log.info("orgId=" + orgId);
		log.info("parentOrgId=" + parentOrgId);
		
		
		if ( orgId != null) {
			org = orgDataService.getOrganization(orgId);
			log.info("OrgId in not null. Org name=" + org.getOrganizationName());
			// check the attributes
			Map<String, OrganizationAttribute> attrMap = org.getAttributes();
			Set<OrganizationAttribute> attrSet = new HashSet<OrganizationAttribute>();
			if (attrMap != null && !attrMap.isEmpty()) {
				log.info("Attributes found");
				Set<String> keySet = attrMap.keySet();
				Iterator<String> strIt = keySet.iterator();
				while ( strIt.hasNext()) {
					OrganizationAttribute attr = attrMap.get(strIt.next());
					attrSet.add(attr);
				}
				orgCommand.setOrgAttributeSet( attrSet);
			}else {
				// get the attribes for the type
				attrSet = new HashSet<OrganizationAttribute>();
				String typeId = org.getMetadataTypeId();
				if (typeId != null) {
					MetadataElement[] elementAry = metadataService.getMetadataElementByType(typeId).getMetadataElementAry();
					if (elementAry !=null) {
						for ( MetadataElement el : elementAry) {
							OrganizationAttribute attr = new OrganizationAttribute();
							attr.setMetadataElementId(el.getMetadataElementId());
							attr.setName(el.getAttributeName());
							attr.setOrganizationId(orgId);
							log.info("Adding attribute: " + attr.getName());
							attrSet.add(attr);
						}
					}
					log.info("OrgAttributeSet size=" + attrSet.size());
					orgCommand.setOrgAttributeSet(attrSet);
				}
			}
		}else {
			org = new Organization();
			org.setParentId(null);
		}
		if (parentOrgId != null && parentOrgId.length() > 0) {
			org.setParentId(parentOrgId);
		}
	
		orgCommand.setOrg(org);
		// get the list of child Organizations if any
		if (orgId != null) {
			List<Organization> childOrgList = orgDataService.subOrganizations(org.getOrgId());
			orgCommand.setChildOrg(childOrgList);
		}
		
		return orgCommand;
	}


	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		OrganizationDetailCommand orgCommand = (OrganizationDetailCommand)command;
		
		Organization org = orgCommand.getOrg();
		prepareObject(org);
		
		String btn = request.getParameter("btn");

		if (btn != null && btn.equalsIgnoreCase("Delete")) {
			orgDataService.removeOrganization(org.getOrgId());
	        ModelAndView mav = new ModelAndView("/deleteconfirm");
	        mav.addObject("msg", "Organization has been successfully deleted.");
	        return mav;

		}
		// manage the attribute
		Set<OrganizationAttribute> orgSet =  orgCommand.getOrgAttributeSet();
		
		//
		if (org.getOrgId() != null && org.getOrgId().length() > 0) {
			// manage the attribute
			if (orgSet != null && orgSet.size() > 0) {
				Map<String, OrganizationAttribute> attrMap = new HashMap<String, OrganizationAttribute>();
				for (OrganizationAttribute oAttr  : orgSet) {
					if (oAttr.getAttrId() == null || oAttr.getAttrId().length() ==0) {
						oAttr.setAttrId(null);
					}
					oAttr.setOrganizationId(org.getOrgId());
					attrMap.put(oAttr.getName(), oAttr);
				}
				org.setAttributes(attrMap);
			}
			orgDataService.updateOrganization(org);
		}else {
			// manage the attribute
			if (orgSet != null && orgSet.size() > 0) {
				Map<String, OrganizationAttribute> attrMap = new HashMap<String, OrganizationAttribute>();
				for (OrganizationAttribute oAttr  : orgSet) {
					oAttr.setAttrId(null);
					oAttr.setOrganizationId(null);
					attrMap.put(oAttr.getName(), oAttr);
				}
				org.setAttributes(attrMap);
			}
			org.setOrgId(null);
			orgDataService.addOrganization(org);
		}
	
		return new ModelAndView(new RedirectView(redirectView, true));
		
		/* ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("orgDetailCmd", orgCommand);
		return mav;
		*/

		
	}

	
	private void prepareObject(Organization org) {
		if (org.getMetadataTypeId().equals("")) {
			org.setMetadataTypeId(null);
		}

		if (org.getParentId() != null &&  org.getParentId().equals("")) {
			org.setParentId(null);
		}
		if (org.getClassification() != null &&  org.getClassification().equals("")) {
			org.setClassification(null);
		}
	}
	

	public OrganizationDataService getOrgDataService() {
		return orgDataService;
	}


	public void setOrgDataService(OrganizationDataService orgDataService) {
		this.orgDataService = orgDataService;
	}



	public String getOrgTypeCategory() {
		return orgTypeCategory;
	}

	public void setOrgTypeCategory(String orgTypeCategory) {
		this.orgTypeCategory = orgTypeCategory;
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



	

}
