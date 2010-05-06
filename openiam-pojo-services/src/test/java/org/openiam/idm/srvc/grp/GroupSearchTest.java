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

/**
 * Tests related to search on the user object.
 * @author suneet
 *
 */
public class GroupSearchTest extends  AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	GroupDataService grpMgr;
	Group grp;


	


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/groupTest-applicationContext.xml",
				"/userTest-applicationContext.xml" });
		grpMgr = (GroupDataService) ctx.getBean("groupManager");
		grp = (Group) ctx.getBean("grpBean");

	

	}

	
	@Test
	public void testSearchById() {
		
		GroupSearch search = new GroupSearch();
		search.setGrpId("END_USER_GRP");
		List<Group> grpList = grpMgr.search(search);
		assertNotNull(grpList);
		this.assertEquals(1, grpList.size());

		
	}
	public void testSearchByName() {
		
		GroupSearch search = new GroupSearch();
		search.setGrpName("S%");
		List<Group> grpList = grpMgr.search(search);
		assertNotNull(grpList);
		this.assertEquals(3, grpList.size());

		
	}	


	

}
