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
 * Validates a password to ensure that it does not contain the characters defined in the rule
 * the password.
 * @author suneet
 *
 */
public class RejectCharactersRule extends AbstractPasswordRule {


	public PasswordValidationCode isValid() {
		PasswordValidationCode retval = PasswordValidationCode.SUCCESS;
		String excludeCharList=null;

				
		PolicyAttribute attribute = policy.getAttribute("REJECT_CHARS_IN_PSWD");
		if (attribute.getValue1() != null) {
			excludeCharList = attribute.getValue1();
		}
		
		if ( excludeCharList == null) {
			return retval;
		}

		// count the number of characters in the password
		if (password == null) {
			return PasswordValidationCode.FAIL_REJECT_CHARS_IN_PSWD;
		}
		
		// check the password for each of these characters.
		int size = excludeCharList.length();
		for ( int i=0; i < size; i ++ ) {
			int ch = excludeCharList.charAt(i);
			
			int pswdSize = password.length();
			for ( int x=0; x < pswdSize; x++ ) {
				int pswdCh = password.charAt(x);
				if ( pswdCh == ch ) {
					return PasswordValidationCode.FAIL_REJECT_CHARS_IN_PSWD;
				}
			}
		}
			
		return retval;
	}
	

	
	
}
