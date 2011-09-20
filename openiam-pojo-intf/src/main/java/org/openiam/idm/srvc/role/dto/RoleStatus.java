
package org.openiam.idm.srvc.role.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Rolestatus provides valid values for a status of Role.
 * @author suneet
 *
 */
@XmlType(name = "RoleStatus")
@XmlEnum
public enum RoleStatus {

    @XmlEnumValue("active")
    ACTIVE("active"),
    @XmlEnumValue("deleted")
    DELETED("deleted"),
    @XmlEnumValue("pending_approval")
    PENDING_APPROVAL("pending_approval");
    private final String value;
  
    RoleStatus(String val) {
    	value = val;
    }

}
