
package org.openiam.idm.srvc.user.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.continfo.dto.EmailAddress;


/**
 * <p>Java class for removeEmailAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeEmailAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{urn:idm.openiam.org/srvc/continfo/dto}emailAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeEmailAddress", propOrder = {
    "arg0"
})
public class RemoveEmailAddress {

    protected EmailAddress arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link EmailAddress }
     *     
     */
    public EmailAddress getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailAddress }
     *     
     */
    public void setArg0(EmailAddress value) {
        this.arg0 = value;
    }

}
