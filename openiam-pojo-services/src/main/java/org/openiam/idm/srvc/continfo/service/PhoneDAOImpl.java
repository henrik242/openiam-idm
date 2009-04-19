package org.openiam.idm.srvc.continfo.service;


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
import org.openiam.idm.srvc.continfo.dto.Phone;
import org.openiam.util.ws.collection.MapItem;

/**
 * Implementation for PhoneDAO. Associated with a RDBMS.
 * @author Suneet Shah
 *
 */
public class PhoneDAOImpl implements PhoneDAO, PhoneWSDAO {

	private static final Log log = LogFactory.getLog(PhoneDAOImpl.class);

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

	public Phone findById(java.lang.String id) {
		log.debug("getting Phone instance with id: " + id);
		try {
			Phone instance = (Phone) sessionFactory
					.getCurrentSession().get(
							"org.openiam.idm.srvc.continfo.dto.Phone", id);
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
	public void add(Phone instance) {
		log.debug("persisting instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("persist failed", re);
			throw re;
		}

	}

	/**
	 * Removes an existing instance
	 * @param instance
	 */
	public void remove(Phone instance) {
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
	public void update(Phone instance) {
		log.debug("merging instance");
		try {
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("merge failed", re);
			throw re;
		}

	}

	

	public Phone findByName(String name, String parentId, String parentType) {
		log.debug("getting Phone instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.Phone a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType and a.name = :name");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		qry.setString("name", name);
		return (Phone)qry.uniqueResult();		

	}

	public Map<String, Phone> findByParent(String parentId, String parentType) {
		log.debug("getting Phone instances by userId " + parentId );
		
		Map<String, Phone> adrMap = new HashMap<String,Phone>();

		List<Phone> addrList = findByParentAsList(parentId, parentType);
		if (addrList == null)
			return null;
		int size = addrList.size();
		for (int i=0; i<size; i++) {
			Phone adr = addrList.get(i);
			//adrMap.put(adr.getDescription(), adr);
			adrMap.put(adr.getPhoneId(), adr);
		}
		if (adrMap.isEmpty())
			return null;
		return adrMap;
	}

	public List<Phone> findByParentAsList(String parentId, String parentType) {
		log.debug("getting Phone instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.Phone a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		List<Phone> result = (List<Phone>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}

	public Phone findDefault(String parentId, String parentType) {
		log.debug("getting Phone instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.Phone a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType and a.isDefault = 1");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		return (Phone)qry.uniqueResult();
	}

	public void removeByParent(String parentId, String parentType) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.continfo.dto.Phone a " + 
				" where a.parentId = :parentId and   " +
				" 		a.parentType = :parentType");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		qry.executeUpdate();	
		
	}


	
	public void savePhoneMap(String parentId, String parentType, Map<String, Phone> adrMap) {
		// get the current map and then compare each record with the map that has been passed in.
		Map<String,Phone> currentMap =  this.findByParent(parentId, parentType);
		if (currentMap != null) {
			Iterator<Phone> it = currentMap.values().iterator();
			while (it.hasNext()) {
				Phone curPhone =  it.next();
				Phone newPhone = adrMap.get(curPhone.getPhoneId());
				if (newPhone == null) {
					this.remove(curPhone);
				}else {
					this.update(newPhone);
				}
					
			}			
		}
		Collection<Phone> adrCol = adrMap.values();
		Iterator<Phone> itr = adrCol.iterator();
		while (itr.hasNext()) {
			Phone newPhone = itr.next();
			Phone curPhone = null;
			if (currentMap != null ) {
				curPhone = currentMap.get(newPhone.getPhoneId());
			}
			if (curPhone == null) {
				add(newPhone);
			}
		}
		
	}

	public Phone[] findByParentAsArray(String parentId, String parentType) {
		log.debug("getting Phone instances by userId " + parentId );

		List<Phone> result = this.findByParentAsList(parentId, parentType);
		if (result == null || result.size() == 0)
			return null;
		return (Phone[])result.toArray();		
	}


	public void savePhoneArray(String parentId, String parentType, Phone[] phoneAry) {
		int len = phoneAry.length;

		Map<String,Phone> currentMap =  this.findByParent(parentId, parentType);
		if (currentMap != null) {
			Set<String> keys = currentMap.keySet();
			Iterator<String> it = keys.iterator();
			int ctr = 0;
			while (it.hasNext()) {
				String key = it.next();
				Phone newPhone = getPhoneFromArray(phoneAry, key);
				Phone curPhone = currentMap.get(key);
				if (newPhone == null) {
					// address was removed - deleted
					remove(curPhone);
				}else if (!curPhone.equals(newPhone)) {
					// object changed
					this.update(newPhone);
				}
			}
		}
		// add the new records in currentMap that are not in the existing records
		for (int i=0; i<len; i++) {
			Phone curAdr = null;
			Phone  phone = phoneAry[i];
			String key =  phone.getPhoneId();
			if (currentMap != null )  {
				curAdr = currentMap.get(key);
			}
			if (curAdr == null) {
				// new address object
				this.add(phone);
			}
		}
		
	}
	
	private Phone getPhoneFromArray(Phone[] phoneAry, String id) {
		Phone phone = null;
		int len = phoneAry.length;
		for (int i=0;i<len;i++) {
			Phone tempPhone = phoneAry[i];
			if (tempPhone.getPhoneId().equals(id)) {
				return tempPhone;
			}
		}
		return phone;
	}
	
}
