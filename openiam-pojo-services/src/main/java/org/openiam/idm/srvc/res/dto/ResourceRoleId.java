package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceRoleId is the primary key used the identify the resource role combination
 */
public class ResourceRoleId implements java.io.Serializable {

	private String resourceId;
	private String roleId;
	private String privilegeId;

	public ResourceRoleId() {
	}

	public ResourceRoleId(String resourceId, String roleId, String privilegeId) {
		this.resourceId = resourceId;
		this.roleId = roleId;
		this.privilegeId = privilegeId;
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

}
