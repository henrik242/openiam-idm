package org.openiam.base.id;
// Generated Dec 2, 2007 5:41:38 PM by Hibernate Tools 3.2.0.b11


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

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
import org.openiam.idm.srvc.auth.spi.LDAPLoginModule;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Home object for domain model class SequenceGen.
 * @see org.openidm.srvc.dto.SequenceGen
 * @author Hibernate Tools
 */
public class OracleSequenceGenDAOImpl  {

    private static final Log log = LogFactory.getLog(OracleSequenceGenDAOImpl.class);

	static protected ResourceBundle res = ResourceBundle.getBundle("datasource");

	
	static String driver = res.getString("openiam.driver_classname");
	static String url = res.getString("openiam.driver_url");
	static String userName = res.getString("openiam.username");
	static String password = res.getString("openiam.password");
	   
	   public synchronized String getNextId(String key) throws DataException {

		   Connection con=null;
		   int nextval=0;
		   try{
			   Class.forName(driver);
			    con = DriverManager.getConnection(url, userName, password);
			    
			    String sql = "SELECT " + key + ".nextval from Dual";
			    PreparedStatement stmt = con.prepareStatement(sql);
			    ResultSet rs =  stmt.executeQuery();
			    if ( rs.next()) {
			    	nextval = rs.getInt(1);
			    	
			    }
			    
			    
		   }catch(Exception dae) {	
			   dae.printStackTrace();
			   log.error("GetNextId Failed.", dae);
			   throw new DataException( dae.getMessage(), dae.getCause() );
		   }finally {
			    try {
			    	con.close();
			    }catch(Exception e) {
			    	e.printStackTrace();
			    }
		   }
		   return String.valueOf( nextval );
		   
	   }
	   
    
     



    
 
}

