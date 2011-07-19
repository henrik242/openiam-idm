package org.openiam.idm.srvc.meta.service;

import java.util.List;

import org.openiam.idm.srvc.meta.dto.MetadataElement;

/**
 * DAO Interface for MetadataElement
 * @author suneet
 *
 */
public interface MetadataElementDAO {

	 void add(MetadataElement transientInstance);

	void remove(MetadataElement persistentInstance);

	MetadataElement update(MetadataElement detachedInstance);

	MetadataElement findById(java.lang.String id);

	List<MetadataElement> findByExample(MetadataElement instance);
	
	void removeByParentId(String id);
	List<MetadataElement> findbyCategoryType(String categoryType) ;
	
}