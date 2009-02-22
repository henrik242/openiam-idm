
package org.openiam.util.ws.collection;

import java.util.*;


/**
 * Helper class called between Lists of Strings and Arrays of Strings.
 * @author Suneet Shah
 */
public class StringUtil {

	/**
	 * 
	 */
	  public static String[] stringListToArray(List stringList) {
	    if (stringList == null)
			return null;
		int size = stringList.size();
		String[] stringArray = new String[size];
		for (int i = 0; i < size; i++) {
			String s = (String) stringList.get(i);
			stringArray[i] = s;
		}
	   return stringArray;
	  }
	  
	  public static List stringArrayToList(String[] s) {
		if (s == null) return null;
		List l = new ArrayList();
		int size = s.length;
		for (int i = 0; i < size; i++)
			l.add(s[i]);	  	
	  	return l;
	  }
	
}
