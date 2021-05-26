package steps;

import Utils.TestListener;
import component.TheDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import pages.HomePage;
import pages.SearchPage;

import java.util.List;

import static Utils.HelpersMethods.getRandomStringFromList;
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class SearchStepsDefinitions {

    @Autowired
    TheDriver driver;
    @Autowired
    HomePage homePage;
    @Autowired
    SearchPage searchPage;

    Logger log = LoggerFactory.getLogger(SearchStepsDefinitions.class);

    private String randomCategory;

    @When("I press search button")
    public void iPressSearchButton() {
        homePage.clickSearchButton();
    }

    @Then("Page header display the category title")
    public void pageHeaderDisplayTheCategoryTitle() {
        String pageHeader = searchPage.getSearchCategoryPageHeader();
        log.info("Page header title is: {} while category title is: {}", pageHeader, randomCategory);
        assertTrue(pageHeader.contains(randomCategory));
    }

    @When("I set a {string} value of {string} to price filter")
    public void iSeTPriceFilterByTypeAndValue(String type, String value) {
        searchPage.setPriceFilterByTypeAndVelue(type, value);
    }

    @Then("Search results contains {string}")
    public void searchResultsContains(String text) {
        assertTrue(searchPage.getSearchResultsText().contains(text));
    }

    @When("I select a random search category")
    public void iSelectARandomSearchCategory() {
        List<String> categoryList = homePage.getAllCategoryOptions();
        randomCategory = getRandomStringFromList(categoryList);
        homePage.selectByVisibleTextFromSearchCategoriesBox(randomCategory);
    }

    @When("I sort by {string}")
    public void iSortBy(String text) {
        searchPage.sortSearchListByType(text);
    }

    @Then("The price of first product should be less or equal to the {string}")
    public void thePriceOfFirstProductShouldBeLessOrEqualToTheMaximumIntervalValue(String maxValue) {
        assertTrue(Double.parseDouble(maxValue) >= Double.parseDouble(searchPage.getPriceOfFirstProductFromSearchList()),
                "product price " + searchPage.getPriceOfFirstProductFromSearchList() );
    }

    @Then("The price of first product should be greater or equal to the {string}")
    public void thePriceOfFirstProductShouldBeGreaterOrEqualToTheMinimumIntervalValue(String minValue) {
        assertTrue(Double.parseDouble(minValue) <= Double.parseDouble(searchPage.getPriceOfFirstProductFromSearchList()),
                "product price " + searchPage.getPriceOfFirstProductFromSearchList());
    }
}
