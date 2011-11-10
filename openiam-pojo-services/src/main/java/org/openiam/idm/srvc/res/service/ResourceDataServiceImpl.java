package org.openiam.idm.srvc.res.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.exception.data.ObjectNotFoundException;
//import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
//import org.openiam.idm.srvc.mngsys.service.AttributeMapDAO;
import org.openiam.idm.srvc.res.dto.*;

@WebService(endpointInterface = "org.openiam.idm.srvc.res.service.ResourceDataService", targetNamespace = "urn:idm.openiam.org/srvc/res/service", portName = "ResourceDataWebServicePort", serviceName = "ResourceDataWebService")
public class ResourceDataServiceImpl implements ResourceDataService {

	protected ResourceDAO resourceDao;
	protected ResourceTypeDAO resourceTypeDao;
	protected ResourcePropDAO resourcePropDao;
	protected ResourceRoleDAO resourceRoleDao;
	protected ResourceUserDAO resourceUserDao;
	//protected AttributeMapDAO attributeMapDao;

	private static final Log log = LogFactory
			.getLog(ResourceDataServiceImpl.class);

	public ResourceDAO getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

	public ResourceTypeDAO getResourceTypeDao() {
		return resourceTypeDao;
	}

	public void setResourceTypeDao(ResourceTypeDAO resourceTypeDao) {
		this.resourceTypeDao = resourceTypeDao;
	}

	public ResourcePropDAO getResourcePropDao() {
		return resourcePropDao;
	}

	public void setResourcePropDao(ResourcePropDAO resourcePropDao) {
		this.resourcePropDao = resourcePropDao;
	}

	/**
	 * Gets the resource role dao.
	 * 
	 * @return the resource role dao
	 */
	public ResourceRoleDAO getResourceRoleDao() {
		return resourceRoleDao;
	}

	/**
	 * Sets the resource role dao.
	 * 
	 * @param resourceRoleDao
	 *            the new resource role dao
	 */
	public void setResourceRoleDao(ResourceRoleDAO resourceRoleDao) {
		this.resourceRoleDao = resourceRoleDao;
	}

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

	// Resource ---------------------------------------

	/**
	 * Add a new resource from a transient resource object and sets resourceId
	 * in the returned object.
	 * 
	 * @param resource
	 * @return
	 */
	public Resource addResource(Resource resource) {
		if (resource == null)
			throw new IllegalArgumentException("Resource object is null");

		return resourceDao.add(resource);
	}

	/**
	 * Add a new resource from a transient resource object. Sets resourceId and
	 * associates resource with a resource type. Sets category and branch from
	 * parent.
	 * 
	 * @param resource
	 * @param resourceTypeId
	 * @return
	 */
	// public Resource addChildResource(Resource resource, Resource
	// resourceParent) {
	// resource.setResourceParent(resourceParent);
	// resource.setResourceType(resourceParent.getResourceType());
	// resource.setCategoryId(resourceParent.getCategoryId());
	// resource.setBranchId(resourceParent.getBranchId());
	// return addResource(resource);
	// }

	/**
	 * Add a new resource from a transient resource object. Sets resourceId and
	 * associates resource with a resource type.
	 * 
	 * @param resource
	 * @param resourceTypeId
	 * @return
	 */
	// public Resource addTypedResource(Resource resource, String
	// resourceTypeId) {
	// ResourceType resourceType = this.getResourceType(resourceTypeId);
	// resource.setResourceType(resourceType);
	// return addResource(resource);
	// }

	/**
	 * Find a resource.
	 * 
	 * @param resourceId
	 * 
	 * @return resource
	 */
	public Resource getResource(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");

		return resourceDao.findById(resourceId);
	}


 	public Resource getResourceByName(String resourceName) {
		if (resourceName == null)
			throw new IllegalArgumentException("resourceName is null");

		return resourceDao.findResourceByName(resourceName);

	}
	
