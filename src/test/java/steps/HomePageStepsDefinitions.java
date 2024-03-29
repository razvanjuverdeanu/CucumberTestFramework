package steps;

import Utils.TestListener;
import component.TheDriver;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class HomePageStepsDefinitions {

    @Autowired
    TheDriver driver;
    @Autowired
    HomePage homePage;

    @When("that I open ebay")
    public void thatIOpenEbay() {
        driver.get(driver.getBaseUrl());
        driver.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(driver.getBaseUrl()));
    }

    @When("I type {string} in search bar")
    public void i_type_in_search_bar(String text) {
        homePage.typeTextInSearchBar(text);
    }

}
