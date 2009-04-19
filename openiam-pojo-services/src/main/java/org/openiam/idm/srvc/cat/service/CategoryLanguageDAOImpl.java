package org.openiam.idm.srvc.cat.service;

// Generated Nov 22, 2008 1:32:51 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.cat.dto.CategoryLanguage;
import org.openiam.idm.srvc.cat.dto.CategoryLanguageId;
import org.hibernate.HibernateException;

/**
 * DAO Object for CategoryLanguage
 * @author Suneet Shah
 */
public class CategoryLanguageDAOImpl implements CategoryLanguageDAO {

	private static final Log log = LogFactory
			.getLog(CategoryLanguageDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.cat.service.CategoryLanguageDAO#add(org.openiam.idm.srvc.cat.dto.CategoryLanguage)
	 */
	public void add(CategoryLanguage transientInstance) {
		log.debug("persisting CategoryLanguage instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cat.service.CategoryLanguageDAO#remove(org.openiam.idm.srvc.cat.dto.CategoryLanguage)
	 */
	public void remove(CategoryLanguage persistentInstance) {
		log.debug("deleting CategoryLanguage instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cat.service.CategoryLanguageDAO#update(org.openiam.idm.srvc.cat.dto.CategoryLanguage)
	 */
	public CategoryLanguage update(CategoryLanguage detachedInstance) {
		log.debug("merging CategoryLanguage instance");
		try {
			CategoryLanguage result = (CategoryLanguage) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.cat.service.CategoryLanguageDAO#findById(org.openiam.idm.srvc.cat.dto.CategoryLanguageId)
	 */
	public CategoryLanguage findById(
			org.openiam.idm.srvc.cat.dto.CategoryLanguageId id) {
		log.debug("getting CategoryLanguage instance with id: " + id);
		try {
			CategoryLanguage instance = (CategoryLanguage) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.cat.dto.CategoryLanguage",
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
	 * @see org.openiam.idm.srvc.cat.service.CategoryLanguageDAO#findByExample(org.openiam.idm.srvc.cat.dto.CategoryLanguage)
	 */
	public List<CategoryLanguage> findByExample(CategoryLanguage instance) {
		log.debug("finding CategoryLanguage instance by example");
		try {
			List<CategoryLanguage> results = (List<CategoryLanguage>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.openiam.idm.srvc.cat.dto.CategoryLanguage")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
