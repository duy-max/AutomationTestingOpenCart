package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id ="input-firstname")
    private WebElement firstNameInput;
    @FindBy(id ="input-lastname")
    private WebElement lastNameInput;
    @FindBy(id ="input-email")
    private WebElement emailInput;
    @FindBy(id ="input-telephone")
    private WebElement telephoneInput;
    @FindBy(id ="input-password")
    private WebElement passwordInput;
    @FindBy(id ="input-confirm")
    private WebElement passwordConfirmInput;


    @FindBy(xpath = "//input[@name='newsletter'][@value='1']")
    private WebElement yesNewsletterOption;

    @FindBy(xpath = "//input[@name='newsletter'][@value='0']")
    private WebElement noNewsletterOption;

    @FindBy(name = "agree")
    private WebElement privacyPolicyField;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;

    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;

    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarning;

    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;

    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;

    @FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
    private WebElement passwordConfirmWarning;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement privacyPolicyWarning;

    public void enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }
    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }
    public void enterTelephone(String telephone){
        telephoneInput.sendKeys(telephone);
    }
    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }
    public void enterPasswordConfirm(String passwordConfirm){
        passwordConfirmInput.sendKeys(passwordConfirm);
    }

    public void clickPrivacyPolicyFiled(){
        privacyPolicyField.click();
    }

    public void clickYesNewsletterOption(){
        yesNewsletterOption.click();
    }

    public void clickNoNewsletterOption(){
        noNewsletterOption.click();
    }

    public AccountSuccessPage clickContinueButton(){
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public String getFirstNameWarning(){
        return firstNameWarning.getText();
    }

    public String getLastNameWarning(){
        return lastNameWarning.getText();
    }

    public String getEmailWarning(){
        return emailWarning.getText();
    }

    public String getTelephoneWarning(){
        return telephoneWarning.getText();
    }

    public String getPasswordWarning(){
        return passwordWarning.getText();
    }

    public String getPasswordConfirmWarning(){
        return passwordConfirmWarning.getText();
    }

    public String getPrivacyPolicyWarning(){
        return privacyPolicyWarning.getText();
    }

}
