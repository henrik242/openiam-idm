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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.openiam.idm.srvc.cd.dto.ReferenceData;
import org.openiam.idm.srvc.cd.dto.ReferenceDataId;

/**
 * @author suneet
 *
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/ref/service",	name = "ReferenceDataWebService")	
public interface ReferenceDataService {

	/**
	 * Finds a list of reference data for a particular codeGroup.. 
	 * @param codeGroup - A service may have several groups of code. Code Group breaks that down
	 * @param languageCd - LanguageCode
	 * @return List of ReferenceData objects.
	 * @throws FinderException
	 */
	@WebMethod
	public abstract List<ReferenceData> getRefByGroup(
			@WebParam(name = "codeGroup", targetNamespace = "")
			String codeGroup,
			@WebParam(name = "languageCd", targetNamespace = "")
			String languageCd);

	@WebMethod
	public abstract void addRefData(
			@WebParam(name = "val", targetNamespace = "")
			ReferenceData val);

	@WebMethod
	public abstract void saveRefData(
			@WebParam(name = "val", targetNamespace = "")
			ReferenceData val);

	@WebMethod
	public abstract void removeRefData(
			@WebParam(name = "val", targetNamespace = "")
			ReferenceData val);

	@WebMethod
	public abstract void getRefDataById(
			@WebParam(name = "val", targetNamespace = "")
			ReferenceDataId val);

}