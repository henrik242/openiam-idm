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
package org.openiam.idm.srvc.auth.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.auth.dto.Subject;


/**
 * Response object for a web service operation that returns a role.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationResponse", propOrder = {
    "subject",
    "authErrorCode",
    "authErrorMessage",
    "status"
})
public class AuthenticationResponse {

	@XmlAttribute(required = true)
    protected ResponseStatus status;
	
	protected Subject subject;
	protected int authErrorCode;
	protected String authErrorMessage;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(ResponseStatus s) {
		status = s;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getAuthErrorCode() {
		return authErrorCode;
	}

	public void setAuthErrorCode(int authErrorCode) {
		this.authErrorCode = authErrorCode;
	}

	public String getAuthErrorMessage() {
		return authErrorMessage;
	}

	public void setAuthErrorMessage(String authErrorMessage) {
		this.authErrorMessage = authErrorMessage;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}







	
	
}
