package org.openiam.idm.srvc.continfo;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.continfo.service.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.springframework.transaction.*;


/**
 * Test the EmailAddress data access object
 * @author Suneet Shah
 *
 */

public class EmailAddressDAOTest extends AbstractOpenIAMTestCase  {
//public class EmailAddressDAOTest extends AbstractTransactionalSpringContextTests  {
	
	ApplicationContext ctx = null;

	EmailAddress eml1,eml2,eml3;
	EmailAddressDAO emailAddressDAO;
	
	static String emId1=null, emId2 = null, emId3=null;
	

//	public void setTransactionManager(PlatformTransactionManager tm){
//		super.setTransactionManager(tm);
//	}
	
	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;

		 eml1= (EmailAddress)ctx.getBean("email1");
		 eml2 = (EmailAddress)ctx.getBean("email2");
		 eml3 = (EmailAddress)ctx.getBean("email3");
		 emailAddressDAO = (EmailAddressDAO)ctx.getBean("emailAddressDAO");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testAddAddress() {
				
		emailAddressDAO.add(eml1); 		
		
		emId1 = eml1.getEmailId();
		
		EmailAddress tempAdr = emailAddressDAO.findById(eml1.getEmailId());
		assertNotNull(tempAdr);
		assertEquals(eml1.getEmailAddress(), tempAdr.getEmailAddress());
		
	}


 	public void testAddAddresses2() {

		emailAddressDAO.add(eml2);
		emailAddressDAO.add(eml3);
		
		emId2 = eml2.getEmailId();
		emId3 = eml3.getEmailId();
		
		List<EmailAddress> adrList = emailAddressDAO.findByParentAsList(eml2.getParentId(), eml2.getParentType());
		
		assertEquals(3, adrList.size());
	}	
	
	public void testUpdateAddress() {
		
		eml2.setEmailId(emId2);
		eml2.setEmailAddress("sas@dml.org");
		
		emailAddressDAO.update(eml2);
		
		EmailAddress adrTemp = emailAddressDAO.findById(eml2.getEmailId());
		assertEquals(eml2.getEmailAddress(), adrTemp.getEmailAddress());

	}
	
	public void testFindDefault() {
		EmailAddress adrTemp = emailAddressDAO.findDefault(eml1.getParentId(), eml1.getParentType());
		assertNotNull(adrTemp);
		assertEquals(emId3, adrTemp.getEmailId());
	}

	public void testFindByDescription() {
		EmailAddress adrTemp = emailAddressDAO.findByName(eml1.getName(), eml1.getParentId(), eml1.getParentType());
		assertNotNull(adrTemp);
		assertEquals(emId1, adrTemp.getEmailId());
	}	
	
	public void testFindByParent() {
		Map<String, EmailAddress> adrMap = emailAddressDAO.findByParent(eml1.getParentId(), eml1.getParentType());
		assertNotNull(adrMap);
		assertEquals(3, adrMap.size());
	}
	
	public void testRemoveAddress() {
		
		eml2.setEmailId(emId2);
		emailAddressDAO.remove(eml2);
		
		assertNull(emailAddressDAO.findById(emId2));
		
		List<EmailAddress> adrList = emailAddressDAO.findByParentAsList(eml2.getParentId(), eml2.getParentType());
		assertEquals(2, adrList.size());
		
	}
	public void testRemoveAll() {
		
		emailAddressDAO.removeByParent(eml1.getParentId(), eml1.getParentType());
		
		assertNull(emailAddressDAO.findById(emId2));
		
		List<EmailAddress> adrList = emailAddressDAO.findByParentAsList(eml2.getParentId(), eml2.getParentType());
		assertNull(adrList);
		
	}	
	
		
}








