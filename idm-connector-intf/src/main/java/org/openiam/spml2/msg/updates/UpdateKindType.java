
package org.openiam.spml2.msg.updates;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateKindType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpdateKindType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="add"/>
 *     &lt;enumeration value="modify"/>
 *     &lt;enumeration value="delete"/>
 *     &lt;enumeration value="capability"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UpdateKindType")
@XmlEnum
public enum UpdateKindType {

    @XmlEnumValue("add")
    ADD("add"),
    @XmlEnumValue("modify")
    MODIFY("modify"),
    @XmlEnumValue("delete")
    DELETE("delete"),
    @XmlEnumValue("capability")
    CAPABILITY("capability");
    private final String value;

    UpdateKindType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpdateKindType fromValue(String v) {
        for (UpdateKindType c: UpdateKindType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
