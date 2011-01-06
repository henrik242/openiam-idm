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
package org.openiam.idm.srvc.msg.ws;

import java.util.List;

import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.msg.dto.SysMessage;
import org.openiam.idm.srvc.msg.service.SysMessageService;


/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.msg.ws.SysMessageWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/msg/service", 
		portName = "SysMessagePort", 
		serviceName = "SysMessageWebService")
public class SysMessageWebServiceImpl implements
		SysMessageWebService {

	SysMessageService msgService; 
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.ws.SysMessageDeliveryWebService#addMessage(org.openiam.idm.srvc.msg.dto.SysMessageDelivery)
	 */
	public SysMessageResponse addMessage(SysMessage msg) {
		SysMessageResponse resp = new SysMessageResponse(ResponseStatus.SUCCESS);
		SysMessage newMsg = msgService.addMessage(msg);
		if (newMsg.getMsgId() == null || newMsg.getMsgId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSysMessage(newMsg);
		}
		return resp;

	}



	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.ws.SysMessageDeliveryWebService#getMessageById(java.lang.String)
	 */
	public SysMessageResponse getMessageById(String id) {
		SysMessageResponse resp = new SysMessageResponse(ResponseStatus.SUCCESS);
		SysMessage msg = msgService.getMessageById(id);
		if (msg.getMsgId() == null || msg.getMsgId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSysMessage(msg);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.ws.SysMessageDeliveryWebService#removeMessage(org.openiam.idm.srvc.msg.dto.SysMessageDelivery)
	 */
	public Response removeMessage(String msgId) {
		Response resp = new Response(ResponseStatus.SUCCESS);
		msgService.removeMessage(msgId);
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.ws.SysMessageDeliveryWebService#updateMessage(org.openiam.idm.srvc.msg.dto.SysMessageDelivery)
	 */
	public SysMessageResponse updateMessage(
			SysMessage msg) {
		SysMessageResponse resp = new SysMessageResponse(ResponseStatus.SUCCESS);
		SysMessage newMsg = msgService.updateMessage(msg);
		if (newMsg.getMsgId() == null || newMsg.getMsgId().isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSysMessage(newMsg);
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.ws.SysMessageWebService#getAllMessages()
	 */
	public SysMessageListResponse getAllMessages() {
		SysMessageListResponse resp = new SysMessageListResponse(ResponseStatus.SUCCESS);
		List<SysMessage> msgList = msgService.getAllMessages();
		if (msgList == null || msgList.isEmpty()) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setSysMessageList(msgList);
		}
		return resp;
	}

	public SysMessageService getMsgService() {
		return msgService;
	}

	public void setMsgService(SysMessageService msgService) {
		this.msgService = msgService;
	}

}
