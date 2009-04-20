package org.openiam.idm.srvc.user.dto;

// Generated Jun 12, 2007 10:46:13 PM by Hibernate Tools 3.2.0.beta8


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.idm.srvc.meta.dto.*;

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
    "attrGroup"
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

	

	// Constructors

	/** default constructor */
	public UserAttribute() {
	}

	/** minimal constructor */
	public UserAttribute(String id) {
		this.id = id;
	}

	/** full constructor */
/*	public UserAttribute(String id, User users,
			MetadataElement metadataElement, String name, String value) {
		this.id = id;
		this.users = users;
		this.metadataElement = metadataElement;
		this.name = name;
		this.value = value;
	}
*/
	public UserAttribute(String id, String users,
						String metadataElement, String name, String value) {
		this.id = id;
		userId = users;
		metadataElementId = metadataElement;
		this.name = name;
		this.value = value;
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

}