/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.exception;



/**
 * PasswordException is thrown when ever there is an error in setting or resetting
 * the password. Use the errorCode and errorMessage properties to determine the 
 * cause of the exception.
 * @author Suneet Shah
 * @version 2.1
 * 
 */
public class PasswordException extends Exception {
    
	int errorCode;
	String errorMessage;
	Object exception;
	
	public PasswordException(int errCd) {
    	
    	this.errorCode = errCd;
  
    }
	
	
	public PasswordException(int errCode,String errorMessage,
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
