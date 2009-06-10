package org.openiam.idm.srvc.secdomain;

import static org.junit.Assert.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.*;
import org.springframework.test.*;

import org.junit.Test;

import org.openiam.idm.srvc.secdomain.dto.*;
import org.openiam.idm.srvc.secdomain.service.*;

public class SecurityDomainDataServiceTest extends AbstractDependencyInjectionSpringContextTests {

	SecurityDomainDataService secDomService;
	SecurityDomain secDom;

	
	
	ApplicationContext ctx = null;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		secDomService = (SecurityDomainDataService)ctx.getBean("secDomainService");
		secDom = (SecurityDomain)ctx.getBean("securityDomainBean");

		
	} 
	


	@Test
	public void testAddSecurityDomain() {
		secDomService.addSecurityDomain(secDom);
	}

	
	@Test
	public void testGetSecurityDomain() {
		SecurityDomain domTest = secDomService.getSecurityDomain(this.secDom.getDomainId());
		assertNotNull(domTest); 
		assertEquals(secDom.getDomainId(), domTest.getDomainId());
	}
	
	@Test
	public void testUpdateSecurityDomain() {
		SecurityDomain domTest = secDomService.getSecurityDomain(secDom.getDomainId());
		domTest.setName("Updated name");
		secDomService.updateSecurityDomain( domTest);
		
		SecurityDomain srvTest2 = secDomService.getSecurityDomain(secDom.getDomainId());
		assertNotNull(srvTest2); 
		assertEquals("Updated name", srvTest2.getName());

	}

 

	@Test
	public void testGetAllDomains() {
		SecurityDomain[] domAry = secDomService.getAllSecurityDomains();
		assertNotNull(domAry);
		assertEquals(domAry.length, 1);

	}



}
