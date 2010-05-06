/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
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
package org.openiam.idm.srvc.cd.service;

import java.util.List;

import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.dto.ReferenceDataId;

/**
 * @author suneet
 *
 */
public interface ReferenceDataService {

	/**
	 * Finds a list of reference data for a particular codeGroup.. 
	 * @param codeGroup - A service may have several groups of code. Code Group breaks that down
	 * @param languageCd - LanguageCode
	 * @return List of ReferenceData objects.
	 * @throws FinderException
	 */
	public abstract List<ReferenceData> getRefByGroup(String codeGroup,
			String languageCd);

	public abstract void addRefData(ReferenceData val);

	public abstract void saveRefData(ReferenceData val);

	public abstract void removeRefData(ReferenceData val);

	public abstract void getRefDataById(ReferenceDataId val);

}