package org.openiam.idm.srvc.auth.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
// Generated Feb 18, 2008 3:56:06 PM by Hibernate Tools 3.2.0.b11



/**
 * Attributes of a Login object.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginAttribute", propOrder = {
		"loginAttrId",
		"domainId",
		"login",
		"name",
		"value",
		"metadataId",
		"attrGroup"
})
public class LoginAttribute  implements java.io.Serializable {


     protected String loginAttrId;
     protected String domainId;
     protected String login;
     
     protected String name;
     protected String value;
     protected String metadataId;
     protected String attrGroup;


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
       this.domainId = serviceId;
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


	public String getDomainId() {
		return domainId;
	}


	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}




}


