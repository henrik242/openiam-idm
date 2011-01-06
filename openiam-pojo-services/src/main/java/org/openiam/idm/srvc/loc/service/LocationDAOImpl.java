package org.openiam.idm.srvc.loc.service;

// Generated May 9, 2009 1:42:34 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.dto.LocationSearch;


import static org.hibernate.criterion.Example.create;

/**
 * DAO to manage the list of Locations
 * @see org.openiam.idm.srvc.loc.dto.Location
 * @author Suneet shah
 */
public class LocationDAOImpl implements LocationDAO {

	private static final Log log = LogFactory.getLog(LocationDAOImpl.class);

	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDAO#add(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public void add(Location transientInstance) {
		log.debug("persisting Location instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDAO#remove(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public void remove(Location persistentInstance) {
		log.debug("deleting Location instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDAO#update(org.openiam.idm.srvc.loc.dto.Location)
	 */
	public Location update(Location detachedInstance) {
		log.debug("merging Location instance");
		try {
			Location result = (Location) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDAO#findById(java.lang.String)
	 */
	public Location findById(java.lang.String id) {
		log.debug("getting Location instance with id: " + id);
		try {
			Location instance = (Location) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.loc.dto.Location", id);
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
	 * @see org.openiam.idm.srvc.loc.service.LocationDAO#findAllLocations()
	 */
	public List<Location> findAllLocations() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.loc.dto.Location l " +
					" order by l.name asc");
			List<Location> results = (List<Location>)qry.list();
			return results;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.loc.service.LocationDAO#searchLocations(org.openiam.idm.srvc.loc.dto.LocationSearch)
	 */
	public List<Location> searchLocations(LocationSearch search) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(org.openiam.idm.srvc.loc.dto.Location.class);
		
		if (search.getAddress1() != null  ) {
			log.info("search: address1=" + search.getAddress1() );
			crit.add(Restrictions.like("address1",search.getAddress1()));
		}
		if (search.getName() != null  ) {
			log.info("search: name=" + search.getName() );
			crit.add(Restrictions.eq("name",search.getName()));
		}
		
		crit.addOrder(Order.asc("address1"));
		
		List<Location> results = (List<Location>)crit.list();
		return results;		

	}

}
