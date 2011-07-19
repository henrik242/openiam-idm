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
package org.openiam.idm.srvc.recon.dto;

import org.openiam.base.ws.ResponseCode;
import org.openiam.base.ws.ResponseStatus;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Response object from a synchronization request.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReconciliationResponse", propOrder = {
    "status",
    "errorCode",
    "errorText",
    "lastRecordTime",
    "lastRecProcessed"
})
public class ReconciliationResponse {

	@XmlAttribute(required = true)
	protected ResponseStatus status;
	protected ResponseCode errorCode;
	protected String errorText;
	@XmlSchemaType(name = "dateTime")
	protected Date lastRecordTime;
    protected String lastRecProcessed;

	public ReconciliationResponse() {

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
	public ReconciliationResponse(ResponseStatus status) {
		super();
		this.status = status;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public Date getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

    @Override
    public String toString() {
        return "SyncResponse{" +
                "status=" + status +
                ", errorCode=" + errorCode +
                ", errorText='" + errorText + '\'' +
                ", lastRecordTime=" + lastRecordTime +
                '}';
    }

    public String getLastRecProcessed() {
        return lastRecProcessed;
    }

    public void setLastRecProcessed(String lastRecProcessed) {
        this.lastRecProcessed = lastRecProcessed;
    }
}
