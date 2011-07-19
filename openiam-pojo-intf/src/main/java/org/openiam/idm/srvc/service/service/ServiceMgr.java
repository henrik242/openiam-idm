package org.openiam.idm.srvc.service.service;


import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.openiam.idm.srvc.service.dto.*;
/**
 * Service interface that clients will access to gain information about services.
 * @author Suneet Shah
 *
 */
public class ServiceMgr {

	protected ServiceDAO serviceDao;
	
	public ServiceMgr() {
		
	}
	
	public ServiceMgr(ServiceDAO serviceDao) {
		super();
		this.serviceDao = serviceDao;
	}
	
	/**
	 * Returns the <code>Service</code> object specified by the the service ID
	 * @param id - Service
	 * @return
	 * @throws RemoteException
	 */
	public Service getService(String id) {
		return serviceDao.findById(id);
	}

/**
 * Adds a new service to the system. 
 * @param val - Service Object
 */

  public void addService(Service serv)  {
	  if (serv == null)
		   throw new NullPointerException("Service object is null");
	  if (serv.getServiceId() == null)
		  throw new NullPointerException("Service id is null");
	  
	  serviceDao.persist(serv);
  }
  
  /**
   * Updates an existing service object.
   * @param serv - Service Object
   */
  public void updateService(Service serv) {
	  if (serv == null)
		   throw new NullPointerException("Service object is null");
	  if (serv.getServiceId() == null)
		  throw new NullPointerException("Service id is null");

	  serviceDao.merge(serv);

  }

  /**
   * Removes an existing service.
   * 
   * @param id - Service id
   */
  public void removeService(String id) {
	  if (id == null)
		  throw new NullPointerException("Service id is null");

	  Service serv = new Service(id);
	  serviceDao.delete(serv);
  }

  /**
   * Removes an existing service.
   * 
   * @param id - Service id
   */
  public void removeService(Service serv) {
	  if (serv == null)
		   throw new NullPointerException("Service object is null");
	  if (serv.getServiceId() == null)
		  throw new NullPointerException("Service id is null");

	  serviceDao.delete(serv);
  }
  
  /**
 * Returns a list of all services in the system
 * @return
 */
  public java.util.List<Service> getAllServices() {
	  return serviceDao.findAll();
  }
  /**
   * Returns all the services in the system in a Map. The map is keyed on the serviceId.
   * The value component of the Map is the <code>Service</code> object.
   * @return
   */
  public java.util.Map<String,Service> getServicesMap() {
	  List<Service> serviceList =  serviceDao.findAll();
	  if (serviceList == null )
		  return null;
	  Map<String,Service> serviceMap = new HashMap<String,Service>();
	  int size = serviceList.size();
	  for (int i=0; i < size; i++) {
		  Service service = serviceList.get(i);
		  serviceMap.put(service.getServiceId(),service);
	  }
	  return serviceMap;
  }
  
  /**
   * Returns a list of Services that are children of the service id that is provided.
   * @param id - ServiceId
   * @return
   */
  public java.util.List<Service> getChildServices(String id) {
	  return serviceDao.findChildServices(id);
  }
  
  /**
   * Returns a list of ServiceValue objects that belong to a type. A service type could be things like AD for Active Directory.
   * @param type
   * @return
   */
  public List getServicesByType(String type) {
	  return serviceDao.findServicesByType(type);
  }

 

  
}
