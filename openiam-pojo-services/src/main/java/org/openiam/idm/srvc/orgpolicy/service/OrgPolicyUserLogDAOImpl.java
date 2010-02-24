package org.openiam.idm.srvc.orgpolicy.service;

// Generated Nov 29, 2009 2:09:10 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class OrgPolicyUserLog.
 * @see org.openiam.idm.srvc.pswd.service.OrgPolicyUserLog
 * @author Hibernate Tools
 */
public class OrgPolicyUserLogDAOImpl implements OrgPolicyUserLogDAO {

	private static final Log log = LogFactory
			.getLog(OrgPolicyUserLogDAOImpl.class);

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

	public OrgPolicyUserLog add(OrgPolicyUserLog transientInstance) {
		log.debug("persisting OrgPolicyUserLog instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	public void remove(OrgPolicyUserLog persistentInstance) {
		log.debug("deleting OrgPolicyUserLog instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrgPolicyUserLog update(OrgPolicyUserLog detachedInstance) {
		log.debug("merging OrgPolicyUserLog instance");
		try {
			OrgPolicyUserLog result = (OrgPolicyUserLog) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrgPolicyUserLog findById(java.lang.String id) {
		log.debug("getting OrgPolicyUserLog instance with id: " + id);
		try {
			OrgPolicyUserLog instance = (OrgPolicyUserLog) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog",
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

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.orgpolicy.service.OrgPolicyUserLogDAO#findLogForUser(java.lang.String)
	 */
	public List<OrgPolicyUserLog> findLogForUser(String userId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog o " +
				" where o.userId = :userId " +	
				" order by o.timeStamp asc");
			qry.setString("userId", userId);
			List<OrgPolicyUserLog> results = (List<OrgPolicyUserLog>)qry.list();
			return results;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}


}
