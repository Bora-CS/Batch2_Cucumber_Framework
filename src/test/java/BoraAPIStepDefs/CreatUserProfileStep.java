package BoraAPIStepDefs;


import java.util.Map;

import javax.swing.Spring;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class CreatUserProfileStep {
	public String response;
	public String token;
	
	@Given( "User logged into Bora API")
	public void user_is_loged_in_to_Bora_API() {
		 BoraAPI.login("student@gmail.com", "student123");
	}

	@When("User pass in valid information")
	public void user_pass_in_valid_information(DataTable dataTable) {
		Map<String,String> StudentInfo= dataTable.asMap(String.class,String.class);
		
		for (String key : StudentInfo.keySet() ) {
			System.out.println(key +StudentInfo.get(key));
			
		}
	}

	@Then("User should get a {int} status code")
	public void user_should_get_a_status_code(int  statusCode) {
	 

//		Assert.assertEquals(statusCode, response.getStatusCode());
//		Assert.assertTrue("Expected status code:"+statusCode+" Actual status code:"+response.getStatusCode(),statusCode==response.getStatusCode() );
//		
	}


	
	
}
