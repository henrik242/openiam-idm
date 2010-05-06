package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.mngsys.dto.ApproverAssociation;

public interface ApproverAssociationDAO {

	/**
	 * Sets the session factory.
	 * 
	 * @param session
	 *            the new session factory
	 */
	void setSessionFactory(SessionFactory session);

	/**
	 * Adds the.
	 * 
	 * @param transientInstance
	 *            the transient instance
	 * @return the approver association
	 */
	ApproverAssociation add(ApproverAssociation transientInstance);

	/**
	 * Removes the.
	 * 
	 * @param persistentInstance
	 *            the persistent instance
	 */
	void remove(ApproverAssociation persistentInstance);

	/**
	 * Updates.
	 * 
	 * @param detachedInstance
	 *            the detached instance
	 * @return the approver association
	 */
	ApproverAssociation update(ApproverAssociation detachedInstance);

	/**
	 * Finds by id.
	 * 
	 * @param id
	 *            the id
	 * @return the approver association
	 */
	ApproverAssociation findById(java.lang.String id);

	// e.g NEW_HIRE
	/**
	 * Finds approvers by request type.
	 * 
	 * @param requestType
	 *            the request type
	 * @param level
	 *            the level
	 * @return the list
	 */
	List<ApproverAssociation> findApproversByRequestType(String requestType,
			int level);

	/**
	 * Removes the approvers by request type.
	 * 
	 * @param requestType
	 *            the request type
	 * @return the int
	 */
	int removeApproversByRequestType(String requestType);

	// RESOURCE_ID
	/**
	 * Finds approvers by object id.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @return the list
	 */
	List<ApproverAssociation> findApproversByObjectId(String associationObjId);

	/**
	 * Removes the approvers by object id.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @return the int
	 */
	int removeApproversByObjectId(String associationObjId);

	// find by RESOURCE, GROUP, ROLE, SUPERVISOR,INDIVIDUAL
	/**
	 * Finds approvers by object type.
	 * 
	 * @param associationType
	 *            the association type
	 * @return the list
	 */
	List<ApproverAssociation> findApproversByObjectType(String associationType);

	/**
	 * Removes the approvers by object type.
	 * 
	 * @param associationType
	 *            the association type
	 * @return the int
	 */
	int removeApproversByObjectType(String associationType);

	/**
	 * Finds approvers by action.
	 * 
	 * @param associationObjId
	 *            the association obj id
	 * @param action
	 *            the action
	 * @param level
	 *            the level
	 * @return the list
	 */
	List<ApproverAssociation> findApproversByAction(String associationObjId,
			String action, int level);

	/**
	 * Finds approvers by user.
	 * 
	 * @param userId
	 *            the user id
	 * @return the list
	 */
	List<ApproverAssociation> findApproversByUser(String userId);

	/**
	 * Removes the approvers by user.
	 * 
	 * @param userId
	 *            the user id
	 * @return the int
	 */
	int removeApproversByUser(String userId);

	/**
	 * Removes the all approvers.
	 * 
	 * @return the int
	 */
	int removeAllApprovers();

}