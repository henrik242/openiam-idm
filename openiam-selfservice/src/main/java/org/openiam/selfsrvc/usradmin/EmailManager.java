package org.openiam.selfsrvc.usradmin;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import org.openiam.idm.srvc.user.dto.User;

public class EmailManager {

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private String approveText;
    private String rejectText;
    
    
	public MailSender getMailSender() {
		return mailSender;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}
	public String getApproveText() {
		return approveText;
	}
	public void setApproveText(String approveText) {
		this.approveText = approveText;
	}
	public String getRejectText() {
		return rejectText;
	}
	public void setRejectText(String rejectText) {
		this.rejectText = rejectText;
	}
	
	public void sendMail(User user, String emailAddress, boolean state) {
		String txt = null; 
		if (state) {
			 // approve
			txt = "Dear " + user.getFirstName() + ": \n" + approveText;
		 }else {
			 // reject 
			 txt = "Dear " + user.getFirstName() + ": \n" + rejectText;
		 }
		
	
		 SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
	     msg.setTo(emailAddress);
	     msg.setText(txt);

	        try{
	            this.mailSender.send(msg);
	        }
	        catch(MailException ex) {
	            // simply log it and go on...
	            System.err.println(ex.getMessage());            
	        }		
	}
	

}
