package org.openiam.idm.srvc.auth.login;
// Generated Feb 18, 2008 3:56:08 PM by Hibernate Tools 3.2.0.b11


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.base.id.SequenceGenDAO;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;

import static org.hibernate.criterion.Example.create;

/**
 * Data access interface for domain model class Login.
 * @see org.openiam.idm.srvc.auth.dto.Login
 */
public class LoginDAOImpl implements LoginDAO {

    private static final Log log = LogFactory.getLog(LoginDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#add(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public Login add(Login transientInstance) {
        log.debug("persisting Login instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
            
            return transientInstance;
            
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
   
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#remove(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public void remove(Login lg) {
        log.debug("deleting Login instance");
        try {
        	sessionFactory.getCurrentSession().delete(lg);
        }
        catch (HibernateException re) {
        	re.printStackTrace();
            log.error("delete failed", re);
            throw re;
        }
    }
    

    public int changeIdentity(String principal, String pswd, String userId, String managedSysId) {
		Session session = sessionFactory.getCurrentSession();
		String hq = " UPDATE Login l " +
		 			 " set l.id.login = :principal,  " + 
		 			 "     l.password = :pswd," +
		 			 "	   l.passwordChangeCount = 0," +
		 			 " 	   l.isLocked = 0, " +
		 			 "	   l.authFailCount = 0	 " + 
		 			 " where l.id.managedSysId = :managedSysId and " + 
		 			 "		 l.userId = :userId";
		Query qry = session.createQuery(hq);
		qry.setString("userId", userId);
		qry.setString("principal", principal);
		qry.setString("pswd", pswd);
		qry.setString("managedSysId", managedSysId);
		return qry.executeUpdate();
       }

	/*public void updateIdentity(Login lg) {
		log.info("updateLogin called.");
		Session session = sessionFactory.getCurrentSession();
		session.evict(lg);
		log.info("- login=" + lg + " userId=" + lg.getUserId());
		
		Login l = findLoginByManagedSys(lg.getId().getDomainId(), lg.getId().getManagedSysId(), lg.getUserId());
		log.info("Found object to delete=" + l.getId());
		
		//Login l = this.findById(lg.getId());
		this.remove(l);
		this.add(lg);
		
		
	}
	*/
    
    /* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#update(org.openiam.idm.srvc.auth.dto.Login)
	 */
    public Login update(Login detachedInstance) {
        log.debug("merging Login instance");
        try {
            Login result = (Login) sessionFactory.getCurrentSession()
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
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#findById(org.openiam.idm.srvc.auth.dto.LoginId)
	 */
    public Login findById( org.openiam.idm.srvc.auth.dto.LoginId id) {
        log.debug("getting Login instance with id: " + id);
        try {
            Login instance = (Login) sessionFactory.getCurrentSession()
                    .get("org.openiam.idm.srvc.auth.dto.Login", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
                return null;
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

    public List<Login> findAllLoginByManagedSys(String managedSysId) {
        Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.id.managedSysId = :managedSysId order by l.id.login asc " );
    	qry.setString("managedSysId", managedSysId);
    	List<Login> results = (List<Login>)qry.list();
    	return results;
    }

    public List<Login> findUser(String userId) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.userId = :userId order by l.status desc, l.id.managedSysId asc " );
    	qry.setString("userId", userId);
    	List<Login> results = (List<Login>)qry.list();
    	return results;
    	
    }
    
    public List<Login> findLoginByDomain(String domain) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.id.domainId = :domain " );
    	qry.setString("domain", domain);
    	List<Login> results = (List<Login>)qry.list();
    	return results;    	
    }

    public Login findLoginByManagedSys(String domain, String managedSys, String userId) {
    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.id.domainId = :domain and " + 
    			"  l.id.managedSysId = :managedSys and " +
    			"  l.userId = :userId ");
    	log.info("domain=" + domain + " managedSys=" + managedSys + " userId=" + userId);
    	qry.setString("domain", domain);
    	qry.setString("managedSys", managedSys);
    	qry.setString("userId", userId);
    	List<Login> results = (List<Login>)qry.list();
    	if (results != null && results.size() > 0) {
    		return results.get(0);
    	}
    	return null;    	
    }

	public List findLoginByDept(String managedSysId, String department, String div) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "select login, user from Login as login JOIN User u ON (login.userId = u.userId ) " +
					" where " +
					" 		login.id.managedSysId = :managedSysId  and " +
					"       user.deptCd = :department ";
		if (div != null && div.length() > 0 ) {
			sql = sql + " and user.division = :division"; 
		}
		sql = sql + " order by user.firstName, user.lastName";
	

		Query qry = session.createQuery(sql);
	
	System.out.println("sql=" + sql);
	
		qry.setString("managedSysId", managedSysId);
		qry.setString("department", department);
		qry.setString("division", div);
	
		List result = (List) qry.list();
		if (result == null || result.size() == 0)
			return null;

		
		return result;
	}
	
	public List<Login> findLockedUsers(Date startTime) {
	   	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery("from org.openiam.idm.srvc.auth.dto.Login l " +
    			" where l.isLocked = 1 and  " + 
    			"  l.lastAuthAttempt >= :startTime " );
    	qry.setTimestamp("startTime", startTime);
    	List<Login> results = (List<Login>)qry.list();
    	return results;    	
 		
	}
	
	String loginQry = " UPDATE org.openiam.idm.srvc.auth.dto.Login l  " +
	 " SET l.isLocked = 0 " +
	 "       where l.id.domainId = :domain and  " + 
	 "             l.isLocked = :status and " + 
	 "             l.lastAuthAttempt <= :policyTime" ;
    

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#bulkUnlock(org.openiam.idm.srvc.user.dto.UserStatusEnum)
	 */
	public int bulkUnlock(String domainId,UserStatusEnum status, int autoUnlockTime) {
    	
		log.info("bulkUnlock operation in LoginDAO called." );
		Session session = sessionFactory.getCurrentSession();
    	
	
    	String userQry = " UPDATE org.openiam.idm.srvc.user.dto.User u  " +
    	 				 " SET u.secondaryStatus = null " +
    	 				 " where u.secondaryStatus = 'LOCKED' and " +
    	 				 "       u.userId in (" +
    	 				 " 	select l.userId from org.openiam.idm.srvc.auth.dto.Login as l  " +
    	    			 "       where l.id.domainId = :domain and  " + 
    	    			 "             l.isLocked = :status and " + 
    	    			 "             l.lastAuthAttempt <= :policyTime" +
    	    			 "   )";

    	String loginQry = " UPDATE org.openiam.idm.srvc.auth.dto.Login l  " +
		 " SET l.isLocked = 0, " +
		 "     l.authFailCount = 0 " +
		 "       where l.id.domainId = :domain and  " + 
		 "             l.isLocked = :status and " + 
		 "             l.lastAuthAttempt <= :policyTime" ;

   	
    	Query qry = session.createQuery(userQry);
    	


    	Date policyTime = new Date(System.currentTimeMillis());
    	
    	log.info("Auto unlock time:" + autoUnlockTime);
    	
    	Calendar c = Calendar.getInstance();
        c.setTime(policyTime);
        c.add(Calendar.MINUTE, (-1 * autoUnlockTime));
        policyTime.setTime( c.getTimeInMillis() );
        
        log.info("Policy time=" + policyTime.toString());
        
    	qry.setString("domain", domainId);
    	
    	log.info("DomainId=" + domainId);
    	
    	int statusParam = 0;
    	if  (status.equals( UserStatusEnum.LOCKED)) {
    		statusParam = 1;
    		 //qry.setInteger("status", 1);
    		 log.info("status=1" );
    	 }else {
    		 statusParam = 2;
    		 //qry.setInteger("status", 2);
    		 log.info("status=2" );
    	 }

    	qry.setInteger("status", statusParam);
    	qry.setTimestamp("policyTime", policyTime);
    	int rowCount =  qry.executeUpdate();
    	
    	log.info("Bulk unlock updated:" + rowCount);
    	
    	if (rowCount > 0) {
    		    		
    		Query lQry = session.createQuery(loginQry);
    		lQry.setString("domain", domainId);
    		lQry.setInteger("status", statusParam);
    		lQry.setTimestamp("policyTime", policyTime);
    		lQry.executeUpdate();
    		    		
    	}
    	
    	return rowCount;
    	

	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#findInactiveUsers(int, int)
	 */
	public List<Login> findInactiveUsers(int startDays, int endDays) {
		log.info("findInactiveUsers called.");
		log.info("Start days=" + startDays);
		log.info("End days=" + endDays);
		
		boolean start = false;
    	Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(System.currentTimeMillis());
		
		StringBuffer sql = new StringBuffer(" from org.openiam.idm.srvc.auth.dto.Login l where ");
    	
    	
		if (startDays != 0 ) {
			sql.append(" l.lastLogin < :startDate " );
			start = true;
			
	    	Calendar c = Calendar.getInstance();
	        c.setTime(startDate);
	        c.add(Calendar.DAY_OF_YEAR, (-1 * startDays));
	        startDate.setTime( c.getTimeInMillis() );
	        log.info("starDate = " + startDate.toString());
			
		}
		if ( endDays != 0) {
			if (start) {
				sql.append( " and " );
			}
			sql.append(" l.lastLogin > :endDate ");
	    	
			Calendar c = Calendar.getInstance();
	        c.setTime(endDate);
	        c.add(Calendar.DAY_OF_YEAR, (-1 * endDays));
	        endDate.setTime( c.getTimeInMillis() );
	        log.info("endDate = " + endDate.toString());
	        
		}


    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery(sql.toString());
    	if (startDays != 0 ) {
    		qry.setDate("startDate", startDate);
    	}
    	if (endDays != 0 ) {
    		qry.setDate("endDate", endDate);
    	}

    	List<Login> results = (List<Login>)qry.list();
    	if (results == null) {
    		return (new ArrayList<Login>());
    	}
    	return results;
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#findUserNearPswdExp(int)
	 */
	public List<Login> findUserNearPswdExp(int daysToExpiration) {
		log.info("findUserNearPswdExp called.");
		log.info("days to Expiration=" + daysToExpiration);
		
    	Date expDate = new Date(System.currentTimeMillis());
		if (daysToExpiration != 0 ) {

	    	Calendar c = Calendar.getInstance();
	        c.setTime(expDate);
	        c.add(Calendar.DAY_OF_YEAR, (daysToExpiration));
	        expDate.setTime( c.getTimeInMillis() );
	        log.info("expDate = " + expDate.toString());
			
		}
				
		String sql = new String(" from org.openiam.idm.srvc.auth.dto.Login l where " +
				" l.pwdExp <= :expDate" );
    	

    	Session session = sessionFactory.getCurrentSession();
    	Query qry = session.createQuery(sql.toString());
    	qry.setDate("expDate", expDate);

    	List<Login> results = (List<Login>)qry.list();
    	if (results == null) {
    		return (new ArrayList<Login>());
    	}
    	return results;

	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#bulkResetPasswordChangeCount()
	 */
	public int bulkResetPasswordChangeCount() {
		log.info("bulkResetPasswordChangeCount operation in LoginDAO called." );
		Session session = sessionFactory.getCurrentSession();
    	

    	String loginQry = " UPDATE org.openiam.idm.srvc.auth.dto.Login l  " +
		 " SET l.passwordChangeCount = 0 "  ;
   	
    	Query qry = session.createQuery(loginQry);
    	return qry.executeUpdate();
    	
	}
    
    
}

