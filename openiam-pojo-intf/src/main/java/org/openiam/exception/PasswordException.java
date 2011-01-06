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
 * PasswordException is thrown when ever there is an error in setting or resetting
 * the password. Use the errorCode and errorMessage properties to determine the 
 * cause of the exception.
 * @author Suneet Shah
 * @version 2.1
 * 
 */
@WebFault(name="PasswordException")
@XmlAccessorType( XmlAccessType.FIELD )
public class PasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1775801947691690796L;
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
