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
package org.openiam.webadmin.conn.mngsys;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openiam.idm.srvc.mngsys.dto.ManagedSystemObjectMatch;

/**
 * Command object for ManagedSystemConnection
 * @author suneet
 *
 */
public class ManagedSysConnectionCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8987849239297542982L;

	private String managedSysId;
	private String name;
	private String description;
	private String status;
	private String connectorId;
	private String domainId;
	private String hostUrl;
	private Integer port;
	private String commProtocol;
	private String userId;
	private String pswd;
	private Date startDate;
	private Date endDate;
	protected String resourceType;
	
	protected String objectSearchId;
	protected String baseDn;
	protected String searchBaseDn;
	protected String searchFilter;
	protected String keyField;
	
	public ManagedSysConnectionCommand() {
		
	}
	
	
	public ManagedSysConnectionCommand(String commProtocol, String connectorId,
			String description, String domainId, Date endDate, String hostUrl,
			String managedSysId, String name, Integer port, String pswd,
			Date startDate, String status, String userId) {
		super();
		this.commProtocol = commProtocol;
		this.connectorId = connectorId;
		this.description = description;
		this.domainId = domainId;
		this.endDate = endDate;
		this.hostUrl = hostUrl;
		this.managedSysId = managedSysId;
		this.name = name;
		this.port = port;
		this.pswd = pswd;
		this.startDate = startDate;
		this.status = status;
		this.userId = userId;
	}
	public ManagedSystemObjectMatch getMatchObj() {
		ManagedSystemObjectMatch matchObj = new ManagedSystemObjectMatch();
		matchObj.setObjectSearchId(objectSearchId);
		matchObj.setBaseDn(baseDn);
		matchObj.setSearchBaseDn(searchBaseDn);
		matchObj.setKeyField(keyField);
		matchObj.setObjectType("USER");
		matchObj.setSearchFilter(searchFilter);
		
		return matchObj;
	}
	
	public String getManagedSysId() {
		return managedSysId;
	}
	public void setManagedSysId(String managedSysId) {
		this.managedSysId = managedSysId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConnectorId() {
		return connectorId;
	}
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domId) {
		if (this.domainId == null || this.domainId.length() == 0) {
			this.domainId = domId;
		}
	}
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getCommProtocol() {
		return commProtocol;
	}
	public void setCommProtocol(String commProtocol) {
		this.commProtocol = commProtocol;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getStartDate() {
		return formatDate( startDate);
	}
	public Date getStartDt() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return formatDate(endDate);
	}
	public Date getEndDt() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String formatDate(Date dt) {
		if (dt == null) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		return fmt.format(dt);
	}


	public String getResourceType() {
		return resourceType;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}


	public String getBaseDn() {
		return baseDn;
	}


	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}


	public String getSearchFilter() {
		return searchFilter;
	}


	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}


	public String getKeyField() {
		return keyField;
	}


	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}


	public String getObjectSearchId() {
		return objectSearchId;
	}


	public void setObjectSearchId(String objectSearchId) {
		this.objectSearchId = objectSearchId;
	}


	public String getSearchBaseDn() {
		return searchBaseDn;
	}


	public void setSearchBaseDn(String searchBaseDn) {
		this.searchBaseDn = searchBaseDn;
	}
	
}
