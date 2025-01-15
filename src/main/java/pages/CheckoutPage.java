package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class CheckoutPage extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;
    public CheckoutPage(WebDriver driver)  {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Checkout']")
    private WebElement checkoutPageBreadcrumb;


    @FindBy(xpath = "//input[@id='button-payment-address']")
    private WebElement continueBillingDetails;

    @FindBy(xpath = "//input[@id='button-shipping-address']")
    private WebElement continueDeliveryDetails;


    @FindBy(xpath = "//input[@id='button-shipping-method']")
    private WebElement continueDeliveryMethod;



    @FindBy(xpath = "//input[@name='agree']")
    private WebElement termsAndConditionsCb;


    @FindBy(xpath = "//input[@id='button-payment-method']")
    private WebElement continuePaymentMethod;


    @FindBy(xpath = "//input[@id='button-confirm']")
    private WebElement confirmOrderBtn;

    public boolean didWeNavigateToCheckoutPage() {
        return elementUtils.isElementDisplayed(checkoutPageBreadcrumb);
    }

    public void clickOnContinueBillingDetails() {
        elementUtils.clickOnElement(continueBillingDetails);
    }

    public void clickOnContinueDeliveryDetails() {
        elementUtils.clickOnElement(continueDeliveryDetails);
    }

    public void clickOnContinueDeliveryMethod() {
        elementUtils.clickOnElement(continueDeliveryMethod);
    }

    public void clickOnTermsAndConditionsCb() {
        elementUtils.clickOnElement(termsAndConditionsCb);
    }

    public void clickOnContinuePaymentMethod() {
        elementUtils.clickOnElement(continuePaymentMethod);
    }

    public OrderSuccess clickOnConfirmOrderBtn() {
        elementUtils.clickOnElement(confirmOrderBtn);
        return new OrderSuccess(driver);
    }
}
