/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;



/**
 * AuthenticationException is thrown when ever there is an error in the authentication
 * process.  Check the errorCode and errorMessage properties to determine the 
 * cause of the exception.
 * @author Suneet Shah
 * @version 1
 * 
 */
@WebFault(name="AuthenticationException")
@XmlAccessorType( XmlAccessType.FIELD )
public class AuthenticationException extends Exception {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 4882777562707037702L;
	int errorCode;
	String errorMessage;
	Object exception;

	public AuthenticationException() {
  
    }
	
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
