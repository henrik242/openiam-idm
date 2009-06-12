package org.openiam.idm.srvc.mngsys;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.org.service.*;
import org.openiam.idm.srvc.org.dto.*;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openiam.base.AbstractOpenIAMTestCase;

public class ConnectorServiceTest extends AbstractOpenIAMTestCase {

	ApplicationContext ctx = null;

	ConnectorDataService connectService;

	ProvisionConnector con; 
	static String connectorId, connectorId2 = null;


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml" });
		connectService = (ConnectorDataService) ctx.getBean("connectorService");
	}

	@Test
	public void testAdd() {
		con = new ProvisionConnector();
		con.setClientCommProtocol("SSL");
		con.setName("Test Con");
		con.setServiceUrl("http://host/testUrl");
		connectService.addConnector(con);
		
		connectorId = con.getConnectorId();
		assertNotNull(connectorId);
		
	}

	public void testAdd2() {
		ProvisionConnector con2 = new ProvisionConnector();
		con2.setClientCommProtocol("SSL");
		con2.setName("Test Con2");
		con2.setServiceUrl("http://host/testUrl2");
		connectService.addConnector(con2);
		
		connectorId2 = con2.getConnectorId();
		
	}
	
	@Test
	public void testUpdate() {
		con = connectService.getConnector(connectorId);
		assertNotNull(con);
		
		con.setName("Updated name");
		connectService.updateConnector(con);
		
		ProvisionConnector con2 = connectService.getConnector(connectorId);
		assertEquals("Updated name", con2.getName());			
	}	

	public void testGetAll() {
		ProvisionConnector[] conAry = connectService.getAllConnectors();
		assertEquals(2, conAry.length);
					
	}	

	public void testRemove() {
		connectService.removeConnector(connectorId); 
		this.assertNull(connectService.getConnector(connectorId));
					
	}

	public void testRemove2() {
		connectService.removeConnector(connectorId2); 
		this.assertNull(connectService.getConnector(connectorId2));
					
	}
}
