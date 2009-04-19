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

	public void persist(Resources transientInstance) {
		log.debug("persisting Resources instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(Resources persistentInstance) {
		log.debug("deleting Resources instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Resources update(Resources detachedInstance) {
		log.debug("merging Resources instance");
		try {
			Resources result = (Resources) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Resources findById(java.lang.String id) {
		log.debug("getting Resources instance with id: " + id);
		try {
			Resources instance = (Resources) sessionFactory.getCurrentSession()
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

	public List<Resources> findByExample(Resources instance) {
		log.debug("finding Resources instance by example");
		try {
			List<Resources> results = (List<Resources>) sessionFactory
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
