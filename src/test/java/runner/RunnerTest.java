package runner;

import component.TheDriver;
import config.ApplicationContextConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.test.context.ContextConfiguration;

@CucumberOptions(features = {"src/test/java/resourceCucumber/Category.feature"}, glue = {"steps"})
@ContextConfiguration(classes = {ApplicationContextConfig.class, TheDriver.class})
@CucumberContextConfiguration
public class RunnerTest extends AbstractTestNGCucumberTests {

}
