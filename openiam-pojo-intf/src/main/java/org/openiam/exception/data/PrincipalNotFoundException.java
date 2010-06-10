package org.openiam.exception.data;

/**
 * Exception that is thrown when a Principal is not found.
 * @author Suneet Shah
 *
 */
public class PrincipalNotFoundException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3878760560679563812L;

	public PrincipalNotFoundException() {
		
	}

	public PrincipalNotFoundException(String msg) {
		super(msg);
	}

	public PrincipalNotFoundException(String msg, Throwable ex) {
		super(msg,ex);
	}

	public PrincipalNotFoundException( Throwable ex) {
		super(ex);
	}
}
