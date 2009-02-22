package org.openiam.idm.srvc.menu.service;
// Generated Nov 30, 2007 3:01:47 AM by Hibernate Tools 3.2.0.b11


import java.util.List;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import org.springframework.dao.*;

import org.openiam.idm.srvc.menu.dto.*;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.exception.data.*;

/**
 * RDMBS implementation the DAO for Navigator
 * @see org.openiam.idm.srvc.menu.dto.Menu
 * @author Suneet Shah 
 */
public class NavigatorDAOImpl implements NavigatorDAO {

    private static final Log log = LogFactory.getLog(NavigatorDAOImpl.class);

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
   public Menu add(Menu instance) throws DataException {
		try {
			sessionFactory.getCurrentSession().persist(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw new DataException( re.getMessage(), re.getCause() );
		} 
   }




	public void remove(Menu instance) throws DataException {


		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
	   }catch(DataAccessException dae) {		   
		   log.error("Remove operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }

	}

	public void update(Menu instance) throws DataException {
	   try {
		   sessionFactory.getCurrentSession().saveOrUpdate(instance);
	   }catch(DataAccessException dae) {		   
		   log.error("Update operation failed.", dae);
		   throw new DataException( dae.getMessage(), dae.getCause() );
	   }		
	}

    public Menu findById(MenuId id) throws DataException {
        log.debug("getting Navigator instance with id: " + id);
       
		try {
			Menu instance = (Menu) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.service.dto.Menu", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			   log.error("Update operation failed.", re);
			   throw new DataException( re.getMessage(), re.getCause() );
		}
        
	
    }

    
    public List findByCriteria(Menu instance) throws DataException {
        log.debug("finding IdmAuditLog instance by example");
    
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"org.openiam.idm.srvc.menu.service.dto.Menu").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw new DataException( re.getMessage(), re.getCause() );      
		}

    }

    public List<Menu> findTopLevelMenus(String langCd) throws DataException {
		Session session = sessionFactory.getCurrentSession();
		try {
		Query qry = session.createQuery("from org.openiam.idm.srvc.menu.dto.Menu m " +
				" where m.menuGroup is null and " +
				" 		m.id.languageCd = :langCd ");
		qry.setString("langCd", langCd);
		List<Menu> results = (List<Menu>)qry.list();
		return results;    	
		}catch(RuntimeException re) {
			log.error("findTopLevelMenus", re);
			throw new DataException( re.getMessage(), re.getCause() );      		
		}
    }
    
    public List<Menu> findByMenuGroup(String menuGroup,String langCd) throws DataException {
		Session session = sessionFactory.getCurrentSession();
		try {
		Query qry = session.createQuery("from org.openiam.idm.srvc.menu.dto.Menu m " +
				" where m.menuGroup = :menuGroup and " +
				" 		m.id.languageCd = :langCd ");
		qry.setString("menuGroup", menuGroup);
		qry.setString("langCd", langCd);
		List<Menu> results = (List<Menu>)qry.list();
		return results;    	
		}catch(RuntimeException re) {
			log.error("findTopLevelMenus", re);
			throw new DataException( re.getMessage(), re.getCause() );      		
		}
    }
    
    
    public List<Menu> findSubMenus(String parentMenuId, String langCd) throws DataException {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.menu.dto.Menu m " +
				" where m.menuGroup = :parentMenuId and " +
				" 		m.id.languageCd = :langCd ");
		qry.setString("parentMenuId", parentMenuId);
		qry.setString("langCd", langCd);
		List<Menu> results = (List<Menu>)qry.list();
		return results;
    }
    
    public List<String> findSubMenuIDs(String parentMenuId, String langCd) throws DataException {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery(
				"select new String(m.id.menuId) " + 
				"from org.openiam.idm.srvc.menu.dto.Menu m " +
				" where m.menuGroup = :parentMenuId and " +
				" 		m.id.languageCd = :langCd ");
		qry.setString("parentMenuId", parentMenuId);
		qry.setString("langCd", langCd);
		List<String> results = (List<String>)qry.list();
		return results;
    }
    
}



