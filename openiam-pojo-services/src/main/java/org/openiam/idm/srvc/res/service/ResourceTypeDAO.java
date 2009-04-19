package org.openiam.idm.srvc.res.service;

import java.util.List;

import org.openiam.idm.srvc.res.dto.ResourceType;

/**
 * DAO Interface for ResourceType.
 * @author Suneet Shah
 *
 */
public interface ResourceTypeDAO {

	public abstract void add(ResourceType transientInstance);

	public abstract void remove(ResourceType persistentInstance);

	public abstract ResourceType update(ResourceType detachedInstance);

	public abstract ResourceType findById(java.lang.String id);

	public abstract List<ResourceType> findByExample(ResourceType instance);

}