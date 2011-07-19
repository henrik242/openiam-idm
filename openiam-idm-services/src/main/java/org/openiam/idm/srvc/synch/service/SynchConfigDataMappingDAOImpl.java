package org.openiam.idm.srvc.synch.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.synch.dto.SynchConfig;
import org.openiam.idm.srvc.synch.dto.SynchConfigDataMapping;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SynchConfigDataMapping.
 * @see org.openiam.idm.srvc.pswd.service.SynchConfigDataMapping
 * @author Hibernate Tools
 */
public class SynchConfigDataMappingDAOImpl implements  SynchConfigDataMappingDAO {

	private static final Log log = LogFactory
			.getLog(SynchConfigDataMappingDAOImpl.class);

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

	public SynchConfigDataMapping add(SynchConfigDataMapping transientInstance) {
		log.debug("persisting SynchConfig instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	


	public void remove(SynchConfigDataMapping persistentInstance) {
		log.debug("deleting SynchConfigDataMapping instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SynchConfigDataMapping update(SynchConfigDataMapping detachedInstance) {
		log.debug("merging SynchConfigDataMapping instance");
		try {
			SynchConfigDataMapping result = (SynchConfigDataMapping) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SynchConfigDataMapping findById(java.lang.String id) {
		log.debug("getting SynchConfigDataMapping instance with id: " + id);
		try {
			SynchConfigDataMapping instance = (SynchConfigDataMapping) sessionFactory
					.getCurrentSession()
					.get(
							"org.openiam.idm.srvc.pswd.service.SynchConfigDataMapping",
							id);
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

}
