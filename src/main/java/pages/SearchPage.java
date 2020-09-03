package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static Utils.Constants.TIMEOUT;

@Component
public class SearchPage {

    private final Logger log = LoggerFactory.getLogger(SearchPage.class);
    @Autowired
    BasePage basePage;

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = ".//span[@class='b-breadcrumb__text b-link--tertiary truncate']")
    WebElement breadcrumbTitle;
    @FindBy(how = How.CSS, using = "button[aria-label*='see all - Brand']")
    WebElement seeAllFilterButton;
    @FindBy(how = How.ID, using = "s0-13-11-0-1-2-6")
    WebElement filtersPanel;
    @FindBy(how = How.CSS, using = " #s0-13-11-0-1-2-6-0-6\\[3\\]-0-textrange > div > button")
    WebElement priceFilterSubmitArrow;
    @FindBy(how = How.CSS, using = "a.srp-carousel-list__item-link > div")
    WebElement appliedPriceFilter;
    @FindBy(how = How.CSS, using = "div.srp-sort > span > button")
    WebElement sortByButton;
    @FindBy(how = How.CSS, using = "div.srp-controls__control.srp-controls__count > h1 > span:nth-child(2)")
    WebElement searchResults;
    @FindBy(how = How.CSS, using = "#srp-river-results > ul > li:nth-child(3) > div > div.s-item__info.clearfix > div.s-item__details.clearfix > div:nth-child(1) > span")
    WebElement priceOfFirstProduct;

    private String sortType = "div.srp-sort > span div > a:nth-child(%d) > span";
    private String priceFilterBox = "div.x-textrange__block>[aria-label='%s Value in $']";
    private By sortTypeValue = By.cssSelector("div.srp-sort > span div > a > span");


    public SearchPage(BasePage basePage) {
        wait = new WebDriverWait(basePage.driver.getDriver(), TIMEOUT);
        PageFactory.initElements(basePage.driver.getDriver(), this);
    }

    @Step("Getting search category page header")
    public String getSearchCategoryPageHeader() {
        return wait.until(ExpectedConditions.visibilityOf(breadcrumbTitle)).getText();
    }

    @Step("Verifying if see all filter button is displayed")
    public boolean isSeeAllFilterButtonDisplayed() {
        return seeAllFilterButton.isDisplayed();
    }

    @Step("Clicking on see all filter button")
    public void clickSeeAllFilterButton() {
        seeAllFilterButton.click();
    }

    @Step("Set minimum price filter value")
    public void setPriceFilterByTypeAndVelue(String priceType, String value) {
        int count = 0;
        wait.until(ExpectedConditions.visibilityOf(filtersPanel));
        WebElement element = basePage.driver.getDriver().findElement(By.cssSelector(String.format(priceFilterBox, priceType)));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(value);
        log.info("Set " + priceType + " of " + value + " to price filter");
        while (!appliedPriceFilter.getText().contains(value) && count < 5) {
            element.sendKeys(Keys.ENTER);
            count++;
        }
    }

    @Step("Sort search product list by type")
    public void sortSearchListByType(String type) {
        wait.until(ExpectedConditions.visibilityOf(appliedPriceFilter));
        List<String> values = getAllSortTypesValues();
        Integer index = values.indexOf(type) + 1;
        WebElement element = basePage.driver.getDriver().findElement(By.cssSelector(String.format(sortType, index)));
        element.click();
    }

    @Step("Get sort types values")
    public List<String> getAllSortTypesValues() {
        List<String> values = new ArrayList<>();
        int listSize = basePage.driver.getDriver().findElements(sortTypeValue).size();
        log.info("list size " + listSize);
        sortByButton.click();
        for (int i = 1; i <= listSize; i++) {
            String element = basePage.driver.getDriver().findElement(By.cssSelector(String.format(sortType, i))).getText();
            values.add(element);
        }
        log.info("sort values " + values);
        return values;
    }

    @Step("Get search results text")
    public String getSearchResultsText() {
        wait.until(ExpectedConditions.visibilityOf(searchResults));
        return searchResults.getText();
    }

    @Step("Get price of first searched product")
    public String getPriceOfFirstProductFromSearchList() {
        wait.until(ExpectedConditions.visibilityOf(priceOfFirstProduct));
        String priceText = priceOfFirstProduct.getText();
        if (priceText.contains("to")) {
            int index = priceText.indexOf("t");
            log.info("INSIDE " + priceOfFirstProduct.getText().substring(1, index).trim());
            return priceOfFirstProduct.getText().substring(1, index).trim();
        }
        log.info("OUTSIDE " + priceOfFirstProduct.getText().substring(1).trim());
        return priceOfFirstProduct.getText().substring(1).trim();
    }
}
