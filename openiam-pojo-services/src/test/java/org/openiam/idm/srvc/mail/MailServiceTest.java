package org.openiam.idm.srvc.mail;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.email.MailService;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class MailServiceTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	MailService mailServc;


	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml"} ) ;
		mailServc = (MailService)ctx.getBean("mailService");
	
		
	} 

	public void testSendMail() {
		mailServc.send(null, "suneetshah2000@gmail.com", "Test Message", "Testing email client to send email.");
		
	}
	
	

	
		
}








