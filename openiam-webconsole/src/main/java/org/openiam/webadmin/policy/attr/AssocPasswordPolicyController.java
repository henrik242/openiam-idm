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

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.policy.dto.PolicyDef;
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;
import org.openiam.idm.srvc.policy.dto.PolicyObjectAssoc;



/**
 * Controller for the attribute policy form
 * @author suneet
 *
 */
public class AssocPasswordPolicyController extends SimpleFormController {




	private static final Log log = LogFactory.getLog(AssocPasswordPolicyController.class);

	protected PolicyDataService policyDataService;
	protected String redirectView;


	public AssocPasswordPolicyController() {
		super();
	}


	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		AssocPasswordPolicyCommand policyCommand = new AssocPasswordPolicyCommand();
		
		
		// load the policy in the event of a view operation
		String policyId = request.getParameter("policyId");
		if (policyId != null) {
			List<PolicyObjectAssoc> assocList = policyDataService.getAssociationsForPolicy(policyId);
			if (assocList != null && !assocList.isEmpty()) {
				policyCommand.setAssoc(assocList.get(0));
			}
		}else {
			policyCommand = new AssocPasswordPolicyCommand();
			
		}
		return policyCommand;
	}
	
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		AssocPasswordPolicyCommand policyCommand = (AssocPasswordPolicyCommand)command;

		PolicyObjectAssoc assoc = policyCommand.getAssoc();
		
		if (assoc.getPolicyObjectId() == null || assoc.getPolicyObjectId().length() == 0) {
			assoc.setPolicyObjectId(null);
			assoc.setPolicyId(request.getParameter("policyId"));
			policyDataService.associatePolicyToObject(assoc);
		}else {
			assoc.setPolicyId(request.getParameter("policyId"));
			policyDataService.updatePolicyAssociation(assoc);
		}
		
	

		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("assocPolicyCmd", policyCommand);
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
