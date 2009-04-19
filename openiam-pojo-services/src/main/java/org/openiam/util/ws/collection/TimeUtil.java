package org.openiam.util.ws.collection;
import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * @author Owner
 *
 * Utility class to handle time and calendars
 */
public class TimeUtil implements Serializable {

	/*
	 * @deprecated use calendarToTime class method
	 */
	public Timestamp getTimestamp(Calendar cal) {
		if (cal == null)
			return null;		
		long timeval = cal.getTime().getTime();		
		return new Timestamp(timeval);
	}
	
	/*
	 * @deprecated use timeToCalendar class method
	 */
	public Calendar getCalendar(Timestamp t){
		if (t == null)
			return null;		
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(t.getTime());
		return cal;
	}
	
	public Calendar getCurrentCalendar() {
		Calendar now = new GregorianCalendar();
		return now;
	}

	// static methods ------------------------------
	public static Calendar timeToCalendar(Timestamp t) {
		if (t == null) return null;		
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(t.getTime());
		return c;		
	}

	public static Timestamp calendarToTime(Calendar c) {
		if (c == null) return null;		
		//long timeval = c.getTime().getTime();		
		return new Timestamp(c.getTimeInMillis());
	}

	public static Calendar dateToCalendar(java.util.Date d) {
		if (d == null) return null;
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		return c;
	}
	
	public static java.util.Date calendarToDate(Calendar c){
		if (c == null) return null;		
		return new java.util.Date(c.getTimeInMillis());
	}
	
	public static Calendar longToCalendar(long l){
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(l);
		return c;
	}
	
	public static long calendarToLong(Calendar c) {
		if (c == null) return 0;
		return c.getTimeInMillis();
	}
	
}
