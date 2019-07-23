package BoraAPIStepDefs;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class CreateUserProfileStep {
	public static Response response;
	public String token;
	int statusCode = 200;

	@Given("User is loged in to Bora API")
	public void user_is_loged_in_to_Bora_API() {
		response = BoraAPI.login("student@gmail.com", "student123");
		System.out.println();

		Assert.assertTrue("Status code unexpected! Expected status code:" + statusCode + " Actual status code:"
				+ response.getStatusCode(), response.getStatusCode() == statusCode);
		Assert.assertTrue("No token received!", response.getBody().asString().contains("token"));
	}

//	public static void validationLogin(int statusCode) {
//		Assert.assertTrue("Status code unexpected! Expected status code:" + statusCode + " Actual status code:"
//				+ response.getStatusCode(), response.getStatusCode() == statusCode);
//		Assert.assertTrue("No token received!", response.getBody().asString().contains("token"));
//	}
//	 

	@When("User pass in valid information")
	public void user_pass_in_valid_information(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

	}

	@Then("User should get a {int} status code")
	public void user_should_get_a_status_code(Integer int1) {

	}

}
