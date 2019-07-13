package Batch2_Cucumber_Framework;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Utilities.Keywords;

public class AmazonTestSteps {
	
	public static WebDriver driver;
	
	@Given("User is on amazon homepage")
	public void user_is_on_amazon_homepage() {
	    try {
			Keywords.chromeSetup();
			driver = new ChromeDriver();
			driver.get("https://www.amazon.com/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("User search for {string}")
	public void user_search_for(String item) {
	   WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	   searchBox.sendKeys(item);
	   searchBox.submit();
	}

	@Then("User should see results that related to {string}")
	public void user_should_see_results_that_related_to(String item) {
	    List<WebElement> results = driver.findElements(By.cssSelector(".a-link-normal.a-text-normal"));
	    
	    String[] arr = item.split(" ");
	    
	    int matches = 0;
	    for (int i = 0; i < 20; i++) {
	    	String resultText = results.get(i).getText().toLowerCase();
	    		    	
	    	for (String str : arr) {
				if (resultText.contains(str.toLowerCase())) {
					matches++;
					break;
				}
			}
		}
	    	    
	    driver.close();
	    driver.quit();
	    
	    Assert.assertTrue("There were less than 5 items related to " + item, matches > 5); 
	}

}
