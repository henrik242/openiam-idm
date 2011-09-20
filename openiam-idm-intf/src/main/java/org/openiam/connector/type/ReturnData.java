
package org.openiam.connector.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnDataType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReturnDataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="identifier"/>
 *     &lt;enumeration value="data"/>
 *     &lt;enumeration value="everything"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReturnData")
@XmlEnum
public enum ReturnData {

    @XmlEnumValue("identifier")
    IDENTIFIER("identifier"),
    @XmlEnumValue("data")
    DATA("data"),
    @XmlEnumValue("everything")
    EVERYTHING("everything");
    private final String value;

    ReturnData(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReturnData fromValue(String v) {
        for (ReturnData c: ReturnData.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
