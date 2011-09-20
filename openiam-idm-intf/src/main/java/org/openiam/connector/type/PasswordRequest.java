
package org.openiam.connector.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PasswordRequest complex type.
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordRequest", propOrder = {
    "userIdentity",
    "operation",
    "password",
    "currentPassword"
})
public class PasswordRequest extends RequestType  {

    @XmlElement(required = true)    
    protected String userIdentity;

    /* Change to an enum */
    protected String operation;
    
    
    @XmlElement(required = true)
    protected String password;
    protected String currentPassword;


    public PasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordRequest(String currentPassword, String password,
			String userIdentity) {
		super();
		this.currentPassword = currentPassword;
		this.password = password;
		this.userIdentity = userIdentity;
	}


    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the currentPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * Sets the value of the currentPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentPassword(String value) {
        this.currentPassword = value;
    }

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
