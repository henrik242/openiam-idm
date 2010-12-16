package org.openiam.idm.srvc.grp.service;

// Generated Jun 12, 2007 10:46:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.openiam.idm.srvc.grp.dto.*;

/**
 *  Data access object implementation for GroupAttribute. 
 * @see org.openiam.idm.srvc.grp.dto.GroupAttribute
 */
public class GroupAttributeDAOImpl implements GroupAttributeDAO {



	private static final Log log = LogFactory.getLog(GroupAttributeDAOImpl.class);

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


	public GroupAttribute findById(java.lang.String id) {
		log.debug("getting CompanyAttribute instance with id: " + id);
		try {
			GroupAttribute instance = (GroupAttribute) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.grp.dto.GroupAttribute", id);
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

	public void add(GroupAttribute instance) {
		log.debug("persisting GroupAttribute instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
		
	}

	public void remove(GroupAttribute instance) {
		log.debug("deleting GroupAttribute instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}	
		
	}

	public void update(GroupAttribute instance) {
		log.debug("merging Group instance");
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}		
	}
	
	/**
	 * Return a list of GroupAttribute objects for the organization that is specified by the parentId
	 * @param parentId
	 * @return
	 */
	public List<GroupAttribute> findAttributesByParent(String parentId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.grp.dto.GroupAttribute oa " + 
						" where oa.groupId = :parentId order by oa.grpName asc");
		qry.setString("companyId", parentId);
		List<GroupAttribute> results = (List<GroupAttribute>)qry.list();
		return results;		
	}
	/**
	 * Removes all the GroupAttributes that are associated with the Group specified by the parentId.
	 * @param parentId
	 * @return The number of entities deleted.
	 */
	public int removeAttributesByParent(String parentId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.grp.dto.GroupAttribute oa " + 
					" where oa.groupId = :parentId ");
		qry.setString("parentId", parentId);
		return qry.executeUpdate();		
	}

	/**
	 * Removes the attributes for a list of groups specified by the groupIdList. groupIdList is a string containing a concatenated
	 * list of groupIds.
	 * @param groupIdList
	 * @return The number of entities deleted.
	 */
	public int removeAttributesForGroupList(String groupIdList) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.grp.dto.GroupAttribute g  " + 
					" where g.groupId  in (" + groupIdList + ")" );
		return qry.executeUpdate();		
	}	
	

}
