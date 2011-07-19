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
    "actionList",
    "customAttr1",
    "customAttrValue1",
    "reason",
    "objectTypeId",
    "actionId",
    "objectId",
    "requestId",
    "sessionId"
    
})
public class SearchAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3894692584016172289L;
	private String applicationName;
	private String userId;
	private String loginId;
	@XmlSchemaType(name = "dateTime")
	private Date startDate;
	@XmlSchemaType(name = "dateTime")
	private Date endDate;
	private String[] actionList;
	private String srcSystemId;
	private String customAttr1;
	private String customAttrValue1;
	private String reason;
	
    private String objectTypeId;
    private String objectId;
    private String actionId;
    private String requestId;
    private String sessionId;
	
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
	public String getCustomAttr1() {
		return customAttr1;
	}
	public void setCustomAttr1(String customAttr1) {
		this.customAttr1 = customAttr1;
	}
	public String getCustomAttrValue1() {
		return customAttrValue1;
	}
	public void setCustomAttrValue1(String customAttrValue1) {
		this.customAttrValue1 = customAttrValue1;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getObjectTypeId() {
		return objectTypeId;
	}
	public void setObjectTypeId(String objectTypeId) {
		this.objectTypeId = objectTypeId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
}
