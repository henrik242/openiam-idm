package org.openiam.spml2.spi.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.AttributeMap;
import org.openiam.idm.srvc.mngsys.service.ManagedSystemDataService;
import org.openiam.idm.srvc.res.dto.Resource;
import org.openiam.idm.srvc.res.dto.ResourceProp;
import org.openiam.idm.srvc.res.service.ResourceDataService;
import org.openiam.provision.type.ExtensibleAttribute;
import org.openiam.spml2.msg.ErrorCode;
import org.openiam.spml2.msg.ResponseType;
import org.openiam.spml2.msg.StatusCodeType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Base class for commands that are usec by the AppTableConnector
 * User: suneetshah
 * Date: 7/30/11
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AppTableAbstractCommand {

    protected static final Log log = LogFactory.getLog(AppTableAbstractCommand.class);

    protected ManagedSystemDataService managedSysService;
    protected ResourceDataService resourceDataService;
    protected JDBCConnectionMgr connectionMgr;

    protected void setStatement(PreparedStatement statement, int column, ExtensibleAttribute att)
            throws SQLException, ParseException {

        if (att.getDataType().equalsIgnoreCase("Date")) {

            String DATE_FORMAT = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            java.util.Date d = sdf.parse(att.getValue());

            // get the date into a java.sql.Date
            statement.setDate(column, new Date(d.getTime()));
        }
        if (att.getDataType().equalsIgnoreCase("Integer")) {
            statement.setInt(column, Integer.valueOf(att.getValue()));
        }
        if (att.getDataType().equalsIgnoreCase("Float")) {
            statement.setFloat(column, Float.valueOf(att.getValue()));
        }
        if (att.getDataType().equalsIgnoreCase("String")) {
            statement.setString(column, att.getValue());
        }
        if (att.getDataType().equalsIgnoreCase("Timestamp")) {
            statement.setTimestamp(column, Timestamp.valueOf(att.getValue()));
        }

    }

    protected void setStatement(PreparedStatement statement, int column, String dataType, String value)
            throws SQLException, ParseException {

        log.debug("Set search parameters in query");
        log.debug("- datatype = " + dataType);
        log.debug("- search value " + value);

        if (dataType.equalsIgnoreCase("Date")) {

            String DATE_FORMAT = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            java.util.Date d = sdf.parse(value);

            // get the date into a java.sql.Date
            statement.setDate(column, new Date(d.getTime()));
        }
        if (dataType.equalsIgnoreCase("Integer")) {
            statement.setInt(column, Integer.valueOf(value));
        }
        if (dataType.equalsIgnoreCase("Float")) {
            statement.setFloat(column, Float.valueOf(value));
        }
        if (dataType.equalsIgnoreCase("String")) {

            log.debug(" - Set string search parameter ");

            statement.setString(column, value);
        }
        if (dataType.equalsIgnoreCase("Timestamp")) {
            statement.setTimestamp(column, Timestamp.valueOf(value));
        }

    }

    protected ResponseType populateResponse(ResponseType response, StatusCodeType status,
                                            ErrorCode err, String msg) {

        response.setStatus(status);
        response.setError(err);
        response.addErrorMessage(msg);
        return response;
    }

    protected List<AttributeMap> attributeMaps(Resource res) {
        List<AttributeMap> attrMap = managedSysService.getResourceAttributeMaps(res.getResourceId());
        return attrMap;
    }

    protected String getTableName(Resource res) {
        ResourceProp prop = res.getResourceProperty("TABLE_NAME");

        if (prop.getPropValue() == null || prop.getPropValue().length() == 0) {
            return null;
        }
        return prop.getPropValue();
    }


    public ManagedSystemDataService getManagedSysService() {
        return managedSysService;
    }

    public void setManagedSysService(ManagedSystemDataService managedSysService) {
        this.managedSysService = managedSysService;
    }

    public ResourceDataService getResourceDataService() {
        return resourceDataService;
    }

    public void setResourceDataService(ResourceDataService resourceDataService) {
        this.resourceDataService = resourceDataService;
    }

    public JDBCConnectionMgr getConnectionMgr() {
        return connectionMgr;
    }

    public void setConnectionMgr(JDBCConnectionMgr connectionMgr) {
        this.connectionMgr = connectionMgr;
    }

    public PreparedStatement createSelectStatement(Connection con, Resource res, String tableName, String principalName) throws SQLException, ParseException {
        QueryObject qryObj = new QueryObject();

        qryObj.columnList = new StringBuffer("SELECT ");

        List<AttributeMap> attrMap = attributeMaps(res);
        if (attrMap == null) {
            log.debug("Attribute Map is null");
            return null;
        }

        int colCount = 0;
        for (org.openiam.idm.srvc.mngsys.dto.AttributeMap atr : attrMap) {

            if (atr.getMapForObjectType().equalsIgnoreCase("PRINCIPAL")) {
                qryObj.principalFieldName = atr.getAttributeName();
                qryObj.principalFieldDataType = atr.getDataType();

            } else if (atr.getMapForObjectType().equalsIgnoreCase("USER") ) {

                if (colCount > 0) {
                    qryObj.columnList.append(", ");
                }
                qryObj.columnList.append(atr.getAttributeName());
                colCount++;
            }
        }

        qryObj.columnList.append(" FROM " + tableName);
        qryObj.columnList.append(" WHERE  " + qryObj.principalFieldName  + " = ?");

        String sql = qryObj.columnList.toString();

        log.debug("SQL=" + sql);

        PreparedStatement statement = con.prepareStatement(sql);
        setStatement(statement, 1, qryObj.principalFieldDataType, principalName);

        return statement;

    }

    public PreparedStatement createDeleteStatement(Connection con, Resource res, String tableName, String principalName) throws SQLException, ParseException {
        QueryObject qryObj = new QueryObject();

        List<AttributeMap> attrMap = attributeMaps(res);
         if (attrMap == null) {
             log.debug("Attribute Map is null");
             return null;
         }

         int colCount = 0;
         for (org.openiam.idm.srvc.mngsys.dto.AttributeMap atr : attrMap) {

             if (atr.getMapForObjectType().equalsIgnoreCase("PRINCIPAL")) {
                 qryObj.principalFieldName = atr.getAttributeName();
                 qryObj.principalFieldDataType = atr.getDataType();

             }
         }


        StringBuffer delBuf = new StringBuffer("DELETE FROM " + tableName + " WHERE " + qryObj.principalFieldName + " = ?");

        log.debug("Del SQL=" + delBuf.toString());

        PreparedStatement statement = con.prepareStatement(delBuf.toString());

        // set the parameters
        setStatement(statement, 1, qryObj.principalFieldDataType, principalName);

        return statement;


    }

    public PreparedStatement createSetPasswordStatement(Connection con, Resource res,
                                                        String tableName, String principalName,
                                                        String password ) throws SQLException, ParseException {
             QueryObject qryObj = new QueryObject();

           String colName = null;
           String colDataType = null;

           List<AttributeMap> attrMap = attributeMaps(res);
            if (attrMap == null) {
                log.debug("Attribute Map is null");
                return null;
            }

            int colCount = 0;
            for (org.openiam.idm.srvc.mngsys.dto.AttributeMap atr : attrMap) {

                if (atr.getMapForObjectType().equalsIgnoreCase("PASSWORD")) {
                    colName = atr.getAttributeName();
                    colDataType = atr.getDataType();

                }
               if (atr.getMapForObjectType().equalsIgnoreCase("PRINCIPAL")) {
                 qryObj.principalFieldName = atr.getAttributeName();
                 qryObj.principalFieldDataType = atr.getDataType();

               }
            }

           StringBuffer updateBuf = new StringBuffer("UPDATE " + tableName );
           updateBuf.append( " SET " + colName + " = ? ");
           updateBuf.append( " WHERE " + qryObj.principalFieldName + " = ? ");

           PreparedStatement statement = con.prepareStatement(updateBuf.toString());

           // set the parameters
           setStatement(statement, 1, colDataType, password );
           setStatement(statement, 2, qryObj.principalFieldDataType, principalName);

           return statement;


       }



    public class QueryObject {

        public String principalFieldName;
        public String principalFieldDataType;
        public StringBuffer columnList = new StringBuffer();

    }


}

