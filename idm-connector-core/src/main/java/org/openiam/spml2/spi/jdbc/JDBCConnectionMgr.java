package org.openiam.spml2.spi.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.mngsys.dto.ManagedSys;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


/**
 * Manages connections to LDAP
 * @author Suneet Shah
 *
 */
public class JDBCConnectionMgr  {

    Connection sqlCon = null;


    private static final Log log = LogFactory.getLog(JDBCConnectionMgr.class);

    public JDBCConnectionMgr() {
    }
    


	public Connection  connect(ManagedSys managedSys) throws ClassNotFoundException, SQLException {

        Class.forName(managedSys.getDriverUrl());
        String url = managedSys.getConnectionString() ;
        sqlCon = DriverManager.getConnection(url,managedSys.getUserId(), managedSys.getDecryptPassword() );

        return sqlCon;



	}

	public void close() throws SQLException {

		if (this.sqlCon != null) {
    		sqlCon.close();
		}
		sqlCon = null;
		
	}

}
