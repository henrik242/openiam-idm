package org.openiam.idm.srvc.prov.request.dto;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
/**
 * Domain object for a provisioning request
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvisionRequest", propOrder = {
    "requestId",
    "requestorId",
    "requestDate",
    "status",
    "statusDate",
    "requestReason",
    "requestType",
    "requestXML",
    "managedResourceId",
    "changeAccessBy",
    "newRoleId",
    "newServiceId",
    "managedSyses",
    "requestAttributes",
    "requestApprovers",
    "requestAttachments",
    "requestUsers"
})
public class ProvisionRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5432383771223874649L;
	protected String requestId;
	protected String requestorId;
	protected Date requestDate;
	protected String status;
    @XmlSchemaType(name = "dateTime")
	protected Date statusDate;
	protected String requestReason;
	protected String requestType;
	protected String requestXML;
	protected String managedResourceId;
	
	protected String changeAccessBy;
	protected String newRoleId;
	protected String newServiceId;
	
	protected Set<ManagedSys> managedSyses = new HashSet<ManagedSys>(0);
	protected Set<RequestAttribute> requestAttributes = new HashSet<RequestAttribute>(0);
	protected Set<RequestApprover> requestApprovers = new HashSet<RequestApprover>(0);
	protected Set<RequestAttachment> requestAttachments = new HashSet<RequestAttachment>(0);
	protected Set<RequestUser> requestUsers = new HashSet<RequestUser>(0);

	public ProvisionRequest() {
	}

	public ProvisionRequest(String requestId) {
		this.requestId = requestId;
	}

	public ProvisionRequest(String requestId, String requestorId, Date requestDate,
			String status, Date statusDate, String requestReason,
			Set<ManagedSys> managedSyses,
			Set<RequestAttribute> requestAttributes,
			Set<RequestApprover> requestApprovers,
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
		this.requestApprovers = requestApprovers;
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

	public Set<RequestApprover> getRequestApprovers() {
		return requestApprovers;
	}

	public void setRequestApprovers(Set<RequestApprover> requestApprovers) {
		this.requestApprovers = requestApprovers;
	}



	public String getChangeAccessBy() {
		return changeAccessBy;
	}

	public void setChangeAccessBy(String changeAccessBy) {
		this.changeAccessBy = changeAccessBy;
	}

	public String getRequestXML() {
		return requestXML;
	}

	public void setRequestXML(String requestXML) {
		this.requestXML = requestXML;
	}

	public String getNewRoleId() {
		return newRoleId;
	}

	public void setNewRoleId(String newRoleId) {
		this.newRoleId = newRoleId;
	}

	public String getNewServiceId() {
		return newServiceId;
	}

	public void setNewServiceId(String newServiceId) {
		this.newServiceId = newServiceId;
	}

	public String getManagedResourceId() {
		return managedResourceId;
	}

	public void setManagedResourceId(String managedResourceId) {
		this.managedResourceId = managedResourceId;
	}


}
