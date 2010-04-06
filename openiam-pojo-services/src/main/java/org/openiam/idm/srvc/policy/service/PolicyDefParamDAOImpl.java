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
import org.openiam.idm.srvc.policy.dto.PolicyDefParam;

import static org.hibernate.criterion.Example.create;

/**
 * Data access implementation for PolicyDefinitions
 * 
 */
public class PolicyDefParamDAOImpl implements PolicyDefParamDAO {

	private static final Log log = LogFactory.getLog(PolicyDefParamDAOImpl.class);

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
	public void add(PolicyDefParam transientInstance) {
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
	public void remove(PolicyDefParam persistentInstance) {
		log.debug("deleting PolicyDefParam instance");
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
	public PolicyDefParam update(PolicyDefParam detachedInstance) {
		log.debug("merging PolicyDefParam instance");
		try {
			PolicyDefParam result = (PolicyDefParam) sessionFactory.getCurrentSession()
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
	public PolicyDefParam findById(java.lang.String id) {
		log.debug("getting PolicyDef instance with id: " + id);
		try {
			PolicyDefParam instance = (PolicyDefParam) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.policy.dto.PolicyDefParam", id);
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
	
	public List<PolicyDefParam> findPolicyDefParamByGroup(String defId, String group) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from PolicyDefParam as p "
					+ " where p.policyDefId = :defId and  p.paramGroup = :group" );
			qry.setString("group", group);
			qry.setString("defId", defId);
			List<PolicyDefParam> result = (List<PolicyDefParam>) qry.list();
			if (result == null || result.size() == 0)
				return null;
			return result;			
		} catch (HibernateException re) {
			log.error("findPolicyDefParamByGroup failed", re);
			throw re;
		}		
	}

	

}
