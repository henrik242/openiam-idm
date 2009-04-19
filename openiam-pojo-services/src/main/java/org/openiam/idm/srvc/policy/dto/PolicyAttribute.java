package org.openiam.idm.srvc.policy.dto;

// Generated Mar 7, 2009 11:47:12 AM by Hibernate Tools 3.2.2.GA

/**
 * PolicyAttribute is used to add additional attributes to a policy object.
 */
public class PolicyAttribute implements java.io.Serializable {

	private String policyAttrId;
	private Policy policy;
	private PolicyDefParam policyDefParam;
	private String name;
	private String operation;
	private String value1;
	private String value2;

	public PolicyAttribute() {
	}

	public PolicyAttribute(String policyAttrId) {
		this.policyAttrId = policyAttrId;
	}

	public PolicyAttribute(String policyAttrId, Policy policy,
			PolicyDefParam policyDefParam, String name, String operation,
			String value1, String value2) {
		this.policyAttrId = policyAttrId;
		this.policy = policy;
		this.policyDefParam = policyDefParam;
		this.name = name;
		this.operation = operation;
		this.value1 = value1;
		this.value2 = value2;
	}

	public String getPolicyAttrId() {
		return this.policyAttrId;
	}

	public void setPolicyAttrId(String policyAttrId) {
		this.policyAttrId = policyAttrId;
	}

	public Policy getPolicy() {
		return this.policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public PolicyDefParam getPolicyDefParam() {
		return this.policyDefParam;
	}

	public void setPolicyDefParam(PolicyDefParam policyDefParam) {
		this.policyDefParam = policyDefParam;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getValue1() {
		return this.value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return this.value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
