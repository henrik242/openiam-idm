package org.openiam.idm.srvc.res.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.res.dto.*;


/**
 * DAO Implementation for ResourceUsers.
 * 
 */
public class ResourceUserDAOImpl implements ResourceUserDAO {

	private static final Log log = LogFactory.getLog(ResourceUserDAOImpl.class);

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

	public void persist(ResourceUser transientInstance) {
		log.debug("persisting ResourceUser instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#remove(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#remove(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	public void remove(ResourceUser persistentInstance) {
		log.debug("deleting ResourceUser instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#update(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#update(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	public ResourceUser update(ResourceUser detachedInstance) {
		log.debug("merging ResourceUser instance");
		try {
			ResourceUser result = (ResourceUser) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ResourceUser findById(ResourceUserId id) {
		log.debug("getting ResourceUser instance with id: " + id.getResourceId() + "-" + id.getUserId() + "-" + id.getPrivilegeId());
		try {
			ResourceUser instance = (ResourceUser) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.res.dto.ResourceUser", id);
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
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#findByExample(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#findByExample(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	public List<ResourceUser> findByExample(ResourceUser instance) {
		log.debug("finding ResourceUser instance by example");
		try {
			List<ResourceUser> results = (List<ResourceUser>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.res.dto.ResourceUser").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	//==================================================================
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#add(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#add(org.openiam.idm.srvc.res.dto.ResourceUser)
	 */
	public ResourceUser add(ResourceUser instance) {
		log.debug("persisting instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#findAllResourceUsers()
	 */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#findAllResourceUsers()
	 */
	public List<ResourceUser> findAllResourceUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.res.dto.ResourceUser") ;
		List<ResourceUser> result = (List<ResourceUser>)qry.list();
		
	
		return result;
	}

	public List<ResourceUser> findAllResourceForUsers(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.res.dto.ResourceUser ru" +
				" where ru.id.userId = :userId ") ;
		qry.setString("userId", userId);
		List<ResourceUser> result = (List<ResourceUser>)qry.list();		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#removeAllResourceUsers()
	 */
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.res.service.ResourceUserDAO#removeAllResourceUsers()
	 */
	public void removeAllResourceUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from org.openiam.idm.srvc.res.dto.ResourceUser");
		qry.executeUpdate();
	}
	
	public void removeUserFromAllResources(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from org.openiam.idm.srvc.res.dto.ResourceUser ru " +
				" where ru.id.userId = :userId "	);
		qry.setString("userId", userId);
		qry.executeUpdate();
		
	}
	
}
