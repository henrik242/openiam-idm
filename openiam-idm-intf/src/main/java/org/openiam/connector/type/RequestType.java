
package org.openiam.connector.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java class for RequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ExtensibleType">
 *       &lt;attribute name="requestID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="executionMode" type="{urn:oasis:names:tc:SPML:2:0}ExecutionModeType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseRequestType",
	propOrder = {
	    "requestID",
	    "executionMode",
	    "targetID",
	    "hostUrl",
	    "hostPort",
	    "hostLoginId",
	    "hostLoginPassword",
	    "baseDN"
	})
@XmlSeeAlso({
    UserRequest.class
})
public class RequestType   {

    @XmlElement(required = true)
    protected String requestID;
    protected String executionMode;
    protected String targetID;
    protected String hostUrl;
    protected String hostPort;
    protected String hostLoginId;
    protected String hostLoginPassword;
    protected String baseDN;

    public RequestType() {
    	
    }
    
    public RequestType(String requestID, String executionMode) {
    	this.requestID = requestID;
    	this.executionMode = executionMode;
    }
    
    /**
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the executionMode property.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionModeType }
     *     
     */
    public String getExecutionMode() {
        return executionMode;
    }

    /**
     * Sets the value of the executionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionModeType }
     *     
     */
    public void setExecutionMode(String value) {
        this.executionMode = value;
    }

	public String getTargetID() {
		return targetID;
	}

	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getHostPort() {
		return hostPort;
	}

	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public String getHostLoginId() {
		return hostLoginId;
	}

	public void setHostLoginId(String hostLoginId) {
		this.hostLoginId = hostLoginId;
	}

	public String getHostLoginPassword() {
		return hostLoginPassword;
	}

	public void setHostLoginPassword(String hostLoginPassword) {
		this.hostLoginPassword = hostLoginPassword;
	}

	public String getBaseDN() {
		return baseDN;
	}

	public void setBaseDN(String baseDN) {
		this.baseDN = baseDN;
	}

}
