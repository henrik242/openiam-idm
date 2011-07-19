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
package org.openiam.idm.srvc.msg.service;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author suneet
 *
 */
public class Message {
	InternetAddress to;
	InternetAddress cc;
	InternetAddress bcc;
	List<String> toList;
	InternetAddress from;
	String subject;
	String body;
	
	private static final Log log = LogFactory.getLog(Message.class);
	
	public InternetAddress getTo() {
		return to;
	}
	public void setTo(String to) {
		try{
			this.to = new InternetAddress(to);
		}catch(AddressException ae) {
			log.error(ae);
		}
	}
	public InternetAddress getFrom() {	
		return from;
	}
	public void setFrom(String fr) {
		try {
			this.from = new InternetAddress(fr);
		}catch(AddressException ae) {
			log.error(ae);
		}

	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public InternetAddress getCc() {
		return cc;
	}
	public void setCc(String cc) {
		try {
			this.cc = new InternetAddress( cc );
		}catch(AddressException ae) {
			log.error(ae);
		}
	}
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
	}
	public InternetAddress getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		
		try {
			this.bcc = new InternetAddress(bcc);
		}catch(AddressException ae) {
			log.error(ae);
		}
		
	}

	
}
