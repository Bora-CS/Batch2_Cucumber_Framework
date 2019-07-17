package Batch2_Cucumber_Framework;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTableType;

import java.util.List;
import java.util.Map;

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

	@Given("User is registered with amazon.com")
	public void user_is_registered_with_amazon_com(DataTable dataTable) {

		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.

		List<UserInfo> tableToUserInfos = dataTable.asList(UserInfo.class);

		for (UserInfo userInfo : tableToUserInfos) {
			System.out.println("| " + userInfo.ID + "\t| " + userInfo.Username + "\t| " + userInfo.Password + "\t|");
		}

	}

	private List<String> getDataByID(List<List<String>> infos, int i) {
		for (List<String> info : infos) {
			if (info.get(0).equals(i + "")) {
				return info;
			}
		}
		return null;
	}

	@When("User logs in on amazon homepage")
	public void user_logs_in_on_amazon_homepage() {

	}

	@Then("User should be able")
	public void user_should_be_able() {

	}

}
