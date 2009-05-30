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
	String errorMessage;
	Object exception;
	
	public AuthenticationException(int errCd) {
    	
    	this.errorCode = errCd;
  
    }
	
	
	public AuthenticationException(int errCode,String errorMessage,
			Object exception) {
		super();
		this.errorCode = errCode;
		this.errorMessage = errorMessage;
		this.exception = exception;
	}


	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getException() {
		return exception;
	}
	public void setException(Object exception) {
		this.exception = exception;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
