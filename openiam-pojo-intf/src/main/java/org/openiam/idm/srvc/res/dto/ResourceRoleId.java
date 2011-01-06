package org.openiam.idm.srvc.res.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceRoleId is the primary key used the identify the resource role combination
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceRoleId", propOrder = {
    "resourceId",
    "domainId",
    "roleId",
    "privilegeId"
})
public class ResourceRoleId implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 949995078674560248L;
	private String resourceId;
	private String domainId;
	private String roleId;
	private String privilegeId;

	public ResourceRoleId() {
	}



	public ResourceRoleId(String domainId, String privilegeId,
			String resourceId, String roleId) {
		super();
		this.domainId = domainId;
		this.privilegeId = privilegeId;
		this.resourceId = resourceId;
		this.roleId = roleId;
	}

	public String toString() {
		String str = "ResourceRoleId=> resourceId=" + resourceId + 
			" domainId=" + domainId +
			" roleId=" + roleId + 
			" privledgeId=" + privilegeId;
		return str;
	}


	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourceRoleId))
			return false;
		ResourceRoleId castOther = (ResourceRoleId) other;

		return ((this.getResourceId() == castOther.getResourceId()) || (this
				.getResourceId() != null
				&& castOther.getResourceId() != null && this.getResourceId()
				.equals(castOther.getResourceId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null
						&& castOther.getRoleId() != null && this.getRoleId()
						.equals(castOther.getRoleId())))
				&& ((this.getPrivilegeId() == castOther.getPrivilegeId()) || (this
						.getPrivilegeId() != null
						&& castOther.getPrivilegeId() != null && this
						.getPrivilegeId().equals(castOther.getPrivilegeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getResourceId() == null ? 0 : this.getResourceId()
						.hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37
				* result
				+ (getPrivilegeId() == null ? 0 : this.getPrivilegeId()
						.hashCode());
		return result;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

}
