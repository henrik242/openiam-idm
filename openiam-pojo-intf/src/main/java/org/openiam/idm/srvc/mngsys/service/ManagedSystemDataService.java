package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.dto.ResourceApprover;
import org.openiam.idm.srvc.user.dto.User;

/**
 * Interface for <code>ManagedSystemDataService</code>
 * @author suneet shah
 *
 */
@WebService
public interface ManagedSystemDataService {

	/**
	 * Returns a ManagedSys object for the specified systemId.
	 * @param sysId
	 * @return
	 */
	ManagedSys getManagedSys(String sysId);

	/**
	 * Returns a ManagedSys object for the specified name. The name is the value in the
	 * name field in the ManagedSys object. 
	 * @param name
	 * @return
	 */
	ManagedSys getManagedSysByName(String name);
	
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
	
	ManagedSys getManagedSysByResource(String resourceId);
	
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
	
	/**
	 * Finds objects for an object type (like User, Group) for a ManagedSystem definition
	 * @param managedSystemId
	 * @param objectType
	 * @return
	 */
	public ManagedSystemObjectMatch[] managedSysObjectParam(String managedSystemId, String objectType);
	
	/**
	 * Get the full list of ResourceApprovers for a resource/managed system
	 * @param managedSysId
	 * @return
	 */
	
	public List<ResourceApprover> getApproversByResource(String managedSysId);
	
	/**
	 * Get the approvers for a resource that are linked to a specific approval step. From example, you can
	 * get the approvers to start the 2nd level of approval.
	 * 
	 * @param managedSysId
	 * @param action
	 * @param level
	 * @return
	 */
	public List<ResourceApprover> getApproversByAction(String managedSysId, String action, int level);
	

	
}
