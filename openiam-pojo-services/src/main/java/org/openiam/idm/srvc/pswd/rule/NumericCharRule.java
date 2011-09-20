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
 * Validates a password to ensure that it contains the appropriate number of numeric characters in 
 * the password.
 * @author suneet
 *
 */
public class NumericCharRule extends AbstractPasswordRule {


	public PasswordValidationCode isValid() {
		PasswordValidationCode retval = PasswordValidationCode.SUCCESS;
		
		int minChar = 0;
		int maxChar = 0;
				
		PolicyAttribute attribute = policy.getAttribute("NUMERIC_CHARS");
		if (attribute.getValue1() != null && attribute.getValue1().length() > 0 ) {
			minChar = Integer.parseInt(attribute.getValue1());
		}
		if (attribute.getValue2() != null && attribute.getValue2().length() >0) {
			maxChar = Integer.parseInt(attribute.getValue2());
		}
		// count the number of characters in the password
		if (password == null) {
			return PasswordValidationCode.FAIL_NUMERIC_CHAR_RULE;
		}
		int charCtr = 0;
		for (int i=0; i < password.length(); i++) {
			int ch = password.charAt(i);
			if (ch >= 48 && ch <= 57) {
				charCtr++;
			}
		}
		
		if (minChar > 0 ) {
			if (charCtr  < minChar) {
				retval = PasswordValidationCode.FAIL_NUMERIC_CHAR_RULE;;
			}
		}
		if (maxChar > 0 ) {
			if (charCtr > maxChar ) {
				retval = PasswordValidationCode.FAIL_NUMERIC_CHAR_RULE;;
			}
		}
		
		
		return retval;
	}
	

	
	
}