    /**
     * Find resources by name
     *
     * @return list of resources
     */
    public List<Resource> getResourcesByName(String resourceName) {
        if (resourceName == null)
            throw new IllegalArgumentException("resourceName is null");

        List<Resource> resourceList = resourceDao.findResourcesByName(resourceName);

        return resourceList;
    }


    /**
      * Find resources by example
      *
      * @return list of resources
      */
    public List<Resource> getResourcesByExample(Resource resource) {
        return resourceDao.findByExample(resource);
    }


    /**
     * Find resources which have a specified property
     *
     * @param propName
     * @param propValue
     * @return
     */
    public List<Resource> getResourcesByProperty (String propName, String propValue) {
        if (propName == null)
            throw new IllegalArgumentException("propName is null");
        if (propValue == null)
            throw new IllegalArgumentException("propValue is null");

        return resourceDao.findResourcesByProperty(propName, propValue);

    }

    /**
     * Find resource which has a specified set of unique properties
     *
     * @param propList
     * @return
     */
    public Resource getResourceByProperties(List<ResourceProp> propList) {
        if (propList == null)
            throw new IllegalArgumentException("propList is null");

        return resourceDao.findResourceByProperties(propList);

    }


	/**
	 * Update a resource.
	 * 
	 * @param resource
	 * @return
	 */
	public Resource updateResource(Resource resource) {
		if (resource == null)
			throw new IllegalArgumentException("resource object is null");

		return resourceDao.update(resource);
	}

	/**
	 * Find all resources
	 * 
	 * @return list of resources
	 */
	public List<Resource> getAllResources() {
		List<Resource> resourceList = resourceDao.findAllResources();

		return resourceList;
	}

	/**
	 * Remove a resource
	 * 
	 * @param resourceId
	 */
	public void removeResource(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");
		Resource obj = this.resourceDao.findById(resourceId);
		this.resourceDao.remove(obj);
	}

	/**
	 * Remove all resources
	 */
	public int removeAllResources() {
		return this.resourceDao.removeAllResources();
	}

	// ResourceType -----------------------------------

	/**
	 * Add a new resource type
	 * 
	 * @param val
	 * @return
	 */
	public ResourceType addResourceType(ResourceType val) {
		if (val == null)
			throw new IllegalArgumentException("ResourcType is null");

		return resourceTypeDao.add(val);
	}

	/**
	 * Find a resource type
	 * 
	 * @param resourceTypeId
	 * @return
	 */
	public ResourceType getResourceType(String resourceTypeId) {
		if (resourceTypeId == null)
			throw new IllegalArgumentException("resourceTypeId is null");

		return resourceTypeDao.findById(resourceTypeId);
	}

	/**
	 * Update a resource type
	 * 
	 * @param resourceType
	 * @return
	 */
	public ResourceType updateResourceType(ResourceType resourceType) {
		if (resourceType == null)
			throw new IllegalArgumentException("resourceType object is null");

		return resourceTypeDao.update(resourceType);
	}

	/**
	 * Find all resource types
	 * 
	 * @return
	 */
	public List<ResourceType> getAllResourceTypes() {
		List<ResourceType> resourceTypeList = resourceTypeDao
				.findAllResourceTypes();

		return resourceTypeList;
	}

	/**
	 * Remove a resource type
	 * 
	 * @param resourceTypeId
	 */
	public void removeResourceType(String resourceTypeId) {
		if (resourceTypeId == null)
			throw new IllegalArgumentException("resourceTypeId is null");
		ResourceType obj = this.resourceTypeDao.findById(resourceTypeId);
		this.resourceTypeDao.remove(obj);
	}

	/**
	 * Remove all resource types
	 * 
	 * @return
	 */
	public int removeAllResourceTypes() {
		return this.resourceTypeDao.removeAllResourceTypes();
	}

	/**
	 * Find type of a resource
	 * 
	 * @param resourceId
	 * @return
	 */
	public ResourceType findTypeOfResource(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");

		return resourceDao.findTypeOfResource(resourceId);
	}

