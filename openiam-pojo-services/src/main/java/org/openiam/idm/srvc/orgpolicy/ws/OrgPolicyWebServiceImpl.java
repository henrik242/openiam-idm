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

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicy;
import org.openiam.idm.srvc.orgpolicy.dto.OrgPolicyUserLog;
import org.openiam.idm.srvc.orgpolicy.service.OrgPolicyService;

/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.orgpolicy.ws.OrgPolicyWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/orgpolicy/service", 
		portName = "OrgPolicyPort", 
		serviceName = "OrgPolicyWebService")
public class OrgPolicyWebServiceImpl implements OrgPolicyWebService {

	OrgPolicyService acceptService;
	

	public OrgPolicyResponse addPolicyMessage(OrgPolicy msg) {
		OrgPolicyResponse resp = new OrgPolicyResponse(ResponseStatus.SUCCESS);
		OrgPolicy accept = acceptService.addPolicyMessage(msg);
		if (accept.getOrgPolicyId() == null || accept.getOrgPolicyId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAcceptanceMsg(accept);
		}
		return resp;
	}


	public OrgPolicyListResponse getActiveOrgPoliciesForUser(
			String userId) {
		OrgPolicyListResponse resp = new OrgPolicyListResponse(ResponseStatus.SUCCESS);
		List<OrgPolicy> acceptList = acceptService.getActiveOrgPoliciesForUser(userId);
		if (acceptList == null || acceptList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPolicyAcceptanceList(acceptList);
		}
		return resp;
	}


	public OrgPolicyListResponse getAllPolicyMessages() {
		OrgPolicyListResponse resp = new OrgPolicyListResponse(ResponseStatus.SUCCESS);
		List<OrgPolicy> acceptList = acceptService.getAllPolicyMessages();
		if (acceptList == null || acceptList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setPolicyAcceptanceList(acceptList);
		}
		return resp;
	}


	public OrgPolicyResponse getPolicyMessageById(String id) {
		OrgPolicyResponse resp = new OrgPolicyResponse(ResponseStatus.SUCCESS);
		OrgPolicy accept = acceptService.getPolicyMessageById(id);
		if (accept.getOrgPolicyId() == null || accept.getOrgPolicyId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAcceptanceMsg(accept);
		}
		return resp;
	}


	public Response removePolicyMessage(String messageId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		acceptService.removePolicyMessage(messageId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.orgpolicy.ws.OrgPolicyWebService#logUserResponse(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response logUserResponse(String orgPolicyId, String userId,
			String response) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		acceptService.logUserResponse(orgPolicyId, userId, response);
		return resp;	
	}

	
	public OrgPolicyResponse updatePolicyMessage(
			OrgPolicy msg) {
		OrgPolicyResponse resp = new OrgPolicyResponse(ResponseStatus.SUCCESS);
		OrgPolicy accept = acceptService.updatePolicyMessage(msg);
		if (accept.getOrgPolicyId() == null || accept.getOrgPolicyId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setAcceptanceMsg(accept);
		}
		return resp;
	}

	public OrgPolicyService getAcceptService() {
		return acceptService;
	}

	public void setAcceptService(OrgPolicyService acceptService) {
		this.acceptService = acceptService;
	}


	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.orgpolicy.ws.OrgPolicyWebService#getLogEntryForUser(java.lang.String)
	 */
	public OrgPolicyUserLogListResponse getLogEntryForUser(String userId) {
		OrgPolicyUserLogListResponse resp = new OrgPolicyUserLogListResponse(ResponseStatus.SUCCESS);
		List<OrgPolicyUserLog> acceptList = acceptService.getLogEntryForUser(userId);
		if (acceptList == null || acceptList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setUserLogList(acceptList);
		}
		return resp;
	}



}
