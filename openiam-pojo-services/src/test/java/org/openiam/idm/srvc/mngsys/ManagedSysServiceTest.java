package org.openiam.idm.srvc.mngsys;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ManagedSysServiceTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	ManagedSystemDataService managedSysService;


	static String sysId = null;


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml" });
		managedSysService = (ManagedSystemDataService) ctx.getBean("managedSysService");
	}

	@Test
	public void testAdd() {
		ManagedSys sys = new ManagedSys();
		sys.setName("OpenIAM Directory");
		sys.setUserId("cn=Directory Manager");
		sys.setPswd("scorpio18");
		sys.setPort(new Integer("1389"));
		sys.setConnectorId("51");
		sys.setDomainId("USR_SEC_DOMAIN");
		sys.setStatus("ACTIVE");
		sys.setHostUrl("ldap://96.56.80.245");
		sys.setCommProtocol("ssl");
		managedSysService.addManagedSystem(sys);
		
		sysId = sys.getManagedSysId();
	
	}
	public void testGet() {
		ManagedSys managedSys =  managedSysService.getManagedSys(sysId);
		
		assertNotNull(managedSys);
		this.assertEquals(sysId, managedSys.getManagedSysId());
	}

	public void testAddMatchObject() {
		ManagedSystemObjectMatch match = new ManagedSystemObjectMatch();
		match.setBaseDn("ou=test");
		match.setMatchMethod("BASE_DN");
		match.setObjectType("USER");
		match.setManagedSys(sysId);
		
		ManagedSys managedSys =  managedSysService.getManagedSys(sysId);
		Set<ManagedSystemObjectMatch> matchSet = managedSys.getMngSysObjectMatchs();
		matchSet.add(match);
		
		managedSysService.updateManagedSystem(managedSys);
		
		
		
		
	}
	
	
	public void testGetAll() {
		ManagedSys[] sysAry =  managedSysService.getManagedSysByProvider("51");
		
		assertNotNull(sysAry);
		this.assertEquals(1, sysAry.length);
	}
	
	public void testUpdate() {
		ManagedSys managedSys =  managedSysService.getManagedSys(sysId);
		managedSys.setName("Updated value");
		
		managedSysService.updateManagedSystem(managedSys);

		ManagedSys managedSysTest =  managedSysService.getManagedSys(sysId);
		
		assertNotNull(managedSysTest);
		assertEquals(managedSysTest.getName(), managedSys.getName());
	}

	public void testDelete() {
		managedSysService.removeManagedSystem(sysId);

		ManagedSys managedSysTest =  managedSysService.getManagedSys(sysId);		
		assertNull(managedSysTest);

	}
}
