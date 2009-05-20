
package org.openiam.idm.srvc.org.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.meta.dto.MetadataElement;


/**
 * <p>Java class for organizationAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organizationAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attrId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="metadataElement" type=""{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizationId" type=""{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "organizationAttribute", propOrder = {
    "attrId",
    "metadataElementId",
    "name",
    "organizationId",
    "value"
})
public class OrganizationAttribute implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -231974705360001659L;
	protected String attrId;
   // protected MetadataElement metadataElement;
    protected String metadataElementId;
    protected String name;
    protected String organizationId;
    protected String value;

	// Constructors

	/** default constructor */
	public OrganizationAttribute() {
	}

	/** minimal constructor */
	public OrganizationAttribute(String companyAttrId) {
		this.attrId = companyAttrId;
	}

	/** full constructor */
	public OrganizationAttribute(String companyAttrId,
			String metadataId, String organizationId, String name,
			String value) {
		this.attrId = companyAttrId;
		this.metadataElementId = metadataId;
		this.organizationId = organizationId;
		this.name = name;
		this.value = value;
	}

	// Property accessors
   
    /**
     * Gets the value of the attrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttrId() {
        return attrId;
    }

    /**
     * Sets the value of the attrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttrId(String value) {
        this.attrId = value;
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



    public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public String getMetadataElementId() {
		return metadataElementId;
	}

	public void setMetadataElementId(String metadataElementId) {
		this.metadataElementId = metadataElementId;
	}



}
