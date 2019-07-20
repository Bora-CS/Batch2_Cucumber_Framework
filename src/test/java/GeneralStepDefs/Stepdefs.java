package GeneralStepDefs;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//import static org.junit.Assert.*;

class IsItFriday {
	static String isItFriday(String today) {
		if ("Friday".equals(today)) {
			return "Yes";
		} else {
			return "Nope";
		}
	}
}

public class Stepdefs {
	private String today;
	private String actualAnswer;

	@Given("today is {string}")
	public void today_is_Someday(String today) {
		this.today = today;
	}

	@When("I ask whether it's Friday yet")
	public void i_ask_whether_it_s_Friday_yet() {
		actualAnswer = IsItFriday.isItFriday(today);
	}

	@Then("I should be told {string}")
	public void i_should_be_told(String expectedAnswer) {
		Assert.assertEquals(expectedAnswer, actualAnswer);
	}
}
