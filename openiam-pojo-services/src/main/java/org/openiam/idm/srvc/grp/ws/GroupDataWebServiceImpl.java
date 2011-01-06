package org.openiam.idm.srvc.grp.ws;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.openiam.idm.srvc.grp.dto.*;
import org.openiam.idm.srvc.grp.service.*;

import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDAO;
import org.openiam.idm.srvc.user.ws.UserListResponse;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.exception.data.DataException;
import org.openiam.exception.data.ObjectNotFoundException;

/**
 * <code>GroupDataServiceImpl</code> provides a service to manage groups as
 * well as related objects such as Users. Groups are stored in an hierarchical
 * relationship. A user belongs to one or more groups.<br>
 * Groups are often modeled after an organizations structure.
 * 
 * @author Suneet Shah
 * @version 2.0
 */

@WebService(endpointInterface = "org.openiam.idm.srvc.grp.ws.GroupDataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/grp/service", 
		portName = "GroupDataWebServicePort", 
		serviceName = "GroupDataWebService")
public class GroupDataWebServiceImpl implements GroupDataWebService {
	protected GroupDataService groupManager;
		
	private static final Log log = LogFactory.getLog(GroupDataWebServiceImpl.class);

	public GroupDataWebServiceImpl() {

	}

	public GroupDataService getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(GroupDataService groupManager) {
		this.groupManager = groupManager;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#addAttribute(org.openiam.idm.srvc.grp.dto.GroupAttribute)
	 */
	public Response addAttribute(GroupAttribute attribute) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.addAttribute(attribute);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#addGroup(org.openiam.idm.srvc.grp.dto.Group)
	 */
	public GroupResponse addGroup(Group grp) {
		GroupResponse resp = new GroupResponse(ResponseStatus.SUCCESS);
		groupManager.addGroup(grp);
		if (grp.getGrpId() == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		resp.setGroup(grp);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#addUserToGroup(java.lang.String, java.lang.String)
	 */
	public Response addUserToGroup(String grpId, String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.addUserToGroup(grpId, userId); 
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getAllAttributes(java.lang.String)
	 */
	public GroupAttrMapResponse getAllAttributes(String groupId) {
		GroupAttrMapResponse resp = new GroupAttrMapResponse(ResponseStatus.SUCCESS);
		Map<String, GroupAttribute> attrMap = groupManager.getAllAttributes(groupId); 
		if (attrMap == null || attrMap.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupAttrMap(attrMap);
		return resp;

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getAllGroups()
	 */
	public GroupListResponse getAllGroups() {
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.getAllGroups(); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getAllGroupsWithDependents(boolean)
	 */
	public GroupListResponse getAllGroupsWithDependents(boolean subgroups) {
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.getAllGroupsWithDependents(subgroups); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getAttribute(java.lang.String)
	 */
	public GroupAttributeResponse getAttribute(String attrId) {
		GroupAttributeResponse resp = new GroupAttributeResponse(ResponseStatus.SUCCESS);
		GroupAttribute attr = groupManager.getAttribute(attrId);  
		if (attr == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		resp.setGroupAttr(attr);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getChildGroups(java.lang.String, boolean)
	 */
	public GroupListResponse getChildGroups(String parentGroupId,		boolean subgroups) {
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.getChildGroups(parentGroupId, subgroups); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getGroup(java.lang.String)
	 */
	public GroupResponse getGroup(String grpId) {
		GroupResponse resp = new GroupResponse(ResponseStatus.SUCCESS);
		Group grp = groupManager.getGroup(grpId); 
		if (grp == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroup(grp);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getGroupWithDependants(java.lang.String)
	 */
	public GroupResponse getGroupWithDependants(String grpId) {
		GroupResponse resp = new GroupResponse(ResponseStatus.SUCCESS);
		Group grp = groupManager.getGroupWithDependants(grpId); 
		if (grp == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroup(grp);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getGroupsNotLinkedToUser(java.lang.String, java.lang.String, boolean)
	 */
	public GroupListResponse getGroupsNotLinkedToUser(String userId,
			String parentGroupId, boolean nested) {
		
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.getGroupsNotLinkedToUser(userId, parentGroupId, nested); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getParentGroup(java.lang.String, boolean)
	 */
	public GroupResponse getParentGroup(String groupId, boolean dependants) {
		GroupResponse resp = new GroupResponse(ResponseStatus.SUCCESS);
		Group grp = groupManager.getParentGroup(groupId, dependants); 
		if (grp == null) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroup(grp);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getUserInGroups(java.lang.String)
	 */
	public GroupListResponse getUserInGroups(String userId) {
		log.info("getUserInGroups: userId=" + userId);
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.getUserInGroups(userId); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;
	}
	
	
	public GroupListResponse getUserInGroupsAsFlatList(	String userId) {
		log.info("getUserInGroupsAsFlatList: userId=" + userId);
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.getUserInGroupsAsFlatList(userId); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#getUsersByGroup(java.lang.String)
	 */
	public UserListResponse getUsersByGroup(String grpId) {
		UserListResponse resp = new UserListResponse(ResponseStatus.SUCCESS);
		List<User> userList = groupManager.getUsersByGroup(grpId); 
		if (userList == null || userList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setUserList(userList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#isUserInGroup(java.lang.String, java.lang.String)
	 */
	public Response isUserInGroup(String groupId, String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		boolean retval = groupManager.isUserInGroup(groupId, userId); 
		resp.setResponseValue(new Boolean(retval));
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#removeAllAttributes(java.lang.String)
	 */
	public Response removeAllAttributes(String groupId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.removeAllAttributes(groupId); 
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#removeAttribute(org.openiam.idm.srvc.grp.dto.GroupAttribute)
	 */
	public Response removeAttribute(GroupAttribute attr) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.removeAttribute(attr); 

		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#removeChildGroups(java.lang.String)
	 */
	public Response removeChildGroups(String parentGroupId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.removeChildGroups(parentGroupId); 
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#removeGroup(java.lang.String)
	 */
	public Response removeGroup(String grpId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.removeGroup(grpId); 
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#removeUserFromGroup(java.lang.String, java.lang.String)
	 */
	public Response removeUserFromGroup(String groupId, String userId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		groupManager.removeUserFromGroup(groupId, userId); 
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#saveAttributes(org.openiam.idm.srvc.grp.dto.GroupAttribute[])
	 */
	public Response saveAttributes(GroupAttribute[] groupAttr) {
		groupManager.saveAttributes(groupAttr);
		return  new Response(ResponseStatus.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#search(org.openiam.idm.srvc.grp.dto.GroupSearch)
	 */
	public GroupListResponse search(GroupSearch search) {
		GroupListResponse resp = new GroupListResponse(ResponseStatus.SUCCESS);
		List<Group> grpList = groupManager.search(search); 
		if (grpList == null || grpList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
			return resp;
		}
		resp.setGroupList(grpList);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#updateAttribute(org.openiam.idm.srvc.grp.dto.GroupAttribute)
	 */
	public Response updateAttribute(GroupAttribute attribute) {
		groupManager.updateAttribute(attribute); 
		return  new Response(ResponseStatus.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.ws.GroupDataWebService#updateGroup(org.openiam.idm.srvc.grp.dto.Group)
	 */
	public GroupResponse updateGroup(Group grp) {
		GroupResponse resp = new GroupResponse(ResponseStatus.SUCCESS);	
		groupManager.updateGroup(grp); 
		
		if (grp.getGrpId() == null) {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		resp.setGroup(grp);
		return  resp;
	}





}
