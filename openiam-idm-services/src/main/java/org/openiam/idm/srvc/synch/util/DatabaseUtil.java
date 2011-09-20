/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.synch.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openiam.idm.srvc.synch.dto.Attribute;
import org.openiam.idm.srvc.synch.dto.LineObject;

/**
 * @author suneet
 *
 */
public class DatabaseUtil {

	
	public static void populateTemplate(ResultSetMetaData rsMetadata, LineObject rowHeader ) throws SQLException {
		Map<String,Attribute> columnMap = new HashMap<String, Attribute>();
		
		System.out.println("Creating column list from Metadata");
		int colCount = rsMetadata.getColumnCount();
		for (int i=1;  i <= colCount; i++) {
			Attribute a = new Attribute(rsMetadata.getColumnName(i), null);
		 	a.setType( rsMetadata.getColumnTypeName(i) );
			columnMap.put(a.getName(),a);

		}
		
		rowHeader.setColumnMap(columnMap);
	}

	public static void populateRowObject(LineObject rowObj , ResultSet rs, String changeLog) throws SQLException {
		
		DateFormat df =  new SimpleDateFormat("MM-dd-yyyy"); 
	
		Map<String, Attribute> attrMap =  rowObj.getColumnMap();
		Set<String> keySet = attrMap.keySet();
		Iterator<String> it  = keySet.iterator();
		
		
		while ( it.hasNext()) {
			String key  = it.next();
			Attribute attr =  rowObj.get(key);
			if (attr.getType().contains("DATE")) {
				if (changeLog != null && key.equalsIgnoreCase(changeLog)) {
					Timestamp tm = rs.getTimestamp(key);
					if ( tm != null) {
						attr.setValue( df.format(tm) );
					}
					rowObj.setLastUpdate(tm);
				}else {
					Date dt = rs.getDate(key);
					if ( dt != null) {
						attr.setValue( df.format(dt) );
					}
				}

			}else {
				attr.setValue(rs.getString(key));
			}
		}
		

	}
	
}
