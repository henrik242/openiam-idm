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
 * Exception is thrown when there is an exception during the encryption/decryption process.
 * @author Suneet Shah
 * @version 1
 * 
 */
@WebFault(name="EncryptionException")
@XmlAccessorType( XmlAccessType.FIELD )
public class EncryptionException extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6538874938419392249L;
	Exception exception;

	public EncryptionException() {
  
    }

	public EncryptionException(Exception exception) {
		super();
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}



}
