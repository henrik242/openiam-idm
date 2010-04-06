package org.openiam.idm.srvc.res.service;

import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.dto.ResourceRoleId;
import org.openiam.idm.srvc.res.dto.ResourceType;
import org.openiam.idm.srvc.res.dto.ResourceUser;

@WebService(targetNamespace = "urn:idm.openiam.org/srvc/res/service", name = "ResourceDataWebService")
public interface ResourceDataService {

/*	ResourceDAO getResourceDao();

	void setResourceDao(ResourceDAO resourceDao);

	ResourceTypeDAO getResourceTypeDao();

	void setResourceTypeDao(ResourceTypeDAO resourceTypeDao);

	ResourcePropDAO getResourcePropDao();

	void setResourcePropDao(ResourcePropDAO resourcePropDao);
*/
	/**
	 * Gets the resource role dao.
	 *
	 * @return the resource role dao
	 */
//	ResourceRoleDAO getResourceRoleDao();

	/**
	 * Sets the resource role dao.
	 *
	 * @param resourceRoleDao the new resource role dao
	 */
//	void setResourceRoleDao(ResourceRoleDAO resourceRoleDao);

	//	
	// /**
	// * Gets the resource user dao.
	// *
	// * @return the resource user dao
	// */
	// public ResourceUserDAO getResourceUserDao() {
	// return resourceUserDao;
	// }
	//
	// /**
	// * Sets the resource user dao.
	// *
	// * @param resourceUserDao the new resource user dao
	// */
	// public void setResourceUserDao(ResourceUserDAO resourceUserDao) {
	// this.resourceUserDao = resourceUserDao;
	// }

	/**
	 * Add a new resource from a transient resource object and sets resourceId
	 * in the returned object.
	 * 
	 * @param resource
	 * @return
	 */
	Resource addResource(Resource resource);

	/**
	 * Find a resource.
	 * 
	 * @param resourceId
	 * 
	 * @return resource
	 */
	Resource getResource(String resourceId);

	/**
	 * Update a resource.
	 * 
	 * @param resource
	 * @return
	 */
	Resource updateResource(Resource resource);

	/**
	 * Find all resources
	 * 
	 * @return list of resources
	 */
	List<Resource> getAllResources();

	/**
	 * Remove a resource
	 * 
	 * @param resourceId
	 */
	void removeResource(String resourceId);

	/**
	 * Remove all resources
	 */
	int removeAllResources();

	/**
	 * Add a new resource type
	 * 
	 * @param resourceType
	 * @return
	 */
	ResourceType addResourceType(ResourceType val);

	/**
	 * Find a resource type
	 * 
	 * @param resourceTypeId
	 * @return
	 */
	ResourceType getResourceType(String resourceTypeId);

	/**
	 * Update a resource type
	 * 
	 * @param resourceType
	 * @return
	 */
	ResourceType updateResourceType(ResourceType resourceType);

	/**
	 * Find all resource types
	 * 
	 * @return
	 */
	List<ResourceType> getAllResourceTypes();

	/**
	 * Remove a resource type
	 * 
	 * @param resourceTypeId
	 */
	void removeResourceType(String resourceTypeId);

	/**
	 * Remove all resource types
	 * 
	 * @return
	 */
	int removeAllResourceTypes();

	/**
	 * Find type of a resource
	 * 
	 * @param resourceId
	 * @return
	 */
	ResourceType findTypeOfResource(String resourceId);

	/**
	 * Add a resource property.
	 * 
	 * @param resourceProp
	 * @return
	 */
	ResourceProp addResourceProp(ResourceProp resourceProp);

	/**
	 * Find a resource property.
	 * 
	 * @param resourcePropId
	 * @return
	 */
	ResourceProp getResourceProp(String resourcePropId);

	/**
	 * Update a resource property
	 * 
	 * @param resourceProp
	 */
	ResourceProp updateResourceProp(ResourceProp resourceProp);

	/**
	 * Find all resource properties
	 * 
	 * @return
	 */
	List<ResourceProp> getAllResourceProps();

