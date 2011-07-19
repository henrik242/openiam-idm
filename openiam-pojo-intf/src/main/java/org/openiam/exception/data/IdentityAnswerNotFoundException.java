package org.openiam.exception.data;

/**
 * Exception that is thrown when an IdentityAnswer is not found.
 * @author Suneet Shah
 *
 */
public class IdentityAnswerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2891205723229201564L;

	public IdentityAnswerNotFoundException() {
		
	}

	public IdentityAnswerNotFoundException(String msg) {
		super(msg);
	}

	public IdentityAnswerNotFoundException(String msg, Throwable ex) {
		super(msg,ex);
	}

	public IdentityAnswerNotFoundException( Throwable ex) {
		super(ex);
	}
}
