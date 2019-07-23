package BoraAPIStepDefs;
import org.json.simple.JSONObject;

import Utilities.Constants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPI {

	public static Response login(String username, String password) {
		String endpoint = "/api/users/login";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("username", username);
		requestBody.put("password", password);
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		return response;
	}

}
