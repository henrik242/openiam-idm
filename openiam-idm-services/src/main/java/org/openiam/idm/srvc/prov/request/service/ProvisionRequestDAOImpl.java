package org.openiam.idm.srvc.prov.request.service;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupSearch;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestApprover;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;

/**
 * DAO to manage ProvisionRequest objects.
 */
public class ProvisionRequestDAOImpl implements ProvisionRequestDAO {

	protected static final Log log = LogFactory.getLog(ProvisionRequestDAOImpl.class);
	protected SessionFactory sessionFactory;
	protected int maxResultSetSize;

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
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#add(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public void add(ProvisionRequest transientInstance) {
		log.debug("persisting ProvRequest instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#remove(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public void remove(ProvisionRequest persistentInstance) {
		log.debug("deleting ProvRequest instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#update(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public ProvisionRequest update(ProvisionRequest detachedInstance) {
		log.debug("merging ProvRequest instance");
		try {
			ProvisionRequest result = (ProvisionRequest) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.ProvisionRequestDAO#findById(java.lang.String)
	 */
	public ProvisionRequest findById(java.lang.String id) {
		log.debug("getting ProvRequest instance with id: " + id);
		try {
			ProvisionRequest instance = (ProvisionRequest) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.prov.request.dto.ProvisionRequest", id);
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
	
	public  List<ProvisionRequest> findByStatus(String status) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.prov.request.dto.ProvisionRequest req " +
				" where req.status = :status order by req.requestDate asc");
		qry.setString("status", status);
		List<ProvisionRequest> results = (List<ProvisionRequest>)qry.list();
		return results;		
	}
	
	public List<ProvisionRequest> findRequestByApprover(String approverId, String status) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("select req from org.openiam.idm.srvc.prov.request.dto.ProvisionRequest req, " +
				" org.openiam.idm.srvc.prov.request.dto.RequestApprover appr " +
				" where req.requestId = appr.requestId and " +
				"	    appr.approverId = :approverId and  " +
				"  		req.status = :status " + 
 				" order by req.requestDate ");
		qry.setString("approverId", approverId);
		qry.setString("status", status);
		List<ProvisionRequest> results = (List<ProvisionRequest>)qry.list();
		return results;	
	}


	
	public List<ProvisionRequest> search(SearchRequest search) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest.class);
		crit.createAlias("requestApprovers", "requestApprovers");
		crit.setMaxResults(maxResultSetSize);

        List<String> roleIdList = search.getRoleIdList();
        if (roleIdList == null) {
            roleIdList = new ArrayList<String>();
        }


		if (search.getEndDate() != null  ) {

			crit.add(Restrictions.le("statusDate",search.getEndDate()));
		}
		if (search.getStartDate() != null  ) {

			crit.add(Restrictions.ge("statusDate",search.getStartDate()));
		}
		if (search.getRequestId() != null && search.getRequestId().length() > 0  ) {

			crit.add(Restrictions.eq("requestId",search.getRequestId()));
		}
		if (search.getRequestorId() != null && search.getRequestorId().length() > 0  ) {

			crit.add(Restrictions.eq("requestorId",search.getRequestorId()));
		}
		if (search.getStatus() != null && search.getStatus().length() > 0  ) {
			log.debug("search: status=" + search.getStatus() );
			crit.add(Restrictions.eq("status",search.getStatus()));
		}

       if (search.getApproverId() != null && search.getApproverId().length() > 0) {

           log.debug("search: approverId=" + search.getApproverId());

           // in our database we have the role Id and the individual approvers in the same field
           roleIdList.add(search.getApproverId());
		}

        if (!roleIdList.isEmpty() ) {
            crit.add(Restrictions.in("requestApprovers.approverId", roleIdList));
        }

        if (search.getRequestForOrgList() != null && !search.getRequestForOrgList().isEmpty()) {

            log.debug("Filtering by OrgList=" + search.getRequestForOrgList());

            crit.add(Restrictions.in("requestForOrgId", search.getRequestForOrgList()));
        }




		crit.addOrder(Order.desc("requestDate"));
		
		List<ProvisionRequest> results = (List<ProvisionRequest>)crit.list();
		return results;		
	}

	public int getMaxResultSetSize() {
		return maxResultSetSize;
	}

	public void setMaxResultSetSize(int maxResultSetSize) {
		this.maxResultSetSize = maxResultSetSize;
	}
	
}
