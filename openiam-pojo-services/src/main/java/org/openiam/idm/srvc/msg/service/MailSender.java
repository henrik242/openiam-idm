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

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Object containing mail server configuration
 * @author suneet
 *
 */
public class MailSender {
	String host;
	String port;
	String username;
	String password;
	boolean auth;
	boolean starttls;
	
	private static final Log log = LogFactory.getLog(Message.class);
	
	public void send(Message msg) {
		 Properties properties = System.getProperties();
		 properties.setProperty("mail.smtp.host", host);  
		 Session session = Session.getDefaultInstance(properties); 
		 MimeMessage message = new MimeMessage(session);  
		 try {
			 message.setFrom(msg.getFrom()); 
			 message.addRecipient(javax.mail.Message.RecipientType.TO, msg.getTo());
			 message.setSubject(msg.getSubject());
			 message.setText(msg.getBody());
			 
			 Transport.send(message);
			 log.info("Message successfully sent.");
		 }catch(MessagingException me) {
			log.error(me);
			me.printStackTrace();
		 }
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public boolean isStarttls() {
		return starttls;
	}
	public void setStarttls(boolean starttls) {
		this.starttls = starttls;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	
}
