package org.openiam.idm.srvc.prov.request.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.base.ws.ResponseCode;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ResourceApprover;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.msg.service.MailService;
import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.RequestApprover;
import org.openiam.idm.srvc.prov.request.dto.RequestUser;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;
import org.openiam.idm.srvc.user.dto.Supervisor;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;

/*
 * Service implementation to manage provisioning requests
 */
public class RequestDataServiceImpl implements RequestDataService {
	private static final Log log = LogFactory.getLog(RequestDataServiceImpl.class);
	

	protected ProvisionRequestDAO requestDao;
	protected ManagedSystemDataService  managedResource;
	protected UserDataService userManager;
	protected MailService mailSender;
	
	protected String defaultSender;
	protected String subjectPrefix;
	
	
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

	public List<ProvisionRequest> search(SearchRequest search) {
		
		List<ProvisionRequest> reqList = requestDao.search(search);
		if (reqList == null || reqList.size() == 0)
			return null;
		
		return reqList;
	}
	
	public List<ProvisionRequest> requestByApprover(String approverId, String status) {
		List<ProvisionRequest> reqList = requestDao.findRequestByApprover(approverId, status);
		if (reqList == null || reqList.size() == 0) {
			return null;
		}
		return reqList;
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
		
	/*	Set<ApprovalHistory> apprHistSet = request.getApprovalHistories();
		ApprovalHistory history = new ApprovalHistory();
		history.setAction(status);
		history.setActionDate(new Date(System.currentTimeMillis()));
		history.setReqApproverId(approverId);
		history.setProvRequestId(requestId);
		apprHistSet.add(history);
	*/	
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

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.RequestDataService#nextApproverForRequest(java.lang.String, java.lang.String)
	 */
	public List<User> nextApproverForRequest(String requestId,	String resourceName) {
		// get the current approval level for the request
		// get the resource approver for the level
		// look at the approver type
		// -- if its SUPERVISOR - LOOK UP THE SUPERVISOR ON THE REQUEST
		// -- if its a group - look up the users in the
		// -- otherwise its an individual user.
		

		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.RequestDataService#approve()
	 */
	public void approve() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.RequestDataService#initiateRequest(org.openiam.idm.srvc.prov.request.dto.ProvisionRequest)
	 */
	public ProvisionRequest initiateRequest(ProvisionRequest request) {
		log.info("initiateRequest called.");
		
		if (request == null) {
			throw new NullPointerException("request is null");
		}
		// get the approver for this resource
		String resourceId = request.getManagedResourceId();
		ManagedSys managedSys = managedResource.getManagedSys(resourceId);
		ResourceApprover approver =  getApprover(managedSys,"START", 1 );
		
		if (approver == null) {
			log.error("Approver for managedSys=" + resourceId + " action=start level=1 not found"  );
			return null;
		}
		// determine the approver.
		RequestApprover reqApprover = new RequestApprover();
		ResponseCode cd = getApprover(approver, request ,reqApprover);
		if (!cd.equals("success")) {
			// an error occurred
			log.error("Response code from getApprover=" + cd.toString());
		}
		
		// add the approver information to this request.
		reqApprover.setStatus("PENDING APPROVAL");
		reqApprover.setApproverLevel(1);
		request.getRequestApprovers().add(reqApprover);

		
		// create the request
		requestDao.add(request);
		
		// notify the approver
		sendNotification(reqApprover, request.getRequestReason(), request.getRequestorId());
		
		return request;
		
	}
	
	private void sendNotification(RequestApprover reqApprover, String reason, String requestorId) {
		
		String approverId = reqApprover.getApproverId();
		User approverDetail = userManager.getUserWithDependent(approverId, false);
		User requestor = userManager.getUserWithDependent(requestorId, false);
		
		// TODO Move this into a template engine 
		
		String msg = "A request for " + reason + " by " + requestor.getFirstName() + " " + requestor.getLastName() + 
			 " is pending approval \n" +
			 "Please log into the self-service application to process this request.\n" +
			 "http://localhost:8080/selfservice .\n";
		
		mailSender.send(this.defaultSender,
				approverDetail.getEmail(), 
				this.getSubjectPrefix() + " " + reason + " Request", 
				msg);
		
		
	}
	
	private ResponseCode getApprover(ResourceApprover approver, ProvisionRequest request, RequestApprover reqApprover) {
		if (approver.getApproverType().equalsIgnoreCase("SUPERVISOR")) {
			
			Set<RequestUser> reqUserSet = request.getRequestUsers();
			if (reqUserSet != null && ! reqUserSet.isEmpty()) {
				Iterator<RequestUser> it = reqUserSet.iterator();
				while (it.hasNext()) {
					RequestUser reqUser = it.next();
					log.info("Request for userID= " + reqUser.getUserId());
					// get the users supervisor
					List<Supervisor> supervisorList =  userManager.getSupervisors(reqUser.getUserId());
					if (supervisorList == null) {
						return ResponseCode.SUPERVISOR_NOT_FOUND;
					}
					Supervisor superVisor = supervisorList.get(0);
					String supervisorUserId = superVisor.getSupervisor().getUserId();
					
					reqApprover.setApproverId(supervisorUserId);
					reqApprover.setApproverType("SUPERVISOR");
					reqApprover.setManagedSysId(request.getManagedResourceId());
					return ResponseCode.SUCCESS;
					
				}
			}
			
		}else if (approver.getApproverType().equalsIgnoreCase("GROUP")) {
			
		}else {
			// single approver
			String approverId =  approver.getApproverId();
		}
		return null;
		
	}
	
	private ResourceApprover getApprover(ManagedSys managedSys, String action, int level) {
		Set<ResourceApprover> approverSet =  managedSys.getResourceApprovers();
		Iterator<ResourceApprover> it = approverSet.iterator();
		while (it.hasNext()) {
			ResourceApprover aprv = it.next();
			if (aprv.getApproverLevel() == level && 
				aprv.getTaskAction().equalsIgnoreCase(action)) {
				return aprv;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.prov.request.service.RequestDataService#reject()
	 */
	public void reject() {
		// TODO Auto-generated method stub
		
	}

	public ManagedSystemDataService getManagedResource() {
		return managedResource;
	}

	public void setManagedResource(ManagedSystemDataService managedResource) {
		this.managedResource = managedResource;
	}

	public UserDataService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDataService userManager) {
		this.userManager = userManager;
	}


	public String getDefaultSender() {
		return defaultSender;
	}

	public void setDefaultSender(String defaultSender) {
		this.defaultSender = defaultSender;
	}

	public String getSubjectPrefix() {
		return subjectPrefix;
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public MailService getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailService mailSender) {
		this.mailSender = mailSender;
	}



}
