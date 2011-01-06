package org.openiam.idm.srvc.res.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.res.dto.*;


/**
 * DAO Implementation for ResourceProps.
 * 
 */
public class ResourcePropDAOImpl implements ResourcePropDAO  {

	private static final Log log = LogFactory.getLog(ResourcePropDAOImpl.class);

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

	public void persist(ResourceProp transientInstance) {
		log.debug("persisting ResourceProp instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(ResourceProp persistentInstance) {
		log.debug("deleting ResourceProp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ResourceProp update(ResourceProp detachedInstance) {
		log.debug("merging ResourceProp instance");
		try {
			ResourceProp result = (ResourceProp) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ResourceProp findById(java.lang.String id) {
		log.debug("getting ResourceProp instance with id: " + id);
		try {
			ResourceProp instance = (ResourceProp) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.res.dto.ResourceProp", id);
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

	public List<ResourceProp> findByExample(ResourceProp instance) {
		log.debug("finding ResourceProp instance by example");
		try {
			List<ResourceProp> results = (List<ResourceProp>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.res.dto.ResourceProp").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	//==================================================================
	
	
	public ResourceProp add(ResourceProp instance) {
		log.debug("persisting instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}		
	}

	public List<ResourceProp> findAllResourceProps() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.res.dto.ResourceProp a " +
				" order by a.resourcePropId asc");
		List<ResourceProp> result = (List<ResourceProp>)qry.list();
		
		return result;
	}
	
	public int removeAllResourceProps() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from org.openiam.idm.srvc.res.dto.ResourceProp");
		return qry.executeUpdate();
	}
	
	
}
