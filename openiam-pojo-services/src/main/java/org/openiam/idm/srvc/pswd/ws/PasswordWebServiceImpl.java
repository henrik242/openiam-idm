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
package org.openiam.idm.srvc.pswd.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.BooleanResponse;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.exception.ObjectNotFoundException;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.ws.PolicyResponse;
import org.openiam.idm.srvc.pswd.dto.Password;
import org.openiam.idm.srvc.pswd.dto.PasswordValidationCode;
import org.openiam.idm.srvc.pswd.service.PasswordService;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Web service implementation for the PasswordWebService
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.pswd.ws.PasswordWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/pswd/service", 
		portName = "PasswordWebServicePort",
		serviceName = "PasswordWebService")
public class PasswordWebServiceImpl implements PasswordWebService {

	PasswordService passwordDS;
	
	public Response daysToPasswordExpiration(String domainId, String principal,
			String managedSysId) {
		
		Response resp = new Response( ResponseStatus.SUCCESS );
		int days = passwordDS.daysToPasswordExpiration(domainId, principal, managedSysId);
		if (days == -1) {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		resp.setResponseValue(new Integer(days));
		
		return resp;
	}

	public PolicyResponse getPasswordPolicy(
			String domainId, 
			String principal, 
			String managedSysId)  {
		PolicyResponse resp = new PolicyResponse( ResponseStatus.SUCCESS );
		Policy policy = passwordDS.getPasswordPolicy(domainId, principal, managedSysId); 
		if (policy == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		resp.setPolicy(policy);
		return resp;
	}

	public BooleanResponse isPasswordChangeAllowed(String domainId, String principal,
			String managedSysId) {
		
		//Response resp = new Response( ResponseStatus.SUCCESS );
		boolean retval = passwordDS.isPasswordChangeAllowed(domainId, principal, managedSysId);
		return new BooleanResponse(retval);

	}

	public Response isPasswordValid(Password pswd)
			throws ObjectNotFoundException {

		Response resp = new Response( ResponseStatus.SUCCESS );
		
		PasswordValidationCode cd = passwordDS.isPasswordValid(pswd);
		if (cd != PasswordValidationCode.SUCCESS) {
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setErrorCode(ResponseCode.valueOf(cd.getValue() ));	
			return resp;				
		}
		
		return resp;
	}

	public Response passwordChangeCount(String domainId,
			String principal, String managedSysId) {

		Response resp = new Response( ResponseStatus.SUCCESS );
		int count = passwordDS.passwordChangeCount(domainId, principal, managedSysId);
		
		if ( count == -1 ){
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setResponseValue(new Integer(count));
		}

		return resp;

	}

	public PasswordService getPasswordDS() {
		return passwordDS;
	}

	public void setPasswordDS(PasswordService passwordDS) {
		this.passwordDS = passwordDS;
	}


}
