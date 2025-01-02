package tutorialsninja.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HeaderOptions;
import pages.LandingPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class ProductCompare extends Base {
    public WebDriver driver;
    Properties prop;


    @BeforeMethod
    public void setup() {

        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);

    }

    @Test(priority = 1)
    public void verifyAddingProductForComparisonFromProductDisplayPage() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        searchPage = headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        String expectedToolTip = "Compare this Product";
        productDisplayPage = searchPage.clickOnProductDisplayInSearchResults();
        Assert.assertEquals(productDisplayPage.getToolTipForThisProductOption(), expectedToolTip);
        productDisplayPage.selectCompareProductOption();
        String expectedSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
        Assert.assertEquals(productDisplayPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = productDisplayPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());

    }

    @Test(priority = 2)
    public void verifyAddingProductForComparisonFromListView() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        searchPage = headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage.selectListViewOption();
        String expectedToolTip = "Compare this Product";
        Assert.assertEquals(searchPage.getToolTip(), expectedToolTip);
        searchPage.selectCompareThisProductOption();
        String expectedSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = searchPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());

    }

    @Test(priority = 3)
    public void verifyAddingProductForComparisonFromGridView() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        searchPage = headerOptions.searchForAProduct(prop.getProperty("existingProduct"));
        searchPage.selectGridViewOption();
        String expectedToolTip = "Compare this Product";
        Assert.assertEquals(searchPage.getToolTip(), expectedToolTip);
        searchPage.selectCompareThisProductOption();
        String expectedSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = searchPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());

    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
