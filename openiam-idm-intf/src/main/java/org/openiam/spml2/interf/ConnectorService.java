package org.openiam.spml2.interf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.ldap.LdapContext;

import org.openiam.exception.AuthenticationException;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.dto.Subject;
import org.openiam.idm.srvc.auth.ws.AuthenticationResponse;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.DeleteRequestType;
import org.openiam.spml2.msg.LookupRequestType;
import org.openiam.spml2.msg.LookupResponseType;
import org.openiam.spml2.msg.ModifyRequestType;
import org.openiam.spml2.msg.ModifyResponseType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;



/**
 * Provides a consolidated interface that is to be used by all connectors..
 * @author suneet
 *
 */
@WebService(targetNamespace = "http://www.openiam.org/service/connector")
public interface ConnectorService {
	
	
	@WebMethod
	public boolean testConnection(
			@WebParam(name = "targetID", targetNamespace = "")
			String targetID);
	
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
	@WebMethod
	AddResponseType add(
			@WebParam(name = "reqType", targetNamespace = "")
			AddRequestType reqType);
	
	@WebMethod
	ModifyResponseType modify(
			@WebParam(name = "reqType", targetNamespace = "")
			ModifyRequestType reqType);
	
	@WebMethod
	ResponseType delete(
			@WebParam(name = "reqType", targetNamespace = "")
			DeleteRequestType reqType);
	@WebMethod
	LookupResponseType lookup(
			@WebParam(name = "reqType", targetNamespace = "")
			LookupRequestType reqType);
	
	/**
	 * The setPassword operation enables a requestor to specify a new password for an object
	 * @param request
	 * @return
	 */
	@WebMethod
	ResponseType setPassword(
			@WebParam(name = "request", targetNamespace = "")
			SetPasswordRequestType request);
	
	/**
	 * The expirePassword operation marks as invalid the current password for an object
	 * @param request
	 * @return
	 */
	@WebMethod
	ResponseType expirePassword(
			@WebParam(name = "request", targetNamespace = "")
			ExpirePasswordRequestType request);

	/**
	 * The resetPassword operation enables a requestor to change (to an unspecified value) the
 	 * password for an object and to obtain that newly generated password value.
	 * @param request
	 * @return
	 */
	@WebMethod
	ResetPasswordResponseType resetPassword(
			@WebParam(name = "request", targetNamespace = "")
			ResetPasswordRequestType request);
	
	/**
	 * The validatePassword operation enables a requestor to determine whether a specified value would
	 *  be valid as the password for a specified object.
	 * @param request
	 * @return
	 */
	@WebMethod
	ValidatePasswordResponseType validatePassword(
			@WebParam(name = "request", targetNamespace = "")
			ValidatePasswordRequestType request);
	
}

