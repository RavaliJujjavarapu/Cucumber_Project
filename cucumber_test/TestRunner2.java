package cucumber_test;

	import org.junit.runner.RunWith;
	import cucumber.api.CucumberOptions;
	import cucumber.api.junit.Cucumber;
	
	
	@RunWith(Cucumber.class)
	@CucumberOptions(
	    features = "src/test/java/Features",
	    glue = {"stepDefinitions"},
	    tags = {"@suite_crm"},
	    strict = true,
	    monochrome = true,
	    plugin={"html:target/test-reports-CRM/html/html_Report"}
	    )
	
	public class TestRunner2 {
	    //empty
	}
