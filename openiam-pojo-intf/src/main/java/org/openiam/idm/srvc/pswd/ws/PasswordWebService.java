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
package org.openiam.idm.srvc.pswd.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.BooleanResponse;
import org.openiam.base.ws.Response;
import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.policy.ws.PolicyResponse;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.user.dto.User;


/**
 * @author Suneet Shah
 *
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/pswd/service", name = "PasswordWebService")
public interface PasswordWebService {

	/**
	 * Determines if a password associated with a principal is valid based on the policies for a security domain.
	 * @param pswd
	 * @return
	 */
	@WebMethod
	Response isPasswordValid(
			@WebParam(name = "pswd", targetNamespace = "")
			Password pswd)  throws ObjectNotFoundException ;
	
	@WebMethod
	BooleanResponse isPasswordChangeAllowed(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "managedSysId", targetNamespace = "")
			String managedSysId );
	
	@WebMethod
	Response daysToPasswordExpiration(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "managedSysId", targetNamespace = "")
			String managedSysId );
	
	@WebMethod
	Response passwordChangeCount(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "managedSysId", targetNamespace = "")
			String managedSysId);

	/**
	 * Determines if a password associated with a principal is valid based on the policies for a security domain.
	 * @param pswd
	 * @return
	 */
	@WebMethod
	PolicyResponse getPasswordPolicy(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "managedSysId", targetNamespace = "")
			String managedSysId)   ;
	

	

}
