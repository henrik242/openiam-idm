package org.openiam.spml2.spi.ldap.client;

import org.openiam.spml2.msg.AddRequestType;

/**
 * Basic client to simplify interaction with the SPML service.  This service makes synchronous calls.
 * @author suneet shah
 *
 */
public class SimpleSynchClient {
	
	public void add(String targetId) {
		AddRequestType request = new AddRequestType();
		request.setTargetID(targetId);
		
		// call the service
		
	}

}
