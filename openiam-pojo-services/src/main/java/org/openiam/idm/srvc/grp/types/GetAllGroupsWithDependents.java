
package org.openiam.idm.srvc.grp.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllGroupsWithDependents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllGroupsWithDependents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subgroups" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGroupsWithDependents", propOrder = {
    "subgroups"
})
public class GetAllGroupsWithDependents {

    protected boolean subgroups;

    /**
     * Gets the value of the subgroups property.
     * 
     */
    public boolean isSubgroups() {
        return subgroups;
    }

    /**
     * Sets the value of the subgroups property.
     * 
     */
    public void setSubgroups(boolean value) {
        this.subgroups = value;
    }

}
