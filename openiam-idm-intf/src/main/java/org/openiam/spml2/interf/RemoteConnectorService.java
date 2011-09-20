package org.openiam.spml2.interf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.ldap.LdapContext;

import org.openiam.connector.type.LookupRequest;
import org.openiam.connector.type.LookupResponse;
import org.openiam.connector.type.PasswordRequest;
import org.openiam.connector.type.ResponseType;
import org.openiam.connector.type.ResumeRequest;
import org.openiam.connector.type.SuspendRequest;
import org.openiam.connector.type.UserRequest;
import org.openiam.connector.type.UserResponse;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;


/**
 * Provides a consolidated interface that is to be used by all connectors..
 * @author suneet
 *
 */
@WebService(targetNamespace = "http://www.openiam.org/service/connector")
public interface RemoteConnectorService {
	
	
	
	/**
	 * The add operation enables a requestor to create a new object on a target
	 * Attributes used by the operation are: <br>
	 * <li>PSOId: Unique identifier for the new object
	 * <li>containerId: Object where this new object should be created in. In a directory, it can be a base DN such as: ou=eng, dc=openiam, dc=org
	 * <li>data: Collection of data attributes that are to be stored in the target system
	 * <li>targetId: An id that is unique for the provider and is the system where this new object
	 * is to be created.
	 * <li>returnData:
	 * @param reqType
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/add") 
	UserResponse add(
			@WebParam(name = "reqType", targetNamespace = "")
			UserRequest reqType);

	/**
	 * The modify operation enables a requestor to update an existing user in the target system
	 * @param reqType
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/modify") 
	UserResponse modify(
			@WebParam(name = "reqType", targetNamespace = "")
			UserRequest reqType);
	
	/**
	 * The delete operation enables the requestor to remove a new user from the target system
	 * @param reqType
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/delete") 
	UserResponse delete(
			@WebParam(name = "reqType", targetNamespace = "")
			UserRequest reqType);
	
	
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/lookup") 
	LookupResponse lookup(
			@WebParam(name = "reqType", targetNamespace = "")
			LookupRequest reqType);
	
	/**
	 * The setPassword operation enables a requestor to specify a new password for an object
	 * @param request
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/setPassword") 
	ResponseType setPassword(
			@WebParam(name = "request", targetNamespace = "")
			PasswordRequest request);
	

	/**
	 * The resetPassword operation enables a requestor to change (to an unspecified value) the
 	 * password for an object and to obtain that newly generated password value.
	 * @param request
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/resetPassword") 
	ResponseType resetPassword(
			@WebParam(name = "request", targetNamespace = "")
			PasswordRequest request);
	


	
	
	
	/**
	 * Suspend / disables a user
	 * @param request
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/suspend") 
	ResponseType suspend(
			@WebParam(name = "request", targetNamespace = "")
			SuspendRequest request); 

	/**
	 * Restores a user that was previously disabled.
	 * @param request
	 * @return
	 */
	@WebMethod(action="http://www.openiam.org/service/connector/RemoteConnectorService/resume") 
	ResponseType resume(
			@WebParam(name = "request", targetNamespace = "")
			ResumeRequest request);

        @WebMethod
    public ResponseType testConnection(
            @WebParam(name = "managedSys", targetNamespace = "")
            ManagedSys managedSys);
	
	
}

