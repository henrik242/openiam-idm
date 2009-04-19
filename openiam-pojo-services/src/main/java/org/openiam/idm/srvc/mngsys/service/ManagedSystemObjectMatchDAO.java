package org.openiam.idm.srvc.mngsys.service;

import java.util.List;

import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;

public interface ManagedSystemObjectMatchDAO {

	public abstract void add(ManagedSystemObjectMatch transientInstance);

	public abstract void remove(ManagedSystemObjectMatch persistentInstance);

	public abstract ManagedSystemObjectMatch update(
			ManagedSystemObjectMatch detachedInstance);

	public abstract ManagedSystemObjectMatch findById(java.lang.String id);

	public abstract List<ManagedSystemObjectMatch> findByExample(
			ManagedSystemObjectMatch instance);

}