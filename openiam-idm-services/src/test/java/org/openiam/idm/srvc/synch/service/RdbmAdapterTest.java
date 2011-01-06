package org.openiam.idm.srvc.synch.service;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.synch.srcadapter.RDBMSAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class RdbmAdapterTest extends AbstractDependencyInjectionSpringContextTests {

	ApplicationContext ctx = null;

	RDBMSAdapter rdbmsAdapter;


	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"/applicationContext.xml", "/idmservice-Context.xml.xml"
				});
		rdbmsAdapter = (RDBMSAdapter) ctx.getBean("rdbmsAdapter");

	}

	@Test
	public void testDatabaseSynch() {
		System.out.println("testing database synch");

	}
	
	
}
