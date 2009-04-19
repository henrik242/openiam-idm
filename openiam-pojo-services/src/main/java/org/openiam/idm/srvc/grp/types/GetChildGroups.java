
package org.openiam.idm.srvc.grp.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getChildGroups complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getChildGroups">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parentGroupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subgroups" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getChildGroups", propOrder = {
    "parentGroupId",
    "subgroups"
})
public class GetChildGroups {

    protected String parentGroupId;
    protected boolean subgroups;

    /**
     * Gets the value of the parentGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentGroupId() {
        return parentGroupId;
    }

    /**
     * Sets the value of the parentGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentGroupId(String value) {
        this.parentGroupId = value;
    }

    /**
     * Gets the value of the subgroups property.
     * 
     */
    public boolean isSubgroups() {
        return subgroups;
    }

    /**
     * Sets the value of the subgroups property.
     * 
     */
    public void setSubgroups(boolean value) {
        this.subgroups = value;
    }

}
