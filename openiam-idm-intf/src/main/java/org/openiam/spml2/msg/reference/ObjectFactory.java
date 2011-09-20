
package org.openiam.spml2.msg.reference;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.spml._2._0.reference package. 
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

    private final static QName _HasReference_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:reference", "hasReference");
    private final static QName _ReferenceDefinition_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:reference", "referenceDefinition");
    private final static QName _Reference_QNAME = new QName("urn:oasis:names:tc:SPML:2:0:reference", "reference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.spml._2._0.reference
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HasReferenceType }
     * 
     */
    public HasReferenceType createHasReferenceType() {
        return new HasReferenceType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link ReferenceDefinitionType }
     * 
     */
    public ReferenceDefinitionType createReferenceDefinitionType() {
        return new ReferenceDefinitionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:reference", name = "hasReference")
    public JAXBElement<HasReferenceType> createHasReference(HasReferenceType value) {
        return new JAXBElement<HasReferenceType>(_HasReference_QNAME, HasReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceDefinitionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:reference", name = "referenceDefinition")
    public JAXBElement<ReferenceDefinitionType> createReferenceDefinition(ReferenceDefinitionType value) {
        return new JAXBElement<ReferenceDefinitionType>(_ReferenceDefinition_QNAME, ReferenceDefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:SPML:2:0:reference", name = "reference")
    public JAXBElement<ReferenceType> createReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_Reference_QNAME, ReferenceType.class, null, value);
    }

}