	/**
	 * Remove a resource property
	 * 
	 * @param resourcePropId
	 */
	void removeResourceProp(String resourcePropId);

	/**
	 * Remove all resource properties
	 * 
	 * @param
	 */
	int removeAllResourceProps();

	/**
	 * Remove properties with a specified resourceId
	 * 
	 * @param resourceId
	 * @return
	 */
	int removePropertiesByResource(String resourceId);

	/**
	 * Find resource properties
	 * 
	 * @param resourceId
	 * @return
	 */
	List<ResourceProp> findResourceProperties(String resourceId);

	/**
	 * Find resource children
	 * 
	 * @param resourceId
	 * @return
	 */
	List<Resource> getChildResources(String resourceId);

	/**
	 * Find a resource and its descendants and return as nested List.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return list of nested lists of resource objects
	 */

	List<Resource> getResourceTree(String resourceId);

	/**
	 * Find a resource and its descendants and return as an xml tree.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return xml string
	 */
	String getResourceTreeXML(String resourceId);

	/**
	 * Find a resource and all its descendants and put them in a list.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return resource list
	 */

	List<Resource> getResourceFamily(String resourceId);

	/**
	 * Find resources having a specified metadata type
	 * 
	 * @param resourceTypeId
	 * @return
	 */
	List<Resource> getResourcesByType(String resourceTypeId);

	/**
	 * Find root resources i.e. resources with null or blank value for parent
	 * 
	 * @return
	 */
	List<Resource> getRootResources();

	/**
	 * Find all resources for a specified category.
	 * 
	 * @param categoryId
	 * @return
	 */
	List<Resource> getResourcesByCategory(String categoryId);

	/**
	 * Find all resources with a specified branch
	 * 
	 * @param branchId
	 * @return
	 */
	List<Resource> getResourcesByBranch(String branchId);

	/**
	 * Remove resources having a specified metadata type
	 * 
	 * @param resourceTypeId
	 * @return rows affected
	 */
	int removeResourcesByType(String resourceTypeId);

	/**
	 * Remove all resources for a specified category.
	 * 
	 * @param categoryId
	 * @return rows affected
	 */
	int removeResourcesByCategory(String categoryId);

	/**
	 * Remove all resources with a specified branch
	 * 
	 * @param branchId
	 * @return rows affected
	 */
	int removeResourcesByBranch(String branchId);

	/**
	 * Add a resource role
	 * @param resourceRole
	 * @return
	 */
	ResourceRole addResourceRole(ResourceRole resourceRole);

	/**
	 * Find resource role
	 * @param resourceRoleId
	 * @return
	 */
	ResourceRole getResourceRole(ResourceRoleId resourceRoleId);

	/**
	 * Update resource role.
	 * @param resourceRole
	 * @return
	 */
	ResourceRole updateResourceRole(ResourceRole resourceRole);

	/**
	 * Find all resource roles
	 * @return
	 */
	List<ResourceRole> getAllResourceRoles();

	/**
	 * Remove resource role.
	 * @param resourceRoleId
	 */
	void removeResourceRole(ResourceRoleId resourceRoleId);

	/**
	 * Remove all resource roles
	 *
	 */
	void removeAllResourceRoles();
	
	ResourceUser addUserToResource(ResourceUser user);
	List<ResourceUser> getUserResources(String userId);
	void removeUserFromAllResources(String userId);
	
	boolean isUserAuthorized(String userId, String resourceId);
	boolean isRoleAuthorized(String domanId, String roleId, String resourceId);
	
	List<ResourceRole> getResourcesForRole(String domainId, String roleId);
	
	/**
	 * Returns a list of Resource objects that are linked to a Role.
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	List<Resource> getResourceListForRole(String domainId, String roleId);
	
	/**
	 * Returns a list of Resource objects that are linked to the list of Roles.
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	List<Resource> getResourceForRoleList(String domainId, List<String> roleIdList);
	
	/**
	 * Return the AttributeMap for the specified resourceId
	 * @param resourceId
	 * @return
	 */
	List<AttributeMap> getAttributeMapForResource(String resourceId);
}