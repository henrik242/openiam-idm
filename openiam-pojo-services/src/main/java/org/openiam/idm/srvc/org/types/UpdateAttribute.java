
package org.openiam.idm.srvc.org.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.org.dto.OrganizationAttribute;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="organizationAttribute" type="{urn:idm.openiam.org/srvc/org/dto}organizationAttribute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "organizationAttribute"
})
@XmlRootElement(name = "updateAttribute")
public class UpdateAttribute {

    protected OrganizationAttribute organizationAttribute;

    /**
     * Gets the value of the organizationAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationAttribute }
     *     
     */
    public OrganizationAttribute getOrganizationAttribute() {
        return organizationAttribute;
    }

    /**
     * Sets the value of the organizationAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationAttribute }
     *     
     */
    public void setOrganizationAttribute(OrganizationAttribute value) {
        this.organizationAttribute = value;
    }

}
