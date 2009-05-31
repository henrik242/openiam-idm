
package oasis.names.tc.spml._2._0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for RequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SPML:2:0}ExtensibleType">
 *       &lt;attribute name="requestID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="executionMode" type="{urn:oasis:names:tc:SPML:2:0}ExecutionModeType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType")
@XmlSeeAlso({
    LookupRequestType.class,
    AddRequestType.class,
    ListTargetsRequestType.class,
    DeleteRequestType.class,
    ModifyRequestType.class
})
public class RequestType
    extends ExtensibleType
{

    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String requestID;
    @XmlAttribute
    protected ExecutionModeType executionMode;

    /**
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the executionMode property.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionModeType }
     *     
     */
    public ExecutionModeType getExecutionMode() {
        return executionMode;
    }

    /**
     * Sets the value of the executionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionModeType }
     *     
     */
    public void setExecutionMode(ExecutionModeType value) {
        this.executionMode = value;
    }

}
