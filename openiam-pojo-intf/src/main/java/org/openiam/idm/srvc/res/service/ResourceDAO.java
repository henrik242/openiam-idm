package org.openiam.idm.srvc.res.service;

import java.util.List;

import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.dto.ResourceType;

public interface ResourceDAO {

	ResourceTypeDAO getResourceTypeDao();

	void setResourceTypeDao(ResourceTypeDAO resourceTypeDao);

	ResourcePropDAO getResourcePropDao();

	void setResourcePropDao(ResourcePropDAO resourcePropDao);

	ResourceRoleDAO getResourceRoleDao();

	void setResourceRoleDao(ResourceRoleDAO resourceRoleDao);

	void persist(Resource transientInstance);

	void remove(Resource persistentInstance);

	Resource update(Resource detachedInstance);

	Resource findById(java.lang.String id);

	List<Resource> findByExample(Resource instance);

	Resource add(Resource instance);

	List<Resource> findAllResources();

	int removeAllResources();

	ResourceType findTypeOfResource(String resourceId);

	int removePropertiesByResource(String resourceId);

	List<ResourceProp> findResourceProperties(String resourceId);

	List<Resource> getResourcesByType(String resourceTypeId);

	List<Resource> getResourcesByCategory(String categoryId);

	List<Resource> getResourcesByBranch(String branchId);

	List<Resource> getChildResources(String resourceId);

	List<Resource> getRootResources();

	int removeResourcesByType(String resourceTypeId);

	int removeResourcesByCategory(String categoryId);

	int removeResourcesByBranch(String branchId);

	List<ResourceRole> findResourceRoles(String resourceId);

	void addResourceRolePrivilege(String resourceId, String roleId,
			String privilegeId);

	void removeResourceRolePrivilege(String resourceId, String roleId,
			String privilegeId);

	int removeAllRolePrivilegesFromResource(String resourceId);
	
	/**
	 * List of Resources for a role
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceForRole(String domainId, String roleId);
	
	/**
	 * List of unique resources for the list of roles.
	 * @param domainId
	 * @param roleIdList
	 * @return
	 */
	public List<Resource> findResourceForRoleList(String domainId, List<String> roleIdList);
	

}