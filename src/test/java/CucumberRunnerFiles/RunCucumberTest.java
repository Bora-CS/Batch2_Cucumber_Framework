package CucumberRunnerFiles;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false,
		plugin = {"html:target/html_reports"},
		glue = {"AmazonStepDefs","GeneralStepDefs"},
		features = {"src/test/resources/FeatureFiles/is_it_friday_yet.feature"}
		)
public class RunCucumberTest {
}
