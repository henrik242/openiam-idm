package org.openiam.idm.srvc.meta.service;

import java.util.List;

import org.openiam.idm.srvc.meta.dto.MetadataElement;

/**
 * DAO Interface for MetadataElement
 * @author suneet
 *
 */
public interface MetadataElementDAO {

	public abstract void add(MetadataElement transientInstance);

	public abstract void remove(MetadataElement persistentInstance);

	public abstract MetadataElement update(MetadataElement detachedInstance);

	public abstract MetadataElement findById(java.lang.String id);

	public abstract List<MetadataElement> findByExample(MetadataElement instance);
	
	void removeByParentId(String id);
	
}