package org.openiam.idm.srvc.mngsys.service;

// Generated Nov 3, 2008 12:14:44 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.mngsys.dto.SysApprover;

/**
 * Home object for domain model class SysApprover.
 * @see org.openiam.idm.srvc.SysApprover
 * @author Hibernate Tools
 */
public class SysApproverDAOImpl {

	private static final Log log = LogFactory.getLog(SysApproverDAOImpl.class);

	private final SessionFactory sessionFactory = getSessionFactory();

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

	public void persist(SysApprover transientInstance) {
		log.debug("persisting SysApprover instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SysApprover instance) {
		log.debug("attaching dirty SysApprover instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SysApprover instance) {
		log.debug("attaching clean SysApprover instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SysApprover persistentInstance) {
		log.debug("deleting SysApprover instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SysApprover merge(SysApprover detachedInstance) {
		log.debug("merging SysApprover instance");
		try {
			SysApprover result = (SysApprover) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysApprover findById(java.lang.String id) {
		log.debug("getting SysApprover instance with id: " + id);
		try {
			SysApprover instance = (SysApprover) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.SysApprover", id);
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

	public List<SysApprover> findByExample(SysApprover instance) {
		log.debug("finding SysApprover instance by example");
		try {
			List<SysApprover> results = (List<SysApprover>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.SysApprover").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
