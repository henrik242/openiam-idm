package org.openiam.idm.srvc.prov.admin.service;
// Generated Apr 19, 2008 11:35:32 PM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openiam.idm.srvc.prov.admin.dto.ProvisionConAttribute;

/**
 * Home object for domain model class ProvisionConAttribute.
 * @see org.openiam.idm.srvc.ProvisionConAttribute
 * @author Hibernate Tools
 */
public class ProvisionConAttributeHome {

    private static final Log log = LogFactory.getLog(ProvisionConAttributeHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(ProvisionConAttribute transientInstance) {
        log.debug("persisting ProvisionConAttribute instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ProvisionConAttribute instance) {
        log.debug("attaching dirty ProvisionConAttribute instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ProvisionConAttribute instance) {
        log.debug("attaching clean ProvisionConAttribute instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ProvisionConAttribute persistentInstance) {
        log.debug("deleting ProvisionConAttribute instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ProvisionConAttribute merge(ProvisionConAttribute detachedInstance) {
        log.debug("merging ProvisionConAttribute instance");
        try {
            ProvisionConAttribute result = (ProvisionConAttribute) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ProvisionConAttribute findById( java.lang.String id) {
        log.debug("getting ProvisionConAttribute instance with id: " + id);
        try {
            ProvisionConAttribute instance = (ProvisionConAttribute) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.ProvisionConAttribute", id);
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
    
    public List findByExample(ProvisionConAttribute instance) {
        log.debug("finding ProvisionConAttribute instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("org.openiam.idm.srvc.ProvisionConAttribute")
                    .add(Example.create(instance))
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

