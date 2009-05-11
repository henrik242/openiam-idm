/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the Lesser GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.lang.service;

import java.util.List;

import org.openiam.idm.srvc.lang.dto.Language;

/**
 * @author suneet
 *
 */
public class LanguageDataServiceImpl implements LanguageDataService {

	LanguageDAO languageDao;
	
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDataService#addLanguage(org.openiam.idm.srvc.lang.dto.Language)
	 */
	public void addLanguage(Language lg) {
		if (lg == null) {
			throw new NullPointerException("lg is null");
		}
		languageDao.add(lg);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDataService#allLanguages()
	 */
	public Language[] allLanguages() {
		List<Language> lgList = languageDao.findAllLanguages();
		if (lgList == null || lgList.isEmpty())
			return null;
		int size = lgList.size();
		Language[] lgAry = new Language[size];
		lgList.toArray(lgAry);
		return lgAry;
		
		
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDataService#getLanguage(java.lang.String)
	 */
	public Language getLanguage(String languageCd) {

		if (languageCd == null) {
			throw new NullPointerException("languageCd is null");
		}
		return languageDao.findById(languageCd);
	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDataService#removeLanguage(java.lang.String)
	 */
	public void removeLanguage(String langCd) {
		if (langCd == null) {
			throw new NullPointerException("languageCd is null");
		}
		Language lg = new Language(langCd);
		languageDao.remove(lg);

	}

	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.lang.service.LanguageDataService#updateLanguage(org.openiam.idm.srvc.lang.dto.Language)
	 */
	public void updateLanguage(Language lg) {
		if (lg == null) {
			throw new NullPointerException("lg is null");
		}
		languageDao.update(lg);

	}

	public LanguageDAO getLanguageDao() {
		return languageDao;
	}

	public void setLanguageDao(LanguageDAO languageDao) {
		this.languageDao = languageDao;
	}

}
