package org.openiam.idm.srvc.mngsys.service;

// Generated Dec 20, 2008 7:54:59 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.mngsys.dto.*;
/**
 * Home object for domain model class MngSysObjectMatch.
 * @see org.openiam.idm.srvc.meta.service.MngSysObjectMatch
 * @author Hibernate Tools
 */
public class ManagedSystemObjectMatchDAOImpl implements ManagedSystemObjectMatchDAO {

	private static final Log log = LogFactory
			.getLog(ManagedSystemObjectMatchDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO#add(org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch)
	 */
	public void add(ManagedSystemObjectMatch transientInstance) {
		log.debug("persisting MngSysObjectMatch instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO#remove(org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch)
	 */
	public void remove(ManagedSystemObjectMatch persistentInstance) {
		log.debug("deleting MngSysObjectMatch instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO#update(org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch)
	 */
	public ManagedSystemObjectMatch update(ManagedSystemObjectMatch detachedInstance) {
		log.debug("merging MngSysObjectMatch instance");
		try {
			ManagedSystemObjectMatch result = (ManagedSystemObjectMatch) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO#findById(java.lang.String)
	 */
	public ManagedSystemObjectMatch findById(java.lang.String id) {
		log.debug("getting MngSysObjectMatch instance with id: " + id);
		try {
			ManagedSystemObjectMatch instance = (ManagedSystemObjectMatch) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch",
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
	
	/**
	 * Finds objects for an object type (like User, Group) for a ManagedSystem definition
	 * @param managedSystemId
	 * @param objectType
	 * @return
	 */
	public List<ManagedSystemObjectMatch> findBySystemId(String managedSystemId, String objectType) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch sys " +
						" where sys.managedSys = :managedSystemId  and sys.objectType = :objectType" );
		qry.setString("managedSystemId", managedSystemId);
		qry.setString("objectType", objectType);
		List<ManagedSystemObjectMatch> result = (List<ManagedSystemObjectMatch>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;
	}
	

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO#findByExample(org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch)
	 */
	public List<ManagedSystemObjectMatch> findByExample(ManagedSystemObjectMatch instance) {
		log.debug("finding MngSysObjectMatch instance by example");
		try {
			List<ManagedSystemObjectMatch> results = (List<ManagedSystemObjectMatch>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
