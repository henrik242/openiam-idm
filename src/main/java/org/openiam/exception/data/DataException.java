package org.openiam.exception.data;


public class DataException extends RuntimeException {

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
