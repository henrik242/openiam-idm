package org.openiam.idm.srvc.continfo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;

// Generated Jun 12, 2007 10:46:13 PM by Hibernate Tools 3.2.0.beta8

/**
 * Phone transfer object
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "phone", propOrder = {
    "isActive",
    "areaCd",
    "countryCd",
    "description",
    "isDefault",
    "parentId",
    "parentType",
    "phoneExt",
    "phoneId",
    "phoneNbr",
    "phoneType",
    "name",
    "operation"
})
public class Phone implements java.io.Serializable {

	// Fields    
	protected AttributeOperationEnum operation;
	
    protected Boolean isActive = new Boolean("True");
    protected String areaCd;
    protected String countryCd;
    protected String description;
    protected Integer isDefault = new Integer(0);
    protected String parentId;
    protected String parentType;
    protected String phoneExt;
    protected String phoneId;
    protected String phoneNbr;
    protected String name;
    protected String phoneType;
	
	// Constructors

	/** default constructor */
	public Phone() {
	}

	/** minimal constructor */
	public Phone(String phoneId) {
		this.phoneId = phoneId;
	}


	/** full constructor */
	public Phone(String phoneId, String areaCd, String countryCd,
			String description, String phoneNbr, String phoneExt,
			Integer isDefault, String addressId) {
		this.phoneId = phoneId;
		this.areaCd = areaCd;
		this.countryCd = countryCd;
		this.description = description;
		this.phoneNbr = phoneNbr;
		this.phoneExt = phoneExt;
		this.isDefault = isDefault;
	}

	public void updatePhone(Phone ph) {
		this.areaCd = ph.getAreaCd();
		this.countryCd = ph.getCountryCd();
		this.description = ph.getDescription();
		this.isActive = ph.isActive;
		this.isDefault = ph.getIsDefault();
		this.name = ph.getName();
		this.phoneExt = ph.getPhoneExt();
		this.phoneNbr = ph.getPhoneNbr();
		this.phoneType = ph.getPhoneType();
	}
	
	// Property accessors
	public String getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getAreaCd() {
		return this.areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getCountryCd() {
		return this.countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNbr() {
		return this.phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	public String getPhoneExt() {
		return this.phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
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
/*	public boolean equals(Object adr) {
		
		Phone newAdr = (Phone)adr;
		
		if (this == newAdr)
			return true;
		if (newAdr == null || newAdr.getClass() != this.getClass())
			return false;
		
		return ( (phoneId == newAdr.getPhoneId() || 
				 ( phoneId != null && phoneId.equals(newAdr.getPhoneId()))) && 
				 
				 (this.areaCd == newAdr.getAreaCd() ||  
				 ( areaCd != null && areaCd.equals(newAdr.getAreaCd()))) && 
				 
				 (this.countryCd == newAdr.getCountryCd() ||  
				 ( countryCd != null && countryCd.equals(newAdr.getCountryCd()))) && 
				 			
				 (this.description == newAdr.getDescription() ||  
				 ( description != null && description.equals(newAdr.getDescription()))) && 
				
				 (this.isDefault.intValue() == newAdr.isDefault.intValue() )  &&
				 
				 (this.parentId == newAdr.getParentId() ||  
  			     ( parentId != null && parentId.equals(newAdr.getParentId()))) && 
  			    		
 			    (this.parentType == newAdr.getParentType() ||  
				( parentType != null && parentType.equals(newAdr.getParentType()))) && 
 			    
				(this.phoneNbr == newAdr.getPhoneNbr() ||  
				( phoneNbr != null && phoneNbr.equals(newAdr.getPhoneNbr()))) && 
 			    
				(this.phoneExt == newAdr.getPhoneExt() ||  
				( phoneExt != null && phoneExt.equals(newAdr.getPhoneExt()))) 		) ; 
		
	}


	
	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getPhoneId() == null ? 0 : this.getPhoneId().hashCode());
		result = 37 * result
				+ (getAreaCd() == null ? 0 : this.getAreaCd().hashCode());
		result = 37 * result
				+ (getCountryCd() == null ? 0 : this.getCountryCd().hashCode());
		result = 37 * result
				+ (getPhoneExt() == null ? 0 : this.getPhoneExt().hashCode());
		result = 37 * result
				+ (getPhoneNbr() == null ? 0 : this.getPhoneNbr().hashCode());
		result = 37 * result
				+ (getDescription() == null ? 0 : this.getDescription().hashCode());
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

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public AttributeOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(AttributeOperationEnum operation) {
		this.operation = operation;
	}
	

	
}
