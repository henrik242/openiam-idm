package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import javax.jws.WebService;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;
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
	 * 
	 * @param sysId
	 *            the sys id
	 * @return the managed sys
	 */
	ManagedSys getManagedSys(String sysId);

	/**
	 * Returns a ManagedSys object for the specified name. The name is the value
	 * in the name field in the ManagedSys object.
	 * 
	 * @param name
	 *            the name
	 * @return the managed sys by name
	 */
	ManagedSys getManagedSysByName(String name);
	
	/**
	 * Returns an array of ManagedSys object for a provider. The providerId is
	 * the same as the connectorId.
	 * 
	 * @param providerId
	 *            the provider id
	 * @return the managed sys by provider
	 */
	ManagedSys[] getManagedSysByProvider(String providerId);
	
	/**
	 * Returns an array of ManagedSys object for a security domain.
	 * 
	 * @param domainId
	 *            the domain id
	 * @return the managed sys by domain
	 */
	ManagedSys[] getManagedSysByDomain(String domainId);
	
	ManagedSys[] getAllManagedSys();
	
	/**
	 * Gets the managed sys by resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the managed sys by resource
	 */
	ManagedSys getManagedSysByResource(String resourceId);
	
	/**
	 * Creates a new managed system entry into the system. The ManagedSystemId
	 * is auto-generated. Required fields include: <li>ConnectorId <li>DomainId
	 * 
	 * @param sys
	 *            the sys
	 */
	void addManagedSystem(ManagedSys sys);
	
	
	/**
	 * Updates an existing managed system entry.
	 * 
	 * @param sys
	 *            the sys
	 */
	void updateManagedSystem(ManagedSys sys);
	
	
	/**
	 * Removes a managed system entry from the system.
	 * 
	 * @param sysId
	 *            the sys id
	 */
	void removeManagedSystem(String sysId);
	
	/**
	 * Finds objects for an object type (like User, Group) for a ManagedSystem
	 * definition.
	 * 
	 * @param managedSystemId
	 *            the managed system id
	 * @param objectType
	 *            the object type
	 * @return the managed system object match[]
	 */
	public ManagedSystemObjectMatch[] managedSysObjectParam(String managedSystemId, String objectType);
	
	public void addManagedSystemObjectMatch(ManagedSystemObjectMatch obj) ;
	
	public void updateManagedSystemObjectMatch(ManagedSystemObjectMatch obj);

	public void removeManagedSystemObjectMatch(ManagedSystemObjectMatch obj);
	
	// Approver Association
	

	/**
	 * Adds the approver association.
	 * 
	 * @param approverAssociation
	 *            the approver association
	 * @return the approver association
	 */
	ApproverAssociation addApproverAssociation(
			ApproverAssociation approverAssociation);

	/**
	 * Updates approver association.
	 * 
	 * @param approverAssociation
	 *            the approver association
	 * @return the approver association
	 */
	ApproverAssociation updateApproverAssociation(
			ApproverAssociation approverAssociation);

	/**
	 * Gets the approver association.
	 * 
	 * @param approverAssociationId
	 *            the approver association id
	 * @return the approver association
	 */
	ApproverAssociation getApproverAssociation(String approverAssociationId);

	/**
	 * Removes the approver association.
	 * 
	 * @param approverAssociationId
	 *            the approver association id
	 */
	void removeApproverAssociation(String approverAssociationId);

	/**
	 * Removes the all approver associations.
	 * 
	 * @return the int
	 */
	int removeAllApproverAssociations();

	/**
	 * Gets the approvers by request type.
	 * 
	 * @param requestType
	 *            the request type
	 * @param level
	 *            the level
	 * @return the approvers by request type
	 */
	List<ApproverAssociation> getApproversByRequestType(String requestType,
			int level);


	List<ApproverAssociation> getAllApproversByRequestType(String requestType);
	/**
	 * Gets the approvers by object id.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @return the approvers by object id
	 */
	List<ApproverAssociation> getApproversByObjectId(String associationObjId);

	/**
	 * Removes the approvers by object id.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @return the int
	 */
	int removeApproversByObjectId(String associationObjId);

	/**
	 * Removes the approvers by object type.
	 * 
	 * @param associationType
	 *            the association type
	 * @return the int
	 */
	int removeApproversByObjectType(String associationType);

	/**
	 * Removes the approvers by user.
	 * 
	 * @param userId
	 *            the user id
	 * @return the int
	 */
	int removeApproversByUser(String userId);
	

	
}
