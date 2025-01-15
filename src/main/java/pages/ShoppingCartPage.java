package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.root.RootPage;
import utils.CommonUtils;
import utils.ElementUtils;

import java.time.Duration;

public class ShoppingCartPage extends RootPage {
    WebDriver driver;
    ElementUtils elementUtils;
    public ShoppingCartPage(WebDriver driver){
       super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Shopping Cart']")
    private WebElement shoppingCartBreadcrumb;

    @FindBy(xpath = "//a[text()='HP LP3065']/../following-sibling::td//input[@type='text']")
    private WebElement quantityHPProduct;

    @FindBy(xpath = "//a[text()='iPhone']/../following-sibling::td//input[@type='text']")
    private WebElement quantityIPhoneProduct;


    @FindBy(xpath = "//div[@id='content']/p")
    private WebElement emptyProductContent;

    //img
    @FindBy(xpath = "//img")
    private WebElement productImage;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")
    private WebElement productName;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]")
    private WebElement productUnitPrice;

    @FindBy(xpath = "//tbody//tr//td[6]")
    private WebElement productTotal;

    @FindBy(xpath = "//a[text()='HP LP3065']/ancestor::tr[1]/td[@class='text-left'][2]")
    private WebElement productModel;



    @FindBy(xpath = "//button[@*='Update']")
    private WebElement quantityUpdateButton;

    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement removeProductButton;


    @FindBy(xpath = "//a[.='Use Coupon Code ']")
    private WebElement couponCollapse;

    @FindBy(xpath = "//input[@id='input-coupon']")
    private WebElement couponCodeInput;

    @FindBy(xpath = "//input[@id='button-coupon']")
    private WebElement applyCouponButton;

    @FindBy(xpath = "//a[normalize-space()='Estimate Shipping & Taxes']")
    private WebElement shippingCollapse;


    @FindBy(xpath = "//select[@id='input-country']")
    private WebElement selectCountry;

    @FindBy(xpath = "//select[@id='input-zone']")
    private WebElement selectState;

    @FindBy(xpath = "//option[contains(.,'Viet Nam')]")
    private WebElement optionCountry;

    @FindBy(xpath = "//option[contains(.,'Ho Chi Minh')]")
    private WebElement optionState;

    @FindBy(xpath = "//input[@id='input-postcode']")
    private WebElement postCode;

    @FindBy(xpath = "//button[@id='button-quote']")
    private WebElement btnGetQuote;


    @FindBy(xpath = "//input[@name='shipping_method']")
    private WebElement checkFlatRate;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement btnCancel;

    @FindBy(xpath = "//input[@id='button-shipping']")
    private WebElement btnApplyShipping;


    @FindBy(xpath = "//h4[@class='modal-title']")
    private WebElement modalTitleFlatRate;


    @FindBy(xpath = "//strong[normalize-space()='Total:']/ancestor::tr/td[2]")
    private WebElement shoppingCartTotal;

    @FindBy(xpath = "//strong[normalize-space()='Sub-Total:']/ancestor::tr/td[2]")
    private WebElement shoppingCartSubTotal;

    @FindBy(xpath = "//strong[normalize-space()='Flat Shipping Rate:']/ancestor::tr/td[2]")
    private WebElement shippingFee;

    //div[normalize-space()='Please select a country!']
    //select[@id='input-zone']/following::div[1]
    @FindBy(xpath = "//select[@id='input-zone']/following::div[1]")
    private WebElement warningMessageState;

    @FindBy(xpath = "//input[@id='input-postcode']/following::div[1]")
    private WebElement warningMessagePostCode;

    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    private WebElement checkoutButton;

    public void clickOnShippingCollapse(){
        elementUtils.clickOnElement(shippingCollapse);
    }

    public String getWarningMsgState(){
        return elementUtils.getTextOfElement(warningMessageState);
    }

    public String getShippingFee(){
        return elementUtils.getTextOfElement(shippingFee);
    }

