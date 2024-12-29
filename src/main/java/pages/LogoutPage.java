package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class LogoutPage extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;
    public LogoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Logout']")
    private WebElement logoutBreadcrumb;

    @FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement pageHeading;

    public String getPageHeading() {
        return elementUtils.getTextOfElement(pageHeading);
    }

    public LandingPage clickOnContinueButton() {
        elementUtils.clickOnElement(continueButton);
        return new LandingPage(driver);
    }

    public boolean didWeNavigateToAccountLogoutPage() {
        return elementUtils.isElementDisplayed(logoutBreadcrumb);
    }

    public void clickOnMyAccountDropMenu() {
        elementUtils.clickOnElement(myAccountDropMenu);
    }

    public LoginPage selectLoginOption() {
        elementUtils.clickOnElement(loginOption);
        return new LoginPage(driver);
    }
}
