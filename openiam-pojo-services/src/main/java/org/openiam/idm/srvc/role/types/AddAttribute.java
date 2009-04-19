
package org.openiam.idm.srvc.role.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.role.dto.RoleAttribute;


/**
 * <p>Java class for addAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roleAttribute" type="{urn:idm.openiam.org/srvc/role/dto}roleAttribute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addAttribute", propOrder = {
    "roleAttribute"
})
public class AddAttribute {

    protected RoleAttribute roleAttribute;

    /**
     * Gets the value of the roleAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link RoleAttribute }
     *     
     */
    public RoleAttribute getRoleAttribute() {
        return roleAttribute;
    }

    /**
     * Sets the value of the roleAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleAttribute }
     *     
     */
    public void setRoleAttribute(RoleAttribute value) {
        this.roleAttribute = value;
    }

}
