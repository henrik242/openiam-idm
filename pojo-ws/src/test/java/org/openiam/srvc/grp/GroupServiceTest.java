package org.openiam.srvc.grp;


import static org.junit.Assert.*;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.grp.dto.Group;
import org.openiam.idm.srvc.grp.dto.GroupAttribute;
import org.openiam.idm.srvc.grp.ws.GroupDataWebService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;


public class GroupServiceTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;
	GroupDataWebService client = null;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"classpath:applicationContext.xml" } ) ;
		
		client = (GroupDataWebService)ctx.getBean("groupWS");

		
	}

	public void testAdd() {
		
		   ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		   factory.setServiceClass(GroupDataWebService.class);
          
          factory.setAddress("http://localhost:8081/idm-ws/idmsrvc/GroupDataWebService");
          //javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("urn:idm.openiam.org/srvc/grp/service");
          //factory.setEndpointName(qname);
          GroupDataWebService client = (GroupDataWebService) factory.create();    
            
          Group grp = new Group();
          grp.setGroupClass("USER_GROUP");
          grp.setGrpName("My Group");
          grp.setInheritFromParent(false);
          
          GroupAttribute grpAtr = new GroupAttribute();
          grpAtr.setName("NAME1");
          grpAtr.setValue("value1");
          grp.getAttributes().put(grpAtr.getName(), grpAtr);
         // grpAtr.setGroup(grp);
          
          client.addGroup(grp);
          
 
	}
	


		
}








