package api.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostuser(String userid, String username, String firstname, String lastname, String email,
			String password, String phone) {
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstname);
		userPayload.setLastName(lastname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);

		Response response = UserEndpoints.CreateUser(userPayload);

		// validate the status code
		response.then().statusCode(200);

		// Print the response payload body
		response.then().log().body();

		// validate the message
		String ActualMessage = response.jsonPath().get("message").toString();
		System.out.println("UserID :" + userid);
		Assert.assertEquals(ActualMessage, userid);

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void getUserTest(String username) {

		Response response = UserEndpoints.FetchUser(username);

		response.then().statusCode(200);
		response.then().log().body();

	}

	@Test
	(priority = 3, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void updateUserTest(String userid, String username, String firstname, String lastname, String email, String password, String phone) {
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(username);
		userPayload.setFirstName("Arunachela");
		userPayload.setLastName("eswaran S");
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);		 
		 
		Response response = UserEndpoints.UpdateUser(userPayload, username);
		
		//Print the response body
		response.then().log().body();
		
		//After update the user details again fetch and print the response payload
		Response response1 = UserEndpoints.FetchUser(username);
		response1.then().statusCode(200);
		response1.then().log().body();
		
	}
	
	@Test
	(priority = 4, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void deleteUserTest(String username) {
		
		Response response = UserEndpoints.DeleteUser(username);
		
		//Check the status code
		response.then().statusCode(200);
		
		//Print the body
		response.then().log().body();
		
		//After deleting user's try to fetch the user details
		Response response1 = UserEndpoints.FetchUser(username);
		response1.then().statusCode(404);
		response1.then().log().body();
		
	}

}
