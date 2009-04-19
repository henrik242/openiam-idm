package org.openiam.idm.srvc.audit.dto;
// Generated Nov 30, 2007 3:01:45 AM by Hibernate Tools 3.2.0.b11


import java.util.Date;

/**
 * DTO object that is used log and retrieve audit information
 */
public class IdmAuditLog  implements java.io.Serializable {

     private String logId;
     private String objectTypeId;
     private String actionId;
     private String actionStatus;
     private String reason;
     private String reasonDetail;
     private Date actionDatetime;
     private String objectName;
     private String resourceName;
     private String userId;
     private String serviceId;
     private String loginId;
     private String host;
     private String clientId;
     private String reqUrl;
     private String attributesChanges;
     private String customAttrname1;
     private String customAttrname2;
     private String customAttrname3;
     private String customAttrname4;
     private String customAttrname5;
     private String customAttrname6;
     private String customAttrname7;
     private String customAttrname8;
     private String customAttrname9;
     private String customAttrname10;
     private String customAttrvalue1;
     private String customAttrvalue2;
     private String customAttrvalue3;
     private String customAttrvalue4;
     private String customAttrvalue5;
     private String customAttrvalue6;
     private String customAttrvalue7;
     private String customAttrvalue8;
     private String customAttrvalue9;
     private String customAttrvalue10;
     private String customParamname1;
     private String customParamname2;
     private String customParamname3;
     private String customParamname4;
     private String customParamname5;
     private String customParamname6;
     private String customParamname7;
     private String customParamname8;
     private String customParamname9;
     private String customParamname10;
     private String customParamvalue1;
     private String customParamvalue2;
     private String customParamvalue3;
     private String customParamvalue4;
     private String customParamvalue5;
     private String customParamvalue6;
     private String customParamvalue7;
     private String customParamvalue8;
     private String customParamvalue9;
     private String customParamvalue10;

     private String linkedLogId;
     private int linkSequence = 0;
     private String logHash;

     
	public IdmAuditLog() {
    }
	
    public IdmAuditLog(String logId, String objectTypeId, String actionId, Date actionDatetime, String userId) {
        this.logId = logId;
        this.objectTypeId = objectTypeId;
        this.actionId = actionId;
        this.actionDatetime = actionDatetime;
        this.userId = userId;
    }
    
    
    
    public IdmAuditLog(Date actionDatetime, String actionId,
			String actionStatus, String attributesChanges, String clientId,
			String host, int linkSequence, String linkedLogId, String logHash,
			String logId, String loginId, String objectName,
			String objectTypeId, String reason, String reasonDetail,
			String reqUrl, String resourceName, String serviceId, String userId) {
		super();
		this.actionDatetime = actionDatetime;
		this.actionId = actionId;
		this.actionStatus = actionStatus;
		this.attributesChanges = attributesChanges;
		this.clientId = clientId;
		this.host = host;
		this.linkSequence = linkSequence;
		this.linkedLogId = linkedLogId;
		this.logHash = logHash;
		this.logId = logId;
		this.loginId = loginId;
		this.objectName = objectName;
		this.objectTypeId = objectTypeId;
		this.reason = reason;
		this.reasonDetail = reasonDetail;
		this.reqUrl = reqUrl;
		this.resourceName = resourceName;
		this.serviceId = serviceId;
		this.userId = userId;
	}

