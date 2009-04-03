package org.openiam.webadmin.conn.mngsys;

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
import org.openiam.idm.srvc.mngsys.dto.SysAttributeMap;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyConstants;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.secdomain.dto.SecurityDomain;


/**
 * Controller to manage the attribute mappings that are necessary for a managed system.
 *  
 * @author suneet
 *
 */
public class ManagedSysAttrMapController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(ManagedSysAttrMapController.class);



	private ManagedSystemDataService managedSysService; 
	private SecurityDomainDataService secDomainService;
	private PolicyDataService policyDataService;

	


	public ManagedSysAttrMapController() {
		super();
	}


	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		SecurityDomain[] domainAry = secDomainService.getAllSecurityDomains();
		
		Map model = new HashMap();
		model.put("secDomainAry", domainAry);
		
		return model;
	}
	
	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {

		String connectorId = request.getParameter("connectorId");
		String menuGroup = request.getParameter("menuGroup");
		
		// used by the UI for to show the side menu
		request.setAttribute("menuGroup", menuGroup);
		request.setAttribute("connectorId", connectorId);

		
		
		SysAttrMapCommand attrMapCommand  = new SysAttrMapCommand();
		
		// set the list of policies
		Policy[] attrPolicyAry = policyDataService.getAllPolicies(PolicyConstants.ATTRIBUTE_POLICY);
		attrMapCommand.setAttrPolicyAry(attrPolicyAry);

		// always padd an extra row so that user can add a row without having to hit the 
		// add row button
		SysAttributeMap attr = new SysAttributeMap();
		SysAttributeMap[] attrMapAry = new SysAttributeMap[1];
		attrMapAry[0] = attr;
		attrMapCommand.setAttrMapAry(attrMapAry);
		
		
		return attrMapCommand;


}


	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		SysAttrMapCommand attrMapCommand = (SysAttrMapCommand)command;
		
		SysAttributeMap attr = new SysAttributeMap();
		attr.setAttributeMapId("123");
		attr.setTargetAttributeName("cn");
		
		SysAttributeMap[] attrAry = new SysAttributeMap[1];
		attrAry[0] = attr;
		//attrMapCommand.setMapAry(attrAry);
		
	
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("attrMapCmd", attrMapCommand);
	
		/*mav.addObject("managedSysAry", managedSysAry);
		if (managedSysAry != null) {
			mav.addObject("searchResults", managedSysAry.length);
		}else {
			mav.addObject("searchResults", 0);
		}
		*/
		
		return mav;

		
	}



	public ManagedSystemDataService getManagedSysService() {
		return managedSysService;
	}

	public void setManagedSysService(ManagedSystemDataService managedSysService) {
		this.managedSysService = managedSysService;
	}

	
	public SecurityDomainDataService getSecDomainService() {
		return secDomainService;
	}

	public void setSecDomainService(SecurityDomainDataService secDomainService) {
		this.secDomainService = secDomainService;
	}


	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}


	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	

}
