
package org.openiam.spml2.msg.bulk;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.spml2.msg.ModificationType;
import org.openiam.spml2.msg.RequestType;
import org.openiam.spml2.msg.search.SearchQueryType;


/**
 * <p>Java class for BulkModifyRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkModifyRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}RequestType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SPML:2:0:search}query"/>
 *         &lt;element name="modification" type="{urn:oasis:names:tc:SPML:2:0}ModificationType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkModifyRequestType", propOrder = {
    "query",
    "modification"
})
public class BulkModifyRequestType
    extends RequestType
{

    @XmlElement(namespace = "urn:oasis:names:tc:SPML:2:0:search", required = true)
    protected SearchQueryType query;
    @XmlElement(required = true)
    protected List<ModificationType> modification;

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
     * Gets the value of the modification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationType }
     * 
     * 
     */
    public List<ModificationType> getModification() {
        if (modification == null) {
            modification = new ArrayList<ModificationType>();
        }
        return this.modification;
    }

}
