package Utilities;

public class Keywords {
	
	public static void chromeSetup() throws Exception {
		System.setProperty("webdriver.chrome.driver", determineOSandGetDriverPath());
	}
	
	private static String determineOSandGetDriverPath() throws Exception {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")) {
			return Constants.CHROME_DRIVER_PATH_MAC;
		} else if (os.toLowerCase().startsWith("windows")) {
			return Constants.CHROME_DRIVER_PATH_WINDOWS;
		} else {
			throw new Exception("Unknown Operating System: " + os);
		}
	} 
	
}