	public IdmAuditLog(String logId, String objectTypeId, String actionId, String actionStatus, String reason, String reasonDetail, Date actionDatetime, String objectName, String resourceName, String userId, String serviceId, String loginId, String host, String clientId, String reqUrl, String attributesChanges, String customAttrname1, String customAttrname2, String customAttrname3, String customAttrname4, String customAttrname5, String customAttrname6, String customAttrname7, String customAttrname8, String customAttrname9, String customAttrname10, String customAttrvalue1, String customAttrvalue2, String customAttrvalue3, String customAttrvalue4, String customAttrvalue5, String customAttrvalue6, String customAttrvalue7, String customAttrvalue8, String customAttrvalue9, String customAttrvalue10, String customParamname1, String customParamname2, String customParamname3, String customParamname4, String customParamname5, String customParamname6, String customParamname7, String customParamname8, String customParamname9, String customParamname10, String customParamvalue1, String customParamvalue2, String customParamvalue3, String customParamvalue4, String customParamvalue5, String customParamvalue6, String customParamvalue7, String customParamvalue8, String customParamvalue9, String customParamvalue10) {
       this.logId = logId;
       this.objectTypeId = objectTypeId;
       this.actionId = actionId;
       this.actionStatus = actionStatus;
       this.reason = reason;
       this.reasonDetail = reasonDetail;
       this.actionDatetime = actionDatetime;
       this.objectName = objectName;
       this.resourceName = resourceName;
       this.userId = userId;
       this.serviceId = serviceId;
       this.loginId = loginId;
       this.host = host;
       this.clientId = clientId;
       this.reqUrl = reqUrl;
       this.attributesChanges = attributesChanges;
       this.customAttrname1 = customAttrname1;
       this.customAttrname2 = customAttrname2;
       this.customAttrname3 = customAttrname3;
       this.customAttrname4 = customAttrname4;
       this.customAttrname5 = customAttrname5;
       this.customAttrname6 = customAttrname6;
       this.customAttrname7 = customAttrname7;
       this.customAttrname8 = customAttrname8;
       this.customAttrname9 = customAttrname9;
       this.customAttrname10 = customAttrname10;
       this.customAttrvalue1 = customAttrvalue1;
       this.customAttrvalue2 = customAttrvalue2;
       this.customAttrvalue3 = customAttrvalue3;
       this.customAttrvalue4 = customAttrvalue4;
       this.customAttrvalue5 = customAttrvalue5;
       this.customAttrvalue6 = customAttrvalue6;
       this.customAttrvalue7 = customAttrvalue7;
       this.customAttrvalue8 = customAttrvalue8;
       this.customAttrvalue9 = customAttrvalue9;
       this.customAttrvalue10 = customAttrvalue10;
       this.customParamname1 = customParamname1;
       this.customParamname2 = customParamname2;
       this.customParamname3 = customParamname3;
       this.customParamname4 = customParamname4;
       this.customParamname5 = customParamname5;
       this.customParamname6 = customParamname6;
       this.customParamname7 = customParamname7;
       this.customParamname8 = customParamname8;
       this.customParamname9 = customParamname9;
       this.customParamname10 = customParamname10;
       this.customParamvalue1 = customParamvalue1;
       this.customParamvalue2 = customParamvalue2;
       this.customParamvalue3 = customParamvalue3;
       this.customParamvalue4 = customParamvalue4;
       this.customParamvalue5 = customParamvalue5;
       this.customParamvalue6 = customParamvalue6;
       this.customParamvalue7 = customParamvalue7;
       this.customParamvalue8 = customParamvalue8;
       this.customParamvalue9 = customParamvalue9;
       this.customParamvalue10 = customParamvalue10;
    }

    public String getLinkedLogId() {
		return linkedLogId;
	}


	public void setLinkedLogId(String linkedLogId) {
		this.linkedLogId = linkedLogId;
	}


	public int getLinkSequence() {
		return linkSequence;
	}


	public void setLinkSequence(int linkSequence) {
		this.linkSequence = linkSequence;
	}


	public String getLogHash() {
		return logHash;
	}


	public void setLogHash(String logHash) {
		this.logHash = logHash;
	}
    
    public String getLogId() {
        return this.logId;
    }
    
    public void setLogId(String logId) {
        this.logId = logId;
    }
    public String getObjectTypeId() {
        return this.objectTypeId;
    }
    
