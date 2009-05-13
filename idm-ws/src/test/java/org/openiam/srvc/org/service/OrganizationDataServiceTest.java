package org.openiam.srvc.org.service;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class OrganizationDataServiceTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext  ctx = null;

	OrganizationDataService orgService;

	Organization org, org2, org3;
	OrganizationAttribute orgAttribute, orgAttribute2, orgAttribute3;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		
	
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"client-beans.xml", "/orgTest-applicationContext.xml" });
		orgService = (OrganizationDataService) ctx.getBean("organizationService");

		org = (Organization) ctx.getBean("orgBean");
		org2 = (Organization) ctx.getBean("orgBean2");
		org3 = (Organization) ctx.getBean("orgBean3");
		orgAttribute = (OrganizationAttribute) ctx.getBean("orgAttribute");
		orgAttribute2 = (OrganizationAttribute) ctx.getBean("orgAttribute2");
		orgAttribute3 = (OrganizationAttribute) ctx.getBean("orgAttribute3");
		
	}



	@Test
	public void testGetOrganization() {
		Organization u = orgService.getOrganization("100");
		assertNotNull(u);
		this.assertEquals("100",u.getOrgId());
	}
	
	public void testAddOrganization() {
		orgService.addOrganization(org);

		Organization u = orgService.getOrganization(org.getOrgId());
		assertNotNull(u);
		assertEquals(org.getOrgId(), u.getOrgId());	
	}

	public void testUpdateOrganization() {

		Organization og = orgService.getOrganization(org.getOrgId());
		
		og.setDescription("updated description"); 
		og.setDomainName("new domain");

		orgService.updateOrganization(og);

		Organization u = orgService.getOrganization(org.getOrgId());
		assertNotNull(u);

		assertEquals(u.getDescription(), "updated description");
		assertEquals(u.getDomainName(), "new domain");

	}

	public void testAddAttribute() {
		// create a org

		Organization orgTemp = orgService.getOrganization(org.getOrgId());
		Map<String, OrganizationAttribute> attrMap = orgTemp.getAttributes();

		attrMap.put(orgAttribute.getName(), orgAttribute);
		orgTemp.setAttributes(attrMap);

		orgService.updateOrganization(orgTemp);

		Map<String, OrganizationAttribute> atMap = orgService.getAllAttributes(org.getOrgId());
		assertNotNull(atMap);
		assertEquals(atMap.size(), 1);

	}

	
	
	@Test
	public void testRemoveOrganization() {

		orgService.removeOrganization(org.getOrgId());

		Organization u = orgService.getOrganization(org.getOrgId());
		assertNull(u);
	}
	
	
}
