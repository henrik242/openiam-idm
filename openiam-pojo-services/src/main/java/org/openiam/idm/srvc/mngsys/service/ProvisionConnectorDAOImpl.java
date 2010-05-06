package org.openiam.idm.srvc.mngsys.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;



/**
 * Data access object implementation for ProvisionConnector. 
 */
public class ProvisionConnectorDAOImpl implements ProvisionConnectorDAO {

	private SessionFactory sessionFactory;

	private static final Log log = LogFactory
			.getLog(ProvisionConnectorDAOImpl.class);

	
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
	 * @see org.openiam.idm.srvc.mngsys.service.ProvisionConnectorDAO#add(org.openiam.idm.srvc.mngsys.dto.ProvisionConnector)
	 */
	public void add(ProvisionConnector transientInstance) {
		log.debug("persisting ProvisionConnector instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ProvisionConnectorDAO#remove(org.openiam.idm.srvc.mngsys.dto.ProvisionConnector)
	 */
	public void remove(ProvisionConnector persistentInstance) {
		log.debug("deleting ProvisionConnector instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ProvisionConnectorDAO#update(org.openiam.idm.srvc.mngsys.dto.ProvisionConnector)
	 */
	public ProvisionConnector update(ProvisionConnector detachedInstance) {
		log.debug("merging ProvisionConnector instance");
		try {
			ProvisionConnector result = (ProvisionConnector) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("Update failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ProvisionConnectorDAO#findById(java.lang.String)
	 */
	public ProvisionConnector findById(java.lang.String id) {
		log.debug("getting ProvisionConnector instance with id: " + id);
		try {
			ProvisionConnector instance = (ProvisionConnector) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.mngsys.dto.ProvisionConnector", id);
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
	 * @see org.openiam.idm.srvc.mngsys.service.ProvisionConnectorDAO#findAllConnectors()
	 */
	public List<ProvisionConnector> findAllConnectors() {
		log.debug("getting all ProvisionConnector instances " );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ProvisionConnector p ");
		List<ProvisionConnector> result = (List<ProvisionConnector>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.mngsys.service.ProvisionConnectorDAO#findConnectorByType(java.lang.String)
	 */
	public List<ProvisionConnector> findConnectorByType(String typeId) {
		log.debug("getting Provisioning instances for type:" + typeId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ProvisionConnector p " +
						" where a.metadataTypeId = :typeId " );
		qry.setString("typeId", typeId);
		List<ProvisionConnector> result = (List<ProvisionConnector>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;
	}	
	
}
