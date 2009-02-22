package org.openiam.idm.srvc.mngsys.dto;


/**
 * ProvisionConnector represents a connector for provisioning.  
 */
public class ProvisionConnector implements java.io.Serializable {


	private static final long serialVersionUID = -6981651498633257018L;
	private String connectorId;
	private String name;
	private String metadataTypeId;
	private String stdComplianceLevel;
	private String clientCommProtocol;
	private String serviceUrl;
	private String className;

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

}
