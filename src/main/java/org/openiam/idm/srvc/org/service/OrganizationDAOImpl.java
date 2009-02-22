package org.openiam.idm.srvc.org.service;

// Generated Jun 12, 2007 10:46:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;


import org.openiam.idm.srvc.org.dto.*;

/**
 * Data access object implementation for Organization. 
 */
public class OrganizationDAOImpl implements OrganizationDAO {

	private static final Log log = LogFactory.getLog(OrganizationDAOImpl.class);

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




	public Organization findById(java.lang.String id) {
		log.debug("getting Organization instance with id: " + id);
		try {
			Organization instance = (Organization) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.org.dto.Organization", id);
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

	public List findByExample(Organization instance) {
		log.debug("finding Company instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"org.openiam.idm.srvc.user.Company").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public void add(Organization instance) {
		log.debug("persisting Organization instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
		
	}

	public List<Organization> findChildOrganization(String orgId) {
		log.debug("getting Organization instances for childobjects of  " + orgId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.org.dto.Organization a " +
						" where a.parentId = :orgId " );
		qry.setString("parentId", orgId);
		qry.setCacheable(true);
		List<Organization> result = (List<Organization>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;
	}

	public Organization findParent(String orgId) {
		Organization curOrg = findById(orgId);
		if (curOrg != null && curOrg.getParentId() != null) {
			return findById(curOrg.getParentId());
		}
		
		return null;
	}

	public void remove(Organization instance) {
		log.debug("deleting Address instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}			
	}

	public void update(Organization instance) {
		log.debug("merging Organization instance");
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}		
	}
	
	/**
	 * Returns a list of Organization objects that are root level entities; ie. they 
	 * don't have a parent. 
	 * @return
	 */
	public List<Organization> findRootOrganizations() {
		log.debug("getting Organization instances at parent level  "  );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.org.dto.Organization a " +
						" where a.parentId = null " );
		List<Organization> result = (List<Organization>)qry.list();
		qry.setCacheable(true);
		if (result == null || result.size() == 0)
			return null;
		return result;
		
	}
	
	public List<Organization> findOrganizationByType(String type ) {
		log.debug("getting Organization for a type  "  );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.org.dto.Organization a " +
						" where a.metadataTypeId = :type " );
		qry.setString("type", type);
		List<Organization> result = (List<Organization>)qry.list();	
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}
	
	public List<Organization> findAllOrganization() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.org.dto.Organization a " +
						" rder by a.orgId asc " );
		List<Organization> result = (List<Organization>)qry.list();	
		if (result == null || result.size() == 0)
			return null;
		return result;			
	}
	
	public List<Organization> search(String name, String type) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria =  session.createCriteria(org.openiam.idm.srvc.org.dto.Organization.class);
		if (name == null && type == null) {
			// show all the orgs
			criteria.add(Restrictions.like("organizationName", "%"));
		}
		if (name != null) {
			criteria.add(Restrictions.like("organizationName", name + "%"));
		}
		if (type != null) {
			criteria.add(Restrictions.eq("metadataTypeId", type));
		}
		return criteria.list();
		
	}
		
	
}
