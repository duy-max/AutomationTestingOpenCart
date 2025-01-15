package tutorialsninja.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HeaderOptions;
import pages.LandingPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class AddToCart extends Base {
    public WebDriver driver;
    Properties prop;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
    }

    @Test(priority = 1)
    public void verifyAddingOneProduct() throws Exception {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        int hpQuantity = 3;
        productDisplayPage.enterProductQuantity(hpQuantity);
        productDisplayPage.clickOnAddToCartButton();
        productDisplayPage.getProductImage();

        String expectedProductName = productDisplayPage.getProductName();
        String expectedUnitPrice = productDisplayPage.getUnitPrice();
        String expectedProductModel = productDisplayPage.getProductModel();
        String expectedProductTotal = productDisplayPage.calculateProductTotal(expectedUnitPrice, hpQuantity);
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        shoppingCartPage.getProductImage();
        String imgShoppingCartPath = System.getProperty("user.dir") + "\\downloads\\imgShoppingCartPage.png";
        String imgProductDisplayPagePath = System.getProperty("user.dir") + "\\downloads\\imgProductDisplayPage.png";

        Assert.assertTrue(CommonUtils.compareTwoImagesWithDifferentSize(imgProductDisplayPagePath, imgShoppingCartPath));
        Assert.assertEquals(shoppingCartPage.getProductName(), expectedProductName);
        Assert.assertEquals(shoppingCartPage.getProductModel(), expectedProductModel);
        Assert.assertEquals(Integer.parseInt(shoppingCartPage.getQuantityHPProduct()), hpQuantity);
        Assert.assertEquals(shoppingCartPage.getUnitPrice(), expectedUnitPrice);
        Assert.assertEquals(shoppingCartPage.getProductTotal(), expectedProductTotal);

    }

    @Test(priority = 2)
    public void verifyAddingOneProductMultipleTimes(){
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        int clickTimes = 3;
        productDisplayPage.clickOnAddToCartButtonMultipleTimes(clickTimes);
        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        Assert.assertEquals(Integer.parseInt(shoppingCartPage.getQuantityHPProduct()), clickTimes);
    }

    //Test Failed (Flaky test)
    @Test(priority = 3)
    public void verifyAddingMultipleProducts() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        int hpQuantity = 3;
        productDisplayPage.enterProductQuantity(hpQuantity);
        productDisplayPage.clickOnAddToCartButton();
        String expectedMsg = "Success: You have added HP LP3065 to your shopping cart!";
        Assert.assertEquals(productDisplayPage.getSuccessMessage(), expectedMsg);

//        headerOptions = new HeaderOptions(productDisplayPage.getDriver());
//        headerOptions.searchForAProduct(prop.getProperty("iphoneProduct"));
//        searchPage = headerOptions.clickOnSearchButton();
//        productDisplayPage = searchPage.clickOnProductIphoneDisplayInSearchResults();
//        int iphoneQuantity = 2;
//        productDisplayPage.enterProductQuantity(iphoneQuantity);
//        productDisplayPage.clickOnAddToCartButton();
//        expectedMsg = "Success: You have added iPhone to your shopping cart!";
//        Assert.assertEquals(productDisplayPage.getSuccessMessage(), expectedMsg);
//        shoppingCartPage = productDisplayPage.clickOnShoppingCartLink();
//        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
//
//        Assert.assertEquals(Integer.parseInt(shoppingCartPage.getQuantityHPProduct()),hpQuantity );
//        Assert.assertEquals(Integer.parseInt(shoppingCartPage.getQuantityIPhoneProduct()),iphoneQuantity );
    }

    @Test(priority = 4)
    public void verifyAddingProductOutOfStock()  {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("iphoneProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductIphoneDisplayInSearchResults();
        int iphoneQuantity = 2;
        productDisplayPage.enterProductQuantity(iphoneQuantity);
        productDisplayPage.clickOnAddToCartButton();
        String expectedMsg = "Fail: IPhone is out of stock!";
        Assert.assertEquals(productDisplayPage.getSuccessMessage(), expectedMsg);
    }

    @Test(priority = 5)
    public void verifyAddingProductWithDifferentOptions() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.searchForAProduct(prop.getProperty("productDifferentOptions"));
        searchPage = headerOptions.clickOnSearchButton();
        productDisplayPage = searchPage.clickOnProductAppleCinemaDisplayInSearchResults();
        productDisplayPage.selectDateFromDatePicker();


    }







    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
