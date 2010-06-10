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

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.prov.request.service.RequestDataService;

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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#approve()
	 */
	public Response approve() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#getRequest(java.lang.String)
	 */
	public ProvisionReqResponse getRequest(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#initiateRequest(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public ProvisionReqResponse initiateRequest(ProvisionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#reject()
	 */
	public Response reject() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.ws.RequestWebService#search(org.openiam.idm.srvc.prov.request.dto.SearchRequest)
	 */
	public ProvisionReqListResponse search(SearchRequest search) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public RequestDataService getProvRequestService() {
		return provRequestService;
	}

	public void setProvRequestService(RequestDataService provRequestService) {
		this.provRequestService = provRequestService;
	}

}
