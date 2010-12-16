/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;



/**
 * LogoutException is thrown whenever there is an error in the logout 
 * process.  
 * cause of the exception.
 * @author Suneet Shah
 * @version 1
 * 
 */
@WebFault(name="LogoutException")
@XmlAccessorType( XmlAccessType.FIELD )
public class LogoutException extends Exception {
    
	int errorCode;
	String errorMessage;
	Object exception;
	
	public LogoutException() {
		
	}
	public LogoutException(int errCd) {
    	
    	this.errorCode = errCd;
  
    }
	
	
	public LogoutException(int errCode,String errorMessage,
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
