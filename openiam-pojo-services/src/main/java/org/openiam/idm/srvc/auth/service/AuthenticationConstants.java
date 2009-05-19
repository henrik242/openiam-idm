package org.openiam.idm.srvc.auth.service;

/**
 * Lists a set of constants that are used by the authentication service.
 * @author Suneet Shah
 *
 */
public class AuthenticationConstants {

	/**
	 * Constants indicating which type of authentication to use.
	 */
	public static final String AUTHN_TYPE_PASSWORD = "PASSWORD";
	public static final String AUTHN_TYPE_TOKEN = "TOKEN";
	public static final String AUTHN_TYPE_SAML2 = "SAML2";
	public static final String AUTHN_TYPE_CERT = "CERT";
	
	/* TOKEN TYPES */
	public static final int OPENIAM_TOKEN = 1; 
	public static final int SAML1_TOKEN = 2; 
	public static final int SAML2_TOKEN = 3; 
	
	
	/**
	 * SUCCESS - successful login.
	 */
	public static final int RESULT_SUCCESS = 1;
	/**
	 * SUCCESS_PASSWORD_EXP - successful login, but the password is expiring soon.
	 */
	public static final int RESULT_SUCCESS_PASSWORD_EXP = 2;
	/**
	 * SUCCESS_FIRST_TIME - successful login, but its a first time login..
	 */
	public static final int RESULT_SUCCESS_FIRST_TIME = 3;
	/**
	 * INVALID_LOGIN - Invalid login id
	 */	
	public static final int RESULT_INVALID_LOGIN = 100;
	/**
	 * INVALID_PASSWORD - Invalid password
	 */
	public static final int RESULT_INVALID_PASSWORD = 101;
	/**
	 * PASSWORD_EXPIRED - Password has expired
	 */
	public static final int RESULT_PASSWORD_EXPIRED = 102;
	/**
	 * LOGIN_LOCKED - Login is locked
	 */
	public static final int RESULT_LOGIN_LOCKED = 103;
	/**
	 * INVALID_USER_STATUS - User is not in a valid status
	 */
	public static final int RESULT_INVALID_USER_STATUS = 104;
	/**
	 * SERVICE_UNAVAILABLE - Service is unavailable.
	 */
	public static final int RESULT_SERVICE_UNAVAILABLE = 105;
	/**
	 * SERVICE_NOT_FOUND - Service Id does not exist in the system
	 */
	public static final int RESULT_SERVICE_NOT_FOUND = 107;
	
	/**
	 * SENSITIVE_APP - Sensitive application which require re-authentication. Typically thrown
	 * when attempting to use Single Sign On.
	 */
	public static final int RESULT_SENSITIVE_APP = 106;
}
