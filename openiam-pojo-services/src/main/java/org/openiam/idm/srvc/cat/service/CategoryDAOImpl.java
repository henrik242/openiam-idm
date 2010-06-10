package org.openiam.idm.srvc.cat.service;

// Generated Nov 22, 2008 1:32:51 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.user.dto.User;
import org.hibernate.HibernateException;

/**
 * DAO Implementation for Category
 * @author Suneet Shah
 */
public class CategoryDAOImpl implements CategoryDAO {

	private static final Log log = LogFactory.getLog(CategoryDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.cat.service.CategoryDAO#add(org.openiam.idm.srvc.cat.dto.Category)
	 */
	public void add(Category transientInstance) {
		log.debug("persisting Category instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cat.service.CategoryDAO#remove(org.openiam.idm.srvc.cat.dto.Category)
	 */
	public void remove(Category persistentInstance) {
		int count = 0;
		log.debug("deleting Category instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cat.service.CategoryDAO#update(org.openiam.idm.srvc.cat.dto.Category)
	 */
	public Category update(Category detachedInstance) {
		log.debug("merging Category instance");
		try {
			Category result = (Category) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cat.service.CategoryDAO#findById(java.lang.String)
	 */
	public Category findById(java.lang.String id) {
		log.debug("getting Category instance with id: " + id);
		try {
			Category instance = (Category) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.cat.dto.Category", id);
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
	 * @see org.openiam.idm.srvc.cat.service.CategoryDAO#findByExample(org.openiam.idm.srvc.cat.dto.Category)
	 */
	public List<Category> findByExample(Category instance) {
		log.debug("finding Category instance by example");
		try {
			List<Category> results = (List<Category>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.cat.dto.Category").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Return a list of Categories where the parentId is null.
	 * @return
	 */
	public List<Category> findRootCategories() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.cat.dto.Category cat " + 
				" where cat.parentId is null order by cat.categoryId asc");
		List<Category> results = (List<Category>)qry.list();
		return results;		
	}
	

	
	/**
	 * Return a list of Categories for the specified parentId.
	 * @param parentId
	 * @return
	 */
	public List<Category> findChildCategories(String parentId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.cat.dto.Category cat " + 
				" where cat.parentId = :parentId order by cat.categoryId asc");
		qry.setString("parentId", parentId);
		List<Category> results = (List<Category>)qry.list();
		return results;
		
	}
	
	/**
	 * Removes a list of categories
	 * @param catIdList
	 * @return
	 */
	public int removeGroupList(String catIdList) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.cat.dto.Category cat  " + 
					" where cat.categoryId in (" + catIdList + ")" );
		return qry.executeUpdate();		
	}	
	
}
