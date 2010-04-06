package org.openiam.base;


/**
 * Base object for all POJOs that represent domain objects.
 * @author Suneet Shah
 * @version 3
 */
public class BaseObject implements java.io.Serializable{

	public static final String NEW = "NEW";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	
	protected String objectState = NEW;

	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}


	
}