	// /**
	// * Link metadata type to a resource
	// *
	// * @param resourceTypeId
	// * @param resourceId
	// */
	// public void linkTypeToResource(String resourceId, String resourceTypeId)
	// {
	// if (resourceTypeId == null)
	// throw new IllegalArgumentException("resourceTypeId is null");
	// if (resourceId == null)
	// throw new IllegalArgumentException("resourceId is null");
	// this.resourceDao.linkTypeToResource(resourceId, resourceTypeId);
	// }
	//
	// /**
	// * Unlink type from resource
	// *
	// * @param resourceId
	// */
	// public void unlinkTypeFromResource(String resourceId) {
	// if (resourceId == null)
	// throw new IllegalArgumentException("resourceId is null");
	//
	// this.resourceDao.unlinkTypeFromResource(resourceId);
	// }

	// ResourceProp ---------------------------------------

	/**
	 * Add a resource property.
	 * 
	 * @param resourceProp
	 * @return
	 */
	public ResourceProp addResourceProp(ResourceProp resourceProp) {
		if (resourceProp == null)
			throw new IllegalArgumentException("ResourceProp object is null");

		return resourcePropDao.add(resourceProp);

	}

	// /**
	// * Add a new resource Property from a transient resource Property object.
	// * Sets resourcePropId and associates resourceProp with a resource.
	// *
	// * @param resourceProp
	// * @param resourceId
	// * @return
	// */
	// public ResourceProp addLinkedResourceProp(ResourceProp resourceProp,
	// String resourceId) {
	// if (resourceId == null)
	// throw new IllegalArgumentException("resourceId is null");
	// Resource resource = resourceDao.findById(resourceId);
	//
	// if (resource == null) {
	// log.error("Resource not found for resourceId =" + resourceId);
	// throw new ObjectNotFoundException();
	// }
	//		
	// resourceProp.setResource(resource);
	// Set<ResourceProp> props = resource.getResourceProps();
	// if (props == null)
	// props = new HashSet<ResourceProp>();
	// props.add(resourceProp);
	// return resourcePropDao.add(resourceProp);
	// }

	/**
	 * Find a resource property.
	 * 
	 * @param resourcePropId
	 * @return
	 */
	public ResourceProp getResourceProp(String resourcePropId) {
		if (resourcePropId == null)
			throw new IllegalArgumentException("resourcePropId is null");

		return resourcePropDao.findById(resourcePropId);
	}

	/**
	 * Update a resource property
	 * 
	 * @param resourceProp
	 */
	public ResourceProp updateResourceProp(ResourceProp resourceProp) {
		if (resourceProp == null)
			throw new IllegalArgumentException("resourceProp object is null");

		return resourcePropDao.update(resourceProp);
	}

	/**
	 * Find all resource properties
	 * 
	 * @return
	 */
	public List<ResourceProp> getAllResourceProps() {
		List<ResourceProp> resourcePropList = resourcePropDao
				.findAllResourceProps();

		return resourcePropList;
	}

	/**
	 * Remove a resource property
	 * 
	 * @param resourcePropId
	 */
	public void removeResourceProp(String resourcePropId) {
		if (resourcePropId == null)
			throw new IllegalArgumentException("resourcePropId is null");
		ResourceProp obj = this.resourcePropDao.findById(resourcePropId);
		this.resourcePropDao.remove(obj);
	}

	/**
	 * Remove all resource properties
	 * 
	 * @param
	 */
	public int removeAllResourceProps() {
		return this.resourcePropDao.removeAllResourceProps();
	}

	/**
	 * Remove properties with a specified resourceId
	 * 
	 * @param resourceId
	 * @return
	 */
	public int removePropertiesByResource(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");

		return this.resourceDao.removePropertiesByResource(resourceId);
	}

