package org.openiam.idm.srvc.prov.request.service;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.hibernate.HibernateException;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;

/**
 * DAO to manage ProvisionRequest objects.
 */
public class ProvisionRequestDAOImpl implements ProvisionRequestDAO {

	private static final Log log = LogFactory.getLog(ProvisionRequestDAOImpl.class);
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
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#add(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public void add(ProvisionRequest transientInstance) {
		log.debug("persisting ProvRequest instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#remove(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public void remove(ProvisionRequest persistentInstance) {
		log.debug("deleting ProvRequest instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#update(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public ProvisionRequest update(ProvisionRequest detachedInstance) {
		log.debug("merging ProvRequest instance");
		try {
			ProvisionRequest result = (ProvisionRequest) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#findById(java.lang.String)
	 */
	public ProvisionRequest findById(java.lang.String id) {
		log.debug("getting ProvRequest instance with id: " + id);
		try {
			ProvisionRequest instance = (ProvisionRequest) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.prov.request.dto.ProvisionRequest", id);
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
	
	public  List<ProvisionRequest> findByStatus(String status) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.prov.request.dto.ProvisionRequest req " +
				" where req.status = :status order by req.requestDate asc");
		qry.setString("status", status);
		List<ProvisionRequest> results = (List<ProvisionRequest>)qry.list();
		return results;		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#findByExample(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public List<ProvisionRequest> findByExample(ProvisionRequest instance) {
		log.debug("finding ProvRequest instance by example");
		try {
			List<ProvisionRequest> results = (List<ProvisionRequest>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.prov.service.ProvRequest")
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
