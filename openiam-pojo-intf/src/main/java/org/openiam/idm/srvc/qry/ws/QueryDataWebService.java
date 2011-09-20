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
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.qry.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


import org.openiam.idm.srvc.qry.dto.Query;



/**
 * @author Suneet Shah
 *
 */
@WebService(targetNamespace = "urn:idm.openiam.org/srvc/qry/service", name = "QueryDataWebService")
public interface QueryDataWebService {

	/**
	 * Executes a sql query
	 * @param pswd
	 * @return
	 */
	@WebMethod
	QueryListResponse executeQuery(
			@WebParam(name = "qry", targetNamespace = "")
			Query qry);
	

	

}
