
package org.openiam.idm.srvc.grp.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * GroupStatus provides valid values for a status of Group.
 * @author suneet
 *
 */
@XmlType(name = "GroupStatus")
@XmlEnum
public enum GroupStatus {

    @XmlEnumValue("active")
    ACTIVE("active"),
    @XmlEnumValue("deleted")
    DELETED("deleted"),
    @XmlEnumValue("pending_approval")
    PENDING_APPROVAL("pending_approval");
    private final String value;
  
    GroupStatus(String val) {
    	value = val;
    }

}
