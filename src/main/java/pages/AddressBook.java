package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class AddressBook extends RootPage {

    ElementUtils elementUtils;
    WebDriver driver;

    public AddressBook(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Address Book']")
    private WebElement addressBookPageBreadcrumb;

    @FindBy(xpath = "//a[normalize-space()='New Address']")
    private WebElement newAddressBtn;


    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//label[normalize-space()='Address 1']/following::div[1]/input")
    private WebElement address1;

    @FindBy(xpath = "//input[@id='input-city']")
    private WebElement city;

    @FindBy(xpath = "//input[@id='input-postcode']")
    private WebElement postcode;

    @FindBy(xpath = "//select[@id='input-country']")
    private WebElement selectCountry;

    @FindBy(xpath = "//select[@id='input-zone']")
    private WebElement selectState;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueBtn;


    @FindBy(xpath = "//input[@value='1']")
    private WebElement defaultAddressCb;

    public void clickOnNewAddressBtn() {
        elementUtils.clickOnElement(newAddressBtn);
    }

    public void enterFirstName(String text) {
        elementUtils.enterTextIntoElement(firstName, text);
    }

    public void enterLastName(String text) {
        elementUtils.enterTextIntoElement(lastName, text);
    }

    public void enterAddress1(String text) {
        elementUtils.enterTextIntoElement(address1, text);
    }

    public void enterCity(String text) {
        elementUtils.enterTextIntoElement(city, text);
    }

    public void enterPostcode(String text) {
        elementUtils.enterTextIntoElement(postcode, text);
    }

    public void selectCountry(String text) {
        elementUtils.clickOnElement(selectCountry);
        elementUtils.selectElementByVisibleText(selectCountry, text);
    }

    public void selectState(String text) {
        elementUtils.clickOnElement(selectState);
        elementUtils.selectElementByVisibleText(selectState, text);
    }

    public void clickOnContinueBtn() {
        elementUtils.clickOnElement(continueBtn);
    }

    public void checkDefaultAddress() {
        elementUtils.clickOnElement(defaultAddressCb);
    }

    public void enterRequiredFieldAddressBook(String firstName, String lastName, String addressBook1,
                                              String city, String postCode, String country, String state) {
        clickOnNewAddressBtn();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress1(addressBook1);
        enterCity(city);
        enterPostcode(postCode);
        selectCountry(country);
        selectState(state);
        checkDefaultAddress();
        clickOnContinueBtn();

    }


}
