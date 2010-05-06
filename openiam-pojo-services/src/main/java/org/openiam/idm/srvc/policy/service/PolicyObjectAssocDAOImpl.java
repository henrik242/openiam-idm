package org.openiam.idm.srvc.policy.service;

// Generated Dec 1, 2009 12:48:52 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.policy.dto.PolicyObjectAssoc;
import org.openiam.idm.srvc.policy.dto.PolicyObjectAssocId;


/**
 * Home object for domain model class PolicyObjectAssoc.
 * @see org.openiam.idm.srvc.pswd.service.PolicyObjectAssoc
 * @author Hibernate Tools
 */
public class PolicyObjectAssocDAOImpl implements PolicyObjectAssocDAO {

	private static final Log log = LogFactory
			.getLog(PolicyObjectAssocDAOImpl.class);

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

	public void add(PolicyObjectAssoc transientInstance) {
		log.debug("persisting PolicyObjectAssoc instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	public void remove(PolicyObjectAssoc persistentInstance) {
		log.debug("deleting PolicyObjectAssoc instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PolicyObjectAssoc update(PolicyObjectAssoc detachedInstance) {
		log.debug("merging PolicyObjectAssoc instance");
		try {
			PolicyObjectAssoc result = (PolicyObjectAssoc) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PolicyObjectAssoc findById(
			PolicyObjectAssocId id) {
		log.debug("getting PolicyObjectAssoc instance with id: " + id);
		try {
			PolicyObjectAssoc instance = (PolicyObjectAssoc) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.policy.dto.PolicyObjectAssocId",
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
	
//	Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.UserAttribute ua where ua.userId = :userId order by ua.name asc");
//	qry.setString("userId", userId);
	
	public PolicyObjectAssoc findAssociationByLevel(String level, String value) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.policy.dto.PolicyObjectAssoc p " +
					" where p.associationLevel = :level and p.associationValue = :value " );
			qry.setString("level", level);
			qry.setString("value", value);
			List<PolicyObjectAssoc> results = (List<PolicyObjectAssoc>)qry.list();

			if (results == null || results.isEmpty()) {
				log.info("No policyAssociation objects found.");
				return null;
			}else {
				log.info("PolicAssoc found. Count=" + results.size());
			}
			return results.get(0);
		} catch (HibernateException re) {
			log.error("findAssociationByLevel failed", re);
			throw re;
		}		
	}




}
