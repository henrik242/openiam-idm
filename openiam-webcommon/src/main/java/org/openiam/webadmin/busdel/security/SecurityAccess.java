package org.openiam.webadmin.busdel.security;

import java.rmi.RemoteException;
import java.util.*;
import org.openiam.webadmin.busdel.base.NavigationAccess;
import org.openiam.webadmin.busdel.base.TabOption;
import diamelle.security.auth.*;

public class SecurityAccess extends NavigationAccess {

    diamelle.security.auth.SecurityManager security = null;

    public SecurityAccess() {
      super();
      try {
        SecurityManagerHome securityHome = (SecurityManagerHome)getHome("SecurityManager");
        security = securityHome.create();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }


// Entitlement methods

        // getting a list of all Entitlements from the Entitlement table
        public List getAllEntitlements () throws java.rmi.RemoteException {
            return security.getAllEntitlements();
        }

        // getting a list of Entitlements for the particular role
        public List getEntitlementsByRole (String roleId) throws java.rmi.RemoteException {
              return security.getRoleEntitlements(roleId);
        }

        // deleting an Entitlement from the role
        public void deleteRoleEntitlement (String roleId, String entitlementId) throws java.rmi.RemoteException {
             security.removeRoleEntitlement(roleId,entitlementId);
        }

        // deleting all Entitlements from the role
        public void deleteAllEntitlement (String roleId) throws java.rmi.RemoteException {
              security.removeRoleEntitlements(roleId);
        }

        // adding an Entitlement to the Entitlement table
        public void addEntitlement(EntitlementValue ev) throws java.rmi.RemoteException {
              if (ev.getEntitlementId() == null || ev.getEntitlementId().length() < 1)
                 ev.setEntitlementId(getNextId("ENTITLEMENT_ID"));
              security.addEntitlement(ev);
        }

        // adding an Entitlement to the role
        public void addRoleEntitlement(String roleId,String entitlementId) throws java.rmi.RemoteException {
              security.addRoleToEntitlement(roleId,entitlementId);
        }

        // getting the Entitlement details of the Entitlement selected
        public EntitlementValue editEntitlement(String  entitlementId) throws java.rmi.RemoteException {
              return security.getEntitlement( entitlementId);
        }

        // deleting an Entitlement from the Entitlement table
        public void deleteEntitlement (String entitlementId) throws java.rmi.RemoteException {
              security.removeEntitlement(entitlementId);
        }

        // updating an Entitlement
        public void updateEntitlement (EntitlementValue ev) throws java.rmi.RemoteException {
              security.saveEntitlement(ev);
        }


// Permissions methods

   /**
	 * Gets only those menus within an application that a user has permissions
	 * to access.
	 *
	 * @param userId for which menus are to be retrieved.
	 * @param serviceId for which menu has to retrieved.
	 * @param langCd for which menu has to retrieved - null if not defined.
	 * @param appMenuId is the menuId of the application
	 *
	 * @return Collection of MenuData objects.
	 */
	 	public Collection getPermissions(String userId, String menuGroup, String langCd) throws RemoteException {
	 		return security.getPermissions(userId, menuGroup, langCd);
	 	}

        // checking if the user has the Role
        public boolean isUserInRole (String userId, String roleId) throws java.rmi.RemoteException {
               return security.isUserInRole(userId, roleId);
        }

        // getting a list of all Permissions
        public List getPermissionsForRole (String roleId, String langcd) throws java.rmi.RemoteException {
              List permissionList =security.getPermissionsInRole(roleId,null);
              return permissionList;
        }

        // adding a Permission
        public void addPermission (String roleId, String menuId) throws java.rmi.RemoteException {
	          security.addPermission(roleId,menuId);            
        }
        
        // removing a Permission from Role
        public void removePermission (String roleId, String menuId) throws java.rmi.RemoteException {
	            security.removePermission(roleId, menuId);
        }

        // getting a list of all groups
        public List getAllGroups() throws java.rmi.RemoteException {
           return security.getAllGroups();
        }

        // Gets all users in the particular Role
        public List getallUsersbyrole(String role) throws Exception {
          return(security.getUsersInRole(role));
         }

        // adding a new group to the group table
        public void create(GroupValue grpData) throws Exception  {
            if (grpData.getGrpId() == null || grpData.getGrpId().length() <1)
               grpData.setGrpId(getNextId("GRP_ID"));
            security.addGroup(grpData);
        }

        // adding a group for a particular role
        public void addGroupForRole(String groupId,String roleId) throws java.rmi.RemoteException {
            security.addRoleToGroup(groupId,roleId);
        }

        // deleting all groups from the role
        public void deleteAllGrpRoles(String roleId) throws java.rmi.RemoteException {
           security.removeGroupsInRole(roleId);
        }

        // deleting a group from the role
        public void deleteGrpRole(String groupId,String roleId) throws java.rmi.RemoteException {
           security.removeGroupRole(groupId,roleId);
        }

        // getting a list of groups for a particular role
        public List getGroupsForRole(String roleId) throws java.rmi.RemoteException {
            return security.getGroupsInRole(roleId);
        }

        // deleting a group from the group table
        public void remove(String groupId) throws Exception  {
            security.removeGroup(groupId);
        }

        // updating the group details
        public void updateGroup(GroupValue groupData) throws Exception {
            security.saveGroup(groupData);
        }

        // getting details of a particular role
        public RoleValue getRoleValue(String roleId) throws Exception {
            RoleValue roleVal = security.getRole(roleId);
            return roleVal;
        }

        // adding a new Role to the service
        public void addRole(RoleValue roleValue) throws java.rmi.RemoteException {
           if (roleValue.getRoleId() == null || roleValue.getRoleId().length() <1)
            roleValue.setRoleId(getNextId("ROLE_ID"));
            security.addRole(roleValue.getRoleId(),roleValue.getRoleName(),roleValue.getServiceId());
        }
        
       // deprecate this
       /*
        public void addNewRole(RoleValue roleValue) throws java.rmi.RemoteException {
           if (roleValue.getRoleId() == null || roleValue.getRoleId().length() <1)
            roleValue.setRoleId(getNextId("ROLE_ID"));
            security.addRole(roleValue.getRoleId(),roleValue.getRoleName(),roleValue.getServiceId());
        }
        */

        // updating the role details
        public void updateRole(RoleValue rv) throws java.rmi.RemoteException {
          security.saveRole(rv);
        }

        // deleting a role from the service
        public void deleteRole(String roleId) throws java.rmi.RemoteException {
           security.removeRole(roleId);
        }

        // getting a list of all Services
        public Map getAllServices() throws java.rmi.RemoteException {
            return security.getAllServices();
        }

        // getting a list of all Roles
        public List getAllRoles(String serviceId) throws java.rmi.RemoteException {
            return security.getRolesInService(serviceId);
        }

        // deletes a group from the group table
        public void deleteGroup(String groupId) throws java.rmi.RemoteException {
            security.removeGroup(groupId);
        }

        /*/ returns a list of all groups
        public List getAllGroups() throws java.rmi.RemoteException {
             return security.getAllGroups();
        }*/


         public Map roleTabOptions() {
            Map mp = new HashMap();
            TabOption option = new TabOption("Role Details", true, "role", "editrole.jsp");
            mp.put("RoleDetail", option);

            option = new TabOption("Entitlements", false, "entitlement", "entitlementlist.jsp");
            mp.put("Entitlement", option);
            option = new TabOption("AccessControls", false, "accesscontrol", "resourcelist.jsp");
            mp.put("Access Control", option);

            option = new TabOption("Groups", false, "group", "grouplist.jsp");
            mp.put("Group", option);
            option = new TabOption("Permissions", false, "permission", "permissionlist.jsp");
            mp.put("Permission", option);

            return mp;
         }


        public void setDefaultTabOption(String detail, Map optionMap) {
          TabOption option = null;
          Iterator it =  optionMap.values().iterator();
          while (it.hasNext()) {
            option = (TabOption)it.next();
            if (option.getUrlParam().equals(detail)) {
               option.setActive(true);
            }else {
               option.setActive(false);
            }
            optionMap.put(option.getUrlParam(),option);
          }
        }

 // Checks the role of user
  public boolean isUserRole(String userId, String role) throws Exception {
	  return(security.isUserInRole(userId,role));
   }


 public List getPermissionsInRole(String role, String langCd) throws Exception {
        return(security.getPermissionsInRole(role, langCd));
   }
 


}