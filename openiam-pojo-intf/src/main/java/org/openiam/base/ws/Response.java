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
package org.openiam.base.ws;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Base class that is used for responses from a web service.
 * @author Suneet Shah
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "status",
    "errorCode",
    "responseValue"
})
public class Response {

	@XmlAttribute(required = true)
    protected ResponseStatus status;
	protected ResponseCode errorCode;
	
	protected Object responseValue;

	public Response() {
		
	}
	public Response(ResponseStatus s) {
		status = s;
	}
	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}



	public Object getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(Object responseValue) {
		this.responseValue = responseValue;
	}
	public ResponseCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ResponseCode errorCode) {
		this.errorCode = errorCode;
	}
    
}
