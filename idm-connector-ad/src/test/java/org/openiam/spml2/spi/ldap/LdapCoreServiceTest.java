package org.openiam.spml2.spi.ldap;


import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

import org.openiam.spml2.interf.SpmlCore;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.CapabilityDataType;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.ExecutionModeType;
import org.openiam.spml2.msg.ReturnDataType;
import org.openiam.spml2.msg.ExtensibleType;

public class LdapCoreServiceTest extends AbstractDependencyInjectionSpringContextTests {


	SpmlCore spml = null;
	

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		
	
    	ClassPathXmlApplicationContext context 
        = new ClassPathXmlApplicationContext(new String[] {"testDirBeansContext.xml", "classpath:applicationContext.xml"});

    	spml = (SpmlCore)context.getBean("ldapSpmlCoreService");
		
	}



	@Test
	public void testAddUser() {
		ExtensibleType data = new ExtensibleType();
		
		PSOIdentifierType psoId = new PSOIdentifierType("psoId", null, "psoTarget");
		PSOIdentifierType containerId = new PSOIdentifierType("psoContainerId", null, "psoTarget");
		
        CapabilityDataType[] cda = new CapabilityDataType[]{
                new CapabilityDataType(true, "foo:1"),
                new CapabilityDataType(true, "foo:2"),
                new CapabilityDataType(true, "foo:3"),
                new CapabilityDataType(true, "foo:4"),
                new CapabilityDataType(true, "foo:5"),
            };
        AddRequestType addReq = new AddRequestType();
        addReq.setTargetID("100");
        spml.add(addReq);
		
		System.out.println(" WS call result=" +  spml );
	}
	

	
}
