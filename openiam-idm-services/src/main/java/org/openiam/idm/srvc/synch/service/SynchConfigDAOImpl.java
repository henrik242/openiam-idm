package org.openiam.idm.srvc.synch.service;

// Generated May 29, 2010 8:20:09 PM by Hibernate Tools 3.2.2.GA

import java.sql.Timestamp;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.synch.dto.SynchConfig;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class SynchConfig.
 * @see org.openiam.idm.srvc.pswd.service.SynchConfig
 * @author Hibernate Tools
 */
public class SynchConfigDAOImpl implements SynchConfigDAO {

	private static final Log log = LogFactory.getLog(SynchConfigDAOImpl.class);

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

	public SynchConfig add(SynchConfig transientInstance) {
		log.debug("persisting SynchConfig instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	public void remove(SynchConfig persistentInstance) {
		log.debug("deleting SynchConfig instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SynchConfig update(SynchConfig detachedInstance) {
		log.debug("merging SynchConfig instance");
		try {
			SynchConfig result = (SynchConfig) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SynchConfig findById(java.lang.String id) {
		log.debug("getting SynchConfig instance with id: " + id);
		try {
			SynchConfig instance = (SynchConfig) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.synch.dto.SynchConfig", id);
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
	 * @see org.openiam.idm.srvc.synch.service.SynchConfigDAO#findAllConfig()
	 */
	public List<SynchConfig> findAllConfig() {
		log.debug("getting all synchronization configurations  "  );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.synch.dto.SynchConfig sc " +
						" order by sc.name asc  " );
		
		List<SynchConfig> result = (List<SynchConfig>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;
		
	}

	public int updateExecTime(String configId, Timestamp execTime) {
		log.debug("Updates the last execution   "  );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("UPDATE org.openiam.idm.srvc.synch.dto.SynchConfig sc " +
						" 					SET  sc.lastExecTime = :execTime  " +
						" 					WHERE  sc.synchConfigId = :configId	");
		
		qry.setTimestamp("execTime",execTime);
		qry.setString("configId", configId);
		
		return qry.executeUpdate();
		
		
	}
	
}
