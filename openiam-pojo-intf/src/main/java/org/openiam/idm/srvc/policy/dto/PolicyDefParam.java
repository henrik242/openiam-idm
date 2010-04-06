package org.openiam.idm.srvc.policy.dto;


import java.util.HashSet;
import java.util.Set;

/**
 * PolicyDefParam represent the parameters of a policy definition.
 */
public class PolicyDefParam implements java.io.Serializable {

	private String defParamId;
	private String policyDefId;
	private String name;
	private String description;
	private String operation;
	private String value1;
	private String value2;
	private Integer repeats;
	private String policyParamHandler;
	private String paramGroup;
	
	private Set<PolicyAttribute> policyAttributes = new HashSet<PolicyAttribute>(
			0);

	public PolicyDefParam() {
	}

	public PolicyDefParam(String defParamId) {
		this.defParamId = defParamId;
	}

	public PolicyDefParam(String defParamId, String policyDefId, String name,
			String description, String operation, String value1, String value2,
			Integer repeats, String policyParamHandler,
			Set<PolicyAttribute> policyAttributes) {
		this.defParamId = defParamId;
		this.policyDefId = policyDefId;
		this.name = name;
		this.description = description;
		this.operation = operation;
		this.value1 = value1;
		this.value2 = value2;
		this.repeats = repeats;
		this.policyParamHandler = policyParamHandler;
		this.policyAttributes = policyAttributes;
	}

	public String getDefParamId() {
		return this.defParamId;
	}

	public void setDefParamId(String defParamId) {
		this.defParamId = defParamId;
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

	public Integer getRepeats() {
		return this.repeats;
	}

	public void setRepeats(Integer repeats) {
		this.repeats = repeats;
	}

	public String getPolicyParamHandler() {
		return this.policyParamHandler;
	}

	public void setPolicyParamHandler(String policyParamHandler) {
		this.policyParamHandler = policyParamHandler;
	}

	public Set<PolicyAttribute> getPolicyAttributes() {
		return this.policyAttributes;
	}

	public void setPolicyAttributes(Set<PolicyAttribute> policyAttributes) {
		this.policyAttributes = policyAttributes;
	}

	public String getParamGroup() {
		return paramGroup;
	}

	public void setParamGroup(String paramGroup) {
		this.paramGroup = paramGroup;
	}

	public String getPolicyDefId() {
		return policyDefId;
	}

	public void setPolicyDefId(String policyDefId) {
		this.policyDefId = policyDefId;
	}

}