	/**
	 * Find resource properties
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<ResourceProp> findResourceProperties(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");

		return this.resourceDao.findResourceProperties(resourceId);
	}

	// /**
	// * Add a property to a resource
	// *
	// * @param resourcePropId
	// * @param resourceId
	// */
	// public void linkPropertyToResource(String resourcePropId, String
	// resourceId) {
	// if (resourcePropId == null)
	// throw new IllegalArgumentException("resourcePropId is null");
	// if (resourceId == null)
	// throw new IllegalArgumentException("resourceId is null");
	//
	// this.resourceDao.linkPropertyToResource(resourcePropId, resourceId);
	// }
	//
	// /**
	// * Remove a property from a resource
	// *
	// * @param resourcePropId
	// * @param resourceId
	// */
	// public void unlinkPropertyFromResource(String resourcePropId,
	// String resourceId) {
	// if (resourcePropId == null)
	// throw new IllegalArgumentException("resourcePropId is null");
	// if (resourceId == null)
	// throw new IllegalArgumentException("resourceId is null");
	//
	// this.resourceDao.unlinkPropertyFromResource(resourcePropId, resourceId);
	// }
	//
	// /**
	// * Remove all properties from a resource
	// *
	// * @param resourceId
	// */
	// public void unlinkAllPropertiesFromResource(String resourceId) {
	// if (resourceId == null)
	// throw new IllegalArgumentException("resourceId is null");
	//
	// this.resourceDao.unlinkAllPropertiesFromResource(resourceId);
	//
	// }

	// ResourceParent ------------------------------------------------

	/**
	 * Find resource children
	 * 
	 * @param resourceId
	 * @return
	 */
	public List<Resource> getChildResources(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");
		return this.resourceDao.getChildResources(resourceId);
	}

	// /**
	// * Set a parent resource
	// *
	// * @param parentResourceId
	// * @param childResourceId
	// */
	// public void linkResourceToParent(String childResourceId,
	// String parentResourceId) {
	// if (parentResourceId == null)
	// throw new IllegalArgumentException("parentResourceId is null");
	// if (childResourceId == null)
	// throw new IllegalArgumentException("childResourceId is null");
	// this.resourceDao
	// .linkResourceToParent(childResourceId, parentResourceId);
	// }
	//
	// /**
	// * Remove a resource parent relationship
	// *
	// * @param childResourceId
	// */
	// public void unlinkResourceFromParent(String childResourceId) {
	// if (childResourceId == null)
	// throw new IllegalArgumentException("childResourceId is null");
	// this.resourceDao.unlinkResourceFromParent(childResourceId);
	// }
	//
	// resource hierarchy methods
	// -------------------------------------------------

	/**
	 * Find a resource and its descendants and return as nested List.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return list of nested lists of resource objects
	 */

	public List<Resource> getResourceTree(String resourceId) {

		List<Resource> resourceTree = new ArrayList<Resource>();
		resourceTree.add(resourceDao.findById(resourceId));

		List<Resource> resourceChildren = resourceDao
				.getChildResources(resourceId);
		resourceTree.addAll(resourceChildren);

		for (Iterator<Resource> it = resourceChildren.iterator(); it.hasNext();) {
			Resource r = (Resource) it.next();
			List<Resource> descendents = getResourceTreeHelper(r
					.getResourceId());
			if (!((descendents == null) || (descendents.isEmpty()))) {
				r.setChildResources(new HashSet<Resource>(descendents));
			}
		}
		return resourceTree;
	}

	/**
	 * Recursive helper method to get nested resource descendants.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return the resource tree helper
	 */
	private List<Resource> getResourceTreeHelper(String resourceId) {

		List<Resource> descendents = resourceDao.getChildResources(resourceId);

		for (Iterator<Resource> it = descendents.iterator(); it.hasNext();) {
			Resource r = (Resource) it.next();
			List<Resource> nextChildren = resourceDao.getChildResources(r
					.getResourceId());
			if (!((nextChildren == null) || (nextChildren.isEmpty()))) {
				r.setChildResources(new HashSet<Resource>(nextChildren));
				getResourceTreeHelper(r.getResourceId());
			}
		}
		return descendents;
	}

