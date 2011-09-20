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
package org.openiam.provision.service;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.provision.dto.AccountLockEnum;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.dto.ProvisionGroup;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.LookupUserResponse;
import org.openiam.provision.resp.PasswordResponse;
import org.openiam.provision.resp.ProvisionUserResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * <code>AsynchUserProvisionService</code> Interface for the Asynchronous Provisioning service which is used for provisioning users.
 * @author suneet
 *
 */
@WebService(targetNamespace = "http://www.openiam.org/service/provision", name="AsynchUserProvisionService")
public interface AsynchUserProvisionService {

	/**
	 * The addUser operation enables a requestor to create a new user on the target systems
	 */
	@WebMethod
	public void addUser(
            @WebParam(name = "user", targetNamespace = "")
            ProvisionUser user);
	
	/**
	 * The modifyUser operation enables the requestor to modify an existing user in appropriate target systems
	 */
	@WebMethod
	public void modifyUser(
            @WebParam(name = "user", targetNamespace = "")
            ProvisionUser user);
	
		

}
