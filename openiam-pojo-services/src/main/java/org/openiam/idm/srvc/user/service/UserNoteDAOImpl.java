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
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserNote;

/**
 * Home object for domain model class UserNote.
 * @see org.openiam.idm.srvc.user.UserNote
 * @author Hibernate Tools
 */
public class UserNoteDAOImpl implements UserNoteDAO {

	private static final Log log = LogFactory.getLog(UserNoteDAOImpl.class);

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

	public void persist(UserNote transientInstance) {
		log.debug("persisting UserNote instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserNote instance) {
		log.debug("attaching dirty UserNote instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserNote instance) {
		log.debug("attaching clean UserNote instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UserNote persistentInstance) {
		log.debug("deleting UserNote instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserNote merge(UserNote detachedInstance) {
		log.debug("merging UserNote instance");
		try {
			UserNote result = (UserNote) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserNote findById(java.lang.String id) {
		log.debug("getting UserNote instance with id: " + id);
		try {
			UserNote instance = (UserNote) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.user.dto.UserNote", id);
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
	/**
	 * Delete all the notes associated with a user.
	 * @param userId
	 */
	public void deleteUserNotes(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.user.dto.UserNote un " + 
					" where un.userId = :userId ");
		qry.setString("userId", userId);
		qry.executeUpdate();

		
	}

	public List<UserNote> findUserNotes(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.UserNote un where un.userId = :userId order by un.userNoteId asc");
		qry.setString("userId", userId);
		List<UserNote> results = (List<UserNote>)qry.list();
		return results;
	}	
	
	public List findByExample(UserNote instance) {
		log.debug("finding UserNote instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"org.openiam.idm.srvc.user.UserNote").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
