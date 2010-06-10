package org.openiam.idm.srvc.menu.service;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
 import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.menu.dto.Menu;
import org.openiam.idm.srvc.menu.dto.Permission;
import org.openiam.idm.srvc.menu.dto.PermissionId;
import org.openiam.idm.srvc.role.dto.Role;

public class PermissionDAOImpl implements PermissionDAO {

	private static final Log log = LogFactory.getLog(PermissionDAOImpl.class);
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


	public Permission findById(PermissionId id) {
		log.debug("getting Permission instance with id: " + id);
		try {
			Permission instance = (Permission) sessionFactory.getCurrentSession().get(
					"org.openiam.idm.srvc.menu.dto.Permission", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Permission> findByExample(Permission instance) {
		log.debug("finding Permission instance by example");
		try {
			List<Permission> results = (List<Permission>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.menu.service.Permission").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	//==================================================================
	
	
	public Permission add(Permission instance) {
		log.debug("persisting instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}		
	}

	public void remove(Permission instance) {
		log.debug("deleting instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}			
	}

	public Permission update(Permission instance) {
		log.debug("merging instance. id = " + instance.getId());
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
			return instance;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}		
	}

	public List<Permission> findAllPermissions() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.menu.dto.Permission " +
				"order by id.menuId asc");
		qry.setCacheable(true);
		qry.setCacheRegion("query.permission.findAllPermissions");
		List<Permission> result = (List<Permission>)qry.list();
		
		return result;
	}
	

	public int removeAllPermissions() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from org.openiam.idm.srvc.menu.dto.Permission");
		return qry.executeUpdate();
	}
	

	
	
	public List<Role> findRolesByMenu(String menuId) {
		Session session = sessionFactory.getCurrentSession();

		SQLQuery qry = session.createSQLQuery( 
		"select distinct r.* from permissions p, role r " +
		"where p.ROLE_ID = r.ROLE_ID " +
		"and p.SERVICE_ID = r.SERVICE_ID " +
		"and p.MENU_ID = :menuId ");		

		qry.addEntity(Role.class);
		qry.setString("menuId", menuId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.permission.findRolesByMenu");
		List<Role> result = (List<Role>)qry.list();
		
		for (Role instance:result) {
				Hibernate.initialize(instance.getId());
				Hibernate.initialize(instance.getGroups());
				Hibernate.initialize(instance.getRoleAttributes());
				Hibernate.initialize(instance.getChildRoles());
		}

		return result;
	}
	
	public List<Menu> findMenusByRole(String roleId, String serviceId) {
		Session session = sessionFactory.getCurrentSession();

		SQLQuery qry = session.createSQLQuery( 
		"select distinct m.* from menu m, permissions p " +
		"where m.MENU_ID = p.MENU_ID " +
		"and p.ROLE_ID = :roleId " +
		"and p.SERVICE_ID = :serviceId ");
		
		qry.addEntity(Menu.class);
		qry.setString("roleId", roleId);
		qry.setString("serviceId", serviceId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.permission.findMenusByRole");
		List<Menu> result = (List<Menu>)qry.list();
		
		for (Menu instance:result) {
				Hibernate.initialize(instance.getId());
				Hibernate.initialize(instance.getSubMenus());
		}

		return result;		
	}
	

	public List<Menu> findMenusByUser(String menuGroup, String roleId, String userId) {
		Session session = sessionFactory.getCurrentSession();

		SQLQuery qry = session.createSQLQuery( 
				"select distinct m.* " +
				"from menu m join permissions p on (m.MENU_ID = p.MENU_ID) " +
				"left join grp_role gr on " +
				"(gr.ROLE_ID = p.ROLE_ID and gr.SERVICE_ID = p.SERVICE_ID) " +
				"left join user_grp ug on (gr.GRP_ID = ug.GRP_ID) " +
				"left join user_role ur on " +
				"(p.ROLE_ID = ur.ROLE_ID and p.SERVICE_ID = ur.SERVICE_ID) " +
				"where m.MENU_GROUP = :menuGroup " +
				"and (ug.USER_ID = :userId or ur.USER_ID = :userId) " +
				"and p.ROLE_ID = :roleId ");
				
		qry.addEntity(Menu.class);
		qry.setString("menuGroup", menuGroup);
		qry.setString("userId", userId);
		qry.setString("roleId", roleId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.permission.findMenusByUser");
		List<Menu> result = (List<Menu>)qry.list();
		
		for (Menu instance:result) {
				Hibernate.initialize(instance.getId());
				Hibernate.initialize(instance.getSubMenus());
		}

		return result;		
	}
	
	
	
}
