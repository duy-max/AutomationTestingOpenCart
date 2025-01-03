package tutorialsninja.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HeaderOptions;
import pages.LandingPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.util.Properties;

import Record.RecordVideo;

public class ProductCompare extends Base {
    public WebDriver driver;
    Properties prop;
//    Record video
//    @BeforeClass
//    public void setupClass() {
//        try {
//            RecordVideo.startRecording("Start recording");
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to start recording: " + e.getMessage(), e);
//        }
//    }
//
//    @AfterClass
//    public void tearDownClass() {
//        try {
//            RecordVideo.stopRecording();
//            System.out.println("Stop recording");
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to stop recording: " + e.getMessage(), e);
//        }
//    }

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
        productDisplayPage.selectCompareThisProductOption();
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
        Assert.assertEquals(searchPage.getToolTipForThisProductOption(), expectedToolTip);
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
        Assert.assertEquals(searchPage.getToolTipForThisProductOption(), expectedToolTip);
        searchPage.selectCompareThisProductOption();
        String expectedSuccessMessage = "Success: You have added HP LP3065 to your product comparison!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = searchPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());
    }

    @Test(priority = 4)
    public void verifyAddingProductForComparisonFromCategoryPage() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.clickOnDesktopsMenu();
        categoryPage = headerOptions.selectShowAllDesktopsOption();
        Assert.assertTrue(categoryPage.didWeNavigateToCategoryBreadcrumb());
        categoryPage.selectCompareThisProductOption();
        String expectedToolTip = "Compare this Product";
        Assert.assertEquals(categoryPage.getToolTipForThisProductOption(), expectedToolTip);
        String expectedSuccessMessage = "Success: You have added Apple Cinema 30\" to your product comparison!";
        Assert.assertEquals(categoryPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = categoryPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());

    }

    @Test(priority = 5)
    public void verifyAddingProductForComparisonFromCategoryPageGridView() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.clickOnDesktopsMenu();
        categoryPage = headerOptions.selectShowAllDesktopsOption();
        Assert.assertTrue(categoryPage.didWeNavigateToCategoryBreadcrumb());
        categoryPage.selectGridViewOption();
        categoryPage.selectCompareThisProductOption();
        String expectedToolTip = "Compare this Product";
        Assert.assertEquals(categoryPage.getToolTipForThisProductOption(), expectedToolTip);
        String expectedSuccessMessage = "Success: You have added Apple Cinema 30\" to your product comparison!";
        Assert.assertEquals(categoryPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = categoryPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());

    }

    @Test(priority = 6)
    public void verifyAddingProductForComparisonFromHomePage() {
        landingPage.selectCompareThisProductOption();
        String expectedToolTip = "Compare this Product";
        Assert.assertEquals(landingPage.getToolTipForThisProductOption(), expectedToolTip);
        String expectedSuccessMessage = "Success: You have added MacBook to your product comparison!";
        Assert.assertEquals(landingPage.getSuccessMessage(), expectedSuccessMessage);
        productComparisonPage = landingPage.clickOnProductComparisonLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        Assert.assertTrue(productComparisonPage.didDetailOfProductGotAddedForComparison());

    }


    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
