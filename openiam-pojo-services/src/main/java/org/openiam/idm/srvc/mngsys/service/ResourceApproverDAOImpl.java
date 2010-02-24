package org.openiam.idm.srvc.mngsys.service;

// Generated Dec 24, 2009 9:53:19 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ResourceApprover;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ResApprover.
 * @see org.openiam.idm.srvc.pswd.service.ResApprover
 * @author Hibernate Tools
 */
public class ResourceApproverDAOImpl implements ResourceApproverDAO {

	private static final Log log = LogFactory.getLog(ResourceApproverDAOImpl.class);

	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ResourceApproverDAO#setSessionFactory(org.hibernate.SessionFactory)
	 */
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
	 * @see org.openiam.idm.srvc.mngsys.service.ResourceApproverDAO#add(org.openiam.idm.srvc.mngsys.dto.ResourceApprover)
	 */
	public void add(ResourceApprover transientInstance) {
		log.debug("persisting ResApprover instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ResourceApproverDAO#remove(org.openiam.idm.srvc.mngsys.dto.ResourceApprover)
	 */
	public void remove(ResourceApprover persistentInstance) {
		log.debug("deleting ResApprover instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ResourceApproverDAO#update(org.openiam.idm.srvc.mngsys.dto.ResourceApprover)
	 */
	public ResourceApprover update(ResourceApprover detachedInstance) {
		log.debug("merging ResApprover instance");
		try {
			ResourceApprover result = (ResourceApprover) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ResourceApproverDAO#findById(java.lang.String)
	 */
	public ResourceApprover findById(java.lang.String id) {
		log.debug("getting ResApprover instance with id: " + id);
		try {
			ResourceApprover instance = (ResourceApprover) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.mngsys.dto.ResourceApprover", id);
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

	public List<ResourceApprover> findApproversByResource(String managedSysId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ResourceApprover ra " +
				" where ra.managedSysId = :managedSysId order by ra.approverLevel ");
		qry.setString("managedSysId", managedSysId);
		List<ResourceApprover> results = (List<ResourceApprover>)qry.list();
		return results;	
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ResourceApproverDAO#findApproversByAction(java.lang.String, java.lang.String, int)
	 */
	public List<ResourceApprover> findApproversByAction(String managedSysId,
			String action, int level) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ResourceApprover ra " +
				" where ra.managedSysId = :managedSysId and " +
				"       ra.approverLevel = :level and " +
				"       ra.taskAction = :action " +
				" order by ra.approverLevel ");
		qry.setString("managedSysId", managedSysId);
		qry.setInteger("level", level);
		qry.setString("action", action);
		List<ResourceApprover> results = (List<ResourceApprover>)qry.list();
		return results;	
	}

}
