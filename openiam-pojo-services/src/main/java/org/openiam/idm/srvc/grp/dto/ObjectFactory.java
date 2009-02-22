
package org.openiam.idm.srvc.grp.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openiam.idm.srvc.grp.dto package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openiam.idm.srvc.grp.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GroupAttributeMap.GroupAttributeEntry }
     * 
     */
    public GroupAttributeMap.GroupAttributeEntry createGroupAttributeMapGroupAttributeEntry() {
        return new GroupAttributeMap.GroupAttributeEntry();
    }

    /**
     * Create an instance of {@link GroupSet.GroupObj }
     * 
     */
    public GroupSet.GroupObj createGroupSetGroupObj() {
        return new GroupSet.GroupObj();
    }

    /**
     * Create an instance of {@link GroupAttributeMap }
     * 
     */
    public GroupAttributeMap createGroupAttributeMap() {
        return new GroupAttributeMap();
    }

    /**
     * Create an instance of {@link GroupAttribute }
     * 
     */
    public GroupAttribute createGroupAttribute() {
        return new GroupAttribute();
    }

    /**
     * Create an instance of {@link GroupSet }
     * 
     */
    public GroupSet createGroupSet() {
        return new GroupSet();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

}