	private String getResourceType(Resource r) {
		String rt = "";
		ResourceType resType = r.getResourceType();
		if (resType != null)
			rt = resType.getResourceTypeId() + ":";
		return rt;
	}

	/**
	 * Find a resource and its descendants and return as an xml tree.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return xml string
	 */
	public String getResourceTreeXML(String resourceId) {
		StringBuffer xml = new StringBuffer();

		Resource mainResource = resourceDao.findById(resourceId);

		xml.append("<Resource label='" + getResourceType(mainResource)
				+ mainResource.getName() + "' resourceId='"
				+ mainResource.getResourceId() + "'>");
		// xml.append("<ResourceId label='id'>" + mainResource.getResourceId() +
		// "</ResourceId>");
		// xml.append("<ResourceDescription label='desc'>" +
		// mainResource.getDescription()+ "</ResourceDescription>");
		// xml.append("<Resources label='child'>");

		// descendants:
		List<Resource> resourceTree = resourceDao.getChildResources(resourceId);
		for (Iterator<Resource> it = resourceTree.iterator(); it.hasNext();) {
			Resource r = (Resource) it.next();

			xml.append("<Resource label='" + getResourceType(r) + r.getName()
					+ "' resourceId='" + r.getResourceId() + "'>");
			// xml.append("<ResourceId label='id'>" + r.getResourceId() +
			// "</ResourceId>");
			// xml.append("<ResourceDescription label='desc'>" +
			// r.getDescription()+ "</ResourceDescription>");

			// xml.append(getResourceTreeXmlHelper(r.getResourceId(), xml));
			getResourceTreeXmlHelper(r.getResourceId(), xml);

			xml.append("</Resource>");
		}

		// xml.append("</Resources>");
		xml.append("</Resource>");

		return xml.toString();
	}

	/**
	 * Recursive helper method to get nested resource descendants as xml.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @param xml
	 *            the xml
	 * 
	 * @return the resource tree xml helper
	 */
	private StringBuffer getResourceTreeXmlHelper(String resourceId,
			StringBuffer xml) {

		List<Resource> descendents = resourceDao.getChildResources(resourceId);

		// xml.append("<Resources label='child'>");
		for (Iterator<Resource> it = descendents.iterator(); it.hasNext();) {
			Resource r = (Resource) it.next();

			xml.append("<Resource label='" + getResourceType(r) + r.getName()
					+ "' resourceId='" + r.getResourceId() + "'>");
			// xml.append("<ResourceId label='id'>" + r.getResourceId() +
			// "</ResourceId>");
			// xml.append("<ResourceDescription label='desc'>" +
			// r.getDescription()+ "</ResourceDescription>");
			getResourceTreeXmlHelper(r.getResourceId(), xml);
			xml.append("</Resource>");
		}
		// xml.append("</Resources>");

		return xml;

	}

	/**
	 * Find a resource and all its descendants and put them in a list.
	 * 
	 * @param resourceId
	 *            the resource id
	 * 
	 * @return resource list
	 */

	public List<Resource> getResourceFamily(String resourceId) {

		List<Resource> resourceTree = new ArrayList<Resource>();
		resourceTree.add(resourceDao.findById(resourceId));

		List<Resource> resourceChildren = resourceDao
				.getChildResources(resourceId);
		resourceTree.addAll(resourceChildren);

		for (Iterator<Resource> it = resourceChildren.iterator(); it.hasNext();) {
			Resource r = (Resource) it.next();
			List<Resource> descendents = new ArrayList<Resource>();
			resourceTree.addAll(getResourceFamilyHelper(r.getResourceId(),
					descendents));
		}
		return resourceTree;
	}

