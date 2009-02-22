package org.openiam.idm.srvc.role.service;

// Generated Mar 4, 2008 1:12:08 AM by Hibernate Tools 3.2.0.b11

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDAO;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.service.GroupDAO;

/**
 * Data access interface for domain model class Role.
 * 
 * @see org.openiam.idm.srvc.role.dto.Role
 */
public class RoleDAOImpl implements RoleDAO {

	protected UserDAO userDao;
	protected GroupDAO groupDao;

	private static final Log log = LogFactory.getLog(RoleDAOImpl.class);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.role.service.RoleDAO#add(org.openiam.idm.srvc.role.dto.Role)
	 */
	public void add(Role transientInstance) {
		log.debug("persisting Role instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.role.service.RoleDAO#remove(org.openiam.idm.srvc.role.dto.Role)
	 */
	public void remove(Role persistentInstance) {
		log.debug("deleting Role instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.role.service.RoleDAO#update(org.openiam.idm.srvc.role.dto.Role)
	 */
	public void update(Role detachedInstance) {
		log.debug("merging Role instance");
		try {
			sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.role.service.RoleDAO#findById(java.lang.String)
	 */
	public Role findById(RoleId id) {
		log.debug("getting Role instance with id: " + id);
		try {
			Role instance = (Role) sessionFactory.getCurrentSession().get(
					"org.openiam.idm.srvc.role.dto.Role", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openiam.idm.srvc.role.service.RoleDAO#findByExample(org.openiam.idm.srvc.role.dto.Role)
	 */
	public List<Role> findByExample(Role instance) {
		log.debug("finding Role instance by example");
		try {
			List<Role> results = (List<Role>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.role.dto.Role").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Role> findUserRoles(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select role from Role as role "
				+ " inner join role.users as user "
				+ " where user.userId = :userId ");
		
		qry.setString("userId", userId);
		List<Role> result = (List<Role>) qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;
	}
	
	public List<Role> findUserRolesByService(String serviceId, String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select role from Role as role "
				+ " inner join role.users as user "
				+ " where user.userId = :userId and role.id.serviceId = :serviceId");
		
		qry.setString("userId", userId);
		qry.setString("serviceId", serviceId);
		List<Role> result = (List<Role>) qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}
	
	
	public List<Role>findIndirectUserRoles(String userId) {
		Session session = sessionFactory.getCurrentSession();

		SQLQuery qry = session.createSQLQuery("SELECT role.ROLE_ID, role.SERVICE_ID, role.ROLE_NAME, "
							+ " 	role.CREATE_DATE, role.CREATED_BY, role.DESCRIPTION," 
							+ " 	role.PROVISION_OBJ_NAME, role.PARENT_ROLE_ID, role.TYPE_ID " 
							+ "  FROM ROLE role  "
							+ "  	JOIN GRP_ROLE grp_role  "
							+ "		JOIN USER_GRP user_grp  "
							+ "     ON (role.ROLE_ID = grp_role.ROLE_ID and " 
							+ " 		grp_role.GRP_ID =  user_grp.GRP_ID) " 
							+ "	WHERE user_grp.USER_ID = :userId");
		
		qry.addEntity(Role.class);
		qry.setString("userId", userId);
		List<Role> result = (List<Role>) qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}

	public List<Role>findIndirectUserRolesByService(String serviceId, String userId) {
		Session session = sessionFactory.getCurrentSession();

		SQLQuery qry = session.createSQLQuery("SELECT role.ROLE_ID, role.SERVICE_ID, role.ROLE_NAME, "
							+ " 	role.CREATE_DATE, role.CREATED_BY, role.DESCRIPTION," 
							+ " 	role.PROVISION_OBJ_NAME, role.PARENT_ROLE_ID, role.TYPE_ID " 
							+ "  FROM ROLE role  "
							+ "  	JOIN GRP_ROLE grp_role  "
							+ "		JOIN USER_GRP user_grp  "
							+ "     ON (role.ROLE_ID = grp_role.ROLE_ID and " 
							+ " 		grp_role.GRP_ID =  user_grp.GRP_ID) " 
							+ "	WHERE user_grp.USER_ID = :userId and role.serviceId = :serviceId");
		
		qry.addEntity(Role.class);
		qry.setString("userId", userId);
		qry.setString("serviceId", serviceId);
		List<Role> result = (List<Role>) qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}
	
	/**
	 * Get the users that are in a role
	 * 
	 * @param roleId
	 * @return
	 */
	public List<User> findUsersInRole(String serviceId, String roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session
				.createQuery("select user from org.openiam.idm.srvc.role.dto.Role role "
						+ " inner join role.users as user "
						+ " where role.id.serviceId = :serviceId and " 
						+ " role.id.roleId = :roleId ");
		qry.setString("serviceId", serviceId);
		qry.setString("roleId", roleId);
		List<User> results = (List<User>) qry.list();
		if (results == null || results.size() == 0)
			return null;
		return results;
	}
	


	/**
	 * Returns a list of all Roles regardless of service The list is sorted by
	 * ServiceId, Role
	 * 
	 * @return
	 */
	public List<Role> findAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from Role r "
				+ " order by r.id.serviceId asc, r.id.roleId asc ");
		List<Role> result = (List<Role>) qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;

	}

	public void addGroupToRole(String serviceId, String roleId, String groupId) {


		
		
		Group grp = groupDao.findById(groupId);

	
		Role role = findById(new RoleId(serviceId, roleId));
		
		
		role.getGroups().add(grp);

		try {
			sessionFactory.getCurrentSession().save(role);
			log.debug("persist user to group successful");
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}
	}

	public void addUserToRole(String serviceId, String roleId, String userId) {
		User user = userDao.findById(userId);
		
		log.debug("In addUserToRole: UserId = " + userId);
		log.debug("-User = " + user);

		Role role = findById(new RoleId(serviceId, roleId));
		
		log.debug("-role = " + role);
		log.debug("-role users = " + role.getUsers());

	
		role.getUsers().add(user);

		try {
			sessionFactory.getCurrentSession().save(role);
			log.debug("persist user to group successful");
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public GroupDAO getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	public void removeGroupFromRole(String serviceId, String roleId,
			String groupId) {

		Role rl = findById(new RoleId(serviceId, roleId));
		if (rl == null) {
			log.error("Role not found for roleId =" + roleId);
			throw new ObjectNotFoundException();
		}
		org.hibernate.Hibernate.initialize(rl.getGroups());
		Set<Group> grpSet = rl.getGroups();
		if (grpSet == null || grpSet.isEmpty()) {
			return;
		}
		Iterator<Group> it = grpSet.iterator();
		while (it.hasNext()) {
			Group grp = it.next();
			if (grp.getGrpId().equalsIgnoreCase(grp.getGrpId())) {
				it.remove();
			}
		}

	}

	public void removeAllGroupsFromRole(String serviceId, String roleId) {

		Role rl = findById(new RoleId(serviceId, roleId));
		if (rl == null) {
			log.error("Role not found for roleId =" + roleId);
			throw new ObjectNotFoundException();
		}
		org.hibernate.Hibernate.initialize(rl.getGroups());
		Set<Group> grpSet = rl.getGroups();
		if (grpSet == null || grpSet.isEmpty()) {
			return;
		}
		Iterator<Group> it = grpSet.iterator();
		while (it.hasNext()) {
			Group grp = it.next();
			it.remove();

		}

	}

	public void removeUserFromRole(String serviceId, String roleId, String userId) {
		Role rl = findById(new RoleId(serviceId, roleId));
		if (rl == null) {
			log.error("Role not found for roleId =" + roleId);
			throw new ObjectNotFoundException();
		}
		org.hibernate.Hibernate.initialize(rl.getUsers());
		Set<User> userSet = rl.getUsers();
		if (userSet == null || userSet.isEmpty()) {
			return;
		}
		Iterator<User> it = userSet.iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (user.getUserId().equalsIgnoreCase(user.getUserId())) {
				it.remove();
			}
		}
	}

	public List<Role> findRolesInService(String serviceId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session
				.createQuery(" from org.openiam.idm.srvc.role.dto.Role r "
						+ " where r.id.serviceId = :serviceId "
						+ " order by r.id.roleId asc");
		qry.setString("serviceId", serviceId);
		List<Role> results = (List<Role>) qry.list();
		return results;
	}

	public List<Role> findRolesInGroup(String groupId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session
				.createQuery(" select role from org.openiam.idm.srvc.role.dto.Role role "
						+ "		 join role.groups as group "
						+ " where group.grpId = :groupId "
						+ " order by role.id.roleId asc");
		qry.setString("groupId", groupId);
		List<Role> results = (List<Role>) qry.list();
		if (results == null || results.size() == 0)
			return null;
		return results;
	}
	
    /**
     * Returns a role object if a direct relationship between a user and role exists.
     * @return
     */
    public Role findDirectRoleForUser(String serviceId, String roleId, String userId) {
    	Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select role from Role as role "
				+ " inner join role.users as user "
				+ " where user.userId = :userId and  " 
				+ " 	  role.id.serviceId = :serviceId and " 
				+ " 	  role.id.roleId = :roleId ");
		qry.setString("userId", userId);
		qry.setString("serviceId", serviceId);
		qry.setString("roleId", roleId);
		return (Role) qry.uniqueResult();
	
    }


}
