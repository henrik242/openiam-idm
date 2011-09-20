package org.openiam.spml2.spi.jdbc;

import org.openiam.idm.srvc.mngsys.dto.ManagedSys;
import org.openiam.idm.srvc.pswd.service.PasswordGenerator;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.provision.type.*;
import org.openiam.spml2.msg.*;
//import org.openiam.spml2.msg.ExtensibleAttribute;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.spml2.msg.ModificationType;


import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Implements modify capability for the AppTableConnector
 * User: suneetshah
 * Date: 7/30/11
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppTableModifyCommand extends AppTableAbstractCommand {

     public ModifyResponseType modify(ModifyRequestType reqType) {
          String tableName;
        String principalName = null;
           Connection con = null;
                     String principalFieldName = null;
            String principalFieldDataType = null;

        ModifyResponseType response = new ModifyResponseType();
        response.setStatus(StatusCodeType.SUCCESS);

        principalName = reqType.getPsoID().getID();


        String requestID = reqType.getRequestID();
          /* PSO - Provisioning Service Object -
             *     -  ID must uniquely specify an object on the target or in the target's namespace
             *     -  Try to make the PSO ID immutable so that there is consistency across changes. */
        PSOIdentifierType psoID = reqType.getPsoID();
        principalName  = psoID.getID();

          /* targetID -  */
        String targetID = psoID.getTargetID();
          /* ContainerID - May specify the container in which this object should be created
             *      ie. ou=Development, org=Example */
         PSOIdentifierType containerID = psoID.getContainerID();


         /* A) Use the targetID to look up the connection information under managed systems */
          ManagedSys managedSys = managedSysService.getManagedSys(targetID);

        if (managedSys.getResourceId() == null || managedSys.getResourceId().length() == 0) {
            response.setStatus(StatusCodeType.FAILURE);
            response.setError(ErrorCode.INVALID_CONFIGURATION);
            response.addErrorMessage("ResourceID is not defined in the ManagedSys Object");
            return response;
        }

        Resource res = resourceDataService.getResource(managedSys.getResourceId());
        tableName = getTableName(res);
        if (tableName == null || tableName.length() == 0) {
            return populateResp(response, StatusCodeType.FAILURE,
                    ErrorCode.INVALID_CONFIGURATION,
                    "TABLE NAME is not defined.");
        }

        // modificationType contains a collection of objects for each type of operation
         List<ModificationType> modificationList = reqType.getModification();

         log.debug("ModificationList = " + modificationList);
         log.debug("Modificationlist size= " + modificationList.size());


         List<ModificationType> modTypeList = reqType.getModification();

         try {
             con = connectionMgr.connect(managedSys);

            // build sql
            StringBuffer whereBuf = new StringBuffer(" WHERE ");
            StringBuffer updateBuf = new StringBuffer("UPDATE " + tableName);
            updateBuf.append(" SET ");

        for (ModificationType mod : modTypeList) {
            ExtensibleType extType = mod.getData();
            List<ExtensibleObject> extobjectList = extType.getAny();

            int ctr = 0;


            for (ExtensibleObject obj : extobjectList) {
                    List<ExtensibleAttribute> attrList = obj.getAttributes();
                    principalFieldName =  obj.getPrincipalFieldName();
                    principalFieldDataType = obj.getPrincipalFieldDataType();

                    whereBuf.append( principalFieldName + " = ? " );

                    for (ExtensibleAttribute att : attrList) {


                        if (att.getOperation() != 0 && att.getName() != null) {
                            if ( att.getObjectType().equalsIgnoreCase("USER"))  {
                                if (ctr != 0) {
                                    updateBuf.append(",");
                                }
                                ctr++;

                                updateBuf.append( att.getName() + " = ? ");
                            }
                        }
                    }

                updateBuf.append( whereBuf );
                log.debug(" SQL=" + updateBuf.toString());

                PreparedStatement statement = con.prepareStatement(updateBuf.toString());

                ctr = 1;
                 for (ExtensibleAttribute att : attrList) {
                     if (att.getOperation() != 0 && att.getName() != null) {
                            if ( att.getObjectType().equalsIgnoreCase("USER"))  {
                                setStatement(statement, ctr, att);
                                ctr++;
                            }
                        }

                 }
                if (principalFieldName != null) {

                    setStatement(statement, ctr, principalFieldDataType, principalName);
                }

                statement.executeUpdate();

            }
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



    protected ModifyResponseType populateResp(ModifyResponseType response, StatusCodeType status,
                                                      ErrorCode err, String msg) {

                response.setStatus(status);
                response.setError(err);
                response.addErrorMessage(msg);
                return response;
            }



}


