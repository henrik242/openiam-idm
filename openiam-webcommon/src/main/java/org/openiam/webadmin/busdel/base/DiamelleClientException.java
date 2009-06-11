package org.openiam.webadmin.busdel.base;

import java.io.*;
/**
 * Base exception class for component suite. Provides the ability to nest exception that
 * be across layers.
 * @author: Administrator
 */
public class DiamelleClientException extends Exception {
	private Exception nestedException;
	private String stackTrace;


/**
 */
public DiamelleClientException() {
	super();
}
/**
 * Creates a nested exception by adding adding another exception.
 *
 * @param param java.lang.Exception
 */
public DiamelleClientException(Exception param) {
	this.nestedException = param;
	stackTrace = createStackTraceString(param);
	}
/**
 * @param s java.lang.String
 */
public DiamelleClientException(String s) {
	super(s);
}
/**
 * @param msg java.lang.String
 * @param e java.lang.Exception
 */
public DiamelleClientException(String msg, Exception e) {
	this(msg);
	this.nestedException = e;
	stackTrace = createStackTraceString(e);

	}
/**
 * Converts an exception objects stackTrace to a string
 *
 * @return java.lang.String
 * @param e java.lang.Exception
 */
public static String createStackTraceString(Exception e) {
	StringWriter s = new StringWriter();
	e.printStackTrace(new PrintWriter(s));
	return s.toString();
}
/**
 * Returns the exception object with in this object
 * @return java.lang.Exception
 */
public Exception getException() {
	return null;
}
/**
 * Returns a message string that is a concatination of all the messages for the nested exceptions.
 *
 * @return java.lang.String
 */
public String getMessage() {
	String newMsg = super.getMessage();

	if (getNestedException() == null)	return newMsg;

	StringBuffer mainMsg = new StringBuffer();

	String nestedMsg = getNestedException().getMessage();
	if (nestedMsg != null)
		mainMsg.append(newMsg).append(": ").append(nestedMsg);
	else
		mainMsg.append(newMsg);
	return  mainMsg.toString();

}
/**
 * Insert the method's description here.
 *
 * @return java.lang.Exception
 */
public Exception getNestedException() {
	return null;
}
/**
 * Returns a list of all exception that were trapped.  The deepest trace is shown first
 *
 */
public String getStackTraceString() {
	if (nestedException == null)	return null;

	StringBuffer buf = new StringBuffer();
	if (nestedException instanceof DiamelleClientException ) {
		buf.append( ((DiamelleClientException)nestedException).getStackTraceString() );
		buf.append(" ------- ");
	}
	buf.append(stackTrace);
	return buf.toString();

	}
/**
 * Converts the exception to string
 *
 * @return java.lang.String
 */
public String toString() {
	StringBuffer theMsg = new StringBuffer(super.toString());
	if (getNestedException() != null)
		theMsg.append("; \n\t ----> nested ")
			  .append(getNestedException());
	return theMsg.toString();
}
}