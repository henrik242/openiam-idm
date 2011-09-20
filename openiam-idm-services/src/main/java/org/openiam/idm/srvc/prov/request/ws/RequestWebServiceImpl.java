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
package org.openiam.idm.srvc.prov.request.ws;

import java.util.List;

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;
import org.openiam.idm.srvc.user.dto.Supervisor;

/**
 * Implementation object for the request web service.
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.prov.request.ws.RequestWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/prov/request/service", 
		serviceName = "RequestWebService")
public class RequestWebServiceImpl implements RequestWebService {

	RequestDataService provRequestService;
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#addRequest(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public ProvisionReqResponse addRequest(ProvisionRequest request) {
		System.out.println("Add request operation called.");
		ProvisionReqResponse resp = new ProvisionReqResponse(ResponseStatus.SUCCESS);
		provRequestService.addRequest(request);
		resp.setRequest(request);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#approve()
	 */
	public Response approve(String requestId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		provRequestService.approve(requestId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#getRequest(java.lang.String)
	 */
	public ProvisionReqResponse getRequest(String requestId) {
		ProvisionRequest provReq = provRequestService.getRequest(requestId);
		ProvisionReqResponse resp = new ProvisionReqResponse(ResponseStatus.SUCCESS);
		resp.setRequest(provReq);
		return resp;
	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#reject()
	 */
	public Response reject(String requestId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		provRequestService.reject(requestId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#removeRequest(java.lang.String)
	 */
	public Response removeRequest(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#requestByApprover(java.lang.String, java.lang.String)
	 */
	public ProvisionReqListResponse requestByApprover(String approverId,
			String status) {
		List<ProvisionRequest> provReq = provRequestService.requestByApprover(approverId, status);
		ProvisionReqListResponse resp = new ProvisionReqListResponse(ResponseStatus.SUCCESS);
		resp.setReqList(provReq);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#search(org.openiam.idm.srvc.prov.request.dto.SearchRequest)
	 */
	public ProvisionReqListResponse search(SearchRequest search) {
		ProvisionReqListResponse resp = new ProvisionReqListResponse(ResponseStatus.SUCCESS);
		List<ProvisionRequest> reqList = provRequestService.search(search);
		System.out.println("Request search results=" + reqList);
		System.out.println("Search parameters=" + search);
		
		if ( reqList != null && !reqList.isEmpty()) {
			resp.setReqList(reqList);
		}else {
			resp.setStatus(ResponseStatus.FAILURE);
		}
		
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#setRequestStatus(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Response setRequestStatus(String requestId, String approverId,
			String status) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#updateRequest(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public ProvisionReqResponse updateRequest(ProvisionRequest request) {
		System.out.println("Update request operation called.");
		ProvisionReqResponse resp = new ProvisionReqResponse(ResponseStatus.SUCCESS);
		provRequestService.updateRequest(request);
		resp.setRequest(request);
		return resp;
	}

	public RequestDataService getProvRequestService() {
		return provRequestService;
	}

	public void setProvRequestService(RequestDataService provRequestService) {
		this.provRequestService = provRequestService;
	}

}
