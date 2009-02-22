package org.openiam.idm.srvc.meta.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Category;
import org.openiam.idm.srvc.meta.dto.MetadataElement;
import org.openiam.idm.srvc.meta.dto.MetadataType;

/**
 * Data service implementation for Metadata.
 * @author suneet
 * @version 1
 */
public class MetadataServiceImpl implements MetadataService {

	MetadataTypeDAO metadataTypeDao;
	MetadataElementDAO metadataElementDao;
	
	
	public void addMetadataElement(MetadataElement metadataElement) {
		if (metadataElement == null) {
			throw new NullPointerException("metadataElement is null");
		}
		metadataElementDao.add(metadataElement);
	}

	public void addMetadataType(MetadataType type) {
		if (type == null) {
			throw new NullPointerException("Metadatatype is null");
		}
		metadataTypeDao.add(type);

	}

	public void addTypeToCategory(String typeId, String categoryId) {
		if (typeId == null)
			throw new NullPointerException("typeId is null");
		if (categoryId == null)
			throw new NullPointerException("category is null");

		this.metadataTypeDao.addCategoryToType(typeId, categoryId);
		

	}

	public MetadataElement getMetadataElementById(String elementId) {
		if (elementId == null) {
			throw new NullPointerException("elementId is null");
		}
		return metadataElementDao.findById(elementId);
	}

	public MetadataElement[] getMetadataElementByType(String typeId) {
		if (typeId == null) {
			throw new NullPointerException("typeId is null");
		}
		MetadataType type = metadataTypeDao.findById(typeId);
		if (type == null ) 
			return null;
		Map<String, MetadataElement> elementMap = type.getElementAttributes();		
		if (elementMap == null || elementMap.isEmpty()) {
			return null;
		}
		// convert to an array
		Collection<MetadataElement> elementCollection = elementMap.values();
		MetadataElement[] elementAry = new MetadataElement[elementCollection.size()];
		elementCollection.toArray(elementAry);
		return elementAry;
	}

	public MetadataType getMetadataType(String typeId) {
		if (typeId == null) {
			throw new NullPointerException("typeId is null");
		}
		return metadataTypeDao.findById(typeId);
	}

	public MetadataType[] getMetadataTypes() {
		List<MetadataType> typeList =  metadataTypeDao.findAll();
		if (typeList == null || typeList.isEmpty()) {
			return null;
		}
		
		int size = typeList.size();
		MetadataType[] typeAry = new MetadataType[size];
		typeList.toArray(typeAry);
		
		return typeAry;
	}

	public MetadataType[] getTypesInCategory(String categoryId) {
	
		if (categoryId == null) {
			throw new NullPointerException("categoryId is null");
		}
		
		List<MetadataType> typeList = metadataTypeDao.findTypesInCategory(categoryId);
		if (typeList == null || typeList.isEmpty()) {
			return null;
		}
		int size = typeList.size();
		MetadataType[] typeAry = new MetadataType[size];
		typeList.toArray(typeAry);
		return typeAry;
	}

	public void removeMetadataElement(String elementId) {
		if (elementId == null) {
			throw new NullPointerException("elementId is null");
		}
		MetadataElement element = new MetadataElement(elementId);
		metadataElementDao.remove(element);
	}

	public void removeMetadataType(String typeId) {
		if (typeId == null) {
			throw new NullPointerException("typeId is null");
		}
		
		metadataElementDao.removeByParentId(typeId);
		
		MetadataType type = new MetadataType();
		type.setMetadataTypeId(typeId);
		metadataTypeDao.remove(type);
	}

	public void removeTypeFromCategory(String typeId, String categoryId) {
		if (typeId == null)
			throw new NullPointerException("typeId is null");
		if (categoryId == null)
			throw new NullPointerException("category is null");

		metadataTypeDao.removeCategoryFromType(typeId, categoryId);
	}

	public void updateMetadataElement(MetadataElement mv) {
		if (mv == null) {
			throw new NullPointerException("metadataElement is null");
		}
		metadataElementDao.update(mv);
	}

	public void updateMetdataType(MetadataType type) {
		if (type == null) {
			throw new NullPointerException("Metadatatype is null");
		}
		metadataTypeDao.update(type);

	}

	public MetadataTypeDAO getMetadataTypeDao() {
		return metadataTypeDao;
	}

	public void setMetadataTypeDao(MetadataTypeDAO metadataTypeDao) {
		this.metadataTypeDao = metadataTypeDao;
	}

	public MetadataElementDAO getMetadataElementDao() {
		return metadataElementDao;
	}

	public void setMetadataElementDao(MetadataElementDAO metadataElementDao) {
		this.metadataElementDao = metadataElementDao;
	}

}
