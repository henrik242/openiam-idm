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
public class AddGroupTest extends  AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	GroupDataService grpMgr;
	GroupAttribute grpAttr, grpAttr2, grpAttr3;

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
		grpAttr = (GroupAttribute) ctx.getBean("grpAttribute");
		grpAttr2 = (GroupAttribute) ctx.getBean("grpAttribute2");
		grpAttr3 = (GroupAttribute) ctx.getBean("grpAttribute3");
		

	}

	@Test
	public void testAddGroup() {
		
		grp.getAttributes().put(grpAttr.getName(), grpAttr);
		//grpAttr.setGroup(grp);
		
		grpMgr.addGroup(grp);
			
		Group u = grpMgr.getGroup(grp.getGrpId()); 
						
		assertNotNull(u);
		


	}


	
	
}
