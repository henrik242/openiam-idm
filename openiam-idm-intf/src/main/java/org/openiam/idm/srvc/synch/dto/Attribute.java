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

/**
 * @author suneet
 *
 */
public class Attribute implements Cloneable {
	private String name;
	private String value;
	private String type;
	private int columnNbr;
	
	public Attribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Attribute(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	protected Object clone() {
		Attribute a = new Attribute(name, type, value, columnNbr);
		return a;
		
	}
	
	public String toString() {
		String s = "name=" + name + " value=" + value;
		return s;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Attribute(String name, String type, String value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}
	public Attribute(String name, String type, String value, int colNbr) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
		this.columnNbr = colNbr;
	}
	
	public int getColumnNbr() {
		return columnNbr;
	}

	public void setColumnNbr(int columnNbr) {
		this.columnNbr = columnNbr;
	}
}
