package component;

import config.ApplicationContextConfig;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TheDriver extends ApplicationContextConfig {

    public WebDriver driver;
    @Value("${baseurl}")
    private String baseUrl;

    public TheDriver() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/win/chromedriver.exe");
        }

        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/linux/chromedriver");
        }

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\win\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(chromeOptions);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void get(String url) {
        driver.get(url);
    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public void closeDriver() {
        driver.close();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
