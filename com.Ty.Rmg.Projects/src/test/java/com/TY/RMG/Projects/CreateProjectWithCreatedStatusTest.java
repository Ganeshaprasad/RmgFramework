package com.TY.RMG.Projects;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TY.RmgYantra.GenericUtils.BaseApiClass;
import com.TY.RmgYantra.GenericUtils.DataBaseUtilities;
import com.TY.RmgYantra.GenericUtils.EndPoint;
import com.TY.RmgYantra.GenericUtils.JavaUtility;
import com.TY.RmgYantra.GenericUtils.RestAssuredUtility;
import com.comcast.POJOClass.ProjectLibrary;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;

import java.sql.SQLException;

public class CreateProjectWithCreatedStatusTest extends BaseApiClass {
	@Test
	public void createProjectWithCreatedStatusTest() throws SQLException
	{
		JavaUtility jLib = new JavaUtility();
		RestAssuredUtility rLib = new RestAssuredUtility();
		DataBaseUtilities dLib = new DataBaseUtilities();
		ProjectLibrary pLib = new ProjectLibrary("ganesh", "icecream"+jLib.randomNumber(), "created", 10);
	Response response = given().contentType(ContentType.JSON).body(pLib)
	.when().post(EndPoint.addProject);
	//capture project id
	String firstProjectId = rLib.jsonPathConsant(response, "projectId");
	String actProjectName = rLib.jsonPathConsant(response, "projectName");
	System.out.println(firstProjectId);
	System.out.println(actProjectName);
	//verify the project id in db
	String query="select * from project";
	
	String expData = dLib.executeQueryAndGetData(query ,1, firstProjectId);
	String expPName = dLib.executeQueryAndGetData( query,4, actProjectName);
	//validation
	Assert.assertEquals(firstProjectId, expData);
	Assert.assertEquals(actProjectName, expPName);
	}

}
