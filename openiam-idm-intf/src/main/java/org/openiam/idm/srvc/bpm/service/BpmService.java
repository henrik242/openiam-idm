package org.openiam.idm.srvc.bpm.service;



import java.util.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;

/**
 * Interface for <code>BpmService</code>. Provides a service layer to interact with the process engine.
 */
@WebService(targetNamespace = "http://www.openiam.org/service/bpm", name = "BpmWebService")
public interface BpmService {

	@WebMethod
	Response getBpmSecurityToken(
			@WebParam(name = "principal", targetNamespace = "")
			String principal, 
			@WebParam(name = "password", targetNamespace = "")
			String password);
	

}