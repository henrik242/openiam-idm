package org.openiam.idm.srvc.orgpolicy.service;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.List;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;



/**
 * Home object for domain model class SysMessageDelivery.
 * @see org.openiam.idm.srvc.msg.dto.SysMessage
 * @author Hibernate Tools
 */
public class OrgPolicyDAOImpl implements OrgPolicyDAO {

	private static final Log log = LogFactory
			.getLog(OrgPolicyDAOImpl.class);

	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public OrgPolicy add(OrgPolicy transientInstance) {
		log.debug("persisting OrgPolicy instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(OrgPolicy persistentInstance) {
		log.debug("deleting OrgPolicy instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrgPolicy update(OrgPolicy detachedInstance) {
		log.debug("merging OrgPolicy instance");
		try {
			OrgPolicy result = (OrgPolicy) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrgPolicy findById(java.lang.String id) {
		log.debug("getting OrgPolicy instance with id: " + id);
		try {
			OrgPolicy instance = (OrgPolicy) sessionFactory
					.getCurrentSession()
					.get(
							"org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy",
							id);
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

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.orgpolicy.service.OrgPolicyAcceptanceDAO#findAll()
	 */
	public List<OrgPolicy> findAll() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy o " +
					" order by o.name asc");
			
			qry.setCacheable(true);
			qry.setCacheRegion("query.orgPolicy.getAllPolicyMessages");
			
			List<OrgPolicy> results = (List<OrgPolicy>)qry.list();
			return results;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}


}
