package org.openiam.idm.srvc.prov.request.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataServiceImpl;
import org.openiam.idm.srvc.prov.request.dto.ApprovalHistory;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;

/*
 * Service implementation to manage provisioning requests
 */
public class RequestDataServiceImpl implements RequestDataService {
	private static final Log log = LogFactory.getLog(RequestDataServiceImpl.class);
	

	ProvisionRequestDAO requestDao;
	
	public void addRequest(ProvisionRequest request) {
		if (request == null) {
			throw new NullPointerException("request is null");
		}
		requestDao.add(request);

	}

	public ProvisionRequest getRequest(String requestId) {
		if (requestId == null) {
			throw new NullPointerException("requestId is null");
		}
		return requestDao.findById(requestId);
	}

	public void removeRequest(String requestId) {
		if (requestId == null) {
			throw new NullPointerException("requestId is null");
		}
		ProvisionRequest request = new ProvisionRequest();
		request.setRequestId(requestId);
		requestDao.remove(request);

	}

	public ProvisionRequest[] search(SearchRequest search) {
		// TODO Auto-generated method stub
		
		List<ProvisionRequest> reqList = requestDao.findByStatus("OPEN");
		if (reqList == null || reqList.size() == 0)
			return null;
		ProvisionRequest[] reqAry = new ProvisionRequest[reqList.size()];
		reqList.toArray(reqAry);
		
		return reqAry;
	}

	public void setRequestStatus(String requestId, String approverId, String status) {
		if (requestId == null) {
			throw new NullPointerException("requestId is null");
		}
		if (approverId == null) {
			throw new NullPointerException("userId is null");
		}
		if (status == null) {
			throw new NullPointerException("status is null");
		}
	 	ProvisionRequest request = requestDao.findById(requestId);
		request.setStatus(status);
		request.setStatusDate(new Date(System.currentTimeMillis()));
		
		Set<ApprovalHistory> apprHistSet = request.getApprovalHistories();
		ApprovalHistory history = new ApprovalHistory();
		history.setAction(status);
		history.setActionDate(new Date(System.currentTimeMillis()));
		history.setReqApproverId(approverId);
		history.setProvRequestId(requestId);
		apprHistSet.add(history);
		
		requestDao.update(request);
	}

	public void updateRequest(ProvisionRequest request) {
		if (request == null) {
			throw new NullPointerException("request is null");
		}
		requestDao.update(request);
	}

	public ProvisionRequestDAO getRequestDao() {
		return requestDao;
	}

	public void setRequestDao(ProvisionRequestDAO requestDao) {
		this.requestDao = requestDao;
	}

}
