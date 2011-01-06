package org.openiam.idm.srvc.cat;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.cat.service.CategoryDataService;
import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.cat.dto.CategoryLanguage;
import org.openiam.idm.srvc.cat.dto.CategoryLanguageId;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class CategoryServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	CategoryDataService catdataSrvc;

	Category cat, catsub1, catsub2, catsub3;
	static String categoryId, subCategoryId;

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/idmservice-Context.xml"
				});
		catdataSrvc = (CategoryDataService) ctx.getBean("categorydataService");
		cat = (Category)ctx.getBean("category");
		catsub1 = (Category) ctx.getBean("category2");
		catsub2 = (Category) ctx.getBean("category3");
		catsub3 = (Category) ctx.getBean("category4");

	}

	@Test
	public void testAddCategory() {
		catdataSrvc.addCategory(cat);
		
		categoryId = cat.getCategoryId();

	}
	
	public void testGetCategory() {
		Category c = catdataSrvc.getCategory(categoryId);
		
		this.assertNotNull(c);
		this.assertEquals(categoryId, c.getCategoryId());
	}
	
	public void testUpdateCategory() {
		Category c = catdataSrvc.getCategory(categoryId);
		c.setCategoryDesc("Update cat. desc");
		
		catdataSrvc.updateCategory(c);
		
		Category ct = catdataSrvc.getCategory(categoryId);
		assertNotNull(ct);
		assertEquals("Update cat. desc", ct.getCategoryDesc());
		
	}
	
	public void testAddChildCategories() {
		
		// first child node
		catsub1.setParentId(categoryId);
		catdataSrvc.addCategory(catsub1);
				
		subCategoryId = catsub1.getCategoryId();
		
		// 2nd level of child nodes
		catsub2.setParentId(subCategoryId);
		catsub3.setParentId(subCategoryId);
		
		catdataSrvc.addCategory(catsub2);
		catdataSrvc.addCategory(catsub3);
		
	}
	
	public void testChildNodesFromRootNotNested() {
		Category[] catAry = catdataSrvc.getAllCategories(false);
		assertNotNull(catAry);
		assertEquals(2,catAry.length);
		assertNull(catAry[0].getChildCategories());
	
	}
	public void testChildNodesFromRootNested() {
		Category[] catAry = catdataSrvc.getAllCategories(true);
		assertNotNull(catAry);
		assertEquals(2,catAry.length);
		assertEquals(1,catAry[0].getChildCategories().length);
	
	}
	public void testChildNodesFromLevel1NotNested() {
		Category[] catAry = catdataSrvc.getChildCategories(categoryId, false); 
		assertNotNull(catAry);
		assertEquals(1,catAry.length);
		assertNull(catAry[0].getChildCategories());
		
	}
	
	public void testChildNodesFromLevel1Nested() {
		Category[] catAry = catdataSrvc.getChildCategories(categoryId, true); 
		assertNotNull(catAry);
		assertEquals(1,catAry.length);
		assertEquals(2,catAry[0].getChildCategories().length);
		
	}
	
	public void testRemoveCategoryWithChildren() {
		catdataSrvc.removeCategory(subCategoryId,true); 
		Category c = catdataSrvc.getCategory(subCategoryId);
		this.assertNull(c);				
	}
	
	
	public void testRemoveCategory() {
	
		catdataSrvc.removeCategory(categoryId,false); 
		Category c = catdataSrvc.getCategory(categoryId);
		this.assertNull(c);		
	}

	public void testAddCategoryWithLang() {
		catdataSrvc.addCategory(cat);
		
		categoryId = cat.getCategoryId();

		CategoryLanguageId id = new CategoryLanguageId(categoryId,"en");
		CategoryLanguage lang = new CategoryLanguage(id, "Lang Name");
		
		Set<CategoryLanguage> langSet = cat.getCategoryLanguages();
		langSet.add(lang);
		
		catdataSrvc.updateCategory(cat);
		
	}
	public void testRemoveCategoryWithLanguage() {
		
		catdataSrvc.removeCategory(categoryId,false); 
		Category c = catdataSrvc.getCategory(categoryId);
		this.assertNull(c);		
	}
	
}
