
package org.openiam.idm.srvc.user.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userAttributeMap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userAttributeMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userAttributeEntry" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="userAttribute" type="{urn:idm.openiam.org/srvc/user/dto}userAttribute" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "userAttributeMap", propOrder = {
    "userAttributeEntry"
})
public class UserAttributeMap {

    protected List<UserAttributeMap.UserAttributeEntry> userAttributeEntry;

    /**
     * Gets the value of the userAttributeEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAttributeEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAttributeEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserAttributeMap.UserAttributeEntry }
     * 
     * 
     */
    public List<UserAttributeMap.UserAttributeEntry> getUserAttributeEntry() {
        if (userAttributeEntry == null) {
            userAttributeEntry = new ArrayList<UserAttributeMap.UserAttributeEntry>();
        }
        return this.userAttributeEntry;
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
     *         &lt;element name="userAttribute" type="{urn:idm.openiam.org/srvc/user/dto}userAttribute" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "userAttribute"
    })
    public static class UserAttributeEntry {

        protected UserAttribute userAttribute;
        @XmlAttribute
        protected String key;

        /**
         * Gets the value of the userAttribute property.
         * 
         * @return
         *     possible object is
         *     {@link UserAttribute }
         *     
         */
        public UserAttribute getUserAttribute() {
            return userAttribute;
        }

        /**
         * Sets the value of the userAttribute property.
         * 
         * @param value
         *     allowed object is
         *     {@link UserAttribute }
         *     
         */
        public void setUserAttribute(UserAttribute value) {
            this.userAttribute = value;
        }

        /**
         * Gets the value of the key property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets the value of the key property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKey(String value) {
            this.key = value;
        }

    }

}
