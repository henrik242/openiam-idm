package org.openiam.idm.srvc.res.service;

import java.util.List;

import org.openiam.idm.srvc.res.dto.ResourceType;

public interface ResourceTypeDAO {

	void remove(ResourceType persistentInstance);

	ResourceType update(ResourceType detachedInstance);

	ResourceType findById(java.lang.String id);

	List<ResourceType> findByExample(ResourceType instance);

	ResourceType add(ResourceType instance);

	List<ResourceType> findAllResourceTypes();

	int removeAllResourceTypes();

}