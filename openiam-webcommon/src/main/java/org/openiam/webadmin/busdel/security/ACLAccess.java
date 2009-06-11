package org.openiam.webadmin.busdel.security;

import org.openiam.webadmin.busdel.base.NavigationAccess;

import diamelle.security.resource.*;
import java.rmi.RemoteException;
import java.util.*;
import diamelle.base.prop.*;

public class ACLAccess extends NavigationAccess  {
  AccessController ac;

  public ACLAccess() {
     super();
     try {
       AccessControllerHome home = (AccessControllerHome)getHome("AccessController");
       ac = home.create();
     } catch(Exception e) {
       e.printStackTrace();
     }
  }
  
 /**
  * This method gets all descendent resources of a node. For a given resourceId,
  * all the children and descendent resources will be returned.<br>
  * For example: <p>
  * <code>
  *    List l = ac.getNodeResources(resourceId);<br>
  * </code>
  *
  * @param String resourceId
  * @return List of ResourceValue objects
  */
  public List getNodeResources(String resourceId) throws RemoteException {
    return ac.getNodeResources(resourceId);
  }

 /**
  * This is a recursive method to get all child resources of a node
  * It is called by getNodeResources method to get all children for a node.
  * If any child resources are found, they are added to the resourceList passed in.<br>
  * 
  * For example: <p>
  * <code>
  *      ResourceValue val = (ResourceValue)it.next();
  *      resourceList.add( getNextGen(val, resourceList) );
  *    <br>
  * </code>
  *
  * @param ResourceValue val, List resourceList
  * @return List of ResourceValue objects
  * @throws FinderException
  */
  public List getNextGen(ResourceValue val, List resourceList) throws Exception { 
    return ac.getNextGen(val, resourceList);
  }
  
 /**
  * This method gets all descendent resources of a node for a specific role. For a given 
  * resourceId all the children and descendent resources for a role will be returned.<br>
  * For example: <p>
  * <code>
  *    List l = ac.getNodeResources(resourceId, roleId);<br>
  * </code>
  *
  * @param String resourceId
  * @return List of ResourceValue objects
  */

  public List getNodeResources(String resourceId, String roleId) throws RemoteException{
    return ac.getNodeResources(resourceId, roleId);
  }
 /**
  * This is a recursive method to get all child resources of a node for a given role.
  * It is called by getNodeResources method to get all children for a node.<br>
  * If any child resources are found, they are added to the resourceList passed in<br>
  * For example: <p>
  * <code>
  *      ResourceValue val = (ResourceValue)it.next();
  *      resourceList.add( getNextGen(roleId, val, resourceList) );
  *    <br>
  * </code>
  *
  * @param 
  * @returns List of ResourceValue objects
  *
  */
  public List getNextGen(String roleId, ResourceValue val, List resourceList) throws Exception {
    return ac.getNextGen(roleId, val, resourceList);
  }
    
  // -------- RESOURCE -----------
 /**
  * This method adds a resource<br>
  * For example: <p>
  * <code>
  *    ResourceValue val = new ResourceValue();
  *    val.setBranchId(branchId);
  *    val.setDescription(description);
  *    val.setResourceId(resourceId);
  *    val.setResourceParent(resourceParent);
  *    ac.addResource(val)<br>
  * </code>
  *
  * @param ResourceValue
  *
  */
  public void addResource(ResourceValue val) throws RemoteException {
    if (val == null) return;
    if ((val.getResourceId() == null)||(val.getResourceId().length()==0)){
      val.setResourceId(getNextId("RESOURCE_ID"));
    }    
    ac.addResource(val);
  }
  
  // for a root, branchId = resourceId
  public void addRoot(ResourceValue val) throws RemoteException {
    if (val == null) return;
    if ((val.getResourceId() == null)||(val.getResourceId().length()==0)){
      val.setResourceId(getNextId("RESOURCE_ID"));
    }    
    val.setBranchId(val.getResourceId());
    ac.addResource(val);
  }


 /**
  * This method extracts a resource.<br>
  * For example: <p>
  * <code>
  *    ac.getResource(resourceId);<br>
  * </code>
  *
  * @param String resourceId
  * @return ResourceValue
  */
  public ResourceValue getResource(String resourceId) throws RemoteException {
    return ac.getResource(resourceId);
  }

