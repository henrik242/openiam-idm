
package org.openiam.spml2.msg.updates;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.openiam.spml2.msg.ResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.updates package. 
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

    private final static QName _IterateRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:updates", "iterateRequest");
    private final static QName _IterateResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:updates", "iterateResponse");
    private final static QName _CloseIteratorResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:updates", "closeIteratorResponse");
    private final static QName _CloseIteratorRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:updates", "closeIteratorRequest");
    private final static QName _UpdatesRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:updates", "updatesRequest");
    private final static QName _UpdatesResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:updates", "updatesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.updates
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CloseIteratorRequestType }
     * 
     */
    public CloseIteratorRequestType createCloseIteratorRequestType() {
        return new CloseIteratorRequestType();
    }

    /**
     * Create an instance of {@link UpdatesResponseType }
     * 
     */
    public UpdatesResponseType createUpdatesResponseType() {
        return new UpdatesResponseType();
    }

    /**
     * Create an instance of {@link IterateRequestType }
     * 
     */
    public IterateRequestType createIterateRequestType() {
        return new IterateRequestType();
    }

    /**
     * Create an instance of {@link UpdateType }
     * 
     */
    public UpdateType createUpdateType() {
        return new UpdateType();
    }

    /**
     * Create an instance of {@link ResultsIteratorType }
     * 
     */
    public ResultsIteratorType createResultsIteratorType() {
        return new ResultsIteratorType();
    }

    /**
     * Create an instance of {@link UpdatesRequestType }
     * 
     */
    public UpdatesRequestType createUpdatesRequestType() {
        return new UpdatesRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IterateRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:updates", name = "iterateRequest")
    public JAXBElement<IterateRequestType> createIterateRequest(IterateRequestType value) {
        return new JAXBElement<IterateRequestType>(_IterateRequest_QNAME, IterateRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatesResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:updates", name = "iterateResponse")
    public JAXBElement<UpdatesResponseType> createIterateResponse(UpdatesResponseType value) {
        return new JAXBElement<UpdatesResponseType>(_IterateResponse_QNAME, UpdatesResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:updates", name = "closeIteratorResponse")
    public JAXBElement<ResponseType> createCloseIteratorResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_CloseIteratorResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CloseIteratorRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:updates", name = "closeIteratorRequest")
    public JAXBElement<CloseIteratorRequestType> createCloseIteratorRequest(CloseIteratorRequestType value) {
        return new JAXBElement<CloseIteratorRequestType>(_CloseIteratorRequest_QNAME, CloseIteratorRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatesRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:updates", name = "updatesRequest")
    public JAXBElement<UpdatesRequestType> createUpdatesRequest(UpdatesRequestType value) {
        return new JAXBElement<UpdatesRequestType>(_UpdatesRequest_QNAME, UpdatesRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatesResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:updates", name = "updatesResponse")
    public JAXBElement<UpdatesResponseType> createUpdatesResponse(UpdatesResponseType value) {
        return new JAXBElement<UpdatesResponseType>(_UpdatesResponse_QNAME, UpdatesResponseType.class, null, value);
    }

}
