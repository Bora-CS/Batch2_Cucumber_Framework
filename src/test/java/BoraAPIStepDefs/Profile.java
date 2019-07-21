package BoraAPIStepDefs;

import org.json.simple.JSONObject;

import Utilities.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Profile {

	public static Response createProfile(String handle, String company, String website, String location,
					) {
		String endpoint = "/api/users/profile";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("email", username);
		requestBody.put("password", password);
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		return response;
	}
	
}
