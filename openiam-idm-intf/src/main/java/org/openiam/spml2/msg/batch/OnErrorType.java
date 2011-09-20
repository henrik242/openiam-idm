package org.openiam.spml2.msg.batch;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OnErrorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OnErrorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="resume"/>
 *     &lt;enumeration value="exit"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OnErrorType")
@XmlEnum
public enum OnErrorType {

    @XmlEnumValue("resume")
    RESUME("resume"),
    @XmlEnumValue("exit")
    EXIT("exit");
    private final String value;

    OnErrorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OnErrorType fromValue(String v) {
        for (OnErrorType c: OnErrorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
