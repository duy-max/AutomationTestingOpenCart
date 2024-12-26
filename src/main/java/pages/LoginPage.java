package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Register']")
    private WebElement registerOption;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
    private WebElement loginBreadcrumb;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement warningMessage;

    @FindBy(linkText = "Forgotten Password")
    private WebElement forgottenPasswordLink;


    @FindBy(xpath = " //a[@class='list-group-item'][normalize-space()='My Account']")
    private WebElement myAccountRightColumnOption;

    public void clickContinueButton(){
        continueButton.click();
    }

    public void clickRegisterOption(){
        registerOption.click();
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public AccountPage clickLoginButton() {
        loginButton.click();
        return new AccountPage(driver);
    }

    public boolean isLoginBreadcrumbDisplayed() {
        return loginBreadcrumb.isDisplayed();
    }

    public String getWarningMessage(){
        return warningMessage.getText();
    }

    public boolean isForgottenPasswordLinkDisplayed() {
        return forgottenPasswordLink.isDisplayed();
    }
    public ForgottenPasswordPage clickForgottenPasswordLink() {
        forgottenPasswordLink.click();
        return new ForgottenPasswordPage(driver);
    }

    public String getEmailPlaceholder() {
        return emailField.getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return passwordField.getAttribute("placeholder");
    }

    public AccountPage clickOnMyAccountRightColumnOption(){
        myAccountRightColumnOption.click();
        return new AccountPage(driver);
    }

    public String getPasswordFieldType(){
        return passwordField.getAttribute("type");
    }

    public void selectPasswordFieldTextAndCopy(){
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.CONTROL + "c");

    }

    public void pasteCopiedTextIntoEmailField(){
        emailField.sendKeys(Keys.CONTROL + "v");
    }

    public String getTextFromEmailField(){
        return emailField.getAttribute("value");
    }
}
