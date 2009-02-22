package org.openiam.idm.srvc.org;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class OrgMgrTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	OrganizationDataService orgMgr;

	Organization org, org2, org3;

	OrganizationAttribute orgAttribute, orgAttribute2, orgAttribute3;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/orgTest-applicationContext.xml" });
		orgMgr = (OrganizationDataService) ctx.getBean("orgManager");
		org = (Organization) ctx.getBean("orgBean");
		org2 = (Organization) ctx.getBean("orgBean2");
		org3 = (Organization) ctx.getBean("orgBean3");
		orgAttribute = (OrganizationAttribute) ctx.getBean("orgAttribute");
		orgAttribute2 = (OrganizationAttribute) ctx.getBean("orgAttribute2");
		orgAttribute3 = (OrganizationAttribute) ctx.getBean("orgAttribute3");

	}

	@Test
	public void testAddOrganization() {
		orgMgr.addOrganization(org);

		Organization u = orgMgr.getOrganization(org.getOrgId());
		assertNotNull(u);
		
		orgMgr.addOrganization(org2);
		orgMgr.addOrganization(org3);

	}

	@Test
	public void testGetOrganization() {
		Organization u = orgMgr.getOrganization(org.getOrgId());
		assertNotNull(u);
	}

	@Test
	public void testUpdateOrganization() {

		Organization og = orgMgr.getOrganization(org.getOrgId());
		
		og.setDescription("updated description"); 
		og.setDomainName("new domain");

		orgMgr.updateOrganization(og);

		Organization u = orgMgr.getOrganization(org.getOrgId());
		assertNotNull(u);

		assertEquals(u.getDescription(), "updated description");
		assertEquals(u.getDomainName(), "new domain");

	}

	@Test
	public void testRemoveOrganization() {

		orgMgr.removeOrganization(org3.getOrgId());

		Organization u = orgMgr.getOrganization(org3.getOrgId());
		assertNull(u);
	}

	/*--------- Test Attributes    -------- */

	public void testAddAttribute() {
		// create a org

		Organization orgTemp = orgMgr.getOrganization(org.getOrgId());
		Map<String, OrganizationAttribute> attrMap = orgTemp.getAttributes();

		attrMap.put(orgAttribute.getName(), orgAttribute);
		orgTemp.setAttributes(attrMap);

		orgMgr.updateOrganization(orgTemp);

		Map<String, OrganizationAttribute> atMap = orgMgr.getAllAttributes(org.getOrgId());
		assertNotNull(atMap);
		assertEquals(atMap.size(), 1);

	}

	public void testAddAttributeWithoutUser() {

		this.orgMgr.addAttribute(orgAttribute2);
		this.orgMgr.addAttribute(orgAttribute3);

		Map<String, OrganizationAttribute> attrMap = orgMgr.getAllAttributes(org.getOrgId());
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);

	}

	public void testGetAllAttributes() {
		Organization tempOrg = orgMgr.getOrganization(org.getOrgId());
		Map<String, OrganizationAttribute> attrMap = tempOrg.getAttributes();
		System.out.println("map size=" + attrMap.size());
		assertNotNull(attrMap);
		assertEquals(attrMap.size(), 3);
	}

	public void testSaveAttribute() {
		Organization tempOrg = orgMgr.getOrganization(org.getOrgId());
		Map<String, OrganizationAttribute> attrMap = tempOrg.getAttributes();
		OrganizationAttribute tempAt = attrMap.get(this.orgAttribute.getName());
		tempAt.setValue("updated value");
		attrMap.put(tempAt.getName(), tempAt);
		tempOrg.setAttributes(attrMap);

		orgMgr.updateOrganization(tempOrg);

		OrganizationAttribute checkAttr = orgMgr.getAttribute(tempAt.getAttrId());
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value");

	}

	public void testSaveAttributeWithoutOrg() {

		orgAttribute2.setValue("updated value test");
		this.orgMgr.updateAttribute(orgAttribute2);

		OrganizationAttribute checkAttr = orgMgr.getAttribute(orgAttribute2.getAttrId());
		assertNotNull(checkAttr);
		assertEquals(checkAttr.getValue(), "updated value test");

	}

	public void testRemoveAttribute() {
		Organization tempOrg = orgMgr.getOrganization(org.getOrgId());
		Map<String, OrganizationAttribute> attrMap = tempOrg.getAttributes();
		attrMap.remove(orgAttribute.getName());
		tempOrg.setAttributes(attrMap);

		orgMgr.updateOrganization(tempOrg);

		OrganizationAttribute checkAttr = orgMgr.getAttribute(orgAttribute.getAttrId());
		assertNull(checkAttr);
	}

	public void testRemoveAttributeDirect() {
		orgMgr.removeAttribute(orgAttribute2);

		OrganizationAttribute checkAttr = orgMgr.getAttribute(orgAttribute2.getAttrId());
		assertNull(checkAttr);

	}

	public void testRemoveAllAttributes() {
		orgMgr.removeAllAttributes(org.getOrgId());

		Map<String, OrganizationAttribute> attrMap = orgMgr.getAllAttributes(org.getOrgId());
		assertNull(attrMap);
	}

	public void testRemoveOrgWithChildren() {

		orgMgr.removeOrganization(org.getOrgId());

		Organization u = orgMgr.getOrganization(org.getOrgId());
		assertNull(u);

	}
	public void testRemoveOrg() {

		orgMgr.removeOrganization(org2.getOrgId());

		Organization u = orgMgr.getOrganization(org2.getOrgId());
		assertNull(u);

	}
}
