package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class AccountPage extends RootPage {

    ElementUtils elementUtils;
    WebDriver driver;
    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Edit your account information")
    private WebElement editYourAccountInformationOption;

    @FindBy(linkText = "Subscribe / unsubscribe to newsletter")
    private WebElement subscribeUnsubscribeNewsletterOption;


    @FindBy(linkText = "Change your password")
    private WebElement changeYourPassword;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement message;


    public String getMessage() {
        return elementUtils.getTextOfElement(message);
    }

    public ChangePasswordPage clickOnChangeYourPasswordOption() {
        elementUtils.clickOnElement(changeYourPassword);
        return new ChangePasswordPage(driver);
    }

//    public LogoutPage clickOnLogoutOption() {
//        elementUtils.clickOnElement(logoutOption);
//        return new LogoutPage(driver);
//    }
//
//    public boolean isUserLoggedIn() {
//        return elementUtils.isElementDisplayed(logoutOption);
//    }

    public boolean didWeNavigateToAccountPage() {
        return elementUtils.isElementDisplayed(editYourAccountInformationOption);
    }

    public NewsletterPage selectSubscribeUnSubscribeNewsletterOption() {
        elementUtils.clickOnElement(subscribeUnsubscribeNewsletterOption);
        return new NewsletterPage(driver);
    }

    public EditAccountInformationPage clickOnEditYourAccountInformationOption() {
        elementUtils.clickOnElement(editYourAccountInformationOption);
        return new EditAccountInformationPage(driver);
    }
}

