package org.openiam.idm.srvc.prov.admin.service;
// Generated Apr 19, 2008 11:35:32 PM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openiam.idm.srvc.prov.admin.dto.ProvisionConnector;

/**
 * Home object for domain model class ProvisionConnector.
 * @see org.openiam.idm.srvc.ProvisionConnector
 * @author Hibernate Tools
 */
public class ProvisionConnectorHome {

    private static final Log log = LogFactory.getLog(ProvisionConnectorHome.class);

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
    
    public void persist(ProvisionConnector transientInstance) {
        log.debug("persisting ProvisionConnector instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ProvisionConnector instance) {
        log.debug("attaching dirty ProvisionConnector instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ProvisionConnector instance) {
        log.debug("attaching clean ProvisionConnector instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ProvisionConnector persistentInstance) {
        log.debug("deleting ProvisionConnector instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ProvisionConnector merge(ProvisionConnector detachedInstance) {
        log.debug("merging ProvisionConnector instance");
        try {
            ProvisionConnector result = (ProvisionConnector) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ProvisionConnector findById( java.lang.String id) {
        log.debug("getting ProvisionConnector instance with id: " + id);
        try {
            ProvisionConnector instance = (ProvisionConnector) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.ProvisionConnector", id);
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
    
    public List findByExample(ProvisionConnector instance) {
        log.debug("finding ProvisionConnector instance by example");
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("org.openiam.idm.srvc.ProvisionConnector")
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

