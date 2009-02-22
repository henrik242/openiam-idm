
package org.openiam.idm.srvc.grp.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.grp.dto.GroupAttributeMap;


/**
 * <p>Java class for getAllAttributesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllAttributesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{urn:idm.openiam.org/srvc/grp/dto}groupAttributeMap" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllAttributesResponse", propOrder = {
    "_return"
})
public class GetAllAttributesResponse {

    @XmlElement(name = "return")
    protected GroupAttributeMap _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GroupAttributeMap }
     *     
     */
    public GroupAttributeMap getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupAttributeMap }
     *     
     */
    public void setReturn(GroupAttributeMap value) {
        this._return = value;
    }

}
