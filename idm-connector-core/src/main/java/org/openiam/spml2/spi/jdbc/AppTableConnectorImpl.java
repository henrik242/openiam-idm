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
import org.openiam.idm.srvc.audit.dto.IdmAuditLog;
import org.openiam.idm.srvc.audit.service.IdmAuditLogDataService;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemObjectMatchDAO;
import org.openiam.idm.srvc.policy.service.PolicyDAO;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.idm.srvc.secdomain.service.SecurityDomainDataService;
import org.openiam.idm.srvc.user.service.UserDataService;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.base.AbstractSpml2Complete;
import org.openiam.provision.type.ExtensibleUser;
import org.openiam.spml2.interf.ConnectorService;
import org.openiam.spml2.msg.*;
import org.openiam.spml2.msg.password.*;
import org.openiam.spml2.msg.suspend.ResumeRequestType;
import org.openiam.spml2.msg.suspend.SuspendRequestType;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Enables provisionign and deprovisioning to custom tables in a database.  This is usually the case when mapping to
 * a custom database structure for an application
 *
 * @author suneet
 */

@WebService(endpointInterface = "org.openiam.spml2.interf.ConnectorService",
        targetNamespace = "http://www.openiam.org/service/connector",
        portName = "ApplicationTablesConnectorPort",
        serviceName = "ApplicationTablesConnector")
public class AppTableConnectorImpl extends AbstractSpml2Complete implements ConnectorService {

    private static final Log log = LogFactory.getLog(AppTableConnectorImpl.class);

    protected JDBCConnectionMgr connectionMgr;

    protected AppTableAddCommand addCommand;
    protected AppTableDeleteCommand delCommand;
    protected AppTableSetPasswordCommand setPasswordCommand;
    protected AppTableLookupCommand lookupCommand;

     protected AppTableSuspendCommand suspendCommand;
     protected AppTableResumeCommand resumeCommand;
    protected AppTableModifyCommand modifyCommand;



    public ResponseType testConnection(ManagedSys managedSys) {
        ResponseType response = new ResponseType();
        response.setStatus(StatusCodeType.SUCCESS);

        Connection con = null;

        try {

            con = connectionMgr.connect(managedSys);

        } catch (SQLException se) {
            log.error(se.toString());
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.SQL_ERROR);
            response.addErrorMessage(se.toString());
            return response;

        } catch (ClassNotFoundException cnfe) {
            log.error(cnfe.toString());
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage(cnfe.toString());
            return response;
        } finally {
            /* close the connection to the directory */
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception n) {
                log.error(n);
            }

        }

        return response;
    }

    public AddResponseType add(AddRequestType reqType) {

        return addCommand.add(reqType);

    }


    public ModifyResponseType modify(ModifyRequestType reqType) {
        return modifyCommand.modify(reqType);
    }

    public ResponseType delete(DeleteRequestType reqType) {

        return delCommand.delete(reqType);
    }

    public LookupResponseType lookup( LookupRequestType reqType) {
        return lookupCommand.lookup(reqType);
    }

    public ResponseType setPassword( SetPasswordRequestType request) {
        return setPasswordCommand.setPassword(request);
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

    public ResponseType suspend( SuspendRequestType request) {
        return suspendCommand.suspend(request);
    }

    public ResponseType resume( ResumeRequestType request) {
        return resumeCommand.resume(request);
    }

    public void log(String objectTypeId, String actionId, String actionStatus, String reason,
                    String domainId, String userId, String principal,
                    String linkedLogId, String clientId) {
        IdmAuditLog log = new IdmAuditLog(objectTypeId, actionId, actionStatus,
                reason, domainId, userId, principal,
                linkedLogId, clientId);
    }



    public JDBCConnectionMgr getConnectionMgr() {
        return connectionMgr;
    }

    public void setConnectionMgr(JDBCConnectionMgr connectionMgr) {
        this.connectionMgr = connectionMgr;
    }

    public AppTableAddCommand getAddCommand() {
        return addCommand;
    }

    public void setAddCommand(AppTableAddCommand addCommand) {
        this.addCommand = addCommand;
    }

    public AppTableDeleteCommand getDelCommand() {
        return delCommand;
    }

    public void setDelCommand(AppTableDeleteCommand delCommand) {
        this.delCommand = delCommand;
    }

    public AppTableSetPasswordCommand getSetPasswordCommand() {
        return setPasswordCommand;
    }

    public void setSetPasswordCommand(AppTableSetPasswordCommand setPasswordCommand) {
        this.setPasswordCommand = setPasswordCommand;
    }

    public AppTableLookupCommand getLookupCommand() {
        return lookupCommand;
    }

    public void setLookupCommand(AppTableLookupCommand lookupCommand) {
        this.lookupCommand = lookupCommand;
    }

    public AppTableSuspendCommand getSuspendCommand() {
        return suspendCommand;
    }

    public void setSuspendCommand(AppTableSuspendCommand suspendCommand) {
        this.suspendCommand = suspendCommand;
    }

    public AppTableResumeCommand getResumeCommand() {
        return resumeCommand;
    }

    public void setResumeCommand(AppTableResumeCommand resumeCommand) {
        this.resumeCommand = resumeCommand;
    }

    public AppTableModifyCommand getModifyCommand() {
        return modifyCommand;
    }

    public void setModifyCommand(AppTableModifyCommand modifyCommand) {
        this.modifyCommand = modifyCommand;
    }
}
