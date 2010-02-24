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
package org.openiam.idm.srvc.lang.dto;

import java.io.Serializable;

/**
 * Domain object representing a language
 * @author suneet
 *
 */
public class Language implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6695606794883491243L;
	
	private String langCd;
	private String name;
	private boolean locale;
	

	public Language() {
	}
	
	public Language(String langCd) {
		super();
		this.langCd = langCd;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Indicates if a language is part of the locale list and therefore a language that is supported on the OpenIAM UI.
	 * @return
	 */
	public boolean isLocale() {
		return locale;
	}
	/*
	 * Indicates if a language is part of the locale list and therefore a language that is supported on the OpenIAM UI. True - indicates 
	 * that it is part of the locale.
	 */
	public void setLocale(boolean locale) {
		this.locale = locale;
	}
	
}
