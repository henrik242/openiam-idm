/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.webadmin.admin;

import java.sql.Timestamp;
import java.text.*;
import java.util.Date;

/**
 * Utility class used in JSP classes to assist with formating issues.
 * @author suneet
 *
 */
public class JSPUtil {
	public static String display(Object obj) {
		if (obj == null ){
			return "";
		}else {
			return obj.toString();
		}
	}
	public static String displayTimestamp(Timestamp ts) {
		if (ts == null)
			return null;
		DateFormat df = new SimpleDateFormat();
		Date dt = (java.util.Date)ts;
		return df.format(dt);
		
	}
}
