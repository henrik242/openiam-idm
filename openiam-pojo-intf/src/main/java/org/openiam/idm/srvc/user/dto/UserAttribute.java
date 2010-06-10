package org.openiam.idm.srvc.user.dto;

// Generated Jun 12, 2007 10:46:13 PM by Hibernate Tools 3.2.0.beta8


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.meta.dto.*;
import org.openiam.idm.srvc.role.dto.Role;

/**
 * UserAttribute represents an individual attribute that is associated with a user. A user may
* have many attributes. A UserAttribute should also be associated
 * with a MetadataElement. This approach is used as a way to extend the attributes associated with 
* a user without having to extend the schema.
*/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userAttribute", propOrder = {
    "id",
    "metadataElementId",
    "name",
    "userId",
    "value",
    "attrGroup",
    "operation"
})
public class UserAttribute implements java.io.Serializable{

	// Fields    

	//private User users;

	//private MetadataElement metadataElement;


	protected String id;
    protected String metadataElementId;
    protected String name;
    protected String userId;
    protected String value;    
	protected String attrGroup;
	protected AttributeOperationEnum operation;

	

	// Constructors

	/** default constructor */
	public UserAttribute() {
	}

	/** minimal constructor */
	public UserAttribute(String id) {
		this.id = id;
	}

	public UserAttribute(String id, String users,
						String metadataElement, String name, String value) {
		this.id = id;
		userId = users;
		metadataElementId = metadataElement;
		this.name = name;
		this.value = value;
	}
	
    @Override
	public boolean equals(Object obj) {
		if (this == obj) { 
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Group)) {
			return false;
		}
		
		UserAttribute compareAttr = (UserAttribute)obj;
		
		return this.attrGroup.equals(compareAttr.attrGroup) && 
		 	this.id.equals(compareAttr.id) && 
		 	this.name.equals(compareAttr.name) && 
		 	this.userId.equals(compareAttr.userId) && 
		 	this.value.equals(compareAttr.value) ;

	}
    
    public void updateUserAttribute(UserAttribute attr) {
    	this.attrGroup = attr.getAttrGroup();
    	this.metadataElementId = attr.getMetadataElementId();
    	this.name = attr.getName();
    	this.value = attr.getValue();
    }
	
	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	public String getMetadataElementId() {
		return metadataElementId;
	}

	public void setMetadataElementId(String metadataElementId) {
		this.metadataElementId = metadataElementId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAttrGroup() {
		return attrGroup;
	}

	public void setAttrGroup(String attrGroup) {
		this.attrGroup = attrGroup;
	}

	public AttributeOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(AttributeOperationEnum operation) {
		this.operation = operation;
	}



}
