package org.openiam.idm.srvc.auth.service;


import javax.jws.WebService;

import org.openiam.exception.*;
import org.openiam.idm.srvc.auth.context.AuthenticationContext;
import org.openiam.idm.srvc.auth.dto.*;

/**
 * <p>
 * <code>AuthenticateService</code> <font face="arial"> allows users to authenticate using
 * various methods </font>
 * </p>
 */
@WebService
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
	void globalLogout(String userId);
	
	


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
	Subject passwordAuth(String domainId, String principal, String password) throws AuthenticationException;


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
	Subject authenticateByToken(String userId, String token, String tokenType) ;
	
	boolean validateToken(String loginId, String token, String tokenType);
	
	boolean validateTokenByUser(String userId, String token, String tokenType);
	
	void updateAppStatus (String managedSysId, String loginId, String status, String sessionId, String token);
	

}
