package org.openiam.idm.srvc.prov.request.dto;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
/**
 * Domain object for a provisioning request
 */
public class ProvisionRequest implements java.io.Serializable {

	private String requestId;
	private String requestorId;
	private Date requestDate;
	private String status;
	private Date statusDate;
	private String requestReason;
	private String requestType;
	
	private Set<ManagedSys> managedSyses = new HashSet<ManagedSys>(0);
	private Set<RequestAttribute> requestAttributes = new HashSet<RequestAttribute>(0);
	private Set<ApprovalHistory> approvalHistories = new HashSet<ApprovalHistory>(0);
	private Set<RequestAttachment> requestAttachments = new HashSet<RequestAttachment>(0);
	private Set<RequestUser> requestUsers = new HashSet<RequestUser>(0);

	public ProvisionRequest() {
	}

	public ProvisionRequest(String requestId) {
		this.requestId = requestId;
	}

	public ProvisionRequest(String requestId, String requestorId, Date requestDate,
			String status, Date statusDate, String requestReason,
			Set<ManagedSys> managedSyses,
			Set<RequestAttribute> requestAttributes,
			Set<ApprovalHistory> approvalHistories,
			Set<RequestAttachment> requestAttachments,
			Set<RequestUser> requestUserLists) {
		this.requestId = requestId;
		this.requestorId = requestorId;
		this.requestDate = requestDate;
		this.status = status;
		this.statusDate = statusDate;
		this.requestReason = requestReason;
		this.managedSyses = managedSyses;
		this.requestAttributes = requestAttributes;
		this.approvalHistories = approvalHistories;
		this.requestAttachments = requestAttachments;
		this.requestUsers = requestUserLists;
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestorId() {
		return this.requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getRequestReason() {
		return this.requestReason;
	}

	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}

	public Set<ManagedSys> getManagedSyses() {
		return this.managedSyses;
	}

	public void setManagedSyses(Set<ManagedSys> managedSyses) {
		this.managedSyses = managedSyses;
	}

	public Set<RequestAttribute> getRequestAttributes() {
		return this.requestAttributes;
	}

	public void setRequestAttributes(Set<RequestAttribute> requestAttributes) {
		this.requestAttributes = requestAttributes;
	}

	public Set<ApprovalHistory> getApprovalHistories() {
		return this.approvalHistories;
	}

	public void setApprovalHistories(Set<ApprovalHistory> approvalHistories) {
		this.approvalHistories = approvalHistories;
	}

	public Set<RequestAttachment> getRequestAttachments() {
		return this.requestAttachments;
	}

	public void setRequestAttachments(Set<RequestAttachment> requestAttachments) {
		this.requestAttachments = requestAttachments;
	}

	public Set<RequestUser> getRequestUsers() {
		return this.requestUsers;
	}

	public void setRequestUsers(Set<RequestUser> requestUserLists) {
		this.requestUsers = requestUserLists;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
