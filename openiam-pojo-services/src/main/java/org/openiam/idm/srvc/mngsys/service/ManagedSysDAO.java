package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

public interface ManagedSysDAO {

	 void add(ManagedSys transientInstance);

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
}