 /**
  * This method updates a resource<br>
  * For example: <p>
  * <code>
  *    ResourceValue val = new ResourceValue();
  *    val.setBranchId(branchId);
  *    val.setDescription(description);
  *    val.setResourceId(resourceId);
  *    val.setResourceParent(resourceParent);
  *    ac.saveResource(val);
  *    <br>
  * </code>
  *
  * @param ResourceValue
  */
  public void saveResource(ResourceValue val) throws RemoteException {
    ac.saveResource(val);
  }

 /**
  * This method removes a resource.<br>
  * For example: <p>
  * <code>
  *    ac.removeResource(resourceId);<br>
  * </code>
  *
  * @param String resourceId
  */
  public void removeResource(String resourceId) throws RemoteException {
    ac.removeResource(resourceId);
  }
  
 /**
  * Get a list of all resources for a resource type<br>
  * For example: <p>
  * <code>
  *   List l = ac.getResourcesByType(resourceTypeId);
  *    <br>
  * </code>
  *
  * @return List of resourceValue objects 
  */

  public List getResourcesByType(String resourceTypeId) throws RemoteException {
    return ac.getResourcesByType(resourceTypeId);   
  }
  
     /**
  * Get a list of all resources.<br>
  * For example: <p>
  * <code>
  *   List l = ac.getAllResources();
  *    <br>
  * </code>
  *
  * @return List of resourceValue objects 
  */

  public List getAllResources() throws RemoteException {
    return ac.getAllResources();
  }

   /**
  * Get a list of all resources for a resource parent<br>
  * For example: <p>
  * <code>
  *   List l = ac.getResourcesByParent(resourceParent);
  *    <br>
  * </code>
  *
  * @return List of resourceValue objects 
  */

  public List getResourcesByParent(String resourceParent) throws RemoteException {
    return ac.getResourcesByParent(resourceParent);
  }
  
   /**
  * Get a list of all resources for a resource branch<br>
  * For example: <p>
  * <code>
  *   List l = ac.getResourcesByBranchId(resourceBranchId);
  *    <br>
  * </code>
  *
  * @return List of resourceValue objects 
  */

  public List getResourcesByBranchId(String resourceBranchId) throws RemoteException {
    return ac.getResourcesByBranchId(resourceBranchId);
  }
  
   /**
  * Get a list of all resources for a category<br>
  * For example: <p>
  * <code>
  *   List l = ac.getResourcesByCategory(categoryId);
  *    <br>
  * </code>
  *
  * @return List of resourceValue objects 
  */

  public List getResourcesByCategory(String categoryId) throws RemoteException {
    return ac.getResourcesByCategory(categoryId);
  }
  
  // --------- RESOURCE_ROLE ---------
 /**
  * This method gives privilege to a role to use a resource. <br>
  * For example: <p>
  * <code>
  *   ac.addResourcePrivilege(resourceId, roleId, privilegeId);
  *   <br>
  * </code>
  *
  * @param String resourceId
  * @param String roleId
  * @param String privilegeId
  */
  public void addResourcePrivilege(String resourceId, String roleId, 
    String privilegeId) throws RemoteException {
    ac.addResourcePrivilege(resourceId, roleId, privilegeId);  
  }
  
 /**
  * This method gives a list of privileges to a role to use a resource <br>
  * For example: <p>
  * <code>
  *   List privileges = new ArrayList();
  *   privileges.add(privilegeId1);
  *   privileges.add(privilegeId2);
  *   privileges.add(privilegeId3);
  *   addResourcePrivileges(resourceId, roleId, privileges);
  *    <br>
  * </code>
  *
  * @param String resourceId
  * @param String roleId
  * @param List privileges
  */
  public void addResourcePrivileges(String resourceId, String roleId, 
    List privileges) throws RemoteException {
    ac.addResourcePrivileges(resourceId, roleId, privileges);        
  } 
    
