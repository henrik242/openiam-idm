package org.openiam.idm.srvc.meta.service;

// Generated Nov 4, 2008 12:11:29 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.dto.MetadataType;

/**
 * DAO Implementation for MetadataElement
 */
public class MetadataElementDAOImpl implements MetadataElementDAO {

	private static final Log log = LogFactory.getLog(MetadataElementDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.meta.service.MetadataElementDAO#add(org.openiam.idm.srvc.meta.dto.MetadataElement)
	 */
	public void add(MetadataElement transientInstance) {
		log.debug("persisting MetadataElement instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadataElementDAO#remove(org.openiam.idm.srvc.meta.dto.MetadataElement)
	 */
	public void remove(MetadataElement persistentInstance) {
		log.debug("deleting MetadataElement instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadataElementDAO#update(org.openiam.idm.srvc.meta.dto.MetadataElement)
	 */
	public MetadataElement update(MetadataElement detachedInstance) {
		log.debug("merging MetadataElement instance");
		try {
			MetadataElement result = (MetadataElement) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadataElementDAO#findById(java.lang.String)
	 */
	public MetadataElement findById(java.lang.String id) {
		log.debug("getting MetadataElement instance with id: " + id);
		try {
			MetadataElement instance = (MetadataElement) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.meta.dto.MetadataElement",
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
	
	public List<MetadataElement> findbyCategoryType(String categoryType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("select me from MetadataElement me, " +
					"		CategoryType ct, MetadataType m " +
					" where ct.id.categoryId = :categoryId and " +
					"       ct.id.typeId  = m.metadataTypeId  and " +
					" 	    m.metadataTypeId = me.metadataTypeId " + 
					" order by me.metadataTypeId, me.attributeName ");
			qry.setString("categoryId", categoryType);
			List<MetadataElement> results = (List<MetadataElement>)qry.list();
			return results;	
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.meta.service.MetadataElementDAO#findByExample(org.openiam.idm.srvc.meta.dto.MetadataElement)
	 */
	public List<MetadataElement> findByExample(MetadataElement instance) {
		log.debug("finding MetadataElement instance by example");
		try {
			List<MetadataElement> results = (List<MetadataElement>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"org.openiam.idm.srvc.meta.service.MetadataElement")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public void removeByParentId(String id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("delete org.openiam.idm.srvc.meta.dto.MetadataElement me " + 
						" where me.metadataTypeId = :id ");
			qry.setString("id", id);
			qry.executeUpdate();		
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
		}
	

}
