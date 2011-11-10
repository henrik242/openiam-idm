/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.role.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.ws.GroupArrayResponse;
import org.openiam.idm.srvc.grp.ws.GroupListResponse;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleAttribute;
import org.openiam.idm.srvc.role.dto.RolePolicy;
import org.openiam.idm.srvc.role.dto.RoleSearch;
import org.openiam.idm.srvc.role.dto.UserRole;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.ws.UserArrayResponse;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.role.ws.RoleDataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/role/service", 
		portName = "RoleDataWebServicePort",
		serviceName = "RoleDataWebService")
public class RoleDataWebServiceImpl implements RoleDataWebService {

	RoleDataService roleDataService;

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#addAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
	public RoleAttributeResponse addAttribute(RoleAttribute attribute) {
		RoleAttributeResponse resp = new RoleAttributeResponse(ResponseStatus.SUCCESS);
		roleDataService.addAttribute(attribute);
		if (attribute.getRoleAttrId() == null || attribute.getRoleAttrId().isEmpty() ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setRoleAttr(attribute);
		}
		return resp;

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#addGroupToRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response addGroupToRole(String serviceId, String roleId,
			String groupId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.addGroupToRole(serviceId, roleId, groupId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#addRole(org.openiam.idm.srvc.role.dto.Role)
	 */
	public RoleResponse addRole(Role role) {
		RoleResponse resp = new RoleResponse(ResponseStatus.SUCCESS);
		roleDataService.addRole(role);
		if (role.getId().getRoleId() != null) {
			resp.setRole(role);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}

		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#addUserToRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response addUserToRole(String serviceId, String roleId, String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.addUserToRole(serviceId, roleId, userId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getAllAttributes(java.lang.String, java.lang.String)
	 */
	public RoleAttributeArrayResponse getAllAttributes(String serviceId,
			String roleId) {
		RoleAttributeArrayResponse resp = new RoleAttributeArrayResponse(ResponseStatus.SUCCESS);
		RoleAttribute[] roleAttrAry = roleDataService.getAllAttributes(serviceId, roleId); 
		if (roleAttrAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleAttrAry(roleAttrAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getAllRoles()
	 */
	public RoleListResponse getAllRoles() {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleList = roleDataService.getAllRoles(); 
		if (roleList == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getAttribute(java.lang.String)
	 */
	public RoleAttributeResponse getAttribute(String attrId) {
		RoleAttributeResponse resp = new RoleAttributeResponse(ResponseStatus.SUCCESS);
		RoleAttribute roleAttr = roleDataService.getAttribute(attrId); 
		if (roleAttr == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleAttr(roleAttr);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getGroupsInRole(java.lang.String, java.lang.String)
	 */
	public GroupArrayResponse getGroupsInRole(String serviceId, String roleId) {
		GroupArrayResponse resp = new GroupArrayResponse(ResponseStatus.SUCCESS);
		Group[] groupAry = roleDataService.getGroupsInRole(serviceId, roleId);
		if (groupAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupAry(groupAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getRole(java.lang.String, java.lang.String)
	 */
	public RoleResponse getRole(String serviceId, String roleId) {
		RoleResponse resp = new RoleResponse(ResponseStatus.SUCCESS);
		Role role = roleDataService.getRole(serviceId, roleId);
		if (role == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRole(role);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getRolesInDomain(java.lang.String)
	 */
	public RoleListResponse getRolesInDomain(String domainId) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.getRolesInDomain(domainId); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getRolesInGroup(java.lang.String)
	 */
	public RoleListResponse getRolesInGroup(String groupId) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.getRolesInGroup(groupId); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getUserRoles(java.lang.String)
	 */
	public RoleListResponse getUserRoles(String userId) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.getUserRoles(userId); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;
	}
	
	public RoleListResponse getUserRolesAsFlatList(	String userId) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.getUserRolesAsFlatList(userId); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getUserRolesByDomain(java.lang.String, java.lang.String)
	 */
	public RoleListResponse getUserRolesByDomain(String domainId, String userId) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.getUserRolesByDomain(domainId, userId); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getUserRolesDirect(java.lang.String)
	 */
	public RoleListResponse getUserRolesDirect(String userId) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.getUserRolesDirect(userId); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#getUsersInRole(java.lang.String, java.lang.String)
	 */
	public UserArrayResponse getUsersInRole(String serviceId, String roleId) {
		UserArrayResponse resp = new UserArrayResponse(ResponseStatus.SUCCESS);
		User[] userAry = roleDataService.getUsersInRole(serviceId, roleId); 
		if (userAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setUserAry(userAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#isGroupInRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response isGroupInRole(String serviceId, String roleId,
			String groupId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.isGroupInRole(serviceId, roleId, groupId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#isUserInRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response isUserInRole(String serviceId, String roleId, String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		boolean retval = roleDataService.isUserInRole(serviceId, roleId, userId);
		resp.setResponseValue(new Boolean(retval));

		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#removeAllAttributes(java.lang.String, java.lang.String)
	 */
	public Response removeAllAttributes(String serviceId, String roleId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.removeAllAttributes(serviceId, roleId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#removeAllGroupsFromRole(java.lang.String, java.lang.String)
	 */
	public Response removeAllGroupsFromRole(String serviceId, String roleId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.removeAllGroupsFromRole(serviceId, roleId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#removeAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
	public Response removeAttribute(RoleAttribute attr) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.removeAttribute(attr);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#removeGroupFromRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response removeGroupFromRole(String serviceId, String roleId,
			String groupId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.removeGroupFromRole(serviceId, roleId, groupId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#removeRole(java.lang.String, java.lang.String)
	 */
	public Response removeRole(String serviceId, String roleId) {
		Response resp = new Response(ResponseStatus.SUCCESS);

		    int retval = roleDataService.removeRole(serviceId, roleId);
            if (retval == 0) {
                 resp.setStatus( ResponseStatus.FAILURE);
                return resp;
            }

		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#removeUserFromRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response removeUserFromRole(String serviceId, String roleId,
			String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.removeUserFromRole(serviceId, roleId, userId);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#search(org.openiam.idm.srvc.role.dto.RoleSearch)
	 */
	public RoleListResponse search(RoleSearch search) {
		RoleListResponse resp = new RoleListResponse(ResponseStatus.SUCCESS);
		List<Role> roleAry = roleDataService.search(search); 
		if (roleAry == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setRoleList(roleAry);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#updateAttribute(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
	public Response updateAttribute(RoleAttribute attribute) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.updateAttribute(attribute);
		//if (retval == 0) {
		//	resp.setStatus(ResponseStatus.FAILURE);
		//}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.ws.RoleDataWebService#updateRole(org.openiam.idm.srvc.role.dto.Role)
	 */
	public Response updateRole(Role role) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.updateRole(role);
		return resp;
	}


	public RolePolicyResponse addRolePolicy(RolePolicy rPolicy) {
		RolePolicyResponse resp = new RolePolicyResponse(ResponseStatus.SUCCESS);
		RolePolicy rp = roleDataService.addRolePolicy(rPolicy);
		if (rp == null || rp.getRolePolicyId() == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setRolePolicy(rp);
		}
		return resp;
	}

	public RolePolicyResponse updateRolePolicy(RolePolicy rolePolicy) {
		RolePolicyResponse resp = new RolePolicyResponse(ResponseStatus.SUCCESS);
		RolePolicy rp = roleDataService.updateRolePolicy(rolePolicy);
		if (rp == null || rp.getRolePolicyId() == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setRolePolicy(rp);
		}
		return resp;
	}

	public RolePolicyListResponse getAllRolePolicies(String domainId,String roleId) {
		RolePolicyListResponse resp = new RolePolicyListResponse(ResponseStatus.SUCCESS);
		List<RolePolicy> rp = roleDataService.getAllRolePolicies(domainId, roleId);
		if (rp == null || rp.size() == 0) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setRolePolicy(rp);
		}
		return resp;
	}

	public RolePolicyResponse getRolePolicy(String rolePolicyId) {
		RolePolicyResponse resp = new RolePolicyResponse(ResponseStatus.SUCCESS);
		RolePolicy rp = roleDataService.getRolePolicy(rolePolicyId);
		if (rp == null || rp.getRolePolicyId() == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setRolePolicy(rp);
		}
		return resp;
	}

	public Response assocUserToRole(UserRole userRole) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.assocUserToRole(userRole);
		return resp;
	}

	public Response updateUserRoleAssoc(UserRole userRole) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.updateUserRoleAssoc(userRole);
		return resp;
	}

	public UserRoleResponse getUserRoleById(String userRoleId) {
		UserRoleResponse resp = new UserRoleResponse(ResponseStatus.SUCCESS);
	 	UserRole ur =  roleDataService.getUserRoleById(userRoleId);
		if ( ur != null) {
			resp.setUserRole(ur);
			return resp;
		}
		resp.setStatus(ResponseStatus.FAILURE);
	 	return resp;
	}

	public UserRoleListResponse getUserRolesForUser(String userId) {
		UserRoleListResponse resp = new UserRoleListResponse(ResponseStatus.SUCCESS);
	 	List<UserRole> ur =  roleDataService.getUserRolesForUser(userId);
		if ( ur != null) {
			resp.setUserRoleList(ur);
			return resp;
		}
		resp.setStatus(ResponseStatus.FAILURE);
	 	return resp;
	}
	
	
	public Response removeRolePolicy(RolePolicy rolePolicy) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		roleDataService.removeRolePolicy(rolePolicy);
		return resp;
	}


	public RoleDataService getRoleDataService() {
		return roleDataService;
	}

	public void setRoleDataService(RoleDataService roleDataService) {
		this.roleDataService = roleDataService;
	}


}
