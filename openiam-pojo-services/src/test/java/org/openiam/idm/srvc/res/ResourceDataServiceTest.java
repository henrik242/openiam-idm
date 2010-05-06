package org.openiam.idm.srvc.res;


import java.util.List;

import org.junit.Test;
import org.openiam.base.AbstractOpenIAMTestCase;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.dto.ResourceRole;
import org.openiam.idm.srvc.res.dto.ResourceType;
import org.openiam.idm.srvc.res.dto.ResourceUser;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceDataServiceTest extends AbstractOpenIAMTestCase  {

	ApplicationContext ctx = null;

	ResourceDataService resourceSvc;
	Resource res1, res11, res12, res121;
	public static String res1ResourceId, res11ResourceId, res12ResourceId, res121ResourceId;
	ResourceProp prop1a, prop1b, prop11a, prop11b, prop12a, prop12b, prop121a, prop121b;
	public static String prop1aResourcePropId,prop1bResourcePropId, prop11aResourcePropId, prop11bResourcePropId, 
		prop12aResourcePropId, prop12bResourcePropId, prop121aResourcePropId,  prop121bResourcePropId;
	ResourceType resType1;
	public static String resType1ResourceTypeId; //this is not generated, but assigned
	//ResourceRole role1, role11, role12, role121;
	//ResourceUser user10003000, user10003001, user11003000;
	
	@Override

	protected void onSetUp() throws Exception {
		super.onSetUp();
		
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/resourceDataServiceTest-applicationContext.xml"} ) ;
		
		resourceSvc = (ResourceDataService)ctx.getBean("resourceDataService");
		
		res1 = (Resource)ctx.getBean("resourceBeanR1000");
		res11 = (Resource)ctx.getBean("resourceBeanR1100");
		res12 = (Resource)ctx.getBean("resourceBeanR1200");
		res121 = (Resource)ctx.getBean("resourceBeanR1210");
		
		prop1a = (ResourceProp)ctx.getBean("resourcePropR1000-RP01");
		prop1b = (ResourceProp)ctx.getBean("resourcePropR1000-RP02");
		prop11a = (ResourceProp)ctx.getBean("resourcePropR1100-RP01");
		prop11b = (ResourceProp)ctx.getBean("resourcePropR1100-RP02");
		prop12a = (ResourceProp)ctx.getBean("resourcePropR1200-RP01");
		prop12b = (ResourceProp)ctx.getBean("resourcePropR1200-RP02");
		prop121a = (ResourceProp)ctx.getBean("resourcePropR1210-RP01");
		prop121b = (ResourceProp)ctx.getBean("resourcePropR1210-RP02");
		
		resType1 = (ResourceType)ctx.getBean("resourceTypeBeanRT1");
		
				
	} 
	

	@Test
	public void testGetResourceTreeXml() {
		resourceSvc.getResourceTreeXML("40288087239ab11601239b11bc230002");
	}
	
	
	
/*
	@Test
	public void testFindTypeOfResource() {
		resourceSvc.findTypeOfResource( "402880852376ee8601237773ad480005");
	}
	@Test
	public void testAddResource() {
		res1ResourceId = resourceSvc.addResource(res1).getResourceId();
		res11ResourceId = resourceSvc.addResource(res11).getResourceId();
		res12ResourceId = resourceSvc.addResource(res12).getResourceId();
		res121ResourceId = resourceSvc.addResource(res121).getResourceId();
	}


	@Test
	public void testSetResourceParentRelationship() {
		resourceSvc.linkResourceToParent(res11ResourceId,res1ResourceId);
		resourceSvc.linkResourceToParent(res12ResourceId,res1ResourceId);
		resourceSvc.linkResourceToParent(res121ResourceId,res12ResourceId);
	}

	
	@Test
	public void testGetChildResources() {
		assertEquals(2, resourceSvc.getChildResources(res1ResourceId).size());
	}

	

	@Test
	public void testGetResource() {
		Resource r = resourceSvc.getResource(res1ResourceId);
		assertEquals(res1ResourceId, r.getResourceId());
	}


	@Test
	public void testUpdateResource() {
		Resource r = resourceSvc.getResource(res11ResourceId);
		r.setDescription("Updated Resource Desc");
		r.setSensitiveApp(0);
		resourceSvc.updateResource(r);
		assertEquals("Updated Resource Desc", resourceSvc.getResource(r.getResourceId()).getDescription());
	}

	@Test
	public void testRemoveTypeFromResource() {
		resourceSvc.unlinkTypeFromResource(res1ResourceId);		
		resourceSvc.unlinkTypeFromResource(res11ResourceId);		
		resourceSvc.unlinkTypeFromResource(res12ResourceId);		
		resourceSvc.unlinkTypeFromResource(res121ResourceId);		
	}


	@Test
	public void testAddResourceType() {
		resourceSvc.removeAllResourceTypes();
		resType1ResourceTypeId = resourceSvc.addResourceType(resType1).getResourceTypeId();	
	}


	@Test
	public void testGetResourceType() {
		ResourceType t = resourceSvc.getResourceType(resType1ResourceTypeId);
		assertEquals(resType1ResourceTypeId, t.getResourceTypeId());
	}

	@Test
	public void testUpdateResourceType() {
		ResourceType t = resourceSvc.getResourceType(resType1ResourceTypeId);
		t.setDescription("Updated test type.");
		t.setProcessName("xxxxx");
		resourceSvc.updateResourceType(t);
		ResourceType tUpdated = resourceSvc.getResourceType(t.getResourceTypeId());
		assertEquals("Updated test type.", tUpdated.getDescription());
	}

	@Test
	public void testGetAllResourceTypes() {
		assertEquals(1, resourceSvc.getAllResourceTypes().size());
	}



	@Test
	public void testAddResourceProp() {
		prop1aResourcePropId = resourceSvc.addResourceProp(prop1a).getResourcePropId();
		prop1bResourcePropId = resourceSvc.addResourceProp(prop1b).getResourcePropId();
		prop11aResourcePropId = resourceSvc.addResourceProp(prop11a).getResourcePropId();
		prop11bResourcePropId = resourceSvc.addResourceProp(prop11b).getResourcePropId();
		prop12aResourcePropId = resourceSvc.addResourceProp(prop12a).getResourcePropId();
		prop12bResourcePropId = resourceSvc.addResourceProp(prop12b).getResourcePropId();
		prop121aResourcePropId = resourceSvc.addResourceProp(prop121a).getResourcePropId();
		prop121bResourcePropId = resourceSvc.addResourceProp(prop121b).getResourcePropId();
	}

	@Test
	public void testGetAllResourceProps() {
		assertEquals(8, resourceSvc.getAllResourceProps().size());
	}

	@Test
	public void testGetResourceProp() {
		ResourceProp r = resourceSvc.getResourceProp(prop1aResourcePropId);
		assertEquals(prop1aResourcePropId, r.getResourcePropId());
	}


	@Test
	public void testUpdateResourceProp() {
		ResourceProp r = resourceSvc.getResourceProp(prop1bResourcePropId);
		r.setPropValue("updated value");
		resourceSvc.updateResourceProp(r);
		assertEquals("updated value", resourceSvc.getResourceProp(r.getResourcePropId()).getPropValue());
	}


	
//	@Test
//	public void testAddResourceRole() {
//		resourceSvc.addResourceRole(role1);
//		resourceSvc.addResourceRole(role11);
//		resourceSvc.addResourceRole(role12);
//		resourceSvc.addResourceRole(role121);
//	}
//
//	@Test
//	public void testGetAllResourceRoles() {
//		assertEquals(4, resourceSvc.getAllResourceRoles().size());
//	}
//
//	@Test
//	public void testGetResourceRole() {
//		ResourceRole r = resourceSvc.getResourceRole(role1.getId());
//		assertEquals(role1.getId(), r.getId());
//	}
//
//
//	
//
//	@Test
//	public void testAddResourceUser() {
//		resourceSvc.addResourceUser(user10003000);
//		resourceSvc.addResourceUser(user10003001);
//		resourceSvc.addResourceUser(user11003000);
//	}
//
//	@Test
//	public void testGetAllResourceUsers() {
//		assertEquals(3, resourceSvc.getAllResourceUsers().size());
//	}
//
//	@Test
//	public void testGetResourceUser() {
//		ResourceUser r = resourceSvc.getResourceUser(user10003000.getId());
//		assertEquals(user10003000.getId(), r.getId());
//	}


	@Test
	public void testAddPropertyToResource() {
		resourceSvc.linkPropertyToResource(res1ResourceId, prop1aResourcePropId);		
		resourceSvc.linkPropertyToResource(res1ResourceId, prop1bResourcePropId);
		resourceSvc.linkPropertyToResource(res12ResourceId, prop12aResourcePropId);
		resourceSvc.linkPropertyToResource(res12ResourceId, prop12bResourcePropId);
		resourceSvc.linkPropertyToResource(res11ResourceId, prop11aResourcePropId);
		resourceSvc.linkPropertyToResource(res11ResourceId, prop11bResourcePropId);
		resourceSvc.linkPropertyToResource(res121ResourceId, prop121aResourcePropId);
		resourceSvc.linkPropertyToResource(res121ResourceId, prop121bResourcePropId);
		
	}


	@Test
	public void testFindResourceProperties() {
		List<ResourceProp> props = resourceSvc.findResourceProperties(res1ResourceId);
		assertEquals(2, props.size());
	}
	


	
	@Test
	public void testAddTypeToResource() {
		resourceSvc.linkTypeToResource(resType1ResourceTypeId, res1ResourceId);
		resourceSvc.linkTypeToResource(resType1ResourceTypeId, res11ResourceId);
		resourceSvc.linkTypeToResource(resType1ResourceTypeId, res12ResourceId);
		resourceSvc.linkTypeToResource(resType1ResourceTypeId, res121ResourceId);
	}
	
	@Test
	public void testFindTypeOfResource() {
		ResourceType t = resourceSvc.findTypeOfResource(res1ResourceId);
		assertEquals(resType1ResourceTypeId, t.getResourceTypeId());
	}

	@Test
	public void testGetAllResources() {
		assertEquals(4, resourceSvc.getAllResources().size());
	}
	

//	@Test
//	public void testFindResourceRoles() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddResourceRolePrivilege() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveResourceRolePrivilege() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveAllRolePrivilegesFromResource() {
//		fail("Not yet implemented");
//	}

	
	@Test
	public void testGetResourceTree() {
		resourceSvc.getResourceTree(this.res1ResourceId);
	}
	
	
	
	@Test
	public void testGetResourceTreeXml() {
		resourceSvc.getResourceTreeXML(this.res1ResourceId);
	}

	@Test
	public void testGetResourceFamily() {
		resourceSvc.getResourceFamily(this.res1ResourceId);
	}

	@Test
	public void testGetResourcesByType() {
		this.resourceSvc.getResourcesByType(resType1ResourceTypeId);				
	}

	@Test
	public void testGetRootResources() {
		this.resourceSvc.getRootResources();
	}

	@Test
	public void testGetResourcesByCategory() {
		this.resourceSvc.getResourcesByCategory(this.res1.getCategoryId());				
	}

	@Test
	public void testGetResourcesByBranch() {
		this.resourceSvc.getResourcesByBranch(res12.getBranchId());		
		
	}

//	@Test
//	public void testRemoveResourceUser() {
//		int x = resourceSvc.getAllResourceUsers().size();
//		resourceSvc.removeResourceUser(user10003000.getId());
//		assertEquals(x-1, resourceSvc.getAllResourceUsers().size());
//	}
//
//	@Test
//	public void testRemoveAllResourceUsers() {
//		resourceSvc.removeAllResourceUsers();
//		assertEquals(0, resourceSvc.getAllResourceUsers().size());
//	}
	
	

	@Test
	public void testRemoveAllPropertiesFromResource() {
		resourceSvc.unlinkAllPropertiesFromResource(res1ResourceId);
		resourceSvc.unlinkAllPropertiesFromResource(res11ResourceId);
		resourceSvc.unlinkAllPropertiesFromResource(res12ResourceId);
		resourceSvc.unlinkAllPropertiesFromResource(res121ResourceId);
	}
		

	@Test
	public void testRemovePropertyFromResource() {
		resourceSvc.linkPropertyToResource(prop121aResourcePropId, res121ResourceId);	
		resourceSvc.unlinkPropertyFromResource(prop121aResourcePropId, res121ResourceId);
	}

	@Test
	public void testRemoveResourceProp() {
		int x = resourceSvc.getAllResourceProps().size();
		resourceSvc.removeResourceProp(prop1aResourcePropId);
		assertEquals(x-1, resourceSvc.getAllResourceProps().size());
	}

	@Test
	public void testRemoveAllResourceProps() {
		resourceSvc.removeAllResourceProps();
		assertEquals(0, resourceSvc.getAllResourceProps().size());
	}

//	@Test
//	public void testRemoveResourceRole() {
//		int x = resourceSvc.getAllResourceRoles().size();
//		resourceSvc.removeResourceRole(role1.getId());
//		assertEquals(x-1, resourceSvc.getAllResourceRoles().size());
//	}
//
//	@Test
//	public void testRemoveAllResourceRoles() {
//		resourceSvc.removeAllResourceRoles();
//		assertEquals(0, resourceSvc.getAllResourceRoles().size());
//	}


	@Test
	public void testRemoveResourceParentRelationship() {
		resourceSvc.unlinkResourceFromParent(res121ResourceId);
		resourceSvc.unlinkResourceFromParent(res12ResourceId);
		resourceSvc.unlinkResourceFromParent(res11ResourceId);
	}
	
	

	@Test
	public void testRemoveResource() {
		int x = resourceSvc.getAllResources().size();
		resourceSvc.removeResource(res121ResourceId);
		assertEquals(x-1, resourceSvc.getAllResources().size());
	}

	@Test
	public void testRemoveAllResources() {
		resourceSvc.removeAllResources();
		assertEquals(0, resourceSvc.getAllResources().size());
	}

	@Test
	public void testRemoveResourceType() {
		int x = resourceSvc.getAllResourceTypes().size();
		resourceSvc.removeResourceType(resType1ResourceTypeId);
		assertEquals(x-1, resourceSvc.getAllResourceTypes().size());
	}

	@Test
	public void testRemoveAllResourceTypes() {
		resourceSvc.addResourceType(resType1);	
		resourceSvc.removeAllResourceTypes();
		assertEquals(0, resourceSvc.getAllResourceTypes().size());
	}
*/
	
}








