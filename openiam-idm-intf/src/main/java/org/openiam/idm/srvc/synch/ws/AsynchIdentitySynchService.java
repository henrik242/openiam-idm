package org.openiam.idm.srvc.synch.ws;

import org.openiam.idm.srvc.synch.dto.SyncResponse;
import org.openiam.idm.srvc.synch.dto.SynchConfig;

import java.util.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Interface for <code>AsynchIdentitySynchService</code>. This interface is used in an asynchronous manner.
 */
@WebService(targetNamespace = "http://www.openiam.org/service/synch", name = "AsynchIdentitySynchService")
public interface AsynchIdentitySynchService {

	@WebMethod
	void startSynchronization(
			@WebParam(name = "config", targetNamespace = "")
			SynchConfig config);

}