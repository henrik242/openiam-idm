package org.openiam.idm.srvc.res.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

/**
 * ResourceUserId is the primary key to identify a resource user.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceUserId", propOrder = {
    "resourceId",
    "userId",
    "privilegeId"
})
public class ResourceUserId implements java.io.Serializable {

	private String resourceId;
	private String userId;
	private String privilegeId;

	public ResourceUserId() {
	}

	public ResourceUserId(String resourceId, String userId, String privilegeId) {
		this.resourceId = resourceId;
		this.userId = userId;
		this.privilegeId = privilegeId;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		if (!(other instanceof ResourceUserId))
			return false;
		ResourceUserId castOther = (ResourceUserId) other;

		return ((this.getResourceId() == castOther.getResourceId()) || (this
				.getResourceId() != null
				&& castOther.getResourceId() != null && this.getResourceId()
				.equals(castOther.getResourceId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())))
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
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getPrivilegeId() == null ? 0 : this.getPrivilegeId()
						.hashCode());
		return result;
	}

}
