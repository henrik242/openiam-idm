package org.openiam.idm.srvc.recon.ws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.base.ws.Response;



import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.recon.dto.ReconciliationResponse;

/**
 * Interface for  <code>ReconciliationWebService</code>. Service is responsible for activities related to reconcilation
 * persisted through this service.
 */
@WebService(targetNamespace = "http://www.openiam.org/service/recon", name = "ReconciliationWebService")
public interface ReconciliationWebService {

    @WebMethod
	ReconciliationConfigResponse addConfig(
            @WebParam(name = "config", targetNamespace = "")
            ReconciliationConfig config);

    @WebMethod
    ReconciliationConfigResponse updateConfig(
            @WebParam(name = "config", targetNamespace = "")
            ReconciliationConfig config);

    @WebMethod
    Response removeConfigByResourceId(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

    @WebMethod
    ReconciliationConfigResponse getConfigById(
            @WebParam(name = "configId", targetNamespace = "")
            String configId);

    @WebMethod
    ReconciliationConfigResponse getConfigByResource(
            @WebParam(name = "resourceId", targetNamespace = "")
            String resourceId);

    @WebMethod
      Response removeConfig(
             @WebParam(name = "configId", targetNamespace = "")
            String configId) ;

    ReconciliationResponse startReconciliation(
			@WebParam(name = "config", targetNamespace = "")
            ReconciliationConfig config);



}