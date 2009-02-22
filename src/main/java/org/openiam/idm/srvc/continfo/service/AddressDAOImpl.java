package org.openiam.idm.srvc.continfo.service;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.HibernateException;

import org.openiam.exception.data.DataException;
import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.util.ws.collection.MapItem;


/**
 * DAO Implementation for domain model class Address.
 * @see org.openiam.idm.srvc.user.Address
 */
public class AddressDAOImpl implements AddressDAO, AddressWSDAO {


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


	/**
	 * Return an address object for the id.
	 * @param id
	 */
	public Address findById(java.lang.String id) {
		log.debug("getting Address instance with id: " + id);
		try {
			Address instance = (Address) sessionFactory.getCurrentSession()
					.get("org.openiam.idm.srvc.continfo.dto.Address", id);
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
	
	
	
	public List findByExample(Address instance) {
		log.debug("finding Address instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria(
					"org.openiam.idm.srvc.user.Address").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	

	public void add(Address instance) {
		log.debug("persisting Address instance");
		try {
			sessionFactory.getCurrentSession().persist(instance);
			log.debug("persist successful");
		} catch (HibernateException re) {
			log.error("persist failed", re);
			throw new DataException( re.getMessage(), re.getCause() ); 
		}
		
	}
	public void update(Address instance) {
		log.debug("merging Address instance");
		try {
			//sessionFactory.getCurrentSession().saveOrUpdate(instance);
			sessionFactory.getCurrentSession().merge(instance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}		
	}
	public void remove(Address instance){
		log.debug("deleting Address instance");
		try {
			sessionFactory.getCurrentSession().delete(instance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}		
	}
	
	/**
	 * Persist a map of address objects at one time. Handles add, update, delete.
	 * @param adrMap
	 */
	public void saveAddressMap(String parentId, String parentType, Map<String,Address> adrMap) {
		// get the current map and then compare each record with the map that has been passed in.
		Map<String,Address> currentMap =  this.findByParent(parentId, parentType);
		if (currentMap != null) {
			Iterator<Address> it = currentMap.values().iterator();
			while (it.hasNext()) {
				Address curAdr =  it.next();
				Address newAdr = adrMap.get(curAdr.getAddressId());
				if (newAdr == null) {
					this.remove(curAdr);
				}else {
					this.update(newAdr);
				}
					
			}
		}
		
		
	
		Collection<Address> adrCol = adrMap.values();
		Iterator<Address> itr = adrCol.iterator();
		while (itr.hasNext()) {
			Address newAdr = itr.next();
			Address curAdr = null;
			if (currentMap != null ) {
				curAdr = currentMap.get(newAdr.getAddressId());
			}
			if (curAdr == null) {
				add(newAdr);
			}
		}
		
		
		
	}
	
	/**
	 * Returns a Map of Address objects for the parentId and parentType combination.
	 * The map is keyed on the address.description. Address.description indicates
	 * the type of address that we have; ie. Shipping, Billing, etc.
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	public Map<String, Address> findByParent(String parentId,String parentType) {
		log.debug("getting Address instances by userId " + parentId );
		Map<String, Address> adrMap = new HashMap<String,Address>();

		List<Address> addrList = findByParentAsList(parentId, parentType);
		if (addrList == null)
			return null;
		int size = addrList.size();
		for (int i=0; i<size; i++) {
			Address adr = addrList.get(i);
			//adrMap.put(adr.getDescription(), adr);
			adrMap.put(adr.getAddressId(), adr);
		}
		if (adrMap.isEmpty())
			return null;
		return adrMap;
		
	}

	/**
	 * Returns a List of Address objects for the parentId and parentType combination.
	 * @param parentId
	 * @param parentType
	 * @return
	 */	
	public List<Address> findByParentAsList(String parentId,String parentType) {
		log.debug("getting Address instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.Address a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		List<Address> result = (List<Address>)qry.list();
		if (result == null || result.size() == 0)
			return null;
		return result;		
	}
	
	
	/**
	 * Removes all address for a parent
	 * @param parentId
	 * @param parentType
	 */
	public void removeByParent(String parentId,String parentType) {
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("delete org.openiam.idm.srvc.continfo.dto.Address a " + 
				" where a.parentId = :parentId and   " +
				" 		a.parentType = :parentType");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		qry.executeUpdate();	
	}
	/**
	 * Returns a default address for the parentId and parentType combination. 
	 * Returns null if a match is not found.
	 * @return
	 */
	public Address findDefault(String parentId,String parentType) {
		log.debug("getting Address instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.Address a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType and a.isDefault = 1");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		return (Address)qry.uniqueResult();
		
	}
	/**
	 * Return an address object that matches the description. Returns null if a match
	 * is not found.
	 * @param description
	 * @param parentId
	 * @param parentType
	 * @return
	 */
	public Address findByName(String name, String parentId,String parentType) {
		log.debug("getting Address instances by userId " + parentId );

		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery("from org.openiam.idm.srvc.continfo.dto.Address a " +
						" where a.parentId = :parentId and   " +
						" 		a.parentType = :parentType and a.name = :name");
		qry.setString("parentId", parentId);
		qry.setString("parentType", parentType);
		qry.setString("name", name);
		return (Address)qry.uniqueResult();		
	}

	public Address[] findByParentAsArray(String parentId, String parentType) {
		log.debug("getting Address instances by userId " + parentId );

		List<Address> result = this.findByParentAsList(parentId, parentType);
		if (result == null || result.size() == 0)
			return null;
		return (Address[])result.toArray();		
	}


	public void saveAddressArray(String parentId, String parentType,	Address[] adrAry) {
		
		int len = adrAry.length;

		Map<String,Address> currentMap =  this.findByParent(parentId, parentType);
		if (currentMap != null) {
			Set<String> keys = currentMap.keySet();
			Iterator<String> it = keys.iterator();
			int ctr = 0;
			while (it.hasNext()) {
				String key = it.next();
				Address newAdr = getAddrFromArray(adrAry, key);
				Address curAdr = currentMap.get(key);
				if (newAdr == null) {
					// address was removed - deleted
					remove(curAdr);
				}else if (!curAdr.equals(newAdr)) {
					// object changed
					this.update(newAdr);
				}
			}
		}
		// add the new records in currentMap that are not in the existing records
		for (int i=0; i<len; i++) {
			Address curAdr = null;
			Address  adr = adrAry[i];
			String key =  adr.getAddressId();
			if (currentMap != null )  {
				curAdr = currentMap.get(key);
			}
			if (curAdr == null) {
				// new address object
				this.add(adr);
			}
		}
	
	}
	
	private Address getAddrFromArray(Address[] adrAry, String id) {
		Address adr = null;
		int len = adrAry.length;
		for (int i=0;i<len;i++) {
			Address tempAdr = adrAry[i];
			if (tempAdr.getAddressId().equals(id)) {
				return tempAdr;
			}
		}
		return adr;
	}
	
}
