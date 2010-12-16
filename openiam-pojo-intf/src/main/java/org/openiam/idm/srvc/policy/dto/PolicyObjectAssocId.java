package org.openiam.idm.srvc.policy.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Dec 1, 2009 12:48:52 AM by Hibernate Tools 3.2.2.GA

/**
 * PolicyObjectAssocId represents the level at which a policy is associated with other 
 * objects. Policy can be associated at the following levels: USER_TYPE, USER_CLASSIFICATION, RESOURCE, 
 * ORGANIZATION, SECURITY_DOMAIN, GLOBAL
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyObjectAssocId", propOrder = {
    "policyObjectId",
    "policyId",
    "associationLevel",
    "associationValue",
    "objectType",
    "objectId",
    "parentAssocId"
})
public class PolicyObjectAssocId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2391148522964349978L;
	private String policyObjectId;
	private String policyId;
	private String associationLevel;
	private String associationValue;
	private String objectType;
	private String objectId;
	private String parentAssocId;

	public PolicyObjectAssocId() {
	}

	public PolicyObjectAssocId(String associationValue) {
		this.associationValue = associationValue;
	}

	public PolicyObjectAssocId(String policyObjectId, String policyId,
			String associationLevel, String associationValue,
			String objectType, String objectId, String parentAssocId) {
		this.policyObjectId = policyObjectId;
		this.policyId = policyId;
		this.associationLevel = associationLevel;
		this.associationValue = associationValue;
		this.objectType = objectType;
		this.objectId = objectId;
		this.parentAssocId = parentAssocId;
	}

	public String getPolicyObjectId() {
		return this.policyObjectId;
	}

	public void setPolicyObjectId(String policyObjectId) {
		this.policyObjectId = policyObjectId;
	}

	public String getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getAssociationLevel() {
		return this.associationLevel;
	}

	public void setAssociationLevel(String associationLevel) {
		this.associationLevel = associationLevel;
	}

	public String getAssociationValue() {
		return this.associationValue;
	}

	public void setAssociationValue(String associationValue) {
		this.associationValue = associationValue;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getParentAssocId() {
		return this.parentAssocId;
	}

	public void setParentAssocId(String parentAssocId) {
		this.parentAssocId = parentAssocId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PolicyObjectAssocId))
			return false;
		PolicyObjectAssocId castOther = (PolicyObjectAssocId) other;

		return ((this.getPolicyObjectId() == castOther.getPolicyObjectId()) || (this
				.getPolicyObjectId() != null
				&& castOther.getPolicyObjectId() != null && this
				.getPolicyObjectId().equals(castOther.getPolicyObjectId())))
				&& ((this.getPolicyId() == castOther.getPolicyId()) || (this
						.getPolicyId() != null
						&& castOther.getPolicyId() != null && this
						.getPolicyId().equals(castOther.getPolicyId())))
				&& ((this.getAssociationLevel() == castOther
						.getAssociationLevel()) || (this.getAssociationLevel() != null
						&& castOther.getAssociationLevel() != null && this
						.getAssociationLevel().equals(
								castOther.getAssociationLevel())))
				&& ((this.getAssociationValue() == castOther
						.getAssociationValue()) || (this.getAssociationValue() != null
						&& castOther.getAssociationValue() != null && this
						.getAssociationValue().equals(
								castOther.getAssociationValue())))
				&& ((this.getObjectType() == castOther.getObjectType()) || (this
						.getObjectType() != null
						&& castOther.getObjectType() != null && this
						.getObjectType().equals(castOther.getObjectType())))
				&& ((this.getObjectId() == castOther.getObjectId()) || (this
						.getObjectId() != null
						&& castOther.getObjectId() != null && this
						.getObjectId().equals(castOther.getObjectId())))
				&& ((this.getParentAssocId() == castOther.getParentAssocId()) || (this
						.getParentAssocId() != null
						&& castOther.getParentAssocId() != null && this
						.getParentAssocId()
						.equals(castOther.getParentAssocId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPolicyObjectId() == null ? 0 : this.getPolicyObjectId()
						.hashCode());
		result = 37 * result
				+ (getPolicyId() == null ? 0 : this.getPolicyId().hashCode());
		result = 37
				* result
				+ (getAssociationLevel() == null ? 0 : this
						.getAssociationLevel().hashCode());
		result = 37
				* result
				+ (getAssociationValue() == null ? 0 : this
						.getAssociationValue().hashCode());
		result = 37
				* result
				+ (getObjectType() == null ? 0 : this.getObjectType()
						.hashCode());
		result = 37 * result
				+ (getObjectId() == null ? 0 : this.getObjectId().hashCode());
		result = 37
				* result
				+ (getParentAssocId() == null ? 0 : this.getParentAssocId()
						.hashCode());
		return result;
	}

}
