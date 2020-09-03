package pages;

import component.TheDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static Utils.Constants.TIMEOUT;

@Component
public class BasePage {

    @Autowired
    TheDriver driver;

    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(driver.getDriver());
        actions.moveToElement(webElement);
    }

    public void waitForElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void clickElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
    }

    public void typeValueInTextBox(WebElement webElement, String text) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }
}
