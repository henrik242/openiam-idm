package org.openiam.idm.srvc.grp;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.service.*;
import org.openiam.idm.srvc.grp.dto.*;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.service.RoleDataService;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class GroupMgrTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	GroupDataService grpMgr;

	Group grp, grp2, grp3, grp4,grp5, grp6, grp7;

	GroupAttribute grpAttr, grpAttr2, grpAttr3;
	UserDataService userMgr;
	User user, user10;
	static String userId;
	
	RoleDataService roleMgr;
	Role role;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/groupTest-applicationContext.xml",
				"/userTest-applicationContext.xml" });
		grpMgr = (GroupDataService) ctx.getBean("groupManager");
		grp = (Group) ctx.getBean("grpBean");
		grp2 = (Group) ctx.getBean("grpBean2");
		grp3 = (Group) ctx.getBean("grpBean3");
		grp4 = (Group) ctx.getBean("grpBean4");
		grp5 = (Group) ctx.getBean("grpBean5");
		grp6 = (Group) ctx.getBean("grpBean6");
		grp7 = (Group) ctx.getBean("grpBean7");
		grpAttr = (GroupAttribute) ctx.getBean("grpAttribute");
		grpAttr2 = (GroupAttribute) ctx.getBean("grpAttribute2");
		grpAttr3 = (GroupAttribute) ctx.getBean("grpAttribute3");
		
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBean");
		
		roleMgr = (RoleDataService)ctx.getBean("roleDataService");
		role = (Role)ctx.getBean("roleBean");

	}

	@Test
	public void testAddGroup() {
		grpMgr.addGroup(grp);

		long startTime = System.currentTimeMillis();
		
		Group u = grpMgr.getGroup(grp.getGrpId()); 
		
		long endTime = System.currentTimeMillis();
		System.out.println("Test cache result. First call to retrieve Group id=" + grp.getGrpId() + " time=" + (endTime - startTime) + "ms" );
				
		assertNotNull(u);
		
		grpMgr.addGroup(grp2);
		grpMgr.addGroup(grp3);
		grpMgr.addGroup(grp4);
		grpMgr.addGroup(grp5);
		grpMgr.addGroup(grp6);
		grpMgr.addGroup(grp7);


	}

	@Test
	public void testGetGroup() {
		
		// also test out caching to see that each call is getting faster
		long startTime = System.currentTimeMillis();
		
		Group u = grpMgr.getGroup(grp.getGrpId()); 
		
		long endTime = System.currentTimeMillis();
		System.out.println("-- 2nd call to retrieve Group id=" + grp.getGrpId() + " time=" + (endTime - startTime) + "ms" );
		
		assertNotNull(u);
		assertEquals(u.getGrpId(), grp.getGrpId());
	
		startTime = System.currentTimeMillis();
		
		u = grpMgr.getGroup(grp.getGrpId()); 
		
		endTime = System.currentTimeMillis();
		System.out.println("-- 3rd call to retrieve Group id=" + grp.getGrpId() + " time=" + (endTime - startTime) + "ms" );

		// call a different group -grp2
		startTime = System.currentTimeMillis();		
		u = grpMgr.getGroup(grp2.getGrpId()); 
		endTime = System.currentTimeMillis();
		System.out.println("-- 1st call to retrieve Group id=" + grp2.getGrpId() + " time=" + (endTime - startTime) + "ms" );
		
		startTime = System.currentTimeMillis();		
		u = grpMgr.getGroup(grp.getGrpId()); 
		endTime = System.currentTimeMillis();
		System.out.println("-- 4th call to retrieve Group id=" + grp.getGrpId() + " time=" + (endTime - startTime) + "ms" );

		
		// call a different group -grp2
		startTime = System.currentTimeMillis();		
		u = grpMgr.getGroup(grp2.getGrpId()); 
		endTime = System.currentTimeMillis();
		System.out.println("-- 2nd call to retrieve Group id=" + grp2.getGrpId() + " time=" + (endTime - startTime) + "ms" );

	}


	
	@Test
	public void testUpdateGroup() {

		grp2.setGrpName("updated name");
		grp2.setProvisionMethod("test method");

		grpMgr.updateGroup(grp2);

		Group u = grpMgr.getGroup(grp2.getGrpId());
		assertNotNull(u);

		assertEquals(grp2.getGrpName(), "updated name");
		assertEquals(grp2.getProvisionMethod(), "test method");

	}

	@Test
	public void testGetParentGroups() {
		
		long startTime = System.currentTimeMillis();


		List groupList = grpMgr.getAllGroups();  

		long endTime = System.currentTimeMillis();
		System.out.println("Call to retrieve Parent Groups time=" + (endTime - startTime) + "ms" );
		
		assertNotNull(groupList);

		// testing the cache performance
		startTime = System.currentTimeMillis();		
		groupList = grpMgr.getAllGroups();  
		endTime = System.currentTimeMillis();
		System.out.println("-- 2nd call to retrieve Parent Groups time=" + (endTime - startTime) + "ms" );
		
	
	}
	
	@Test
	public void testGetParentGroupWithChildren() {
		List groupList = grpMgr.getAllGroups();  
		assertNotNull(groupList);
	}

	@Test
	public void testGetChildGroupWithChildren() {
		
		long startTime = System.currentTimeMillis();
		
		List<Group> groupList = grpMgr.getChildGroups("Finance_GRP", true); 

		long endTime = System.currentTimeMillis();
		System.out.println("Call to retrieve Groups for FINANCE_GRP  time=" + (endTime - startTime) + "ms" );

		assertNotNull(groupList);
		assertEquals(2, groupList.size());
		
		Group grp = groupList.get(0);
		assertEquals("Finance_sub_GRP", grp.getGrpId());
		
		// test the cache performance
		startTime = System.currentTimeMillis();		
		groupList = grpMgr.getAllGroups();  
		endTime = System.currentTimeMillis();
		System.out.println("-- 2nd call to retrieve Groups for FINANCE_GRP  time=" + (endTime - startTime) + "ms"  );

		
		
	}
	
	@Test
	public void testGetChildGroupWithOutChildren() {
		List<Group> groupList = grpMgr.getChildGroups("Sales_GRP", true);  
		assertNull(groupList);
		

	}

	
	


	
	@Test
	public void testRemoveUser() {
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);	
	}
	
	/*--------- Test Attributes    -------- */

	public void testAddAttribute() {

		Group groupTemp = grpMgr.getGroup(grp.getGrpId());
		Map<String, GroupAttribute> attrMap = groupTemp.getAttributes();

		attrMap.put(grpAttr.getName(), grpAttr);
		groupTemp.setAttributes(attrMap);

		grpMgr.updateGroup(groupTemp);

		Map<String, GroupAttribute> atMap = grpMgr.getAllAttributes(grp.getGrpId());
		assertNotNull(atMap);

	}

	public void testAddAttributeWithoutUser() {

		grpMgr.addAttribute(grpAttr2);
		grpMgr.addAttribute(grpAttr3);

		Map<String, GroupAttribute> attrMap = grpMgr.getAllAttributes(grp.getGrpId());
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);

	}

	public void testGetAllAttributes() {
		Group tempOrg = grpMgr.getGroup(grp.getGrpId());
		Map<String, GroupAttribute> attrMap = tempOrg.getAttributes();
		System.out.println("map size=" + attrMap.size());
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);
	}

	public void testSaveAttribute() {
		Group tempOrg = grpMgr.getGroup(grp.getGrpId());
		Map<String, GroupAttribute> attrMap = tempOrg.getAttributes();
		GroupAttribute tempAt = attrMap.get(this.grpAttr.getName());
		tempAt.setValue("updated value");
		attrMap.put(tempAt.getName(), tempAt);
		tempOrg.setAttributes(attrMap);

		grpMgr.updateGroup(tempOrg);

		GroupAttribute checkAttr = grpMgr.getAttribute(tempAt.getId());
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value");

	}

	public void testSaveAttributeWithoutOrg() {

		grpAttr2.setValue("updated value test");
		this.grpMgr.updateAttribute(grpAttr2);

		GroupAttribute checkAttr = grpMgr.getAttribute(grpAttr2.getId());
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value test");

	}

	public void testRemoveAttribute() {
		Group tempOrg = grpMgr.getGroup(grp.getGrpId());
		Map<String, GroupAttribute> attrMap = tempOrg.getAttributes();
		attrMap.remove(grpAttr.getName());
		tempOrg.setAttributes(attrMap);

		grpMgr.updateGroup(tempOrg);

		GroupAttribute checkAttr = grpMgr.getAttribute(grpAttr.getId());
		assertNull(checkAttr);
	}

	public void testRemoveAttributeDirect() {
		grpMgr.removeAttribute(grpAttr2);

		GroupAttribute checkAttr = grpMgr.getAttribute(grpAttr2.getId());
		assertNull(checkAttr);

	}

	public void testRemoveAllAttributes() {
		grpMgr.removeAllAttributes(grp.getGrpId());

		Map<String, GroupAttribute> attrMap = grpMgr.getAllAttributes(grp.getGrpId());
		assertNull(attrMap);
	}


	@Test
	public void testRemoveChildGroup() {

		grpMgr.removeChildGroups(grp3.getGrpId()); 
		Group u = grpMgr.getGroup(grp4.getGrpId());
		assertNull(u);
	}

	@Test
	public void testRemoveGroup() {

		grpMgr.removeGroup(grp.getGrpId());

		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNull(u);
	}
	
	
}
