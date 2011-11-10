package org.openiam.idm.srvc.res.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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

	/**
	 * Add a new resource from a transient resource object and sets resourceId
	 * in the returned object.
	 * 
	 * @param resource
	 * @return
	 */
    @WebMethod
	Resource addResource(
            @WebParam(name = "resource", targetNamespace = "")
            Resource resource);

	/**
	 * Find a resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return resource
	 */
    @WebMethod
	Resource getResource(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

    /**
     * Find resources by resource name.
     * Then pick the first one and return that value
     * Use getResourcesByName to return the full list
     * of resources for a name
     *
     * @return resource
     */
    @WebMethod
 	Resource getResourceByName(
            @WebParam(name = "resourceName", targetNamespace = "")
            String resourceName);


    /**
     * Find resources by resource name.
     *
     * @return list of resources
     */
    @WebMethod
    List<Resource> getResourcesByName(
            @WebParam(name = "resourceName", targetNamespace = "")
            String resourceName);

    /**
       * Find resources by example
       *
       * @return list of resources
       */
    @WebMethod
     List<Resource> getResourcesByExample(
            @WebParam(name = "resource", targetNamespace = "")
            Resource resource);
    /**
     * Find resources which have a specified property
     *
     * @param propName
     * @param propValue
     * @return list of resources
     */
    @WebMethod
    List<Resource> getResourcesByProperty (String propName, String propValue);
    /**
      * Find resource which has a specified set of unique properties
      *
      * @param propList
      * @return resource
      */
    @WebMethod
     Resource getResourceByProperties(
            @WebParam(name = "propList", targetNamespace = "")
            List<ResourceProp> propList);
	/**
	 * Update a resource.
	 * 
	 * @param resource
	 *            the resource
	 * @return the resource
	 */
    @WebMethod
	Resource updateResource(
            @WebParam(name = "resource", targetNamespace = "")
            Resource resource);

	/**
	 * Find all resources.
	 * 
	 * @return list of resources
	 */
    @WebMethod
	List<Resource> getAllResources();

	/**
	 * Remove a resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 */
    @WebMethod
	void removeResource(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Remove all resources.
	 * 
	 * @return the int count
	 */
    @WebMethod
	int removeAllResources();

	/**
	 * Add a new resource type.
	 * 
	 * @param resourceType
	 *            the resourceType
	 * @return the resource type
	 */
    @WebMethod
	ResourceType addResourceType(
            @WebParam(name = "resourceType", targetNamespace = "")
            ResourceType resourceType);

	/**
	 * Find a resource type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 * @return the resource type
	 */
    @WebMethod
	ResourceType getResourceType(
            @WebParam(name = "resourceTypeId", targetNamespace = "")
            String resourceTypeId);

	/**
	 * Update a resource type.
	 * 
	 * @param resourceType
	 *            the resource type
	 * @return the resource type
	 */
    @WebMethod
	ResourceType updateResourceType(
            @WebParam(name = "resourceType", targetNamespace = "")
            ResourceType resourceType);

	/**
	 * Find all resource types.
	 * 
	 * @return the all resource types
	 */
    @WebMethod
	List<ResourceType> getAllResourceTypes();

	/**
	 * Remove a resource type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 */
    @WebMethod
	void removeResourceType(
            @WebParam(name = "resourceTypeId", targetNamespace = "")
            String resourceTypeId);

	/**
	 * Remove all resource types.
	 * 
	 * @return the int count
	 */
    @WebMethod
	int removeAllResourceTypes();

	/**
	 * Find type of a resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the resource type
	 */
    @WebMethod
	ResourceType findTypeOfResource(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Add a resource property.
	 * 
	 * @param resourceProp
	 *            the resource prop
	 * @return the resource prop
	 */
    @WebMethod
	ResourceProp addResourceProp(
            @WebParam(name = "resourceProp", targetNamespace = "")
            ResourceProp resourceProp);

	/**
	 * Find a resource property.
	 * 
	 * @param resourcePropId
	 *            the resource prop id
	 * @return the resource prop
	 */
    @WebMethod
	ResourceProp getResourceProp(
            @WebParam(name = "resourcePropId", targetNamespace = "")
            String resourcePropId);

	/**
	 * Update a resource property.
	 * 
	 * @param resourceProp
	 *            the resource prop
	 * @return the resource prop
	 */
    @WebMethod
	ResourceProp updateResourceProp(
            @WebParam(name = "resourceProp", targetNamespace = "")
            ResourceProp resourceProp);

	/**
	 * Find all resource properties.
	 * 
	 * @return all resource props
	 */
    @WebMethod
	List<ResourceProp> getAllResourceProps();

	/**
	 * Remove a resource property.
	 * 
	 * @param resourcePropId
	 *            the resource prop id
	 */
    @WebMethod
	void removeResourceProp(
            @WebParam(name = "resourcePropId", targetNamespace = "")
            String resourcePropId);

	/**
	 * Remove all resource properties.
	 * 
	 * @return the int count
	 */
    @WebMethod
	int removeAllResourceProps();

	/**
	 * Remove all properties belonging a resource
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the int count
	 */
    @WebMethod
	int removePropertiesByResource(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Find resource properties
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the list of resources
	 */
    @WebMethod
	List<ResourceProp> findResourceProperties(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Find resource children.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the child resources
	 */
    @WebMethod
	List<Resource> getChildResources(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Find a resource and its descendants and return as nested List.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return list of nested lists of resource objects
	 */
    @WebMethod
	List<Resource> getResourceTree(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Find a resource and its descendants and return as an xml tree.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return xml string  of resource and its descendants
	 */
    @WebMethod
	String getResourceTreeXML(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Find a resource and all its descendants and put them in a list.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return resource list
	 */

    @WebMethod
	List<Resource> getResourceFamily(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

	/**
	 * Find resources having a specified metadata type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 * @return the resources by type
	 */
    @WebMethod
	List<Resource> getResourcesByType(
            @WebParam(name = "resourceTypeId", targetNamespace = "")
            String resourceTypeId);

	/**
	 * Find root resources i.e. resources with null or blank value for parent
	 * 
	 * @return the root resources
	 */
    @WebMethod
	List<Resource> getRootResources();

	/**
	 * Find all resources for a specified category.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return the resources by category
	 */
    @WebMethod
	List<Resource> getResourcesByCategory(
            @WebParam(name = "categoryId", targetNamespace = "")
            String categoryId);

	/**
	 * Find all resources with a specified branch.
	 * 
	 * @param branchId
	 *            the branch id
	 * @return the resources by branch
	 */
    @WebMethod
	List<Resource> getResourcesByBranch(
            @WebParam(name = "branchId", targetNamespace = "")
            String branchId);

	/**
	 * Remove resources having a specified metadata type.
	 * 
	 * @param resourceTypeId
	 *            the resource type id
	 * @return rows affected
	 */
    @WebMethod
	int removeResourcesByType(
            @WebParam(name = "resourceTypeId", targetNamespace = "")
            String resourceTypeId);

	/**
	 * Remove all resources for a specified category.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return rows affected
	 */
    @WebMethod
	int removeResourcesByCategory(
            @WebParam(name = "categoryId", targetNamespace = "")
            String categoryId);

	/**
	 * Remove all resources with a specified branch.
	 * 
	 * @param branchId
	 *            the branch id
	 * @return rows affected
	 */
    @WebMethod
	int removeResourcesByBranch(
            @WebParam(name = "branchId", targetNamespace = "")
            String branchId);

	/**
	 * Add a resource role.
	 * 
	 * @param resourceRole
	 *            the resource role
	 * @return the resource role
	 */
    @WebMethod
	ResourceRole addResourceRole(
            @WebParam(name = "resourceRole", targetNamespace = "")
            ResourceRole resourceRole);

	/**
	 * Find resource role.
	 * 
	 * @param resourceRoleId
	 *            the resource role id
	 * @return the resource role
	 */
    @WebMethod
	ResourceRole getResourceRole(
            @WebParam(name = "resourceRoleId", targetNamespace = "")
            ResourceRoleId resourceRoleId);

	/**
	 * Update resource role.
	 * 
	 * @param resourceRole
	 *            the resource role
	 * @return the resource role
	 */
    @WebMethod
	ResourceRole updateResourceRole(
            @WebParam(name = "resourceRole", targetNamespace = "")
            ResourceRole resourceRole);

	/**
	 * Find all resource roles.
	 * 
	 * @return the all resource roles
	 */
    @WebMethod
	List<ResourceRole> getAllResourceRoles();

	/**
	 * Remove resource role.
	 * 
	 * @param resourceRoleId
	 *            the resource role id
	 */
    @WebMethod
	void removeResourceRole(
            @WebParam(name = "resourceRoleId", targetNamespace = "")
            ResourceRoleId resourceRoleId);

	/**
	 * Remove all resource roles.
	 */
    @WebMethod
	void removeAllResourceRoles();
	
	/**
	 * Adds the user to resource.
	 * 
	 * @param user
	 *            the user
	 * @return the resource user
	 */
    @WebMethod
	ResourceUser addUserToResource(
            @WebParam(name = "user", targetNamespace = "")
            ResourceUser user);
	
	/**
	 * Gets the user resources.
	 * 
	 * @param userId
	 *            the user id
	 * @return the user resources
	 */
    @WebMethod
	List<ResourceUser> getUserResources(
            @WebParam(name = "userId", targetNamespace = "")
            String userId);
	
	/**
	 * Removes the user from all resources.
	 * 
	 * @param userId
	 *            the user id
	 */
    @WebMethod
	void removeUserFromAllResources(
            @WebParam(name = "userId", targetNamespace = "")
            String userId);
	
	/**
	 * Check if is user authorized.
	 * 
	 * @param userId
	 *            the user id
	 * @param resourceId
	 *            the resource id
	 * @return true, if is user authorized
	 */
    @WebMethod
	boolean isUserAuthorized(
            @WebParam(name = "userId", targetNamespace = "")
            String userId,
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

    /**
     * Check if user is authorized based on a resource's property
     * @param userId
     * @param propertyName
     * @param propertyValue
     * @return  <code>true</code> if user is authorized
     */
    @WebMethod
    boolean isUserAuthorizedByProperty(
            @WebParam(name = "userId", targetNamespace = "")
            String userId,
            @WebParam(name = "propertyName", targetNamespace = "")
            String propertyName,
            @WebParam(name = "propertyValue", targetNamespace = "")
            String propertyValue);
	
	/**
	 * Checks if is role authorized.
	 * 
	 * @param domainId
	 *            the domain id
	 * @param roleId
	 *            the role id
	 * @param resourceId
	 *            the resource id
	 * @return true if is role authorized
	 */
    @WebMethod
	boolean isRoleAuthorized(
            @WebParam(name = "domainId", targetNamespace = "")
            String domainId,
            @WebParam(name = "roleId", targetNamespace = "")
            String roleId,
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);
	
	
	/**
	 * Returns a list of Resource objects that are linked to a Role.
	 * 
	 * @param domainId
	 *            the domain id
	 * @param roleId
	 *            the role id
	 * @return the resources for role
	 */
    @WebMethod
	List<Resource> getResourcesForRole(
            @WebParam(name = "domainId", targetNamespace = "")
            String domainId,
            @WebParam(name = "roleId", targetNamespace = "")
            String roleId);
	
	/**
	 * Returns a list of Resource objects that are linked to the list of Roles.
	 * 
	 * @param domainId
	 *            the domain id
	 * @param roleIdList
	 *            the role id list
	 * @return the resources for roles
	 */

    @WebMethod
	List<Resource> getResourcesForRoles(
            @WebParam(name = "domainId", targetNamespace = "")
            String domainId,
            @WebParam(name = "roleIdList", targetNamespace = "")
            List<String> roleIdList);

    List<Resource> getResourceObjForUser(String userId);

}