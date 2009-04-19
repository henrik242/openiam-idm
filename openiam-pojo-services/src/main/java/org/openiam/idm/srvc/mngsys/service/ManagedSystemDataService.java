package org.openiam.idm.srvc.mngsys.service;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;

/**
 * Interface for <code>ManagedSystemDataService</code>
 * @author suneet shah
 *
 */
public interface ManagedSystemDataService {

	/**
	 * Returns a ManagedSys object for the specified systemId.
	 * @param sysId
	 * @return
	 */
	ManagedSys getManagedSys(String sysId);
	/**
	 * Returns an array of ManagedSys object for a provider.  The providerId is the same as the 
	 * connectorId.
	 * @param providerId
	 * @return
	 */
	ManagedSys[] getManagedSysByProvider(String providerId);
	
	/**
	 * Returns an array of ManagedSys object for a security domain.  
	 * @param domainId
	 * @return
	 */
	ManagedSys[] getManagedSysByDomain(String domainId);
	
	/**
	 * Creates a new managed system entry into the system. The ManagedSystemId is auto-generated.
	 * Required fields include:
	 * 	<li>ConnectorId
		<li>DomainId
	 * @param sys
	 */
	void addManagedSystem(ManagedSys sys);
	
	
	/**
	 * Updates an existing managed system entry.
	 * @param sys
	 */
	void updateManagedSystem(ManagedSys sys);
	
	
	/**
	 * Removes a managed system entry from the system.
	 * @param sysId
	 */
	void removeManagedSystem(String sysId);
}
