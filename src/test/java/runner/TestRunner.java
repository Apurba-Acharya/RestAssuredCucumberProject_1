package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepdefinations"},
        plugin = {"pretty", "html:target/cucumber-Report.html", "json:target/cucumber.json"},
        monochrome = true // To get the output in readable format
)

public class TestRunner {

}