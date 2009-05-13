package org.openiam.idm.srvc;

import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public interface HelloWorld {

	String test();
	
	//Subject login(String userId, String password);
}
