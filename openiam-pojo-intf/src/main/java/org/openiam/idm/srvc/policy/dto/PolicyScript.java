package org.openiam.idm.srvc.policy.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// Generated Mar 7, 2009 11:47:12 AM by Hibernate Tools 3.2.2.GA

/**
 * PolicyScript generated by hbm2java
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyScript", propOrder = {
    "policyScriptId",
    "resourcePolicyId",
    "categoryId",
    "sequenceId",
    "rule"
})
public class PolicyScript implements java.io.Serializable {

	private String policyScriptId;
	private String resourcePolicyId;
	private String categoryId;
	private Integer sequenceId;
	private String rule;

	public PolicyScript() {
	}

	public PolicyScript(String policyScriptId) {
		this.policyScriptId = policyScriptId;
	}

	public PolicyScript(String policyScriptId, String resourcePolicyId,
			String categoryId, Integer sequenceId, String rule) {
		this.policyScriptId = policyScriptId;
		this.resourcePolicyId = resourcePolicyId;
		this.categoryId = categoryId;
		this.sequenceId = sequenceId;
		this.rule = rule;
	}

	public String getPolicyScriptId() {
		return this.policyScriptId;
	}

	public void setPolicyScriptId(String policyScriptId) {
		this.policyScriptId = policyScriptId;
	}

	public String getResourcePolicyId() {
		return this.resourcePolicyId;
	}

	public void setResourcePolicyId(String resourcePolicyId) {
		this.resourcePolicyId = resourcePolicyId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
