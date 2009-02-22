package org.openiam.idm.srvc.user.service;


import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.HibernateException;

import org.openiam.base.id.SequenceGenDAO;
import org.openiam.idm.srvc.org.dto.Organization;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserSearchField;
import org.openiam.util.db.Search;
import org.openiam.util.db.QueryCriteria;
import org.openiam.util.db.OperationConstant;

/**
 * Data access implementation for domain model class User and UserWS. UserWS is similar to User,
 * however, the interface has been simplified to support usage in a web service.
 * @see org.openiam.idm.srvc.user
 * @author Suneet Shah
 */
public class UserDAOImpl implements UserDAO{



	private static final Log log = LogFactory.getLog(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	private SequenceGenDAO seqGenDao;

	private Integer maxResultSet;
	
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

	public void add(User usr) {
		log.debug("persisting User instance");
		try {
			// If the object has not been assigned an id, we should automatically assign one
			// based on the predefined id generator. Hibernate ID generator does not give us 
			// the flexibility to let the user assign the id.
			if (usr.getUserId() == null || 
				usr.getUserId().length() == 0) {			
				usr.setUserId( this.seqGenDao.getNextId("USER_ID"));
			}
			
			sessionFactory.getCurrentSession().persist(usr);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User update(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			// Since contact info objects can be shared between users and other entities, the 
			// filters will help ensure that we only see the User related object.
			
			session.enableFilter("parentTypeFilter")
					.setParameter("parentFilter", "USER");
			
			User instance = (User)session.get("org.openiam.idm.srvc.user.dto.User", id);
			
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

	public List findByExample(User instance) {
		System.out.println("In findByExample....");
		log.debug("finding User instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"org.openiam.idm.srvc.user.dto.User").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByStatus(String status) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.User u " + 
				" where u.status = :status order by u.lastName asc");
		qry.setString("status", status);
		List<User> results = (List<User>)qry.list();
		return results;
	}
	
	public List findByLastUpdateRange(Date startDate, Date endDate) {
		log.debug("finding User based on the lastUpdate date range that is provided");
		try {
			
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =  session.createCriteria(org.openiam.idm.srvc.user.dto.User.class);
			criteria.add(Restrictions.between("lastUpdate", startDate, endDate));
			return criteria.list();
		
		} catch (HibernateException re) {
			re.printStackTrace();
			log.error("findByLastUpdateRange failed.", re);
			throw re;
		}
	}
	

	public List<User> search(Search search) {
		log.debug("Search for user based on search parameters.");
		boolean phoneLinked = false;
		try {
			
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria =  session.createCriteria(org.openiam.idm.srvc.user.dto.User.class, "user");
				
			criteria.createAlias("emailAddress", "emailAddress", CriteriaSpecification.LEFT_JOIN );
			criteria.setFetchMode("emailAddress", FetchMode.JOIN);
			
			//session.create
			
			// get the criteria set by the user.
			List criteriaList = search.getCriteria();
			if (criteriaList != null) {
				int size = criteriaList.size();
				for (int i=0; i<size; i++) {
					QueryCriteria qc = (QueryCriteria)criteriaList.get(i);
					// link in the tables
					if (qc.getPropertyName().startsWith("phone") && !phoneLinked) {
						criteria.createAlias("phone", "phone", CriteriaSpecification.LEFT_JOIN );
						criteria.setFetchMode("phone", FetchMode.JOIN);		
						phoneLinked = true;
					}
					
					String operationName = qc.getOperationName();
					if (operationName.equalsIgnoreCase(OperationConstant.Equals)) {
						criteria.add(Restrictions.eq(qc.getPropertyName(), qc.getValue()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.NotEquals)) {
						criteria.add(Restrictions.ne(qc.getPropertyName(), qc.getValue()));						
					}
					if (operationName.equalsIgnoreCase(OperationConstant.GreaterThan)) {
						criteria.add(Restrictions.ge(qc.getPropertyName(), qc.getValue()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.GreaterThanEquals)) {
						criteria.add(Restrictions.gt(qc.getPropertyName(), qc.getValue()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.In)) {
						criteria.add(Restrictions.in(qc.getPropertyName(), qc.getValueAry()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.IsNotNull)) {
						criteria.add(Restrictions.isNotNull(qc.getPropertyName()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.IsNull)) {
						criteria.add(Restrictions.isNull(qc.getPropertyName()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.LessThan)) {
						criteria.add(Restrictions.le(qc.getPropertyName(), qc.getValue()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.LessThanEquals)) {
						criteria.add(Restrictions.lt(qc.getPropertyName(), qc.getValue()));
					}
					if (operationName.equalsIgnoreCase(OperationConstant.Like)) {
						criteria.add(Restrictions.ilike(qc.getPropertyName(), qc.getValue()));
					}
				}
			}
	
			
		criteria.addOrder(Order.asc("user.lastName"));
		List<User> searchResult = criteria.list();
		return searchResult; 

		
		} catch (HibernateException re) {
			log.error("findByLastUpdateRange failed.", re);
			throw re;
		}
	}

	public List<User> findByOrganization(String orgId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.User u " +
				" where u.companyId = :orgId order by u.lastName asc");
		qry.setString("orgId", orgId);
		List<User> results = (List<User>)qry.list();
		return results;
	
	}
	
	public List<User> findStaff(String supervisorId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
				" inner join org.openiam.idm.srvc.user.dto.User u " + 
				" where s.supervisor = :supervisorId and  s.staff = u.userId order by u.userId asc");
		qry.setString("supervisor", supervisorId);
		List<User> results = (List<User>)qry.list();
		return results;
	}

	public List<User> findSupervisors(String staffId) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.user.dto.Supervisor s " +
				" inner join org.openiam.idm.srvc.user.dto.User u " + 
				" where s.staff = :staffId and  s.supervisor = u.userId order by u.userId asc");
		qry.setString("supervisor", staffId);
		List<User> results = (List<User>)qry.list();
		return results;
	}	
	
	
	public SequenceGenDAO getSeqGenDao() {
		return seqGenDao;
	}

	public void setSeqGenDao(SequenceGenDAO seqGenDao) {
		this.seqGenDao = seqGenDao;
	}


	public Integer getMaxResultSet() {
		return maxResultSet;
	}


	public void setMaxResultSet(Integer maxResultSet) {
		this.maxResultSet = maxResultSet;
	}

	
}
