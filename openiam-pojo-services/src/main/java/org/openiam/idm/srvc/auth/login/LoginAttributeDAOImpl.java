package org.openiam.idm.srvc.auth.login;
// Generated Feb 18, 2008 3:56:08 PM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.openiam.base.id.SequenceGenDAO;
import org.openiam.idm.srvc.auth.dto.LoginAttribute;

import static org.hibernate.criterion.Example.create;

/**
 * HData access interface for domain model class LoginAttribute.
 * @see org.openiam.idm.srvc.auth.dto.LoginAttribute
 */
public class LoginAttributeDAOImpl implements LoginAttributeDAO {

    private static final Log log = LogFactory.getLog(LoginAttributeDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.auth.login.LoginAttributeDAO#add(org.openiam.idm.srvc.auth.dto.LoginAttribute)
	 */
    public void add(LoginAttribute transientInstance) {
        log.debug("persisting LoginAttribute instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginAttributeDAO#remove(org.openiam.idm.srvc.auth.dto.LoginAttribute)
	 */
    public void remove(LoginAttribute persistentInstance) {
        log.debug("deleting LoginAttribute instance");
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
	 * @see org.openiam.idm.srvc.auth.login.LoginAttributeDAO#update(org.openiam.idm.srvc.auth.dto.LoginAttribute)
	 */
    public LoginAttribute update(LoginAttribute detachedInstance) {
        log.debug("merging LoginAttribute instance");
        try {
            LoginAttribute result = (LoginAttribute) sessionFactory.getCurrentSession()
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
	 * @see org.openiam.idm.srvc.auth.login.LoginAttributeDAO#findById(java.lang.String)
	 */
    public LoginAttribute findById( java.lang.String id) {
        log.debug("getting LoginAttribute instance with id: " + id);
        try {
            LoginAttribute instance = (LoginAttribute) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm..srvc.auth.login.LoginAttribute", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
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
	 * @see org.openiam.idm.srvc.auth.login.LoginAttributeDAO#findByExample(org.openiam.idm.srvc.auth.dto.LoginAttribute)
	 */
    public List<LoginAttribute> findByExample(LoginAttribute instance) {
        log.debug("finding LoginAttribute instance by example");
        try {
            List<LoginAttribute> results = (List<LoginAttribute>) sessionFactory.getCurrentSession()
                    .createCriteria("org.openiam.idm..srvc.auth.login.LoginAttribute")
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
}

