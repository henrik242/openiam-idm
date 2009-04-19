package org.openiam.idm.srvc.auth.login;
// Generated Feb 18, 2008 3:56:08 PM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.base.id.SequenceGenDAO;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.user.dto.Supervisor;

import static org.hibernate.criterion.Example.create;

/**
 * Data access interface for domain model class Login.
 * @see org.openiam.idm.srvc.auth.dto.Login
 */
public class LoginDAOImpl implements LoginDAO {

    private static final Log log = LogFactory.getLog(LoginDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#add(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public Login add(Login transientInstance) {
        log.debug("persisting Login instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
            
            return transientInstance;
            
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
   
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#remove(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public void remove(Login persistentInstance) {
        log.debug("deleting Login instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#update(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public Login update(Login detachedInstance) {
        log.debug("merging Login instance");
        try {
            Login result = (Login) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#findById(org.openiam.idm.srvc.auth.dto.LoginId)
	 */
    public Login findById( org.openiam.idm.srvc.auth.dto.LoginId id) {
        log.debug("getting Login instance with id: " + id);
        try {
            Login instance = (Login) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.auth.dto.Login", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
                return null;
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#findByExample(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public List<Login> findByExample(Login instance) {
        log.debug("finding Login instance by example");
        try {
            List<Login> results = (List<Login>) sessionFactory.getCurrentSession()
                    .createCriteria("org.openiam.idm..srvc.auth.dto.Login")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    
    public List<Login> findUser(String userId) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.user.userId = :userId order by l.status desc, l.id.managedSysId asc " );
    	qry.setString("userId", userId);
    	List<Login> results = (List<Login>)qry.list();
    	return results;
    	
    }
    
    public List<Login> findLoginByDomain(String domain) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.id.serviceId = :domain " );
    	qry.setString("domain", domain);
    	List<Login> results = (List<Login>)qry.list();
    	return results;    	
    }
    
}

