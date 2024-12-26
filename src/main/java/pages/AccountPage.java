package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Edit your account information")
    private WebElement editYourAccountInformationOption;

    @FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
    private WebElement logoutOption;

    public boolean didWeNavigateToAccountPage() {
        return editYourAccountInformationOption.isDisplayed();
    }

    public boolean isUserLoggedIn() {
        return logoutOption.isDisplayed();
    }

    public LogoutPage clickLogoutOption(){
        logoutOption.click();
        return new LogoutPage(driver);
    }
}

