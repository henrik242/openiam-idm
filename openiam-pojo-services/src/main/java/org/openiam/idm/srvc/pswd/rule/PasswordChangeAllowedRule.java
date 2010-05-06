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

/**
 * 
 */
package org.openiam.idm.srvc.pswd.rule;


import org.openiam.idm.srvc.policy.dto.PolicyAttribute;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;

/**
 * Validates a password to ensure the password is not equal to the principal
 * @author suneet
 *
 */
public class PasswordChangeAllowedRule extends AbstractPasswordRule {


	public PasswordValidationCode isValid() {
			
		PasswordValidationCode retval = PasswordValidationCode.SUCCESS;
		boolean enabled = false;
				
		PolicyAttribute attribute = policy.getAttribute("PASSWORD_CHANGE_ALLOWED");
		if (attribute.getValue1() != null) {
			enabled = true;

		}
		if (enabled) {
			if (attribute.getValue1() != null && attribute.getValue1().equalsIgnoreCase("0")) {
				return PasswordValidationCode.FAIL_PASSWORD_CHANGE_ALLOW;
			}
			
		}
			
		return retval;
	}

	
}


