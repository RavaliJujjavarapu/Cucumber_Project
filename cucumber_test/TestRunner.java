package cucumber_test;

	import org.junit.runner.RunWith;
	import cucumber.api.CucumberOptions;
	import cucumber.api.junit.Cucumber;
	
	
	@RunWith(Cucumber.class)
	@CucumberOptions(
	    features = "src/test/java/Features",
	    glue = {"stepDefinitions"},
	    tags = {"@new_user"},
	    strict = true,
	    monochrome = true,
	    plugin={"html:target/test-reports-JOBS/html/html_Report"}	    
	    )
	
	public class TestRunner {
	    //empty
	}


