
package org.openiam.provision.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Valid status codes to pass to the lock operation.
 * @author suneet
 *
 */
@XmlType(name = "AccountLockEnum")
@XmlEnum
public enum AccountLockEnum {

	@XmlEnumValue("locked")
	LOCKED("LOCKED"),
	@XmlEnumValue("unlock")
	UNLOCK("UNLOCK"),
	@XmlEnumValue("locked_admin")
	LOCKED_ADMIN("LOCKED_ADMIN");

    private String value;
  
    AccountLockEnum(String val) {
    	value = val;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String val) {
    	value = val;
    }  


}
