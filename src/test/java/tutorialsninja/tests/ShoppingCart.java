package tutorialsninja.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.ShoppingCartPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class ShoppingCart extends Base {
    public WebDriver driver;
    Properties prop;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
    }

    @Test(priority = 1)
    public void verifyShoppingCartWithEmptyProduct() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        shoppingCartPage = headerOptions.selectShoppingCartOption();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        String expectedContent = "Your shopping cart is empty!";
        Assert.assertEquals(shoppingCartPage.getEmptyProductContent(), expectedContent);

    }

    @Test(priority = 2)
    public void verifyUpdateQuantityOfProduct() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        int quantityUpdated = 3;
        String unitPrice = shoppingCartPage.getUnitPrice();
        String expectedProductTotal = shoppingCartPage.calculateProductTotal(unitPrice, quantityUpdated);
        shoppingCartPage.enterQuantityProduct(quantityUpdated);
        shoppingCartPage.clickOnUpdateQuantityProduct();
        //khởi tạo lại sau khi tải lại trang
        shoppingCartPage = new ShoppingCartPage(landingPage.getDriver());
        String expectedMessage = "Success: You have modified your shopping cart!";
        Assert.assertEquals(Integer.parseInt(shoppingCartPage.getQuantityHPProduct()), quantityUpdated);
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
        Assert.assertEquals(shoppingCartPage.getProductTotal(), expectedProductTotal);

    }

    @Test(priority = 3)
    public void verifyUpdateZeroQuantityOfProduct() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        int quantityUpdated = 0;
        shoppingCartPage.enterQuantityProduct(quantityUpdated);
        shoppingCartPage.clickOnUpdateQuantityProduct();
        //khởi tạo lại sau khi tải lại trang
        shoppingCartPage = new ShoppingCartPage(landingPage.getDriver());
        String expectedMessage = "Your shopping cart is empty!";
        Assert.assertEquals(shoppingCartPage.getEmptyProductContent(), expectedMessage);

    }

    @Test(priority = 4)
    public void verifyUpdateNegativeOrNonNumberQuantityOfProduct() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        int quantityUpdated = -2;
        shoppingCartPage.enterQuantityProduct(quantityUpdated);
        shoppingCartPage.clickOnUpdateQuantityProduct();
        //khởi tạo lại sau khi tải lại trang
        shoppingCartPage = new ShoppingCartPage(landingPage.getDriver());
        String expectedMessage = "Please enter a valid quantity!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
        Assert.assertNotEquals(Integer.parseInt(shoppingCartPage.getQuantityHPProduct()), quantityUpdated);

        String quantityNonNumber = "a";
        shoppingCartPage.enterNonNumberQuantityProduct(quantityNonNumber);
        shoppingCartPage.clickOnUpdateQuantityProduct();
        //khởi tạo lại sau khi tải lại trang
        shoppingCartPage = new ShoppingCartPage(landingPage.getDriver());
        expectedMessage = "Please enter a valid quantity!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
        Assert.assertNotEquals(shoppingCartPage.getQuantityHPProduct(), quantityNonNumber);
    }

    @Test(priority = 5)
    public void verifyRemoveProductFromShoppingCart() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnRemoveProduct();
        Thread.sleep(2000);
        String expectedMessage = "Your shopping cart is empty!";
        Assert.assertEquals(shoppingCartPage.getEmptyProductContent(), expectedMessage);
    }

    @Test(priority = 6)
    public void verifyValidCouponCode() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String validCouponCode = "uit20192024";
        shoppingCartPage.enterCouponCode(validCouponCode);
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Success: Your coupon code has been applied successfully!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
    }

    @Test(priority = 7)
    public void verifyInvalidCouponCode(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String invalidCouponCode = "uit2019";
        shoppingCartPage.enterCouponCode(invalidCouponCode);
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Warning: Coupon is either invalid, expired or reached its usage limit!";
        Assert.assertEquals(shoppingCartPage.getFailMessage(), expectedMessage);
    }

    @Test(priority = 8)
    public void verifyValidExpiredCouponCode(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String expiredCouponCode = "uit2019001";
        shoppingCartPage.enterCouponCode(expiredCouponCode);
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Warning: Coupon is either invalid, expired or reached its usage limit!";
        Assert.assertEquals(shoppingCartPage.getFailMessage(), expectedMessage);
    }

    @Test(priority = 9)
    public void verifyValidCouponReachUsageLimit(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String limitCouponCode = "uit2019002";
        shoppingCartPage.enterCouponCode(limitCouponCode);
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Warning: Coupon is either invalid, expired or reached its usage limit!";
        Assert.assertEquals(shoppingCartPage.getFailMessage(), expectedMessage);
    }

    @Test(priority = 10)
    public void verifyValidCouponForSpecificProduct(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String specificValidCouponCode = "uit2019003";
        shoppingCartPage.enterCouponCode(specificValidCouponCode);
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Success: Your coupon code has been applied successfully!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
    }

    @Test(priority = 11)
    public void verifyValidCouponForMinimumValueOfOrder(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String minimumValidCouponCode = "uit2019004";
        shoppingCartPage.enterCouponCode(minimumValidCouponCode);
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Success: Your coupon code has been applied successfully!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
    }

    @Test(priority = 12)
    public void verifyWithoutEnterCoupon(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        shoppingCartPage.clickOnApplyCouponButton();
        String expectedMessage = "Warning: Please enter a coupon code!";
        Assert.assertEquals(shoppingCartPage.getFailMessage(), expectedMessage);
    }

    @Test(priority = 13)
    public void verifyPlaceHolderCouponField(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnCouponCollapse();
        String expectedMessage = "Enter your coupon here";
        Assert.assertEquals(shoppingCartPage.getPlaceholderCouponCode(), expectedMessage);
    }

    @Test(priority = 14)
    public void verifyEnterMandatoryFieldShippingTax(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnShippingCollapse();
        shoppingCartPage.selectCountry(prop.getProperty("country"));
        shoppingCartPage.selectState(prop.getProperty("state"));
        shoppingCartPage.clickOnButtonGetQuote();
        String expectedModalTitle = "Please select the preferred shipping method to use on this order.";
        Assert.assertEquals(shoppingCartPage.getModalTitleFlatRate(),expectedModalTitle);
        String totalBeforeShip = shoppingCartPage.getShoppingCartTotal();
        shoppingCartPage.clickOnCheckBoxFlatRate();
        shoppingCartPage.clickOnButtonApplyShipping();
        String expectedMessage = "Success: Your shipping estimate has been applied!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
        Assert.assertNotEquals(shoppingCartPage.getShoppingCartTotal(),totalBeforeShip);
        Assert.assertEquals(shoppingCartPage.getShoppingCartTotal(),shoppingCartPage.checkTotalAfterAddShippingFee());
    }

    @Test(priority = 15)
    public void verifyEnterAllFieldShippingTax() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        Thread.sleep(2000);
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnShippingCollapse();
        shoppingCartPage.selectCountry(prop.getProperty("country"));
        shoppingCartPage.selectState(prop.getProperty("state"));
        shoppingCartPage.enterPostcode(prop.getProperty("postCode"));
        shoppingCartPage.clickOnButtonGetQuote();
        String expectedModalTitle = "Please select the preferred shipping method to use on this order.";
        Assert.assertEquals(shoppingCartPage.getModalTitleFlatRate(),expectedModalTitle);
        String totalBeforeShip = shoppingCartPage.getShoppingCartTotal();
        shoppingCartPage.clickOnCheckBoxFlatRate();
        shoppingCartPage.clickOnButtonApplyShipping();
        String expectedMessage = "Success: Your shipping estimate has been applied!";
        Assert.assertEquals(shoppingCartPage.getSuccessMessage(), expectedMessage);
        Assert.assertNotEquals(shoppingCartPage.getShoppingCartTotal(),totalBeforeShip);
        Assert.assertEquals(shoppingCartPage.getShoppingCartTotal(),shoppingCartPage.checkTotalAfterAddShippingFee());
    }

    @Test(priority = 15)
    public void checkShippingTaxWhenFieldsAreEmpty() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        Thread.sleep(1000);
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnShippingCollapse();
        shoppingCartPage.clickOnButtonGetQuote();
        String expectedMsgState = "Please select a region / state!";
        String expectedMsgPostCode = "Postcode must be between 2 and 10 characters!";
        Assert.assertEquals(shoppingCartPage.getWarningMsgState(),expectedMsgState);
        Assert.assertEquals(shoppingCartPage.getWarningMsgPostCode(),expectedMsgPostCode);

    }

    @Test(priority = 16)
    public void checkNumberOfCharactersAllowPostCodeField() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        Thread.sleep(1000);
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnShippingCollapse();
        String expectedMsg = "Postcode must be between 2 and 10 characters!";
        shoppingCartPage.enterPostcode(CommonUtils.generateString(1));

        shoppingCartPage.clickOnButtonGetQuote();
        Thread.sleep(1000);
        Assert.assertEquals(shoppingCartPage.getWarningMsgPostCode(), expectedMsg);

        Thread.sleep(1000);
        shoppingCartPage.enterPostcode(CommonUtils.generateString(2));
        shoppingCartPage.clickOnButtonGetQuote();

        Thread.sleep(1000);
        shoppingCartPage.enterPostcode(CommonUtils.generateString(3));
        shoppingCartPage.clickOnButtonGetQuote();


        Thread.sleep(1000);
        shoppingCartPage.enterPostcode(CommonUtils.generateString(9));
        shoppingCartPage.clickOnButtonGetQuote();

        Thread.sleep(1000);
        shoppingCartPage.enterPostcode(CommonUtils.generateString(10));
        shoppingCartPage.clickOnButtonGetQuote();

        Thread.sleep(1000);
        shoppingCartPage.enterPostcode(CommonUtils.generateString(11));
        shoppingCartPage.clickOnButtonGetQuote();
        Thread.sleep(1000);
        Assert.assertEquals(shoppingCartPage.getWarningMsgPostCode(), expectedMsg);

    }

    @Test(priority = 17)
    public void verifyShippingTaxWhenClickCancel() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        Thread.sleep(1000);
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        shoppingCartPage.clickOnShippingCollapse();
        shoppingCartPage.selectCountry(prop.getProperty("country"));
        shoppingCartPage.selectState(prop.getProperty("state"));
        shoppingCartPage.enterPostcode(prop.getProperty("postCode"));
        shoppingCartPage.clickOnButtonGetQuote();
        shoppingCartPage.clickOnCheckBoxFlatRate();
        shoppingCartPage.clickOnButtonCancel();
        Assert.assertEquals(shoppingCartPage.getShippingFee(), "");
    }




    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }

}
