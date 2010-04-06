package org.openiam.idm.srvc.batch.service;

// Generated Feb 25, 2010 11:45:18 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.batch.dto.BatchTask;


/**
 * DAO implementation for BatchConfig.
 * @see org.openiam.idm.srvc.batch.dto.BatchTask

 */
public class BatchConfigDAOImpl implements BatchConfigDAO {

	private static final Log log = LogFactory.getLog(BatchConfigDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.batch.service.BatchConfigDAO#add(org.openiam.idm.srvc.batch.dto.BatchTask)
	 */
	public void add(BatchTask transientInstance) {
		log.debug("persisting BatchConfig instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchConfigDAO#remove(org.openiam.idm.srvc.batch.dto.BatchTask)
	 */
	public void remove(BatchTask persistentInstance) {
		log.debug("deleting BatchConfig instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchConfigDAO#update(org.openiam.idm.srvc.batch.dto.BatchTask)
	 */
	public BatchTask update(BatchTask detachedInstance) {
		log.debug("merging BatchConfig instance");
		try {
			BatchTask result = (BatchTask) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchConfigDAO#findById(java.lang.String)
	 */
	public BatchTask findById(java.lang.String id) {
		log.debug("getting BatchConfig instance with id: " + id);
		try {
			BatchTask instance = (BatchTask) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.batch.dto.BatchTask", id);
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
	 * @see org.openiam.idm.srvc.batch.service.BatchConfigDAO#findAllBatchTasks()
	 */
	public List<BatchTask> findAllBatchTasks() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.batch.dto.BatchTask task " + 
				"  order by task.taskName asc");
		List<BatchTask> results = (List<BatchTask>)qry.list();
		if (results == null) {
			log.debug("get successful, no instance found");
		} else {
			log.debug("get successful, instance found");
		}
		return results;		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.batch.service.BatchConfigDAO#findBatchTasksByFrequency(java.lang.String)
	 */
	public List<BatchTask> findBatchTasksByFrequency(String frequency) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.batch.dto.BatchTask task " + 
				" where task.frequencyUnitOfMeasure = :frequency " +
				" order by task.taskName asc");
		qry.setString("frequency", frequency);
		List<BatchTask> results = (List<BatchTask>)qry.list();
		if (results == null) {
			log.debug("get successful, no instance found");
		} else {
			log.debug("get successful, instance found");
		}
		return results;		
	}
	

}
