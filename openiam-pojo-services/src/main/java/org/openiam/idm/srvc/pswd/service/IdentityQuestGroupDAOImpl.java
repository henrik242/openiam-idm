package org.openiam.idm.srvc.pswd.service;


import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup;


/**
 * DAO implementation object for the domain model class IdentityQuestGroup.
 */
public class IdentityQuestGroupDAOImpl implements IdentityQuestGroupDAO {

	private static final Log log = LogFactory
			.getLog(IdentityQuestGroupDAOImpl.class);

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
	 * @see org.openiam.idm.srvc.pswd.service.IdentityQuestionGroupDAO#add(org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup)
	 */
	public void add(IdentityQuestGroup transientInstance) {
		log.debug("persisting IdentityQuestGrp instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.IdentityQuestionGroupDAO#remove(org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup)
	 */
	public void remove(IdentityQuestGroup persistentInstance) {
		log.debug("deleting IdentityQuestGrp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.IdentityQuestionGroupDAO#update(org.openiam.idm.srvc.pswd.dto.IdentityQuestGroup)
	 */
	public IdentityQuestGroup update(IdentityQuestGroup detachedInstance) {
		log.debug("merging IdentityQuestGrp instance");
		try {
			IdentityQuestGroup result = (IdentityQuestGroup) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.pswd.service.IdentityQuestionGroupDAO#findById(java.lang.String)
	 */
	public IdentityQuestGroup findById(java.lang.String id) {
		log.debug("getting IdentityQuestGrp instance with id: " + id);
		try {
			IdentityQuestGroup instance = (IdentityQuestGroup) sessionFactory
					.getCurrentSession()
					.get("org.openiam.idm.srvc.pswd.service.IdentityQuestGrp",
							id);
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


}
