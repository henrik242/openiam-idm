package org.openiam.idm.srvc.menu;

import org.junit.Test;
import org.openiam.idm.srvc.menu.dto.*;
import org.openiam.idm.srvc.menu.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class NavigatorServiceTest extends AbstractOpenIAMTestCase  {


	ApplicationContext ctx = null;

	Menu menu;
	NavigatorDataService navDataService;
	

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml",
								  "/navigatorTest-applicationContext.xml"} ) ;

		menu = (Menu)ctx.getBean("menuBean");
		navDataService = (NavigatorDataService)ctx.getBean("navigatorDataService");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testaddMenu() {
				
		//navDataService.addMenu(menu); 		
		
		
	}
	public void testMenuByUser() {
		
		Menu[] menuAry = navDataService.menuGroupByUser("SELFCENTER", "3101", "en"); 	
		this.assertNotNull(menuAry);
		assertEquals(menuAry.length,3);
		
		
	}

	public void testMenuGroupSelectedByUser() {
		
		Menu[] menuAry = navDataService.menuGroupSelectedByUser("SELFCENTER", "3101", "en"); 	
		this.assertNotNull(menuAry);
		assertEquals(menuAry.length,7);
		
		
	}
}
