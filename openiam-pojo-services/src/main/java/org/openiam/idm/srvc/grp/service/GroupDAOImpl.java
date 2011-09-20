package org.openiam.idm.srvc.grp.service;

// Generated Jun 12, 2007 10:46:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.HibernateException;
import org.openiam.exception.data.DataException;
import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupSearch;

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
	private Integer maxResultSetSize;

	
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
		} catch (HibernateException re) {
			log.error("Group save failed.", re);
			throw new DataException( re.getMessage(), re.getCause() ); 
		}		
	}
	


	public int remove(Group instance) {
		log.debug("deleting Group instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("Group id=" + instance.getGrpId() + " successfully updated.");
			return 1;
		} catch (HibernateException re) {
			log.error("Group delete failed", re);
			throw new DataException( re.getMessage(), re.getCause() ); 
		}		
	}

	public void update(Group instance) {
		log.debug("merging Group instance. GrpId = " + instance.getGrpId());
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("Group id=" + instance.getGrpId() + " successfully updated.");
		} catch (HibernateException re) {
			log.error("Group update failed", re);
			throw new DataException( re.getMessage(), re.getCause() ); 
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
			
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw new DataException( re.getMessage(), re.getCause() ); 
		}
	}
	
	public List<Group> search(GroupSearch search) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Group.class, "grp");
		crit.setMaxResults(maxResultSetSize);
		
		if (search.getGrpId() != null && search.getGrpId().length() > 0 ) {
			log.debug("search: grpId=" + search.getGrpId() );
			crit.add(Restrictions.eq("grpId",search.getGrpId()));
		}
		if (search.getGrpName() != null && search.getGrpName().length() > 0 ) {
			log.debug("search: grpName=" + search.getGrpName() );
			crit.add(Restrictions.like("grpName",search.getGrpName()));
		}
		if (search.getOwnerId() != null && search.getOwnerId().length() > 0 ) {
			log.debug("search: ownerId=" + search.getOwnerId() );
			crit.add(Restrictions.eq("ownerId",search.getOwnerId()));
		}
		if (search.getInternalGroupId() != null && search.getInternalGroupId().length() > 0 ) {
			log.debug("search: interGroupId=" + search.getInternalGroupId() );
			crit.add(Restrictions.eq("internalGroupId",search.getInternalGroupId()));
		}
		crit.addOrder(Order.asc("grpName"));
		
		List<Group> results = (List<Group>)crit.list();
		return results;		
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

		return parentGroup;
		
	}
	
	/**
	 * Returns a list of Groups that a user is associated with
	 * @return
	 */
	public List<Group> findGroupsForUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select grp  from Group as grp, UserGroup ug " +
						" where ug.userId = :userId and grp.grpId = ug.grpId ");
		

		qry.setString("userId", userId);
		List<Group> result = (List<Group>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;			
	}
	
	public List<Group> findGroupNotLinkedToUser(String userId, String parentGroupId) {
		
	   	Session session = sessionFactory.getCurrentSession();
	    
    	try{
    		SQLQuery qry = session.createSQLQuery("SELECT  GRP_ID, GRP_NAME, CREATE_DATE, CREATED_BY, COMPANY_ID,  " +
    				" PARENT_GRP_ID, INHERIT_FROM_PARENT, PROVISION_METHOD, PROVISION_OBJ_NAME, " +
    				" TYPE_ID, GROUP_CLASS, GROUP_DESC, STATUS, LAST_UPDATE, LAST_UPDATED_BY, INTERNAL_GROUP_ID, OWNER_ID  " +
				 "  FROM 	GRP g  " +
				 "  WHERE g.GRP_ID NOT IN (SELECT GRP_ID FROM USER_GRP ug WHERE ug.USER_ID = :userId ) ");
	    	
	    	
	    	qry.addEntity(Group.class);
			qry.setString("userId", userId);
			
	
			List<Group> result = (List<Group>) qry.list();
			if (result == null || result.size() == 0)
				return null;
			return result;		
	    }catch(HibernateException re) {
			log.error("findGroupNotLinkedToUser", re);
			throw new DataException( re.getMessage(), re.getCause() );      		
		}

		
	}





	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public Integer getMaxResultSetSize() {
		return maxResultSetSize;
	}

	public void setMaxResultSetSize(Integer maxResultSetSize) {
		this.maxResultSetSize = maxResultSetSize;
	}
	
}


