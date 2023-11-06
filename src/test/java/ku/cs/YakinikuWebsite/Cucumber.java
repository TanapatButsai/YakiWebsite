package ku.cs.YakinikuWebsite;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "ku.cs.YakinikuWebsite", // Specify the package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class Cucumber {

}