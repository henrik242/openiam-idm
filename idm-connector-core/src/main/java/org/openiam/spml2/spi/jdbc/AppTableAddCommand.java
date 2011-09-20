package org.openiam.spml2.spi.jdbc;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleObject;
import org.openiam.spml2.msg.AddRequestType;
import org.openiam.spml2.msg.AddResponseType;
import org.openiam.spml2.msg.ErrorCode;
import org.openiam.spml2.msg.StatusCodeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * AppTableAddCommand implements the add operation for the AppTableConnector
 * User: suneetshah
 * Date: 7/30/11
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppTableAddCommand extends AppTableAbstractCommand {

    public AddResponseType add(AddRequestType reqType) {

        String tableName;
        String principalName = null;

        AddResponseType response = new AddResponseType();
        response.setStatus(StatusCodeType.SUCCESS);

        principalName = reqType.getPsoID().getID();


        String targetID = reqType.getTargetID();

        ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        if (managedSys.getResourceId() == null || managedSys.getResourceId().length() == 0) {
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage("ResourceID is not defined in the ManagedSys Object");
            return response;
        }

        Resource res = resourceDataService.getResource(managedSys.getResourceId());
        ResourceProp prop = res.getResourceProperty("TABLE_NAME");

        if (prop.getPropValue() == null || prop.getPropValue().length() == 0) {
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage("TABLE NAME is not defined.");
            return response;
        }
        tableName = prop.getPropValue();


        List<ExtensibleObject> objectList = reqType.getData().getAny();

        log.debug("ExtensibleObject in Add Request=" + objectList);

        Connection con = null;
        try {
            con = connectionMgr.connect(managedSys);

            // build sql
            StringBuffer insertBuf = new StringBuffer("INSERT INTO " + tableName);
            StringBuffer columnBuf = new StringBuffer("(");
            StringBuffer valueBuf = new StringBuffer(" values (");

            for (ExtensibleObject obj : objectList) {
                List<ExtensibleAttribute> attrList = obj.getAttributes();

                log.debug("Number of attributes to persist in ADD = " + attrList.size());

                int ctr = 0;
                for (ExtensibleAttribute att : attrList) {
                    if (ctr != 0) {
                        columnBuf.append(",");
                        valueBuf.append(",");
                    }
                    ctr++;
                    columnBuf.append(att.getName());
                    valueBuf.append("?");
                }
                // add the primary key
                log.debug("Principal column name=" + obj.getPrincipalFieldName());
                if (obj.getPrincipalFieldName() != null) {
                    if (ctr != 0) {
                        columnBuf.append(",");
                        valueBuf.append(",");
                    }
                    columnBuf.append(obj.getPrincipalFieldName());
                    valueBuf.append("?");
                }

                columnBuf.append(")");
                valueBuf.append(")");
                //}
                insertBuf.append(columnBuf);
                insertBuf.append(valueBuf);

                log.debug("ADD SQL=" + insertBuf.toString());

                PreparedStatement statement = con.prepareStatement(insertBuf.toString());
                // set the parameters

                for (ExtensibleObject extObj : objectList) {
                    List<ExtensibleAttribute> extAttrList = extObj.getAttributes();
                    ctr = 1;
                    for (ExtensibleAttribute att : extAttrList) {

                        setStatement(statement, ctr, att);
                        ctr++;
                        log.debug("Binding parameter: " + att.getName() + " -> " + att.getValue());

                    }
                }

                if (obj.getPrincipalFieldName() != null) {

                    setStatement(statement, ctr, obj.getPrincipalFieldDataType(), principalName);
                }

                statement.executeUpdate();
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


        return response;
    }


}
