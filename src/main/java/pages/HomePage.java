package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static Utils.Constants.TIMEOUT;

@Component
public class HomePage {

    private final Logger log = LoggerFactory.getLogger(HomePage.class);
    WebDriverWait wait;
    @Autowired
    BasePage basePage;
    @FindBy(how = How.ID, using = "gh-ac")
    WebElement searchBar;
    @FindBy(how = How.ID, using = "gh-btn")
    WebElement searchButton;
    @FindBy(how = How.ID, using = "gh-cat")
    WebElement categoryComboBox;
    @FindBy(how = How.ID, using = "ocs-inflow")
    WebElement helpPopUP;
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Sign in')]")
    WebElement signIn;


    public HomePage(BasePage basePage) {
        wait = new WebDriverWait(basePage.driver.getDriver(), TIMEOUT);
        PageFactory.initElements(basePage.driver.getDriver(), this);
    }

    @Step("Type text in search bar")
    public void typeTextInSearchBar(String text) {
        basePage.typeValueInTextBox(searchBar, text);
    }

    @Step("CLick on search button")
    public void clickSearchButton() {
        basePage.clickElement(searchButton);
    }

    @Step("Selecting {0} category from Categories ComboBox")
    public void selectByVisibleTextFromSearchCategoriesBox(String category) {
        basePage.waitForElement(categoryComboBox);
        Select select = new Select(categoryComboBox);
        select.selectByVisibleText(category);
    }

    @Step("Getting all categories options as a list of string")
    public List<String> getAllCategoryOptions() {
        log.info("Getting all categories options");
        List<String> categoryAsString = new ArrayList<String>();
        List<WebElement> categoryOptions = new ArrayList<>();
        while (categoryOptions.size() < 2) {
            Select select = new Select(categoryComboBox);
            categoryOptions = select.getOptions();
        }
        log.info("Categories options: " + categoryOptions);
        for (WebElement webElement : categoryOptions) {
            categoryAsString.add(webElement.getText());
        }
        return categoryAsString;
    }
}
