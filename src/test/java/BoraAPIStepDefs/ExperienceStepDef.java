package BoraAPIStepDefs;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;

import Utilities.Constants;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExperienceStepDef {
	
	public static String token;
	public static Response response;
	public static final int HTTP_200 = 200;
	public static final int HTTP_400 = 400;

	@Given("User log into BoraTech API")
	public void user_log_into_BoraTech_API() {
		Response loginResponse = BoraAPI.login("maro9090@mail.ru", "Marat123");
		JsonPath jp = loginResponse.jsonPath();
		token = jp.get("token");
	}

	@When("User add valid experience data to user's profile")
	public void user_add_valid_experience_data_to_user_s_profile(DataTable dataTable) {
		Map<String, String> Data = dataTable.asMap(String.class, String.class);
		response = BoraAPI.AddExperience(token, Data);
	}

	@Then("User should get a {int}")
	public void user_should_get_a(int statusCode) {
		Assert.assertTrue(statusCode == response.getStatusCode());
	}

	@Then("User should see updated profile with experience added")
	public void user_should_see_updated_profile_with_experience_added(Map<String, String> data) {
			JsonPath jp = response.jsonPath();
			String title = jp.get("experience[0].title");
			Assert.assertTrue(title.equals(data.get("title")));
	}
	@When("User add INvalid experience data to user's profile")
	public void user_add_INvalid_experience_data_to_user_s_profile(DataTable dataTable) {
		Map<String, String> Data = dataTable.asMap(String.class, String.class);
		response = BoraAPI.AddExperience(token, Data);
	}

	@Then("User should get a {int} status code")
	public void user_should_get_a_status_code(int statusCode2) {
		Assert.assertTrue(statusCode2 == response.getStatusCode());
	}
	
	@Then("User should get a response {string}")
	public void user_should_get_a_response(String rspns) {
		Assert.assertTrue(response.getBody().asString().contains(rspns));
	}


}
