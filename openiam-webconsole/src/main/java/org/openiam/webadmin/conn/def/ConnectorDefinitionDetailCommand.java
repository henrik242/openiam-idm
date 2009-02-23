package org.openiam.webadmin.conn.def;

import java.io.Serializable;

import org.openiam.idm.srvc.menu.dto.Menu;

/**
 * Command object for the NewHireController
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
    private String className;
    
    
    
    public ConnectorDefinitionDetailCommand() {
    	
    }
	public ConnectorDefinitionDetailCommand(String className,
			String clientCommProtocol, String metadataTypeId, String name,
			String provConnectorId, String serviceUrl, String stdComplianceLevel) {
		super();
		this.className = className;
		this.clientCommProtocol = clientCommProtocol;
		this.metadataTypeId = metadataTypeId;
		this.name = name;
		this.provConnectorId = provConnectorId;
		this.serviceUrl = serviceUrl;
		this.stdComplianceLevel = stdComplianceLevel;
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
    

	

}
