package org.openiam.idm.srvc.orgpolicy.dto;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * OrgPolicyAcceptance DTO.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgPolicyAcceptance", propOrder = {
		"orgPolicyId",
		"name",
		"targetAudienceType",
		"targetAudience",
		"startDate",
		"endDate",
		"policyText"
})
public class OrgPolicy implements java.io.Serializable {

	private String orgPolicyId;
	private String name;
	private String targetAudienceType;
	private String targetAudience;
	@XmlSchemaType(name = "dateTime")
	private Date startDate;
	@XmlSchemaType(name = "dateTime")
	private Date endDate;
	private String policyText;

	public OrgPolicy() {
	}

	public OrgPolicy(String orgPolicyId) {
		this.orgPolicyId = orgPolicyId;
	}

	public OrgPolicy(String orgPolicyId, String name,
			String targetAudienceType, String targetAudience, Date startDate,
			Date endDate, String policyText) {
		this.orgPolicyId = orgPolicyId;
		this.name = name;
		this.targetAudienceType = targetAudienceType;
		this.targetAudience = targetAudience;
		this.startDate = startDate;
		this.endDate = endDate;
		this.policyText = policyText;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetAudienceType() {
		return this.targetAudienceType;
	}

	public void setTargetAudienceType(String targetAudienceType) {
		this.targetAudienceType = targetAudienceType;
	}

	public String getTargetAudience() {
		return this.targetAudience;
	}

	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPolicyText() {
		return this.policyText;
	}

	public void setPolicyText(String policyText) {
		this.policyText = policyText;
	}

	public String getOrgPolicyId() {
		return orgPolicyId;
	}

	public void setOrgPolicyId(String orgPolicyId) {
		this.orgPolicyId = orgPolicyId;
	}

}
