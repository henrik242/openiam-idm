package org.openiam.idm.srvc.res.service;

import java.util.List;

import javax.jws.WebService;

//import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
//import org.openiam.idm.srvc.mngsys.service.AttributeMapDAO;
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
 * @param resource
 *            the resource
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
	 *            the resource id
	 * @return resource
	 */
	Resource getResource(String resourceId);

    /**
     * Find resources by resource name.
     * Then pick the first one and return that value
     * Use getResourcesByName to return the full list
     * of resources for a name
     *
     * @return resource
     */
 	Resource getResourceByName(String resourceName);


    /**
     * Find resources by resource name.
     *
     * @return list of resources
     */
    List<Resource> getResourcesByName(String resourceName);

    /**
       * Find resources by example
       *
       * @return list of resources
       */
     List<Resource> getResourcesByExample(Resource resource);
    /**
     * Find resources which have a specified property
     *
     * @param propName
     * @param propValue
     * @return
     */
    List<Resource> getResourcesByProperty (String propName, String propValue);
    /**
      * Find resource which has a specified set of unique properties
      *
      * @param propList
      * @return
      */
     Resource getResourceByProperties(List<ResourceProp> propList);
	/**
	 * Update a resource.
	 * 
	 * @param resource
	 *            the resource
	 * @return the resource
	 */
	Resource updateResource(Resource resource);

	/**
	 * Find all resources.
	 * 
	 * @return list of resources
	 */
	List<Resource> getAllResources();

	/**
	 * Remove a resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 */
	void removeResource(String resourceId);

	/**
	 * Remove all resources.
	 * 
	 * @return the int
	 */
	int removeAllResources();

	/**
	 * Add a new resource type.
	 * 
	 * @param val
	 *            the val
	 * @return the resource type
	 */
	ResourceType addResourceType(ResourceType val);

	/**
	 * Find a resource type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 * @return the resource type
	 */
	ResourceType getResourceType(String resourceTypeId);

	/**
	 * Update a resource type.
	 * 
	 * @param resourceType
	 *            the resource type
	 * @return the resource type
	 */
	ResourceType updateResourceType(ResourceType resourceType);

	/**
	 * Find all resource types.
	 * 
	 * @return the all resource types
	 */
	List<ResourceType> getAllResourceTypes();

	/**
	 * Remove a resource type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 */
	void removeResourceType(String resourceTypeId);

	/**
	 * Remove all resource types.
	 * 
	 * @return the int
	 */
	int removeAllResourceTypes();

	/**
	 * Find type of a resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the resource type
	 */
	ResourceType findTypeOfResource(String resourceId);

	/**
	 * Add a resource property.
	 * 
	 * @param resourceProp
	 *            the resource prop
	 * @return the resource prop
	 */
	ResourceProp addResourceProp(ResourceProp resourceProp);

	/**
	 * Find a resource property.
	 * 
	 * @param resourcePropId
	 *            the resource prop id
	 * @return the resource prop
	 */
	ResourceProp getResourceProp(String resourcePropId);

	/**
	 * Update a resource property.
	 * 
	 * @param resourceProp
	 *            the resource prop
	 * @return the resource prop
	 */
	ResourceProp updateResourceProp(ResourceProp resourceProp);

	/**
	 * Find all resource properties.
	 * 
	 * @return the all resource props
	 */
	List<ResourceProp> getAllResourceProps();

	/**
	 * Remove a resource property.
	 * 
	 * @param resourcePropId
	 *            the resource prop id
	 */
	void removeResourceProp(String resourcePropId);

	/**
	 * Remove all resource properties.
	 * 
	 * @return the int
	 */
	int removeAllResourceProps();

	/**
	 * Remove properties with a specified resourceId.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the int
	 */
	int removePropertiesByResource(String resourceId);

	/**
	 * Find resource properties.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the list
	 */
	List<ResourceProp> findResourceProperties(String resourceId);

	/**
	 * Find resource children.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the child resources
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
	 * Find resources having a specified metadata type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 * @return the resources by type
	 */
	List<Resource> getResourcesByType(String resourceTypeId);

	/**
	 * Find root resources i.e. resources with null or blank value for parent
	 * 
	 * @return the root resources
	 */
	List<Resource> getRootResources();

	/**
	 * Find all resources for a specified category.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return the resources by category
	 */
	List<Resource> getResourcesByCategory(String categoryId);

	/**
	 * Find all resources with a specified branch.
	 * 
	 * @param branchId
	 *            the branch id
	 * @return the resources by branch
	 */
	List<Resource> getResourcesByBranch(String branchId);

	/**
	 * Remove resources having a specified metadata type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 * @return rows affected
	 */
	int removeResourcesByType(String resourceTypeId);

	/**
	 * Remove all resources for a specified category.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return rows affected
	 */
	int removeResourcesByCategory(String categoryId);

	/**
	 * Remove all resources with a specified branch.
	 * 
	 * @param branchId
	 *            the branch id
	 * @return rows affected
	 */
	int removeResourcesByBranch(String branchId);

	/**
	 * Add a resource role.
	 * 
	 * @param resourceRole
	 *            the resource role
	 * @return the resource role
	 */
	ResourceRole addResourceRole(ResourceRole resourceRole);

	/**
	 * Find resource role.
	 * 
	 * @param resourceRoleId
	 *            the resource role id
	 * @return the resource role
	 */
	ResourceRole getResourceRole(ResourceRoleId resourceRoleId);

	/**
	 * Update resource role.
	 * 
	 * @param resourceRole
	 *            the resource role
	 * @return the resource role
	 */
	ResourceRole updateResourceRole(ResourceRole resourceRole);

	/**
	 * Find all resource roles.
	 * 
	 * @return the all resource roles
	 */
	List<ResourceRole> getAllResourceRoles();

	/**
	 * Remove resource role.
	 * 
	 * @param resourceRoleId
	 *            the resource role id
	 */
	void removeResourceRole(ResourceRoleId resourceRoleId);

	/**
	 * Remove all resource roles.
	 */
	void removeAllResourceRoles();
	
	/**
	 * Adds the user to resource.
	 * 
	 * @param user
	 *            the user
	 * @return the resource user
	 */
	ResourceUser addUserToResource(ResourceUser user);
	
	/**
	 * Gets the user resources.
	 * 
	 * @param userId
	 *            the user id
	 * @return the user resources
	 */
	List<ResourceUser> getUserResources(String userId);
	
	/**
	 * Removes the user from all resources.
	 * 
	 * @param userId
	 *            the user id
	 */
	void removeUserFromAllResources(String userId);
	
	/**
	 * Checks if is user authorized.
	 * 
	 * @param userId
	 *            the user id
	 * @param resourceId
	 *            the resource id
	 * @return true, if is user authorized
	 */
	boolean isUserAuthorized(String userId, String resourceId);

    boolean isUserAuthorizedByProperty(String userId, String propertyName, String propertyValue);
	
	/**
	 * Checks if is role authorized.
	 * 
	 * @param domanId
	 *            the doman id
	 * @param roleId
	 *            the role id
	 * @param resourceId
	 *            the resource id
	 * @return true, if is role authorized
	 */
	boolean isRoleAuthorized(String domanId, String roleId, String resourceId);
	
	
	/**
	 * Returns a list of Resource objects that are linked to a Role.
	 * 
	 * @param domainId
	 *            the domain id
	 * @param roleId
	 *            the role id
	 * @return the resources for role
	 */
	List<Resource> getResourcesForRole(String domainId, String roleId);
	
	/**
	 * Returns a list of Resource objects that are linked to the list of Roles.
	 * 
	 * @param domainId
	 *            the domain id
	 * @param roleIdList
	 *            the role id list
	 * @return the resources for roles
	 */
	List<Resource> getResourcesForRoles(String domainId, List<String> roleIdList);
	

    List<Resource> getResourceObjForUser(String userId);

	

	
	
}