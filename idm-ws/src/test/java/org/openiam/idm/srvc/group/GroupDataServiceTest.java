package org.openiam.idm.srvc.group;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.role.dto.RoleId;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class GroupDataServiceTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext  ctx = null;

	GroupDataService grpMgr;

	Group grp, grp2, grp3, grp4,grp5, grp6, grp7;

	GroupAttribute grpAttr, grpAttr2, grpAttr3;
	UserDataService userMgr;
	User user, user10;
	Role role;
	static String userId;

	 
	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		
	
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"client-beans.xml", "/groupTest-Context.xml" });
		grpMgr = (GroupDataService) ctx.getBean("groupService");
		grp = (Group) ctx.getBean("grpBean");
		grpAttr = (GroupAttribute) ctx.getBean("grpAttribute");

		
	}



	@Test
	public void testAddGroup() {
		grpMgr.addGroup(grp);

		
		Group u = grpMgr.getGroup(grp.getGrpId()); 
		assertNotNull(u);

	}
	
	public void testUpdateGroup() {

		grp.setGrpName("updated name");
		grp.setProvisionMethod("test method");

		grpMgr.updateGroup(grp);

		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNotNull(u);

		assertEquals(grp.getGrpName(), "updated name");
		assertEquals(grp.getProvisionMethod(), "test method");

	}	

	public void testAddAttribute() {

		Group groupTemp = grpMgr.getGroup(grp.getGrpId());
		Map<String, GroupAttribute> attrMap = groupTemp.getAttributes();

		attrMap.put(grpAttr.getName(), grpAttr);
		groupTemp.setAttributes(attrMap);

		grpMgr.updateGroup(groupTemp);

		Map<String, GroupAttribute> atMap = grpMgr.getAllAttributes(grp.getGrpId());
		assertNotNull(atMap);

	}
	
	public void testRemoveGroup() {

		grpMgr.removeGroup(grp.getGrpId());
		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNull(u);
	}	
}
