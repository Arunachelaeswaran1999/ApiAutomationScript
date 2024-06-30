package api.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserEndpointsTestScripts {
	
	//Global variables
	public User userpayload;
	public Faker data;

	@BeforeClass
	public void SetupData() {

		//create object for user pojo class
		userpayload = new User();
		//cerate object for java faker class
		data = new Faker();
		
		//Request payload data's
		userpayload.setId(data.idNumber().hashCode());
		userpayload.setUsername(data.name().fullName());
		userpayload.setFirstName(data.name().firstName());
		userpayload.setLastName(data.name().lastName());
		userpayload.setEmail(data.internet().emailAddress());
		userpayload.setPassword(data.internet().password());
		userpayload.setPhone(data.phoneNumber().cellPhone());

	}

	@Test
	(priority = 1)
	public void CreateUserTest() {

		Response response = UserEndpoints.CreateUser(userpayload);
		
		//Write Validation scripts
		response.then().statusCode(200);
		response.then().log().body();
		
		//get the username
		System.out.println("Username: "+this.userpayload.getUsername());
		

	}
	
	@Test
	(priority = 2)
	public void FetchUserTest() {

		Response response = UserEndpoints.FetchUser(this.userpayload.getUsername());
		
		//Write Validation scripts
		response.then().statusCode(200);
		response.then().log().body();
		
		
	}
	
	@Test
	(priority = 3)
	public void UpdateUserTest() {

		userpayload.setFirstName(data.name().firstName());
		Response response = UserEndpoints.UpdateUser(userpayload, this.userpayload.getUsername());
		
		//Write Validation scripts
		response.then().statusCode(200);
		response.then().log().all();
		
		//after update the same user check the payload
		Response response1 = UserEndpoints.FetchUser(this.userpayload.getUsername());
		response1.then().log().body();
	}
	
	@Test
	(priority = 4)
	public void DeleteUserTest() {

		Response response = UserEndpoints.DeleteUser(this.userpayload.getUsername());
		
		//Write Validation scripts
		response.then().statusCode(200);
		response.then().log().body();
		
		//after delete the same user check the payload
		Response response1 = UserEndpoints.FetchUser(this.userpayload.getUsername());
		response1.then().statusCode(404);
		response1.then().log().body();
		
	}

}
