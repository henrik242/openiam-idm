package org.openiam.base.id;

import java.io.Serializable;
import java.util.*;
import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.TableGenerator;
import org.hibernate.util.*;
import org.hibernate.dialect.Dialect;
import org.hibernate.type.Type;
import org.hibernate.mapping.Table;
import org.hibernate.LockMode;
import org.hibernate.id.IdentifierGenerationException;



/**
  * An <tt>IdentifierGenerator</tt> that uses a database
  * table to store the last generated value + 1. It is not
  * intended that applications use this strategy directly.
  * However, it may be used to build other (efficient)
  * strategies. The returned type is <tt>String</tt> since 
  * most objects in OpenIAM use a String for ID fields. Strings
  * were selected as ID fields to allow the end corporation to 
  * to use other types of IDs..<br>
  * <br>
  * Mapping parameters supported: table, column, attribute
  *
  * @author Suneet Shah
  */

public class SequenceGenerator extends TableGenerator implements IdentifierGenerator   {
	
		public static final String COLUMN = "column";
	    /** Default column name */
	    public static final String DEFAULT_COLUMN_NAME = "next_hi";
	    /** The table parameter */
	    public static final String TABLE = "table";
	    /** Default table name */
	    public static final String DEFAULT_TABLE_NAME = "hibernate_unique_key";

	    /* attribute parameter */
	    public static final String ATTRIBUTE = "attribute";
	
	    private String tableName;
	    private String columnName;
	    private String attributeName;
	    private String query;
	    private String update;

		private static final Log log = LogFactory.getLog(SequenceGenerator.class);
	    
	public void configure(Type type, Properties params, Dialect dialect) {
		
		this.tableName = PropertiesHelper.getString(TABLE, params, DEFAULT_TABLE_NAME);
		this.columnName = PropertiesHelper.getString(COLUMN, params, DEFAULT_COLUMN_NAME);
		this.attributeName = params.getProperty(ATTRIBUTE);
		String schemaName = params.getProperty(SCHEMA);
		String catalogName = params.getProperty(CATALOG);		
		
		if ( tableName.indexOf( '.' )<0 ) {
			tableName = Table.qualify( catalogName, schemaName, tableName );
		}

		query = "select " +
		        columnName +
		        " from " +
		        dialect.appendLockHint(LockMode.UPGRADE, tableName) +
		        " where ATTRIBUTE = ?" + 
		        dialect.getForUpdateString();
		
		update = "update " +
		         tableName +
		         " set " +
		         columnName +
		         " = ? where " +
		         " ATTRIBUTE = ? " ;        	
	}
	
	public synchronized Serializable generate(SessionImplementor session, Object obj)
			throws HibernateException {


		int result = ( (Integer) doWorkInNewTransaction(session) ).intValue();
		return String.valueOf(result);
        	
	}

	
	public Serializable doWorkInCurrentTransaction(Connection conn, String sql) throws SQLException {
		int result;
		int rows;
		do {
			// The loop ensures atomicity of the
			// select + update even for no transaction
			// or read committed isolation level

			sql = query;
			//SQL.debug(query);
			PreparedStatement qps = conn.prepareStatement(query);
			try {
				qps.setString(1, attributeName);
				
				ResultSet rs = qps.executeQuery();
				if ( !rs.next() ) {
					String err = "could not read the current id value - you need to populate the table: " + tableName;
					log.error(err);
					throw new IdentifierGenerationException(err);
				}
				result = rs.getInt(1);
				rs.close();
			}
			catch (SQLException sqle) {
				log.error("could not read a hi value", sqle);
				throw sqle;
			}
			finally {
				qps.close();
			}

			sql = update;
			//SQL.debug(update);
			PreparedStatement ups = conn.prepareStatement(update);
			try {
				ups.setInt( 1, result + 1 );
				ups.setString(2, attributeName);
				rows = ups.executeUpdate();
			}
			catch (SQLException sqle) {
				log.error("could not update value in: " + tableName, sqle);
				throw sqle;
			}
			finally {
				ups.close();
			}
		}
		while (rows==0);
		return new Integer(result);
	}	
	
	
}
