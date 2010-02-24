
package org.openiam.spml2.msg.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.spml2.msg.RequestType;


/**
 * <p>Java class for CloseIteratorRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CloseIteratorRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}RequestType">
 *       &lt;sequence>
 *         &lt;element name="iterator" type="{urn:oasis:names:tc:SPML:2:0:search}ResultsIteratorType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CloseIteratorRequestType", propOrder = {
    "iterator"
})
public class CloseIteratorRequestType
    extends RequestType
{

    @XmlElement(required = true)
    protected ResultsIteratorType iterator;

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

}
