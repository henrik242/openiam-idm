package org.openiam.idm.srvc.prov.request.service;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.prov.request.dto.RequestAttachment;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class RequestAttachment.
 * @see org.openiam.idm.srvc.prov.service.RequestAttachment
 * @author Hibernate Tools
 */
public class RequestAttachmentDAOImpl {

	private static final Log log = LogFactory
			.getLog(RequestAttachmentDAOImpl.class);

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

	public void persist(RequestAttachment transientInstance) {
		log.debug("persisting RequestAttachment instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RequestAttachment instance) {
		log.debug("attaching dirty RequestAttachment instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RequestAttachment instance) {
		log.debug("attaching clean RequestAttachment instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RequestAttachment persistentInstance) {
		log.debug("deleting RequestAttachment instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RequestAttachment merge(RequestAttachment detachedInstance) {
		log.debug("merging RequestAttachment instance");
		try {
			RequestAttachment result = (RequestAttachment) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RequestAttachment findById(java.lang.String id) {
		log.debug("getting RequestAttachment instance with id: " + id);
		try {
			RequestAttachment instance = (RequestAttachment) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.prov.service.RequestAttachment",
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

	public List<RequestAttachment> findByExample(RequestAttachment instance) {
		log.debug("finding RequestAttachment instance by example");
		try {
			List<RequestAttachment> results = (List<RequestAttachment>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.openiam.idm.srvc.prov.service.RequestAttachment")
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
