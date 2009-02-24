package org.openiam.idm.connector;

/**
 * Connector interface for managing passwords
 * @author suneet
 *
 */
public interface Password {

	public void setPassword(String userId,String password);
	public void expirePassword();
	public void resetPassword();
	public void validatePassword();
}
