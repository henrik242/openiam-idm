package org.openiam.base;

import java.util.*;
/**
 * Abstract class that allows you to extend a controller class using "pre" and "post" events
 * @author suneet
 *
 */
public abstract class ExtendController {

	protected Map<String,String> errorMap = new HashMap<String,String>();

	static public int SUCCESS_CONTINUE = 1;
	static public int SUCCESS_STOP = 2;
	static public int FAIL = 0;
	
	public ExtendController() {
	}
	
	public Map<String,String> getErrors() {
		return errorMap;
	}

    /**
     * Pre-processing event before data is persisted
     * @param command  - command name that can used to organize the code with in the operation
     * @param objList  - Map of objects that have been passed in.
     * @param cmd - Command object that corresponds to this controller
     * @return
     */
	
	public abstract int pre(String command,
                          Map<String,Object> objList,
                          Object cmd);

    /**
     * Post-processing event - After data has persisted
    * @param command  - command name that can used to organize the code with in the operation
     * @param objList  - Map of objects that have been passed in.
     * @param cmd - Command object that corresponds to this controller
     * @return
     */
	public abstract int post(String command,
                             Map<String,Object> objList,
                             Object cmd);

    /**
     * Validation step
      * @param command  - command name that can used to organize the code with in the operation
     * @param objList  - Map of objects that have been passed in.
     * @param cmd - Command object that corresponds to this controller
     * @param Errors
     * @return
     */
    public abstract int validate(String command,
                                 Map<String,Object> objList,
                                 Object cmd,
                                 Object Errors);
}