  /**
  * This method gives privilege to a role to use a resource and all its 
  * descendents <br>
  * For example: <p>
  * <code>
  *   ac.addNodePrivilege(node, roleId, privilegeId);
  *    <br>
  * </code>
  *
  * @param String node - the root resourceId
  * @param String roleId
  * @param String privilegeId
  */
  public void addNodePrivilege(String node, String roleId, String privilegeId) 
    throws RemoteException {
    ac.addNodePrivilege(node, roleId, privilegeId);  
  }

  
 /**
  * This method gives a list of privileges to a role to use a resource and all 
  * its descendents<br>
  * For example: <p>
  * <code>
  *   List privileges = new ArrayList();
  *   privileges.add(privilegeId1);
  *   privileges.add(privilegeId2);
  *   privileges.add(privilegeId3);
  *   ac.addNodePrivileges(node, roleId, privileges);
  *    <br>
  * </code>
  *
  * @param String node represents root resourceId
  * @param String roleId
  * @param List privileges
  */
  public void addNodePrivileges(String node, String roleId, List privileges) 
    throws RemoteException {
    ac.addNodePrivileges(node, roleId, privileges);  
  }

  public void removeResourceRole(String resourceId, String roleId) throws RemoteException {
    ac.removeResourceRole(resourceId, roleId);
  }
  
 /**
  * Remove a privilege to use a resource for a role<br>
  * For example: <p>
  * <code>
  *    ac.removePrivilege(resourceId, roleId, privilegeId);<br>
  * </code>
  *
  * @param String resourceId
  * @param String roleId
  * @param String privilegeId
  */
  public void removePrivilege(String resourceId, String roleId, String privilegeId) 
    throws RemoteException {
    ac.removePrivilege(resourceId, roleId, privilegeId);  
  }
  
 /**
  * Gets resources for a role as a list of resourceIds<br>
  * For example: <p>
  * <code>
  *    List l = ac.getRoleResources;<br>
  * </code>
  *
  * @param String roleId
  * @return List of resourceId strings
  */
    
  public List getRoleResources(String roleId) throws RemoteException {
    return ac.getRoleResources(roleId);
  }
 /**
  * Gets privileges and roles for a resource as a list of ResourceRoleValue 
  * objects<br>
  * For example: <p>
  * <code>
  *    List l = ac.getResourceRoles;<br>
  * </code>
  *
  * @param String resourceId
  * @return List of roleId strings
  */
  public List getResourcePrivileges(String resourceId) throws RemoteException {
    return ac.getResourcePrivileges(resourceId);
  }

 /**
  * Removes a role and all privileges associated with it to use resources.<br>
  * For example: <p>
  * <code>
  *    ac.removeRole(roleId);<br>
  * </code>
  *
  * @param 
  */
  public void removeRole(String roleId) throws RemoteException {
    ac.removeRole(roleId);
  }
  
 /**
  * Gets privileges given to a role to use a resource.<br>
  * For example: <p>
  * <code>
  *    List l = ac.getPrivileges(resourceId, roleId);<br>
  * </code>
  *
  * @param String resourceId
  * @param String roleId
  * @return List of resource role value objects
  */
  public List getPrivileges(String resourceId, String roleId) throws RemoteException {
    return ac.getPrivileges(resourceId, roleId);
  }
  
   /**
  * Gets roles as a list of ResourceRoleValue objects for a roleId
  * Use this to display resourceId, privilegeId combinations for each role<br>
  * For example: <p>
  * <code>
  *    List l = ac.getRolePrivileges;<br>
  * </code>
  *
  * @param 
  * @return List of ResourceRoleValue objects
  */
  public List getRolePrivileges(String roleId) throws RemoteException {
    return ac.getRolePrivileges(roleId);
  }
  

  /**
  * This method returns true if role has a certain privilege to use a resource.<br>
  * For example: <p>
  * <code>
  *   boolean check = ac.roleHasPrivilege(roleId,resourceId, privilegeId);<br>
  * </code>
  *
  *
  * @param String roleId 
  * @param String resourceId
  * @param String privilegeId
  * @return boolean Returns True if user belongs to that ResourceId.
  */
  public boolean roleHasPrivilege(String roleId, String resourceId, String privilegeId)
    throws RemoteException {
    return ac.roleHasPrivilege(roleId, resourceId, privilegeId);  
  }
    
 // ------- PRIVILEGE ---------------  
 /**
  * Add a privilege definition.<br>
  * For example: <p>
  * <code>
  *    PrivilegeValue val = new PrivilegeValue();
  *    val.setPrivilegeId(privilegeId);
  *    val.setDescription(description);
  *    ac.addPrivilege(val);<br>
  * </code>
  *
  * @param PrivilegeValue 
  */
  
