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
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.qry.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Object used to capture an adhoc sql query
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Query", propOrder = { 
		"sql",
		"maxRowCount",
		"objectClass",
		"paramList"
	})
public class Query implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3948465313736129128L;
	protected String sql;
	protected int maxRowCount;
	protected String objectClass;
	protected List<QueryParam> paramList;

	
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getSql() {
		return sql;
	}


	public void setSql(String sql) {
		this.sql = sql;
	}


	public int getMaxRowCount() {
		return maxRowCount;
	}


	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}




	public List<QueryParam> getParamList() {
		return paramList;
	}


	public void setParamList(List<QueryParam> paramList) {
		this.paramList = paramList;
	}


	public String getObjectClass() {
		return objectClass;
	}


	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}
	
	
	
	
}
