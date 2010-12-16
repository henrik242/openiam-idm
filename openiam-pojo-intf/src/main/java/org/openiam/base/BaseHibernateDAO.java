package org.openiam.base;

import java.io.Serializable;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.hibernate.*;


import java.lang.reflect.*;


public abstract class BaseHibernateDAO <T, ID extends Serializable> implements BaseDAO<T, ID> {

	protected Class<T> persistentClass;
	protected SessionFactory sessionFactory;
	protected static Log log = null;

    public BaseHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }
    

	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
		   this.getClass();
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
	
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
	


	/**
	 * Adds a new instance
	 * @param instance
	 */
	public void add(T instance) {
		log.debug("persisting instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}

	}

	/**
	 * Return an object for the id.
	 * @param id
	 */
	public T findById(ID id) {
		// TODO Auto-generated method stub
		log.debug("getting instance with id: " + id);
		
        T entity;
        entity = (T) sessionFactory.getCurrentSession().load(getPersistentClass(), id);
        return entity;
        
	}
	

	/**
	 * Removes an existing instance
	 * @param instance
	 */
	public void remove(T instance) {
		log.debug("deleting instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}

	}
	/**
	 * Updates an existing instance
	 * @param instace
	 */
	public void update(T instance) {
		log.debug("merging instance");
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}

	}

}
