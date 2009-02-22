package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;

/**
 * Data access object interface for ProvisionConnector
 * @author suneet
 *
 */
public interface ProvisionConnectorDAO {

	/**
	 * Add a new connector to the system
	 * @param transientInstance
	 */
	public abstract void add(ProvisionConnector transientInstance);

	/**
	 * Remove a connector from the system
	 * @param persistentInstance
	 */
	public abstract void remove(ProvisionConnector persistentInstance);

	/**
	 * Updates an existing connector in the system
	 * @param detachedInstance
	 * @return
	 */
	public abstract ProvisionConnector update(
			ProvisionConnector detachedInstance);

	/**
	 * Finds a connector based on the object id
	 * @param id
	 * @return
	 */
	public abstract ProvisionConnector findById(java.lang.String id);

	/**
	 * Returns a list of all connectors
	 * @return
	 */
	public abstract List<ProvisionConnector> findAllConnectors();

	/**
	 * Returns a list of connectors based on their type.
	 * @param typeId
	 * @return
	 */
	public abstract List<ProvisionConnector> findConnectorByType(String typeId);

}