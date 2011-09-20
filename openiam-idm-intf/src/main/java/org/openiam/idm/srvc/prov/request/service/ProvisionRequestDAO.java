package org.openiam.idm.srvc.prov.request.service;

import java.util.List;

import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;
import org.openiam.idm.srvc.prov.request.dto.SearchRequest;

/**
 * DAO interface for the ProvisionRequest object.
 * @author suneet
 *
 */
public interface ProvisionRequestDAO {

	public abstract void add(ProvisionRequest transientInstance);

	public abstract void remove(ProvisionRequest persistentInstance);

	public abstract ProvisionRequest update(ProvisionRequest detachedInstance);

	public abstract ProvisionRequest findById(java.lang.String id);


	List<ProvisionRequest> search(SearchRequest search);
	
	List<ProvisionRequest> findRequestByApprover(String approverId, String status);
}