package Utilities;

public class Keywords {
	
	public static void chromeSetup() throws Exception {
		System.setProperty("webdriver.chrome.driver", determineOSandGetDriverPath());
	}
	
	public static String determineOSandGetDriverPath() throws Exception {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")) {
			return Constants.CHROME_DRIVER_PATH_MAC;
		} else if (os.toLowerCase().startsWith("windows")) {
			return Constants.CHROME_DRIVER_PATH_WINDOWS;
		} else {
			throw new Exception("Unknown Operating System: " + os);
		}
	} 
	
	public static void waitFor (int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
