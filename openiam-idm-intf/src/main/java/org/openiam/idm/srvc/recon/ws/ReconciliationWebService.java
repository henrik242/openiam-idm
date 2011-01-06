package org.openiam.idm.srvc.recon.ws;




//import javax.jws.WebMethod;
//import javax.jws.WebService;

/**
 * Interface for  <code>IdmAuditLogDataService</code>. All audit logging activities 
 * persisted through this service.
 */
//@WebService(targetNamespace = "http://www.openiam.org/service/recon", name = "ReconciliationWebService")
public interface ReconciliationWebService {
	
	//@WebMethod
	ReconciliationConfigListResponse getAllConfig();
	



}