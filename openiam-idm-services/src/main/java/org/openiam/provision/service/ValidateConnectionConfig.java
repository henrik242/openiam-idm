package org.openiam.provision.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleContext;
import org.openiam.base.ws.Response;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.mngsys.dto.ProvisionConnector;
import org.openiam.idm.srvc.mngsys.service.ConnectorDataService;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;

/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 7/23/11
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidateConnectionConfig {

    protected static final Log log = LogFactory.getLog(ValidateConnectionConfig.class);

    protected ConnectorAdapter connectorAdapter;
    protected RemoteConnectorAdapter remoteConnectorAdapter;
    protected ManagedSystemDataService managedSysService;
    protected ConnectorDataService connectorService;


    Response testConnection(String managedSysId, MuleContext muleContext) {
        Response resp = new Response(org.openiam.base.ws.ResponseStatus.SUCCESS);

        ManagedSys mSys = managedSysService.getManagedSys(managedSysId);
        ProvisionConnector connector = connectorService.getConnector(mSys.getConnectorId());

        if (connector.getConnectorInterface() != null &&
                connector.getConnectorInterface().equalsIgnoreCase("REMOTE")) {

            log.debug("Testing connection with remoteConnector");

            org.openiam.connector.type.ResponseType remoteResp = remoteTestConnection(mSys, connector, muleContext);
            if (remoteResp.getStatus() == StatusCodeType.FAILURE) {

                log.debug("Test connection failed.");

                resp.setStatus(org.openiam.base.ws.ResponseStatus.FAILURE);
                resp.setErrorText(remoteResp.getErrorMsgAsStr());

            }
        } else {

            log.debug("Testing connection with localConnector");

            ResponseType localResp = localTestConnection(mSys, muleContext);
            if (localResp.getStatus() == StatusCodeType.FAILURE) {

                log.debug("Test connection failed.");

                resp.setStatus(org.openiam.base.ws.ResponseStatus.FAILURE);
                resp.setErrorText(localResp.getErrorMsgAsStr());
            }

        }

        return resp;

    }

    private ResponseType localTestConnection(ManagedSys mSys, MuleContext muleContext) {


        return connectorAdapter.testConnection(mSys, muleContext);

    }

    private org.openiam.connector.type.ResponseType remoteTestConnection(ManagedSys mSys, ProvisionConnector connector,
                                                                         MuleContext muleContext) {


        return remoteConnectorAdapter.testConnection(mSys, connector, muleContext);

    }


    public ConnectorAdapter getConnectorAdapter() {
        return connectorAdapter;
    }

    public void setConnectorAdapter(ConnectorAdapter connectorAdapter) {
        this.connectorAdapter = connectorAdapter;
    }

    public RemoteConnectorAdapter getRemoteConnectorAdapter() {
        return remoteConnectorAdapter;
    }

    public void setRemoteConnectorAdapter(RemoteConnectorAdapter remoteConnectorAdapter) {
        this.remoteConnectorAdapter = remoteConnectorAdapter;
    }

    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public ConnectorDataService getConnectorService() {
        return connectorService;
    }

    public void setConnectorService(ConnectorDataService connectorService) {
        this.connectorService = connectorService;
    }
}


