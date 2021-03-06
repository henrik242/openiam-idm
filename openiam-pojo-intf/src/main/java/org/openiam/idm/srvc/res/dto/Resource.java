package org.openiam.idm.srvc.res.dto;

// Generated Mar 8, 2009 12:54:32 PM by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.BaseObject;

/**
 * Resources are items that need to be managed or protected. These can be both logic and physical in nature.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Resource", propOrder = {
    "resourceType",
    "resourceId",
    "name",
    "description",
    "resourceParent",
    "branchId",
    "categoryId",
    "displayOrder",
    "nodeLevel",
    "sensitiveApp",
    "managedSysId",
    "URL",
    "resourceRoles",
    "resourceProps",
    "childResources",
    "resourceGroups",
    "privileges"
})
public class Resource  extends BaseObject {

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
	private String managedSysId;
	private String URL;
	private Set<ResourceRole> resourceRoles = new HashSet<ResourceRole>(0);
	//private Set<ResourceUser> resourceUsers = new HashSet<ResourceUser>(0); // may lead to performance issues?
	//private Set<ResourcePolicy> resourcePolicies = new HashSet<ResourcePolicy>(0);
	private Set<ResourceProp> resourceProps = new HashSet<ResourceProp>(0); // defined as a Set in Hibernate map
	private Set<Resource> childResources = new HashSet<Resource>(0);


    //protected Boolean selected = new Boolean(false);
    private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>(0);

    private Set<PrivilegeDef> privileges = new HashSet<PrivilegeDef>(0);
    //private Set<UserPrivilege> userPrivileges = new HashSet<UserPrivilege>(0);

	
	public Resource() {
	}

	public Resource(String resourceId) {
		this.resourceId = resourceId;
	}


	
	public Resource(String resourceId, String name,
			String resourceType) {
		super();
		this.resourceId = resourceId;
		this.name = name;
		this.resourceType = new ResourceType(resourceType);
	}

	public Resource(String resourceId, ResourceType resourceType, String name,
			String description, String resourceParent, String branchId,
			String categoryId, Integer displayOrder, Integer nodeLevel,
			Integer sensitiveApp, 
			Set<ResourceRole> resourceRoles,
			Set<ResourceUser> resourceUsers,
			//Set<ResourcePolicy> resourcePolicies,
			Set<ResourceProp> resourceProps) {
		this.resourceId = resourceId;
		this.resourceType = resourceType;
		this.description = description;
		this.name = name;
		this.resourceParent = resourceParent;
		this.branchId = branchId;
		this.categoryId = categoryId;
		this.displayOrder = displayOrder;
		this.nodeLevel = nodeLevel;
		this.sensitiveApp = sensitiveApp;
		this.resourceRoles = resourceRoles;
		//this.resourceUsers = resourceUsers;
		//this.resourcePolicies = resourcePolicies;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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



	public Set<ResourceProp> getResourceProps() {
		return resourceProps;
	}

	public void setResourceProps(Set<ResourceProp> resourceProps) {
		this.resourceProps = resourceProps;
	}

    public ResourceProp getResourceProperty(String propName) {
        if (resourceProps == null) {
             return null;
        }
        for (ResourceProp prop  : resourceProps) {
            if (prop.getName().equalsIgnoreCase(propName)) {
                return prop;
            }
        }
        return null;
    }

	public Set<Resource> getChildResources() {
		return childResources;
	}

	public void setChildResources(Set<Resource> childResources) {
		this.childResources = childResources;

	}

	public String toString() {
		String str="resourceId=" + resourceId + 
			" resourceType=" + resourceType + 
			" name=" + name + 
			" description=" + description + 
			" resourceParent=" + resourceParent + 
			" resourceProps=" + resourceProps;
		
		return str;
	}

	public String getManagedSysId() {
		return managedSysId;
	}

	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}
	
	

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

    public Set<ResourceGroup> getResourceGroups() {
          return resourceGroups;
    }

    public void setResourceGroups(Set<ResourceGroup> resourceGroups) {
          this.resourceGroups = resourceGroups;
    }

    public Set<PrivilegeDef> getPrivileges() {
          return privileges;
    }

    public void setPrivileges(Set<PrivilegeDef> privileges) {
          this.privileges = privileges;
    }



}
