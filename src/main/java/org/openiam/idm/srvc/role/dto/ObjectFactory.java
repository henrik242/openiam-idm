
package org.openiam.idm.srvc.role.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.idm.srvc.role.dto package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.idm.srvc.role.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link RoleAttribute }
     * 
     */
    public RoleAttribute createRoleAttribute() {
        return new RoleAttribute();
    }

    /**
     * Create an instance of {@link RoleId }
     * 
     */
    public RoleId createRoleId() {
        return new RoleId();
    }

    /**
     * Create an instance of {@link RoleSet }
     * 
     */
    public RoleSet createRoleSet() {
        return new RoleSet();
    }

    /**
     * Create an instance of {@link RoleAttributeSet }
     * 
     */
    public RoleAttributeSet createRoleAttributeSet() {
        return new RoleAttributeSet();
    }

    /**
     * Create an instance of {@link RoleAttributeSet.RoleAttributeObj }
     * 
     */
    public RoleAttributeSet.RoleAttributeObj createRoleAttributeSetRoleAttributeObj() {
        return new RoleAttributeSet.RoleAttributeObj();
    }

    /**
     * Create an instance of {@link RoleSet.RoleObj }
     * 
     */
    public RoleSet.RoleObj createRoleSetRoleObj() {
        return new RoleSet.RoleObj();
    }

}
