package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;

public interface ManagedSystemObjectMatchDAO {

	public abstract void add(ManagedSystemObjectMatch transientInstance);

	public abstract void remove(ManagedSystemObjectMatch persistentInstance);

	public ManagedSystemObjectMatch update(ManagedSystemObjectMatch detachedInstance);

	/**
	 * Finds objects for an object type (like User, Group) for a ManagedSystem definition
	 * @param managedSystemId
	 * @param objectType
	 * @return
	 */
	public List<ManagedSystemObjectMatch> findBySystemId(String managedSystemId, String objectType);
	
	public ManagedSystemObjectMatch findById(java.lang.String id);

	public abstract List<ManagedSystemObjectMatch> findByExample(
			ManagedSystemObjectMatch instance);

}