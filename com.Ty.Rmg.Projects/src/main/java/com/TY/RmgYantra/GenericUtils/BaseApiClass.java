package com.TY.RmgYantra.GenericUtils;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseApiClass {
	DataBaseUtilities	dbUtils;
	
	@BeforeSuite
	public void bsConfig() throws SQLException
	{
		baseURI = "http://localhost";
		port = 8084;
		dbUtils=new DataBaseUtilities();
		dbUtils.connectionToDB();
		
	}
	@AfterSuite
	public void asConfig() throws SQLException
	{
		dbUtils=new DataBaseUtilities();
		dbUtils.closeConnection();
	}
	

}
