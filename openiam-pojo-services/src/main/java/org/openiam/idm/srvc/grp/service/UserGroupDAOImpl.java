package org.openiam.idm.srvc.grp.service;

// Generated Jul 18, 2009 8:49:10 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.UserGroup;
import org.openiam.idm.srvc.user.dto.User;

import static org.hibernate.criterion.Example.create;

/**
 * Interface for the User-Group DAO which manages the relationship between users and groups.
 * @see org.openiam.idm.srvc.grp.dto.UserGroup
 * @author Suneet Shah
 */
public class UserGroupDAOImpl implements UserGroupDAO {

	private static final Log log = LogFactory.getLog(UserGroupDAOImpl.class);

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

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.service.UserGroupDAO#persist(org.openiam.idm.srvc.grp.dto.UserGroup)
	 */
	public void add(UserGroup transientInstance) {
		log.debug("persisting UserGrp instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.service.UserGroupDAO#delete(org.openiam.idm.srvc.grp.dto.UserGroup)
	 */
	public void remove(UserGroup persistentInstance) {
		log.debug("deleting UserGrp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<UserGroup> findUserInGroup(String groupId, String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.grp.dto.UserGroup ug " + 
					" where ug.userId = :userId and ug.grpId = :groupId ");

		qry.setString("groupId", groupId);
		qry.setString("userId", userId);
		return qry.list();		
	}
	
	public List<User> findUserByGroup(String groupId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select usr from org.openiam.idm.srvc.user.dto.User as usr, UserGroup ug " +
						" where ug.grpId = :groupId and ug.userId = usr.userId " +
						" order by usr.lastName, usr.firstName ");
		

		qry.setString("groupId", groupId);
		List<User> result = (List<User>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;			
	}
	
	
	public void removeUserFromGroup(String grpId, String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.grp.dto.UserGroup ug " + 
					" where ug.grpId = :grpId and ug.userId = :userId ");
		qry.setString("grpId", grpId);
		qry.setString("userId", userId);
		qry.executeUpdate();		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.service.UserGroupDAO#update(org.openiam.idm.srvc.grp.dto.UserGroup)
	 */
	public UserGroup update(UserGroup detachedInstance) {
		log.debug("merging UserGrp instance");
		try {
			UserGroup result = (UserGroup) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.grp.service.UserGroupDAO#findById(java.lang.String)
	 */
	public UserGroup findById(java.lang.String id) {
		log.debug("getting UserGrp instance with id: " + id);
		try {
			UserGroup instance = (UserGroup) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.grp.dto.UserGroup", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}


}
