package org.openiam.spml2.spi.jdbc;

import org.openiam.exception.EncryptionException;
import org.openiam.idm.srvc.auth.dto.Login;
import org.openiam.idm.srvc.auth.login.LoginDataService;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.spml2.msg.ErrorCode;
import org.openiam.spml2.msg.PSOIdentifierType;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;
import org.openiam.spml2.msg.suspend.ResumeRequestType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Implements Resume capability for the AppTableConnector
 * User: suneetshah
 * Date: 7/30/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppTableResumeCommand extends AppTableAbstractCommand {
    protected LoginDataService loginManager;

    public ResponseType resume(ResumeRequestType request) {
        String tableName;
        String principalName = null;
        Connection con = null;

        ResponseType response = new ResponseType();
        response.setStatus(StatusCodeType.SUCCESS);

        principalName = request.getPsoID().getID();

        PSOIdentifierType psoID = request.getPsoID();
        /* targetID -  */
        String targetID = psoID.getTargetID();

        List<Login> loginList = loginManager.getLoginByManagedSys(principalName, targetID);
        if (loginList == null || loginList.isEmpty()) {
            return populateResponse(response, StatusCodeType.FAILURE,
                    ErrorCode.INVALID_IDENTIFIER,
                    "Principal not found");

        }

        try {

            Login login = loginList.get(0);
            String encPassword = login.getPassword();
            String decPassword = loginManager.decryptPassword(encPassword);


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


            con = connectionMgr.connect(managedSys);

            PreparedStatement statement = createSetPasswordStatement(con, res, tableName, principalName, decPassword);

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

        } catch (EncryptionException ee) {
            log.error(ee.toString());
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.OTHER_ERROR);
            response.addErrorMessage(ee.toString());
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


    public LoginDataService getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginDataService loginManager) {
        this.loginManager = loginManager;
    }
}
