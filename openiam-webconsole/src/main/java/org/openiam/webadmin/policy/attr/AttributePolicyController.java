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

import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.dto.Policy;



/**
 * Controller for the attribute policy form
 * @author suneet
 *
 */
public class AttributePolicyController extends SimpleFormController {


	private static final Log log = LogFactory.getLog(AttributePolicyController.class);

	PolicyDataService policyDataService;



	public AttributePolicyController() {
		super();
	}


	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
		AttributePolicyCommand attrPolicyCommand = (AttributePolicyCommand)command;
		Policy plcy = commandToPolicy(attrPolicyCommand);
		if (plcy.getPolicyId() == null) {
			// new
			policyDataService.addPolicy(plcy);
		}else {
			// update
			policyDataService.updatePolicy(plcy);
		}
		
		// get the new policy list to show on the confirmation page
		Policy[] policyAry = policyDataService.getAllPolicies(attrPolicyCommand.getPolicyDefId());
		
		ModelAndView mav = new ModelAndView(getSuccessView());
		mav.addObject("attrPolicyCmd", attrPolicyCommand);
		mav.addObject("confmsg", "Information was successfully saved");
		mav.addObject("policyAry", policyAry );

		
		return mav;

		
	}
	
	private Policy commandToPolicy(AttributePolicyCommand cmd) {
		Policy plcy = new Policy();
		plcy.setPolicyId(cmd.getPolicyId());
		if (cmd.getPolicyId() == null || cmd.getPolicyId().length() == 0 ) {
			plcy.setCreateDate(new Date(System.currentTimeMillis()));
		}
		plcy.setLastUpdate(new Date(System.currentTimeMillis()));
		plcy.setName(cmd.getName());
		plcy.setDescription(cmd.getDescription());
		plcy.setPolicyDefId(cmd.getPolicyDefId());
		plcy.setRule(cmd.getRule());
		plcy.setStatus(cmd.getStatus());

		
		return plcy;
	}






	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}






	

}
