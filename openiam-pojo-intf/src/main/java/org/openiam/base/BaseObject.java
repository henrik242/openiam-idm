package org.openiam.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Base object for all POJOs that represent domain objects.
 * @author Suneet Shah
 * @version 3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseObject", propOrder = {
    "objectState",
    "selected"
})
public class BaseObject implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5732158957137722277L;
	
	public static final String NEW = "NEW";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	
	protected Boolean selected = new Boolean(false);
	
	protected String objectState = NEW;

	public BaseObject() {
		
	}
	
	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}


	
}
