package org.openiam.idm.srvc.audit.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
/**
 * Captures parameters to query the audit log
 * @author suneet
 * @version 2
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchAudit", propOrder = {
    "applicationName",
    "userId",
    "loginId",
    "startDate",
    "endDate",
    "srcSystemId",
    "actionList"
})
public class SearchAudit implements Serializable {

	private String applicationName;
	private String userId;
	private String loginId;
	@XmlSchemaType(name = "dateTime")
	private Date startDate;
	@XmlSchemaType(name = "dateTime")
	private Date endDate;
	private String[] actionList;
	private String srcSystemId;
	
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
	public String getSrcSystemId() {
		return srcSystemId;
	}
	public void setSrcSystemId(String srcSystemId) {
		this.srcSystemId = srcSystemId;
	}

	
}
