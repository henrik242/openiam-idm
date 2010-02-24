
package org.openiam.idm.srvc.role.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openiam.base.AttributeOperationEnum;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupSet;


/**
 * <p>Java class for role complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="role">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groups" type="{urn:idm.openiam.org/srvc/grp/dto}groupSet" minOccurs="0"/>
 *         &lt;element name="id" type="{urn:idm.openiam.org/srvc/role/dto}roleId" minOccurs="0"/>
 *         &lt;element name="provisionObjName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentRoleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleAttributes" type="{urn:idm.openiam.org/srvc/role/dto}roleAttributeSet" minOccurs="0"/>
 *         &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociationMethod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="users" type="{urn:idm.openiam.org/srvc/user/dto}userSet" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "role", propOrder = {
    "createDate",
    "createdBy",
    "description",
    "groups",
    "id",
    "provisionObjName",
    "parentRoleId",
    "roleAttributes",
    "roleName",
    "userAssociationMethod",
    "metadataTypeId",
    "ownerId",
    "inheritFromParent",
    "status",
    "childRoles",
    "selected",
    "internalRoleId",
    "operation"
})
public class Role implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3903402630611423082L;
	
	protected AttributeOperationEnum operation;
	
	@XmlSchemaType(name = "dateTime")
 	protected Date  createDate;
    protected String createdBy;
    protected String description;
  	@XmlJavaTypeAdapter(org.openiam.idm.srvc.grp.dto.GroupSetAdapter.class) 
	protected Set<Group> groups =	new HashSet<Group>(0);    
    protected RoleId id;
    protected String provisionObjName;
    protected String parentRoleId;
	@XmlJavaTypeAdapter(org.openiam.idm.srvc.role.dto.RoleAttributeSetAdapter.class)
	public Set<RoleAttribute> roleAttributes =	new HashSet<RoleAttribute>(0);
    protected String roleName;
    protected int userAssociationMethod = RoleConstant.UN_ASSIGNED;
    
    protected String status;
	protected Boolean selected = new Boolean(false);

	
    /*
    @XmlJavaTypeAdapter(org.openiam.idm.srvc.user.dto.UserSetAdapter.class)
	protected Set<org.openiam.idm.srvc.user.dto.User> users = 	new HashSet<User>(0);
	*/
	protected String metadataTypeId;
    
	protected String ownerId;
	protected Integer inheritFromParent;
	protected String internalRoleId;
	protected List<Role> childRoles = new ArrayList<Role>(0);
	
    public Role() {
    }

	
    public Role(RoleId id) {
        this.id = id;
    }
    

    

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(Date value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link GroupSet }
     *     
     */
    public Set<org.openiam.idm.srvc.grp.dto.Group> getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupSet }
     *     
     */
    public void setGroups(Set<org.openiam.idm.srvc.grp.dto.Group> value) {
        this.groups = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link RoleId }
     *     
     */
    public RoleId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleId }
     *     
     */
    public void setId(RoleId value) {
        this.id = value;
    }

    /**
     * Gets the value of the provisionObjName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvisionObjName() {
        return provisionObjName;
    }

    /**
     * Sets the value of the provisionObjName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvisionObjName(String value) {
        this.provisionObjName = value;
    }

    /**
     * Gets the value of the parentRoleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentRoleId() {
        return parentRoleId;
    }

    /**
     * Sets the value of the parentRoleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentRoleId(String value) {
        this.parentRoleId = value;
    }

  /**
     * Gets the value of the roleAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link RoleAttributeSet }
     *     
     */
    public Set<org.openiam.idm.srvc.role.dto.RoleAttribute> getRoleAttributes() {
        return roleAttributes;
    }

    /**
     * Sets the value of the roleAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleAttributeSet }
     *     
     */
    public void setRoleAttributes(Set<org.openiam.idm.srvc.role.dto.RoleAttribute> value) {
        this.roleAttributes = value;
    }

    /**
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the userAssociationMethod property.
     * 
     */
    public int getUserAssociationMethod() {
        return userAssociationMethod;
    }

    /**
     * Sets the value of the userAssociationMethod property.
     * 
     */
    public void setUserAssociationMethod(int value) {
        this.userAssociationMethod = value;
    }


	public String getMetadataTypeId() {
		return metadataTypeId;
	}


	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}


	public String getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}


	public Integer getInheritFromParent() {
		return inheritFromParent;
	}


	public void setInheritFromParent(Integer inheritFromParent) {
		this.inheritFromParent = inheritFromParent;
	}

    public String toString() {
    	String str = "id=" + id + 
    		" name=" + roleName + 
    		" metadataTypeId=" + metadataTypeId + 
    		" ownerId=" + ownerId + 
    		" inheritFromParent=" + this.inheritFromParent + 
    		" parentRoleId=" + parentRoleId + 
    		" childRole=" + childRoles;
    	return str;
    	
    }


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	public void setRoleStatus(RoleStatus status) {
		this.status = status.toString();
	}


	public List<Role> getChildRoles() {
		return childRoles;
	}


	public void setChildRoles(List<Role> childRoles) {
		this.childRoles = childRoles;
	}


	public String getInternalRoleId() {
		return internalRoleId;
	}


	public void setInternalRoleId(String internalRoleId) {
		this.internalRoleId = internalRoleId;
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
