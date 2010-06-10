package org.openiam.idm.srvc.res.service;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;


import org.openiam.exception.data.ObjectNotFoundException;
import org.openiam.idm.srvc.res.dto.*;


/**
 * DAO Implementation for Resources.
 * 
 */
public class ResourceDAOImpl implements ResourceDAO  {

	private static final Log log = LogFactory.getLog(ResourceDAOImpl.class);
	private ResourceTypeDAO resourceTypeDao;
	private ResourcePropDAO resourcePropDao;
	private ResourceRoleDAO resourceRoleDao;
//	private ResourceUserDAO resourceUserDao;

	private SessionFactory sessionFactory;
	
	// DAO operations ================================================

	public ResourceTypeDAO getResourceTypeDao() {
		return resourceTypeDao;
	}

	public void setResourceTypeDao(ResourceTypeDAO resourceTypeDao) {
		this.resourceTypeDao = resourceTypeDao;
	}

	public ResourcePropDAO getResourcePropDao() {
		return resourcePropDao;
	}

	public void setResourcePropDao(ResourcePropDAO resourcePropDao) {
		this.resourcePropDao = resourcePropDao;
	}

	public ResourceRoleDAO getResourceRoleDao() {
		return resourceRoleDao;
	}

	public void setResourceRoleDao(ResourceRoleDAO resourceRoleDao) {
		this.resourceRoleDao = resourceRoleDao;
	}
	
//	public ResourceUserDAO getResourceUserDao() {
//		return resourceUserDao;
//	}
//
//	public void setResourceUserDao(ResourceUserDAO resourceUserDao) {
//		this.resourceUserDao = resourceUserDao;
//	}
//	
	
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

	// Basic operations =====================================

