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
	private String connectId;
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
	
	protected Integer primaryRepository;
	protected String secondaryRepositoryId;
	protected Integer updateSecondary;

    private  String driverUrl;
    private  String connectionString;
    private  String addHandler;
    private  String modifyHandler;
    private  String deleteHandler;
    private  String passwordHandler;
    private  String suspendHandler;
    private  String handler1;
    private  String handler2;
    private  String handler3;
    private  String handler4;
    private  String handler5;
	
	public ManagedSysConnectionCommand() {
		
	}
	
	
	public ManagedSysConnectionCommand(String commProtocol, String connectorId,
			String description, String domainId, Date endDate, String hostUrl,
			String managedSysId, String name, Integer port, String pswd,
			Date startDate, String status, String userId, Integer primaryRepository, 
			String secondaryRepositoryId, 
			Integer updateSecondary,
            String driverUrl,
             String connectionString,
             String addHandler,
             String modifyHandler,
             String deleteHandler,
             String passwordHandler,
             String suspendHandler,
             String handler1,
             String handler2,
             String handler3,
             String handler4,
             String handler5) {
		super();
		this.commProtocol = commProtocol;
		this.connectId = connectorId;
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
		this.primaryRepository = primaryRepository;
		this.secondaryRepositoryId = secondaryRepositoryId;
		this.updateSecondary = updateSecondary;

        this.driverUrl = driverUrl;
        this.connectionString = connectionString;
        this.addHandler = addHandler;
        this.modifyHandler = modifyHandler;
        this.deleteHandler = deleteHandler;
        this.passwordHandler = passwordHandler;
        this.suspendHandler = suspendHandler;
        this.handler1 = handler1;
        this.handler2 = handler2;
        this.handler3 = handler3;
        this.handler4 = handler4;
        this.handler5 = handler5;
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


	public Integer getPrimaryRepository() {
		return primaryRepository;
	}


	public void setPrimaryRepository(Integer primaryRepository) {
		this.primaryRepository = primaryRepository;
	}


	public String getSecondaryRepositoryId() {
		return secondaryRepositoryId;
	}


	public void setSecondaryRepositoryId(String secondaryRepositoryId) {
		this.secondaryRepositoryId = secondaryRepositoryId;
	}


	public Integer getUpdateSecondary() {
		return updateSecondary;
	}


	public void setUpdateSecondary(Integer updateSecondary) {
		this.updateSecondary = updateSecondary;
	}


	public String getConnectId() {
		return connectId;
	}


	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}


    public String getDriverUrl() {
        return driverUrl;
    }

    public void setDriverUrl(String driverUrl) {
        this.driverUrl = driverUrl;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getAddHandler() {
        return addHandler;
    }

    public void setAddHandler(String addHandler) {
        this.addHandler = addHandler;
    }

    public String getModifyHandler() {
        return modifyHandler;
    }

    public void setModifyHandler(String modifyHandler) {
        this.modifyHandler = modifyHandler;
    }

    public String getDeleteHandler() {
        return deleteHandler;
    }

    public void setDeleteHandler(String deleteHandler) {
        this.deleteHandler = deleteHandler;
    }

    public String getPasswordHandler() {
        return passwordHandler;
    }

    public void setPasswordHandler(String passwordHandler) {
        this.passwordHandler = passwordHandler;
    }

    public String getSuspendHandler() {
        return suspendHandler;
    }

    public void setSuspendHandler(String suspendHandler) {
        this.suspendHandler = suspendHandler;
    }

    public String getHandler1() {
        return handler1;
    }

    public void setHandler1(String handler1) {
        this.handler1 = handler1;
    }

    public String getHandler2() {
        return handler2;
    }

    public void setHandler2(String handler2) {
        this.handler2 = handler2;
    }

    public String getHandler3() {
        return handler3;
    }

    public void setHandler3(String handler3) {
        this.handler3 = handler3;
    }

    public String getHandler4() {
        return handler4;
    }

    public void setHandler4(String handler4) {
        this.handler4 = handler4;
    }

    public String getHandler5() {
        return handler5;
    }

    public void setHandler5(String handler5) {
        this.handler5 = handler5;
    }
}
