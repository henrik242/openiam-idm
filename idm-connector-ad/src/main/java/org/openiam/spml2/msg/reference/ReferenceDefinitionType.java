
package org.openiam.spml2.msg.reference;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.spml2.msg.ExtensibleType;
import org.openiam.spml2.msg.SchemaEntityRefType;


/**
 * <p>Java class for ReferenceDefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceDefinitionType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ExtensibleType">
 *       &lt;sequence>
 *         &lt;element name="schemaEntity" type="{urn:oasis:names:tc:SPML:2:0}SchemaEntityRefType"/>
 *         &lt;element name="canReferTo" type="{urn:oasis:names:tc:SPML:2:0}SchemaEntityRefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referenceDataType" type="{urn:oasis:names:tc:SPML:2:0}SchemaEntityRefType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="typeOfReference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceDefinitionType", propOrder = {
    "schemaEntity",
    "canReferTo",
    "referenceDataType"
})
public class ReferenceDefinitionType
    extends ExtensibleType
{

    @XmlElement(required = true)
    protected SchemaEntityRefType schemaEntity;
    protected List<SchemaEntityRefType> canReferTo;
    protected List<SchemaEntityRefType> referenceDataType;
    @XmlAttribute(required = true)
    protected String typeOfReference;

    /**
     * Gets the value of the schemaEntity property.
     * 
     * @return
     *     possible object is
     *     {@link SchemaEntityRefType }
     *     
     */
    public SchemaEntityRefType getSchemaEntity() {
        return schemaEntity;
    }

    /**
     * Sets the value of the schemaEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SchemaEntityRefType }
     *     
     */
    public void setSchemaEntity(SchemaEntityRefType value) {
        this.schemaEntity = value;
    }

    /**
     * Gets the value of the canReferTo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the canReferTo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCanReferTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchemaEntityRefType }
     * 
     * 
     */
    public List<SchemaEntityRefType> getCanReferTo() {
        if (canReferTo == null) {
            canReferTo = new ArrayList<SchemaEntityRefType>();
        }
        return this.canReferTo;
    }

    /**
     * Gets the value of the referenceDataType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceDataType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceDataType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchemaEntityRefType }
     * 
     * 
     */
    public List<SchemaEntityRefType> getReferenceDataType() {
        if (referenceDataType == null) {
            referenceDataType = new ArrayList<SchemaEntityRefType>();
        }
        return this.referenceDataType;
    }

    /**
     * Gets the value of the typeOfReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfReference() {
        return typeOfReference;
    }

    /**
     * Sets the value of the typeOfReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfReference(String value) {
        this.typeOfReference = value;
    }

}
