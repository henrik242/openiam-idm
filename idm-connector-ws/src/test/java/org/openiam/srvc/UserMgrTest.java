package org.openiam.srvc;

import static org.junit.Assert.*;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import java.util.*;

import org.openiam.idm.srvc.user.service.*;
import org.openiam.idm.srvc.user.dto.*;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.ExtensibleAttribute;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;


import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class UserMgrTest extends AbstractDependencyInjectionSpringContextTests  {

	ApplicationContext ctx = null;

	UserDataService userMgr;
	User user, user10;

	static String userId;

	
	@Override

	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
	
		
	} 

	
	public void testAdd() {
		ClientProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ConnectorService.class);
           
        factory.setAddress("http://localhost:8081/idm-connector-ws/ExampleConnectorService");
         javax.xml.namespace.QName qname = javax.xml.namespace.QName.valueOf("http://www.openiam.org/service/connector");
         factory.setEndpointName(qname);
         ConnectorService client = (ConnectorService) factory.create();    
            
            
        AddRequestType addReqType = new AddRequestType();
        PSOIdentifierType idType = new PSOIdentifierType("test.user",null, "target");
        addReqType.setPsoID(idType);
        addReqType.setRequestID("R4589");
        addReqType.setTargetID("100");
          
        ExtensibleUser extUser = new ExtensibleUser();
        extUser.setName("Test User");
  /*      extUser.getAttributes().add(new ExtensibleAttribute("cn","Test User"));
        extUser.getAttributes().add(new ExtensibleAttribute("givenname","Test"));
        extUser.getAttributes().add(new ExtensibleAttribute("sn","User"));
        extUser.getAttributes().add(new ExtensibleAttribute("description","Test User"));
        extUser.getAttributes().add(new ExtensibleAttribute("mail","test.user@openiam.com"));
    */    
          
        addReqType.getData().getAny().add(extUser);
                      
        client.add(addReqType);

        ResponseType resp =  client.add(addReqType); 
	}
	



	
		
}








