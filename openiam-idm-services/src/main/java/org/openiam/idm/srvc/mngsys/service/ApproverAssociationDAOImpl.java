package org.openiam.idm.srvc.mngsys.service;

// Generated Feb 6, 2010 7:16:15 PM by Hibernate Tools 3.2.2.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;


/**
 * DAO implementation for  domain model class ApproverAssociationDAO.
 */
public class ApproverAssociationDAOImpl implements ApproverAssociationDAO {

	private static final Log log = LogFactory.getLog(ApproverAssociationDAOImpl.class);

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

	public ApproverAssociation add(ApproverAssociation transientInstance) {
		log.debug("persisting ApproverAssociation instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void remove(ApproverAssociation persistentInstance) {
		log.debug("deleting ApproverAssociation instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ApproverAssociation update(ApproverAssociation detachedInstance) {
		log.debug("merging AttributeMap instance");
		try {
			ApproverAssociation result = (ApproverAssociation) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ApproverAssociation findById(java.lang.String id) {
		log.debug("getting AttributeMap instance with id: " + id);
		try {
			ApproverAssociation instance = (ApproverAssociation) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.mngsys.dto.ApproverAssociation",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	// e.g NEW_HIRE
	public ApproverAssociation findApproversByRequestType(String requestType, int level) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation ra " +
				" where ra.approverLevel = :level and ra.requestType = :requestType " );
		qry.setString("requestType", requestType);
		qry.setInteger("level", level);

        return (ApproverAssociation) qry.uniqueResult();

	}
	
	public List<ApproverAssociation> findAllApproversByRequestType(String requestType) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation ra " +
				" where ra.requestType = :requestType " +
				" order by ra.approverLevel ");
		qry.setString("requestType", requestType);
		
		List<ApproverAssociation> results = (List<ApproverAssociation>)qry.list();
		return results;			
	}
	
	public int removeApproversByRequestType(String requestType) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				"where aa.requestType = :requestType " );
				
		qry.setString("requestType", requestType);
		return qry.executeUpdate();		
	}

	// RESOURCE_ID
	public List<ApproverAssociation> findApproversByObjectId(String associationObjId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				" where aa.associationObjId = :associationObjId order by aa.approverLevel ");
		qry.setString("associationObjId", associationObjId);
		List<ApproverAssociation> results = (List<ApproverAssociation>)qry.list();
		return results;	
	}

	public int removeApproversByObjectId(String associationObjId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				"where aa.associationObjId = :associationObjId " );
				
		qry.setString("associationObjId", associationObjId);
		return qry.executeUpdate();		
	}

	// find by RESOURCE, GROUP, ROLE, SUPERVISOR,INDIVIDUAL
	public List<ApproverAssociation> findApproversByObjectType(String associationType) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				" where aa.associationType = :associationType and " +
				"       aa.approverLevel = :level and " +
				" order by aa.approverLevel ");
		qry.setString("managedSysId", associationType);
		List<ApproverAssociation> results = (List<ApproverAssociation>)qry.list();
		return results;	
	}
	
	public int removeApproversByObjectType(String associationType) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				"where aa.associationType = :associationType " );
				
		qry.setString("associationType", associationType);
		return qry.executeUpdate();		
	}
	
	public List<ApproverAssociation> findApproversByAction(String associationObjId,
			String action, int level) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				" where aa.associationObjId = :associationObjId and " +
				"       aa.approverLevel = :level and " +
				"       aa.actn = :action " +
				" order by aa.approverLevel ");
		qry.setString("associationObjId", associationObjId);
		qry.setInteger("level", level);
		qry.setString("actn", action);
		List<ApproverAssociation> results = (List<ApproverAssociation>)qry.list();
		return results;	
	}

	
	public List<ApproverAssociation> findApproversByUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				" where aa.approverUserId = :userId " +
				" order by aa.approverAssocId ");
		qry.setString("approverUserId", userId);
		List<ApproverAssociation> results = (List<ApproverAssociation>)qry.list();
		return results;	
	}

	
	public int removeApproversByUser(String userId) {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation aa " +
				"where aa.approverUserId = :userId " );
				
		qry.setString("approverUserId", userId);
		return qry.executeUpdate();		
	}
	
	public int removeAllApprovers() {
		Session session = sessionFactory.getCurrentSession();

		Query qry = session.createQuery( 
				"delete from org.openiam.idm.srvc.mngsys.dto.ApproverAssociation ");
		return qry.executeUpdate();		
	}
	
	
}
