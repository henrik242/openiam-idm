package org.openiam.spml2.msg.bulk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.spml2.msg.RequestType;
import org.openiam.spml2.msg.search.SearchQueryType;


/**
 * <p>Java class for BulkDeleteRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkDeleteRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}RequestType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SPML:2:0:search}query"/>
 *       &lt;/sequence>
 *       &lt;attribute name="recursive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkDeleteRequestType", propOrder = {
    "query"
})
public class BulkDeleteRequestType
    extends RequestType
{

    @XmlElement(namespace = "urn:oasis:names:tc:SPML:2:0:search", required = true)
    protected SearchQueryType query;
    @XmlAttribute
    protected Boolean recursive;

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
     * Gets the value of the recursive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecursive() {
        return recursive;
    }

    /**
     * Sets the value of the recursive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecursive(Boolean value) {
        this.recursive = value;
    }

}
