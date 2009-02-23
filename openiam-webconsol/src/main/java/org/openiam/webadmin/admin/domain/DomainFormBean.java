package org.openiam.webadmin.admin.domain;

/**
 * MetdataFormBean is used to move data between view and controller layers for the group admin functionality.
 * @author suneet
 *
 */
public class DomainFormBean implements java.io.Serializable {


	String name;
	String description;
	
	
	public DomainFormBean(String description, String name) {
		super();
		this.description = description;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	
	

	public DomainFormBean() {
		
	}
	
	
}
