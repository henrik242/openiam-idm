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
package org.openiam.idm.srvc.qry.service;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openiam.idm.srvc.qry.dto.ParameterTypeEnum;
import org.openiam.idm.srvc.qry.dto.Query;
import org.openiam.idm.srvc.qry.dto.QueryParam;
import org.openiam.idm.srvc.user.dto.User;

/**
 * @author suneet
 *
 */
public class QueryDataServiceImpl implements QueryDataService {

	private static final Log log = LogFactory.getLog(QueryDataServiceImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory session) {
		   this.sessionFactory = session;
	}

	   
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.openiam.idm.srvc.qry.service.QueryDataService#executeQuery(org.openiam.idm.srvc.qry.dto.Query)
	 */
	public List<Object> executeQuery(Query qry) {
		
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery sqlQry = session.createSQLQuery(qry.getSql());
		sqlQry.addEntity(qry.getObjectClass());

		// add the parameters for the query if they exists
		List<QueryParam> paramList = qry.getParamList();
		if (paramList != null && paramList.size() > 0) {
			for (QueryParam param : paramList) {
				if (param.getParamType() == ParameterTypeEnum.STRING ) {
					sqlQry.setString(param.getParamName(), (String)param.getParamValue());
				}
			}
		}
		
		
		if (qry.getMaxRowCount() != 0 ) {
			sqlQry.setFetchSize(qry.getMaxRowCount());
			sqlQry.setMaxResults(qry.getMaxRowCount());			
		} 
		
		return sqlQry.list();
		
	}

}
