package org.openiam.idm.srvc.user;


import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.idm.srvc.continfo.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class UserMgrSupervisorTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user2, user3;
	Supervisor supr1, supr2, supr3;
	static String suprObj1, suprObj2, suprObj3;
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBeanAddress");
		user2 = (User)ctx.getBean("userBeanAddress2");
		user3 = (User)ctx.getBean("userBeanAddress3");
		
		supr1 = (Supervisor)ctx.getBean("supervisor");
		supr2 = (Supervisor)ctx.getBean("supervisor2");
		supr3 = (Supervisor)ctx.getBean("supervisor3");

		
	} 

	@Test
	public void testAddUsers() {
		userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		userMgr.addUser(user2);
		userMgr.addUser(user3);
		
	}
	
	public void testAddSupervisors() {
		userMgr.addSupervisor(supr1);
		userMgr.addSupervisor(supr2);
		userMgr.addSupervisor(supr3);
		
		suprObj1 = supr1.getOrgStructureId();
		suprObj2 = supr2.getOrgStructureId();
		suprObj3 = supr3.getOrgStructureId();
	}
	
	public void testGetEmployeeList() {
		List<Supervisor> employeeList = userMgr.getEmployees(supr1.getSupervisor().getUserId());
	
	    this.assertEquals(2, employeeList.size());
	
	}

	public void testGetSupervisorList() {
		List<Supervisor> supervisorList = userMgr.getSupervisors(user3.getUserId());
	
	    this.assertEquals(2, supervisorList.size());
	
	}
	
	public void testGetPrimarySupervisor() {
		Supervisor supervisor = userMgr.getPrimarySupervisor(user3.getUserId());
	
	    assertNotNull(supervisor);
	
	}
	
	public void testUpdateSupervisorObj() {
		supr2.setComments("Updated record");
		supr2.setEndDate(new Date(System.currentTimeMillis()));
		supr2.setOrgStructureId(suprObj2);
		userMgr.updateSupervisor(supr2);
	}
	
	public void testRemoveSupervisorObj() {
		supr2.setOrgStructureId(suprObj2);
		userMgr.removeSupervisor(supr2);
		
		Supervisor sup = userMgr.getSupervisor(suprObj2);
		assertNull(sup);
	}

	public void testRemoveAllSupervisorObj() {
		supr3.setOrgStructureId(suprObj3);
		userMgr.removeSupervisor(supr3);

		supr1.setOrgStructureId(suprObj1);
		userMgr.removeSupervisor(supr1);
		
	}

	public void testCleanupUsers() {
		userMgr.removeUser(user.getUserId());
		userMgr.removeUser(user2.getUserId());
		userMgr.removeUser(user3.getUserId());
		
		
	}
	
}








