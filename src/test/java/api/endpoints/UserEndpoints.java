package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response CreateUser(User payload) {
		
		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_url);
		
		return response;

	}

	public static Response FetchUser(String username) {
		
		Response response = given()
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.when()
				.get(Routes.get_url);
		
		return response;
		

	}

	public static Response UpdateUser(User payload, String username) {
		
		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)
				.pathParam("username", username)
				.when()
				.put(Routes.put_url);
		
		return response;

	}

	public static Response DeleteUser(String username) {
		
		Response response = given()
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.when()
				.delete(Routes.delete_url);
		
		return response;

	}

}
