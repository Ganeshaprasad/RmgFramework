package com.TY.RmgYantra.GenericUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtilities {
	Statement createStatement;
	Connection connection;
	/**
	 * this method used to create connection with db
	 * @throws SQLException
	 */
	public void connectionToDB() throws SQLException
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		


	}
	/**
	 * this method used to execute and get the data from db
	 * @param query
	 * @param columnIndex
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	public String executeQueryAndGetData(String query,int columnIndex,String expData) throws SQLException
	{
		boolean flag=false;
		connection = DriverManager.getConnection(IConstant.dbUrl,IConstant.dbUserName,IConstant.dbPassword);
		createStatement = connection.createStatement();

		ResultSet result = createStatement.executeQuery(query);
		while(result.next())
		{
			if(result.getString(columnIndex).equalsIgnoreCase(expData))
			{
				flag=true;
				break;
			}

		}
		if(flag==true)
		{
			System.out.println(expData+" data verified in DB ");
			return expData;
		}
		else
		{
			System.out.println(expData+" data is not verified");
			return expData;
		}


	}
	public void closeConnection() throws SQLException {
		connection.close();
	}

}