    public void setObjectTypeId(String objectTypeId) {
        this.objectTypeId = objectTypeId;
    }
    public String getActionId() {
        return this.actionId;
    }
    
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
    public String getActionStatus() {
        return this.actionStatus;
    }
    
    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReasonDetail() {
        return this.reasonDetail;
    }
    
    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail;
    }
    public Date getActionDatetime() {
        return this.actionDatetime;
    }
    
    public void setActionDatetime(Date actionDatetime) {
        this.actionDatetime = actionDatetime;
    }
    public String getObjectName() {
        return this.objectName;
    }
    
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    public String getResourceName() {
        return this.resourceName;
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getLoginId() {
        return this.loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getHost() {
        return this.host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    public String getClientId() {
        return this.clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getReqUrl() {
        return this.reqUrl;
    }
    
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }
    public String getAttributesChanges() {
        return this.attributesChanges;
    }
    
    public void setAttributesChanges(String attributesChanges) {
        this.attributesChanges = attributesChanges;
    }
    public String getCustomAttrname1() {
        return this.customAttrname1;
    }
    
    public void setCustomAttrname1(String customAttrname1) {
        this.customAttrname1 = customAttrname1;
    }
    public String getCustomAttrname2() {
        return this.customAttrname2;
    }
    
    public void setCustomAttrname2(String customAttrname2) {
        this.customAttrname2 = customAttrname2;
    }
    public String getCustomAttrname3() {
        return this.customAttrname3;
    }
    
    public void setCustomAttrname3(String customAttrname3) {
        this.customAttrname3 = customAttrname3;
    }
    public String getCustomAttrname4() {
        return this.customAttrname4;
    }
    
    public void setCustomAttrname4(String customAttrname4) {
        this.customAttrname4 = customAttrname4;
    }
    public String getCustomAttrname5() {
        return this.customAttrname5;
    }
    
    public void setCustomAttrname5(String customAttrname5) {
        this.customAttrname5 = customAttrname5;
    }
    public String getCustomAttrname6() {
        return this.customAttrname6;
    }
    
    public void setCustomAttrname6(String customAttrname6) {
        this.customAttrname6 = customAttrname6;
    }
    public String getCustomAttrname7() {
        return this.customAttrname7;
    }
    
    public void setCustomAttrname7(String customAttrname7) {
        this.customAttrname7 = customAttrname7;
    }
    public String getCustomAttrname8() {
        return this.customAttrname8;
    }
    
    public void setCustomAttrname8(String customAttrname8) {
        this.customAttrname8 = customAttrname8;
    }
    public String getCustomAttrname9() {
        return this.customAttrname9;
    }
    
    public void setCustomAttrname9(String customAttrname9) {
        this.customAttrname9 = customAttrname9;
    }
    public String getCustomAttrname10() {
        return this.customAttrname10;
    }
    
    public void setCustomAttrname10(String customAttrname10) {
        this.customAttrname10 = customAttrname10;
    }
    public String getCustomAttrvalue1() {
        return this.customAttrvalue1;
    }
    
    public void setCustomAttrvalue1(String customAttrvalue1) {
        this.customAttrvalue1 = customAttrvalue1;
    }
    public String getCustomAttrvalue2() {
        return this.customAttrvalue2;
    }
    
    public void setCustomAttrvalue2(String customAttrvalue2) {
        this.customAttrvalue2 = customAttrvalue2;
    }
    public String getCustomAttrvalue3() {
        return this.customAttrvalue3;
    }
    
    public void setCustomAttrvalue3(String customAttrvalue3) {
        this.customAttrvalue3 = customAttrvalue3;
    }
    public String getCustomAttrvalue4() {
        return this.customAttrvalue4;
    }
    
    public void setCustomAttrvalue4(String customAttrvalue4) {
        this.customAttrvalue4 = customAttrvalue4;
    }
    public String getCustomAttrvalue5() {
        return this.customAttrvalue5;
    }
    
    public void setCustomAttrvalue5(String customAttrvalue5) {
        this.customAttrvalue5 = customAttrvalue5;
    }
    public String getCustomAttrvalue6() {
        return this.customAttrvalue6;
    }
    
    public void setCustomAttrvalue6(String customAttrvalue6) {
        this.customAttrvalue6 = customAttrvalue6;
    }
    public String getCustomAttrvalue7() {
        return this.customAttrvalue7;
    }
    
    public void setCustomAttrvalue7(String customAttrvalue7) {
        this.customAttrvalue7 = customAttrvalue7;
    }
    public String getCustomAttrvalue8() {
        return this.customAttrvalue8;
    }
    
    public void setCustomAttrvalue8(String customAttrvalue8) {
        this.customAttrvalue8 = customAttrvalue8;
    }
    public String getCustomAttrvalue9() {
        return this.customAttrvalue9;
    }
    
    public void setCustomAttrvalue9(String customAttrvalue9) {
        this.customAttrvalue9 = customAttrvalue9;
    }
    public String getCustomAttrvalue10() {
        return this.customAttrvalue10;
    }
    
    public void setCustomAttrvalue10(String customAttrvalue10) {
        this.customAttrvalue10 = customAttrvalue10;
    }
    public String getCustomParamname1() {
        return this.customParamname1;
    }
    
    public void setCustomParamname1(String customParamname1) {
        this.customParamname1 = customParamname1;
    }
    public String getCustomParamname2() {
        return this.customParamname2;
    }
    
    public void setCustomParamname2(String customParamname2) {
        this.customParamname2 = customParamname2;
    }
    public String getCustomParamname3() {
        return this.customParamname3;
    }
    
    public void setCustomParamname3(String customParamname3) {
        this.customParamname3 = customParamname3;
    }
    public String getCustomParamname4() {
        return this.customParamname4;
    }
    
    public void setCustomParamname4(String customParamname4) {
        this.customParamname4 = customParamname4;
    }
    public String getCustomParamname5() {
        return this.customParamname5;
    }
    
    public void setCustomParamname5(String customParamname5) {
        this.customParamname5 = customParamname5;
    }
    public String getCustomParamname6() {
        return this.customParamname6;
    }
    
    public void setCustomParamname6(String customParamname6) {
        this.customParamname6 = customParamname6;
    }
    public String getCustomParamname7() {
        return this.customParamname7;
    }
    
    public void setCustomParamname7(String customParamname7) {
        this.customParamname7 = customParamname7;
    }
    public String getCustomParamname8() {
        return this.customParamname8;
    }
    
    public void setCustomParamname8(String customParamname8) {
        this.customParamname8 = customParamname8;
    }
    public String getCustomParamname9() {
        return this.customParamname9;
    }
    
    public void setCustomParamname9(String customParamname9) {
        this.customParamname9 = customParamname9;
    }
    public String getCustomParamname10() {
        return this.customParamname10;
    }
    
    public void setCustomParamname10(String customParamname10) {
        this.customParamname10 = customParamname10;
    }
    public String getCustomParamvalue1() {
        return this.customParamvalue1;
    }
    
    public void setCustomParamvalue1(String customParamvalue1) {
        this.customParamvalue1 = customParamvalue1;
    }
    public String getCustomParamvalue2() {
        return this.customParamvalue2;
    }
    
    public void setCustomParamvalue2(String customParamvalue2) {
        this.customParamvalue2 = customParamvalue2;
    }
    public String getCustomParamvalue3() {
        return this.customParamvalue3;
    }
    
    public void setCustomParamvalue3(String customParamvalue3) {
        this.customParamvalue3 = customParamvalue3;
    }
    public String getCustomParamvalue4() {
        return this.customParamvalue4;
    }
    
    public void setCustomParamvalue4(String customParamvalue4) {
        this.customParamvalue4 = customParamvalue4;
    }
    public String getCustomParamvalue5() {
        return this.customParamvalue5;
    }
    
    public void setCustomParamvalue5(String customParamvalue5) {
        this.customParamvalue5 = customParamvalue5;
    }
    public String getCustomParamvalue6() {
        return this.customParamvalue6;
    }
    
    public void setCustomParamvalue6(String customParamvalue6) {
        this.customParamvalue6 = customParamvalue6;
    }
    public String getCustomParamvalue7() {
        return this.customParamvalue7;
    }
    
    public void setCustomParamvalue7(String customParamvalue7) {
        this.customParamvalue7 = customParamvalue7;
    }
    public String getCustomParamvalue8() {
        return this.customParamvalue8;
    }
    
    public void setCustomParamvalue8(String customParamvalue8) {
        this.customParamvalue8 = customParamvalue8;
    }
    public String getCustomParamvalue9() {
        return this.customParamvalue9;
    }
    
    public void setCustomParamvalue9(String customParamvalue9) {
        this.customParamvalue9 = customParamvalue9;
    }
    public String getCustomParamvalue10() {
        return this.customParamvalue10;
    }
    
    public void setCustomParamvalue10(String customParamvalue10) {
        this.customParamvalue10 = customParamvalue10;
    }
}


