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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;

/**
 * Response object from a synchronization request.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncResponse", propOrder = {
    "status",
    "errorCode"
})
public class SyncResponse {

	@XmlAttribute(required = true)
	protected ResponseStatus status;
	protected ResponseCode errorCode;
	
	public SyncResponse() {
		
	}
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public ResponseCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ResponseCode errorCode) {
		this.errorCode = errorCode;
	}
	public SyncResponse(ResponseStatus status) {
		super();
		this.status = status;
	}
}
