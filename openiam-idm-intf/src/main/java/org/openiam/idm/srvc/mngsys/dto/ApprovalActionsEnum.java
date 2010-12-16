
package org.openiam.idm.srvc.mngsys.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Actions that can be used in an approval process..
 * @author suneet
 *
 */
@XmlType(name = "ApprovalActions")
@XmlEnum
public enum ApprovalActionsEnum {

    @XmlEnumValue("start")
    START("START"),
    @XmlEnumValue("ACCEPT")
    ACCEPT("ACCEPT"),
    @XmlEnumValue("REJECT")
    REJECT("REJECT"),
    @XmlEnumValue("NEXT")
    NEXT("NEXT");
    private String value;
  
    ApprovalActionsEnum(String val) {
    	value = val;
    }
    public String getValue() {
    	return value;
    }
    public void setValue(String val) {
    	value = val;
    }  


}
