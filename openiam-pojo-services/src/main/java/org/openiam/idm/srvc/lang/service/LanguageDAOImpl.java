package org.openiam.idm.srvc.lang.service;

/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.lang.dto.Language;

import static org.hibernate.criterion.Example.create;

/**
 * DAO to manage the list of languages.
 * @see org.openiam.idm.srvc.lang.dto.Language
 * @author Suneet Shah
 */
public class LanguageDAOImpl implements LanguageDAO {

	private static final Log log = LogFactory.getLog(LanguageDAOImpl.class);

	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDAO#add(org.openiam.idm.srvc.lang.dto.Language)
	 */
	public void add(Language transientInstance) {
		log.debug("persisting Language instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDAO#remove(org.openiam.idm.srvc.lang.dto.Language)
	 */
	public void remove(Language persistentInstance) {
		log.debug("deleting Language instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (HibernateException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDAO#update(org.openiam.idm.srvc.lang.dto.Language)
	 */
	public Language update(Language detachedInstance) {
		log.debug("merging Language instance");
		try {
			Language result = (Language) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (HibernateException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDAO#findById(java.lang.String)
	 */
	public Language findById(java.lang.String id) {
		log.debug("getting Language instance with id: " + id);
		try {
			Language instance = (Language) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.lang.dto.Language", id);
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
	 * @see org.openiam.idm.srvc.lang.service.LanguageDAO#findAllLanguages()
	 */
	public List<Language> findAllLanguages() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query qry = session.createQuery("from org.openiam.idm.srvc.lang.dto.Language l " +
					" order by l.name asc");
			List<Language> results = (List<Language>)qry.list();
			return results;
		} catch (HibernateException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