	public void persist(Resource transientInstance) {
		log.debug("persisting Resource instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(Resource persistentInstance) {
		log.debug("deleting Resource instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Resource update(Resource detachedInstance) {
		log.debug("merging Resource instance");
		try {
			Resource result = (Resource) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
							
				//Hibernate.initialize(result.getResourceType());
				//Hibernate.initialize(result.getResourceProps());
				//Hibernate.initialize(result.getResourceRoles());
				
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Resource findById(java.lang.String id) {
		log.debug("getting Resource instance with id: " + id);
		try {
			Resource instance = (Resource) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.res.dto.Resource", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}

			
			if (instance != null){
				Hibernate.initialize(instance.getResourceType());
				Hibernate.initialize(instance.getResourceProps());
				Hibernate.initialize(instance.getResourceRoles());
			}	
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Resource> findByExample(Resource instance) {
		log.debug("finding Resource instance by example");
		try {
			List<Resource> resources = (List<Resource>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.res.dto.Resource").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ resources.size());
			
			for (Resource r:resources ) {
				
				Hibernate.initialize(r.getResourceType());
				Hibernate.initialize(r.getResourceProps());
				Hibernate.initialize(r.getResourceRoles());
			}
			
			return resources;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	// Resource operations ==================================================================
	
	
	public Resource add(Resource instance) {
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

	public List<Resource> findAllResources() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.res.dto.Resource a " +
				" order by a.resourceId asc");
		List<Resource> result = (List<Resource>)qry.list();
		
		for (Resource obj:result) {
			Hibernate.initialize(obj.getResourceProps());
			Hibernate.initialize(obj.getResourceType());
			Hibernate.initialize(obj.getResourceRoles());
		}
		
		return result;
	}
	
	public int removeAllResources() {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete from org.openiam.idm.srvc.res.dto.Resource");
		return qry.executeUpdate();
	}
	

	// Resource Type ---------------------------------------------------------------
	

	public ResourceType findTypeOfResource(String resourceId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resourceType from Resource as resource " +
				"inner join resource.resourceType as resourceType " +
				"where resource.resourceId = :resourceId ");
				
		qry.setString("resourceId", resourceId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.findTypeOfResource");
		ResourceType t = (ResourceType) qry.uniqueResult();
				
		return t;
	}

/*		
	// use update resource to do this
	
	public void linkTypeToResource(String resourceId, String resourceTypeId) {
		
		ResourceType rt = resourceTypeDao.findById(resourceTypeId);
		if (rt == null) {
			log.error("Resource not found for resourceId =" + resourceTypeId);
			throw new ObjectNotFoundException();
		}
		
		Resource res = findById(resourceId);				
		if (res == null) {
			log.error("Resource not found for resourceId =" + resourceId);
			throw new ObjectNotFoundException();
		}

		
		res.setResourceType(rt);

		try {
			sessionFactory.getCurrentSession().merge(res);
			log.debug("persist successful");
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}
	}


	public void unlinkTypeFromResource(String resourceId) {
		Resource res = findById(resourceId);				
		if (res == null) {
			log.error("Resource not found for resourceId =" + resourceId);
			throw new ObjectNotFoundException();
		}
		res.setResourceType(null);
		
		try {
			sessionFactory.getCurrentSession().merge(res);
			log.debug("persist successful");
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}
	}
	
*/	
	
	// ResourceProp-----------------------------------------------------------------

	
public int removePropertiesByResource(String resourceId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.res.dto.ResourceProp as resourceProp " +
				"where resourceProp.resourceId = :resourceId " );
				
		qry.setString("resourceId", resourceId);
		return qry.executeUpdate();
	}
	

	public List<ResourceProp> findResourceProperties(String resourceId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resourceProp from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"inner join resource.resourceProps as resourceProp " +
				"where resource.resourceId = :resourceId ");
				
		qry.setString("resourceId", resourceId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.findResourceProp");
		List<ResourceProp> result = (List<ResourceProp>) qry.list();

		return result;
	}
	

// use resource update to do this	
	
//	public void linkPropertyToResource(String resourcePropId, String resourceId) {
//		
//		ResourceProp p = resourcePropDao.findById(resourcePropId);		
//		Resource resource = findById(resourceId);	
//		if (resource == null) {
//			log.error("Resource not found for resourceId =" + resourceId);
//			throw new ObjectNotFoundException();
//		}
//		
//		//resource.getResourceProps().add(p);  makes no difference
//		p.setResource(resource);
//
//		try {
//			sessionFactory.getCurrentSession().merge(resource);
//			log.debug("persist successful");
//		} catch (HibernateException re) {
//			re.printStackTrace();
//			log.error("persist failed", re);
//			throw re;
//		}
//	}
//
//
//	public void unlinkPropertyFromResource(String resourcePropId, String resourceId) {
//
//		Resource resource = findById(resourceId);				
//
//		if (resource == null) {
//			log.error("Resource not found for resourceId =" + resourceId);
//			throw new ObjectNotFoundException();
//		}
//		
//		Set<ResourceProp> all = resource.getResourceProps();
//		if (all == null || all.isEmpty()) {
//			return;
//		}
//		
//		for (Iterator<ResourceProp> it = all.iterator(); it.hasNext(); ) {
//		    ResourceProp p = it.next(); 
//			if (p.getResourcePropId().equalsIgnoreCase(resourcePropId)) {
//				//it.remove(); makes no difference
//				p.setResource(null);
//			}
//		}
//		
//	}
//	

	
// Resource Parent ==============================================================

// use resource update to set parentId	
	
//	public void linkResourceToParent(String childResourceId, String parentResourceId) {
//		Resource child = this.findById(childResourceId);
//		if (child == null) {
//			log.error("Resource not found for resourceId =" + childResourceId);
//			throw new ObjectNotFoundException();
//		}
//
//		//if (child.getResourceParent() != null) {
//		//	child.getResourceParent().getChildResources().remove(child); // ?? makes no difference
//		//}
//
//
//		child.setResourceParent(parentResourceId);
//		
//		//parent.getChildResources().add(child);		//??
//		
//		try {
//			sessionFactory.getCurrentSession().merge(child);
//			log.debug("persist successful");
//		} catch (HibernateException re) {
//			re.printStackTrace();
//			log.error("persist failed", re);
//			throw re;
//		}
//	}
//	
//	public void unlinkResourceFromParent(String childResourceId) {
//		Resource child = this.findById(childResourceId);
//		if (child == null) {
//			log.error("Resource not found for resourceId =" + childResourceId);
//			throw new ObjectNotFoundException("Resource " + childResourceId + " does not exist");
//		}
//		
//		//if (child.getResourceParent() != null) {
//		//	child.getResourceParent().getChildResources().remove(child); // ?? does not make a difference
//		//}
//		
//		child.setResourceParent(null);
//		try {
//			sessionFactory.getCurrentSession().merge(child);
//			log.debug("persist successful");
//		} catch (HibernateException re) {
//			re.printStackTrace();
//			log.error("persist failed", re);
//			throw re;
//		}
//	}
//	

	// Resource additional operations ====================================================

public List<Resource> getResourcesByType(String resourceTypeId) {
		Session session = sessionFactory.getCurrentSession();

		//org.hibernate.SQLQuery qry = session.createSQLQuery(
		//		"select r.resource_id, r.resource_type_id, r.description, r.name, r.resource_parent, r.branch_id, " + 
		//		"r.category_id, r.display_order, r.node_level, r.sensitive_app " +
		//		"FROM resource r where r.resource_type_id = :resourceTypeId " +
		//		"order by r.name " );
				
		//qry.addEntity(Resource.class);

		Query qry = session.createQuery( 
				"select resource from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.resourceType.resourceTypeId = :resourceTypeId " +
				"order by resource.displayOrder asc " );
		
		
		qry.setString("resourceTypeId", resourceTypeId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.findResourceByType");
		List<Resource> resources = (List<Resource>) qry.list();
		
		for (Resource r:resources ) {
			
			Hibernate.initialize(r.getResourceType());
			Hibernate.initialize(r.getResourceProps());
			Hibernate.initialize(r.getResourceRoles());

		}
		
		return resources;
	}
	

	
	public List<Resource> getResourcesByCategory(String categoryId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resource from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.categoryId = :categoryId " +
				"order by resource.displayOrder asc " );
				
		qry.setString("categoryId", categoryId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.findResourcesByCategory");
		List<Resource> resources = (List<Resource>) qry.list();
		
		for (Resource r:resources ) {
			
			Hibernate.initialize(r.getResourceType());
			Hibernate.initialize(r.getResourceProps());
			Hibernate.initialize(r.getResourceRoles());
		}
		
		return resources;
	}

	public List<Resource> getResourcesByBranch(String branchId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resource from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.branchId = :branchId " +
				"order by resource.displayOrder asc " );
				
		qry.setString("branchId", branchId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.findResourcesByBranch");
		List<Resource> resources = (List<Resource>) qry.list();
		
		for (Resource r:resources ) {
			
			Hibernate.initialize(r.getResourceType());
			Hibernate.initialize(r.getResourceProps());
			Hibernate.initialize(r.getResourceRoles());
		}
		
		return resources;
	}
	
	
	public List<Resource> getChildResources(String resourceId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resource from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.resourceParent = :resourceId " +
				"order by resource.displayOrder asc ");
				
		qry.setString("resourceId", resourceId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.getChildResources");
		List<Resource> resources = (List<Resource>) qry.list();
		
		for (Resource r:resources ) {
			
			Hibernate.initialize(r.getResourceType());
			Hibernate.initialize(r.getResourceProps());
			Hibernate.initialize(r.getResourceRoles());
			
		}
		
		return resources;
	}


	public List<Resource> getRootResources() {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resource from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.resourceParent is null " +
				" or resource.resourceParent = '' " +
				"order by resource.displayOrder asc ");
				
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.getRootResources");
		List<Resource> resources = (List<Resource>) qry.list();
		
		for (Resource r:resources ) {
			
			Hibernate.initialize(r.getResourceType());
			Hibernate.initialize(r.getResourceProps());
			Hibernate.initialize(r.getResourceRoles());
		}
		
		return resources;
	}
	
	

	public int removeResourcesByType(String resourceTypeId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery(
			"delete from resource r where r.resourceType.resourceTypeId = :resourceTypeId" );
				
		qry.setString("resourceTypeId", resourceTypeId);
		return qry.executeUpdate();		
	}
	

	
	public int removeResourcesByCategory(String categoryId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.categoryId = :categoryId " );
				
		qry.setString("categoryId", categoryId);
		return qry.executeUpdate();		
	}

	public int removeResourcesByBranch(String branchId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"where resource.branchId = :branchId " );
				
		qry.setString("branchId", branchId);
		return qry.executeUpdate();
	}
	

// ResourceRole--------------------------------------------------------------------

public List<ResourceRole> findResourceRolesByResource(String resourceId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"select resourceRole from org.openiam.idm.srvc.res.dto.Resource as resource " +
				"inner join resource.resourceRoles as resourceRole " +
		"where resource.resourceId = :resourceId ");

		qry.setString("resourceId", resourceId);
		qry.setCacheable(true);
		qry.setCacheRegion("query.resource.findResourceRoleByResource");
		List<ResourceRole> result = (List<ResourceRole>) qry.list();

		return result;
	}

	public List<Resource> findResourcesForRole(String domainId, String roleId) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Query qry = session.createQuery( 
					"SELECT resource  " +
					"FROM org.openiam.idm.srvc.res.dto.Resource as resource " +
					"	inner join resource.resourceRoles as rr " +
					" where rr.id.domainId = :domainId and rr.id.roleId = :roleId " +
					" order by resource.managedSysId, resource.name  "
					);
	
			qry.setString("domainId", domainId);
			qry.setString("roleId", roleId);
			
			qry.setCacheable(true);
			qry.setCacheRegion("query.resource.findResourceForRole");
			List<Resource> result = (List<Resource>) qry.list();
			if (result == null || result.isEmpty()) {
				log.debug("get successful, no instance found");
				return null;
			}
			log.debug("get successful, resource instances found");
			return result;
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}


	}
	
	public List<Resource> findResourcesForRoles(String domainId, List<String> roleIdList) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Query qry = session.createQuery( 
					"SELECT distinct resource  " +
					"FROM org.openiam.idm.srvc.res.dto.Resource as resource " +
					"	inner join resource.resourceRoles as rr " +
					" where rr.id.domainId = :domainId and rr.id.roleId in ( :roleIdList ) " +
					" order by resource.managedSysId, resource.name  "
					);
	
			qry.setString("domainId", domainId);
			qry.setParameterList("roleIdList", roleIdList);
			
			qry.setCacheable(true);
			qry.setCacheRegion("query.resource.findResourceForRole");
			List<Resource> result = (List<Resource>) qry.list();
			if (result == null || result.isEmpty()) {
				log.debug("get successful, no instance found");
				return null;
			}
			log.debug("get successful, resource instances found");
			return result;
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}


	}
	

	public void addResourceRolePrivilege(String resourceId, String roleId, String privilegeId) {

		ResourceRoleId rrId = new ResourceRoleId();
		rrId.setResourceId(resourceId);
		rrId.setRoleId(roleId);
		rrId.setPrivilegeId(privilegeId);

		ResourceRole p = resourceRoleDao.findById(rrId);

		Resource resource = findById(resourceId);				
		resource.getResourceRoles().add(p);

		try {
			sessionFactory.getCurrentSession().merge(resource);
			log.debug("persist successful");
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}
	}


	public void removeResourceRolePrivilege(String resourceId, String roleId, String privilegeId) {

		Resource resource = findById(resourceId);				

		if (resource == null) {
			log.error("Resource not found for resourceId =" + resourceId);
			throw new ObjectNotFoundException();
		}

		Set<ResourceRole> all = resource.getResourceRoles();
		if (all == null || all.isEmpty()) {
			return;
		}

		for (Iterator<ResourceRole> it = all.iterator(); it.hasNext(); ) {
			ResourceRole rr = it.next(); 

			if ( (rr.getId().getResourceId().equalsIgnoreCase(resourceId))
					&& (rr.getId().getRoleId().equalsIgnoreCase(roleId))
					&& (rr.getId().getPrivilegeId().equalsIgnoreCase(privilegeId))	) {

				it.remove();
			}
		}

	}


	public int removeResourceRolePrivileges(String resourceId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.res.dto.ResourceRole as resourceRole " +
				"where resourceRole.resourceId = :resourceId " );
				
		qry.setString("resourceId", resourceId);
		return qry.executeUpdate();		
		
	}

}


