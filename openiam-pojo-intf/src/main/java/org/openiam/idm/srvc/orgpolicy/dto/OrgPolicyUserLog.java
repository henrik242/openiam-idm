package org.openiam.idm.srvc.orgpolicy.dto;

// Generated Nov 29, 2009 2:09:09 AM by Hibernate Tools 3.2.2.GA

import java.util.Date;

/**
 * OrgPolicyUserLog represents a log entry for a user accepting or rejecting an organization policy.
 */
public class OrgPolicyUserLog implements java.io.Serializable {

	private String orgPolicyLogId;
	private String orgPolicyId;
	private String userId;
	private Date timeStamp;
	private String response;

	public OrgPolicyUserLog() {
	}

	public OrgPolicyUserLog(String orgPolicyLogId, String orgPolicyId,
			String userId, Date timeStamp) {
		this.orgPolicyLogId = orgPolicyLogId;
		this.orgPolicyId = orgPolicyId;
		this.userId = userId;
		this.timeStamp = timeStamp;
	}

	public OrgPolicyUserLog(String orgPolicyLogId, String orgPolicyId,
			String userId, Date timeStamp, String response) {
		this.orgPolicyLogId = orgPolicyLogId;
		this.orgPolicyId = orgPolicyId;
		this.userId = userId;
		this.timeStamp = timeStamp;
		this.response = response;
	}

	public String getOrgPolicyLogId() {
		return this.orgPolicyLogId;
	}

	public void setOrgPolicyLogId(String orgPolicyLogId) {
		this.orgPolicyLogId = orgPolicyLogId;
	}

	public String getOrgPolicyId() {
		return this.orgPolicyId;
	}

	public void setOrgPolicyId(String orgPolicyId) {
		this.orgPolicyId = orgPolicyId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
