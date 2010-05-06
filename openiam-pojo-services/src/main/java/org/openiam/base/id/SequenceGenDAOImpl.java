package org.openiam.base.id;
// Generated Dec 2, 2007 5:41:38 PM by Hibernate Tools 3.2.0.b11


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
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Home object for domain model class SequenceGen.
 * @see org.openidm.srvc.dto.SequenceGen
 * @author Hibernate Tools
 */
public class SequenceGenDAOImpl implements SequenceGenDAO {

    private static final Log log = LogFactory.getLog(SequenceGenDAOImpl.class);

	   private HibernateTemplate hibernateTemplate;  
	   private SessionFactory sessionFactory;

	   public void setSessionFactory(SessionFactory session) {
			hibernateTemplate = new HibernateTemplate(session);
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
	   
	   public synchronized String getNextId(String key) throws DataException {

		   try{
			   SequenceGen seq = this.findById(key);
			   //SequenceGen seq = findIdForUpdate(key);
			   if (seq == null) {
				   return null;
			   }
			   seq.incrementId();
			   
			   hibernateTemplate.saveOrUpdate(seq);
			   return String.valueOf( seq.getNextId() );
			    
		   }catch(DataAccessException dae) {	
			   dae.printStackTrace();
			   log.error("Add operation failed.", dae);
			   throw new DataException( dae.getMessage(), dae.getCause() );
		   }
		   
	   }
	   
    
     
    public SequenceGen findById( java.lang.String id) throws DataException {
        log.debug("getting SequenceGen instance with id: " + id);
        try { 
        	SequenceGen s = new SequenceGen(id);
        	SequenceGen seq =  (SequenceGen)hibernateTemplate
        			.get(org.openiam.base.id.SequenceGen.class, id, LockMode.UPGRADE);
        	//hibernateTemplate.lock(seq, LockMode.UPGRADE);        	
        	return seq;
    	   }catch(DataAccessException dae) {		   
    		   log.error("Find operation failed.", dae);
    		   throw new DataException( dae.getMessage(), dae.getCause() );
    	   }		
    	   
    }


    
 
}

