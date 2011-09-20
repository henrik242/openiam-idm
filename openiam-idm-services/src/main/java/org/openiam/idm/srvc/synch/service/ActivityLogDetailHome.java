package org.openiam.idm.srvc.synch.service;

// Generated Sep 2, 2010 12:56:46 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.synch.dto.ActivityLogDetail;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ActivityLogDetail.
 * @see org.openiam.idm.srvc.pswd.service.ActivityLogDetail
 * @author Hibernate Tools
 */
public class ActivityLogDetailHome {

	private static final Log log = LogFactory
			.getLog(ActivityLogDetailHome.class);

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

	public void persist(ActivityLogDetail transientInstance) {
		log.debug("persisting ActivityLogDetail instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ActivityLogDetail instance) {
		log.debug("attaching dirty ActivityLogDetail instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActivityLogDetail instance) {
		log.debug("attaching clean ActivityLogDetail instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ActivityLogDetail persistentInstance) {
		log.debug("deleting ActivityLogDetail instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActivityLogDetail merge(ActivityLogDetail detachedInstance) {
		log.debug("merging ActivityLogDetail instance");
		try {
			ActivityLogDetail result = (ActivityLogDetail) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ActivityLogDetail findById(java.lang.String id) {
		log.debug("getting ActivityLogDetail instance with id: " + id);
		try {
			ActivityLogDetail instance = (ActivityLogDetail) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.pswd.service.ActivityLogDetail",
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

	public List<ActivityLogDetail> findByExample(ActivityLogDetail instance) {
		log.debug("finding ActivityLogDetail instance by example");
		try {
			List<ActivityLogDetail> results = (List<ActivityLogDetail>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.openiam.idm.srvc.pswd.service.ActivityLogDetail")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
