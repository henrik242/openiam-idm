package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

public interface ManagedSysDAO {

	ManagedSys add(ManagedSys transientInstance);

	 void remove(ManagedSys persistentInstance);

	 ManagedSys update(ManagedSys detachedInstance);

	 ManagedSys findById(java.lang.String id);

	 List<ManagedSys> findByExample(ManagedSys instance);
	 
	 List<ManagedSys> findbyConnectorId(String connectorId);

	 /**
	  * Lists all managed systems belonging to a domain.
	  * @param domainId
	  * @return
	  */
	 List<ManagedSys> findbyDomain(String domainId);
	 
	 List<ManagedSys> findAllManagedSys();
	 
	/**
	 * Returns a ManagedSys object for the specified name. The name is the value in the
	 * name field in the ManagedSys object. 
	 * @param name
	 * @return
	 */
	ManagedSys findByName(String name);
	
	/**
	 * Returns the managed system that is associated with the specified resource id.
	 * @param resourceId
	 * @param status
	 * @return
	 */
	ManagedSys findByResource(String resourceId, String status);
}