  public void addPrivilege(PrivilegeValue val) throws RemoteException {
  	
  	if (val == null) return;
    if ((val.getPrivilegeId() == null)||(val.getPrivilegeId().length()==0)){
      val.setPrivilegeId(getNextId("PRIVILEGE_ID"));
    }    
  	
  	ac.addPrivilege(val);
  }

 /**
  * Update a Privilege<br>
  * For example: <p>
  * <code>
  *   PrivilegeValue val = new PrivilegeValue();
  *   val.setPrivilegeId(privilegeId);
  *   val.setDescription(description);
  *   ac.savePrivilege(val);<br>
  * </code>
  *
  * @param PrivilegeValue
   */

  public void savePrivilege(PrivilegeValue val) throws RemoteException {
    ac.savePrivilege(val);
  }
 /**
  * Get a privilege definition corresponding to its id<br>
  * For example: <p>
  * <code>
  *    PrivilegeValue val = ac.getPrivilege(privilegeId);<br>
  * </code>
  *
  * @param String privilegeId
  * @return PrivilegeValue
  */

  public PrivilegeValue getPrivilege(String privilegeId) throws RemoteException {
    return ac.getPrivilege(privilegeId);
  }
  
 /**
  * Removes privilege from the system.<br>
  * For example: <p>
  * <code>
  *    ac.removePrivilege<br>
  * </code>
  *
  * @param String privilegeId
  */

  public void removePrivilege(String privilegeId) throws RemoteException {
    ac.removePrivilege(privilegeId);
  }
  
   
 /**
  * Gets all priveleges in the system.<br>
  * For example: <p>
  * <code>
  *    ac.getAllPrivileges();<br>
  * </code>
  *
  * @returns List of PrivilegeValue objects
  */

  public List getAllPrivileges() throws RemoteException {
    return ac.getAllPrivileges();
  }
  
 // ---------- RESOURCE TYPE ---------
 /**
  * Adds a new resource type<br>
  * For example: <p>
  * <code>
  *   ResourceTypeValue val = new ResourceTypeValue();
  *   val.setResourceTypeId(resourceTypeId);
  *   val.setDescription(description);
  *   ac.addResourceType(val);
  *   <br>
  * </code>
  *
  * @param ResourceTypeValue
  */
  
  public void addResourceType(ResourceTypeValue val) throws RemoteException {
  	 if (val == null) return;
     
  	 if ((val.getResourceTypeId() == null)||(val.getResourceTypeId().length()==0)){
       val.setResourceTypeId(getNextId("RESOURCE_TYPE_ID"));
     }    
  	 
  	 ac.addResourceType(val);
  }
  
 /**
  * Remove a resourceType<br>
  * For example: <p>
  * <code>
  *    ac.removeResourceType(resourceTypeId);<br>
  * </code>
  *
  * @param String resourceTypeId
  */

  public void removeResourceType(String resourceTypeId) throws RemoteException {
    ac.removeResourceType(resourceTypeId);
  }
  
 /**
  * Update a resource type<br>
  * For example: <p>
  * <code>
  *   ResourceTypeValue val = new ResourceTypeValue();
  *   val.setResourceTypeId(resourceTypeId);
  *   val.setDescription(description);
  *   ac.saveResourceType(val);<br>
  * </code>
  *
  * @param ResourceTypeValue
   */

  public void saveResourceType(ResourceTypeValue val) throws RemoteException {
    ac.saveResourceType(val);
  }

 /**
  * Get a resource type corresponding to its id<br>
  * For example: <p>
  * <code>
  *    ResourceTypeVal val = ac.getResourceType(resourceTypeId);<br>
  * </code>
  *
  * @param String resourceTypeId
  * @return ResourceTypeValue
  */

  public ResourceTypeValue getResourceType(String resourceTypeId) throws RemoteException {
    return ac.getResourceType(resourceTypeId);
  }
  
 /**
  * Get a list of all resource types<br>
  * For example: <p>
  * <code>
  *   List l = ac.getAllResourceTypes();
  *    <br>
  * </code>
  *
  * @return List of resourceTypeValue
  */

  public List getAllResourceTypes() throws RemoteException {
    return ac.getAllResourceTypes();
  }
  
  // --------------- RESOURCE_PROP ------------
  
