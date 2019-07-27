package CucumberRunnerFiles;

	import cucumber.api.CucumberOptions;
	import cucumber.api.junit.Cucumber;
	import org.junit.runner.RunWith;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			
			plugin = {"html:target/html_reports"},
			glue = {"BoraAPIStepDefs"},
			features = {"src/test/resources/FeatureFiles/CreateUserProfile.feature"}
			)
	public class ProfileRunner {
		
	}



