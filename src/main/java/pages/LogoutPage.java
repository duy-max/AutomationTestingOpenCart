package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Logout']")
    private WebElement logoutBreadcrumb;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Login']")
    private WebElement loginLinkRightColumnOption;

    public boolean isLogoutPageDisplayed() {
        return logoutBreadcrumb.isDisplayed();
    }

    public LoginPage clickOnLoginLinkRightColumnOption(){
        loginLinkRightColumnOption.click();
        return new LoginPage(driver);
    }

}
