package org.openiam.idm.srvc.policy.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Dec 1, 2009 12:48:52 AM by Hibernate Tools 3.2.2.GA

/**
 * represents the level at which a policy is associated with other 
 * objects. Policy can be associated at the following levels: USER_CLASSIFICATION, USER_TYPE, RESOURCE, 
 * ORGANIZATION, SECURITY_DOMAIN, GLOBAL
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyObjectAssoc", propOrder = {
    "policyObjectId",
    "policyId",
    "associationLevel",
    "associationValue",
    "objectType",
    "objectId",
    "parentAssocId"
})
public class PolicyObjectAssoc implements java.io.Serializable {

	private String policyObjectId;
	private String policyId;
	private String associationLevel;
	private String associationValue;
	private String objectType;
	private String objectId;
	private String parentAssocId;

	public PolicyObjectAssoc() {
	}

	public String getPolicyObjectId() {
		return policyObjectId;
	}

	public void setPolicyObjectId(String policyObjectId) {
		this.policyObjectId = policyObjectId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getAssociationLevel() {
		return associationLevel;
	}

	public void setAssociationLevel(String associationLevel) {
		this.associationLevel = associationLevel;
	}

	public String getAssociationValue() {
		return associationValue;
	}

	public void setAssociationValue(String associationValue) {
		this.associationValue = associationValue;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getParentAssocId() {
		return parentAssocId;
	}

	public void setParentAssocId(String parentAssocId) {
		this.parentAssocId = parentAssocId;
	}


}
