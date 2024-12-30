package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class HeaderOptions extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;
    public HeaderOptions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a/i[@class='fa fa-phone']")
    private WebElement phoneIconOption;

    @FindBy(xpath = "//a/i[@class='fa fa-heart']")
    private WebElement heartIconOption;

    @FindBy(xpath = "//span[text()='Shopping Cart']")
    private WebElement shoppingCartOption;

    @FindBy(xpath = "//span[text()='Checkout']")
    private WebElement checkoutOption;

    @FindBy(xpath = "//div[@id='logo']//a")
    private WebElement logoOption;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
    private WebElement accountBreadcrumb;

    @FindBy(xpath = "//i[@class='fa fa-home']")
    private WebElement homeBreadcrumb;

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    @FindBy(linkText = "Logout")
    private WebElement logoutOption;

    @FindBy(name = "search")
    private WebElement searchBoxField;

    public String getPlaceHolderTextOfSearchBoxField() {
        return elementUtils.getDomAttributeOfElement(searchBoxField,"placeholder");
    }

    public void enterProductIntoSearchBoxField(String productName) {
        elementUtils.enterTextIntoElement(searchBoxField, productName);
    }

    public boolean isLogoutOptionAvailable() {
        return elementUtils.isElementDisplayed(logoutOption);
    }

    public boolean isLoginOptionAvailable() {
        return elementUtils.isElementDisplayed(loginOption);
    }

    public LogoutPage selectLogoutOption() {
        elementUtils.clickOnElement(logoutOption);
        return new LogoutPage(driver);
    }

    public LoginPage selectLoginOption() {
        elementUtils.clickOnElement(loginOption);
        return new LoginPage(driver);
    }

    public void clickOnMyAccountDropMenu() {
        elementUtils.clickOnElement(myAccountDropMenu);
    }

    public ContactUsPage selectPhoneIconOption() {
        elementUtils.clickOnElement(phoneIconOption);
        return new ContactUsPage(driver);
    }

    public LoginPage selectHeartIconOption() {
        elementUtils.clickOnElement(heartIconOption);
        return new LoginPage(driver);
    }

    public ShoppingCartPage selectShoppingCartOption() {
        elementUtils.clickOnElement(shoppingCartOption);
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage selectCheckoutOption() {
        elementUtils.clickOnElement(checkoutOption);
        return new ShoppingCartPage(driver);
    }

    public LandingPage clickOnHomeBreadcrumb() {
        elementUtils.clickOnElement(homeBreadcrumb);
        return new LandingPage(driver);
    }

    public LoginPage clickOnAccountBreadcrumb() {
        elementUtils.clickOnElement(accountBreadcrumb);
        return new LoginPage(driver);
    }

    public SearchPage clickOnSearchButton() {
        elementUtils.clickOnElement(searchButton);
        return new SearchPage(driver);
    }

    public LandingPage selectLogoOption() {
        elementUtils.clickOnElement(logoOption);
        return new LandingPage(driver);
    }

    public SearchPage searchForAProductUsingKeyboardKeys(String productName) {
        elementUtils.pressKeyMultipleTimes(Keys.TAB,8);
        elementUtils.enterTextIntoFieldUsingKeyboardKeys(productName);
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

}
