
package org.openiam.spml2.msg.suspend;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.openiam.spml2.msg.ResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.suspend package. 
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

    private final static QName _IsActive_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "isActive");
    private final static QName _SuspendResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "suspendResponse");
    private final static QName _ResumeResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "resumeResponse");
    private final static QName _ActiveResponse_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "activeResponse");
    private final static QName _ResumeRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "resumeRequest");
    private final static QName _SuspendRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "suspendRequest");
    private final static QName _ActiveRequest_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:suspend", "activeRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.suspend
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActiveRequestType }
     * 
     */
    public ActiveRequestType createActiveRequestType() {
        return new ActiveRequestType();
    }

    /**
     * Create an instance of {@link ActiveResponseType }
     * 
     */
    public ActiveResponseType createActiveResponseType() {
        return new ActiveResponseType();
    }

    /**
     * Create an instance of {@link ResumeRequestType }
     * 
     */
    public ResumeRequestType createResumeRequestType() {
        return new ResumeRequestType();
    }

    /**
     * Create an instance of {@link IsActiveType }
     * 
     */
    public IsActiveType createIsActiveType() {
        return new IsActiveType();
    }

    /**
     * Create an instance of {@link SuspendRequestType }
     * 
     */
    public SuspendRequestType createSuspendRequestType() {
        return new SuspendRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsActiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "isActive")
    public JAXBElement<IsActiveType> createIsActive(IsActiveType value) {
        return new JAXBElement<IsActiveType>(_IsActive_QNAME, IsActiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "suspendResponse")
    public JAXBElement<ResponseType> createSuspendResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SuspendResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "resumeResponse")
    public JAXBElement<ResponseType> createResumeResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_ResumeResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActiveResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "activeResponse")
    public JAXBElement<ActiveResponseType> createActiveResponse(ActiveResponseType value) {
        return new JAXBElement<ActiveResponseType>(_ActiveResponse_QNAME, ActiveResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResumeRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "resumeRequest")
    public JAXBElement<ResumeRequestType> createResumeRequest(ResumeRequestType value) {
        return new JAXBElement<ResumeRequestType>(_ResumeRequest_QNAME, ResumeRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuspendRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "suspendRequest")
    public JAXBElement<SuspendRequestType> createSuspendRequest(SuspendRequestType value) {
        return new JAXBElement<SuspendRequestType>(_SuspendRequest_QNAME, SuspendRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActiveRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:suspend", name = "activeRequest")
    public JAXBElement<ActiveRequestType> createActiveRequest(ActiveRequestType value) {
        return new JAXBElement<ActiveRequestType>(_ActiveRequest_QNAME, ActiveRequestType.class, null, value);
    }

}
