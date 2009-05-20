package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Resources are items that need to be managed or protected. These can be both logic and physical in nature.
 */
public class Resource implements java.io.Serializable {

	private String resourceId;
	private ResourceType resourceType;
	private String name;
	private String description;
	private String resourceParent;
	private String branchId;
	private String categoryId;
	private Integer displayOrder;
	private Integer nodeLevel;
	private Integer sensitiveApp;
	private Set<ResourceRole> resourceRoles = new HashSet<ResourceRole>(0);
	private Set<ResourceUser> resourceUsers = new HashSet<ResourceUser>(0);
	private Set<ResourcePolicy> resourcePolicies = new HashSet<ResourcePolicy>(0);
	private Map<String,ResourceProp> resourceProps = new HashMap<String,ResourceProp>(0);

	public Resource() {
	}

	public Resource(String resourceId) {
		this.resourceId = resourceId;
	}


	
	public Resource(String resourceId, String resourceName,
			String resourceType) {
		super();
		this.resourceId = resourceId;
		this.name = resourceName;
		this.resourceType = new ResourceType(resourceType);
	}

	public Resource(String resourceId, ResourceType resourceType,
			String description, String resourceParent, String branchId,
			String categoryId, Integer displayOrder, Integer nodeLevel,
			Integer sensitiveApp, Set<ResourceRole> resourceRoles,
			Set<ResourceUser> resourceUsers,
			Set<ResourcePolicy> resourcePolicies,
			Map<String,ResourceProp> resourceProps) {
		this.resourceId = resourceId;
		this.resourceType = resourceType;
		this.description = description;
		this.resourceParent = resourceParent;
		this.branchId = branchId;
		this.categoryId = categoryId;
		this.displayOrder = displayOrder;
		this.nodeLevel = nodeLevel;
		this.sensitiveApp = sensitiveApp;
		this.resourceRoles = resourceRoles;
		this.resourceUsers = resourceUsers;
		this.resourcePolicies = resourcePolicies;
		this.resourceProps = resourceProps;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public ResourceType getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceParent() {
		return this.resourceParent;
	}

	public void setResourceParent(String resourceParent) {
		this.resourceParent = resourceParent;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getNodeLevel() {
		return this.nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public Integer getSensitiveApp() {
		return this.sensitiveApp;
	}

	public void setSensitiveApp(Integer sensitiveApp) {
		this.sensitiveApp = sensitiveApp;
	}

	public Set<ResourceRole> getResourceRoles() {
		return this.resourceRoles;
	}

	public void setResourceRoles(Set<ResourceRole> resourceRoles) {
		this.resourceRoles = resourceRoles;
	}

	public Set<ResourceUser> getResourceUsers() {
		return this.resourceUsers;
	}

	public void setResourceUsers(Set<ResourceUser> resourceUsers) {
		this.resourceUsers = resourceUsers;
	}

	public Set<ResourcePolicy> getResourcePolicies() {
		return this.resourcePolicies;
	}

	public void setResourcePolicies(Set<ResourcePolicy> resourcePolicies) {
		this.resourcePolicies = resourcePolicies;
	}





	public Map<String, ResourceProp> getResourceProps() {
		return resourceProps;
	}

	public void setResourceProps(Map<String, ResourceProp> resourceProps) {
		this.resourceProps = resourceProps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
