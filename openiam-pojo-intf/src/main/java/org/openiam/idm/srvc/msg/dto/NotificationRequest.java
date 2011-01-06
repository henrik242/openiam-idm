/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.msg.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * NotificationRequest contains information for notification service to send out a pre-defined
 * notification
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificationRequest", propOrder = {
		"notificationType",
		"userId",
		"cc",
		"to",
		"paramList",
		"requestId",
		"linkedRequestId"
})
@XmlRootElement(name="NotificationRequest")
@XmlSeeAlso({
    NotificationParam.class
})
public class NotificationRequest {

	String notificationType;
	String userId;
	String to;
	String cc;
	String requestId;
	String linkedRequestId;
	List<NotificationParam> paramList = new ArrayList<NotificationParam>();
	
	public NotificationRequest() {
		
	}

	public NotificationRequest(String userId, String notificationType) {
		super();
		this.userId = userId;
		this.notificationType = notificationType;
	}
	
	public NotificationRequest(String cc, String notificationType,
			List<NotificationParam> paramList, String to) {
		super();
		this.cc = cc;
		this.notificationType = notificationType;
		this.paramList = paramList;
		this.to = to;
	}

	public NotificationRequest(String cc, String notificationType,
			List<NotificationParam> paramList, String to, String requestId, 
			String linkedRequestId) {
		super();
		this.cc = cc;
		this.notificationType = notificationType;
		this.paramList = paramList;
		this.to = to;
		this.requestId = requestId;
		this.linkedRequestId = linkedRequestId;
	}
	
	
	public String toString() {
		String str = "userId=" + userId + 
			" notificationType=" + notificationType + 
			" to=" + to;
		return str;
	}
	
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public List<NotificationParam> getParamList() {
		return paramList;
	}
	public void setParamList(List<NotificationParam> paramList) {
		this.paramList = paramList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public NotificationParam getNotificationParam(String key) {
		if (key == null) {
			return null;
		}
		if (paramList == null || paramList.size() == 0) {
			return null;
		}
		for ( NotificationParam param : paramList) {
			if ( param.getName().equalsIgnoreCase(key)) {
				return param;
			}
		}
		
		return null;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getLinkedRequestId() {
		return linkedRequestId;
	}

	public void setLinkedRequestId(String linkedRequestId) {
		this.linkedRequestId = linkedRequestId;
	}
	
	
}
