package org.openiam.idm.srvc.grp.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import javax.jws.WebService;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.grp.dto.*;
import org.openiam.idm.srvc.grp.service.GroupAttributeDAO;

import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDAO;

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
//Note: as per spec serviceName goes in impl class and name goes in interface
@WebService(endpointInterface = "org.openiam.idm.srvc.grp.service.GroupDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/grp/service", 
		serviceName = "GroupDataWebService")
public class GroupDataServiceImpl implements GroupDataService {
	private GroupDAO groupDao;
	private GroupAttributeDAO groupAttrDao;

	private UserDAO userDao;
	//protected GroupRoleDAO groupRoleDao;
	
	private static final Log log = LogFactory.getLog(GroupDataServiceImpl.class);

	public GroupDataServiceImpl() {

	}

	public GroupDataServiceImpl(GroupDAO groupDao) {
		super();
		this.groupDao = groupDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getAllGroups(boolean)
	 */
	public List<Group> getAllGroupsWithDependents(boolean subgroups) {
		List<Group> groupList = new ArrayList<Group>();
		if (!subgroups) {
			groupList = groupDao.findRootGroups();
			return groupList;
		} else {
			if (subgroups) {
				groupList = getRecursiveChildGroup(null, groupList);
				return groupList;
			}
		}
		return null;

	}
	/**
	 * Returns a list of Group objects that is flat in structure.
	 * @return
	 */
	public List<Group> getAllGroups() {
		return groupDao.findAllGroups();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#addGroup(org.openiam.idm.srvc.grp.dto.Group)
	 */
	public void addGroup(Group grp) {
		if (grp == null)
			throw new NullPointerException("grp object is null");
		if (grp.getGrpId() == null)
			throw new NullPointerException("grp id is null");

		groupDao.add(grp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getGroup(java.lang.String)
	 */
	public Group getGroup(String grpId) {
		if (grpId == null)
			throw new NullPointerException("grp id is null");
		return groupDao.findById(grpId);
	}
	
	/**
	 * This method retrieves an existing group along with an dependant objects that it may 
	 * contain. null is returned if the groupId is not found.
	 * @param grpId
	 * @return
	 */
	public Group getGroupWithDependants(String grpId) {
		if (grpId == null)
			throw new NullPointerException("grp id is null");
		Group grp = groupDao.findById(grpId,true);	
		return grp;	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#updateGroup(org.openiam.idm.srvc.grp.dto.Group)
	 */
	public void updateGroup(Group grp) {
		if (grp == null)
			throw new NullPointerException("grp object is null");
		if (grp.getGrpId() == null)
			throw new NullPointerException("grp id is null");

		groupDao.update(grp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#removeGroup(java.lang.String)
	 */
	public void removeGroup(String grpId) {

		if (grpId == null)
			throw new NullPointerException("grp id is null");

		Group grp = new Group();
		grp.setGrpId(grpId);

		removeChildGroups(grpId);
		this.groupAttrDao.removeAttributesByParent(grpId);
		groupDao.remove(grp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getChildGroups(java.lang.String,
	 *      boolean)
	 */
	public List<Group> getChildGroups(String parentGroupId, boolean subgroups) {
		List<Group> groupList = new ArrayList<Group>();

		if (!subgroups) {
			groupList = groupDao.findChildGroup(parentGroupId);
			return groupList;
		} else {
			if (subgroups) {
				groupList = getRecursiveChildGroup(parentGroupId, groupList);
				return groupList;
			}
		}
		return null;

	}

	private List<Group> getRecursiveChildGroup(String parentGroupId,
			List<Group> groupList) {

		if (parentGroupId == null) {
			groupList = groupDao.findRootGroups();
		} else {
			groupList = groupDao.findChildGroup(parentGroupId);
		}
		if (groupList == null || groupList.isEmpty()) {
			return null;
		}
		int size = groupList.size();
		for (int i = 0; i < size; i++) {
			Group grp = groupList.get(i);
			// check for child group
			grp.setSubGroup(getRecursiveChildGroup(grp.getGrpId(), groupList));
		}
		return groupList;
	}

	private String getRecursiveGroupId(String parentGroupId,
			List<Group> groupList) {
		StringBuffer groupIdBuf = new StringBuffer();

		groupList = groupDao.findChildGroup(parentGroupId);
		if (groupList == null || groupList.isEmpty()) {
			return null;
		}
		int size = groupList.size();
		for (int i = 0; i < size; i++) {
			Group grp = groupList.get(i);
			if (groupIdBuf.length() > 0) {
				groupIdBuf.append(",");
			}
			groupIdBuf.append(" '" + grp.getGrpId() + "' ");

			// check for child group

			String groupStr = getRecursiveGroupId(grp.getGrpId(), groupList);
			if (groupStr != null) {
				if (groupIdBuf.length() > 0) {
					groupIdBuf.append(",");
				}
				groupIdBuf.append(groupStr);
			}
			log.debug("Groupids after = " + groupIdBuf.toString());
		}
		return groupIdBuf.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#removeChildGroups(java.lang.String)
	 */
	public int removeChildGroups(String parentGroupId) {
		StringBuffer groupIdBuf = new StringBuffer();
		List<Group> groupList = new ArrayList<Group>();

		groupList = groupDao.findChildGroup(parentGroupId);

		if (groupList == null || groupList.isEmpty()) {
			// nothing to delete
			return 0;
		}
		int size = groupList.size();
		for (int i = 0; i < size; i++) {
			Group grp = groupList.get(i);
			if (groupIdBuf.length() > 0) {
				groupIdBuf.append(",");
			}
			groupIdBuf.append(" '" + grp.getGrpId() + "' ");

			String groupStr = getRecursiveGroupId(grp.getGrpId(), groupList);
			if (groupStr != null) {
				if (groupIdBuf.length() > 0) {
					groupIdBuf.append(",");
				}
				groupIdBuf.append(groupStr);
			}

		}

		// remove dependant objects first
		String groupIdList = groupIdBuf.toString();
		this.groupAttrDao.removeAttributesForGroupList(groupIdList);
		return groupDao.removeGroupList(groupIdList);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getParentGroup(java.lang.String)
	 */
	public Group getParentGroup(String groupId, boolean dependants) {
		if (groupId == null)
			throw new NullPointerException("parentGroupId id is null");

		return groupDao.findParent(groupId, dependants);

	}



	/**
	 * 
	 * 
	 *  /* --------------------- Group Methods Related to Users --------------
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#isUserInGroup(java.lang.String)
	 */
	public boolean isUserInGroup(String groupId, String userId) {
		if (groupId == null)
			throw new NullPointerException("parentGroupId id is null");
		
		// querying the db will be faster then searching the collection of users which could be very large
		User u = groupDao.findUserInGroup(groupId, userId);
		if (u == null)
			return false;
		return true;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getUserGroups(java.lang.String)
	 */
	public List<Group> getUserInGroups(String userId) {
		if (userId == null)
			throw new NullPointerException("userId id is null");

		List<Group> groupList = groupDao.findGroupsForUser(userId);
		if (groupList == null || groupList.size() == 0)
			return null;
		return groupList;
	}
	
	/**
	 * Return an array of Groups that a user does not belong to
	 * @param userId
	 * @param nested - True, traverse the group hierarchy.  False, search the current hierarchy
	 * @param parentGroupId - Group where the traversing will start
	 * @return
	 */
	public List<Group> getGroupsNotLinkedToUser(String userId, String parentGroupId, boolean nested) {

		if (userId == null)
			throw new NullPointerException("userId id is null");

		List<Group> groupList = groupDao.findGroupNotLinkedToUser(userId, parentGroupId);
		if (groupList == null || groupList.size() == 0)
			return null;
		return groupList;
	}		
	

	/**
	 * This method adds the user to a group .<br>
	 * For example:
	 * <p>
	 * <code>
	 *     grpManager.addUserToGroup(groupId,userId);<br>
	 * </code>
	 * 
	 * 
	 * @param userId
	 *            User to be added to group.
	 * 
	 * @param grpId
	 *            Group to which user will be added .
	 */
	public void addUserToGroup(String groupId, String userId) {
		if (groupId == null)
			throw new NullPointerException("grpId id is null");
		if (userId == null)
			throw new NullPointerException("userId id is null");
	
		groupDao.addUserToGroup(groupId, userId);

		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getUsersByGroupId(java.lang.String)
	 */
	public Set<User> getUsersByGroup(String grpId) {
		if (grpId == null)
			throw new NullPointerException("grpId id is null");

		Group group = groupDao.findById(grpId);
		//org.hibernate.Hibernate.initialize(group.getUsers());
		return group.getUsers();

	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#removeUserFromGroup(java.lang.String,
	 *      java.lang.String)
	 */
	public void removeUserFromGroup(String groupId, String userId) {
		if (groupId == null)
			throw new NullPointerException("grpId id is null");
		if (userId == null)
			throw new NullPointerException("userId id is null");
		

		this.groupDao.removeUserFromGroup(groupId, userId);
		

		
		
		//this.userGroupDao.removeUserGroup(groupId, userId);
	}

	

	/* -------- Methods for Attributes ---------- */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#addAttribute(org.openiam.idm.srvc.grp.dto.GroupAttribute)
	 */
	public void addAttribute(GroupAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getGroupId() == null) {
			throw new NullPointerException(
					"Group has not been associated with this attribute.");
		}

		groupAttrDao.add(attribute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#updateAttribute(org.openiam.idm.srvc.grp.dto.GroupAttribute)
	 */
	public void updateAttribute(GroupAttribute attribute) {
		if (attribute == null)
			throw new NullPointerException("Attribute can not be null");
		if (attribute.getId() == null) {
			throw new NullPointerException("Attribute id is null");
		}
		if (attribute.getGroupId() == null) {
			throw new NullPointerException(
					"Group has not been associated with this attribute.");
		}

		groupAttrDao.update(attribute);

	}
	
	/**
	 * Saves a collection of attributes. The method will determine if the add or update methods should be 
	 * called internally.
	 */
	public void saveAttributes(GroupAttribute[] groupAttr) {
		if (groupAttr == null) {
			throw new NullPointerException("Attr array is null");
		}
		int size = groupAttr.length;
		for (int i=0; i<size; i++) {
			if (groupAttr[i].getId() == null) {
				// add
				addAttribute(groupAttr[i]);
			}else {
				// update
				updateAttribute(groupAttr[i]);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getAllAttributes(java.lang.String)
	 */
	public Map<String, GroupAttribute> getAllAttributes(String groupId) {

		Map<String, GroupAttribute> attrMap = null;

		if (groupId == null) {
			throw new NullPointerException("groupId is null");
		}

		Group grp = groupDao.findById(groupId);
		attrMap = grp.getAttributes();
		if (attrMap != null && attrMap.isEmpty())
			return null;
		return attrMap;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getAttribute(java.lang.String)
	 */
	public GroupAttribute getAttribute(String attrId) {
		if (attrId == null) {
			throw new NullPointerException("attrId is null");
		}
		return groupAttrDao.findById(attrId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#removeAttribute(org.openiam.idm.srvc.grp.dto.GroupAttribute)
	 */
	public void removeAttribute(GroupAttribute attr) {
		if (attr == null) {
			throw new NullPointerException("attr is null");
		}
		if (attr.getId() == null) {
			throw new NullPointerException("attrId is null");
		}

		groupAttrDao.remove(attr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#removeAllAttributes(java.lang.String)
	 */
	public void removeAllAttributes(String groupId) {
		if (groupId == null) {
			throw new NullPointerException("groupId is null");
		}
		this.groupAttrDao.removeAttributesByParent(groupId);
	}

	/*--  Spring framework related getters and setters. ---*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getGroupAttrDao()
	 */
	public GroupAttributeDAO getGroupAttrDao() {
		return groupAttrDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#setGroupAttrDao(org.openiam.idm.srvc.grp.service.GroupAttributeDAO)
	 */
	public void setGroupAttrDao(GroupAttributeDAO groupAttrDao) {
		this.groupAttrDao = groupAttrDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#getGroupDao()
	 */
	public GroupDAO getGroupDao() {
		return groupDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.grp.service.GroupDataService#setGroupDao(org.openiam.idm.srvc.grp.service.GroupDAO)
	 */
	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}



	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}


}
