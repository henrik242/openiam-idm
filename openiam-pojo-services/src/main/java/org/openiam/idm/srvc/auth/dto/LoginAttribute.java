package org.openiam.idm.srvc.auth.dto;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11



/**
 * Attributes of a Login object.
 */
public class LoginAttribute  implements java.io.Serializable {


     private String loginAttrId;
    // private Login login;
     private String serviceId;
     private String login;
     
     private String name;
     private String value;
     private String metadataId;
     private String attrGroup;


    public LoginAttribute() {
    }

	
    public LoginAttribute(String loginAttrId) {
        this.loginAttrId = loginAttrId;
    }
    public LoginAttribute(String loginAttrId, String name, String value, String metadataId,
    		String serviceId, String login, String attrGroup) {
       this.loginAttrId = loginAttrId;
       this.name = name;
       this.value = value;
       this.metadataId = metadataId;
       this.serviceId = serviceId;
       this.login = login;
       this.attrGroup = attrGroup;
    }
   
    public String getLoginAttrId() {
        return this.loginAttrId;
    }
    
    public void setLoginAttrId(String loginAttrId) {
        this.loginAttrId = loginAttrId;
    }

    public String getName() {
        return this.name;
    }
    
    public String getServiceId() {
		return serviceId;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
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


	public String getAttrGroup() {
		return attrGroup;
	}


	public void setAttrGroup(String attrGroup) {
		this.attrGroup = attrGroup;
	}




}


