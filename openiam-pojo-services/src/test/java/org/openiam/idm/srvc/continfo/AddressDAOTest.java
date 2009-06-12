package org.openiam.idm.srvc.continfo;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.continfo.dto.*;
import org.openiam.idm.srvc.continfo.service.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;


/**
 * Test the addresslink data access object
 * @author Suneet Shah
 *
 */

public class AddressDAOTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	Address adr1,adr2,adr3;
	AddressDAO adrDAO;
	static String adrId1=null, adrId2 = null, adrId3=null;
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;

		adr1 = (Address)ctx.getBean("address1");
		adr2 = (Address)ctx.getBean("address2");
		adr3 = (Address)ctx.getBean("address3");
		adrDAO = (AddressDAO)ctx.getBean("addressDAO");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testAddAddress() {
				
		adrDAO.add(adr1); 		
		adrId1 = adr1.getAddressId();
		
		Address tempAdr = adrDAO.findById(adr1.getAddressId());
		assertEquals(adr1.getAddress1(), tempAdr.getAddress1());
		assertEquals(adr1.getCity(), tempAdr.getCity());
		assertEquals(adr1.getAddressId(), tempAdr.getAddressId());
		
	}

	public void testAddAddresses2() {

		adrDAO.add(adr2);
		adrDAO.add(adr3);
		
		adrId2 = adr2.getAddressId();
		adrId3 = adr3.getAddressId();
		
		
		List<Address> adrList = adrDAO.findByParentAsList(adr2.getParentId(), adr2.getParentType());
		
		assertEquals(3, adrList.size());
	}	
	
	public void testUpdateAddress() {
		
		adr2.setAddressId(adrId2);
		adr2.setAddress2("Added Address here");
		adr2.setAddress1("Updated Adr- 123 Some St");
		
		adrDAO.update(adr2);
		
		Address adrTemp = adrDAO.findById(adrId2);
		assertEquals(adr2.getAddress1(), adrTemp.getAddress1());
		assertEquals(adr2.getAddress2(), adrTemp.getAddress2());
	}
	
	public void testFindDefault() {
		Address adrTemp = adrDAO.findDefault(adr1.getParentId(), adr1.getParentType());
		assertNotNull(adrTemp);
		assertEquals(adrId2, adrTemp.getAddressId());
	}

	public void testFindByDescription() {
		Address adrTemp = adrDAO.findByName(adr1.getName(), adr1.getParentId(), adr1.getParentType());
		assertNotNull(adrTemp);
		assertEquals(adrId1, adrTemp.getAddressId());
	}	
	
	public void testFindByParent() {
		Map<String, Address> adrMap = adrDAO.findByParent(adr1.getParentId(), adr1.getParentType());
		assertNotNull(adrMap);
		assertEquals(3, adrMap.size());
	}
	
	public void testRemoveAddress() {
		adr2.setAddressId(adrId2);
		adrDAO.remove(adr2);
		
		assertNull(adrDAO.findById(adrId2));
		
		List<Address> adrList = adrDAO.findByParentAsList(adr2.getParentId(), adr2.getParentType());
		assertEquals(2, adrList.size());
		
	}
	public void testRemoveAll() {
		
	
		adrDAO.removeByParent(adr1.getParentId(), adr1.getParentType());
		
		assertNull(adrDAO.findById(adrId1));
		
		List<Address> adrList = adrDAO.findByParentAsList(adr2.getParentId(), adr2.getParentType());
		assertNull(adrList);
		
	}	
	
		
}








