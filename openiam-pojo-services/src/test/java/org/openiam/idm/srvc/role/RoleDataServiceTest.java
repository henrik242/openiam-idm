package org.openiam.idm.srvc.role;


import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.role.service.*;
import org.openiam.idm.srvc.role.dto.*;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class RoleDataServiceTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	RoleDataService roleMgr;
	Role role;
	RoleAttribute roleAttr1, roleAttr2, roleAttr3;
	
	UserDataService userMgr;
	User user;
	static String userId;
	
	GroupDataService grpMgr;
	Group grp;
	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/roleDataServiceTest-applicationContext.xml",
								  "/userTest-applicationContext.xml"} ) ;
		roleMgr = (RoleDataService)ctx.getBean("roleDataService");
		role = (Role)ctx.getBean("roleBean");
		
		roleAttr1 = (RoleAttribute)ctx.getBean("roleAttr1");
		roleAttr2 = (RoleAttribute)ctx.getBean("roleAttr2");
		roleAttr3 = (RoleAttribute)ctx.getBean("roleAttr3");
		
		//urole = (UserRole)ctx.getBean("userRoleBean");
		userMgr = (UserDataService)ctx.getBean("userManager");
		user = (User)ctx.getBean("userBean");
		grpMgr = (GroupDataService) ctx.getBean("groupManager");
		grp = (Group) ctx.getBean("grpBean");
		
	} 
	
	@Test
	public void testAddUser() {
		user = userMgr.addUser(user);
		
		User u = userMgr.getUserWithDependent(user.getUserId(),false);
		assertNotNull(u);
		
		userId = user.getUserId();
		
	}

	public void testAddGroup() {
		grpMgr.addGroup(grp);	
		Group u = grpMgr.getGroup(grp.getGrpId()); 
		assertNotNull(u);
	}	
	
	@Test
	public void testAddRole() {
		roleMgr.addRole(role);
		
		
	}

	public void testUpdateRole() {
		role.setDescription("Updated desc");
		role.setRoleName("upd-rolename");
		roleMgr.updateRole(role);
			
	}

	public void testGetRole() {
		Role r = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		assertNotNull( r);	
	}
	

	
	public void testAddRoleAttribute1() {

		Role r = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		Set raSet = r.getRoleAttributes();
		raSet.add(this.roleAttr1);
		roleMgr.updateRole(r);
		
		Role r2 = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		assertNotNull( r2);
		assertNotNull(r2.getRoleAttributes());
		assertEquals(1,r2.getRoleAttributes().size());
		
	}


	public void testAddRoleAttributeWithRole() {
		roleMgr.addAttribute(this.roleAttr2);
		roleMgr.addAttribute(this.roleAttr3);

		Role r2 = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		assertNotNull( r2);
		assertNotNull(r2.getRoleAttributes());
		assertEquals(3,r2.getRoleAttributes().size());
		
	}
	
	
	public void testGetAllAttributes() {
		RoleAttribute[] roleAttrAry = roleMgr.getAllAttributes(role.getId().getServiceId(), role.getId().getRoleId());

		assertNotNull(roleAttrAry);
		assertEquals(roleAttrAry.length, 3);
	}
	
	
	public void testUpdateRoleAttribute() {
		String attrId = null;
		Role tempRole = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		Set<RoleAttribute> attrSet = tempRole.getRoleAttributes();
		assertNotNull(attrSet);
		
		Iterator<RoleAttribute> attrIt = attrSet.iterator();
		
		if (attrIt.hasNext() ) {
			RoleAttribute ra = attrIt.next();
			attrId = ra.getRoleAttrId();
			ra.setValue("updated attr");
			roleMgr.updateRole(tempRole);
		}
		
		RoleAttribute checkAttr = roleMgr.getAttribute(attrId);
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated attr");		
		
	}

	public void testUpdateRoleAttributeWithOutRole() {
		roleAttr2.setValue("updated Value test");
		this.roleMgr.updateAttribute(roleAttr2);

		RoleAttribute checkAttr = roleMgr.getAttribute(roleAttr2.getRoleAttrId());
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated Value test");
		
	}	
	

	
	
	public void testRemoveRoleAttribute1() {
		
		Role r = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		Set raSet = r.getRoleAttributes();
		Iterator<RoleAttribute> it = raSet.iterator();
		if (it.hasNext()) {
			RoleAttribute ra = it.next();
			it.remove();
		}
		
		roleMgr.updateRole(r);

		Role r2 = roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId());
		assertNotNull( r2);
		assertNotNull(r2.getRoleAttributes());
		assertEquals(2,r2.getRoleAttributes().size());

	}
	public void testRemoveRoleAttributeWithoutRole() {
		
		roleMgr.removeAttribute(roleAttr2);
		RoleAttribute checkAttr = roleMgr.getAttribute(roleAttr2.getRoleAttrId());
		assertNull(checkAttr);	
	}
	
	public void testRemoveAllRoleAttributes() {
		roleMgr.removeAllAttributes(role.getId().getServiceId(), role.getId().getRoleId());
		assertNull(roleMgr.getAllAttributes(role.getId().getServiceId(), role.getId().getRoleId()));
		
	}


	
	
	public void testRemoveGroup() {

		grpMgr.removeGroup(grp.getGrpId());
		Group u = grpMgr.getGroup(grp.getGrpId());
		assertNull(u);
	}
	
	public void testRemoveRole() {
	
		Role r = new Role();

		roleMgr.removeRole(role.getId().getServiceId(), role.getId().getRoleId());

		assertNull( roleMgr.getRole(role.getId().getServiceId(), role.getId().getRoleId()));
	}

	
	@Test
	public void testRemoveUser() {
		
		userMgr.removeUser(userId);
		
		User u = userMgr.getUserWithDependent(userId,false);
		assertNull(u);	
	}
	

}








