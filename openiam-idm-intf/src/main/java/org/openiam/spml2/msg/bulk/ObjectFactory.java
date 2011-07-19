package org.openiam.spml2.msg.bulk;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.openiam.spml2.msg.ResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.bulk package. 
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

    private final static QName _BulkModifyResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:bulk", "bulkModifyResponse");
    private final static QName _BulkDeleteResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:bulk", "bulkDeleteResponse");
    private final static QName _BulkModifyRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:bulk", "bulkModifyRequest");
    private final static QName _BulkDeleteRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:bulk", "bulkDeleteRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.bulk
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BulkDeleteRequestType }
     * 
     */
    public BulkDeleteRequestType createBulkDeleteRequestType() {
        return new BulkDeleteRequestType();
    }

    /**
     * Create an instance of {@link BulkModifyRequestType }
     * 
     */
    public BulkModifyRequestType createBulkModifyRequestType() {
        return new BulkModifyRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:bulk", name = "bulkModifyResponse")
    public JAXBElement<ResponseType> createBulkModifyResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_BulkModifyResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:bulk", name = "bulkDeleteResponse")
    public JAXBElement<ResponseType> createBulkDeleteResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_BulkDeleteResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BulkModifyRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:bulk", name = "bulkModifyRequest")
    public JAXBElement<BulkModifyRequestType> createBulkModifyRequest(BulkModifyRequestType value) {
        return new JAXBElement<BulkModifyRequestType>(_BulkModifyRequest_QNAME, BulkModifyRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BulkDeleteRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:bulk", name = "bulkDeleteRequest")
    public JAXBElement<BulkDeleteRequestType> createBulkDeleteRequest(BulkDeleteRequestType value) {
        return new JAXBElement<BulkDeleteRequestType>(_BulkDeleteRequest_QNAME, BulkDeleteRequestType.class, null, value);
    }

}
