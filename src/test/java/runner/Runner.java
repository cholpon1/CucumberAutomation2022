package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue ="steps",
        tags = "@MB3-134", // tags also can be noted as "@etsyApplication or/and @otherCrapApp" having multiple tags by using "or"/"and"
        dryRun = false // dryRun function is not for actual execution
)

public class Runner {




}
