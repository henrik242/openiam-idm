package org.openiam.base;

import java.util.*;
/**
 * Abstract class that allows you to extend a command using "pre" and "post" events
 * @author suneet
 *
 */
public abstract class ExtendCommand {

	protected Map<String,String> errorMap = new HashMap<String,String>();

	static int SUCCESS_CONTINUE = 1;
	static int SUCCESS_STOP = 2;
	static int FAIL = 0;
	
	public ExtendCommand() {	
	}
	
	public Map<String,String> getErrors() {
		return errorMap;
	}
	
	
	public abstract int pre(String command, Object obj);
	public abstract int post(String command, Object obj);
}
