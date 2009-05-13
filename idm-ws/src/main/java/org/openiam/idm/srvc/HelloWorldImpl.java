package org.openiam.idm.srvc;

import javax.jws.WebService;

import java.util.*;



@WebService(endpointInterface = "org.openiam.idm.srvc.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    
    public HelloWorldImpl() {
            super();
            

    }

    public String test() {
    	return "WS in HelloWorld called..";
    }

	
  	


}
