
package org.openiam.idm.srvc.continfo.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.idm.srvc.continfo.dto package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.idm.srvc.continfo.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmailAddress }
     * 
     */
    public EmailAddress createEmailAddress() {
        return new EmailAddress();
    }

    /**
     * Create an instance of {@link AddressMap }
     * 
     */
    public AddressMap createAddressMap() {
        return new AddressMap();
    }

    /**
     * Create an instance of {@link Phone }
     * 
     */
    public Phone createPhone() {
        return new Phone();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link AddressMap.AddressEntry }
     * 
     */
    public AddressMap.AddressEntry createAddressMapAddressEntry() {
        return new AddressMap.AddressEntry();
    }

    /**
     * Create an instance of {@link PhoneMap }
     * 
     */
    public PhoneMap createPhoneMap() {
        return new PhoneMap();
    }

    /**
     * Create an instance of {@link EmailAddressMap.EmailAddressEntry }
     * 
     */
    public EmailAddressMap.EmailAddressEntry createEmailAddressMapEmailAddressEntry() {
        return new EmailAddressMap.EmailAddressEntry();
    }

    /**
     * Create an instance of {@link PhoneMap.PhoneEntry }
     * 
     */
    public PhoneMap.PhoneEntry createPhoneMapPhoneEntry() {
        return new PhoneMap.PhoneEntry();
    }

    /**
     * Create an instance of {@link EmailAddressMap }
     * 
     */
    public EmailAddressMap createEmailAddressMap() {
        return new EmailAddressMap();
    }

}
