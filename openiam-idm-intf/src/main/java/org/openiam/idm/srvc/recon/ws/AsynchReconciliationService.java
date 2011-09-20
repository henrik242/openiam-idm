package org.openiam.idm.srvc.recon.ws;

import org.openiam.idm.srvc.recon.dto.ReconciliationConfig;
import org.openiam.idm.srvc.synch.dto.SynchConfig;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Interface for <code>AsynchReconciliationService</code>. This interface is used in an asynchronous manner for reconciliation.
 */
@WebService(targetNamespace = "http://www.openiam.org/service/recon", name = "AsynchReconciliationWebService")
public interface AsynchReconciliationService {

	@WebMethod
	void startReconciliation(
            @WebParam(name = "config", targetNamespace = "")
            ReconciliationConfig config);

}