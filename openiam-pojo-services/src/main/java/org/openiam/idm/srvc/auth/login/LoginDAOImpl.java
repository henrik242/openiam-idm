package org.openiam.idm.srvc.auth.login;
// Generated Feb 18, 2008 3:56:08 PM by Hibernate Tools 3.2.0.b11


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
        	Session session = sessionFactory.getCurrentSession();
        	session.evict(lg);
            session.delete(lg);
            log.debug("delete successful");
        }
        catch (HibernateException re) {
        	re.printStackTrace();
            log.error("delete failed", re);
            throw re;
        }
    }
    


	public void updateIdentity(Login lg) {
		log.info("updateLogin called.");
		Session session = sessionFactory.getCurrentSession();
		session.evict(lg);
		log.info("- login=" + lg + " userId=" + lg.getUserId());
		
		Login l = findLoginByManagedSys(lg.getId().getDomainId(), lg.getId().getManagedSysId(), lg.getUserId());
		log.info("Found object to delete=" + l.getId());
		
		//Login l = this.findById(lg.getId());
		this.remove(l);
		this.add(lg);
		
/*		Query qry = session.createQuery("update org.openiam.idm.srvc.auth.dto.Login lg " +
				    " set lg.id.login = :login " +  
					" where lg.id.domainId= :domainId and  " + 
				    " lg.id.managedSysId = :managedSysId and lg.userId = :userId ");
		qry.setString("domainId", lg.getId().getDomainId());
		qry.setString("login",lg.getId().getLogin());
		qry.setString("managedSysId", lg.getId().getManagedSysId());
		qry.setString("userId", lg.getUserId());
		qry.executeUpdate();
*/
		
	}
    
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
    

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.auth.login.LoginDAO#bulkUnlock(org.openiam.idm.srvc.user.dto.UserStatusEnum)
	 */
	public int bulkUnlock(String domainId,UserStatusEnum status, int autoUnlockTime) {
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
		 " SET l.isLocked = 0 " +
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
    
    
}

