
package org.openiam.provision.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Status' that a user can be in.
 * @author suneet
 *
 */
@XmlType(name = "UserStatus")
@XmlEnum
public enum ProvisionMethodEnum {

    @XmlEnumValue("auto")
    AUTO("AUTO"),
    @XmlEnumValue("manual")
    MANUAL("MANUAL");
;
    private String value;
  
    ProvisionMethodEnum(String val) {
    	value = val;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String val) {
    	value = val;
    }  


}
