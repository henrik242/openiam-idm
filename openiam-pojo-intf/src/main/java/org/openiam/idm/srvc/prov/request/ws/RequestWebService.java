package org.openiam.idm.srvc.prov.request.ws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;

/*
 * Web Service interface to manage provisioning requests
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/prov/request/service", name = "RequestWebService")
public interface RequestWebService {

	@WebMethod
	ProvisionReqResponse addRequest(
			@WebParam(name = "request", targetNamespace = "")
			ProvisionRequest request);
	ProvisionReqResponse updateRequest(ProvisionRequest request);
	/**
	 * Removes a request from the system.
	 * @param requestId
	 */
	@WebMethod
	Response removeRequest(
			@WebParam(name = "requestId", targetNamespace = "")
			String requestId);
	
	/**
	 * Sets the status of the request.
	 * @param requestId
	 * @param approverId - The person who changed the request
	 * @param status - New status of the request
	 */
	@WebMethod
	Response setRequestStatus(
			@WebParam(name = "requestId", targetNamespace = "")
			String requestId, 
			@WebParam(name = "approverId", targetNamespace = "")
			String approverId, 
			@WebParam(name = "status", targetNamespace = "")
			String status);
	
	/**
	 * Returns a request
	 * @param requestId
	 * @return
	 */
	@WebMethod
	ProvisionReqResponse getRequest(
			@WebParam(name = "requestId", targetNamespace = "")
			String requestId);
	
	/**
	 * Method to carry out adhoc search;
	 * @param search
	 * @return
	 */
	@WebMethod
	ProvisionReqListResponse search(
			@WebParam(name = "search", targetNamespace = "")
			SearchRequest search);
	
	@WebMethod
	ProvisionReqListResponse requestByApprover(
			@WebParam(name = "approverId", targetNamespace = "")
			String approverId, 
			@WebParam(name = "status", targetNamespace = "")
			String status);
	
	/**
	 * Creates a request and then sends a notification to the approver
	 * @param request
	 */
	@WebMethod
	ProvisionReqResponse initiateRequest(
			@WebParam(name = "request", targetNamespace = "")
			ProvisionRequest request);
	
	@WebMethod
	Response approve();
	
	@WebMethod
	Response reject();
	
	
	
}
