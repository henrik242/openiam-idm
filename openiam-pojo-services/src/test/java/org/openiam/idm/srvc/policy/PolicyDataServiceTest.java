package org.openiam.idm.srvc.policy;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.policy.service.PolicyDataService;
import org.openiam.idm.srvc.policy.dto.PolicyDef;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class PolicyDataServiceTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	PolicyDataService policyMgr;
	PolicyDef policyDef = null;

	static String defId = null;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml"});
		policyMgr = (PolicyDataService) ctx.getBean("policyDataService");


	}

	@Test
	public void testAddPolicyDef() {
		
		policyDef = new PolicyDef();
		policyDef.setName("Test Definition");
		
		policyMgr.addPolicyDefinition(policyDef);
		
		assertNotNull(policyDef.getPolicyDefId());
		
		defId = policyDef.getPolicyDefId();
		


	}

	public void testGetAllPolicyTypes() {
		
		policyDef = new PolicyDef();
		policyDef.setName("Test Definition");
		
		String[] typeAry = policyMgr.getPolicyTypes();
		
		assertNotNull(typeAry);
		
	
		


	}
	
	}
