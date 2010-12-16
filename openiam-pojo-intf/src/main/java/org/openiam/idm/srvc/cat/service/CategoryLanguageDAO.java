package org.openiam.idm.srvc.cat.service;

import java.util.List;

import org.openiam.idm.srvc.cat.dto.CategoryLanguage;

public interface CategoryLanguageDAO {

	public abstract void add(CategoryLanguage transientInstance);

	public abstract void remove(CategoryLanguage persistentInstance);

	public abstract CategoryLanguage update(CategoryLanguage detachedInstance);

	public abstract CategoryLanguage findById(
			org.openiam.idm.srvc.cat.dto.CategoryLanguageId id);

	public abstract List<CategoryLanguage> findByExample(
			CategoryLanguage instance);

}