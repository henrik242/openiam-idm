package org.openiam.idm.srvc.org.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * Java class for organization complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;organization&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;alias&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;attributes&quot; type=&quot;{urn:idm.openiam.org/srvc/org/dto}organizationAttributes&quot;/&gt;
 *         &lt;element name=&quot;createDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;createdBy&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;description&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;domainName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;ldapStr&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;lstUpdate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;lstUpdatedBy&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;metadataType&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;orgId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;organizationName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;parentId&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;status&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organization", propOrder = { "alias", "attributes",
		"createDate", "createdBy", "description", "domainName", "ldapStr",
		"lstUpdate", "lstUpdatedBy", "metadataTypeId", "orgId",
		"organizationName", "parentId", "status" })
public class Organization implements java.io.Serializable {

	protected String alias;
	@XmlJavaTypeAdapter(OrganizationAttributeMapAdapter.class)
	protected Map<String,org.openiam.idm.srvc.org.dto.OrganizationAttribute> attributes;
	@XmlSchemaType(name = "dateTime")
	protected Date createDate;
	protected String createdBy;
	protected String description;
	protected String domainName;
	protected String ldapStr;
	@XmlSchemaType(name = "dateTime")
	protected Date lstUpdate;
	protected String lstUpdatedBy;
	protected String metadataTypeId;
	protected String orgId;
	protected String organizationName;
	protected String parentId;
	protected String status;

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(String companyId) {
		this.orgId = companyId;
	}

	/** full constructor */
	public Organization(String companyId, String metadataTypeId,
			String companyName, Date lstUpdate, String lstUpdatedBy,
			String parentId, String status, Date createDate, String createdBy,
			String alias, String description, String domainName,
			String ldapStr, Map<String,OrganizationAttribute> companyAttributes) {
		this.orgId = companyId;
		this.metadataTypeId = metadataTypeId;
		this.organizationName = companyName;
		this.lstUpdate = lstUpdate;
		this.lstUpdatedBy = lstUpdatedBy;
		this.parentId = parentId;
		this.status = status;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.alias = alias;
		this.description = description;
		this.domainName = domainName;
		this.ldapStr = ldapStr;
		this.attributes = companyAttributes;
	}

	// Property accessors
	
	/**
	 * Gets the value of the alias property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the value of the alias property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAlias(String value) {
		this.alias = value;
	}

	/**
	 * Gets the value of the attributes property.
	 * 
	 * @return possible object is {@link OrganizationAttributes }
	 * 
	 */
	public Map<String,org.openiam.idm.srvc.org.dto.OrganizationAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the value of the attributes property.
	 * 
	 * @param value
	 *            allowed object is {@link OrganizationAttributes }
	 * 
	 */
	public void setAttributes(Map<String,org.openiam.idm.srvc.org.dto.OrganizationAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the value of the createDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreateDate(Date value) {
		this.createDate = value;
	}

	/**
	 * Gets the value of the createdBy property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the value of the createdBy property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatedBy(String value) {
		this.createdBy = value;
	}

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Gets the value of the domainName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * Sets the value of the domainName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDomainName(String value) {
		this.domainName = value;
	}

	/**
	 * Gets the value of the ldapStr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLdapStr() {
		return ldapStr;
	}

	/**
	 * Sets the value of the ldapStr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLdapStr(String value) {
		this.ldapStr = value;
	}

	/**
	 * Gets the value of the lstUpdate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getLstUpdate() {
		return lstUpdate;
	}

	/**
	 * Sets the value of the lstUpdate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLstUpdate(Date value) {
		this.lstUpdate = value;
	}

	/**
	 * Gets the value of the lstUpdatedBy property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLstUpdatedBy() {
		return lstUpdatedBy;
	}

	/**
	 * Sets the value of the lstUpdatedBy property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLstUpdatedBy(String value) {
		this.lstUpdatedBy = value;
	}

	/**
	 * Gets the value of the metadataType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMetadataTypeId() {
		return metadataTypeId;
	}

	/**
	 * Sets the value of the metadataType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMetadataTypeId(String value) {
		this.metadataTypeId = value;
	}

	/**
	 * Gets the value of the orgId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * Sets the value of the orgId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgId(String value) {
		this.orgId = value;
	}

	/**
	 * Gets the value of the organizationName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * Sets the value of the organizationName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrganizationName(String value) {
		this.organizationName = value;
	}

	/**
	 * Gets the value of the parentId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * Sets the value of the parentId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParentId(String value) {
		this.parentId = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

}
