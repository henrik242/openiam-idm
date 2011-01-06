package org.openiam.idm.srvc.synch.ws;




import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;

/**
 * Interface for  <code>IdmAuditLogDataService</code>. All audit logging activities 
 * persisted through this service.
 */
@WebService(targetNamespace = "http://www.openiam.org/service/synch", name = "IdentitySynchWebService")
public interface IdentitySynchWebService {
	
	@WebMethod
	SynchConfigListResponse getAllConfig();
	
	@WebMethod
	SynchConfigResponse findById(
			@WebParam(name = "id", targetNamespace = "")
			java.lang.String id);
	
	@WebMethod
	SynchConfigResponse addConfig(
			@WebParam(name = "synchConfig", targetNamespace = "")
			SynchConfig synchConfig);

	@WebMethod
	SynchConfigResponse updateConfig(
			@WebParam(name = "synchConfig", targetNamespace = "")
			SynchConfig synchConfig);

	@WebMethod
	Response removeConfig(
			@WebParam(name = "config", targetNamespace = "")
			String configId);

	@WebMethod
	SyncResponse startSynchronization(
			@WebParam(name = "config", targetNamespace = "")
			SynchConfig config);

}