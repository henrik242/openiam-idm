package org.openiam.idm.ws.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.xml.bind.DatatypeConverter;

public class DateAdapter extends XmlAdapter<String, Date> {

	public Date unmarshal(String value) {
		return (org.apache.cxf.tools.common.DataTypeAdapter
				.parseDateTime(value));
	}

	public String marshal(Date value) {
		return (org.apache.cxf.tools.common.DataTypeAdapter
				.printDateTime(value));
	}
}

// @XmlElement(name = "return", type = String.class)
// @XmlJavaTypeAdapter(DateAdapter.class)

