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


import java.util.List;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.dto.PolicyConstants;


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 * Validation class for the Managed System list.
 * @author suneet
 *
 */
public class AttributePolicyValidator implements Validator {

	PolicyDataService policyDataService;

	public boolean supports(Class cls) {
		 return AttributePolicyCommand.class.equals(cls);
	}

	public void validate(Object cmd, Errors err) {
		// TODO Auto-generated method stub
		AttributePolicyCommand attrPolicyCommand =  (AttributePolicyCommand) cmd;
		
		// check for required fields
		if (attrPolicyCommand.getName() == null || attrPolicyCommand.getName().length()==0 ) {
			err.rejectValue("name","required");
		}
		if ((attrPolicyCommand.getRule() == null || attrPolicyCommand.getRule().length()==0 ) &&
		    ((attrPolicyCommand.getRuleSrcUrl() == null || attrPolicyCommand.getRuleSrcUrl().length()==0 ) ) ) {
			err.rejectValue("rule","required");
		}		
		if (err.getErrorCount() > 0) {
			// dont check for duplicates until the basic UI has been validated
			return;
		}
		// check for duplicate policy names on a new policy
		if (attrPolicyCommand.getPolicyId() == null) {
			if (policyDataService.isPolicyExist(PolicyConstants.ATTRIBUTE_POLICY, attrPolicyCommand.getName())) {
				err.rejectValue("name","duplicate");
			}
		}

		
	}

	public PolicyDataService getPolicyDataService() {
		return policyDataService;
	}

	public void setPolicyDataService(PolicyDataService policyDataService) {
		this.policyDataService = policyDataService;
	}


}
