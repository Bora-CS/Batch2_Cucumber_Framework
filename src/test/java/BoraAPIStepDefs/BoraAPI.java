package BoraAPIStepDefs;

import java.util.Map;

import org.json.simple.JSONObject;

import Utilities.Constants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPI {

	public static Response login(String email, String password) {
		String endpoint = "/api/users/login";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("email", email);
		requestBody.put("password", password);
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		return response;
	}

	public static Response AddExperience(String token, Map<String, String> Data) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", token);
		
		JSONObject requestBody = new JSONObject(Data);
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		return response;
	}
	


}
