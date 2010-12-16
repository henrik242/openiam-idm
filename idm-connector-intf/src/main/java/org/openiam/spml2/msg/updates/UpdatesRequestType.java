
package org.openiam.spml2.msg.updates;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.openiam.spml2.msg.RequestType;
import org.openiam.spml2.msg.search.SearchQueryType;


/**
 * <p>Java class for UpdatesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatesRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}RequestType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SPML:2:0:search}query" minOccurs="0"/>
 *         &lt;element name="updatedByCapability" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="updatedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="token" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="maxSelect" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatesRequestType", propOrder = {
    "query",
    "updatedByCapability"
})
public class UpdatesRequestType
    extends RequestType
{

    @XmlElement(namespace = "urn:oasis:names:tc:SPML:2:0:search")
    protected SearchQueryType query;
    protected List<String> updatedByCapability;
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedSince;
    @XmlAttribute
    protected String token;
    @XmlAttribute
    protected Integer maxSelect;

    /**
     * Gets the value of the query property.
     * 
     * @return
     *     possible object is
     *     {@link SearchQueryType }
     *     
     */
    public SearchQueryType getQuery() {
        return query;
    }

    /**
     * Sets the value of the query property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchQueryType }
     *     
     */
    public void setQuery(SearchQueryType value) {
        this.query = value;
    }

    /**
     * Gets the value of the updatedByCapability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the updatedByCapability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdatedByCapability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUpdatedByCapability() {
        if (updatedByCapability == null) {
            updatedByCapability = new ArrayList<String>();
        }
        return this.updatedByCapability;
    }

    /**
     * Gets the value of the updatedSince property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedSince() {
        return updatedSince;
    }

    /**
     * Sets the value of the updatedSince property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedSince(XMLGregorianCalendar value) {
        this.updatedSince = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the maxSelect property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxSelect() {
        return maxSelect;
    }

    /**
     * Sets the value of the maxSelect property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxSelect(Integer value) {
        this.maxSelect = value;
    }

}
