package org.openiam.idm.srvc.secdomain.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;

import static org.hibernate.criterion.Example.create;
import org.openiam.idm.srvc.secdomain.dto.*;

/**
 * Data access object for domain model class SecurityDomain.
 * @author Suneet Shah
 */
public class SecurityDomainDAOImpl implements SecurityDomainDAO {

	private static final Log log = LogFactory.getLog(SecurityDomainDAOImpl.class);

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

	public void add(SecurityDomain transientInstance) {
		log.debug("persisting SecurityDomain instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	public void remove(SecurityDomain persistentInstance) {
		log.debug("deleting SecurityDomain instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecurityDomain update(SecurityDomain detachedInstance) {
		log.debug("merging SecurityDomain instance");
		try {
			SecurityDomain result = (SecurityDomain) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SecurityDomain findById(java.lang.String id) {
		log.debug("getting SecurityDomain instance with id: " + id);
		try {
			SecurityDomain instance = (SecurityDomain) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.secdomain.dto.SecurityDomain", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
				return null;
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			return null;
		}
	}

	public List<SecurityDomain> findByExample(SecurityDomain instance) {
		log.debug("finding SecurityDomain instance by example");
		try {
			List<SecurityDomain> results = (List<SecurityDomain>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.secdomain.dto.SecurityDomain").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			return null;
		}
	}

	public List<SecurityDomain> findAll() {
		try {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.secdomain.dto.SecurityDomain s order by s.domainId asc");
		List<SecurityDomain> results = (List<SecurityDomain>)qry.list();
		return results;
		}catch(HibernateException re) {
			log.error("find by example failed", re);
			return null;			
		}
	}




	
	
}
