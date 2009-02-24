package org.openiam.selfsrvc.util;

/**
 * Connector interface for managing passwords
 * @author suneet
 *
 */
public interface Password {

	public void setPassword(ConnectionParam param, String userId,String password);
	public void expirePassword();
	public void resetPassword();
	public void validatePassword();
}
