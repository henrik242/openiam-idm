
package org.openiam.idm.srvc.user.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userAttributeSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userAttributeSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userAttributeObj" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="userAttribute" type="{urn:idm.openiam.org/srvc/user/dto}userAttribute" minOccurs="0"/>
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
@XmlType(name = "userAttributeSet", propOrder = {
    "userAttributeObj"
})
public class UserAttributeSet {

    protected List<UserAttributeSet.UserAttributeObj> userAttributeObj;

    /**
     * Gets the value of the userAttributeObj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAttributeObj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAttributeObj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserAttributeSet.UserAttributeObj }
     * 
     * 
     */
    public List<UserAttributeSet.UserAttributeObj> getUserAttributeObj() {
        if (userAttributeObj == null) {
            userAttributeObj = new ArrayList<UserAttributeSet.UserAttributeObj>();
        }
        return this.userAttributeObj;
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
    public static class UserAttributeObj {

        protected UserAttribute userAttribute;

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

    }

}
