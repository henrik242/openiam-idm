package org.openiam.idm.srvc.role.service;
// Generated Mar 4, 2008 1:12:08 AM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.role.dto.RolePolicy;
import org.openiam.idm.srvc.user.dto.UserAttribute;

/**
 * Data access interface for domain model class RoleAttribute.
 * @see org.openiam.idm.srvc.role.dto.RoleAttribute
 */
public class RolePolicyDAOImpl implements RolePolicyDAO {

    private static final Log log = LogFactory.getLog(RolePolicyDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.role.service.RoleAttributeDAO#add(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
    public void add(RolePolicy transientInstance) {
        log.debug("persisting RoleAttribute instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (HibernateException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    

    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.service.RoleAttributeDAO#remove(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
    public void remove(RolePolicy persistentInstance) {
        log.debug("deleting RoleAttribute instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (HibernateException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.role.service.RoleAttributeDAO#update(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
    public RolePolicy update(RolePolicy detachedInstance) {
        log.debug("merging RoleAttribute instance");
        try {
        	RolePolicy result = (RolePolicy) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (HibernateException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public RolePolicy findById( java.lang.String id) {
        log.debug("getting RoleAttribute instance with id: " + id);
        try {
        	RolePolicy instance = (RolePolicy) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.role.dto.RolePolicy", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (HibernateException re) {
            log.error("get failed", re);
            throw re;
        }
    }


	public List<RolePolicy> findRolePolicies(String serviceId, String roleId) {
		 try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.role.dto.RolePolicy rp" +
					" 	where rp.serviceId = :serviceId and rp.roleId = :roleId " +
					"   order by rp.executionOrder ");
			qry.setString("serviceId", serviceId);
			qry.setString("roleId", roleId);
			List<RolePolicy> results = (List<RolePolicy>)qry.list();
			return results;
		 }catch(HibernateException he ) {
	            log.error("get failed", he);
	            throw he;			 
		 }
		
	}
    

    


}

