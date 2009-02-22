
package org.openiam.idm.srvc.grp.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;


/**
 * <p>Java class for removeAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attribute" type="{urn:idm.openiam.org/srvc/grp/dto}groupAttribute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeAttribute", propOrder = {
    "attribute"
})
public class RemoveAttribute {

    protected GroupAttribute attribute;

    /**
     * Gets the value of the attribute property.
     * 
     * @return
     *     possible object is
     *     {@link GroupAttribute }
     *     
     */
    public GroupAttribute getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupAttribute }
     *     
     */
    public void setAttribute(GroupAttribute value) {
        this.attribute = value;
    }

}
