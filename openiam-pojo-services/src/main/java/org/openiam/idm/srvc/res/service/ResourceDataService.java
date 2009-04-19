package org.openiam.idm.srvc.res.service;

import org.openiam.idm.srvc.res.dto.ResourceType;

/**
 * <code>ResourceDataService</code> component provides the ability to manage
 * the resource tree, resource type and the privileges to these resources.
 */
public interface ResourceDataService {

	 /**
	  * Adds a new resource type<br>
	  * For example: <p>
	  * <pre>
	  *   ResourceType val = new ResourceType();
	  *   val.setResourceTypeId(resourceTypeId);
	  *   val.setDescription(description);
	  *   resourceDataService.addResourceType(val);
	  *   <br>
	  * </pre>
	  *
	  * @param val ResourceTypeValue
	  */
	  
	  void addResourceType(ResourceType val);
	
}
