
package org.openiam.idm.srvc.role.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="roleAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadataId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleAttrId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "roleAttribute", propOrder = {
    "metadataElementId",
    "name",
    "roleAttrId",
    "roleId",
    "serviceId",
    "value"
})
public class RoleAttribute implements java.io.Serializable {

    protected String metadataElementId;
    protected String name;
    protected String roleAttrId;
    protected String roleId;
    protected String serviceId;
    protected String value;

    public RoleAttribute() {
    }

	
    public RoleAttribute(String roleAttrId, String serviceId, String roleId) {
        this.roleAttrId = roleAttrId;
        this.serviceId = serviceId;
        this.roleId = roleId;
    }
    public RoleAttribute(String roleAttrId, String serviceId, String roleId, String name, String value, String metadataTypeId) {
       this.roleAttrId = roleAttrId;
       this.serviceId = serviceId;
       this.roleId = roleId;
       this.name = name;
       this.value = value;
       this.metadataElementId = metadataTypeId;
    }
   
    /**
     * Gets the value of the metadataId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetadataElementId() {
        return metadataElementId;
    }

    /**
     * Sets the value of the metadataId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetadataElementId(String value) {
        this.metadataElementId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the roleAttrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleAttrId() {
        return roleAttrId;
    }

    /**
     * Sets the value of the roleAttrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleAttrId(String value) {
        this.roleAttrId = value;
    }

    /**
     * Gets the value of the roleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the value of the roleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleId(String value) {
        this.roleId = value;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
