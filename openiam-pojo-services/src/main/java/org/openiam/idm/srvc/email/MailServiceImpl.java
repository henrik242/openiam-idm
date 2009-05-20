package org.openiam.idm.srvc.email;

import javax.jws.WebService;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@WebService(endpointInterface = "org.openiam.idm.srvc.email.MailService", 
		targetNamespace = "urn:idm.openiam.org/srvc/email", 
		serviceName = "EmailWebService")
public class MailServiceImpl implements MailService {

	private MailSender mailSender;
	private String defaultSender;
	private String subjectPrefix;
	
	


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
		
		mailSender.send(message);
		
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
	
}
