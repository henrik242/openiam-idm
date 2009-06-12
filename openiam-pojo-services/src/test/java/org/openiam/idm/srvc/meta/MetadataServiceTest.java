package org.openiam.idm.srvc.meta;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.meta.service.MetadataService;
import org.openiam.idm.srvc.meta.dto.MetadataType;
import org.openiam.idm.srvc.meta.dto.MetadataElement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class MetadataServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	MetadataService metadataSrvc;

	MetadataType type;
	MetadataElement element1, element2;
	static String elementId;
 
	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/metadataTest-applicationContext.xml"
				});
		metadataSrvc = (MetadataService) ctx.getBean("metadataService");
		type =(MetadataType) ctx.getBean("metadataTypeBean");
		
		element1 = (MetadataElement)ctx.getBean("element1");
		element2 = (MetadataElement)ctx.getBean("element2");
	}

	@Test
	public void testAddMetadataType() {
		metadataSrvc.addMetadataType(type);

	}

	public void testGetMetadataType() {
		MetadataType testType = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		assertNotNull(testType);
		
	}

	public void testGeAllMetadataType() {
		MetadataType[] typeAry = metadataSrvc.getMetadataTypes();
		assertNotNull(typeAry);
		assertEquals(10, typeAry.length);
		
	}


	public void testGetElementsByType() {
		MetadataElement[] typeAry = metadataSrvc.getMetadataElementByType("InetOrgPerson");
		assertNotNull(typeAry);
		assertEquals(10, typeAry.length);
		
	}
	
	public void testAddTypeToCategory() {
		metadataSrvc.addTypeToCategory(type.getMetadataTypeId(), "ROOT");
	}

	public void testGeAllMetadataTypeWithCategory() {
		MetadataType[] typeAry = metadataSrvc.getMetadataTypes();
		assertNotNull(typeAry);
		
		// check to see if the embedded objects are initialized.
		int size = typeAry.length;
		for (int i=0; i<size; i++) {
			MetadataType type = typeAry[i];
			type.getElementAttributes();
			
		}
		assertEquals(10, typeAry.length);
		
	}
	
	public void testGetTypeForCategory() {
		MetadataType[] typeAry = metadataSrvc.getTypesInCategory("ROOT");
		assertNotNull(typeAry);
		assertEquals(1, typeAry.length);
	}
	
	public void testRemoveTypeFromCategory() {
		metadataSrvc.removeTypeFromCategory(type.getMetadataTypeId(),"ROOT");
		MetadataType[] typeAry = metadataSrvc.getTypesInCategory("ROOT");
		assertNull(typeAry);

	}
	
	
	public void testUpdateMetadataType() {
		MetadataType testType = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		assertNotNull(testType);
		
		testType.setDescription("Updated des");
		metadataSrvc.updateMetdataType(testType);

		MetadataType testType2 = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		this.assertEquals("Updated des", testType2.getDescription());
	}

	public void testRemoveMetadataType() {
		metadataSrvc.removeMetadataType(type.getMetadataTypeId());
		MetadataType testType = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		assertNull(testType);
	}	
	
	public void testAddTypeWithAttr() {
		Map<String, MetadataElement> elementList = type.getElementAttributes();
		elementList.put(element1.getAttributeName(),element1);
		elementList.put(element2.getAttributeName(), element2);
		metadataSrvc.addMetadataType(type);
	}

	public void testGetTypeWithAttr() {
		MetadataType type1 = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		Map<String, MetadataElement> elementList = type1.getElementAttributes();
		assertEquals(2, elementList.size()); 
	}	
	
	public void testUpdateTypeWithAttr() {
		MetadataType type1 = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		type1.setDescription("Updated desc");
		
		Map<String, MetadataElement> elementList = type1.getElementAttributes();
		if (!elementList.isEmpty()) {
			MetadataElement el = elementList.get(element1.getAttributeName());
			el.setDescription("change des");
		}

		
		metadataSrvc.updateMetdataType(type1);
		
	}
	
	public void testRemoveTypeWithAttr() {
		metadataSrvc.removeMetadataType(type.getMetadataTypeId());
	}

	public void testAddMetadataType2() {
		metadataSrvc.addMetadataType(type);

	}
	
	public void testAddElement() {
		
		metadataSrvc.addMetadataElement(element1);
		elementId = element1.getMetadataElementId();
	}
	
	public void testGetElementById() {
	
		MetadataElement elm = metadataSrvc.getMetadataElementById(elementId);
		assertNotNull(elm);
		assertEquals(elm.getMetadataElementId(),elementId);
	}
	
	public void testGetElementByType() {
		MetadataElement[] elmAry = metadataSrvc.getMetadataElementByType(element1.getMetadataTypeId());
		assertNotNull(elmAry);
		assertEquals(elmAry.length, 1);
		

	}
	public void testUpdateElement() {
		MetadataElement elm = metadataSrvc.getMetadataElementById(elementId);
		elm.setAttributeName("upd attr name");
		metadataSrvc.updateMetadataElement(elm);
	}
	public void testRemoveElement() {
		metadataSrvc.removeMetadataElement(elementId);
		MetadataElement elm = metadataSrvc.getMetadataElementById(elementId);
		assertNull(elm);
		
	}
	public void testRemoveMetadataType2() {
		metadataSrvc.removeMetadataType(type.getMetadataTypeId());
		MetadataType testType = metadataSrvc.getMetadataType(type.getMetadataTypeId());
		assertNull(testType);
	}	
	
	
}
