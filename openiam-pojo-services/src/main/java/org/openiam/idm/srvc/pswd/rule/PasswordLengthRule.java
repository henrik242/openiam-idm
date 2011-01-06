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
 * Validates a password to ensure the lenght is consistent with the lenght defined in the password policy
 * @author suneet
 *
 */
public class PasswordLengthRule extends AbstractPasswordRule {


	public PasswordValidationCode isValid() {
		PasswordValidationCode retval = PasswordValidationCode.SUCCESS;
		int minlen = 0;
		int maxlen = 0;
				
		PolicyAttribute attribute = policy.getAttribute("PWD_LEN");
		if (attribute.getValue1() != null) {
			minlen = Integer.parseInt(attribute.getValue1());
		}
		if (attribute.getValue2() != null) {
			maxlen = Integer.parseInt(attribute.getValue2());
		}
		if (password == null) {
			return PasswordValidationCode.FAIL_LENGTH_RULE;
		}
		
		if (minlen > 0 ) {
			if (password.length() < minlen) {
				retval = PasswordValidationCode.FAIL_LENGTH_RULE;
			}
		}
		if (maxlen > 0 ) {
			if (password.length() > maxlen ) {
				retval = PasswordValidationCode.FAIL_LENGTH_RULE;
			}
		}
		
		
		return retval;
	}
	

	
	
}
