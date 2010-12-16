package org.openiam.idm.srvc.audit.service;
// Generated Nov 30, 2007 3:01:47 AM by Hibernate Tools 3.2.0.b11


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;

import org.springframework.dao.*;

import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.dto.SearchAudit;
import org.openiam.exception.data.*;

/**
 * RDMBS implementation the DAO for IdmAudit
 * @see org.openiam.idm.srvc.audit.dto.IdmAuditLog
 * @author Suneet Shah 
 */
public class IdmAuditLogDAOImpl implements IdmAuditLogDAO {

    private static final Log log = LogFactory.getLog(IdmAuditLogDAOImpl.class);
 
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

   /**
	 * Adds a new ReferenceDAO object to the data log. If successful, the method
	 * return an IdmAuditLog object that contains the system generated ID.
	 */   
   public IdmAuditLog add(IdmAuditLog instance) throws DataException {
	   try {
		   if (instance.getActionDatetime() == null) {
			   instance.setActionDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		   }
		   sessionFactory.getCurrentSession().persist(instance);
		   
		   return instance;
		    
	   }catch(DataAccessException dae) {	
		   dae.printStackTrace();
		   log.error("Add operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }
   }




	public void remove(IdmAuditLog instance) throws DataException {
		try {
			sessionFactory.getCurrentSession().delete(instance);
	   }catch(DataAccessException dae) {		   
		   log.error("Remove operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }

	}

	public void update(IdmAuditLog instance) throws DataException {
	   try {
		   sessionFactory.getCurrentSession().merge(instance);
	   }catch(DataAccessException dae) {		   
		   log.error("Update operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }		
	}

    public IdmAuditLog findById( java.lang.String id) throws DataException {
        log.debug("getting IdmAuditLog instance with id: " + id);
       try { 
    	   
    	return (IdmAuditLog)sessionFactory.getCurrentSession()
    			.get(org.openiam.idm.srvc.audit.dto.IdmAuditLog.class, id);
	   }catch(DataAccessException dae) {		   
		   log.error("Update operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }		
    }

    public List<IdmAuditLog> findAll() {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.audit.dto.IdmAuditLog log");
    	List results = (List<IdmAuditLog>)qry.list();
    	return results;
    }
    
    public List<IdmAuditLog> findPasswordEvents()  {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.audit.dto.IdmAuditLog log " + 
    				" where log.actionId = 'PASSWORD CHANGE' OR log.actionId = 'PASSWORD RESET'");
    	List results = (List<IdmAuditLog>)qry.list();
    	return results;
    	
    }
    
    public List<IdmAuditLog> search(SearchAudit search) throws DataException {
    	List results = null;
    	
    	if (search == null) {
    		throw new NullPointerException("Search parameter is null");
    	}
    	
    	try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =  session.createCriteria(org.openiam.idm.srvc.audit.dto.IdmAuditLog.class);
			// build the criteria list
			if (search.getApplicationName() != null) {
				criteria.add(Restrictions.eq("domainId",search.getApplicationName()));
			}
			if (search.getLoginId() != null) {
				log.info("audit log search: principal = " + search.getLoginId());
				criteria.add(Restrictions.eq("principal", search.getLoginId()));
			}

			if (search.getSrcSystemId() != null) {
				criteria.add(Restrictions.eq("srcSystemId", search.getSrcSystemId()));
			}
			
			if (search.getUserId() != null ) {
				criteria.add(Restrictions.eq("userId", search.getUserId()));
			}
			if (search.getStartDate() != null ) {
				criteria.add(Restrictions.ge("actionDatetime",  search.getStartDate()));
			}

			if (search.getCustomAttr1() != null ) {
				criteria.add(Restrictions.eq("customAttrname1",  search.getCustomAttr1()));
			}
			if (search.getCustomAttrValue1() != null ) {
				criteria.add(Restrictions.eq("customAttrvalue1",  search.getCustomAttrValue1()));
			}
			if (search.getReason() != null ) {
				criteria.add(Restrictions.eq("reason",  search.getReason()));
			}

			if (search.getObjectTypeId() != null ) {
				criteria.add(Restrictions.eq("objectTypeId",  search.getObjectTypeId()));
			}
			if (search.getActionId() != null ) {
				criteria.add(Restrictions.eq("actionId",  search.getActionId()));
			}
			if (search.getObjectId() != null ) {
				criteria.add(Restrictions.eq("objectId",  search.getObjectId()));
			}
			if (search.getRequestId() != null ) {
				criteria.add(Restrictions.eq("requestId",  search.getRequestId()));
			}
	
	    
			
			
			if (search.getEndDate() != null ) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(search.getEndDate());
				calendar.add(Calendar.DAY_OF_MONTH, 1);

				// less than equal may not have the full end date
				criteria.add(Restrictions.lt("actionDatetime", calendar.getTime()));
			}		
			// build the action list of criteria
			String[] actionAry = search.getActionList();
			if (actionAry != null) {
				criteria.add(Restrictions.in("actionId", search.getActionList()));
			}
			criteria.addOrder(Order.asc("actionDatetime"));
			results = criteria.list();
			
    	}catch(HibernateException he) {
			   log.error("search operation failed.", he);
			   throw new DataException( he.getMessage(), he.getCause() );       						
    	}
    	
    	return results;
    	
    }
    
    public List findByCriteria(IdmAuditLog instance) throws DataException {
        log.debug("finding IdmAuditLog instance by example");
    
        try {
            List results =  sessionFactory.getCurrentSession()
            					.createCriteria("org.openiam.idm.srvc.audit.dto.IdmAuditLog")
            					.add(Example.create(instance))
            					.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
			   log.error("Update operation failed.", re);
			   throw new DataException( re.getMessage(), re.getCause() );       					
        }

        

    }
     
}



