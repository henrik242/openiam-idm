package org.openiam.selfsrvc.prov;

import java.io.Serializable;
import java.util.*;

public class RequestSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1464001135162594578L;
	private String status;
	private Date startDate;
	private Date endDate;
	private String requestorId;
	
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



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}
}
