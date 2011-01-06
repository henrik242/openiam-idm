package org.openiam.idm.srvc.user.service;

// Generated Jun 12, 2007 10:46:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import org.openiam.idm.srvc.service.dto.Service;
import org.openiam.idm.srvc.user.dto.*;

/**
 * Home object for domain model class UserAttribute.
 * @see org.openiam.idm.srvc.user.GroupAttribute
 * @author Hibernate Tools
 */
public class UserAttributeDAOImpl implements UserAttributeDAO {

	private static final Log log = LogFactory.getLog(UserAttributeDAOImpl.class);

	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}
	
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void add(UserAttribute transientInstance) {
		log.debug("persisting UserAttribute instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserAttribute instance) {
		log.debug("attaching dirty UserAttribute instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserAttribute instance) {
		log.debug("attaching clean UserAttribute instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void remove(UserAttribute persistentInstance) {
		log.debug("deleting UserAttribute instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserAttribute update(UserAttribute detachedInstance) {
		log.debug("merging UserAttribute instance");
		try {
			UserAttribute result = (UserAttribute) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserAttribute findById(java.lang.String id) {
		log.debug("getting UserAttribute instance with id: " + id);
		try {
			UserAttribute instance = (UserAttribute) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.user.dto.UserAttribute", id);
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
	
	public List<UserAttribute> findUserAttributes(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.UserAttribute ua where ua.userId = :userId order by ua.name asc");
		qry.setString("userId", userId);
		List<UserAttribute> results = (List<UserAttribute>)qry.list();
		return results;
	}
	
	public void deleteUserAttributes(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.user.dto.UserAttribute ua " + 
					" where ua.userId = :userId ");
		qry.setString("userId", userId);
		qry.executeUpdate();

		
	}

}