	/**
	 * Recursive method to get resource descendants in a single non nested list.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @param descendents
	 *            the descendents
	 * 
	 * @return the resource family helper
	 */
	private List<Resource> getResourceFamilyHelper(String resourceId,
			List<Resource> descendents) {

		List<Resource> children = resourceDao.getChildResources(resourceId);
		descendents.addAll(children);
		for (Iterator<Resource> it = children.iterator(); it.hasNext();) {
			Resource r = (Resource) it.next();
			getResourceFamilyHelper(r.getResourceId(), descendents);
		}
		return descendents;
	}

	// Resource get methods
	// =====================================================

	/**
	 * Find resources having a specified metadata type
	 * 
	 * @param resourceTypeId
	 * @return
	 */
	public List<Resource> getResourcesByType(String resourceTypeId) {
		if (resourceTypeId == null)
			throw new IllegalArgumentException("resourceTypeId is null");
		return this.resourceDao.getResourcesByType(resourceTypeId);
	}

	/**
	 * Find root resources i.e. resources with null or blank value for parent
	 * 
	 * @return
	 */
	public List<Resource> getRootResources() {
		return this.resourceDao.getRootResources();
	}

	/**
	 * Find all resources for a specified category.
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<Resource> getResourcesByCategory(String categoryId) {
		if (categoryId == null)
			throw new IllegalArgumentException("categoryId is null");
		return this.resourceDao.getResourcesByCategory(categoryId);
	}

	/**
	 * Find all resources with a specified branch
	 * 
	 * @param branchId
	 * @return
	 */
	public List<Resource> getResourcesByBranch(String branchId) {
		if (branchId == null)
			throw new IllegalArgumentException("branchId is null");
		return this.resourceDao.getResourcesByBranch(branchId);

	}

	/**
	 * Remove resources having a specified metadata type
	 * 
	 * @param resourceTypeId
	 * @return rows affected
	 */
	public int removeResourcesByType(String resourceTypeId) {
		if (resourceTypeId == null)
			throw new IllegalArgumentException("resourceTypeId is null");
		return this.resourceDao.removeResourcesByType(resourceTypeId);
	}

	/**
	 * Remove all resources for a specified category.
	 * 
	 * @param categoryId
	 * @return rows affected
	 */
	public int removeResourcesByCategory(String categoryId) {
		if (categoryId == null)
			throw new IllegalArgumentException("categoryId is null");
		return this.resourceDao.removeResourcesByCategory(categoryId);
	}

	/**
	 * Remove all resources with a specified branch
	 * 
	 * @param branchId
	 * @return rows affected
	 */
	public int removeResourcesByBranch(String branchId) {
		if (branchId == null)
			throw new IllegalArgumentException("branchId is null");
		return this.resourceDao.removeResourcesByBranch(branchId);

	}

	// public List<Resource> getResourceNodeTree (String resourceId) {
	//
	// List<Resource> resourceList = new ArrayList();
	//
	// List<Resource> rootChildren =
	// resourceDao.getResourcesByParent(resourceId);
	//
	// if ( (rootChildren == null)||(rootChildren.isEmpty()) ) {
	// return resourceList;
	// }
	//
	// for (Iterator<Resource> it = rootChildren.iterator(); it.hasNext(); ) {
	// Resource r = (Resource) it.next();
	// resourceList.add(r);
	// getChildResourcesNodeLevel(r.getResourceId(), resourceList);
	// }
	//
	// return resourceList;
	//
	// }

	// public List<Resource> getChildResourcesNodeLevel(String resourceId,
	// List<Resource> resourceList) {
	//
	// Resource res = this.resourceDao.findById(resourceId);
	// int nodeLevel = res.getNodeLevel();
	// ++nodeLevel;
	//
	// List<Resource> childResources =
	// resourceDao.getResourcesByParent(resourceId);
	// if ( (childResources == null)||(childResources.isEmpty()) ) {
	// return resourceList;
	// }
	//
	// for (Iterator<Resource> it = childResources.iterator(); it.hasNext(); ) {
	// Resource r = (Resource)it.next();
	// r.setNodeLevel(nodeLevel);
	// resourceList.add(r);
	// getChildResourcesNodeLevel(r.getResourceId(), resourceList);
	// }
	//
	// return resourceList;
	//
	// }

