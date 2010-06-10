package org.openiam.idm.srvc.role;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.grp.dto.GroupSearch;
import org.openiam.idm.srvc.grp.service.GroupDataService;
import org.openiam.idm.srvc.role.service.*;
import org.openiam.idm.srvc.role.dto.*;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.service.UserDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class RoleSearchTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	RoleDataService roleMgr;
	Role role, role3;




	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml",
				"/roleDataServiceTest-applicationContext.xml" });
		roleMgr = (RoleDataService) ctx.getBean("roleDataService");
		role = (Role) ctx.getBean("roleBean");
		role3 = (Role) ctx.getBean("roleBean3");



	}

	@Test
	public void testSearchById() {
		
		RoleSearch search = new RoleSearch();
		search.setRoleId("SEC_ADMIN");
		List<Role> roleList = roleMgr.search(search);
		assertNotNull(roleList);
		this.assertEquals(1, roleList.size());

		
	}
	public void testSearchByName() {
		
		RoleSearch search = new RoleSearch();
		search.setRoleName("S%");
		List<Role> roleList = roleMgr.search(search);
		assertNotNull(roleList);
		this.assertEquals(4, roleList.size());

		
	}	



	

}
