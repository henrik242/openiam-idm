package org.openiam.idm.srvc.menu.service;
// Generated Nov 30, 2007 3:01:47 AM by Hibernate Tools 3.2.0.b11


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.openiam.exception.data.DataException;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.dto.MenuId;
import org.openiam.idm.srvc.res.dto.Resource;
import org.springframework.dao.DataAccessException;

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
							"org.openiam.idm.srvc.menu.dto.Menu", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			   log.error("Find operation failed.", re);
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
		} catch (HibernateException re) {
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
		}catch(HibernateException re) {
			log.error("findTopLevelMenus", re);
			throw new DataException( re.getMessage(), re.getCause() );      		
		}
    }
    
    public List<Menu> findByMenuGroup(String menuGroup,String langCd) throws DataException {
		Session session = sessionFactory.getCurrentSession();
		try {
		Query qry = session.createQuery("from org.openiam.idm.srvc.menu.dto.Menu m " +
				" where m.menuGroup = :menuGroup and " +
				" 		m.id.languageCd = :langCd " + 
				" order by m.displayOrder "	);
		qry.setString("menuGroup", menuGroup);
		qry.setString("langCd", langCd);
		
		qry.setCacheable(true);
		qry.setCacheRegion("query.menu.findbyMenuGroup");
		
		List<Menu> results = (List<Menu>)qry.list();
		return results;    	
		}catch(HibernateException re) {
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
    
    public int removeByMenuId(String menuId) throws DataException {
    	try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("delete org.openiam.idm.srvc.menu.dto.Menu m  " + 
						" where m.id.menuId = :menuId ");
			qry.setString("menuId", menuId);
			return qry.executeUpdate();	
	    }catch(HibernateException he) {
    		log.error("removeByMenuId", he);
			throw new DataException( he.getMessage(), he.getCause() );  
    	}
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
    
   /*
    * @deprecated - replaced by findMenuGroupsByUser(menuGroupId, serviceId, userId) in 
    * PermissionDAO. Deprecated method does not take into account serviceId 
    */
    
    public List<Menu> findMenuByUser(String menuGroupId, String userId, String languageCd) {    
    	Session session = sessionFactory.getCurrentSession();
    
    	try{
    		SQLQuery qry = session.createSQLQuery("SELECT DISTINCT m.MENU_ID, m.LANGUAGE_CD, m.MENU_GROUP, m.MENU_NAME, " +
    				" m.MENU_DESC, m.URL, m.ACTIVE, m.DISPLAY_ORDER, m.PUBLIC_URL  " +
				 "  FROM 	MENU m JOIN PERMISSIONS p ON (m.MENU_ID = p.MENU_ID)  " +
				 "  		LEFT JOIN GRP_ROLE gr on (gr.ROLE_ID = p.ROLE_ID) " +
						"   LEFT JOIN USER_GRP ug ON (gr.GRP_ID = ug.GRP_ID  ) " +
				 " 		LEFT JOIN USER_ROLE ur ON (p.ROLE_ID = ur.ROLE_ID)  " 
				+ "	 WHERE MENU_GROUP = :menuGroupId AND m.LANGUAGE_CD = :languageCd  AND (ug.USER_ID = :userId or ur.USER_ID= :userId) "
				+ " order by m.DISPLAY_ORDER " );
	    	
	    	
	    	qry.addEntity(Menu.class);
			qry.setString("userId", userId);
			qry.setString("menuGroupId", menuGroupId);
			qry.setString("languageCd", languageCd);
			
			List<Menu> result = (List<Menu>) qry.list();
			if (result == null || result.size() == 0)
				return null;
			return result;		
	    }catch(HibernateException re) {
			log.error("findMenuByUser", re);
			throw new DataException( re.getMessage(), re.getCause() );      		
		}
    }
    
    
	public List<Menu> getMenuFamily(String menuId, String languageCd) {

		List<Menu> menuTree = new ArrayList<Menu>();
		menuTree.add(findById(new MenuId(menuId,languageCd)));

		List<Menu> menuChildren = findSubMenus(menuId,languageCd);
		menuTree.addAll(menuChildren);

		for (Iterator<Menu> it = menuChildren.iterator(); it.hasNext();) {
			Menu r = (Menu) it.next();
			List<Menu> descendents = new ArrayList<Menu>();
			menuTree.addAll(getMenuFamilyHelper(r.getId(),
					descendents));
		}
		return menuTree;
	}

	private List<Menu> getMenuFamilyHelper(MenuId id,
			List<Menu> descendents) {

		List<Menu> children = findSubMenus(id.getMenuId(), id.getLanguageCd());
		descendents.addAll(children);
		for (Iterator<Menu> it = children.iterator(); it.hasNext();) {
			Menu r = (Menu) it.next();
			getMenuFamilyHelper(r.getId(), descendents);
		}
		return descendents;
	}
    

	public List<Menu> getMenuTree(String menuId, String languageCd) {

		List<Menu> menuTree = new ArrayList<Menu>();
		menuTree.add(findById(new MenuId(menuId, languageCd)));

		List<Menu> menuChildren = findSubMenus(menuId, languageCd);
		menuTree.addAll(menuChildren);

		for (Iterator<Menu> it = menuChildren.iterator(); it.hasNext();) {
			Menu r = (Menu) it.next();
			List<Menu> descendents = getMenuTreeHelper(r.getId());
			if (!((descendents == null) || (descendents.isEmpty()))) {
				r.setSubMenus((Menu[]) descendents.toArray());
			}
		}
		return menuTree;
	}

	/**
	 * Recursive helper method to get nested menu descendants.
	 * 
	 * @param menuId
	 *            the menu id
	 * 
	 * @return the menu tree helper
	 */
	private List<Menu> getMenuTreeHelper(MenuId id) {

		List<Menu> descendents = findSubMenus(id.getMenuId(), id.getLanguageCd());

		for (Iterator<Menu> it = descendents.iterator(); it.hasNext();) {
			Menu r = (Menu) it.next();
			List<Menu> nextChildren = findSubMenus(r.getId().getMenuId(),r.getId().getLanguageCd());
			if (!((nextChildren == null) || (nextChildren.isEmpty()))) {
				r.setSubMenus((Menu[]) nextChildren.toArray());
				getMenuTreeHelper(r.getId());
			}
		}
		return descendents;
	}
	
	
}


