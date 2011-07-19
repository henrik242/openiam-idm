
package org.openiam.idm.srvc.msg.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Status' that a user can be in.
 * @author suneet
 *
 */
@XmlType(name = "NotificationType")
@XmlEnum
public enum NotificationType {

    @XmlEnumValue("new_hire")
    NEW_HIRE("NEW_HIRE"),
    @XmlEnumValue("account_locked")
    ACCOUNT_LOCKED("ACCOUNT_LOCKED"),
    @XmlEnumValue("account_expired")
    ACCOUNT_EXPIRED("ACCOUNT_EXPIRED"),
    @XmlEnumValue("password_will_expire")
    PASSWORD_WILL_EXPIRE("PASSWORD_WILL_EXPIRE");
    private String value;
  
    NotificationType(String val) {
    	value = val;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String val) {
    	value = val;
    }  


}
