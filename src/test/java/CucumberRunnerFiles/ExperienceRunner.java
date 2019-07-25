package CucumberRunnerFiles;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false,
		plugin = {"html:target/html_reports"},
		glue = {"BoraAPIStepDefs"},
		features = {"src/test/resources/FeatureFiles/BoraAPIs/Experience.feature"}
		)
public class ExperienceRunner {

	
}
