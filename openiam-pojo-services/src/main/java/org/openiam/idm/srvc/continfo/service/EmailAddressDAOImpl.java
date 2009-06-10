package org.openiam.idm.srvc.continfo.service;

// Generated Jun 12, 2007 10:46:15 PM by Hibernate Tools 3.2.0.beta8

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.continfo.dto.Address;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.util.ws.collection.MapItem;


/**
 * Implementation for EmailAddresDAO. Associated with a RDBMS.
 * @author Suneet Shah
 *
 */
public class EmailAddressDAOImpl  implements EmailAddressDAO, EmailAddressWSDAO {  

	private static final Log log = LogFactory.getLog(AddressDAOImpl.class);

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

	
	

	public EmailAddress findById(java.lang.String id) {
		log.debug("getting EmailAddress instance with id: " + id);
		try {
			EmailAddress instance = (EmailAddress) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.continfo.dto.EmailAddress", id);
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

	/**
	 * Adds a new instance
	 * @param instance
	 */
	public void add(EmailAddress instance) {
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
	 * Removes an existing instance
	 * @param instance
	 */
	public void remove(EmailAddress instance) {
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
	public void update(EmailAddress instance) {
		log.debug("merging instance");
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}

	}

	

	public EmailAddress findByName(String name, String parentId, String parentType) {
		log.debug("getting EmailAddress instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.EmailAddress a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType and a.name = :name");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		qry.setString("name", name);
		List<EmailAddress> result = (List<EmailAddress>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result.get(0);	

	}

	public Map<String, EmailAddress> findByParent(String parentId, String parentType) {
		log.debug("getting EmailAddress instances by userId " + parentId );
		
		Map<String, EmailAddress> adrMap = new HashMap<String,EmailAddress>();

		List<EmailAddress> addrList = findByParentAsList(parentId, parentType);
		if (addrList == null)
			return null;
		int size = addrList.size();
		for (int i=0; i<size; i++) {
			EmailAddress adr = addrList.get(i);
			//adrMap.put(adr.getDescription(), adr);
			adrMap.put(adr.getEmailId(), adr);
		}
		if (adrMap.isEmpty())
			return null;
		return adrMap;
	}

	public List<EmailAddress> findByParentAsList(String parentId, String parentType) {
		log.debug("getting EmailAddress instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.EmailAddress a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		List<EmailAddress> result = (List<EmailAddress>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}

	public EmailAddress findDefault(String parentId, String parentType) {
		log.debug("getting EmailAddress instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.EmailAddress a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType and a.isDefault = 1");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		return (EmailAddress)qry.uniqueResult();
	}

	public void removeByParent(String parentId, String parentType) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.continfo.dto.EmailAddress a " + 
				" where a.parentId = :parentId and   " +
				" 		a.parentType = :parentType");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		qry.executeUpdate();	
		
	}

	
	public void saveEmailAddressMap(String parentId, String parentType, Map<String, EmailAddress> adrMap) {
		// get the current map and then compare each record with the map that has been passed in.
		Map<String,EmailAddress> currentMap =  this.findByParent(parentId, parentType);
		if (currentMap != null) {
			Iterator<EmailAddress> it = currentMap.values().iterator();
			while (it.hasNext()) {
				EmailAddress curEmail =  it.next();
				EmailAddress newEmail = adrMap.get(curEmail.getEmailId());
				if (newEmail == null) {
					this.remove(curEmail);
				}else {
					this.update(newEmail);
				}
					
			}
		}
		// add the new records in currentMap that are not in the existing records
		Collection<EmailAddress> adrCol = adrMap.values();
		Iterator<EmailAddress> itr = adrCol.iterator();
		while (itr.hasNext()) {
			EmailAddress newEmail = itr.next();
			EmailAddress curEmail = null;
			if (currentMap != null ) {
				curEmail = currentMap.get(newEmail.getEmailId());
			}
			if (curEmail == null) {
				add(newEmail);
			}
		}		
	}

	public EmailAddress[] findByParentAsArray(String parentId, String parentType) {
		log.debug("getting EmailAddress instances by userId " + parentId );

		List<EmailAddress> result = this.findByParentAsList(parentId, parentType);
		if (result == null || result.size() == 0)
			return null;
		return (EmailAddress[])result.toArray();		
	}



	public void saveEmailAddressArray(String parentId, String parentType,	EmailAddress[] emailAry) {
		int len = emailAry.length;

		Map<String,EmailAddress> currentMap =  this.findByParent(parentId, parentType);
		if (currentMap != null) {
			Set<String> keys = currentMap.keySet();
			Iterator<String> it = keys.iterator();
			int ctr = 0;
			while (it.hasNext()) {
				String key = it.next();
				EmailAddress newEmail = getEmailFromArray(emailAry, key);
				EmailAddress curEmail = currentMap.get(key);
				if (newEmail == null) {
					// address was removed - deleted
					remove(curEmail);
				}else if (!curEmail.equals(newEmail)) {
					// object changed
					this.update(newEmail);
				}
			}
		}
		// add the new records in currentMap that are not in the existing records
		for (int i=0; i<len; i++) {
			EmailAddress curAdr = null;
			EmailAddress  email = emailAry[i];
			String key =  email.getEmailId();
			if (currentMap != null )  {
				curAdr = currentMap.get(key);
			}
			if (curAdr == null) {
				// new address object
				this.add(email);
			}
		}
		
	}

	private EmailAddress getEmailFromArray(EmailAddress[] adrAry, String id) {
		EmailAddress adr = null;
		int len = adrAry.length;
		for (int i=0;i<len;i++) {
			EmailAddress tempAdr = adrAry[i];
			if (tempAdr.getEmailId().equals(id)) {
				return tempAdr;
			}
		}
		return adr;
	}
}
