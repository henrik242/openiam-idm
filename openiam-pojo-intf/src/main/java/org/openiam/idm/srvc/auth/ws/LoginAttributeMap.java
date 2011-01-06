
package org.openiam.idm.srvc.auth.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.openiam.idm.srvc.auth.dto.LoginAttribute;


/**
 * <p>Java class for organizationAttributeMap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organizationAttributeMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="organizationAttributeEntry" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="organizationAttribute" type="{urn:idm.openiam.org/srvc/org/dto}organizationAttribute" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "LoginAttributeMap", propOrder = {
    "loginAttributeEntry"
})
public class LoginAttributeMap {

    protected List<LoginAttributeMap.LoginAttributeEntry> loginAttributeEntry;

    /**
     * Gets the value of the organizationAttributeEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organizationAttributeEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganizationAttributeEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoginAttributeMap.OrganizationAttributeEntry }
     * 
     * 
     */
    public List<LoginAttributeMap.LoginAttributeEntry> getLoginAttributeEntry() {
        if (loginAttributeEntry == null) {
        	loginAttributeEntry = new ArrayList<LoginAttributeMap.LoginAttributeEntry>();
        }
        return this.loginAttributeEntry;
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
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "loginAttribute"
    })
    public static class LoginAttributeEntry {

        protected LoginAttribute loginAttribute;
        @XmlAttribute
        protected String id;

        /**
         * Gets the value of the organizationAttribute property.
         * 
         * @return
         *     possible object is
         *     {@link OrganizationAttribute }
         *     
         */
        public LoginAttribute getLoginAttribute() {
            return loginAttribute;
        }

        /**
         * Sets the value of the organizationAttribute property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrganizationAttribute }
         *     
         */
        public void setLoginAttribute(LoginAttribute value) {
            this.loginAttribute = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

    }

}
