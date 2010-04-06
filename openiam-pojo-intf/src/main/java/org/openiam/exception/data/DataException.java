package org.openiam.exception.data;


public class DataException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7369318275930039620L;

	public DataException() {
		
	}

	public DataException(String msg) {
		super(msg);
	}

	public DataException(String msg, Throwable ex) {
		super(msg,ex);
	}

	public DataException( Throwable ex) {
		super(ex);
	}
}
