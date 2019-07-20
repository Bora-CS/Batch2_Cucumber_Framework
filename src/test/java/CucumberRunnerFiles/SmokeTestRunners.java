package CucumberRunnerFiles;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = { "@Smoke" }, 
		glue = { "AmazonStepDefs", "GeneralStepDefs" }, 
		features = {"src/test/resources/FeatureFiles/" })
public class SmokeTestRunners {

}
