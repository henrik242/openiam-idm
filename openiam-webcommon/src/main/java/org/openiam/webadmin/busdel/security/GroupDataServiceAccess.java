package org.openiam.webadmin.busdel.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;

import org.apache.struts.util.LabelValueBean;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.webadmin.busdel.base.NavigationAccess;
import org.springframework.web.context.WebApplicationContext;

import diamelle.security.auth.GroupValue;

/**
 * Business delegate for the GroupDataService.
 * 
 * @author Suneet Shah
 * @version 2
 */


public class GroupDataServiceAccess  extends NavigationAccess  {

   
    GroupDataService groupDataService = null;

	public GroupDataServiceAccess(WebApplicationContext  webContext) {
		groupDataService = (GroupDataService)webContext.getBean("groupManager");
	}

	public void addAttribute(GroupAttribute attribute) {
		groupDataService.addAttribute(attribute);
		
	}

	public void addGroup(Group grp) {
		groupDataService.addGroup(grp);
		
	}

	public void addGroupToRole(String groupId, String serviceId, String roleId) {
		//groupDataService.addGroupToRole(groupId, serviceId,roleId);
		
	}

	public void addUserToGroup(String grpId, String userId) {
		groupDataService.addUserToGroup(grpId, userId);
		
	}

	public Map<String, GroupAttribute> getAllAttributes(String groupId) {
		return groupDataService.getAllAttributes(groupId);
	}

	public List getAllGroups(boolean subgroups) {
		return  groupDataService.getAllGroupsWithDependents(subgroups);
	}

	public GroupAttribute getAttribute(String attrId) {
		return groupDataService.getAttribute(attrId);
	}

	public List<Group> getChildGroups(String parentGroupId, boolean subgroups) {
		return groupDataService.getChildGroups(parentGroupId, subgroups);
	}

	public Group getGroup(String grpId) {
		return groupDataService.getGroup(grpId);
	}

	public List getGroupRoles(String parentGroupId) {
		//return groupDataService.getGroupRoles(parentGroupId);
		return null;
	}

	public List getGroupsInRole(String serviceId, String roleId) {
		//return groupDataService.getGroupsInRole(serviceId, roleId);
		return null;
		
	}

	public Group getParentGroup(String groupId) {
		return groupDataService.getParentGroup(groupId,true);
	}

	public List<Group> getUserGroups(String userId) {
		//return groupDataService.getUserGroups(userId);
		List<Group> newList = new LinkedList<Group>();
		List grpList = groupDataService.getUserInGroups(userId);
		// test
		if (grpList == null || grpList.size() == 0)
			return null;
		
		return grpList;
		
	//	int size =grpList.size();
	//	for (int i=0;i<size;i++) {
	//		Object[] obj = (Object[])grpList.get(i);
	//		Group grp = (Group)obj[0];
	//		newList.add(grp);
	//	}
	//	return newList;
		
	}
	

	public List<User> getUsersByGroupId(String grpId) {
		Set<User> userSet = groupDataService.getUsersByGroup(grpId);
		
		List<User> userList = new ArrayList(userSet);
		return userList;
		
	}


	public boolean isUserInGroup(String groupId,String userId) {
		return groupDataService.isUserInGroup(groupId,userId);
	}

	public void removeAllAttributes(String groupId) {
		groupDataService.removeAllAttributes(groupId);
		
	}

/*	public void removeAllGroupsFromRole(String serviceId, String roleId) {
		groupDataService.removeAllGroupsFromRole(serviceId, roleId);
		
	}
*/
	public void removeAttribute(GroupAttribute attr) {
		groupDataService.removeAttribute(attr);
		
	}

	public int removeChildGroups(String parentGroupId) {
		return groupDataService.removeChildGroups(parentGroupId);
	}

	public void removeGroup(String grpId) {
		groupDataService.removeGroup(grpId);
		
	}

/*	public void removeGroupFromRole(String groupId, String serviceId, String roleId) {
		groupDataService.removeGroupFromRole(groupId, serviceId, roleId);
		
	}*/

	public void removeUserFromGroup(String groupId, String userId) {
		groupDataService.removeUserFromGroup(groupId, userId);
		
	}

	public void updateAttribute(GroupAttribute attribute) {
		groupDataService.updateAttribute(attribute);
		
	}

	public void updateGroup(Group grp) {
		groupDataService.updateGroup(grp);
		
	}
	
	/*
	 * Returns all groups as Struts <code>LabelValueBean</code> objects.
	 */
	public List getAllGroupListAsLabels() {
		List<LabelValueBean> newCodeList = new LinkedList();
		List<Group> grpList = getAllGroups(false);
		if (grpList != null && grpList.size() > 0) {
			newCodeList.add(new LabelValueBean("",""));
	    	for (int i = 0; i < grpList.size(); i++) {       		
	    		Group val = grpList.get(i);
	    	 	LabelValueBean label = new LabelValueBean(val.getGrpName(),val.getGrpId());
	    	 	newCodeList.add(label);
	    	}
		}
		return newCodeList;
    }
}

    



