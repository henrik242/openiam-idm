package org.openiam.idm.srvc.meta.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openiam.idm.srvc.cat.dto.Category;
import org.openiam.idm.srvc.meta.dto.MetadataType;

/**
 * DAO Interface for MetadataType
 * @author suneet
 *
 */
public interface MetadataTypeDAO {

	public abstract void add(MetadataType transientInstance);

	public abstract void remove(MetadataType persistentInstance);

	public abstract MetadataType update(MetadataType detachedInstance);

	public abstract MetadataType findById(java.lang.String id);

	public abstract List<MetadataType> findByExample(MetadataType instance);
	
	List<MetadataType> findAll();
	
	
	void addCategoryToType(String typeId, String categoryId);
	
	void removeCategoryFromType(String typeId, String categoryId);
	
	 List<MetadataType> findTypesInCategory(String categoryId);

}

