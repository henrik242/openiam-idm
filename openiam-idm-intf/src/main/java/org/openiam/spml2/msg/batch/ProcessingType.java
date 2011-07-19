package org.openiam.spml2.msg.batch;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcessingType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProcessingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="sequential"/>
 *     &lt;enumeration value="parallel"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProcessingType")
@XmlEnum
public enum ProcessingType {

    @XmlEnumValue("sequential")
    SEQUENTIAL("sequential"),
    @XmlEnumValue("parallel")
    PARALLEL("parallel");
    private final String value;

    ProcessingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProcessingType fromValue(String v) {
        for (ProcessingType c: ProcessingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
