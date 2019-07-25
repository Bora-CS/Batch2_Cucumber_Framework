package BoraAPIStepDefs;

import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserProfileStep {
	public static Response response;
	public static String token;
	public static final int HTTP_200 = 200;
	
	@Given("User is logged in via BoraTech APIs")
	public void user_is_logged_in_via_BoraTech_APIs() {
		Response loginResponse = BoraAPI.login("student@gmail.com", "student123");
		JsonPath jp = loginResponse.jsonPath();
		token = jp.get("token");
	}

	@When("User pass in valid profile information")
	public void user_pass_in_valid_profile_information(DataTable dataTable) {
		Map<String, String> profileInfo = dataTable.asMap(String.class, String.class);
	    response = BoraAPI.createProfile(token, profileInfo);	
	}

	@Then("User should see their profile updates")
	public void user_should_see_their_profile_updates() {
	    
	}

	@Then("User should get a {int} status code")
	public void user_should_get_a_status_code(int statusCode) {
		Assert.assertTrue(statusCode == response.getStatusCode());
	}

}
