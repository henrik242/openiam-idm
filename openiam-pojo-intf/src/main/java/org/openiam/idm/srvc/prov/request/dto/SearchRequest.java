package org.openiam.idm.srvc.prov.request.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * SearchRequest is a DTO enabling ad-hoc searches. Define the values by which the search should be carried and then
 * pass it on to the search operation.
 * @author Suneet Shah
 *
 */
public class SearchRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8956796587364871510L;
	private String requestId;
	private String requestorId;
	private String status;
	private Date startDate;
	private Date endDate;
	private String approverId;
	private int level;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestorId() {
		return requestorId;
	}
	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
