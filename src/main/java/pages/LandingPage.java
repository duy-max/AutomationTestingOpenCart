package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.root.RootPage;
import utils.ElementUtils;

import java.time.Duration;

public class LandingPage extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[text()='My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(linkText="Register")
    private WebElement registerOption;

    @FindBy(linkText="Login")
    private WebElement loginOption;

    @FindBy(name="search")
    private WebElement searchBoxField;

    @FindBy(xpath="//button[@class='btn btn-default btn-lg']")
    private WebElement searchButton;

    public SearchPage clickOnSearchButton() {
        elementUtils.clickOnElement(searchButton);
        return new SearchPage(driver);
    }

    public void enterProductNameIntoIntoSearchBoxFiled(String productNameText) {
        elementUtils.enterTextIntoElement(searchBoxField, productNameText);
    }

    public RegisterPage navigateToRegisterPage() {
        clickOnMyAccount();
        return selectRegisterOption();
    }

    public LoginPage navigateToLoginPage() {
        clickOnMyAccount();
        return selectLoginOption();
    }

    public LoginPage selectLoginOption() {
        elementUtils.clickOnElement(loginOption);
        return new LoginPage(driver);
    }

    public void clickOnMyAccount() {
        elementUtils.clickOnElement(myAccountDropMenu);
    }

    public RegisterPage selectRegisterOption() {
        elementUtils.clickOnElement(registerOption);
        return new RegisterPage(driver);
    }

}
