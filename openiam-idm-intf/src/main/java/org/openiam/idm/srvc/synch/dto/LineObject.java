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
package org.openiam.idm.srvc.synch.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author suneet
 *
 */
public class LineObject implements Cloneable {
	Map<String,Attribute> columnMap = new HashMap<String, Attribute>();
	Date lastUpdate = new Date();

	public Map<String, Attribute> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, Attribute> columnMap) {
		this.columnMap = columnMap;
	}
	
	public void put(String key,String name, String lineObject) {
		columnMap.put(key, new Attribute(name,lineObject));
	}
	
	public void put(String key,Attribute attr) {
	
		columnMap.put(key, attr);
	}
	
	public Attribute get(String key) {
		return columnMap.get(key);
		
	}
	protected Object clone() {
		LineObject obj = new LineObject();
		Map<String,Attribute> newColumnMap = new HashMap<String, Attribute>();
		
		Set<String> keySet = columnMap.keySet();
		for (String key : keySet) {
			Attribute a = columnMap.get(key);
			newColumnMap.put(key, (Attribute)a.clone());
		}
		obj.setColumnMap(newColumnMap);
		return obj;
		
	}
	public LineObject copy() {
		return (LineObject)clone();
	}
	
	public String toString() {
		return columnMap.toString();
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
