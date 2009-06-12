package org.openiam.idm.srvc.loc;

import org.junit.Test;
import org.openiam.idm.srvc.lang.dto.Language;
import org.openiam.idm.srvc.loc.dto.Location;
import org.openiam.idm.srvc.loc.service.LocationDataService;
import org.openiam.idm.srvc.menu.dto.*;
import org.openiam.idm.srvc.menu.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class LocationServiceTest extends AbstractDependencyInjectionSpringContextTests  {


	ApplicationContext ctx = null;


	LocationDataService locationDataService;
	static String locId;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"/applicationContext.xml"} ) ;


		locationDataService = (LocationDataService)ctx.getBean("locationDataService");
		
		
	} 

	
	/* Test direct address methods */
	
	@Test
	public void testaddLocation() {
				
		Location loc = new Location();
		loc.setName("Home");
		loc.setAddress1("Some St");
		loc.setBldgNum("124");
		loc.setStreetDirection("N");
		loc.setCity("mycity");
		loc.setState("mystate");
		loc.setPostalCd("12345");
		locationDataService.addLocation(loc);
		
		locId = loc.getLocationId();
		assertNotNull(locId);
		
	}

	
	public void testAllLocations() {
		Location[] lgAry = locationDataService.allLocations();
		this.assertEquals(1, lgAry.length);
	}

	public void testRemoveLocation() {
		locationDataService.removeLocation(locId);
		Location[] lgAry = locationDataService.allLocations();
		assertNull(lgAry);
	}}
