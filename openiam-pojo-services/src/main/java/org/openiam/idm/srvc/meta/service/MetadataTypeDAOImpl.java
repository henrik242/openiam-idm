package org.openiam.idm.srvc.meta.service;

// Generated Nov 4, 2008 12:11:29 AM by Hibernate Tools 3.2.2.GA

import static org.hibernate.criterion.Example.create;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.log4j.Category;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.cat.service.CategoryDAO;
import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;

/**
 * DAO implementation for MetadataType
 */
public class MetadataTypeDAOImpl implements MetadataTypeDAO {

	private static final Log log = LogFactory.getLog(MetadataTypeDAOImpl.class);

	private SessionFactory sessionFactory;
	private CategoryDAO categoryDao;

	
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
	 * @see org.openiam.idm.srvc.meta.service.MetadatatypeDAO#add(org.openiam.idm.srvc.meta.dto.MetadataType)
	 */
	public void add(MetadataType transientInstance) {
		log.debug("persisting MetadataType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadatatypeDAO#remove(org.openiam.idm.srvc.meta.dto.MetadataType)
	 */
	public void remove(MetadataType persistentInstance) {
		log.debug("deleting MetadataType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadatatypeDAO#update(org.openiam.idm.srvc.meta.dto.MetadataType)
	 */
	public MetadataType update(MetadataType detachedInstance) {
		log.debug("merging MetadataType instance");
		try {
			MetadataType result = (MetadataType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadatatypeDAO#findById(java.lang.String)
	 */
	public MetadataType findById(java.lang.String id) {
		log.debug("getting MetadataType instance with id: " + id);
		try {
			MetadataType instance = (MetadataType) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.meta.dto.MetadataType",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			if (instance != null) {
				org.hibernate.Hibernate.initialize(instance.getCategories());
			}
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadatatypeDAO#findByExample(org.openiam.idm.srvc.meta.dto.MetadataType)
	 */
	public List<MetadataType> findByExample(MetadataType instance) {
		log.debug("finding MetadataType instance by example");
		try {
			List<MetadataType> results = (List<MetadataType>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.meta.dto.MetadataType")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<MetadataType> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.meta.dto.MetadataType  order by metadataTypeId asc " ); 
		List<MetadataType> results = (List<MetadataType>)qry.list();
		return results;		
	}
	

	
	public void addCategoryToType(String typeId, String categoryId) {
		Category cat = categoryDao.findById(categoryId);
		if (cat == null)
			return;
		MetadataType type = findById(typeId);
		Set<Category> categorySet = type.getCategories();
		categorySet.add(cat);
		
		try {
			sessionFactory.getCurrentSession().save(type);
			log.debug("persist type successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}
   }
	
	public void removeCategoryFromType(String typeId, String categoryId) {
		
		MetadataType type = findById(typeId);
		org.hibernate.Hibernate.initialize(type.getCategories());
		Set<Category> categorySet = type.getCategories();
		if (categorySet == null || categorySet.isEmpty()) {
			return;			
		}
		Iterator<Category> it = categorySet.iterator();
		while (it.hasNext()) {
			Category cat = it.next();
			if (cat.getCategoryId().equalsIgnoreCase(categoryId)) {
				it.remove();
			}
		}
	}
	
	public List<MetadataType> findTypesInCategory(String categoryId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session
				.createQuery(" select type from org.openiam.idm.srvc.meta.dto.MetadataType type "
						+ "		 join type.categories as cat "
						+ " where cat.categoryId = :catId "
						+ " order by type.metadataTypeId asc");
		qry.setString("catId", categoryId);
		List<MetadataType> results = (List<MetadataType>) qry.list();
		if (results == null || results.size() == 0)
			return null;
		return results;
		
	}
	

	

	public CategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}
	
}

