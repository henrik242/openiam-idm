package org.openiam.idm.srvc.mngsys.service;

// Generated Feb 6, 2010 7:16:15 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

import static org.hibernate.criterion.Example.create;

/**
 * DAO implementation for  domain model class AttributeMap.
 */
public class AttributeMapDAOImpl implements AttributeMapDAO {

	private static final Log log = LogFactory.getLog(AttributeMapDAOImpl.class);

	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.AttributeMapDAO#setSessionFactory(org.hibernate.SessionFactory)
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
	 * @see org.openiam.idm.srvc.mngsys.service.AttributeMapDAO#add(org.openiam.idm.srvc.mngsys.dto.AttributeMap)
	 */
	public void add(AttributeMap transientInstance) {
		log.debug("persisting AttributeMap instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.AttributeMapDAO#remove(org.openiam.idm.srvc.mngsys.dto.AttributeMap)
	 */
	public void remove(AttributeMap persistentInstance) {
		log.debug("deleting AttributeMap instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.AttributeMapDAO#update(org.openiam.idm.srvc.mngsys.dto.AttributeMap)
	 */
	public AttributeMap update(AttributeMap detachedInstance) {
		log.debug("merging AttributeMap instance");
		try {
			AttributeMap result = (AttributeMap) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.AttributeMapDAO#findById(java.lang.String)
	 */
	public AttributeMap findById(java.lang.String id) {
		log.debug("getting AttributeMap instance with id: " + id);
		try {
			AttributeMap instance = (AttributeMap) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.pswd.service.AttributeMap",
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
	
	public List<AttributeMap> findByResourceId(String resourceId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.AttributeMap am " +
				" where am.resourceId = :resourceId order by am.mapForObjectType, am .managedSysId asc");
		qry.setString("resourceId", resourceId);
		List<AttributeMap> results = (List<AttributeMap>)qry.list();
		if (results == null || results.size() == 0) {
			log.debug("get successful, no instance found");
			return null;
		}
		log.debug("get successful, attribute map instances found = " + results.size());
		return results;			
	}


}
