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

/**
 * Used to test the handling in the add method of the group dataservice
 * @author suneet
 *
 */
public class HierachicalGroupTest extends  AbstractOpenIAMTestCase {

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
	public void testGetUserGroup() {
		
		List<Group> groupList = grpMgr.getUserInGroups("300");
					
		assertNotNull(groupList);
		
		// proof
		
		// top level - should have 2 node
		for (Group grp : groupList) {
			System.out.println("Group=" + grp);
		}


	}

	public void testGetUserGroupFlatList() {
		
		List<Group> groupList = grpMgr.getUserInGroupsAsFlatList("300");
					
		assertNotNull(groupList);
		
		// proof
		System.out.println("grouplist size=" + groupList.size());
		
		// should have 4 node
		for (Group grp : groupList) {
			System.out.println("Group=" + grp);
		}


	}
	
	
}
