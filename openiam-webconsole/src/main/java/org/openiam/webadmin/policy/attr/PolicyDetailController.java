package org.openiam.webadmin.policy.attr;

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
import org.springframework.web.servlet.view.RedirectView;

import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;



/**
 * Controller for the attribute policy form
 * @author suneet
 *
 */
public class PolicyDetailController extends SimpleFormController {




	private static final Log log = LogFactory.getLog(PolicyDetailController.class);

	protected PolicyDataService policyDataService;
	protected String redirectView;


	public PolicyDetailController() {
		super();
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		PolicyDetailCommand policyCommand = new PolicyDetailCommand();
		
		
		// load the policy in the event of a view operation
		String policyId = request.getParameter("policyId");
		if (policyId != null) {
			Policy policy = policyDataService.getPolicy(policyId);
			Set<PolicyAttribute> policyAttrSet = policy.getPolicyAttributes();
			
			policyCommand.setPolicy(policy);
			policyCommand.setPolicyAttr(policyAttrSet);

		}else {
			policyCommand = new PolicyDetailCommand();
		}
		return policyCommand;
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		PolicyDetailCommand policyCommand = (PolicyDetailCommand)command;

		Policy policy = policyCommand.getPolicy();
		Set<PolicyAttribute> attrSet = policyCommand.getPolicyAttr();
		policy.setPolicyAttributes(attrSet);
		
		

		
		// check which button was clicked
		String btn = request.getParameter("btn");
		if (btn != null && btn.equalsIgnoreCase("Delete")) {

			policyDataService.removePolicy(policy.getPolicyId());
		}else {		
			if (policy.getPolicyId() == null || policy.getPolicyId().length() ==0) {
				// new
				policy.setPolicyId(null);
				policyDataService.addPolicy(policy);
			}else {
				// update
				policyDataService.updatePolicy(policy);
			}
		}
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		return mav;

		
	}
	




	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


	public String getRedirectView() {
		return redirectView;
	}


	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}






	

}
