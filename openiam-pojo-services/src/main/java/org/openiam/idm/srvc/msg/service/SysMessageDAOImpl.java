package org.openiam.idm.srvc.msg.service;

// Generated Nov 27, 2009 11:18:13 PM by Hibernate Tools 3.2.2.GA

import java.util.List;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.msg.service.SysMessageDAO;



/**
 * Home object for domain model class SysMessageDelivery.
 * @see org.openiam.idm.srvc.msg.dto.SysMessage
 * @author Hibernate Tools
 */
public class SysMessageDAOImpl implements SysMessageDAO {

	private static final Log log = LogFactory
			.getLog(SysMessageDAO.class);

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

	public SysMessage add(SysMessage transientInstance) {
		log.debug("persisting SysMessageDelivery instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(SysMessage persistentInstance) {
		log.debug("deleting SysMessageDelivery instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SysMessage update(SysMessage detachedInstance) {
		log.debug("merging SysMessageDelivery instance");
		try {
			SysMessage result = (SysMessage) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SysMessage findById(java.lang.String id) {
		log.debug("getting SysMessageDelivery instance with id: " + id);
		try {
			SysMessage instance = (SysMessage) sessionFactory
					.getCurrentSession()
					.get(
							"org.openiam.idm.srvc.msg.dto.SysMessage",
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
	 * @see org.openiam.idm.srvc.msg.service.SysMessageDAO#findAll()
	 */
	public List<SysMessage> findAll() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.msg.dto.SysMessage msg " +
					" order by msg.name asc");
			List<SysMessage> results = (List<SysMessage>)qry.list();
			return results;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}


}
