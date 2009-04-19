package org.openiam.idm.srvc.res;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.res.dto.ResourceType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ResourceTypeTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	ResourceDataService resMgr;
	ResourceType resType = null;

	static String defId = null;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml"});
		resMgr = (ResourceDataService) ctx.getBean("resourceDataService");


	}

	@Test
	public void testAddPolicyDef() {
		
		resType = new ResourceType();
		resType.setDescription("Test type");
		resType.setResourceTypeId("test");

		
		resMgr.addResourceType(resType);
		

		


	}

	}
