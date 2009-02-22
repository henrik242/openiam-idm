package org.openiam.idm.srvc.continfo;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.continfo.service.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.springframework.transaction.*;


/**
 * Test the EmailAddress data access object
 * @author Suneet Shah
 *
 */

public class PhoneDAOTest extends AbstractDependencyInjectionSpringContextTests  {
//public class EmailAddressDAOTest extends AbstractTransactionalSpringContextTests  {
	
	ApplicationContext ctx = null;

	Phone phone1,phone2,phone3;
	PhoneDAO phoneDAO;
	static String phoneId1=null, phoneId2 = null, phoneId3=null;
	

//	public void setTransactionManager(PlatformTransactionManager tm){
//		super.setTransactionManager(tm);
//	}
	
	
	@Override

	protected void onSetUp() throws Exception {
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;

		 phone1= (Phone)ctx.getBean("phone1");
		 phone2 = (Phone)ctx.getBean("phone2");
		 phone3 = (Phone)ctx.getBean("phone3");
		 phoneDAO = (PhoneDAO)ctx.getBean("phoneDAO");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testAddAddress() {
				
		phoneDAO.add(phone1); 		
		
		phoneId1 = phone1.getPhoneId();
		
		
		Phone tempAdr = phoneDAO.findById(phone1.getPhoneId());
		assertNotNull(tempAdr);
		assertEquals(phone1.getPhoneNbr(), tempAdr.getPhoneNbr());
		
	}


 	public void testAddAddresses2() {

		phoneDAO.add(phone2);
		phoneDAO.add(phone3);
		
		phoneId2 = phone2.getPhoneId();
		phoneId3 = phone3.getPhoneId();
		
		List<Phone> adrList = phoneDAO.findByParentAsList(phone2.getParentId(), phone2.getParentType());
		
		assertEquals(3, adrList.size());
	}	
	
	public void testUpdateAddress() {
		
		phone2.setPhoneNbr("269-1234");
		phone2.setPhoneId(phoneId2);
		
		phoneDAO.update(phone2);
		
		Phone adrTemp = phoneDAO.findById(phone2.getPhoneId());
		assertEquals(phone2.getPhoneNbr(), adrTemp.getPhoneNbr());

	}
	
	public void testFindDefault() {
		Phone adrTemp = phoneDAO.findDefault(phone1.getParentId(), phone1.getParentType());
		assertNotNull(adrTemp);
		assertEquals(phoneId3, adrTemp.getPhoneId());
	}

	public void testFindByDescription() {
		Phone adrTemp = phoneDAO.findByName(phone1.getName(), phone1.getParentId(), phone1.getParentType());
		assertNotNull(adrTemp);
		assertEquals(phoneId1, adrTemp.getPhoneId());
	}	
	
	public void testFindByParent() {
		Map<String, Phone> adrMap = phoneDAO.findByParent(phone1.getParentId(), phone1.getParentType());
		assertNotNull(adrMap);
		assertEquals(3, adrMap.size());
	}
	
	public void testRemovePhone() {
		
		phone2.setPhoneId(phoneId2);
		phoneDAO.remove(phone2);
		
		assertNull(phoneDAO.findById(phoneId2));
		
		List<Phone> adrList = phoneDAO.findByParentAsList(phone2.getParentId(), phone2.getParentType());
		assertEquals(2, adrList.size());
		
	}
	public void testRemoveByParent() {
		
		
		phoneDAO.removeByParent(phone1.getParentId(), phone1.getParentType());
		
		assertNull(phoneDAO.findById(phoneId1));
		
		List<Phone> adrList = phoneDAO.findByParentAsList(phone2.getParentId(), phone2.getParentType());
		assertNull(adrList);
		
	}	
	
		
}








