
package org.openiam.idm.srvc.qry.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Enum describing the list of types that a query parameter can have.
 * @author suneet
 *
 */
@XmlType(name = "ParameterTypeEnum")
@XmlEnum
public enum ParameterTypeEnum {

    @XmlEnumValue("string")
    STRING("string"),
    @XmlEnumValue("date")
    SDATE("date"),
    @XmlEnumValue("timestamp")
    TIMESTAMP("timestamp"),    
    @XmlEnumValue("integer")
    INTEGER("integer");
    private final String value;
  
    ParameterTypeEnum(String val) {
    	value = val;
    }

}
