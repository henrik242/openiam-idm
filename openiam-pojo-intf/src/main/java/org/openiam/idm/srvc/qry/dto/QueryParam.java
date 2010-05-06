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
package org.openiam.idm.srvc.qry.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author suneet
 *
 */
public class QueryParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1858970104714815975L;

	protected ParameterTypeEnum paramType;
	protected String paramName;
	protected Object paramValue;
	protected List<Object> paramValueList;
	public ParameterTypeEnum getParamType() {
		return paramType;
	}
	public void setParamType(ParameterTypeEnum paramType) {
		this.paramType = paramType;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public Object getParamValue() {
		return paramValue;
	}
	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}
	public List<Object> getParamValueList() {
		return paramValueList;
	}
	public void setParamValueList(List<Object> paramValueList) {
		this.paramValueList = paramValueList;
	}

}
