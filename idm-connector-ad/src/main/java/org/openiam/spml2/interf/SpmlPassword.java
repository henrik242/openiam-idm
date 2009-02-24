package org.openiam.spml2.interf;

import org.openiam.spml2.msg.password.SetPasswordRequestType;
import org.openiam.spml2.msg.password.ExpirePasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordRequestType;
import org.openiam.spml2.msg.password.ResetPasswordResponseType;
import org.openiam.spml2.msg.password.ValidatePasswordRequestType;
import org.openiam.spml2.msg.password.ValidatePasswordResponseType;
import org.openiam.spml2.msg.ResponseType;

/**
 * The Password Capability defines four operations: setPassword, expirePassword, resetPassword and 
 * validatePassword.
 * @author suneet shah
 *
 */
public interface SpmlPassword {

	/**
	 * The setPassword operation enables a requestor to specify a new password for an object
	 * @param request
	 * @return
	 */
	ResponseType setPassword(SetPasswordRequestType request);
	
	/**
	 * The expirePassword operation marks as invalid the current password for an object
	 * @param request
	 * @return
	 */
	ResponseType expirePassword(ExpirePasswordRequestType request);

	/**
	 * The resetPassword operation enables a requestor to change (to an unspecified value) the
 	 * password for an object and to obtain that newly generated password value.
	 * @param request
	 * @return
	 */
	ResetPasswordResponseType resetPassword(ResetPasswordRequestType request);
	
	/**
	 * The validatePassword operation enables a requestor to determine whether a specified value would
	 *  be valid as the password for a specified object.
	 * @param request
	 * @return
	 */
	ValidatePasswordResponseType validatePassword(ValidatePasswordRequestType request);

}
