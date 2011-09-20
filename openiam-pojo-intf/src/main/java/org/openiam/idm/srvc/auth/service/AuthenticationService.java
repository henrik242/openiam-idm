package org.openiam.idm.srvc.auth.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openiam.base.ws.BooleanResponse;
import org.openiam.base.ws.Response;
import org.openiam.base.ws.StringResponse;
import org.openiam.exception.*;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.dto.*;
import org.openiam.idm.srvc.auth.ws.AuthenticationResponse;
import org.openiam.idm.srvc.grp.dto.Group;

/**
 * <p>
 * <code>AuthenticateService</code> <font face="arial"> allows users to authenticate using
 * various methods </font>
 * </p>
 */
@WebService
@XmlSeeAlso({
	Group.class
})
public interface AuthenticationService {

	/**
	 * This method executes a global logout so that the user is logged out all the application they have logged into. <br>
	 * For example:
	 * <p>
	 * <code>
	 *   authenticationService.globalLogout(userId);<br>
	 * </code>
	 * 
	 * 
	 * @param userId
	 *            The id of the user.
	 */
	@WebMethod
	void globalLogout(
			@WebParam(name = "userId", targetNamespace = "")
			String userId) throws LogoutException;
	
	


	/**
	 * Sets the login and the password and the password related details for a
	 * new user and also authenticates the user. It can add a new login for a
	 * new service for an existing user. <br>
	 * If authentication is successful returns a Subject which has principals,
	 * userGroups userId, authenticating authority, credentials, token and
	 * expiration time. If not successful, a null is returned. <br>
	 * For example:
	 * <p>
	 * <code>
	 *   Login lv = new Login();<br>
	 lv.setLogin(login);<br>
	 lv.setPassword(password);<br>
	 lv.setService(serviceId);<br>
	 lv.setToken(token);<br>
	 lv.setNewUser(false);<br>
	 Subject sub =  authenticationService.authenticate(lv);<br>
	 * </code>
	 * 
	 * 
	 * @param loginValue
	 *            The bulk accessor class for the PasswordLoginBean.
	 * @return SSOSubject which holds user information
	 */
	//Subject authenticate(AuthenticationContext ctx) throws AuthenticationException;
	
	/**
	 * passwordAuth provides a simple approach to enabling password based authentication.
	 * @param domainId
	 * @param principal
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 */
/*	@WebMethod
	Subject passwordAuth(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "password", targetNamespace = "")
			String password) throws AuthenticationException;
*/
	@WebMethod
	AuthenticationResponse passwordAuth(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId, 
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "password", targetNamespace = "")
			String password);

	/**
	 * For Single Sign On, takes the userId and checks if this user has been
	 * authenticated. The userId may have come from a token or a Subject object.
	 * If authentication is successful returns a Subject which has principals,
	 * userGroups userId, authenticating authority, credentials, token and
	 * expiration time. If not successful, a null is returned.
	 * 
	 * For example:
	 * <p>
	 * <code>
	 *  SSOSubject sub =  authenticationService.authenticate(userId, token);<br>
	 * </code>
	 * 
	 * 
	 * @param userId
	 *            The id of the user
	 * @param token
	 *            An encoded string unique for each login incidence
	 * @return SSOSubject which holds user information.
	 */
	@WebMethod
	Subject authenticateByToken(
			@WebParam(name = "userId", targetNamespace = "")
			String userId, 
			@WebParam(name = "token", targetNamespace = "")
			String token, 
			@WebParam(name = "tokenType", targetNamespace = "")
			String tokenType) throws AuthenticationException ;
	
	@WebMethod
	BooleanResponse validateToken(
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "token", targetNamespace = "")
			String token, 
			@WebParam(name = "tokenType", targetNamespace = "")
			String tokenType);

	@WebMethod
	Response renewToken(
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "token", targetNamespace = "")
			String token, 
			@WebParam(name = "tokenType", targetNamespace = "")
			String tokenType);
	
	@WebMethod
	BooleanResponse validateTokenByUser(
			@WebParam(name = "userId", targetNamespace = "")
			String userId, 
			@WebParam(name = "token", targetNamespace = "")
			String token, 
			@WebParam(name = "tokenType", targetNamespace = "")
			String tokenType);
	
	@WebMethod
	void updateAppStatus (
			@WebParam(name = "managedSysId", targetNamespace = "")
			String managedSysId, 
			@WebParam(name = "principal", targetNamespace = "")
			String loginId, 
			@WebParam(name = "status", targetNamespace = "")
			String status, 
			@WebParam(name = "sessionId", targetNamespace = "")
			String sessionId, 
			@WebParam(name = "token", targetNamespace = "")
			String token);
	

}
