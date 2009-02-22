package org.openiam.idm.srvc.prov.request.service;

import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
/*
 * Service interface to manage provisioning requests
 */
public interface RequestDataService {

	void addRequest(ProvisionRequest request);
	void updateRequest(ProvisionRequest request);
	/**
	 * Removes a request from the system.
	 * @param requestId
	 */
	void removeRequest(String requestId);
	
	/**
	 * Sets the status of the request.
	 * @param requestId
	 * @param approverId - The person who changed the request
	 * @param status - New status of the request
	 */
	void setRequestStatus(String requestId, String approverId, String status);
	
	/**
	 * Returns a request
	 * @param requestId
	 * @return
	 */
	ProvisionRequest getRequest(String requestId);
	
	/**
	 * Method to carry out adhoc search;
	 * @param search
	 * @return
	 */
	ProvisionRequest[] search(SearchRequest search);
	
	
}
