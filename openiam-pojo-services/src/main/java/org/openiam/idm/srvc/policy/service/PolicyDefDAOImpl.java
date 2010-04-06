package org.openiam.idm.srvc.policy.service;

// Generated Mar 7, 2009 11:47:13 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.policy.dto.PolicyDef;
import org.openiam.idm.srvc.role.dto.Role;

import static org.hibernate.criterion.Example.create;

/**
 * Data access implementation for PolicyDefinitions
 * 
 */
public class PolicyDefDAOImpl implements PolicyDefDAO {

	private static final Log log = LogFactory.getLog(PolicyDefDAOImpl.class);

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

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDefDAO#add(org.openiam.idm.srvc.policy.dto.PolicyDef)
	 */
	public void add(PolicyDef transientInstance) {
		log.debug("persisting PolicyDef instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDefDAO#remove(org.openiam.idm.srvc.policy.dto.PolicyDef)
	 */
	public void remove(PolicyDef persistentInstance) {
		log.debug("deleting PolicyDef instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDefDAO#update(org.openiam.idm.srvc.policy.dto.PolicyDef)
	 */
	public PolicyDef update(PolicyDef detachedInstance) {
		log.debug("merging PolicyDef instance");
		try {
			PolicyDef result = (PolicyDef) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDefDAO#findById(java.lang.String)
	 */
	public PolicyDef findById(java.lang.String id) {
		log.debug("getting PolicyDef instance with id: " + id);
		try {
			PolicyDef instance = (PolicyDef) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.policy.dto.PolicyDef", id);
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
	
	public List<String> findAllPolicyTypes() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("select distinct p.name from PolicyDef as p "
					+ " order by p.name ");
			List<String> result = (List<String>) qry.list();
			if (result == null || result.size() == 0)
				return null;
			return result;			
		} catch (HibernateException re) {
			log.error("findAllPolicyTypes failed", re);
			throw re;
		}		
	}
	
	public List<PolicyDef> findAllPolicyDef() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from PolicyDef p "
					+ " order by p.policyDefId ");
			List<PolicyDef> result = (List<PolicyDef>) qry.list();
			if (result == null || result.size() == 0)
				return null;
			return result;			
		} catch (HibernateException re) {
			log.error("findAllPolicyDef failed", re);
			throw re;
		}			
	}
	

	

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDefDAO#findByExample(org.openiam.idm.srvc.policy.dto.PolicyDef)
	 */
	public List<PolicyDef> findByExample(PolicyDef instance) {
		log.debug("finding PolicyDef instance by example");
		try {
			List<PolicyDef> results = (List<PolicyDef>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.policy.dto.PolicyDef").add(
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
