package org.openiam.idm.srvc.policy.service;

// Generated Mar 22, 2009 12:07:00 AM by Hibernate Tools 3.2.2.GA

import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.policy.dto.Policy;
import org.openiam.idm.srvc.policy.dto.PolicyAttribute;

import static org.hibernate.criterion.Example.create;

/**
 * DAO Implementation for the Policy.
 * @
 */
public class PolicyDAOImpl implements PolicyDAO {

	private static final Log log = LogFactory.getLog(PolicyDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.policy.service.PolicyDAO#add(org.openiam.idm.srvc.policy.dto.Policy)
	 */
	public void add(Policy transientInstance) {
		log.debug("persisting Policy instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDAO#remove(org.openiam.idm.srvc.policy.dto.Policy)
	 */
	public void remove(Policy persistentInstance) {
		log.debug("deleting Policy instance");
		try {
            removePolicyAttributes(persistentInstance.getPolicyId());

			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDAO#update(org.openiam.idm.srvc.policy.dto.Policy)
	 */
	public Policy update(Policy detachedInstance) {
		log.debug("merging Policy instance");
		
		//
		log.info("--policy attributes - checking");
		Set<PolicyAttribute> attrSet = detachedInstance.getPolicyAttributes(); 
		for ( PolicyAttribute a : attrSet) {
			log.info("Attribute: id" + a.getPolicyAttrId() + " " + a.getName() + " defParamId=" + a.getDefParamId());
		}
		
		try {
			Policy result = (Policy) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDAO#findById(java.lang.String)
	 */
	public Policy findById(java.lang.String id) {
		log.debug("getting Policy instance with id: " + id);
		try {
			Policy instance = (Policy) sessionFactory.getCurrentSession().get(
					"org.openiam.idm.srvc.policy.dto.Policy", id);
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

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.policy.service.PolicyDAO#findByExample(org.openiam.idm.srvc.policy.dto.Policy)
	 */
	public List<Policy> findByExample(Policy instance) {
		log.debug("finding Policy instance by example");
		try {
			List<Policy> results = (List<Policy>) sessionFactory
					.getCurrentSession().createCriteria(
							"org.openiam.idm.srvc.policy.dto.Policy").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Policy> findAllPolicies(String policyDefId) {
		log.debug("finding all Policy instances");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session
					.createQuery(" from Policy p "
							+ " where p.policyDefId = :policyDefId "
							+ " order by p.policyId asc");
			qry.setString("policyDefId", policyDefId);
			List<Policy> results = (List<Policy>) qry.list();
			if (results == null || results.size() == 0)
				return null;
			return results;
		} catch (HibernateException re) {
			log.error("find all Policies failed", re);
			throw re;
		}

		}
	public List<Policy> findPolicyByName(String policyDefId, String policyName) {
		log.debug("finding Policy instance by name");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session
					.createQuery(" from Policy p "
							+ " where p.policyDefId = :policyDefId and p.name = :policyName ");
			qry.setString("policyDefId", policyDefId);
			qry.setString("policyName", policyName);
			List<Policy> results = (List<Policy>) qry.list();
			if (results == null || results.size() == 0)
				return null;
			return results;
		} catch (HibernateException re) {
			log.error("find by example failed", re);
			throw re;
		}
		
	}
    public int removePolicyAttributes(String policyID) {
		try {
            Session session = sessionFactory.getCurrentSession();
            Query qry = session.createQuery("delete from org.openiam.idm.srvc.policy.dto.PolicyAttribute pa where" +
                    " pa.policyId =:policyID ");

            qry.setString("policyID", policyID);


            return qry.executeUpdate();
        }catch (HibernateException he) {
            log.error(he);
            throw he;

        }
	}

}
