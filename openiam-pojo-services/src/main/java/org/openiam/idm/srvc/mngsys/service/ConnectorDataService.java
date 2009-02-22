package org.openiam.idm.srvc.mngsys.service;

import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
/**
 * Service layer to manage the provisioning connectors in OpenIAM. <br>
 * Connectors are associated with a MetadataType. The MetadataType allows us to customize 
 * the attributes that are captured for a connector. Additionally, the type is often used to indicate
 *  end system such as 'Active Directory', 'LDAP', etc.
 * @author suneet
 * @version 2
 */
public interface ConnectorDataService {

	/**
	 * Adds a new connector to the system
	 * @param con
	 */
	void addConnector(ProvisionConnector con);
	/**
	 * Updates an existing connector in the system
	 * @param con
	 */
	void updateConnector(ProvisionConnector con);
	/**
	 * Removes a connector from the system
	 * @param conId
	 */
	void removeConnector(String conId);
	/**
	 * Returns a connector based on the connector id that has been provided.
	 * @param conId
	 * @return
	 */
	ProvisionConnector getConnector(String conId);
	/**
	 * Return an array containing all the connectors in the system
	 * @return
	 */
	ProvisionConnector[] getAllConnectors();
	/**
	 * Returns an array of connectors that belong to a particular type. A type would
	 * usually represent an end system such as LDAP, Active Directory, RDBMS.
	 * @param typeId
	 * @return
	 */
	ProvisionConnector[] getConnectorsByType(String typeId);
}
