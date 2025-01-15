package openCart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.SearchPage;
import openCart.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class Search extends Base {
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
    public void verifySearchWithValidProduct() {
    landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("existingProduct"));
    searchPage = landingPage.clickOnSearchButton();
    Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
    Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());

    }

    @Test(priority = 2)
    public void verifySearchWithNonExistingProduct() {
        landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("nonExistingProduct"));
        searchPage = landingPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getNoProductMessage(), expectedMessage);
    }

    @Test(priority = 3)
    public void verifySearchWithoutEnteringAnyProduct() {
        searchPage = landingPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getNoProductMessage(), expectedMessage);
    }
    @Test(priority = 4)
    public void verifySearchAfterLogin() {
        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"), prop.getProperty("validPassword"));
        landingPage = new LandingPage(accountPage.getDriver());
        landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("existingProduct"));
        searchPage = landingPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());
    }
    @Test(priority = 5)
    public void verifySearchResultingInMultipleProducts() {
        landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        searchPage = landingPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 0);
    }
    @Test(priority = 6)
    public void verifySearchFieldsPlaceholders() {
            headerOptions = new HeaderOptions(landingPage.getDriver());
        String expectedSearchBoxPlaceHolderText = "Search";
            Assert.assertEquals(headerOptions.getPlaceHolderTextOfSearchBoxField(),expectedSearchBoxPlaceHolderText );
            searchPage = headerOptions.clickOnSearchButton();
        String expectedSearchCriteriaFieldPlaceHolderText = "Keywords";
        Assert.assertEquals(searchPage.getPlaceHolderTextOfSearchCriteriaField(), expectedSearchCriteriaFieldPlaceHolderText);

    }

    @Test(priority = 7)
    public void verifySearchingForProductUsingSearchCriteriaField() {

        headerOptions = new HeaderOptions(driver);
        searchPage = headerOptions.clickOnSearchButton();
        searchPage.enterIntoSearchCriteriaField(prop.getProperty("existingProduct"));
        searchPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());

    }

    @Test(priority = 8)
    public void verifySearchingForProductUsingSomeTextInProductDescription() {

        headerOptions = new HeaderOptions(driver);
        searchPage = headerOptions.clickOnSearchButton();
        searchPage.enterIntoSearchCriteriaField(prop.getProperty("termInProductDescription"));
        searchPage.selectSearchInProductDescriptionCheckboxBoxField();
        searchPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.isProductHavingDescriptionTextDisplayedInSearchResults());

    }
    @Test(priority = 9)
    public void verifySearchBySelectingSubCategory() {
        headerOptions = new HeaderOptions(driver);
        searchPage = headerOptions.clickOnSearchButton();
        searchPage.enterIntoSearchCriteriaField(prop.getProperty("exitingProductInSubCategory"));
        searchPage.selectOptionFromCategoryDropdownField(prop.getProperty("exitingSubCategory"));
        searchPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());
    }


    @Test(priority = 10)
    public void verifySearchByUsingParentCategoryAndSearchInSubCategoriesOption() {
        headerOptions = new HeaderOptions(driver);
        searchPage = headerOptions.clickOnSearchButton();
        searchPage.enterIntoSearchCriteriaField(prop.getProperty("exitingProductInSubCategory"));
        searchPage.selectOptionFromCategoryDropdownField(prop.getProperty("exitingCategory"));
        searchPage.clickOnSearchButton();
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getNoProductMessage(), expectedMessage);
        searchPage.selectSearchInSubCategoriesCheckboxField();
        searchPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());

    }
    @Test(priority = 11)
    public void verifyProductInSearchResultsInListViewAndGridView() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exitingProductInSubCategory"));
        searchPage = headerOptions.clickOnSearchButton();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());

        //Click ListView
        searchPage.selectListViewOption();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
        searchPage.selectAddToCartOption();
        String expectedMessage = "Success: You have added iMac to your shopping cart!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
        searchPage.selectAddToWishListOption();
        expectedMessage = "You must login or create an account to save iMac to your wish list!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
        searchPage.selectCompareThisProductOption();
        expectedMessage = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
        productDisplayPage = searchPage.selectProductImage();
        Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
        driver = navigateBack(productDisplayPage.getDriver());
        productDisplayPage = searchPage.selectProductUsingName();
        Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
        driver = navigateBack(productDisplayPage.getDriver());

        //click Gridview
        searchPage = new SearchPage(driver);
        searchPage.selectGridViewOption();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
        searchPage.selectAddToCartOption();
        expectedMessage = "Success: You have added iMac to your shopping cart!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
        searchPage.selectAddToWishListOption();
        expectedMessage = "You must login or create an account to save iMac to your wish list!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
        searchPage.selectCompareThisProductOption();
        expectedMessage = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
        productDisplayPage = searchPage.selectProductImage();
        Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
        driver = navigateBack(productDisplayPage.getDriver());
        productDisplayPage = searchPage.selectProductUsingName();
        Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
        driver = navigateBack(productDisplayPage.getDriver());
    }

    @Test(priority = 12)
    public void verifyMultipleProductsInSearchResultsInListViewAndGridView() {

        headerOptions = new HeaderOptions(driver);
        headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        searchPage = headerOptions.clickOnSearchButton();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);
        searchPage.selectListViewOption();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);
        searchPage.selectGridViewOption();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);

    }

    @Test(priority = 13)
    public void verifyNavigationToProductComparePage() {

        headerOptions = new HeaderOptions(driver);
        headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        productComparisonPage = searchPage.selectProductCompareLink();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());

    }

    @Test(priority = 14)
    public void verifySortingProductsInSearchResultsPage() {
        headerOptions = new HeaderOptions(driver);
        headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        searchPage = headerOptions.clickOnSearchButton();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);
       //Name a-z
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionOne"));
        Assert.assertEquals(searchPage.getFirstProductName(), "iMac");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getFourthProductName(), "MacBook Pro");
        //Name z-a
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionTwo"));
        Assert.assertEquals(searchPage.getFirstProductName(), "MacBook Pro");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook");
        Assert.assertEquals(searchPage.getFourthProductName(), "iMac");
        //Price High - Low
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionThree"));
        Assert.assertEquals(searchPage.getFirstProductName(), "iMac");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getFourthProductName(), "MacBook Pro");
        //Price Low - High
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionFour"));
        Assert.assertEquals(searchPage.getFirstProductName(), "MacBook Pro");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook");
        Assert.assertEquals(searchPage.getFourthProductName(), "iMac");
    }

    @Test(priority = 15)
    public void verifyNavigatingToSearchPageFromSiteMapPage() {
        footerOptions = new FooterOptions(landingPage.getDriver());
        siteMapPage = footerOptions.clickOnSiteMapFooterOption();
        searchPage = siteMapPage.clickOnSearchLink();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
    }

    @Test(priority = 16)
    public void verifySearchPageBreadcrumb() {

        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchButton();
        searchPage = searchPage.selectSearchBreadcrumbOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        headerOptions = new HeaderOptions(searchPage.getDriver());
        landingPage = headerOptions.clickOnHomeBreadcrumb();
        Assert.assertEquals(getPageURL(landingPage.getDriver()), prop.getProperty("landingPageURL"));

    }

    @Test(priority = 17)
    public void verifySearchFunctionalityUsageByUsingMouse() throws InterruptedException {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        searchPage = headerOptions.searchForAProductUsingKeyboardKeys(prop.getProperty("exitingProductInSubCategory"));
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);

        searchPage = searchPage.searchUsingSearchCriteriaFieldInSearchResultsPageUsingKeyboardKeys(
                prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 4);

        searchPage = searchPage.verifySearchingByCategoryUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 2);

        searchPage = searchPage.verifySearchingInSubcategoriesUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 3);

        searchPage = searchPage
                .verifySearchingUsingDescriptionUsingKeyboardKeys(prop.getProperty("termInProductDescription"));
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);

        searchPage = searchPage.verifySearchingInListViewUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);

        searchPage = searchPage.verifySearchingInGridViewUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
        productComparisonPage = searchPage.verifyNavigatingToProductComparedPageUsingKeyboardKeys();
        Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
        driver = navigateBack(productComparisonPage.getDriver());

        searchPage = new SearchPage(driver);
        searchPage = searchPage.verifySortInSearchPageUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);

        searchPage = searchPage.verifyProductsCountInSearchPageUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
    }

    @Test(priority = 18)
    public void verifySearchFunctionalityHeadingURLAndTitle() {

        searchPage = landingPage.clickOnSearchButton();
        Assert.assertEquals(searchPage.getSearchPageHeading(), prop.getProperty("searchPageHeading"));
        Assert.assertEquals(getPageTitle(searchPage.getDriver()), prop.getProperty("searchPageTitle"));
        Assert.assertEquals(getPageURL(searchPage.getDriver()), prop.getProperty("searchPageURL"));

    }

    @Test(priority=19)
    public void verifySearchFunctionalityUI() {

        searchPage = landingPage.clickOnSearchButton();
        if(prop.getProperty("browserName").equals("chrome")) {
            CommonUtils.takeScreenshot(searchPage.getDriver(),"\\Screenshots\\actualChromeSearchPageUI.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualChromeSearchPageUI.png",System.getProperty("user.dir")+"\\Screenshots\\expectedChromeSearchPageUI.png"));
        }else if(prop.getProperty("browserName").equals("firefox")) {
            CommonUtils.takeScreenshot(searchPage.getDriver(),"\\Screenshots\\actualFirefoxSearchPageUI.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualFirefoxSearchPageUI.png",System.getProperty("user.dir")+"\\Screenshots\\expectedFirefoxSearchPageUI.png"));
        }else if(prop.getProperty("browserName").equals("edge")) {
            CommonUtils.takeScreenshot(searchPage.getDriver(),"\\Screenshots\\actualEdgeSearchPageUI.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualEdgeSearchPageUI.png",System.getProperty("user.dir")+"\\Screenshots\\expectedEdgeSearchPageUI.png"));
        }
    }

    @Test(priority=20)
    public void verifySearchInAllEnvironments() {

        landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("existingProduct"));
        searchPage = landingPage.clickOnSearchButton();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());

    }
    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
