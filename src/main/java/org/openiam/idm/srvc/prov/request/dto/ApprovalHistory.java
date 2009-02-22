package org.openiam.idm.srvc.prov.request.dto;

// Generated Jan 9, 2009 5:33:58 PM by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * Object to track the approval / rejection history of a request and the users that are associated with these actions.
 */
public class ApprovalHistory implements java.io.Serializable {

	private String apprvHistId;
	private String provRequestId;
	private String reqApproverId;
	private Date actionDate;
	private String action;

	
	
	public ApprovalHistory() {
	}



	public ApprovalHistory(String action, Date actionDate, String apprvHistId,
			String provRequestId, String reqApproverId) {
		super();
		this.action = action;
		this.actionDate = actionDate;
		this.apprvHistId = apprvHistId;
		this.provRequestId = provRequestId;
		this.reqApproverId = reqApproverId;
	}



	public String getApprvHistId() {
		return this.apprvHistId;
	}

	public void setApprvHistId(String apprvHistId) {
		this.apprvHistId = apprvHistId;
	}



	public Date getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}



	public String getProvRequestId() {
		return provRequestId;
	}



	public void setProvRequestId(String provRequestId) {
		this.provRequestId = provRequestId;
	}



	public String getReqApproverId() {
		return reqApproverId;
	}



	public void setReqApproverId(String reqApproverId) {
		this.reqApproverId = reqApproverId;
	}

}
