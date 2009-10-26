package org.openiam.idm.ws.adapters;

import java.util.Calendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {

	public Calendar unmarshal(String value) {
		return (javax.xml.bind.DatatypeConverter.parseDateTime(value));
	}

	public String marshal(Calendar value) {
		if (value == null) {
			return null;
		}
		return (javax.xml.bind.DatatypeConverter.printDateTime(value));
	}

}

// add this to xml file:
/*
<bindings version="2.1"
    xmlns="http://java.sun.com/xml/ns/jaxb"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
    >

    <globalBindings>
            <xjc:javaType name="java.util.Calendar" xmlType="xsd:date"
                    adapter="org.openiam.util.ws.adapters.CalendarAdapter" />

            <xjc:javaType name="java.util.Calendar" xmlType="xsd:time"
                    adapter="xxx.common.jaxb.CalendarAdapter" />

            <xjc:javaType name="java.util.Calendar" xmlType="xsd:dateTime"
                    adapter="xxx.common.jaxb.CalendarAdapter" />
    </globalBindings>
</bindings>
*/

