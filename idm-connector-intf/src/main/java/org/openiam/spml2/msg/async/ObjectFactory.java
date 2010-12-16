
package org.openiam.spml2.msg.async;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.async package. 
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

    private final static QName _CancelResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:async", "cancelResponse");
    private final static QName _StatusRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:async", "statusRequest");
    private final static QName _CancelRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:async", "cancelRequest");
    private final static QName _StatusResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:async", "statusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.async
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StatusRequestType }
     * 
     */
    public StatusRequestType createStatusRequestType() {
        return new StatusRequestType();
    }

    /**
     * Create an instance of {@link StatusResponseType }
     * 
     */
    public StatusResponseType createStatusResponseType() {
        return new StatusResponseType();
    }

    /**
     * Create an instance of {@link CancelRequestType }
     * 
     */
    public CancelRequestType createCancelRequestType() {
        return new CancelRequestType();
    }

    /**
     * Create an instance of {@link CancelResponseType }
     * 
     */
    public CancelResponseType createCancelResponseType() {
        return new CancelResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:async", name = "cancelResponse")
    public JAXBElement<CancelResponseType> createCancelResponse(CancelResponseType value) {
        return new JAXBElement<CancelResponseType>(_CancelResponse_QNAME, CancelResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:async", name = "statusRequest")
    public JAXBElement<StatusRequestType> createStatusRequest(StatusRequestType value) {
        return new JAXBElement<StatusRequestType>(_StatusRequest_QNAME, StatusRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:async", name = "cancelRequest")
    public JAXBElement<CancelRequestType> createCancelRequest(CancelRequestType value) {
        return new JAXBElement<CancelRequestType>(_CancelRequest_QNAME, CancelRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:async", name = "statusResponse")
    public JAXBElement<StatusResponseType> createStatusResponse(StatusResponseType value) {
        return new JAXBElement<StatusResponseType>(_StatusResponse_QNAME, StatusResponseType.class, null, value);
    }

}
