package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * Object representing the association between a resource and a policy. 
 */
public class ResourcePolicy implements java.io.Serializable {

	private String resourcePolicyId;
	private Resource resources;
	private String roleId;
	private Date policyStart;
	private Date policyEnd;
	private Integer applyToChildren;

	public ResourcePolicy() {
	}

	public ResourcePolicy(String resourcePolicyId) {
		this.resourcePolicyId = resourcePolicyId;
	}

	public ResourcePolicy(String resourcePolicyId, Resource resources,
			String roleId, Date policyStart, Date policyEnd,
			Integer applyToChildren) {
		this.resourcePolicyId = resourcePolicyId;
		this.resources = resources;
		this.roleId = roleId;
		this.policyStart = policyStart;
		this.policyEnd = policyEnd;
		this.applyToChildren = applyToChildren;
	}

	public String getResourcePolicyId() {
		return this.resourcePolicyId;
	}

	public void setResourcePolicyId(String resourcePolicyId) {
		this.resourcePolicyId = resourcePolicyId;
	}

	public Resource getResources() {
		return this.resources;
	}

	public void setResources(Resource resources) {
		this.resources = resources;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getPolicyStart() {
		return this.policyStart;
	}

	public void setPolicyStart(Date policyStart) {
		this.policyStart = policyStart;
	}

	public Date getPolicyEnd() {
		return this.policyEnd;
	}

	public void setPolicyEnd(Date policyEnd) {
		this.policyEnd = policyEnd;
	}

	public Integer getApplyToChildren() {
		return this.applyToChildren;
	}

	public void setApplyToChildren(Integer applyToChildren) {
		this.applyToChildren = applyToChildren;
	}

}
