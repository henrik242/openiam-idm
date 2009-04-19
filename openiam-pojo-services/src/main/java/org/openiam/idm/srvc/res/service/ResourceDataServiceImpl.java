package org.openiam.idm.srvc.res.service;

import org.openiam.idm.srvc.res.dto.ResourceType;

public class ResourceDataServiceImpl implements ResourceDataService {

	ResourceTypeDAO resourceTypeDao;
	ResourceDAO resourceDao;
	
	public void addResourceType(ResourceType val) {
		if (val == null)
			throw new NullPointerException("ResourcType is null");
		
		resourceTypeDao.add(val);
	}

	public ResourceTypeDAO getResourceTypeDao() {
		return resourceTypeDao;
	}

	public void setResourceTypeDao(ResourceTypeDAO resourceTypeDao) {
		this.resourceTypeDao = resourceTypeDao;
	}

	public ResourceDAO getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

}
