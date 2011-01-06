package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
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
	@WebMethod
	ManagedSys getManagedSys(
			@WebParam(name = "sysId", targetNamespace = "")
			String sysId);

	/**
	 * Returns a ManagedSys object for the specified name. The name is the value
	 * in the name field in the ManagedSys object.
	 * 
	 * @param name
	 *            the name
	 * @return the managed sys by name
	 */
	@WebMethod
	ManagedSys getManagedSysByName(
			@WebParam(name = "name", targetNamespace = "")
			String name);
	
	/**
	 * Returns an array of ManagedSys object for a provider. The providerId is
	 * the same as the connectorId.
	 * 
	 * @param providerId
	 *            the provider id
	 * @return the managed sys by provider
	 */
	@WebMethod
	ManagedSys[] getManagedSysByProvider(
			@WebParam(name = "providerId", targetNamespace = "")
			String providerId);
	
	/**
	 * Returns an array of ManagedSys object for a security domain.
	 * 
	 * @param domainId
	 *            the domain id
	 * @return the managed sys by domain
	 */
	@WebMethod
	ManagedSys[] getManagedSysByDomain(
			@WebParam(name = "domainId", targetNamespace = "")
			String domainId);
	
	@WebMethod
	ManagedSys[] getAllManagedSys();
	
	/**
	 * Gets the managed sys by resource.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the managed sys by resource
	 */
	@WebMethod
	ManagedSys getManagedSysByResource(
			@WebParam(name = "resourceId", targetNamespace = "")
			String resourceId);
	
	/**
	 * Creates a new managed system entry into the system. The ManagedSystemId
	 * is auto-generated. Required fields include: <li>ConnectorId <li>DomainId
	 * 
	 * @param sys
	 *            the sys
	 */
	@WebMethod
	ManagedSys addManagedSystem(
			@WebParam(name = "sys", targetNamespace = "")
			ManagedSys sys);
	
	
	/**
	 * Updates an existing managed system entry.
	 * 
	 * @param sys
	 *            the sys
	 */
	@WebMethod
	void updateManagedSystem(
			@WebParam(name = "sys", targetNamespace = "")
			ManagedSys sys);
	
	
	/**
	 * Removes a managed system entry from the system.
	 * 
	 * @param sysId
	 *            the sys id
	 */
	@WebMethod
	void removeManagedSystem(
			@WebParam(name = "sysId", targetNamespace = "")
			String sysId);
	
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
	@WebMethod
	public ManagedSystemObjectMatch[] managedSysObjectParam(
			@WebParam(name = "managedSystemId", targetNamespace = "")
			String managedSystemId, 
			@WebParam(name = "objectType", targetNamespace = "")
			String objectType);
	
	@WebMethod
	public void addManagedSystemObjectMatch(
			@WebParam(name = "obj", targetNamespace = "")
			ManagedSystemObjectMatch obj) ;
	
	@WebMethod
	public void updateManagedSystemObjectMatch(
			@WebParam(name = "obj", targetNamespace = "")
			ManagedSystemObjectMatch obj);

	@WebMethod
	public void removeManagedSystemObjectMatch(
			@WebParam(name = "obj", targetNamespace = "")
			ManagedSystemObjectMatch obj);
	
	// Approver Association
	

	/**
	 * Adds the approver association.
	 * 
	 * @param approverAssociation
	 *            the approver association
	 * @return the approver association
	 */
	@WebMethod
	ApproverAssociation addApproverAssociation(
			@WebParam(name = "approverAssociation", targetNamespace = "")
			ApproverAssociation approverAssociation);

	/**
	 * Updates approver association.
	 * 
	 * @param approverAssociation
	 *            the approver association
	 * @return the approver association
	 */
	@WebMethod
	ApproverAssociation updateApproverAssociation(
			@WebParam(name = "approverAssociation", targetNamespace = "")
			ApproverAssociation approverAssociation);

	/**
	 * Gets the approver association.
	 * 
	 * @param approverAssociationId
	 *            the approver association id
	 * @return the approver association
	 */
	@WebMethod
	ApproverAssociation getApproverAssociation(
			@WebParam(name = "approverAssociationId", targetNamespace = "")
			String approverAssociationId);

	/**
	 * Removes the approver association.
	 * 
	 * @param approverAssociationId
	 *            the approver association id
	 */
	@WebMethod
	void removeApproverAssociation(
			@WebParam(name = "approverAssociationId", targetNamespace = "")
			String approverAssociationId);

	/**
	 * Removes the all approver associations.
	 * 
	 * @return the int
	 */
	@WebMethod
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
	@WebMethod
	List<ApproverAssociation> getApproversByRequestType(
			@WebParam(name = "requestType", targetNamespace = "")
			String requestType,
			@WebParam(name = "level", targetNamespace = "")
			int level);


	@WebMethod
	List<ApproverAssociation> getAllApproversByRequestType(
			@WebParam(name = "requestId", targetNamespace = "")
			String requestType);
	/**
	 * Gets the approvers by object id.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @return the approvers by object id
	 */
	@WebMethod
	List<ApproverAssociation> getApproversByObjectId(
			@WebParam(name = "associationObjId", targetNamespace = "")
			String associationObjId);

	/**
	 * Removes the approvers by object id.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @return the int
	 */
	@WebMethod
	int removeApproversByObjectId(
			@WebParam(name = "associationObjId", targetNamespace = "")
			String associationObjId);

	/**
	 * Removes the approvers by object type.
	 * 
	 * @param associationType
	 *            the association type
	 * @return the int
	 */
	@WebMethod
	int removeApproversByObjectType(
			@WebParam(name = "associationType", targetNamespace = "")
			String associationType);

	/**
	 * Removes the approvers by user.
	 * 
	 * @param userId
	 *            the user id
	 * @return the int
	 */
	@WebMethod
	int removeApproversByUser(
			@WebParam(name = "userId", targetNamespace = "")
			String userId);
	

	/**
	 * Gets the attribute map.
	 * 
	 * @param attributeMapId
	 *            the attribute map id
	 * @return the attribute map
	 */
	@WebMethod
	AttributeMap getAttributeMap(
			@WebParam(name = "attributeMapId", targetNamespace = "")
			String attributeMapId) ;

	
	/**
	 * Adds the attribute map.
	 * 
	 * @param attributeMap
	 *            the attribute map
	 * @return the attribute map
	 */
	@WebMethod
	AttributeMap addAttributeMap(
			@WebParam(name = "attributeMap", targetNamespace = "")
			AttributeMap attributeMap) ;
	
	
	/**
	 * Updates attribute map.
	 * 
	 * @param attributeMap
	 *            the attribute map
	 * @return the attribute map
	 */
	@WebMethod
	AttributeMap updateAttributeMap(
			@WebParam(name = "attributeMap", targetNamespace = "")
			AttributeMap attributeMap) ;
	

	/**
	 * Removes the attribute map.
	 * 
	 * @param attributeMapId
	 *            the attribute map id
	 */
	@WebMethod
	void removeAttributeMap(
			@WebParam(name = "attributeMapId", targetNamespace = "")
			String attributeMapId) ;

	
	/**
	 * Removes the resource attribute maps.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the int
	 */
	@WebMethod
	int removeResourceAttributeMaps(
			@WebParam(name = "resourceId", targetNamespace = "")
			String resourceId) ;

	
	/**
	 * Return the AttributeMap for the specified resourceId.
	 * 
	 * @param resourceId
	 *            the resource id
	 * @return the attribute map for resource
	 */
	@WebMethod
	List<AttributeMap> getResourceAttributeMaps(
			@WebParam(name = "resourceId", targetNamespace = "")
			String resourceId);
	
	/**
	 * Gets the all attribute maps.
	 * 
	 * @return the all attribute maps
	 */
	@WebMethod
	List<AttributeMap> getAllAttributeMaps() ;
	
}
