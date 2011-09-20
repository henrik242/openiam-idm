package org.openiam.idm.srvc.policy.dto;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <code>Policy</code> represents a policy object that is used by the policy service.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Policy", propOrder = {
    "policyId",
    "policyDefId",
    "name",
    "description",
    "status",
    "createDate",
    "createdBy",
    "lastUpdate",
    "lastUpdatedBy",
    "rule",
    "ruleSrcUrl",
    "policyMemberships",
    "policyAttributes"
})
public class Policy implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5733143745301294956L;
	protected String policyId;
	protected String policyDefId;
	protected String name;
	protected String description;
	protected Integer status;
    @XmlSchemaType(name = "dateTime")
	protected Date createDate;
	protected String createdBy;
    @XmlSchemaType(name = "dateTime")
	protected Date lastUpdate;
	protected String lastUpdatedBy;
	protected String rule;
	protected String ruleSrcUrl;
	
	protected Set<PolicyMembership> policyMemberships = new HashSet<PolicyMembership>(
			0);
	protected Set<PolicyAttribute> policyAttributes = new HashSet<PolicyAttribute>(
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

	public PolicyAttribute getAttribute(String name) {
		for ( PolicyAttribute attr : policyAttributes) {
			if (attr.getName().equalsIgnoreCase(name)) {
				return attr;
			}
		}
		return null;
	}
	

	public String getPolicyDefId() {
		return policyDefId;
	}

	public void setPolicyDefId(String policyDefId) {
		this.policyDefId = policyDefId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRuleSrcUrl() {
		return ruleSrcUrl;
	}

	public void setRuleSrcUrl(String ruleSrcUrl) {
		this.ruleSrcUrl = ruleSrcUrl;
	}

    @Override
    public String toString() {
        return "Policy{" +
                "policyId='" + policyId + '\'' +
                ", policyDefId='" + policyDefId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", rule='" + rule + '\'' +
                ", ruleSrcUrl='" + ruleSrcUrl + '\'' +
                ", policyMemberships=" + policyMemberships +
                ", policyAttributes=" + policyAttributes +
                '}';
    }
}
