package org.openiam.idm.srvc.grp.service;

// Generated Jun 12, 2007 10:46:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.HibernateException;
import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDAO;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.UserNote;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDAO;
/**
 * Data access object interface for Group. 
 * @see org.openiam.idm.srvc.grp.dto.Group
 * @author Suneet Shah
 */
public class GroupDAOImpl implements org.openiam.idm.srvc.grp.service.GroupDAO {

	protected UserDAO userDao;

	private static final Log log = LogFactory.getLog(GroupDAOImpl.class);

	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void add(Group instance) {
		log.debug("persisting Group instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}		
	}
	
	public void addUserToGroup(String groupId, String userId) {
		
		log.debug("adding user to group");
		
	
		Group grp = findById(groupId);
		User usr =  userDao.findById(userId);

		System.out.println("Group = " + grp);
		System.out.println("User=" + usr);
		System.out.println("In addUserToGroup - users=" + grp.getUsers());
		
		grp.getUsers().add(usr);
				
		try {
			sessionFactory.getCurrentSession().save(grp);
			log.debug("persist user to group successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
		
	}
	
	public void removeUserFromGroup(String groupId, String userId) {
		
		log.debug("In removeUserFromGroup..groupId=" + groupId);
		log.debug("In removeUserFromGroup..userId=" + userId);
		
		System.out.println("In remove user from Group.... groupId=" + groupId);
		System.out.println(".... userId=" + userId);
		
		Group group = findById(groupId);
		if (group == null) {
			log.error("Group not found for groupId =" + groupId);
			throw new ObjectNotFoundException();
		}
		org.hibernate.Hibernate.initialize(group.getUsers());
		Set<User> userSet = group.getUsers();
		if (userSet == null || userSet.isEmpty()) {
			return;
		}
		
		Iterator<User> it =  userSet.iterator();
		while (it.hasNext()) {
			User usr = it.next();
			if (usr.getUserId().equalsIgnoreCase(userId)) {
				System.out.println("removed userId=" + userId + "from Group");
				log.debug("removed userId=" + userId + "from Group");
				it.remove();
			}		
		}
				
	}



	public void remove(Group instance) {
		log.debug("deleting Group instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}			
	}

	public void update(Group instance) {
		log.debug("merging Group instance. GrpId = " + instance.getGrpId());
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}		
	}
	
	

	public Group findById(java.lang.String id) {
	
		return findById(id,false);
	
	}

	public Group findById(java.lang.String id, boolean dependants) {
		log.debug("getting Grp instance with id: " + id);
		try {
			Group instance = (Group) sessionFactory.getCurrentSession().get(
					"org.openiam.idm.srvc.grp.dto.Group", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			if (instance != null) {	
				org.hibernate.Hibernate.initialize(instance.getUsers());
			}			
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public List<Group> findRootGroups() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.grp.dto.Group g " +
				" where g.parentGrpId is null order by g.grpId asc");
	//	qry.setCacheable(true);
		List<Group> results = (List<Group>)qry.list();
		return results;
	}
	public List<Group> findAllGroups() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.grp.dto.Group g " +
				" order by g.grpName asc");
	//	qry.setCacheable(true);
		List<Group> results = (List<Group>)qry.list();
		return results;
	}
	
	public List<Group> findGroupsInRole(String serviceId, String roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery(" from  org.openiam.idm.srvc.grp.dto.Group grp " +
										" 		inner join grp.groupRoles as groupRole " +
										" where groupRole.role.serviceId = :serviceId and " +
										" 		groupRole.role.roleId = :roleId ");
		qry.setString("serviceId", serviceId);
		qry.setString("roleId", roleId);

		List<Group> results = (List<Group>)qry.list();
		return results;		
	}
	
	


	
	public List<Group> findChildGroup(String parentGroupId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.grp.dto.Group g where g.parentGrpId = :parentId order by g.grpId asc");
		qry.setString("parentId", parentGroupId);
	//	qry.setCacheable(true);
		List<Group> results = (List<Group>)qry.list();
		return results;
	}

	/**
	 * Removes the groups specified by the groupIdList. groupIdList is a string containing a concatenated
	 * list of groupIds.
	 * @param groupIdList
	 * @return The number of entities deleted.
	 */
	public int removeGroupList(String groupIdList) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.grp.dto.Group g  " + 
					" where g.grpId in (" + groupIdList + ")" );
		return qry.executeUpdate();		
	}	
	
	public Group findParent(String groupId, boolean dependants) {
				
		// get the group object for the groupId
		Group curGroup = findById(groupId);
		// TODO Throw exception if the curGroup is null. That means that there is no group for this groupId
		if (curGroup == null) {
			log.error("Group for groupId=" + groupId + "  not found.");
			throw new ObjectNotFoundException();
		}
		
		if (curGroup.getParentGrpId() == null ) {
			log.debug("groupId=" + groupId + " does not contain a parentGroupId");
			return null;
		}
		
		// get the parent group object
		
		Group parentGroup = findById(curGroup.getParentGrpId());
		if (parentGroup == null) {
			log.error("Group for parent groupId=" + curGroup.getParentGrpId()  + "  not found.");
			throw new ObjectNotFoundException();					
		}
		if (dependants) {
			// initalize the collections that are marked to be loaded by lazy loading
			org.hibernate.Hibernate.initialize(parentGroup.getUsers());
		}
		return parentGroup;
		
	}
	
	/**
	 * Returns a list of Groups that a user is associated with
	 * @return
	 */
	public List<Group> findGroupsForUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select grp  from Group as grp " +
						" join grp.users as usr " +
						" where usr.userId = :userId ");
		qry.setString("userId", userId);
		List<Group> result = (List<Group>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;			
	}

	




	public User findUserInGroup(String groupId,String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select u from Group as g " +
						" join g.users as u " +
						" where u.userId = :userId and g.grpId = :groupId ");
		qry.setString("groupId", groupId);
		qry.setString("userId", userId);
		return (User)qry.uniqueResult();

		
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
}


