/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.exception;



/**
 * AuthenticationException is thrown when ever there is an error in the authentication
 * process.  Check the errorCode and errorMessage properties to determine the 
 * cause of the exception.
 * @author Suneet Shah
 * @version 1
 * 
 */
public class AuthenticationException extends Exception {
    
	int errorCode;
	public AuthenticationException(int errCd) {
    	
    	this.errorCode = errCd;
    }
	public int getErrorCode() {
		return errorCode;
	}

}
