package org.openiam.base;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;



/**
 * Status' that a user can be in.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseConstants", propOrder = {
    "NULL_STRING",
    "NULL_INTEGER",
    "NULL_DATE"
})
public class BaseConstants {

	public static final String NULL_STRING = "null";
	public static final Date NULL_DATE = new Date(0L);
	public static final int NULL_INTEGER = -999999;
	
	public static String getNullString() {
		return NULL_STRING;
	}
	public static Date getNullDate() {
		return NULL_DATE;
	}
	public static int getNullInteger() {
		return NULL_INTEGER;
	}
	
	
    


}
