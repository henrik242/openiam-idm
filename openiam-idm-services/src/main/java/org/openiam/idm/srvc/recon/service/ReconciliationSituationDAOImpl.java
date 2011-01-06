package org.openiam.idm.srvc.recon.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.recon.dto.ReconciliationSituation;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ReconciliationSituation.
 * @see org.openiam.idm.srvc.pswd.service.ReconciliationSituation
 * @author Hibernate Tools
 */
public class ReconciliationSituationDAOImpl implements ReconciliationSituationDAO {

	private static final Log log = LogFactory
			.getLog(ReconciliationSituationDAO.class);

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

	public void add(ReconciliationSituation transientInstance) {
		log.debug("persisting ReconciliationSituation instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ReconciliationSituation persistentInstance) {
		log.debug("deleting ReconciliationSituation instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReconciliationSituation update(ReconciliationSituation detachedInstance) {
		log.debug("merging ReconciliationSituation instance");
		try {
			ReconciliationSituation result = (ReconciliationSituation) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ReconciliationSituation findById(java.lang.String id) {
		log.debug("getting ReconciliationSituation instance with id: " + id);
		try {
			ReconciliationSituation instance = (ReconciliationSituation) sessionFactory
					.getCurrentSession()
					.get(
							"org.openiam.idm.srvc.pswd.service.ReconciliationSituation",
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
