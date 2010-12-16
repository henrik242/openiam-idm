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
package org.openiam.idm.srvc.qry.ws;

import java.util.List;

import javax.jws.WebService;

import org.openiam.base.ws.ResponseStatus;
import org.openiam.idm.srvc.qry.dto.Query;
import org.openiam.idm.srvc.qry.service.QueryDataService;



/**
 * @author suneet
 *
 */
@WebService(endpointInterface = "org.openiam.idm.srvc.qry.ws.QueryDataWebService", 
		targetNamespace = "urn:idm.openiam.org/srvc/qry/service", 
		portName = "QueryDataWebServicePort",
		serviceName = "QueryDataWebService")
public class QueryDataWebServiceImpl implements QueryDataWebService {

	protected QueryDataService queryService;
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.qry.ws.QueryDataWebService#executeQuery(org.openiam.idm.srvc.qry.dto.Query)
	 */
	public QueryListResponse executeQuery(Query qry) {
		QueryListResponse resp = new QueryListResponse(ResponseStatus.SUCCESS);
		List<Object> resultList = queryService.executeQuery(qry);
		if (resultList == null ) {
			resp.setStatus(ResponseStatus.FAILURE);
		}else {
			resp.setResultList(resultList);
		}
		return resp;
	}
	public QueryDataService getQueryService() {
		return queryService;
	}
	public void setQueryService(QueryDataService queryService) {
		this.queryService = queryService;
	}

}
