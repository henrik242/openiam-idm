package org.openiam.idm.srvc.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

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
			message.setFrom("suneet_shah@openiam.com");
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
