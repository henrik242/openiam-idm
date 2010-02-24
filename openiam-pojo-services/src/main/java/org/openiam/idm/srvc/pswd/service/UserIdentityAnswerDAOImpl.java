package org.openiam.idm.srvc.pswd.service;

// Generated Aug 23, 2009 12:07:53 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestion;
import org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer;

import static org.hibernate.criterion.Example.create;

/**
 * DAO implementation object for the domain model class  UserIdentityAnswer.
 */
public class UserIdentityAnswerDAOImpl implements UserIdentityAnswerDAO {

	private static final Log log = LogFactory.getLog(UserIdentityAnswerDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.pswd.service.UserIdentityAnswerDAO#add(org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer)
	 */
	public UserIdentityAnswer add(UserIdentityAnswer transientInstance) {
		log.debug("persisting UserIdentityAns instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.UserIdentityAnswerDAO#delete(org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer)
	 */
	public void delete(UserIdentityAnswer persistentInstance) {
		log.debug("deleting UserIdentityAns instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.UserIdentityAnswerDAO#update(org.openiam.idm.srvc.pswd.dto.UserIdentityAnswer)
	 */
	public UserIdentityAnswer update(UserIdentityAnswer detachedInstance) {
		log.debug("merging UserIdentityAns instance");
		try {
			UserIdentityAnswer result = (UserIdentityAnswer) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.UserIdentityAnswerDAO#findById(java.lang.String)
	 */
	public UserIdentityAnswer findById(java.lang.String id) {
		log.debug("getting UserIdentityAns instance with id: " + id);
		try {
			UserIdentityAnswer instance = (UserIdentityAnswer) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.pswd.service.UserIdentityAnswer",
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
	
	public List<UserIdentityAnswer> findAnswersByUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from UserIdentityAnswer ans "
				+ " where ans.userId = :userId" +
				  " order by ans.questionText asc ");
		qry.setString("userId", userId);

		
		List<UserIdentityAnswer> result = (List<UserIdentityAnswer>) qry.list();
		if (result == null || result.size() == 0)
			return null;

		return result;			
	}

}
