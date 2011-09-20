package org.openiam.spml2.spi.jdbc;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.msg.*;

import java.sql.*;
import java.text.ParseException;

/**
 * Implements lookup furnctionality for the .
 * User: suneetshah
 * Date: 7/30/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppTableLookupCommand extends AppTableAbstractCommand {

    public LookupResponseType lookup(LookupRequestType reqType) {
        String tableName;
        String principalName = null;
        ExtensibleObject extObj = null;

        boolean found = false;


        log.debug("AppTable lookup operation called.");

        LookupResponseType response = new LookupResponseType();
        response.setStatus(StatusCodeType.SUCCESS);

        principalName = reqType.getPsoID().getID();

        PSOIdentifierType psoID = reqType.getPsoID();
        /* targetID -  */
        String targetID = psoID.getTargetID();

        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        if (managedSys.getResourceId() == null || managedSys.getResourceId().length() == 0) {
            return populateResp(response, StatusCodeType.FAILURE,
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

        ExtensibleObject resultObject = new ExtensibleObject();
        resultObject.setObjectId(principalName);


        try {
            con = connectionMgr.connect(managedSys);

            PreparedStatement statement = createSelectStatement(con, res, tableName, principalName);
            if (statement == null) {
                return populateResp(response, StatusCodeType.FAILURE,
                        ErrorCode.INVALID_CONFIGURATION,
                        "Unable to generate SQL based on configuration");
            }
            log.debug("Executing lookup query");

            ResultSet rs = statement.executeQuery();
            ResultSetMetaData rsMetadata = rs.getMetaData();
            int columnCount = rsMetadata.getColumnCount();

            log.debug("Query contains column count =" + columnCount);

            if (rs.next()) {
                found = true;


                for (int colIndx = 1; colIndx <= columnCount; colIndx++) {

                    ExtensibleAttribute extAttr = new ExtensibleAttribute();

                    extAttr.setName(rsMetadata.getColumnName(colIndx));

                    setColumnValue(extAttr, colIndx, rsMetadata, rs);
                    resultObject.getAttributes().add(extAttr);


                }

                response.getAny().add(resultObject);
            } else {

                log.debug("Principal not found");

                response.setStatus(StatusCodeType.FAILURE);
                return response;

            }


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


        response.setStatus(StatusCodeType.SUCCESS);
        return response;


    }

    private void setColumnValue(ExtensibleAttribute extAttr, int colIndx, ResultSetMetaData rsMetadata, ResultSet rs)
            throws SQLException {

        int fieldType = rsMetadata.getColumnType(colIndx);

        log.debug("column type = " + fieldType);

        if (fieldType == Types.INTEGER) {
            log.debug("type = Integer");
            extAttr.setDataType("INTEGER");
            extAttr.setValue(String.valueOf(rs.getInt(colIndx)));


        }

        if (fieldType == Types.FLOAT || fieldType == Types.NUMERIC) {
            log.debug("type = Float");

            extAttr.setDataType("FLOAT");
            extAttr.setValue(String.valueOf(rs.getFloat(colIndx)));

        }

        if (fieldType == Types.DATE) {
            log.debug("type = Date");

            extAttr.setDataType("DATE");
            if (rs.getDate(colIndx) != null) {
                extAttr.setValue(String.valueOf(rs.getDate(colIndx).getTime()));
            }

        }
        if (fieldType == Types.TIMESTAMP) {
            log.debug("type = Timestamp");

            extAttr.setDataType("TIMESTAMP");
            extAttr.setValue(String.valueOf(rs.getTimestamp(colIndx).getTime()));

        }
        if (fieldType == Types.VARCHAR || fieldType == Types.CHAR) {
            log.debug("type = Varchar");

            extAttr.setDataType("STRING");
            if (rs.getString(colIndx) != null) {
                extAttr.setValue(rs.getString(colIndx));
            }

        }


    }

    protected LookupResponseType populateResp(LookupResponseType response, StatusCodeType status,
                                              ErrorCode err, String msg) {

        response.setStatus(status);
        response.setError(err);
        response.addErrorMessage(msg);
        return response;
    }


}


