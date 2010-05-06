package org.openiam.webadmin.conn.def;

import java.io.Serializable;


/**
 * Command object for the ConnectorDefinitionController
 * @author suneet
 *
 */
public class ConnectorDefinitionDetailCommand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5516615530449090321L;
	private String provConnectorId;
    private String name;
    private String metadataTypeId;
    private String stdComplianceLevel;
    private String clientCommProtocol;
    private String serviceUrl;
    private String serviceNameSpace;
    private String className;
    private String servicePort;
    
    
    
    public ConnectorDefinitionDetailCommand() {
    	
    }
	public ConnectorDefinitionDetailCommand(String className,
			String clientCommProtocol, String metadataTypeId, String name,
			String provConnectorId, String serviceUrl, String stdComplianceLevel,
			String nameSpace,
			String servicePort) {
		super();
		this.className = className;
		this.clientCommProtocol = clientCommProtocol;
		this.metadataTypeId = metadataTypeId;
		this.name = name;
		this.provConnectorId = provConnectorId;
		this.serviceUrl = serviceUrl;
		this.stdComplianceLevel = stdComplianceLevel;
		serviceNameSpace = nameSpace;
		this.servicePort = servicePort;
		
	}
	
	public String getProvConnectorId() {
		return provConnectorId;
	}
	public void setProvConnectorId(String provConnectorId) {
		this.provConnectorId = provConnectorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMetadataTypeId() {
		return metadataTypeId;
	}
	public void setMetadataTypeId(String metadataTypeId) {
		this.metadataTypeId = metadataTypeId;
	}
	public String getStdComplianceLevel() {
		return stdComplianceLevel;
	}
	public void setStdComplianceLevel(String stdComplianceLevel) {
		this.stdComplianceLevel = stdComplianceLevel;
	}
	public String getClientCommProtocol() {
		return clientCommProtocol;
	}
	public void setClientCommProtocol(String clientCommProtocol) {
		this.clientCommProtocol = clientCommProtocol;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getClassName() {
		return className;
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
	public String getServicePort() {
		return servicePort;
	}
	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}
    

	

}
