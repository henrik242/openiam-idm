package org.openiam.idm.srvc.role.service;
// Generated Mar 4, 2008 1:12:08 AM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.idm.srvc.role.dto.*;

/**
 * Data access interface for domain model class RoleAttribute.
 * @see org.openiam.idm.srvc.role.dto.RoleAttribute
 */
public class RoleAttributeDAOImpl implements RoleAttributeDAO {

    private static final Log log = LogFactory.getLog(RoleAttributeDAOImpl.class);

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
    public void add(RoleAttribute transientInstance) {
        log.debug("persisting RoleAttribute instance");
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
	 * @see org.openiam.idm.srvc.role.service.RoleAttributeDAO#remove(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
    public void remove(RoleAttribute persistentInstance) {
        log.debug("deleting RoleAttribute instance");
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
	 * @see org.openiam.idm.srvc.role.service.RoleAttributeDAO#update(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
    public RoleAttribute update(RoleAttribute detachedInstance) {
        log.debug("merging RoleAttribute instance");
        try {
            RoleAttribute result = (RoleAttribute) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public RoleAttribute findById( java.lang.String id) {
        log.debug("getting RoleAttribute instance with id: " + id);
        try {
            RoleAttribute instance = (RoleAttribute) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.role.dto.RoleAttribute", id);
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
	 * @see org.openiam.idm.srvc.role.service.RoleAttributeDAO#findByExample(org.openiam.idm.srvc.role.dto.RoleAttribute)
	 */
    public List<RoleAttribute> findByExample(RoleAttribute instance) {
        log.debug("finding RoleAttribute instance by example");
        try {
            List<RoleAttribute> results = (List<RoleAttribute>) sessionFactory.getCurrentSession()
                    .createCriteria("org.openiam.idm.srvc.role.dto.RoleAttribute")
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
    
	public void deleteRoleAttributes(String serviceId, String roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.role.dto.RoleAttribute ra " + 
					" where ra.serviceId = :serviceId and ra.roleId = :roleId ");
		qry.setString("serviceId", serviceId);
		qry.setString("roleId",roleId);
		qry.executeUpdate();

		
	}


}

