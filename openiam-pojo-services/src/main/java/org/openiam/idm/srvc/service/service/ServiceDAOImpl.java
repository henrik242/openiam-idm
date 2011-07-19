package org.openiam.idm.srvc.service.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;

import static org.hibernate.criterion.Example.create;
import org.openiam.idm.srvc.service.dto.*;

/**
 * Data access object for domain model class Service.
 * @see org.openiam.idm.srvc.service.dto.Service
 * @author Suneet Shah
 */
public class ServiceDAOImpl implements ServiceDAO {

	private static final Log log = LogFactory.getLog(ServiceDAOImpl.class);

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

	public void persist(Service transientInstance) {
		log.debug("persisting Service instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Service instance) {
		log.debug("attaching dirty Service instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Service instance) {
		log.debug("attaching clean Service instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Service persistentInstance) {
		log.debug("deleting Service instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Service merge(Service detachedInstance) {
		log.debug("merging Service instance");
		try {
			Service result = (Service) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Service findById(java.lang.String id) {
		log.debug("getting Service instance with id: " + id);
		try {
			Service instance = (Service) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.service.dto.Service", id);
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

	public List<Service> findByExample(Service instance) {
		log.debug("finding Service instance by example");
		try {
			List<Service> results = (List<Service>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.service.dto.Service").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Service> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.service.dto.Service s order by s.serviceId asc");
		List<Service> results = (List<Service>)qry.list();
		return results;
	}

	public List<Service> findChildServices(String serviceId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.service.dto.Service s where s.parentServiceId = :parentServiceId order by s.serviceId asc");
		qry.setString("parentServiceId", serviceId);
		List<Service> results = (List<Service>)qry.list();
		return results;
	}

	public List<Service> findServicesByType(String type) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.service.dto.Service s where s.serviceType = :serviceType order by s.serviceId asc");
		qry.setString("serviceType", type);
		List<Service> results = (List<Service>)qry.list();
		return results;
	}


	
	
}
