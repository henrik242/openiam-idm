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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
		"customType",
		"cc",
		"to",
		"languageCd",
		"paramList"
})
public class NotificationRequest {
	NotificationType notificationType;
	String customType;
	String to;
	String cc;
	String languageCd;
	List<NotificationParam> paramList;
	
	public NotificationRequest() {
		
	}
	
	public NotificationRequest(String cc, String customType,
			NotificationType notificationType,
			List<NotificationParam> paramList, String to) {
		super();
		this.cc = cc;
		this.customType = customType;
		this.notificationType = notificationType;
		this.paramList = paramList;
		this.to = to;
	}
	
	
	public NotificationType getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
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
	
	
}
