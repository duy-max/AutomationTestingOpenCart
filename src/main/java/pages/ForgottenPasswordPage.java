package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class ForgottenPasswordPage extends RootPage {
    WebDriver driver;
ElementUtils elementUtils;
    public ForgottenPasswordPage(WebDriver driver) {
       super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
    private WebElement forgottenPasswordBreacrumb;

    public boolean didWeNavigateToForgottendPasswordPage() {
        return elementUtils.isElementDisplayed(forgottenPasswordBreacrumb);
    }
}
