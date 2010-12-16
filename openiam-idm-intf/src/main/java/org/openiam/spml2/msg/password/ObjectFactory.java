
package org.openiam.spml2.msg.password;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.openiam.spml2.msg.ResponseType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.password package. 
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

	// spml spec namespace: urn:oasis:names:tc:SPML:2:0:password
		
    private final static QName _ValidatePasswordRequest_QNAME = new QName("http://www.openiam.org/service/connector", "validatePasswordRequest");
    private final static QName _SetPasswordRequest_QNAME = new QName("http://www.openiam.org/service/connector", "setPasswordRequest");
    private final static QName _SetPasswordResponse_QNAME = new QName("http://www.openiam.org/service/connector", "setPasswordResponse");
    private final static QName _ValidatePasswordResponse_QNAME = new QName("http://www.openiam.org/service/connector", "validatePasswordResponse");
    private final static QName _ResetPasswordResponse_QNAME = new QName("http://www.openiam.org/service/connector", "resetPasswordResponse");
    private final static QName _ResetPasswordRequest_QNAME = new QName("http://www.openiam.org/service/connector", "resetPasswordRequest");
    private final static QName _ExpirePasswordResponse_QNAME = new QName("http://www.openiam.org/service/connector", "expirePasswordResponse");
    private final static QName _ExpirePasswordRequest_QNAME = new QName("http://www.openiam.org/service/connector", "expirePasswordRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.password
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidatePasswordResponseType }
     * 
     */
    public ValidatePasswordResponseType createValidatePasswordResponseType() {
        return new ValidatePasswordResponseType();
    }

    /**
     * Create an instance of {@link ResetPasswordRequestType }
     * 
     */
    public ResetPasswordRequestType createResetPasswordRequestType() {
        return new ResetPasswordRequestType();
    }

    /**
     * Create an instance of {@link SetPasswordRequestType }
     * 
     */
    public SetPasswordRequestType createSetPasswordRequestType() {
        return new SetPasswordRequestType();
    }

    /**
     * Create an instance of {@link ExpirePasswordRequestType }
     * 
     */
    public ExpirePasswordRequestType createExpirePasswordRequestType() {
        return new ExpirePasswordRequestType();
    }

    /**
     * Create an instance of {@link ResetPasswordResponseType }
     * 
     */
    public ResetPasswordResponseType createResetPasswordResponseType() {
        return new ResetPasswordResponseType();
    }

    /**
     * Create an instance of {@link ValidatePasswordRequestType }
     * 
     */
    public ValidatePasswordRequestType createValidatePasswordRequestType() {
        return new ValidatePasswordRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePasswordRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "validatePasswordRequest")
    public JAXBElement<ValidatePasswordRequestType> createValidatePasswordRequest(ValidatePasswordRequestType value) {
        return new JAXBElement<ValidatePasswordRequestType>(_ValidatePasswordRequest_QNAME, ValidatePasswordRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPasswordRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "setPasswordRequest")
    public JAXBElement<SetPasswordRequestType> createSetPasswordRequest(SetPasswordRequestType value) {
        return new JAXBElement<SetPasswordRequestType>(_SetPasswordRequest_QNAME, SetPasswordRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "setPasswordResponse")
    public JAXBElement<ResponseType> createSetPasswordResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetPasswordResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidatePasswordResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "validatePasswordResponse")
    public JAXBElement<ValidatePasswordResponseType> createValidatePasswordResponse(ValidatePasswordResponseType value) {
        return new JAXBElement<ValidatePasswordResponseType>(_ValidatePasswordResponse_QNAME, ValidatePasswordResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetPasswordResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "resetPasswordResponse")
    public JAXBElement<ResetPasswordResponseType> createResetPasswordResponse(ResetPasswordResponseType value) {
        return new JAXBElement<ResetPasswordResponseType>(_ResetPasswordResponse_QNAME, ResetPasswordResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetPasswordRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "resetPasswordRequest")
    public JAXBElement<ResetPasswordRequestType> createResetPasswordRequest(ResetPasswordRequestType value) {
        return new JAXBElement<ResetPasswordRequestType>(_ResetPasswordRequest_QNAME, ResetPasswordRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "expirePasswordResponse")
    public JAXBElement<ResponseType> createExpirePasswordResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_ExpirePasswordResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpirePasswordRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openiam.org/service/connector", name = "expirePasswordRequest")
    public JAXBElement<ExpirePasswordRequestType> createExpirePasswordRequest(ExpirePasswordRequestType value) {
        return new JAXBElement<ExpirePasswordRequestType>(_ExpirePasswordRequest_QNAME, ExpirePasswordRequestType.class, null, value);
    }

}
