package org.openiam.idm.srvc.continfo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;

// Generated Jun 12, 2007 10:46:13 PM by Hibernate Tools 3.2.0.beta8

/**
 * EmailAddress transfer object
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emailAddress", propOrder = {
    "isActive",
    "description",
    "emailAddress",
    "emailId",
    "isDefault",
    "parentId",
    "parentType",
    "name",
    "operation"
})
public class EmailAddress implements java.io.Serializable {

	// Fields    
	protected AttributeOperationEnum operation;
	
    protected Boolean isActive  = new Boolean("True");
    protected String description;
    protected String emailAddress;
    protected String emailId;
    protected Integer isDefault = new Integer(0);
    protected String parentId;
    protected String parentType;
    protected String name;

	
	// Constructors

	/** default constructor */
	public EmailAddress() {
	}

	/** minimal constructor */
	public EmailAddress(String emailId) {
		this.emailId = emailId;
	}

	/** full constructor */
	public EmailAddress(String emailId, String description,
			String emailAddress, Integer isDefault) {
		this.emailId = emailId;
		this.description = description;
		this.emailAddress = emailAddress;
		this.isDefault = isDefault;
	}

	public void updateEmailAddress(EmailAddress emailAdr) {
		this.description = emailAdr.getDescription();
		this.emailAddress = emailAdr.getEmailAddress();
		this.isActive = emailAdr.isActive();
		this.isDefault = emailAdr.getIsDefault();
		this.name = emailAdr.getName();
	}
	
	// Property accessors
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}



	/**
	 * Returns the Id of the parent that owns this address. The parent may be another entity like a 
	 * USER, ORGANIZATION, etc
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * Associates the address with a parent entity, such as USER or ORGANIZATION that owns this address.
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * Returns the type of the parent.  
	 * @return
	 */
	public String getParentType() {
		return parentType;
	}
	/**
	 * Sets the type of the parent.  While the parent type can be anything you choose, a few 
	 * constants are defined in the ContactConstants clss.
	 * @param parentType
	 */
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	
	/**
	 * Indicates if the address is currently active if the value is 
	 * true and inactive if the value false.
	 * @return
	 */
	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	public boolean equals(Object adr) {
		
		EmailAddress newAdr = (EmailAddress)adr;
		
		if (this == newAdr)
			return true;
		if (newAdr == null || newAdr.getClass() != this.getClass())
			return false;
		
		return ( (emailId == newAdr.getEmailId() || 
				 ( emailId != null && emailId.equals(newAdr.getEmailId()))) && 
			
				 (this.description == newAdr.getDescription() ||  
				 ( description != null && description.equals(newAdr.getDescription()))) && 
				
				 (this.isDefault.intValue() == newAdr.isDefault.intValue() )  &&
				 			 
				 (this.parentId == newAdr.getParentId() ||  
  			     ( parentId != null && parentId.equals(newAdr.getParentId()))) && 
  			    		
 			    (this.parentType == newAdr.getParentType() ||  
				( parentType != null && parentType.equals(newAdr.getParentType()))) && 
 			    
 			    
				(this.emailAddress == newAdr.getEmailAddress() ||  
				( emailAddress != null && emailAddress.equals(newAdr.getEmailAddress()))) 		) ; 
		

	}

	
/*	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getDescription() == null ? 0 : this.getDescription().hashCode());
		result = 37 * result
				+ (getEmailAddress() == null ? 0 : this.getEmailAddress().hashCode());
		result = 37 * result
				+ (getEmailId() == null ? 0 : this.getEmailId().hashCode());
		result = 37 * result
				+ (getIsDefault() == null ? 0 : this.getIsDefault().hashCode());
		result = 37 * result
				+ (getParentId() == null ? 0 : this.getParentId().hashCode());
		result = 37 * result
				+ (getParentType() == null ? 0 : getParentType().hashCode());

		return result;
		
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(AttributeOperationEnum operation) {
		this.operation = operation;
	}
	

}
