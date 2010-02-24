package org.openiam.idm.srvc.prov.admin.dto;




/**
 * Object to represent the attributes of the  Provisioning Connector in the IAM system.
 */
public class ProvisionConAttribute  implements java.io.Serializable {


     private String provConAttrId;
     private ProvisionConnector provisionConnector;
     private String name;
     private String value;
     private String metadataId;

    public ProvisionConAttribute() {
    }

	
    public ProvisionConAttribute(String provConAttrId, ProvisionConnector provisionConnector) {
        this.provConAttrId = provConAttrId;
        this.provisionConnector = provisionConnector;
    }
    public ProvisionConAttribute(String provConAttrId, ProvisionConnector provisionConnector, String name, String value, String metadataId) {
       this.provConAttrId = provConAttrId;
       this.provisionConnector = provisionConnector;
       this.name = name;
       this.value = value;
       this.metadataId = metadataId;
    }
   
    public String getProvConAttrId() {
        return this.provConAttrId;
    }
    
    public void setProvConAttrId(String provConAttrId) {
        this.provConAttrId = provConAttrId;
    }
    public ProvisionConnector getProvisionConnector() {
        return this.provisionConnector;
    }
    
    public void setProvisionConnector(ProvisionConnector provisionConnector) {
        this.provisionConnector = provisionConnector;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public String getMetadataId() {
        return this.metadataId;
    }
    
    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }




}


