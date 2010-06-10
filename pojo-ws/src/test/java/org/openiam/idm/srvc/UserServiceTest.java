package org.openiam.idm.srvc;


import static org.junit.Assert.*;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.dto.User;
import org.openiam.idm.srvc.user.dto.UserAttribute;
import org.openiam.idm.srvc.user.dto.UserStatusEnum;
import org.openiam.idm.srvc.user.ws.UserDataWebService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;



public class UserServiceTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;
	UserDataWebService client = null;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		ctx = new ClassPathXmlApplicationContext(
					new String[] {"classpath:applicationContext.xml" } ) ;
		
		client = (UserDataWebService)ctx.getBean("userWS");

		
	}

	public void testAdd() {
		
		   ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		   factory.setServiceClass(UserDataWebService.class);
          
          factory.setAddress("http://localhost:8081/idm-ws/idmsrvc/UserDataService");
          client = (UserDataWebService) factory.create();    
            
          User user = new User();
          user.setFirstName("Joe");
          user.setLastName("Frate");
          user.setStatus(UserStatusEnum.ACTIVE);
          user.setAddress1("123 Main Street");
          user.setCompanyId("100");
          user.setDeptCd("101");
          user.setMetadataTypeId("InetOrgPerson");
          user.setClassification("vendor");
          
          
          UserAttribute userAtr = new UserAttribute();
          userAtr.setName("NAME1");
          userAtr.setValue("value1");
          user.getUserAttributes().put(userAtr.getName(), userAtr);
         // grpAtr.setGroup(grp);
          client.addUser(user);
          
          
 
	}
	


		
}








