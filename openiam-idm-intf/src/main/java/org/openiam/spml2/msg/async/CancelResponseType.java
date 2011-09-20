package org.openiam.spml2.msg.async;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.openiam.spml2.msg.ResponseType;


/**
 * <p>Java class for CancelResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CancelResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ResponseType">
 *       &lt;attribute name="asyncRequestID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelResponseType")
public class CancelResponseType
    extends ResponseType
{

    @XmlAttribute(required = true)
    protected String asyncRequestID;

    /**
     * Gets the value of the asyncRequestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsyncRequestID() {
        return asyncRequestID;
    }

    /**
     * Sets the value of the asyncRequestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsyncRequestID(String value) {
        this.asyncRequestID = value;
    }

}
