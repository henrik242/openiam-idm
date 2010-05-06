package org.openiam.idm.srvc.msg.service;

import javax.jws.WebService;
import javax.mail.SendFailedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.msg.dto.NotificationRequest;
import org.openiam.idm.srvc.msg.service.MailService;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@WebService(endpointInterface = "org.openiam.idm.srvc.msg.service.MailService", 
		targetNamespace = "urn:idm.openiam.org/srvc/msg", 
		portName = "EmailWebServicePort",
		serviceName = "EmailWebService")
public class MailServiceImpl implements MailService {

	private MailSender mailSender;
	private String defaultSender;
	private String subjectPrefix;
	
	private static final Log log = LogFactory.getLog(SysMessageDAO.class);
	


	public void sendToAllUsers() {
		// TODO Auto-generated method stub
		
	}

	public void sendToGroup(String groupId) {
		// TODO Auto-generated method stub
		
	}

	public void send(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		if (from != null && from.length()  > 0) {
			message.setFrom(from);
		}else {
			message.setFrom(defaultSender);
		}
		message.setTo(to);
		if (subjectPrefix != null) {
			subject = subjectPrefix + " " + subject;
		}
		message.setSubject(subject);
		message.setText(msg);
		try {
			mailSender.send(message);
		}catch(MailException sf) {
			log.error(sf);
		}catch(Exception e) {
			log.error(e);
		}
		
	}

	public void sendWithCC(String from, String to,String cc, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		if (from != null && from.length()  > 0) {
			message.setFrom(from);
		}else {
			message.setFrom(defaultSender);
		}
		message.setTo(to);
		message.setCc(cc);
		if (subjectPrefix != null) {
			subject = subjectPrefix + " " + subject;
		}
		message.setSubject(subject);
		message.setText(msg);
		try {
			mailSender.send(message);
		}catch(MailException sf) {
			log.error(sf);
		}catch(Exception e) {
			log.error(e);
		}
		
	}
	
	public String getDefaultSender() {
		return defaultSender;
	}

	public void setDefaultSender(String defaultSender) {
		this.defaultSender = defaultSender;
	}

	public String getSubjectPrefix() {
		return subjectPrefix;
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.msg.service.MailService#sendNotification(org.openiam.idm.srvc.msg.dto.NotificationRequest)
	 */
	public void sendNotification(NotificationRequest req) {
		// TODO Auto-generated method stub
		
	}
	
}
