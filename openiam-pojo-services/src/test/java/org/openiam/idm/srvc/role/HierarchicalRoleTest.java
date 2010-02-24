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
import org.openiam.base.AbstractOpenIAMTestCase;

public class HierarchicalRoleTest extends AbstractOpenIAMTestCase  {

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
					new String[] {"/applicationContext.xml"} ) ;
		roleMgr = (RoleDataService)ctx.getBean("roleDataService");
		

		
	} 
	
	
	public void testGetUserRole() {
		
		List<Role> roleList = roleMgr.getUserRoles("300");
					
		assertNotNull(roleList);
		
		// proof
		
		// top level - should have 2 node
		for (Role rl : roleList) {
			System.out.println("Role=" + rl);
		}


	}
	
	public void testGetUserRoleFlatList() {
		
		List<Role> roleList = roleMgr.getUserRolesAsFlatList("300");
					
		assertNotNull(roleList);
		
		// proof
		System.out.println("rolelist size=" + roleList.size());
		
		// should have 4 node
		for (Role rl : roleList) {
			System.out.println("Role=" + rl);
		}


	}
	
}

	








