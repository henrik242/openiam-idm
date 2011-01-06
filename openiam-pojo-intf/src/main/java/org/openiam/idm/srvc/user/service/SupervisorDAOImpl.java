package org.openiam.idm.srvc.user.service;
// Generated Feb 18, 2008 3:56:08 PM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.UserAttribute;

import static org.hibernate.criterion.Example.create;

/**
 * Data Access Object implementation for domain model class Supervisor.
 * @see org.openidm.srvc.dto.Supervisor
 */
public class SupervisorDAOImpl implements SupervisorDAO  {

    private static final Log log = LogFactory.getLog(SupervisorDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.user.service.SupervisorDAO#add(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
    public void add(Supervisor transientInstance) {
        log.debug("persisting Supervisor instance");
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
	 * @see org.openiam.idm.srvc.user.service.SupervisorDAO#remove(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
    public void remove(Supervisor persistentInstance) {
        log.debug("deleting Supervisor instance");
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
	 * @see org.openiam.idm.srvc.user.service.SupervisorDAO#update(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
    public Supervisor update(Supervisor detachedInstance) {
        log.debug("merging Supervisor instance");
        try {
            Supervisor result = (Supervisor) sessionFactory.getCurrentSession()
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
	 * @see org.openiam.idm.srvc.user.service.SupervisorDAO#findById(java.lang.String)
	 */
    public Supervisor findById( java.lang.String id) {
        log.debug("getting Supervisor instance with id: " + id);
        try {
            Supervisor instance = (Supervisor) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.user.dto.Supervisor", id);
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
	 * @see org.openiam.idm.srvc.user.service.SupervisorDAO#findByExample(org.openiam.idm.srvc.user.dto.Supervisor)
	 */
    public List<Supervisor> findByExample(Supervisor instance) {
        log.debug("finding Supervisor instance by example");
        try {
            List<Supervisor> results = (List<Supervisor>) sessionFactory.getCurrentSession()
                    .createCriteria("org.openiam.idm.srvc.user.dto.Supervisor")
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
    
    /**
     * Returns a list of Supervisor objects that represents the employees or users for this supervisor
     * @param supervisorId
     * @return
     */
    public List<Supervisor> findEmployees(String supervisorId) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
    			" where s.supervisor.userId = :supervisorId " +
    			" order by s.supervisor asc");
    	qry.setString("supervisorId", supervisorId);
    	List<Supervisor> results = (List<Supervisor>)qry.list();

    	// initalize the objects in the collection
    	
    	int listSize = results.size();
    	for (int i=0; i<listSize; i++) {
    		Supervisor supr = results.get(i);
    		org.hibernate.Hibernate.initialize(supr.getSupervisor());
    		org.hibernate.Hibernate.initialize(supr.getEmployee());
    	}
    	
    	return results;
    	
    }

    public List<Supervisor> findSupervisors(String employeeId) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
    			" where s.employee.userId = :employeeId ");
    	qry.setString("employeeId", employeeId);
    	List<Supervisor> results = (List<Supervisor>)qry.list();
    	return results;    	
    }
    
    public Supervisor findPrimarySupervisor(String employeeId) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
    			" where s.employee.userId = :employeeId and s.isPrimarySuper = 1 " +
    			" order by s.supervisor asc");
    	qry.setString("employeeId", employeeId);
    	Supervisor supr = (Supervisor)qry.uniqueResult();
    	if (supr == null)
    		return null;

    	org.hibernate.Hibernate.initialize(supr.getSupervisor());
    	org.hibernate.Hibernate.initialize(supr.getEmployee());
   	
    	return supr;
    	//List<Supervisor> results = (List<Supervisor>)qry.list();
    	//return results;        	
    }

}



