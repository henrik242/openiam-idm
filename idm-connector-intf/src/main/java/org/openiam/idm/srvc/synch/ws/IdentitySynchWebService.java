package org.openiam.idm.srvc.synch.ws;




import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface for  <code>IdmAuditLogDataService</code>. All audit logging activities 
 * persisted through this service.
 */
@WebService(targetNamespace = "http://www.openiam.org/service/synch", name = "IdentitySynchWebService")
public interface IdentitySynchWebService {
	
	@WebMethod
	SynchConfigListResponse getAllConfig();
	



}