	// ResourceRole ---------------------------------------

	/**
	 * Add a resource role
	 * 
	 * @param resourceRole
	 * @return
	 */
	public ResourceRole addResourceRole(ResourceRole resourceRole) {

		if (resourceRole == null)
			throw new IllegalArgumentException("ResourceRole object is null");

		return resourceRoleDao.add(resourceRole);

	}

	/**
	 * Find resource role
	 * 
	 * @param resourceRoleId
	 * @return
	 */
	public ResourceRole getResourceRole(ResourceRoleId resourceRoleId) {
		if (resourceRoleId == null)
			throw new IllegalArgumentException("resourceRoleId is null");

		return resourceRoleDao.findById(resourceRoleId);
	}

	/**
	 * Update resource role.
	 * 
	 * @param resourceRole
	 * @return
	 */
	public ResourceRole updateResourceRole(ResourceRole resourceRole) {
		if (resourceRole == null)
			throw new IllegalArgumentException("resourceRole object is null");

		return resourceRoleDao.update(resourceRole);
	}

	/**
	 * Find all resource roles
	 * 
	 * @return
	 */
	public List<ResourceRole> getAllResourceRoles() {
		List<ResourceRole> resourceRoleList = resourceRoleDao
				.findAllResourceRoles();

		return resourceRoleList;
	}

	/**
	 * Remove resource role.
	 * 
	 * @param resourceRoleId
	 */
	public void removeResourceRole(ResourceRoleId resourceRoleId) {
		if (resourceRoleId == null)
			throw new IllegalArgumentException("resourceRoleId is null");
		ResourceRole obj = this.resourceRoleDao.findById(resourceRoleId);
		this.resourceRoleDao.remove(obj);
	}

	/**
	 * Remove all resource roles
	 * 
	 */
	public void removeAllResourceRoles() {
		this.resourceRoleDao.removeAllResourceRoles();
	}

	public List<ResourceRole> getResourceRolesByResource(String resourceId) {
		if (resourceId == null) {
			throw new IllegalArgumentException("resourceId is null");
		}
		return resourceDao.findResourceRolesByResource(resourceId);
	}

	/**
	 * Returns a list of Resource objects that are linked to a Role.
	 * 
	 * @param domainId
	 * @param roleId
	 * @return
	 */
	public List<Resource> getResourcesForRole(String domainId, String roleId) {
		if (domainId == null) {
			throw new IllegalArgumentException("domainId is null");
		}
		if (roleId == null) {
			throw new IllegalArgumentException("roleId is null");
		}
		return resourceDao.findResourcesForRole(domainId, roleId);
	}

	/**
	 * Returns a list of Resource objects that are linked to the list of Roles.
	 * 
	 * @param domainId
	 * @param roleIdList
	 * @return
	 */
	public List<Resource> getResourcesForRoles(String domainId,
			List<String> roleIdList) {
		if (domainId == null) {
			throw new IllegalArgumentException("domainId is null");
		}
		if (roleIdList == null) {
			throw new IllegalArgumentException("roleIdList is null");
		}
		return resourceDao.findResourcesForRoles(domainId, roleIdList);
	}

	/**
	 * Add a resource role privilege
	 * 
	 * @param resourceId
	 *            the resource id
	 * @param roleId
	 *            the role id
	 * @param privilegeId
	 *            the privilege id
	 */
	public void addResourceRolePrivilege(String resourceId, String roleId,
			String privilegeId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (privilegeId == null)
			throw new IllegalArgumentException("privilegeId is null");

		this.resourceDao.addResourceRolePrivilege(resourceId, roleId,
				privilegeId);
	}

