package org.openiam.idm.srvc.prov.request.service;

import java.util.List;

import org.openiam.idm.srvc.prov.request.dto.ProvisionRequest;

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

	public abstract List<ProvisionRequest> findByExample(
			ProvisionRequest instance);

	public abstract List<ProvisionRequest> findByStatus(String status);
}