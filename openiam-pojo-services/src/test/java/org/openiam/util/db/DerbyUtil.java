package org.openiam.util.db;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.derby.tools.ij;

public class DerbyUtil {
	
	private static final String DERBY_DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DATABASE_NAME = "openIAMdb";
	
	public static Connection createDataBase(String databaseName) throws SQLException
    {
        // Do not use the EmbeddedDriver class here directly to avoid compile time references
        // on derby.jar
        try
        {
            Driver derbyDriver = (Driver) Class.forName(DERBY_DRIVER_CLASS).newInstance();
            
            String connectionName = "jdbc:derby:" + databaseName + ";create=true";
            return derbyDriver.connect(connectionName,null);             
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Error creating the database " + databaseName, ex);
        }
    }
	
	public static void buildOpeniamDB() throws Exception
	{
		//TODO make code more robust, if database is already created, simply drop all the elements
		Connection conn=createDataBase(DATABASE_NAME);
		//TODO use logging...
		OutputStream os=new ByteArrayOutputStream();
		ij.runScript(conn, new FileInputStream("../schema/rdbms/all/derby_schema_all.sql"), "US-ASCII", os, "US-ASCII");	
		
	}
	
	public static void deleteOpeniamDB() throws Exception
	{
		FileUtils.deleteDirectory(new File(DATABASE_NAME));
	}
	
	public static void stopDatabase() throws SQLException
    {
        try
        {
        	Class.forName(DERBY_DRIVER_CLASS);
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }
        catch (SQLException sqlex)
        {
            // this exception is documented to be thrown upon shutdown
            if (!"XJ015".equals(sqlex.getSQLState()))
            {
                throw sqlex;
            }
        }
        catch (Exception ex)
        {
            // this can only happen when the driver class is not in classpath. In this case, just
            // throw up
            throw new RuntimeException(ex);
        }
    }
}
