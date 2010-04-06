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
package org.openiam.idm.srvc.orgpolicy.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;

/**
 * Response object for a web service operation that returns a list of group objects
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgPolicyUserLogListResponse", propOrder = {
    "userLogList"
})
public class OrgPolicyUserLogListResponse extends Response{

	protected List<OrgPolicyUserLog> userLogList;
	
	public OrgPolicyUserLogListResponse() {
		super();
	}

	public OrgPolicyUserLogListResponse(ResponseStatus s) {
		super(s);

	}

	public List<OrgPolicyUserLog> getUserLogList() {
		return userLogList;
	}

	public void setUserLogList(List<OrgPolicyUserLog> userLogList) {
		this.userLogList = userLogList;
	}







	
	
}
