package BoraAPIStepDefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

public class LogInStepDefs {
	
	public Response response;
	public String token;
	
	@Given("User is registered with Bora API")
	public void user_is_registered_with_Bora_API() {
	}
	
	@When("User submits the login request with {string} and {string}")
	public void user_submits_the_login_request_with_and(String username, String password) {
		response = BoraAPI.login(username, password);
	}

	@Then("User should get a {int} status code and a token")
	public void user_should_get_a_status_code_and_a_token(int statusCode) {
		Assert.assertTrue("Status code unexpected! Expected status code:"+statusCode+" Actual status code:"+response.getStatusCode(), response.getStatusCode() == statusCode);
		Assert.assertTrue("No token received!", response.getBody().asString().contains("token"));
	}
	
	@Then("User should get a {int} status code and an error message")
	public void user_should_get_a_status_code_and_an_error_message(int statusCode, DataTable dataTable) {
		Assert.assertTrue("Status code unexpected! Expected status code:"+statusCode+" Actual status code:"+response.getStatusCode(), response.getStatusCode() == statusCode);
		Map<String, String> actualErrorMsg = response.getBody().as(HashMap.class);
		Map<String, String> expectedErrorMsg = dataTable.asMap(String.class, String.class);
		
		// Asserting 2 maps directly
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	
	@Then("User should get a {int} and an {string}")
	public void user_should_get_a_and_an(int statusCode, String errorMessage) {
		Assert.assertTrue("Status code unexpected! Expected status code:"+statusCode+" Actual status code:"+response.getStatusCode(), response.getStatusCode() == statusCode);
		Map<String, String> actualErrorMsg = response.getBody().as(HashMap.class);
		Map<String, String> expectedErrorMsg = new HashMap<>();
		String[] errorMessageDetails = errorMessage.split(" - ");
		expectedErrorMsg.put(errorMessageDetails[0], errorMessageDetails[1]);
		
		// Asserting the keys of 2 maps first, and then asseting the values
		for (String key : expectedErrorMsg.keySet()) {
			Assert.assertTrue("Key " + key + " does not exist in the response.",actualErrorMsg.containsKey(key));
			Assert.assertEquals(expectedErrorMsg.get(key), actualErrorMsg.get(key));
			
		}
		
	}

}