 /**
  * Add a new resource attribute<br>
  * For example: <p>
  * <code>
  *   Property prop = new PropertyImpl();
  *   prop.setId(resourcePropId);
  *   prop.setMetaDataId(metadataId);
  *   prop.setValue("somevalue");
  *   prop.setParentKey(resourceId);
  *   ac.addResourceProperty(prop);
  *    <br>
  * </code>
  *
  * @param Property prop
  * @returns 
  */
  
  public void addResourceProp(String resourceId, Property prop) throws RemoteException {
  	if (prop == null) return;
    if ((prop.getId() == null)||(prop.getId().length()==0)){
      prop.setId(getNextId("RESOURCE_PROP_ID"));
    }    
    ac.addResourceProp(resourceId, prop);
  }
  
 /**
  * Remove an attribute from a resource<br>
  * For example: <p>
  * <code>
  *   ac.removeResourceProperty(resourcePropId);
  *    <br>
  * </code>
  *
  * @param String resourcePropId
  */

  public void removeResourceProp(String resourceId, String resourcePropId) throws RemoteException {
    ac.removeResourceProp(resourceId, resourcePropId);
  }
  
 /**
  * Update a resource attribute<br>
  * For example: <p>
  * <code>
  *   Property prop = new PropertyImpl();
  *   prop.setId(resourcePropId);
  *   prop.setMetaDataId(metadataId);
  *   prop.setValue("somevalue");
  *   prop.setParentKey(resourceId);
  *   ac.saveResourceType(prop);
  *    <br>
  * </code>
  *
  * @param 
  * @returns 
  */

  public void saveResourceProp(String resourceId, Property prop) throws RemoteException {
    ac.saveResourceProp(resourceId, prop);
  }

 /**
  * Get a resource property with resourcePropId<br>
  * For example: <p>
  * <code>
  *   Property prop = ac.getResourcePropById(resourcePropId);
  *    <br>
  * </code>
  *
  * @param String resourcePropId
  * @return Property
  */

  public Property getResourcePropById(String resourcePropId) throws RemoteException {
    return ac.getResourcePropById(resourcePropId);
  }
  
 /**
  * Get a map of resource properties keyed by metadataId<br>
  * For example: <p>
  * <code>
  *   Map m = ac.getResourceProperties(resourceId);
  *    <br>
  * </code>
  *
  * @param 
  * @returns Map with metadataId as key and Property as the object
  */

  public Collection getResourceProp(String resourceId) throws RemoteException {
    return ac.getResourceProp(resourceId);
  }
  
  // ---------------- SQL used ------------------------
  
  /**
  * This method returns a user's privileges to access a resource<br>
  * @param String userId
  * @param String resourceId which the User wants to access
  * @return List of privilegeId String
  */
  public List getUserPrivileges(String userId, String resourceId) throws RemoteException {
    return ac.getUserPrivileges(userId, resourceId);
  }
  
  /**
  * This method checks if a user has a certain privilege to access a resource
  * such as a WRITE privilege<br>
  * @param String userId
  * @param String resourceId which the User wants to access
  * @param String privilegeId 
  * @return boolean
  */
  public boolean userHasPrivilege(String userId, String resourceId, String privilegeId)
    throws RemoteException {
    return ac.userHasPrivilege(userId, resourceId, privilegeId); 
  }
  
  /**
   * This method gets a user's access list for a domain.
   *
   * @param userId  The user for whom access has to be retrieved.
   * @param serviceId  The domain for which the ACL has to retrieved .
   *
   * @return List of ResourceValue objects.
   */
 public List getUserResources(String userId, String serviceId) throws RemoteException {
   return ac.getUserResources(userId, serviceId);
 }
 
 
  /**
   * This method gets a list of resources for all roles in a domain.
   *
   * @param serviceId  The domain for which the ACL has to retrieved .
   *
   * @return List of ResourceValue object.
   */
 public List getServiceResources(String serviceId) throws RemoteException {
     return ac.getServiceResources(serviceId);
   }
   
  /**
   * This method gets a list of branches for a category as resource value objects
   *
   * @param categoryId  The category for which branches are extracted
   *
   * @return List of ResourceValue object.
   */
  public List getCategoryBranches(String categoryId) throws RemoteException {
    return ac.getCategoryBranches(categoryId);
  }
 
  
}