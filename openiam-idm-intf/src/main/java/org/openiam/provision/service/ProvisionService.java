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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.provision.dto.AccountLockEnum;
import org.openiam.provision.dto.PasswordSync;
import org.openiam.provision.dto.ProvisionGroup;
import org.openiam.provision.dto.ProvisionUser;
import org.openiam.provision.resp.LookupUserResponse;
import org.openiam.provision.resp.PasswordResponse;
import org.openiam.provision.resp.ProvisionUserResponse;


/**
 * @author suneet
 *
 */
@WebService(targetNamespace = "http://www.openiam.org/service/provision", name="ProvisionControllerService")
public interface ProvisionService {

	/**
	 * The addUser operation enables a requestor to create a new user on the target systems
	 */
	@WebMethod
	public ProvisionUserResponse addUser(
			@WebParam(name = "user", targetNamespace = "")
			ProvisionUser user);
	
	/**
	 * The modifyUser operation enables the requestor to modify an existing user in appropriate target systems
	 */
	@WebMethod
	public ProvisionUserResponse modifyUser(
			@WebParam(name = "user", targetNamespace = "")
			ProvisionUser user);
	
		
	
	/**
	 * The deleteUser operation enables the requestor to delete an existing user from the appropriate target systems
	 */
	@WebMethod
	public ProvisionUserResponse deleteUser(
			@WebParam(name = "securityDomain", targetNamespace = "")
			String securityDomain,
			@WebParam(name = "managedSystemId", targetNamespace = "")
			String managedSystemId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal,
			@WebParam(name="status", targetNamespace="")
			UserStatusEnum status,
			@WebParam(name="requestorId", targetNamespace="")
			String requestorId);

	@WebMethod
	public ProvisionUserResponse deleteByUserId(
			@WebParam(name = "user", targetNamespace = "")
			ProvisionUser user,
			@WebParam(name="status", targetNamespace="")
			UserStatusEnum status,
			@WebParam(name="requestorId", targetNamespace="")
			String requestorId);
	
	
	/**
	 * The setPassword operation enables a requestor to specify a new password for an user across target systems
	 * @param request
	 * @return
	 */
	@WebMethod
	public Response setPassword(
			@WebParam(name = "passwordSync", targetNamespace = "")
			PasswordSync passwordSync);

	@WebMethod
	public PasswordResponse resetPassword(
			@WebParam(name = "passwordSync", targetNamespace = "")
			PasswordSync passwordSync);
	
	/**
	 * The addGroup operation enables a requestor to create a new group on the target systems
	 */
	@WebMethod
	ProvisionGroup addGroup(@WebParam(name = "group", targetNamespace = "")
				  ProvisionGroup group);
	
	/**
	 * The modifyGroup operation enables the requestor to modify an existing Group in appropriate target systems
	 */
	@WebMethod
	ProvisionGroup modifyGroup(
			@WebParam(name = "group", targetNamespace = "")
			ProvisionGroup group);
	
	/**
	 * The deleteGroup operation enables the requestor to delete an existing group from the appropriate target systems
	 */
	@WebMethod
	ProvisionGroup deleteGroup(
			@WebParam(name = "groupId", targetNamespace = "")
			String groupId);
	
	/**
	 * Operation locks or unlocks an account.  If the operation flag is true, then the user is locked. Otherwise
	 * its is unlocked.
	 * @param userId
	 * @param operation
	 * @return
	 */
	@WebMethod
	Response lockUser(
			@WebParam(name = "userId", targetNamespace = "")
			String userId, 
			@WebParam(name = "operation", targetNamespace = "")
			AccountLockEnum operation,
			@WebParam(name = "requestorId", targetNamespace = "")
			String requestorId);
	
	/**
	 * Operation disables or un-disables an account.  If the operation flag is true, then the user is disabled. Otherwise
	 * its is disabled.
	 * @param userId
	 * @param operation
	 * @return
	 */
	@WebMethod
	Response disableUser(
			@WebParam(name = "userId", targetNamespace = "")
			String userId, 
			@WebParam(name = "operation", targetNamespace = "")
			boolean operation,
			@WebParam(name = "requestor", targetNamespace = "")
			String requestorId);

    @WebMethod
    LookupUserResponse getTargetSystemUser(
            @WebParam(name = "principalName", targetNamespace = "")
            String principalName,
            @WebParam(name = "managedSysId", targetNamespace = "")
            String managedSysId);

    @WebMethod
    LookupUserResponse getTargetSystemUserWithUserId(
            @WebParam(name = "userId", targetNamespace = "")
            String userId,
            @WebParam(name = "managedSysId", targetNamespace = "")
            String managedSysId);


}
