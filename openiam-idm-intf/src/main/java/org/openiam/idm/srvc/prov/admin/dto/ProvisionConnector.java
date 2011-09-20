package org.openiam.idm.srvc.prov.admin.dto;
// Generated Apr 19, 2008 11:35:32 PM by Hibernate Tools 3.2.0.b11


import java.util.HashSet;
import java.util.Set;

/**
 * Domain object to represent the Provisioning Connectors in the IAM system.
 */
public class ProvisionConnector  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 372601971380645119L;
	private String provConnectorId;
     private String name;
     private String metadataTypeId;
     private String stdComplianceLevel;
     private String clientCommProtocol;
     private String serviceUrl;
     private String className;
     private Set provisionConAttributes = new HashSet(0);

    public ProvisionConnector() {
    }

	
    public ProvisionConnector(String provConnectorId, String clientCommProtocol) {
        this.provConnectorId = provConnectorId;
        this.clientCommProtocol = clientCommProtocol;
    }
    public ProvisionConnector(String provConnectorId, String name, String metadataTypeId, String stdComplianceLevel, String clientCommProtocol, String serviceUrl, String className, Set provisionConAttributes) {
       this.provConnectorId = provConnectorId;
       this.name = name;
       this.metadataTypeId = metadataTypeId;
       this.stdComplianceLevel = stdComplianceLevel;
       this.clientCommProtocol = clientCommProtocol;
       this.serviceUrl = serviceUrl;
       this.className = className;
       this.provisionConAttributes = provisionConAttributes;
    }
   
    public String getProvConnectorId() {
        return this.provConnectorId;
    }
    
    public void setProvConnectorId(String provConnectorId) {
        this.provConnectorId = provConnectorId;
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
    
    public void setMetadataTypeId(String metadataTypeId) {
        this.metadataTypeId = metadataTypeId;
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
    public Set getProvisionConAttributes() {
        return this.provisionConAttributes;
    }
    
    public void setProvisionConAttributes(Set provisionConAttributes) {
        this.provisionConAttributes = provisionConAttributes;
    }




}


