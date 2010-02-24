
package org.openiam.spml2.msg.updates;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.openiam.spml2.msg.ResponseType;


/**
 * <p>Java class for UpdatesResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatesResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="update" type="{urn:oasis:names:tc:SPML:2:0:updates}UpdateType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="iterator" type="{urn:oasis:names:tc:SPML:2:0:updates}ResultsIteratorType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="token" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatesResponseType", propOrder = {
    "update",
    "iterator"
})
public class UpdatesResponseType
    extends ResponseType
{

    protected List<UpdateType> update;
    protected ResultsIteratorType iterator;
    @XmlAttribute
    protected String token;

    /**
     * Gets the value of the update property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the update property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdateType }
     * 
     * 
     */
    public List<UpdateType> getUpdate() {
        if (update == null) {
            update = new ArrayList<UpdateType>();
        }
        return this.update;
    }

    /**
     * Gets the value of the iterator property.
     * 
     * @return
     *     possible object is
     *     {@link ResultsIteratorType }
     *     
     */
    public ResultsIteratorType getIterator() {
        return iterator;
    }

    /**
     * Sets the value of the iterator property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultsIteratorType }
     *     
     */
    public void setIterator(ResultsIteratorType value) {
        this.iterator = value;
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

}
