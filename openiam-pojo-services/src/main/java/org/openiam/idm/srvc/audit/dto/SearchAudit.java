package org.openiam.idm.srvc.audit.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
/**
 * Captures parameters to query the audit log
 * @author suneet
 * @version 2
 *
 */
public class SearchAudit implements Serializable {

	private String applicationName;
	private String userId;
	private String loginId;
	private Date startDate;
	private Date endDate;
	private String[] actionList;
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	public void setEndDate(Date endData) {
		this.endDate = endData;
	}
	/**
	 * Returns an array of actions to search for
	 * @return
	 */
	public String[] getActionList() {
		return actionList;
	}
	/**
	 * Sets an array of actions to search on
	 * @param actionList
	 */
	public void setActionList(String[] actionList) {
		this.actionList = actionList;
	}

	
}
