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

public class AddRoleTest extends AbstractOpenIAMTestCase  {

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

		

		
	} 
	

	
	@Test
/*	public void testAddRole() {
		
		role.getRoleAttributes().add(roleAttr1);
		roleAttr1.setRole(role);
		
		
		
		roleMgr.addRole(role);
		
		
	}
*/

public void removeUserFromRole() {
		
		roleMgr.removeUserFromRole("USR_SEC_DOMAIN", "EOC", "4028818623cb4bf30123cb50c0d50003");
	}
	
	
public void addUserToRole() {
		
		roleMgr.addUserToRole("USR_SEC_DOMAIN", "EOC", "4028818623cb4bf30123cb50c0d50003");
	}
	
public void testGetUserRole() {
		

		
		List<Role> roleAry = roleMgr.getUserRoles("3006");
		System.out.println("User role for 3006:" +roleAry);
		
	}

}








