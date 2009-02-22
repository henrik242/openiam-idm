
package org.openiam.idm.srvc.user.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.idm.srvc.user.dto package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.idm.srvc.user.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Supervisor }
     * 
     */
    public Supervisor createSupervisor() {
        return new Supervisor();
    }

    /**
     * Create an instance of {@link UserAttributeMap }
     * 
     */
    public UserAttributeMap createUserAttributeMap() {
        return new UserAttributeMap();
    }

    /**
     * Create an instance of {@link UserAttributeMap.UserAttributeEntry }
     * 
     */
    public UserAttributeMap.UserAttributeEntry createUserAttributeMapUserAttributeEntry() {
        return new UserAttributeMap.UserAttributeEntry();
    }

    /**
     * Create an instance of {@link UserAttribute }
     * 
     */
    public UserAttribute createUserAttribute() {
        return new UserAttribute();
    }

    /**
     * Create an instance of {@link UserNote }
     * 
     */
    public UserNote createUserNote() {
        return new UserNote();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

}
