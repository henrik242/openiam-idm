
package org.openiam.idm.srvc.org.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for organizationAttributeSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organizationAttributeSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="organizationAttributeObj" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="organizationAttribute" type="{urn:idm.openiam.org/srvc/org/dto}organizationAttribute" minOccurs="0"/>
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
@XmlType(name = "organizationAttributeSet", propOrder = {
    "organizationAttributeObj"
})
public class OrganizationAttributeSet {

    protected List<OrganizationAttributeSet.OrganizationAttributeObj> organizationAttributeObj;

    /**
     * Gets the value of the organizationAttributeObj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organizationAttributeObj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganizationAttributeObj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganizationAttributeSet.OrganizationAttributeObj }
     * 
     * 
     */
    public List<OrganizationAttributeSet.OrganizationAttributeObj> getOrganizationAttributeObj() {
        if (organizationAttributeObj == null) {
            organizationAttributeObj = new ArrayList<OrganizationAttributeSet.OrganizationAttributeObj>();
        }
        return this.organizationAttributeObj;
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
    public static class OrganizationAttributeObj {

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

}
