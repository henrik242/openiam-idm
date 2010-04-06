package org.openiam.idm.srvc.auth.login;

// Generated May 22, 2009 10:08:01 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.auth.dto.AuthState;
import static org.hibernate.criterion.Example.create;

/**
 * DAO implementation object for AuthState. AuthState is used to track the state of an authenticated user across a 
 * security domain an managed systems.
 * @see org.openiam.idm.srvc.auth.dto
 * @author Suneet Shah
 */
public class AuthStateDAOImpl implements AuthStateDAO {

	private static final Log log = LogFactory.getLog(AuthStateDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.auth.login.AuthStateDAO#add(org.openiam.idm.srvc.auth.dto.AuthState)
	 */
	public void add(AuthState transientInstance) {
		log.debug("persisting AuthState instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.AuthStateDAO#remove(org.openiam.idm.srvc.auth.dto.AuthState)
	 */
	public void remove(AuthState persistentInstance) {
		log.debug("deleting AuthState instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.AuthStateDAO#update(org.openiam.idm.srvc.auth.dto.AuthState)
	 */
	public AuthState update(AuthState detachedInstance) {
		log.debug("merging AuthState instance");
		try {
			AuthState result = (AuthState) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void saveAuthState(AuthState state) {
		String userId = state.getUserId();
		
		AuthState as = findById(userId);
		if (as == null) {
			// need to add this record
			add(state);
		}else {
			// update what is there
			update(state);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.AuthStateDAO#findById(java.lang.String)
	 */
	public AuthState findById(java.lang.String id) {
		log.debug("getting AuthState instance with id: " + id);
		try {
			AuthState instance = (AuthState) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.auth.dto.AuthState", id);
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




	public AuthState findByToken(String token) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.AuthState a " +
    			" where a.token = :token " );
    	qry.setString("token", token);
    	AuthState state = (AuthState)qry.uniqueResult();
		if (state == null) {
			log.debug("get successful, no state record found");
		} else {
			log.debug("get successful, state record found");
		}
		return state;  	   	
	}
}
