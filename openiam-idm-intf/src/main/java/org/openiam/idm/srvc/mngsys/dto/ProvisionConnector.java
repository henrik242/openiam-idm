package org.openiam.idm.srvc.mngsys.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * ProvisionConnector represents a connector for provisioning.  
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvisionConnector", propOrder = {
    "connectorId",
    "name",
    "metadataTypeId",
    "stdComplianceLevel",
    "clientCommProtocol",
    "serviceUrl",
    "className",
    "serviceNameSpace",
    "servicePort",
    "wsdlUrl",
    "connectorInterface"
})
public class ProvisionConnector implements java.io.Serializable {


	private static final long serialVersionUID = -6981651498633257018L;
	protected String connectorId;
	protected String name;
	protected String metadataTypeId;
	protected String stdComplianceLevel;
	protected String clientCommProtocol;
	protected String serviceUrl;
	protected String className;
	protected String serviceNameSpace;
	protected String servicePort;
	protected String wsdlUrl;
	protected String connectorInterface;
	

	public ProvisionConnector() {
	}

	public ProvisionConnector(String connectorId) {
		this.connectorId = connectorId;
	}

	public ProvisionConnector(String connectorId, String name,
			String metdataTypeId, String stdComplianceLevel,
			String clientCommProtocol, String serviceUrl, String className) {
		this.connectorId = connectorId;
		this.name = name;
		this.metadataTypeId = metdataTypeId;
		this.stdComplianceLevel = stdComplianceLevel;
		this.clientCommProtocol = clientCommProtocol;
		this.serviceUrl = serviceUrl;
		this.className = className;
	}

	public String getConnectorId() {
		return this.connectorId;
	}

	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMetadataTypeId() {
		return this.metadataTypeId;
	}

	public void setMetadataTypeId(String metdataTypeId) {
		this.metadataTypeId = metdataTypeId;
	}

	public String getStdComplianceLevel() {
		return this.stdComplianceLevel;
	}

	public void setStdComplianceLevel(String stdComplianceLevel) {
		this.stdComplianceLevel = stdComplianceLevel;
	}

	public String getClientCommProtocol() {
		return this.clientCommProtocol;
	}

	public void setClientCommProtocol(String clientCommProtocol) {
		this.clientCommProtocol = clientCommProtocol;
	}

	public String getServiceUrl() {
		return this.serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getServiceNameSpace() {
		return serviceNameSpace;
	}

	public void setServiceNameSpace(String serviceNameSpace) {
		this.serviceNameSpace = serviceNameSpace;
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	public String getConnectorInterface() {
		return connectorInterface;
	}

	public void setConnectorInterface(String connectorInterface) {
		this.connectorInterface = connectorInterface;
	}

}
