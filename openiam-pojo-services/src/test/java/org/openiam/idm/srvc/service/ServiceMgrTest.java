package org.openiam.idm.srvc.service;

import static org.junit.Assert.*;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.*;
import org.springframework.test.*;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openiam.base.AbstractOpenIAMTestCase;
import org.openiam.idm.srvc.service.dto.*;
import org.openiam.idm.srvc.service.service.*;

public class ServiceMgrTest extends AbstractOpenIAMTestCase {

	ServiceMgr mgr;
	Service service;
	ServiceConfig servConfig;
	RequestForm reqForm;
	RequestApprover reqApprover;
	
	
	ApplicationContext ctx = null;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		mgr = (ServiceMgr)ctx.getBean("serviceManager");
		service = (Service)ctx.getBean("serviceBean");
		servConfig = (ServiceConfig)ctx.getBean("serviceConfigBean");
		reqForm = (RequestForm)ctx.getBean("reqFormBean");
		reqApprover = (RequestApprover)ctx.getBean("reqApproverBean");
		
	} 
	


	@Test
	public void testAddService() {
		mgr.addService(service);
	}
	@Test
	public void testAddSecondService() {
		Service serv2 = (Service)ctx.getBean("serviceBean");
		serv2.setParentServiceId(service.getServiceId());
		serv2.setServiceId( String.valueOf( System.currentTimeMillis() ));
		mgr.addService(serv2);
	}
	
	@Test
	public void testGetService() {
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest); 
		assertEquals(service.getServiceId(), srvTest.getServiceId());
	}
	
	@Test
	public void testUpdateService() {
		Service srvTest = mgr.getService(service.getServiceId());
		srvTest.setServiceName("Updated name");
		System.out.println("Service = " + srvTest);
		mgr.updateService( srvTest);
		
		Service srvTest2 = mgr.getService(service.getServiceId());
		assertNotNull(srvTest2); 
		assertEquals("Updated name", srvTest2.getServiceName());

	}

 

	@Test
	public void testGetAllServices() {
		List lst = mgr.getAllServices();
		assertNotNull(lst);
		assertTrue(lst.size() > 1);

	}

	@Test
	public void testGetChildServices() {
		List lst = mgr.getChildServices(this.service.getServiceId());
		assertNotNull(lst);
		assertTrue(lst.size() >= 1);

	}

	@Test
	public void testGetServicesByType() {
		List lst = mgr.getServicesByType(service.getServiceType());
		assertNotNull(lst);
		assertTrue(lst.size() > 1);

	}
 	@Test
	public void testRemoveService() {
 		System.out.println("Service Value = " + service);
		mgr.removeService(service);
		Service srvTest = mgr.getService(service.getServiceId());
		assertNull(srvTest); 	
	}

	public void testAddThirdService() {
		mgr.addService(service);
	}

	public void testAddServiceConfig() {
		
	//	Set<ServiceConfig> servSet = new java.util.HashSet<ServiceConfig>();
	//	servSet.add(this.servConfig);
				
		Service serv = mgr.getService(service.getServiceId());
		Set<ServiceConfig> configSet  = serv.getServiceConfigs();
		configSet.add(servConfig);
		
		//serv.setServiceConfigs(servSet);
		mgr.updateService(serv);
		
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest.getServiceConfigs()); 
		
	}

	
	public void testUpdateServiceConfig() {
		
				
		Service serv = mgr.getService(service.getServiceId());
	
		Set<ServiceConfig> servSet = serv.getServiceConfigs();
		Iterator it = servSet.iterator();
		while (it.hasNext()) {
			ServiceConfig config = (ServiceConfig)it.next();
			config.setValue("Updated Config");
			servSet.add(config);
		}
		
		mgr.updateService(serv);
		
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest); 
		
		servSet = srvTest.getServiceConfigs();
		it = servSet.iterator();
		while (it.hasNext()) {
			ServiceConfig config = (ServiceConfig)it.next();
			assertEquals("Updated Config", config.getValue());
		}

	}

	public void testRemoveServiceWithAssociation() {
 		System.out.println("Service Value = " + service);
		mgr.removeService(service);
		Service srvTest = mgr.getService(service.getServiceId());
		assertNull(srvTest); 	
	}
	
/*	public void testAddRequestApprover() {
		
		Set<RequestApprover> reqSet = new java.util.HashSet<RequestApprover>();
		reqSet.add(this.reqApprover);
				
		Service serv = mgr.getService(service.getServiceId());
		serv.setRequestApprovers(reqSet);
		mgr.updateService(serv);
		
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest.getRequestApprovers()); 
		
	}

	public void testAddRequesForm() {
		
		Set<RequestForm> reqFormSet = new java.util.HashSet<RequestForm>();
		reqFormSet.add(this.reqForm);
				
		Service serv = mgr.getService(service.getServiceId());
		serv.setRequestForms(reqFormSet);
		mgr.updateService(serv);
		
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest.getRequestForms()); 
		
	}

	public void testUpdateRequestApprover() {
		
	
		Service serv = mgr.getService(service.getServiceId());
		
		Set<RequestApprover> servSet = serv.getRequestApprovers();
		Iterator it = servSet.iterator();
		while (it.hasNext()) {
			RequestApprover reqApprover = (RequestApprover)it.next();
			reqApprover.setApproverType("Updated");
			servSet.add(reqApprover);
		}
		
		mgr.updateService(serv);
		
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest); 
		
		servSet = srvTest.getRequestApprovers();
		it = servSet.iterator();
		while (it.hasNext()) {
			RequestApprover reqApprover = (RequestApprover)it.next();
			assertEquals("Updated", reqApprover.getApproverType());
		}
		
	}

	public void testUpdateRequestForm() {
		
	
		Service serv = mgr.getService(service.getServiceId());
		
		Set<RequestForm> servSet = serv.getRequestForms();
		Iterator it = servSet.iterator();
		while (it.hasNext()) {
			RequestForm reqForm = (RequestForm)it.next();
			reqForm.setFormTemplateUrl("Updated");
			servSet.add(reqForm);
		}
		
		mgr.updateService(serv);
		
		Service srvTest = mgr.getService(service.getServiceId());
		assertNotNull(srvTest); 
		
		servSet = srvTest.getRequestForms();
		it = servSet.iterator();
		while (it.hasNext()) {
			RequestForm reqForm = (RequestForm)it.next();
			assertEquals("Updated", reqForm.getFormTemplateUrl());
		}
		
		
	}	
	
	
	public void testRemoveThirdService() {
		mgr.removeService(service);
		Service srvTest = mgr.getService(service.getServiceId());
		assertNull(srvTest); 	
	}
	
 */
}
