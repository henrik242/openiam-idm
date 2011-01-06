package org.openiam.idm.srvc.prov.request.dto;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Object represents an approver for a request.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestApprover", propOrder = {
    "reqApproverId",
    "approverId",
    "approverLevel",
    "approverType",
    "requestId",
    "actionDate",
    "action",
    "comment",
    "status",
    "mngSysGroupId",
    "managedSysId"
})
public class RequestApprover implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -404296971055977744L;
	protected String reqApproverId;
	protected String approverId;
	protected Integer approverLevel;
	protected String approverType;
	protected String requestId;
    @XmlSchemaType(name = "dateTime")
	protected Date actionDate;
	protected String action;
	protected String comment;
	protected String status;
	
	protected String mngSysGroupId;
	protected String managedSysId;


	public RequestApprover() {
	}


	public String getReqApproverId() {
		return reqApproverId;
	}


	public void setReqApproverId(String reqApproverId) {
		this.reqApproverId = reqApproverId;
	}


	public String getApproverId() {
		return approverId;
	}


	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}


	


	public String getApproverType() {
		return approverType;
	}


	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public Date getActionDate() {
		return actionDate;
	}


	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getMngSysGroupId() {
		return mngSysGroupId;
	}


	public void setMngSysGroupId(String mngSysGroupId) {
		this.mngSysGroupId = mngSysGroupId;
	}


	public String getManagedSysId() {
		return managedSysId;
	}


	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getApproverLevel() {
		return approverLevel;
	}


	public void setApproverLevel(Integer approverLevel) {
		this.approverLevel = approverLevel;
	}



}
