package com.ubaid.app.model.singleton;

import java.sql.Connection;


import com.mysql.cj.jdbc.MysqlDataSource;


public class DataSource
{
	private static Connection connection = createConnection();
	
	private DataSource()
	{
		
	}
	
	public static void main(String [] args)
	{
		getConnection();
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
	
	private static Connection createConnection()
	{
		
		
		MysqlDataSource dataSource = new MysqlDataSource();
		
		Connection connection = null;
		try
		{
			dataSource.setAllowPublicKeyRetrieval(true);
			dataSource.setUseSSL( false );
		    dataSource.setServerTimezone("GMT");
		    dataSource.setAutoReconnect(true);
		    System.out.println(System.getProperty("os.name"));
		    if(System.getProperty("os.name").toLowerCase().contains("window"))
		    {
			    dataSource.setServerName("68.183.98.59");	
			    dataSource.setUseSSL(true);
			    System.out.println("remotely");
		    }
		    else
		    {
			    dataSource.setServerName("localhost");		    			    	
			    System.out.println("locoaly");

		    }
		    dataSource.setDatabaseName("sport_db_consize");
		    dataSource.setPortNumber(3306);
		    dataSource.setUser("root");
		    dataSource.setPassword("password");
		    dataSource.setRewriteBatchedStatements(true);
		    connection = dataSource.getConnection();
		    System.out.println("Connected");
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	    return connection;			

	}
}
