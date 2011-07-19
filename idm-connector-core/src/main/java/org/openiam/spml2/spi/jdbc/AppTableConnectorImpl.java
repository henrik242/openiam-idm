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
package org.openiam.spml2.spi.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;

import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.res.dto.Resource;

import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.base.AbstractSpml2Complete;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.*;
import org.openiam.spml2.msg.password.*;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;


import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.*;
import java.sql.*;

/**
 * Enables provisionign and deprovisioning to custom tables in a database.  This is usually the case when mapping to
 * a custom database structure for an application
 * @author suneet
 *
 */

@WebService(endpointInterface="org.openiam.spml2.interf.ConnectorService",
		targetNamespace="http://www.openiam.org/service/connector",
		portName = "ApplicationTablesConnectorPort",
		serviceName="ApplicationTablesConnector")
public class AppTableConnectorImpl extends AbstractSpml2Complete implements ConnectorService  {

	private static final Log log = LogFactory.getLog(AppTableConnectorImpl.class);
	protected ManagedSystemDataService managedSysService;
	protected ManagedSystemObjectMatchDAO managedSysObjectMatchDao;
	protected ResourceDataService resourceDataService;
	protected IdmAuditLogDataService auditDataService;
	protected LoginDataService loginManager;
	protected PolicyDAO policyDao;
	protected SecurityDomainDataService secDomainService; 
	protected UserDataService userManager;
    protected JDBCConnectionMgr connectionMgr;



    public boolean testConnection( String targetID) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public AddResponseType add(AddRequestType reqType) {

        String tableName;
        String principalName = null;
        String password = null;
        String columns = null;
        String values = null;
        String princpalName = null;

        AddResponseType response = new AddResponseType();
        response.setStatus(StatusCodeType.SUCCESS);


        String requestID = reqType.getRequestID();
        principalName = reqType.getPsoID().getID();
        String targetID = reqType.getTargetID();

        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        if (managedSys.getResourceId() == null || managedSys.getResourceId().length() == 0) {
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage("ResourceID is not defined in the ManagedSys Object");
        }

        Resource res = resourceDataService.getResource(managedSys.getResourceId());
        ResourceProp prop =  res.getResourceProperty("TABLE_NAME");

        if (prop.getPropValue() == null || prop.getPropValue().length() == 0) {
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage("TABLE NAME is not defined.");
        }
        tableName = prop.getPropValue();


        List<ExtensibleObject> requestAttributeList =  reqType.getData().getAny();

        Connection con = null;
        try {
            con =  connectionMgr.connect(managedSys);

            // build sql
            StringBuffer insertBuf = new StringBuffer("INSERT INTO " + tableName );
            StringBuffer columnBuf = new StringBuffer("(");
            StringBuffer valueBuf = new StringBuffer(" values (");

            for (ExtensibleObject obj : requestAttributeList) {
                List<ExtensibleAttribute> attrList = obj.getAttributes();
                int ctr = 0;
                for (ExtensibleAttribute att : attrList) {
                    if (ctr != 0) {
                        columnBuf.append(",");
                        valueBuf.append(",");
                    }
                    columnBuf.append(att.getName());
                    valueBuf.append("?");
                }
                columnBuf.append(")");
                valueBuf.append(")");
            }
            insertBuf.append(columnBuf);
            insertBuf.append(valueBuf);

            PreparedStatement statement = con.prepareStatement(insertBuf.toString());
            // set the parameters

            for (ExtensibleObject obj : requestAttributeList) {
                List<ExtensibleAttribute> attrList = obj.getAttributes();
                int ctr = 0;
                for (ExtensibleAttribute att : attrList) {


                }
            }


            statement.executeUpdate();


        }catch(SQLException se) {
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.SQL_ERROR);
            response.addErrorMessage(se.toString());
        }catch (ClassNotFoundException cnfe) {
             response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage(cnfe.toString());
        }finally {
            if (con != null) {

            }
        }



        return response;
    }






    public ModifyResponseType modify( ModifyRequestType reqType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResponseType delete(@WebParam(name = "reqType", targetNamespace = "") DeleteRequestType reqType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public LookupResponseType lookup(@WebParam(name = "reqType", targetNamespace = "") LookupRequestType reqType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResponseType setPassword(@WebParam(name = "request", targetNamespace = "") SetPasswordRequestType request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResponseType expirePassword(@WebParam(name = "request", targetNamespace = "") ExpirePasswordRequestType request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResetPasswordResponseType resetPassword(@WebParam(name = "request", targetNamespace = "") ResetPasswordRequestType request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ValidatePasswordResponseType validatePassword(@WebParam(name = "request", targetNamespace = "") ValidatePasswordRequestType request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResponseType suspend(@WebParam(name = "request", targetNamespace = "") SuspendRequestType request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResponseType resume(@WebParam(name = "request", targetNamespace = "") ResumeRequestType request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public ManagedSystemObjectMatchDAO getManagedSysObjectMatchDao() {
        return managedSysObjectMatchDao;
    }

    public void setManagedSysObjectMatchDao(ManagedSystemObjectMatchDAO managedSysObjectMatchDao) {
        this.managedSysObjectMatchDao = managedSysObjectMatchDao;
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public IdmAuditLogDataService getAuditDataService() {
        return auditDataService;
    }

    public void setAuditDataService(IdmAuditLogDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    public LoginDataService getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginDataService loginManager) {
        this.loginManager = loginManager;
    }

    public PolicyDAO getPolicyDao() {
        return policyDao;
    }

    public void setPolicyDao(PolicyDAO policyDao) {
        this.policyDao = policyDao;
    }

    public SecurityDomainDataService getSecDomainService() {
        return secDomainService;
    }

    public void setSecDomainService(SecurityDomainDataService secDomainService) {
        this.secDomainService = secDomainService;
    }

    public UserDataService getUserManager() {
        return userManager;
    }

    public void setUserManager(UserDataService userManager) {
        this.userManager = userManager;
    }

    public JDBCConnectionMgr getConnectionMgr() {
        return connectionMgr;
    }

    public void setConnectionMgr(JDBCConnectionMgr connectionMgr) {
        this.connectionMgr = connectionMgr;
    }
}
