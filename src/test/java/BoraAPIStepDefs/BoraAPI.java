package BoraAPIStepDefs;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

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

	public static Response AddExperience(String token, Map<String, String> data) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", token);

		JSONObject requestBody = new JSONObject(data);
		request.body(requestBody);

		Response response = request.post(endpoint);
		return response;
	}

	public static Response AddExperienceWithTemplate(String token, Map<String, String> data)
			throws UnsupportedEncodingException, IOException, ParseException {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", token);

		String requestString = new String(
				Files.readAllBytes(
						Paths.get("src/test/resources/JSON_TEMPLATES/"+data.get("typeOfRequest")+".txt")
						),
				"UTF-8");
				
		for (String key : data.keySet()) {
			if (!key.equals("typeOfRequest")) {
				requestString = requestString.replace(key.toUpperCase() + "_VALUE", data.get(key));
			}
		}
		
		System.out.println("Request Body:\n" + requestString);
		
		JSONParser parser = new JSONParser();
		JSONObject requestBody = (JSONObject) parser.parse(requestString);
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		return response;
	}

	public static void statusCodeValidation(int statusCode, int statusCode2) {
		Assert.assertTrue(statusCode == statusCode2);
	}

}
