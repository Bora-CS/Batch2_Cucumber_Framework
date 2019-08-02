package Utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	private static WebDriver driver = null;
	
	private DriverFactory() {
	}
	
	public static WebDriver getInstance(){
		try {
			if (driver == null) {
				String driverPath = Keywords.determineOSandGetDriverPath();
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void cleanUp() {
		driver.close();
		driver.quit();
		driver = null;
	}
	
}
