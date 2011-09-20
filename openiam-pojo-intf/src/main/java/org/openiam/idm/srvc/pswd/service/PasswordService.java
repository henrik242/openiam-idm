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
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.pswd.service;

import javax.jws.WebService;

import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.user.dto.User;


/**
 * Password service provides operations to manage passwords. This includes validation against policy,
 * as well as information such as days to expiration, the number of times a password was changed in 
 * day, etc.
 * @author Suneet Shah
 *
 */

public interface PasswordService {

	/**
	 * Determines if a password associated with a principal is valid based on the policies for a security domain.
	 * @param pswd
	 * @return
	 */
	PasswordValidationCode isPasswordValid(Password pswd)  throws ObjectNotFoundException ;
	
	boolean isPasswordChangeAllowed(String domainId, String principal, String managedSysId );
	
	int daysToPasswordExpiration(String domainId, String principal, String managedSysId);
	
	int passwordChangeCount(String domainId, String principal, String managedSysId);

	/**
	 * Determines if a password associated with a principal is valid based on the policies for a security domain.
	 * @param pswd
	 * @return
	 */
	Policy getPasswordPolicy(String domainId, String principal, String managedSysId) ;
	
	/**
	 * Checks to see if a password exists in the history log based on the policy
	 * 
	 * @return 1 - In History, 0 - Not in history, -1 No policy defined
	 */
	int passwordInHistory(Password pswd, Policy policy);
	

	

}
