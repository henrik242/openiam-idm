package org.openiam.idm.srvc.policy.dto;

// Generated Mar 7, 2009 11:47:12 AM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * PolicyDef represent a policy definition
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyDef", propOrder = {
    "policyDefId",
    "name",
    "description",
    "policyType",
    "locationType",
    "policyRule",
    "policyHandler",
    "policyAdviseHandler",
    "policyDefParams",
    "policies"
})
public class PolicyDef implements java.io.Serializable {

	private String policyDefId;
	private String name;
	private String description;
	private String policyType;
	private String locationType;
	private String policyRule;
	private String policyHandler;
	private String policyAdviseHandler;
	private Set<PolicyDefParam> policyDefParams = new HashSet<PolicyDefParam>(0);
	private Set<Policy> policies = new HashSet<Policy>(0);

	public PolicyDef() {
	}

	public PolicyDef(String policyDefId) {
		this.policyDefId = policyDefId;
	}

	public PolicyDef(String policyDefId, String name, String description,
			String policyType, String locationType, String policyRule,
			String policyHandler, String policyAdviseHandler,
			Set<PolicyDefParam> policyDefParams, Set<Policy> policies) {
		this.policyDefId = policyDefId;
		this.name = name;
		this.description = description;
		this.policyType = policyType;
		this.locationType = locationType;
		this.policyRule = policyRule;
		this.policyHandler = policyHandler;
		this.policyAdviseHandler = policyAdviseHandler;
		this.policyDefParams = policyDefParams;
		this.policies = policies;
	}

	public String getPolicyDefId() {
		return this.policyDefId;
	}

	public void setPolicyDefId(String policyDefId) {
		this.policyDefId = policyDefId;
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

	public String getPolicyType() {
		return this.policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getPolicyRule() {
		return this.policyRule;
	}

	public void setPolicyRule(String policyRule) {
		this.policyRule = policyRule;
	}

	public String getPolicyHandler() {
		return this.policyHandler;
	}

	public void setPolicyHandler(String policyHandler) {
		this.policyHandler = policyHandler;
	}

	public String getPolicyAdviseHandler() {
		return this.policyAdviseHandler;
	}

	public void setPolicyAdviseHandler(String policyAdviseHandler) {
		this.policyAdviseHandler = policyAdviseHandler;
	}

	public Set<PolicyDefParam> getPolicyDefParams() {
		return this.policyDefParams;
	}

	public void setPolicyDefParams(Set<PolicyDefParam> policyDefParams) {
		this.policyDefParams = policyDefParams;
	}

	public Set<Policy> getPolicies() {
		return this.policies;
	}

	public void setPolicies(Set<Policy> policies) {
		this.policies = policies;
	}

    @Override
    public String toString() {
        return "PolicyDef{" +
                "policyDefId='" + policyDefId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", policyType='" + policyType + '\'' +
                ", locationType='" + locationType + '\'' +
                ", policyRule='" + policyRule + '\'' +
                ", policyHandler='" + policyHandler + '\'' +
                ", policyAdviseHandler='" + policyAdviseHandler + '\'' +
                ", policyDefParams=" + policyDefParams +
                ", policies=" + policies +
                '}';
    }
}