    public String getWarningMsgPostCode(){
        return elementUtils.getTextOfElement(warningMessagePostCode);
    }

    public String getModalTitleFlatRate(){
       return elementUtils.getTextOfElement(modalTitleFlatRate);
    }

    public String getShoppingCartTotal(){
        return elementUtils.getTextOfElement(shoppingCartTotal);
    }


    public void clickOnButtonGetQuote(){
        elementUtils.clickOnElement(btnGetQuote);
    }

    public void clickOnCheckBoxFlatRate(){
        elementUtils.clickOnElement(checkFlatRate);
    }

    public void clickOnButtonCancel(){
        elementUtils.clickOnElement(btnCancel);
    }

    public void clickOnButtonApplyShipping(){
        elementUtils.clickOnElement(btnApplyShipping);
    }

    public void enterPostcode(String postCodeValue){
        elementUtils.enterTextIntoElement(postCode,postCodeValue);
    }

    public void selectCountry(String country){
        elementUtils.selectElementByVisibleText(selectCountry,country);
    }

    public void selectState(String state){
        elementUtils.selectElementByVisibleText(selectState,state);
    }


 public String checkTotalAfterAddShippingFee(){
        return CommonUtils.checkTotalBySubTotal(elementUtils.getTextOfElement(shoppingCartSubTotal),
                elementUtils.getTextOfElement(shippingFee));
 }


    public String getPlaceholderCouponCode(){
        return elementUtils.getDomAttributeOfElement(couponCodeInput,"placeholder");
    }
    public void clickOnCouponCollapse(){
        elementUtils.clickOnElement(couponCollapse);
    }

    public void enterCouponCode(String couponCode){
        elementUtils.enterTextIntoElement(couponCodeInput,couponCode);
    }

    public void clickOnApplyCouponButton(){
        elementUtils.clickOnElement(applyCouponButton);
    }

    public boolean didWeNavigateToShoppingCartPage() {
        return elementUtils.isElementDisplayed(shoppingCartBreadcrumb);
    }

    public String getQuantityHPProduct() {
        return elementUtils.getDomAttributeOfElement(quantityHPProduct,"value");
    }

    public String getQuantityIPhoneProduct() {
        return elementUtils.getDomAttributeOfElement(quantityIPhoneProduct,"value");
    }

    public String getEmptyProductContent(){
        return elementUtils.getTextOfElement(emptyProductContent);
    }

    public String getUnitPrice(){

        return elementUtils.getTextOfElement(productUnitPrice);
    }

    public String getProductModel(){
        return elementUtils.getTextOfElement(productModel);
    }

    public String getProductName(){
        return elementUtils.getTextOfElement(productName);
    }
    public String getProductTotal(){
        return elementUtils.getTextOfElement(productTotal);
    }

    public void getProductImage() throws Exception {
        String imgURL = elementUtils.getDomAttributeOfElement(productImage, "src");
        CommonUtils.downloadImage(imgURL,System.getProperty("user.dir")+"\\downloads\\imgShoppingCartPage.png");
    }

    public void enterQuantityProduct(int quantity){
        elementUtils.enterTextIntoElement(quantityHPProduct,String.valueOf(quantity));
    }

    public void enterNonNumberQuantityProduct(String quantity){
        elementUtils.enterTextIntoElement(quantityHPProduct,quantity);
    }

    public void clickOnUpdateQuantityProduct(){
        elementUtils.clickOnElement(quantityUpdateButton);
    }

    public void clickOnRemoveProduct(){
        elementUtils.clickOnElement(removeProductButton);
    }

    public String calculateProductTotal( String unitPriceProduct, int quantityProduct) {
        return CommonUtils.calculateTotal(unitPriceProduct, quantityProduct);
    }

    public CheckoutPage clickOnCheckoutButton(){
        elementUtils.clickOnElement(checkoutButton);
        return new CheckoutPage(driver);
    }


}
