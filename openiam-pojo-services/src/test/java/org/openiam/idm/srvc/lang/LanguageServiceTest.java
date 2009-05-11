package org.openiam.idm.srvc.lang;

import org.junit.Test;
import org.openiam.idm.srvc.lang.dto.Language;
import org.openiam.idm.srvc.lang.service.LanguageDataService;
import org.openiam.idm.srvc.menu.dto.*;
import org.openiam.idm.srvc.menu.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class LanguageServiceTest extends AbstractDependencyInjectionSpringContextTests  {


	ApplicationContext ctx = null;

	Menu menu;
	LanguageDataService langDataService;
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml"} ) ;

		langDataService = (LanguageDataService)ctx.getBean("languageDataService");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testaddLanguage() {
				
		Language lg = new Language("da");
		lg.setName("Danish");
		langDataService.addLanguage(lg);
		
		
	}
	public void testGetLanguage() {
		Language lg = langDataService.getLanguage("da");
		this.assertNotNull(lg);
	}
	
	public void testAllLanguages() {
		Language[] lgAry = langDataService.allLanguages();
		this.assertEquals(8, lgAry.length);
	}

	public void testRemoveLanguage() {
		langDataService.removeLanguage("da");
		Language[] lgAry = langDataService.allLanguages();
		this.assertEquals(7, lgAry.length);
	}
}
