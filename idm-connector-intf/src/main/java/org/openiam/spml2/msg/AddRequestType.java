
package org.openiam.spml2.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
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
@XmlType(name = "AddRequestType", propOrder = {
    "psoID",
    "containerID",
    "data",
    "capabilityData",
    "targetID",
    "returnData"
})
public class AddRequestType   extends RequestType
{

    protected PSOIdentifierType psoID;
    protected PSOIdentifierType containerID;
    @XmlElement(required = true)
    protected ExtensibleType data = new ExtensibleType();
    protected List<CapabilityDataType> capabilityData = new ArrayList<CapabilityDataType>(0);
    @XmlAttribute
    protected String targetID;
    @XmlAttribute
    protected ReturnDataType returnData;

    public AddRequestType() {} ;

    public AddRequestType(String requestId,
                      ExecutionModeType executionMode,
                      PSOIdentifierType type,
                      PSOIdentifierType containerID,
                      ExtensibleType data,
                      CapabilityDataType[] capabilityData,
                      String targetId,
                      ReturnDataType returnData) {
        super(requestId, executionMode);
        this.psoID = type;
        this.containerID = containerID;

        assert (this.data != null);
        this.data = data;

        if (capabilityData != null) {
        	this.capabilityData.addAll(Arrays.asList(capabilityData));
        }

    }
    
    
    /**
     * Gets the value of the psoID property.
     * 
     * @return
     *     possible object is
     *     {@link PSOIdentifierType }
     *     
     */
    public PSOIdentifierType getPsoID() {
        return psoID;
    }

    /**
     * Sets the value of the psoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link PSOIdentifierType }
     *     
     */
    public void setPsoID(PSOIdentifierType value) {
        this.psoID = value;
    }

    /**
     * Gets the value of the containerID property.
     * 
     * @return
     *     possible object is
     *     {@link PSOIdentifierType }
     *     
     */
    public PSOIdentifierType getContainerID() {
        return containerID;
    }

    /**
     * Sets the value of the containerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link PSOIdentifierType }
     *     
     */
    public void setContainerID(PSOIdentifierType value) {
        this.containerID = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensibleType }
     *     
     */
    public ExtensibleType getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensibleType }
     *     
     */
    public void setData(ExtensibleType value) {
        this.data = value;
    }

    /**
     * Gets the value of the capabilityData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the capabilityData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCapabilityData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CapabilityDataType }
     * 
     * 
     */
    public List<CapabilityDataType> getCapabilityData() {
        if (capabilityData == null) {
            capabilityData = new ArrayList<CapabilityDataType>();
        }
        return this.capabilityData;
    }

    /**
     * Gets the value of the targetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetID() {
        return targetID;
    }

    /**
     * Sets the value of the targetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetID(String value) {
        this.targetID = value;
    }

    /**
     * Gets the value of the returnData property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnDataType }
     *     
     */
    public ReturnDataType getReturnData() {
        if (returnData == null) {
            return ReturnDataType.EVERYTHING;
        } else {
            return returnData;
        }
    }

    /**
     * Sets the value of the returnData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnDataType }
     *     
     */
    public void setReturnData(ReturnDataType value) {
        this.returnData = value;
    }

}
