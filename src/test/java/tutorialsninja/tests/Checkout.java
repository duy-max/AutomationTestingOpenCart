package tutorialsninja.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.RightColumnOptions;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class Checkout extends Base {
    public WebDriver driver;
    Properties prop;


    @BeforeMethod
    public void setup() {

        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);

    }

    @Test(priority = 1)
    public void verifyCheckoutWithNoProduct() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        shoppingCartPage = headerOptions.selectCheckoutOption();
        String expectedContent = "Your shopping cart is empty!";
        Assert.assertEquals(shoppingCartPage.getEmptyProductContent(), expectedContent);
    }

    @Test(priority = 2)
    public void verifyCheckoutOnLoginWithExistingAddress() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        loginPage = headerOptions.selectLoginOption();
        accountPage = loginPage.loginAccount(prop.getProperty("existingEmail"), prop.getProperty("validPassword"));

        //Search and add product
        headerOptions = new HeaderOptions(accountPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        productDisplayPage.clickOnAddToCartButton();
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();

        //checkout
        checkoutPage = shoppingCartPage.clickOnCheckoutButton();
        Assert.assertTrue(checkoutPage.didWeNavigateToCheckoutPage());
        checkoutPage.clickOnContinueBillingDetails();
        checkoutPage.clickOnContinueDeliveryDetails();
        checkoutPage.clickOnContinueDeliveryMethod();
        checkoutPage.clickOnTermsAndConditionsCb();
        checkoutPage.clickOnContinuePaymentMethod();
        orderSuccess = checkoutPage.clickOnConfirmOrderBtn();
        Assert.assertTrue(orderSuccess.didWeNavigateToOrderSuccessPage());
        String expectedHeading = "Your order has been placed!";
        Assert.assertEquals(orderSuccess.getOrderSuccessHeading(), expectedHeading);
    }


    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
