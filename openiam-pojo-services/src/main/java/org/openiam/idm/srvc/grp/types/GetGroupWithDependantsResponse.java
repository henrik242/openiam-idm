
package org.openiam.idm.srvc.grp.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.openiam.idm.srvc.grp.dto.Group;


/**
 * <p>Java class for getGroupWithDependantsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getGroupWithDependantsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{urn:idm.openiam.org/srvc/grp/dto}group" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGroupWithDependantsResponse", propOrder = {
    "_return"
})
public class GetGroupWithDependantsResponse {

    @XmlElement(name = "return")
    protected Group _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Group }
     *     
     */
    public Group getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Group }
     *     
     */
    public void setReturn(Group value) {
        this._return = value;
    }

}
