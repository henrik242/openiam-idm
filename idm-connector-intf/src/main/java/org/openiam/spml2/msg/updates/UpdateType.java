
package org.openiam.spml2.msg.updates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.PSOIdentifierType;


/**
 * <p>Java class for UpdateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ExtensibleType">
 *       &lt;sequence>
 *         &lt;element name="psoID" type="{urn:oasis:names:tc:SPML:2:0}PSOIdentifierType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="updateKind" use="required" type="{urn:oasis:names:tc:SPML:2:0:updates}UpdateKindType" />
 *       &lt;attribute name="wasUpdatedByCapability" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateType", propOrder = {
    "psoID"
})
public class UpdateType
    extends ExtensibleType
{

    @XmlElement(required = true)
    protected PSOIdentifierType psoID;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlAttribute(required = true)
    protected UpdateKindType updateKind;
    @XmlAttribute
    protected String wasUpdatedByCapability;

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
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the updateKind property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateKindType }
     *     
     */
    public UpdateKindType getUpdateKind() {
        return updateKind;
    }

    /**
     * Sets the value of the updateKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateKindType }
     *     
     */
    public void setUpdateKind(UpdateKindType value) {
        this.updateKind = value;
    }

    /**
     * Gets the value of the wasUpdatedByCapability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWasUpdatedByCapability() {
        return wasUpdatedByCapability;
    }

    /**
     * Sets the value of the wasUpdatedByCapability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWasUpdatedByCapability(String value) {
        this.wasUpdatedByCapability = value;
    }

}
