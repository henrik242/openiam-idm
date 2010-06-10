package org.openiam.idm.srvc.cd.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import org.openiam.exception.data.DataException;
import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.dto.ReferenceDataId;
import org.openiam.idm.srvc.org.dto.Organization;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * DAO implementation class for an RDBMS.
 * @author Suneet Shah
 */
public class ReferenceDAOImpl implements ReferenceDAO {

    private static final Log log = LogFactory.getLog(ReferenceDAOImpl.class);

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
 
    

	public ReferenceData add(ReferenceData instance) throws DataException {
		log.debug("persisting ReferenceData instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new DataException(re.getMessage(), re.getCause());
		}
	}

	public ReferenceData findById(ReferenceDataId id) throws DataException {
        log.debug("getting ReferenceData instance with id: " + id);
        try { 
        	ReferenceData instance = (ReferenceData)sessionFactory.getCurrentSession().get("org.openiam.idm.srvc.cd.dto.ReferenceData", id);
        	if (instance == null) {
        		log.debug("get successful, no instance found");
        	}else {
        		log.debug("get successful, instance found");
        	}
        	return instance;
 	   }catch(DataAccessException dae) {		   
 		   log.error("findby Primary failed.", dae);
 		   throw new DataException( dae.getMessage(), dae.getCause() );
 	   }		

	}
	
	

	public void remove(ReferenceData instance) throws DataException {
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
	   }catch(DataAccessException dae) {		   
		   log.error("Remove operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }
	}
	
	
	
	public void update(ReferenceData instance) throws DataException {
		   try {
			   sessionFactory.getCurrentSession().saveOrUpdate(instance); 
			   log.debug("merge successful");
		   }catch(DataAccessException dae) {		   
			   log.error("Update operation failed.", dae);
			   throw new DataException( dae.getMessage(), dae.getCause() );
		   }		
	} 

	public List<ReferenceData> findByGroup(String codeGroup, String languageCd) throws DataException {
		   try {
			   Session session = sessionFactory.getCurrentSession();
			   Query qry = session.createQuery("from org.openiam.idm.srvc.cd.dto.ReferenceData rd " +
			   				" where rd.id.codeGroup = :codeGroup and " + 
			   				" 		rd.id.languageCd = :languageCd "		);
			   qry.setString("codeGroup",codeGroup);
			   qry.setString("languageCd", languageCd);
			   qry.setCacheable(true);
			   List<ReferenceData> result = (List<ReferenceData>)qry.list();
			   if (result == null || result.size() == 0) {
			   	return null;
			   }
			   return result;
		   }catch(DataAccessException dae) {		   
			   log.error("Update operation failed.", dae);
			   throw new DataException( dae.getMessage(), dae.getCause() );
		   }		
	}

}



