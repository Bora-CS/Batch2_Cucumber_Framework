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
		requestBody.put("email", username);
		requestBody.put("password", password);
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		return response;
	}

	public static Response createProfile(String token, Map<String, String> profileInfo) {
		String endpoint = "/api/profile";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Authorization", token);
		
		// 1st way to create json object from Map
		JSONObject requestBody = new JSONObject(profileInfo);
		request.body(requestBody);
		
//		// 2nd way to create json object from Map
//		JSONObject requestBody = new JSONObject();
//		for (String key : profileInfo.keySet()) {
//			requestBody.put(key, profileInfo.get(key));
//		}
//		request.body(requestBody);

		Response response = request.post(endpoint);
		return response;
	}

	public static Response getCurrentPro(String token, Map<String, String> profileInfo) {
		
		String endpoint = "/api/profile";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		request.header("Content-Type", "application/json");
	
		JSONObject requestBody = new JSONObject(profileInfo);
		
		request.body(requestBody);
		
		
		
		Response response = request.get(endpoint);
		return response;
	}

	public static Response getProfileWithTemplate (String token, Map<String, String>  profileInfo) throws UnsupportedEncodingException, IOException, ParseException {
		
		String endpoint = "/api/profile";
		RestAssured.baseURI = Constants.BORA_API_APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		request.header("Content-Type", "application/json");
	
		JSONObject requestBody = new JSONObject();
		
		request.body(requestBody);
		
		
		String template = new String(Files.readAllBytes(Paths.get("src/test/resources/JSON_TEMPLATE/GetProfile.txt")),"UTF-8");
		
		for (String  key :  profileInfo.keySet()) {
			if (!key.equals("profileInformation")){
				template = template.replace(key.toUpperCase() + "_VALUE", profileInfo.get(key));
			}
		}
		 
		JSONParser parser = new JSONParser();
		JSONObject requstBody = (JSONObject) parser.parse(template);
		
		request.body(requestBody);
		
		
		Response response = request.get(endpoint);
		return response;
		
		
		
		
		
		

	}

}
