
package org.openiam.idm.srvc.role.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleAttributeSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="roleAttributeSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roleAttributeObj" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="roleAttribute" type="{urn:idm.openiam.org/srvc/role/dto}roleAttribute" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "roleAttributeSet", propOrder = {
    "roleAttributeObj"
})
public class RoleAttributeSet {

    protected List<RoleAttributeSet.RoleAttributeObj> roleAttributeObj;

    /**
     * Gets the value of the roleAttributeObj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleAttributeObj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleAttributeObj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleAttributeSet.RoleAttributeObj }
     * 
     * 
     */
    public List<RoleAttributeSet.RoleAttributeObj> getRoleAttributeObj() {
        if (roleAttributeObj == null) {
            roleAttributeObj = new ArrayList<RoleAttributeSet.RoleAttributeObj>();
        }
        return this.roleAttributeObj;
    }


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
    @XmlType(name = "", propOrder = {
        "roleAttribute"
    })
    public static class RoleAttributeObj {

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

}
