package org.openiam.idm.srvc.auth.context;

/**
 * Enables the capture of credentials needed for password based authentication.
 * @author Suneet Shah
 *
 */
public class PasswordCredential extends BaseCredential {

	String domainId;
	String principal;
	String password;
}
