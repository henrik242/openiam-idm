
package org.openiam.idm.srvc.org.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Classification that an be used to further organize the entries in the organization list.
 * @author suneet
 *
 */
@XmlType(name = "OrgClassification")
@XmlEnum
public enum OrgClassificationEnum {

    @XmlEnumValue("organization")
    ORGANIZATION("ORGANIZATION"),
    @XmlEnumValue("department")
    DEPARTMENT("DEPARTMENT"),
    @XmlEnumValue("division")
    DIVISION("DIVISION"),
    @XmlEnumValue("agency")
    AGENCY("AGENCY"),
    @XmlEnumValue("vendor")
    VENDOR("VENDOR"),
    @XmlEnumValue("partner")
    PARTNER("PARTNER"),   
    @XmlEnumValue("subsidiary")
    SUBSIDIARY("SUBSIDIARY"),
    @XmlEnumValue("customer")
    CUSTOMER("CUSTOMER"),
    @XmlEnumValue("affiliate")
    AFFILIATE("AFFILIATE");
    private String value;
  
    OrgClassificationEnum(String val) {
    	value = val;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String val) {
    	value = val;
    }  


}
