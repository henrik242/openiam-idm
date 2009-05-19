package org.openiam.idm.srvc.res.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.openiam.idm.srvc.res.dto.*;


/**
 * DAO Implementation for Resources.
 * 
 */
public class ResourceDAOImpl implements ResourceDAO {

	private static final Log log = LogFactory.getLog(ResourceDAOImpl.class);

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

	public void persist(Resource transientInstance) {
		log.debug("persisting Resources instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(Resource persistentInstance) {
		log.debug("deleting Resources instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Resource update(Resource detachedInstance) {
		log.debug("merging Resources instance");
		try {
			Resource result = (Resource) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Resource findById(java.lang.String id) {
		log.debug("getting Resources instance with id: " + id);
		try {
			Resource instance = (Resource) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.res.dto.Resources", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Resource> findByExample(Resource instance) {
		log.debug("finding Resources instance by example");
		try {
			List<Resource> results = (List<Resource>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.prov.service.Resources").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
