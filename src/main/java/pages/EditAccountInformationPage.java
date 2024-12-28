package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class EditAccountInformationPage extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;

    public EditAccountInformationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    public String getFirstNameFieldValue() {
        return elementUtils.getDomAttributeOfElement(firstNameField,"value");
    }

    public String getLastNameFieldValue() {
        return elementUtils.getDomAttributeOfElement(lastNameField,"value");
    }

    public String getEmailFieldValue() {
        return elementUtils.getDomAttributeOfElement(emailField,"value");
    }

    public String getTelephoneFieldValue() {
        return elementUtils.getDomAttributeOfElement(telephoneField,"value");
    }
}
