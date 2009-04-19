package org.openiam.idm.srvc.policy.dto;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>Policy</code> represents a policy object that is used by the policy service.
 */
public class Policy implements java.io.Serializable {

	private String policyId;
	private String policyDefId;
	private String name;
	private String description;
	private Integer status;
	private Date createDate;
	private String createdBy;
	private Date lastUpdate;
	private String lastUpdatedBy;
	private String rule;
	private Set<PolicyMembership> policyMemberships = new HashSet<PolicyMembership>(
			0);
	private Set<PolicyAttribute> policyAttributes = new HashSet<PolicyAttribute>(
			0);

	public Policy() {
	}

	public Policy(String policyId) {
		this.policyId = policyId;
	}

	public Policy(String policyId, String policyDef, String name,
			String description, Integer enablement, Date createDate,
			String createdBy, Date lastUpdate, String lastUpdatedBy,
			Set<PolicyMembership> policyMemberships,
			Set<PolicyAttribute> policyAttributes) {
		this.policyId = policyId;
		this.policyDefId = policyDef;
		this.name = name;
		this.description = description;
		this.status = enablement;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.policyMemberships = policyMemberships;
		this.policyAttributes = policyAttributes;
	}

	public String getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer enablement) {
		this.status = enablement;
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

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Set<PolicyMembership> getPolicyMemberships() {
		return this.policyMemberships;
	}

	public void setPolicyMemberships(Set<PolicyMembership> policyMemberships) {
		this.policyMemberships = policyMemberships;
	}

	public Set<PolicyAttribute> getPolicyAttributes() {
		return this.policyAttributes;
	}

	public void setPolicyAttributes(Set<PolicyAttribute> policyAttributes) {
		this.policyAttributes = policyAttributes;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getPolicyDefId() {
		return policyDefId;
	}

	public void setPolicyDefId(String policyDefId) {
		this.policyDefId = policyDefId;
	}

}