	/**
	 * Removes the resource role privilege.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @param roleId
	 *            the role id
	 * @param privilegeId
	 *            the privilege id
	 */
	void removeResourceRolePrivilege(String resourceId, String roleId,
			String privilegeId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");
		if (roleId == null)
			throw new IllegalArgumentException("roleId is null");
		if (privilegeId == null)
			throw new IllegalArgumentException("privilegeId is null");

		this.resourceDao.removeResourceRolePrivilege(resourceId, roleId,
				privilegeId);
	}

	/**
	 * Removes the all role privileges from resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 */
	void removeResourceRolePrivileges(String resourceId) {
		if (resourceId == null)
			throw new IllegalArgumentException("resourceId is null");

		this.resourceDao.removeResourceRolePrivileges(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openiam.idm.srvc.res.service.ResourceDataService#addUserToResource
	 * (org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	public ResourceUser addUserToResource(ResourceUser resourceUser) {
		if (resourceUser == null) {
			throw new IllegalArgumentException("ResourceUser object is null");
		}
		return resourceUserDao.add(resourceUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openiam.idm.srvc.res.service.ResourceDataService#getUserResources
	 * (java.lang.String)
	 */
	public List<ResourceUser> getUserResources(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("UserId object is null");
		}
		return resourceUserDao.findAllResourceForUsers(userId);
	}

     public List<Resource> getResourceObjForUser(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("UserId object is null");
		}

        return resourceDao.findResourcesForUserRole(userId) ;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.openiam.idm.srvc.res.service.ResourceDataService#
	 * removeUserFromAllResources(java.lang.String)
	 */
	public void removeUserFromAllResources(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("UserId object is null");
		}
		resourceUserDao.removeUserFromAllResources(userId);
	}

	public ResourceUserDAO getResourceUserDao() {
		return resourceUserDao;
	}

	public void setResourceUserDao(ResourceUserDAO resourceUserDao) {
		this.resourceUserDao = resourceUserDao;
	}

	public boolean isUserAuthorized(String userId, String resourceId) {
		log.info("isUserAuthorized called.");
		List<ResourceUser> resList = getUserResources(userId);
		log.info("- resList= " + resList);
		if (resList == null) {
			log.info("resource list for user is null");
			return false;
		}
		for (ResourceUser ru : resList) {
			log.info("resource id = " + ru.getId().getResourceId());
			if (ru.getId().getResourceId().equalsIgnoreCase(resourceId)) {
				return true;
			}
		}
		return false;

	}


    public boolean isUserAuthorizedByProperty(String userId, String propertyName, String propertyValue) {
       log.info("isUserAuthorized called.");

        if (propertyName == null || propertyName.length() == 0) {
            return false;
        }
        if (propertyValue == null || propertyValue.length() == 0) {
            return false;
        }

        List<Resource> resList = getResourceObjForUser(userId);

		log.info("- resList= " + resList);
		if (resList == null) {
			log.info("resource list for user is null");
			return false;
		}
		for (Resource res : resList) {

            ResourceProp prop =  res.getResourceProperty(propertyName);
            if (prop != null) {
                String val = prop.getPropValue();
                if (val != null && val.length() > 0) {
                    val = val.toLowerCase();
                    propertyValue = propertyValue.toLowerCase();

                    if (propertyValue.contains(val)) {
                        return true;
                    }

                }
            }
		}

		return false;

    }

	public boolean isRoleAuthorized(String domainId, String roleId,
			String resourceId) {
		log.info("isUserAuthorized called.");

		List<Resource> resList = this.getResourcesForRole(domainId, roleId);
		log.info("- resList= " + resList);
		if (resList == null) {
			log.info("resource list for user is null");
			return false;
		}
		for (Resource r : resList) {
			log.info("resource id = " + r.getResourceId());
			if (r.getResourceId().equalsIgnoreCase(resourceId)) {
				return true;
			}
		}
		return false;
	}

}




