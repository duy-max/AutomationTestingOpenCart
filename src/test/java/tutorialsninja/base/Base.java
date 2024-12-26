package tutorialsninja.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v118.indexeddb.model.Key;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import utils.CommonUtils;

import java.time.Duration;
import java.util.Properties;

public class Base {
    WebDriver driver;

    public WebDriver openBrowserAndApplication() {

        Properties prop = CommonUtils.loadProperties();

        String browserName = prop.getProperty("browserName");
        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(prop.getProperty("appURL"));
        return driver;
    }

    public void closeBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    public void pressKeyMultipleTimes(WebDriver driver, Keys keyName, int times){
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(keyName).perform();
        }
    }

    public void enterDetailsIntoLoginPageFields(WebDriver driver){
        Actions actions = new Actions(driver);
        Properties prop = CommonUtils.loadProperties();
        actions.sendKeys(prop.getProperty("existingEmail")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword"))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();
    }

    public void navigateBack(WebDriver driver){
        driver.navigate().back();
    }

    public String getHTMLCodeOfThePage(WebDriver driver){
        return driver.getPageSource();
    }
}
