package org.openiam.idm.srvc.prov.request.service;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.prov.request.dto.RequestAttribute;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RequestAttribute.
 * @see org.openiam.idm.srvc.prov.service.RequestAttribute
 * @author Hibernate Tools
 */
public class RequestAttributeDAOImpl {

	private static final Log log = LogFactory
			.getLog(RequestAttributeDAOImpl.class);

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

	public void persist(RequestAttribute transientInstance) {
		log.debug("persisting RequestAttribute instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RequestAttribute instance) {
		log.debug("attaching dirty RequestAttribute instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RequestAttribute instance) {
		log.debug("attaching clean RequestAttribute instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RequestAttribute persistentInstance) {
		log.debug("deleting RequestAttribute instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RequestAttribute merge(RequestAttribute detachedInstance) {
		log.debug("merging RequestAttribute instance");
		try {
			RequestAttribute result = (RequestAttribute) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RequestAttribute findById(java.lang.String id) {
		log.debug("getting RequestAttribute instance with id: " + id);
		try {
			RequestAttribute instance = (RequestAttribute) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.prov.service.RequestAttribute",
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

	public List<RequestAttribute> findByExample(RequestAttribute instance) {
		log.debug("finding RequestAttribute instance by example");
		try {
			List<RequestAttribute> results = (List<RequestAttribute>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.openiam.idm.srvc.prov.service.RequestAttribute")
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
