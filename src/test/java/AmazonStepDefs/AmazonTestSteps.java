package AmazonStepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import DataObjects.Result;
import DataObjects.UserInfo;
import Utilities.DriverFactory;
import Utilities.ExcelManager;
import Utilities.Keywords;

public class AmazonTestSteps {

	@Given("User is on amazon homepage")
	public void user_is_on_amazon_homepage() {
		try {
			Keywords.chromeSetup();
			WebDriver driver = DriverFactory.getInstance();
			driver.get("https://www.amazon.com/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Given("I'm on the amazon.com homepage")
    public void i_m_on_the_amazon_com_homepage() {
        try {
            WebDriver driver = DriverFactory.getInstance();
            driver.get("https://www.amazon.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Keywords.waitFor(1);
    }

    @When("I search for an {string}")
    public void i_search_for_an(String item) {
    	WebDriver driver = DriverFactory.getInstance();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item);
        Keywords.waitFor(1);
        driver.findElement(By.className("nav-input")).click();
        Keywords.waitFor(1);
    }

    @When("I get some results related to {string}")
    public void i_get_some_results_related_to(String itemName) {
    	amazonGet100Results(itemName);
    }

	@Then("I will be able to find out the highest price, lowset price and the average price")
    public void i_will_be_able_to_find_out_the_highest_price_lowset_price_and_the_average_price() {
		DriverFactory.cleanUp();
    }

	@When("User search for {string}")
	public void user_search_for(String item) {
		WebDriver driver = DriverFactory.getInstance();
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(item);
		searchBox.submit();
	}

	@Then("User should see results that related to {string}")
	public void user_should_see_results_that_related_to(String item) {
		WebDriver driver = DriverFactory.getInstance();
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

		List<UserInfo> userInfoTable = dataTable.asList(UserInfo.class);

		for (UserInfo userInfo : userInfoTable) {
			System.out.println("| " + userInfo.ID + "\t| " + userInfo.Username + "\t| " + userInfo.Password + "\t|");
		}
		
		// Get user data eith ID 2
		System.out.println(getDataByID(userInfoTable, "2").Username);

	}
	
	private UserInfo getDataByID(List<UserInfo> inputData, String ID) {
		for (UserInfo input : inputData) {
			if (input.ID.equals(ID)) {
				return input;
			}
		}
		return null;
	}

	private List<String> getDataByID(List<List<String>> infos, int i) {
		for (List<String> info : infos) {
			if (info.get(0).equals(i + "")) {
				return info;
			}
		}
		return null;
	}

    private void amazonGet100Results(String item) {
    	WebDriver driver = DriverFactory.getInstance();
    	List<Result> finalResults = new ArrayList<Result>();
    	
    	while (finalResults.size() < 100) {
			// Repeat from here *********************************
			List<WebElement> results = driver.findElements(By.xpath("//span[@data-component-type='s-search-results']/div/div[starts-with(@data-asin, 'B')]"));
					
			for (int i = 1; i <= results.size(); i++) {
				String parentXpath = "//span[@data-component-type='s-search-results']/div/div[" + i + "]";			
				String title;
				String price;
				
				try {
					title = driver.findElement(By.xpath(parentXpath + "//span[@class='a-size-base-plus a-color-base a-text-normal']")).getText();
				} catch (Exception e) {
					title = "Title not available";
				}
				
				try {
					price = driver.findElement(By.xpath(parentXpath + "//a[@class='a-size-base a-link-normal s-no-hover a-text-normal']//span[@data-a-size='l']")).getText();
				} catch (Exception e) {
					price = "Price not available";
				}
				
				price = getPrice(price);
							
				if (!price.contains("FREE") && !price.equals("Price not available")) {
//					System.out.println("Item "+ itemNumber + " $" + price + "\t" + title);
					finalResults.add(new Result(title, Double.parseDouble(price)));
					if (finalResults.size() == 100) break;
				}
			}
			// to here *********************************
			if (finalResults.size() == 100) break;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		
		System.out.println("Item Number\tPrice\tItem Name");
		
		// Create Excel Sheet =============================================
		String workbookname = "AmazonProductResearch"+ExcelManager.getTimeStamp();
		ExcelManager.createWorkbook(workbookname);
		
		XSSFWorkbook workbook = ExcelManager.openWorkbook(workbookname);
		XSSFSheet sheet = workbook.createSheet(item);
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Item Name");
		row.createCell(1).setCellValue("Price");
		
		// ===================================================================
		
		double sum = 0.0;
		double highest = finalResults.get(0).price;
		double lowest = finalResults.get(0).price;
		for (int i = 0; i < finalResults.size(); i++) {
			System.out.println((i+1) + "\t\t$" + finalResults.get(i).price + "\t" + finalResults.get(i).title);
			double currentPrice = finalResults.get(i).price;
			sum = sum + currentPrice;
			if (highest < currentPrice) highest = currentPrice;
			if (lowest > currentPrice) lowest = currentPrice;
			
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(finalResults.get(i).title);
			row.createCell(1).setCellValue(finalResults.get(i).price);
		}
	
		row = sheet.createRow(102);
		row.createCell(0).setCellValue("Highest Price");
		row.createCell(1).setCellValue(highest);
		row.createCell(2).setCellValue("Lowset Price");
		row.createCell(3).setCellValue(lowest);
		row.createCell(4).setCellValue("Average Price");
		row.createCell(5).setCellValue(sum/100);
		
		ExcelManager.saveAndCloseWorkbook(workbookname, workbook);
	}
    
    public static String getPrice (String a) {
		a = a.replace("\n", ".").replace("-.", "-").replace(".-", "-").replace("$", "");
		
		if (!a.contains("-")) {
			return a;
		}
		
		String[] prices = a.split("-");
		
		double[] pricesInDouble = new double[prices.length];
		for (int i = 0; i < prices.length; i++) {
			pricesInDouble[i] = Double.parseDouble(prices[i]);
		}
		
		double average = (pricesInDouble[0] + pricesInDouble[1]) / 2;
		
		return (Math.round(average * 100.0) / 100.0) + "";
	}

}
