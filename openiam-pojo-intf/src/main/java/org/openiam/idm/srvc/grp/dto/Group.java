package org.openiam.idm.srvc.grp.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.List;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.role.dto.Role;

/**
 * <code>Group</code> is used to represent groups in the IAM system. Groups are frequently modeled 
 * after an organizations structure and represent a way to associate users together so that we don't
 * have to assign policies to individual users.  
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Group", propOrder = {
    "roles",
    "attributes",
    "companyId",
    "createDate",
    "createdBy",
    "description",
    "groupClass",
    "grpId",
    "grpName",
    "inheritFromParent",
    "lastUpdate",
    "lastUpdatedBy",
    "parentGrpId",
    "provisionMethod",
    "provisionObjName",
    "status",
    "subGroup",
    "metadataTypeId",
    "selected",
    "ownerId",
    "internalGroupId",
    "operation"
})

public class Group implements java.io.Serializable {

	// Fields    

	/**
	 * 
	 */
	private static final long serialVersionUID = 7657568959406790313L;
	
	protected AttributeOperationEnum operation;
	
	protected String grpId;
	protected String grpName;
    @XmlSchemaType(name = "dateTime")
	protected Date createDate;
	protected String createdBy;
	protected String companyId;
	protected String ownerId;
	protected String parentGrpId;
	protected Boolean inheritFromParent;
	protected String provisionMethod;
	protected String provisionObjName;
	protected String groupClass;
	protected String description;

	protected String status;
    @XmlSchemaType(name = "dateTime")
	protected Date lastUpdate;
	protected String lastUpdatedBy;
	protected String metadataTypeId;
	protected String internalGroupId = null;
	private Boolean selected = new Boolean(false);
	

	@XmlJavaTypeAdapter(org.openiam.idm.srvc.role.dto.RoleSetAdapter.class)
	protected Set<org.openiam.idm.srvc.role.dto.Role> roles = new HashSet<org.openiam.idm.srvc.role.dto.Role>(0);
	
	@XmlJavaTypeAdapter(org.openiam.idm.srvc.grp.dto.GroupAttributeMapAdapter.class) 
	protected Map<String, org.openiam.idm.srvc.grp.dto.GroupAttribute> attributes = new HashMap<String, GroupAttribute>(0);
	
	protected List<Group> subGroup = new ArrayList<Group>(0);
	
	
	// Constructors

	/** default constructor */
	public Group() {
	}

	/** minimal constructor */
	public Group(String grpId) {
		this.grpId = grpId;
	}

	/** full constructor */
	
	public Group(String grpId, String grpName, Date createDate, String createdBy,
			String companyId, String parentGrpId, boolean inheritFromParent,
			String provisionMethod, String provisionObjName, Set<Role> roles) {
		this.grpId = grpId;
		this.grpName = grpName;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.companyId = companyId;
		this.parentGrpId = parentGrpId;
		this.inheritFromParent = inheritFromParent;
		this.provisionMethod = provisionMethod;
		this.provisionObjName = provisionObjName;
		this.roles = roles;
	}
	

	// Property accessors
	public String getGrpId() {
		return this.grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return this.grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getParentGrpId() {
		return this.parentGrpId;
	}

	public void setParentGrpId(String parentGrpId) {
		this.parentGrpId = parentGrpId;
	}

	public Boolean getInheritFromParent() {
		return this.inheritFromParent;
	}

	public void setInheritFromParent(Boolean inheritFromParent) {
		this.inheritFromParent = inheritFromParent;
	}

	public String getProvisionMethod() {
		return this.provisionMethod;
	}

	public void setProvisionMethod(String provisionMethod) {
		this.provisionMethod = provisionMethod;
	}

	public String getProvisionObjName() {
		return this.provisionObjName;
	}

	public void setProvisionObjName(String provisionObjName) {
		this.provisionObjName = provisionObjName;
	}


	public Set<org.openiam.idm.srvc.role.dto.Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<org.openiam.idm.srvc.role.dto.Role> roles) {
		this.roles = roles;
	}


	public Map<String, GroupAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, GroupAttribute> attributes) {
		this.attributes = attributes;
	}
		
	/**
	 * Updates the underlying collection with the GroupAttribute object that is being passed in.
	 * The attribute is added if its does not exist and updated if its does exist.
	 * @param attr
	 */
	
	public void saveAttribute(GroupAttribute attr) {
		attributes.put(attr.getName(),attr); 
	}
	
	/**
	 * Removes the attribute object from the underlying collection.
	 * @param attr
	 */
	public void removeAttributes(GroupAttribute attr) {
		attributes.remove(attr.getName());
	}
	
	/**
	 * Returns the attribute object that is specified by the NAME parameter. 
	 * @param name - The attribute map is keyed on the NAME property.
	 * @return
	 */
	public GroupAttribute getAttribute(String name) {
	
		return attributes.get(name);

	}

	/**
	 * Returns a list of sub groups that may be associate with this group.  Returns null 
	 * if no sub groups are found.
	 * @return
	 */
	public List<Group> getSubGroup() {
		return subGroup;
	}

	/**
	 * Adds a list of sub groups to the current group.
	 * @param subGroup
	 */
	public void setSubGroup(List<Group> subGroup) {
		this.subGroup = subGroup;
	}

	public String getGroupClass() {
		return groupClass;
	}

	public void setGroupClass(String groupClass) {
		this.groupClass = groupClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	

	public String getMetadataTypeId() {
		return metadataTypeId;
	}

	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}



	public String toString() {
		String str = "grpId=" + grpId + 
					" grpName=" + grpName + 
					" status=" + status + 
					" description=" + description +
					" parentId=" + parentGrpId + 
					" subGroup=" + subGroup + 
					" attributes=" + attributes;
		
		return str;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}


	public void setGroupStatus(GroupStatus status) {
		this.status = status.toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInternalGroupId() {
		return internalGroupId;
	}

	public void setInternalGroupId(String internalGroupId) {
		this.internalGroupId = internalGroupId;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public AttributeOperationEnum getOperation() {
		return operation;
	}

	public void setOperation(AttributeOperationEnum operation) {
		this.operation = operation;
	}


}
