
package org.openiam.provision.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Status' that a user can be in.
 * @author suneet
 *
 */
@XmlType(name = "ProvisionModelEnum")
@XmlEnum
public enum ProvisionModelEnum {

    @XmlEnumValue("role")
    ROLE("ROLE"),
    @XmlEnumValue("static")
    STATIC("static"),
    @XmlEnumValue("rule")
    RULE("RULE");
    private String value;
  
    ProvisionModelEnum(String val) {
    	value = val;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String val) {
    	value = val;
    }  


}
