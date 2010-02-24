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
import org.openiam.base.AbstractOpenIAMTestCase;
import org.openiam.exception.data.DataException;

public class GroupMgrTest extends  AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	GroupDataService grpMgr;

	Group grp, grp2, grp3, grp4,grp5, grp6, grp7;

	GroupAttribute grpAttr, grpAttr2, grpAttr3;
	UserDataService userMgr;
	User user, user10;
	static String userId;
	static String attrId;
	
	static String groupId1 = null, groupId2 = null, groupId3 = null;
	
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
		groupId1 = grp.getGrpId();
		
		long startTime = System.currentTimeMillis();
		
		Group u = grpMgr.getGroup(groupId1); 
		
		long endTime = System.currentTimeMillis();
		System.out.println("Test cache result. First call to retrieve Group id=" + grp.getGrpId() + " time=" + (endTime - startTime) + "ms" );
				
		assertNotNull(u);
		

	}

	@Test
	public void testGetGroup() {
		
		// also test out caching to see that each call is getting faster
		long startTime = System.currentTimeMillis();
		
		Group u = grpMgr.getGroup(groupId1); 
		
		long endTime = System.currentTimeMillis();
		System.out.println("-- 2nd call to retrieve Group id=" + grp.getGrpId() + " time=" + (endTime - startTime) + "ms" );
		
		assertNotNull(u);
		assertEquals(u.getGrpId(), groupId1);
	

	}


	
	@Test
	public void testUpdateGroup() {

		Group u = grpMgr.getGroup(groupId1);
		u.setGrpName("updated name");
		u.setProvisionMethod("test method");

		grpMgr.updateGroup(u);

		Group u1 = grpMgr.getGroup(groupId1);
		assertNotNull(u1);

		assertEquals(u1.getGrpName(), "updated name");
		assertEquals(u1.getProvisionMethod(), "test method");

	}

	@Test
	public void testGetParentGroups() {
		

		Group group = grpMgr.getParentGroup("L1_GRP", false);  
		assertNotNull(group);
		assertEquals("BASE_GRP", group.getGrpId());
		
	
	}
	
	@Test
	public void testGetParentGroupWithDependants() {
		Group group = grpMgr.getParentGroup("L1_GRP", true);  
		assertNotNull(group);
		assertEquals(1, group.getAttributes().size());
	}

	@Test
	public void testGetChildGroupWithChildren() {
		
	
		List<Group> groupList = grpMgr.getChildGroups("BASE_GRP", true); 
		assertNotNull(groupList);
		assertEquals(2, groupList.size());
		
		for (Group g : groupList) {
			if (g.getGrpId().equals("L1_GRP")) {
				assertNotNull(g.getSubGroup());
				assertEquals(1, g.getSubGroup().size());				
			}
		}
		
		


		
		
	}
	
	@Test
	public void testGetChildGroupWithOutChildren() {
		List<Group> groupList = grpMgr.getChildGroups("BASE_GRP", false);  
		assertNotNull(groupList);
		this.assertEquals(2, groupList.size());
		

	}

	
	


	

	
	/*--------- Test Attributes    -------- */

	public void testAddAttribute() {

		Group groupTemp = grpMgr.getGroup(groupId1);
		
		// set the bi-directional relationship
		groupTemp.getAttributes().put(grpAttr.getName(), grpAttr);
		

		grpMgr.updateGroup(groupTemp);

		Map<String, GroupAttribute> atMap = grpMgr.getAllAttributes(groupId1);
		assertNotNull(atMap);

		// get the attrId for use later
		GroupAttribute atr = atMap.get(grpAttr.getName());
		attrId = atr.getId();
		System.out.println("Attribute Id=" + attrId);
		
	}

	public void testAddAttributeWithoutGroup() {

		grpMgr.addAttribute(grpAttr2);
		grpMgr.addAttribute(grpAttr3);

		this.attrId = grpAttr2.getId();
		
		Map<String, GroupAttribute> attrMap = grpMgr.getAllAttributes(groupId1);
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);

	}

	public void testGetAllAttributes() {
		Group tempOrg = grpMgr.getGroup(groupId1);
		Map<String, GroupAttribute> attrMap = tempOrg.getAttributes();
		System.out.println("map size=" + attrMap.size());
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);
	}

	public void testSaveAttribute() {
		Group tempOrg = grpMgr.getGroup(groupId1);
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

	public void testSaveAttributeWithoutGroup() {

		GroupAttribute atr = grpMgr.getAttribute(attrId);
		atr.setValue("updated value test");
		this.grpMgr.updateAttribute(atr);

		GroupAttribute checkAttr = grpMgr.getAttribute(attrId);
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value test");

	}

	public void testRemoveAttribute() {
		Group tempOrg = grpMgr.getGroup(groupId1);
		Map<String, GroupAttribute> attrMap = tempOrg.getAttributes();
		attrMap.remove(grpAttr.getName());
		tempOrg.setAttributes(attrMap);

		grpMgr.updateGroup(tempOrg);

		Map<String,GroupAttribute> attrMap2 = grpMgr.getAllAttributes(groupId1);
		assertNull(attrMap.get(grpAttr.getName()));
	}

	public void testRemoveAttributeDirect() {
		GroupAttribute attr = new GroupAttribute();
		attr.setId(attrId);
		grpMgr.removeAttribute(attr);

		GroupAttribute checkAttr = grpMgr.getAttribute(attrId);
		assertNull(checkAttr);

	}

	public void testRemoveAllAttributes() {
		grpMgr.removeAllAttributes(groupId1);

		Map<String, GroupAttribute> attrMap = grpMgr.getAllAttributes(groupId1);
		assertNull(attrMap);
	}


	@Test
	public void testRemoveChildGroup() {

		grpMgr.removeChildGroups(groupId1); 
		Group u = grpMgr.getGroup(groupId1);
		assertNull(u);
	}

	@Test
	public void testRemoveGroup() {

		grpMgr.removeGroup(groupId1);

		Group u = grpMgr.getGroup(groupId1);
		assertNull(u);
	}
	
	
}
