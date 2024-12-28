package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {
    WebDriver driver;

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Change Password']")
    private WebElement changePasswordBreadcrumb;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='input-confirm']")
    private WebElement passwordConfirmField;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterPasswordConfirm(String passwordConfirm) {
        passwordConfirmField.sendKeys(passwordConfirm);
    }

    public AccountPage clickContinueButton() {
        continueButton.click();
        return new AccountPage(driver);
    }

    public boolean isChangePasswordBreadcrumbDisplayed() {
        return changePasswordBreadcrumb.isDisplayed();
    }
}
