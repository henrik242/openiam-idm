
package org.openiam.connector.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openiam.provision.type.ExtensibleUser;

 
/*
 * <p>Java class for AddRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}RequestType">
 *       &lt;sequence>
 *         &lt;element name="psoID" type="{urn:oasis:names:tc:SPML:2:0}PSOIdentifierType" minOccurs="0"/>
 *         &lt;element name="containerID" type="{urn:oasis:names:tc:SPML:2:0}PSOIdentifierType" minOccurs="0"/>
 *         &lt;element name="data" type="{urn:oasis:names:tc:SPML:2:0}ExtensibleType"/>
 *         &lt;element name="capabilityData" type="{urn:oasis:names:tc:SPML:2:0}CapabilityDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="targetID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="returnData" type="{urn:oasis:names:tc:SPML:2:0}ReturnDataType" default="everything" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserRequest", propOrder = {
    "userIdentity",
    "containerID",
    "operation",
    "user"
})
public class UserRequest   extends RequestType
{

    protected String userIdentity;
    protected String containerID;
 
    /* Change to an enum */
    protected String operation;

    protected ExtensibleUser user;

    public UserRequest() {}
    
    
    

	public UserRequest(String userIdentity, String containerID,
			String targetID, String hostUrl, String hostPort,
			String hostLoginId, String hostLoginPassword, String operation,
			ExtensibleUser user) {
		super();
		this.userIdentity = userIdentity;
		this.containerID = containerID;
		this.targetID = targetID;
		this.hostUrl = hostUrl;
		this.hostPort = hostPort;
		this.hostLoginId = hostLoginId;
		this.hostLoginPassword = hostLoginPassword;
		this.operation = operation;
		this.user = user;
	}




	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}



	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public ExtensibleUser getUser() {
		return user;
	}

	public void setUser(ExtensibleUser user) {
		this.user = user;
	} ;

   
    
  
}
