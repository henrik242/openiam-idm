package org.openiam.idm.srvc.recon.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ReconiliationConfig.
 * @see org.openiam.idm.srvc.pswd.service.ReconiliationConfig
 * @author Hibernate Tools
 */
public class ReconciliationConfigDAOImpl implements ReconciliationConfigDAO {

	private static final Log log = LogFactory
			.getLog(ReconciliationConfigDAO.class);

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


	public void add(ReconciliationConfig transientInstance) {
		log.debug("persisting ReconiliationConfig instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(ReconciliationConfig persistentInstance) {
		log.debug("deleting ReconiliationConfig instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReconciliationConfig update(ReconciliationConfig detachedInstance) {
		log.debug("merging ReconiliationConfig instance");
		try {
			ReconciliationConfig result = (ReconciliationConfig) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ReconciliationConfig findById(java.lang.String id) {
		log.debug("getting ReconiliationConfig instance with id: " + id);
		try {
			ReconciliationConfig instance = (ReconciliationConfig) sessionFactory
					.getCurrentSession()
					.get(
							"org.openiam.idm.srvc.recon.dto.ReconciliationConfig",
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
