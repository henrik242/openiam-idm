package org.openiam.exception.data;

/**
 * Exception that is thrown when an object is not found.
 * @author Suneet Shah
 *
 */
public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException() {
		
	}

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable ex) {
		super(msg,ex);
	}

	public ObjectNotFoundException( Throwable ex) {
		super(ex);
	}
}
