package org.openiam.idm.srvc.mngsys.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
/**
 * Service layer to manage the provisioning connectors in OpenIAM. <br>
 * Connectors are associated with a MetadataType. The MetadataType allows us to customize 
 * the attributes that are captured for a connector. Additionally, the type is often used to indicate
 *  end system such as 'Active Directory', 'LDAP', etc.
 * @author suneet
 * @version 2
 */
@WebService
public interface ConnectorDataService {

	/**
	 * Adds a new connector to the system
	 * @param con
	 */
	@WebMethod
	void addConnector(
			@WebParam(name = "con", targetNamespace = "")
			ProvisionConnector con);
	/**
	 * Updates an existing connector in the system
	 * @param con
	 */
	@WebMethod
	void updateConnector(
			@WebParam(name = "con", targetNamespace = "")
			ProvisionConnector con);
	/**
	 * Removes a connector from the system
	 * @param conId
	 */
	@WebMethod
	void removeConnector(
			@WebParam(name = "conId", targetNamespace = "")
			String conId);
	/**
	 * Returns a connector based on the connector id that has been provided.
	 * @param conId
	 * @return
	 */
	@WebMethod
	ProvisionConnector getConnector(
			@WebParam(name = "conId", targetNamespace = "")
			String conId);
	/**
	 * Return an array containing all the connectors in the system
	 * @return
	 */
	@WebMethod
	ProvisionConnector[] getAllConnectors();
	/**
	 * Returns an array of connectors that belong to a particular type. A type would
	 * usually represent an end system such as LDAP, Active Directory, RDBMS.
	 * @param typeId
	 * @return
	 */
	@WebMethod
	ProvisionConnector[] getConnectorsByType(
			@WebParam(name = "typeId", targetNamespace = "")
			String typeId);
}
