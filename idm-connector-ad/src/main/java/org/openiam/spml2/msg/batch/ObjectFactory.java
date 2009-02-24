
package org.openiam.spml2.msg.batch;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.batch package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BatchRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:batch", "batchRequest");
    private final static QName _BatchResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:batch", "batchResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.batch
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BatchRequestType }
     * 
     */
    public BatchRequestType createBatchRequestType() {
        return new BatchRequestType();
    }

    /**
     * Create an instance of {@link BatchResponseType }
     * 
     */
    public BatchResponseType createBatchResponseType() {
        return new BatchResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BatchRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:batch", name = "batchRequest")
    public JAXBElement<BatchRequestType> createBatchRequest(BatchRequestType value) {
        return new JAXBElement<BatchRequestType>(_BatchRequest_QNAME, BatchRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BatchResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:batch", name = "batchResponse")
    public JAXBElement<BatchResponseType> createBatchResponse(BatchResponseType value) {
        return new JAXBElement<BatchResponseType>(_BatchResponse_QNAME, BatchResponseType.class, null, value);
    }

}
