package org.openiam.webadmin.busdel.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

import org.apache.struts.util.LabelValueBean;


import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleAttribute;
import org.openiam.idm.srvc.role.service.RoleDataService;

import org.openiam.idm.srvc.user.dto.User;
import org.openiam.webadmin.busdel.base.NavigationAccess;
import org.springframework.web.context.WebApplicationContext;



/**
 * Business delegate for the RoleDataService.
 * 
 * @author Suneet Shah
 * @version 2
 */


public class RoleDataServiceAccess  extends NavigationAccess  {

   
    RoleDataService roleDataService = null;

	public RoleDataServiceAccess(WebApplicationContext  webContext) {
		roleDataService = (RoleDataService)webContext.getBean("roleDataService");
	}


	/*
	 * Returns all groups as Struts <code>LabelValueBean</code> objects.
	 */
	public List getRoleListAsLabels(List<Role> roleList) {
		List<LabelValueBean> newCodeList = new LinkedList();
		
		if (roleList != null && roleList.size() > 0) {
			newCodeList.add(new LabelValueBean("",""));
	    	for (int i = 0; i < roleList.size(); i++) {       		
	    		Role val = roleList.get(i);
	    	 	LabelValueBean label = new LabelValueBean(val.getRoleName(),val.getId().getRoleId());
	    	 	newCodeList.add(label);
	    	}
		}
		return newCodeList;
    }
	
	public Role getRole(String serviceId, String roleId) {
		return roleDataService.getRole(serviceId, roleId);
	}
	
	public Role addRole(Role role) {
		return roleDataService.addRole(role);
	}

	public void updateRole(Role role) {
		roleDataService.updateRole(role);
	}
	
	public void removeRole(String serviceId, String roleId) {
		roleDataService.removeRole(serviceId,roleId); 
	}

	public Role[] getAllRoles() {
		return roleDataService.getAllRoles();
	}
	
	public Role[] getRolesInService(String serviceId) {
		return roleDataService.getRolesInService(serviceId);
	}
	

	public Role[] getRolesInGroup( String groupId) {
		return roleDataService.getRolesInGroup(groupId);
	}
	

	public User[] getUsersInRole(String serviceId, String roleId) {
		return roleDataService.getUsersInRole(serviceId,roleId);
	}
	

	public Role[] getUserRoles(String userId) {
		return roleDataService.getUserRoles(userId);
	}

	public Role[] getUserRolesByService(String serviceId, String userId) {
		return roleDataService.getUserRolesByService(serviceId, userId);
	}


	public  boolean isGroupInRole(String serviceId, String roleId, String groupId) {
		return  roleDataService.isGroupInRole(serviceId, roleId, groupId); 
	}

	public boolean isUserInRole(String serviceId, String roleId, String userId) {
		return roleDataService.isUserInRole(serviceId, roleId, userId);
	}

	  
	public  void addGroupToRole(String serviceId, String roleId, String groupId) {
		roleDataService.addGroupToRole(serviceId, roleId, groupId);
	}
	public  void removeGroupFromRole(String serviceId, String roleId, String groupId) {
		roleDataService.removeGroupFromRole(serviceId, roleId, groupId);
	}
	  
	public  Group[] getGroupsInRole(String serviceId, String roleId) {
		return roleDataService.getGroupsInRole(serviceId, roleId);
	}
	   
	public  Group[] getGroupsInRoleByService(String serviceId, String roleId) {
		return roleDataService.getGroupsInRole(serviceId, roleId);
		//return roleDataService.getGroupsInRoleByService(serviceId, roleId);
	}


	public  void addUserToRole(String serviceId, String roleId, String userId) {
		 roleDataService.addUserToRole(serviceId, roleId, userId);
	}

	public void removeUserFromRole(String serviceId, String roleId, String userId) {
		 roleDataService.removeUserFromRole(serviceId, roleId, userId);
	}


	
}

    



