package org.openiam.spml2.spi.jdbc;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.msg.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: suneetshah
 * Date: 7/30/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppTableDeleteCommand extends AppTableAbstractCommand {

    public ResponseType delete(DeleteRequestType reqType) {
        String tableName;
        String principalName = null;
        String principalFieldDataType = null;
        String principalFieldName = null;
        ExtensibleObject extObj = null;


        ResponseType response = new ResponseType();
        response.setStatus(StatusCodeType.SUCCESS);


        principalName = reqType.getPsoID().getID();

        PSOIdentifierType psoID = reqType.getPsoID();
        /* targetID -  */
        String targetID = psoID.getTargetID();

        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        if (managedSys.getResourceId() == null || managedSys.getResourceId().length() == 0) {
            return populateResponse(response, StatusCodeType.FAILURE,
                    ErrorCode.INVALID_CONFIGURATION,
                    "ResourceID is not defined in the ManagedSys Object");

        }

        Resource res = resourceDataService.getResource(managedSys.getResourceId());
        tableName = getTableName(res);
        if (tableName == null || tableName.length() == 0) {
            return populateResp(response, StatusCodeType.FAILURE,
                    ErrorCode.INVALID_CONFIGURATION,
                    "TABLE NAME is not defined.");
        }


        Connection con = null;
        try {
            con = connectionMgr.connect(managedSys);

            PreparedStatement statement = createDeleteStatement(con, res, tableName, principalName);

            statement.executeUpdate();

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
        } catch (ParseException pe) {
            log.error(pe.toString());
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage(pe.toString());
            return response;

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException s) {
                    log.error(s.toString());
                    response.setStatus(StatusCodeType.FAILURE);
                    response.setError(ErrorCode.SQL_ERROR);
                    response.addErrorMessage(s.toString());
                    return response;
                }
            }
        }


        return response;


    }

    protected ResponseType populateResp(ResponseType response, StatusCodeType status,
                                        ErrorCode err, String msg) {

        response.setStatus(status);
        response.setError(err);
        response.addErrorMessage(msg);
        return response;
    }


}
