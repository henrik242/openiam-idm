package org.openiam.idm.srvc.role.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.role.dto.UserRole;
import org.openiam.idm.srvc.user.dto.User;

import static org.hibernate.criterion.Example.create;

/**
 * DAO implementation for the UserRole. Manages the relationship between user and role.
 * @see org.openiam.idm.srvc.role.dto.UserRole
 * @author Hibernate Tools
 */
public class UserRoleDAOImpl implements UserRoleDAO {

	private static final Log log = LogFactory.getLog(UserRoleDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.role.service.UserRoleDAO#add(org.openiam.idm.srvc.role.dto.UserRole)
	 */
	public void add(UserRole transientInstance) {
		log.debug("persisting UserRole instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.service.UserRoleDAO#remove(org.openiam.idm.srvc.role.dto.UserRole)
	 */
	public void remove(UserRole persistentInstance) {
		log.debug("deleting UserRole instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.service.UserRoleDAO#update(org.openiam.idm.srvc.role.dto.UserRole)
	 */
	public UserRole update(UserRole detachedInstance) {
		log.debug("merging UserRole instance");
		try {
			UserRole result = (UserRole) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.service.UserRoleDAO#findById(java.lang.String)
	 */
	public UserRole findById(java.lang.String id) {
		log.debug("getting UserRole instance with id: " + id);
		try {
			UserRole instance = (UserRole) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.role.dto.UserRole", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UserRole> findUserRoleByUser(String userId) {
		
		
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select ur from UserRole ur " +
						" where ur.userId = :userId " +
						" order by ur.roleId ");
		
		qry.setString("userId", userId);
		List<UserRole> result = (List<UserRole>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;			
	}
	
	public List<User> findUserByRole(String domainId, String roleId) {
		
		log.debug("findUserByRole: domainId=" + domainId);
		log.debug("findUserByRole: roleId=" + roleId);
		
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select usr from org.openiam.idm.srvc.user.dto.User as usr, UserRole ur " +
						" where ur.serviceId = :domainId and ur.roleId = :roleId and ur.userId = usr.userId " +
						" order by usr.lastName, usr.firstName ");
		
		qry.setString("domainId",domainId);
		qry.setString("roleId",roleId);
		List<User> result = (List<User>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;			
	}
	
	public void removeUserFromRole(String domainId, String roleId,	String userId) {
		log.debug("removeUserFromRole: userId=" + userId);
		log.debug("removeUserFromRole: roleId=" + roleId);
		
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.role.dto.UserRole ur " + 
					" where ur.roleId = :roleId and ur.serviceId = :domainId and ur.userId = :userId ");
		qry.setString("roleId", roleId);
		qry.setString("domainId", domainId);
		qry.setString("userId", userId);
		qry.executeUpdate();	
	}

	public void removeAllUsersInRole(String domainId, String roleId) {
		log.debug("removeUserFromRole: roleId=" + roleId);
		
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.role.dto.UserRole ur " + 
					" where ur.roleId = :roleId and ur.serviceId = :domainId ");
		qry.setString("roleId", roleId);
		qry.setString("domainId", domainId);
		qry.executeUpdate();			
	}

}
